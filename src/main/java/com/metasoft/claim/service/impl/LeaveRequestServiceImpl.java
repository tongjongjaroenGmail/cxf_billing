/**
 * 
 */
package com.metasoft.claim.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metasoft.claim.bean.query.ViewLeaveQuery;
import com.metasoft.claim.controller.vo.ResultVo;
import com.metasoft.claim.controller.vo.ViewLeaveLeaveVo;
import com.metasoft.claim.dao.LeaveDao;
import com.metasoft.claim.dao.LeaveRequestDao;
import com.metasoft.claim.dao.LeaveTypeDao;
import com.metasoft.claim.model.Employee;
import com.metasoft.claim.model.EmployeeStatus;
import com.metasoft.claim.model.Leave;
import com.metasoft.claim.model.LeaveRequest;
import com.metasoft.claim.model.LeaveRequestStatus;
import com.metasoft.claim.service.LeaveRequestService;
import com.metasoft.claim.service.LeaveService;
import com.metasoft.claim.util.DateUtil;

/**
 * @author 
 * 
 */
@Component
public class LeaveRequestServiceImpl extends ModelBasedServiceImpl<LeaveRequestDao, LeaveRequest, Integer> implements
		LeaveRequestService {
	private LeaveRequestDao leaveRequestDao;
	
	@Autowired
	private LeaveService leaveService;

	@Autowired
	private LeaveTypeDao leaveTypeDao;

	@Autowired
	private LeaveDao leaveDao;
	/**
	 * 
	 * @param entityClass
	 */
	@Autowired
	public LeaveRequestServiceImpl(LeaveRequestDao leaveRequestDao) {
		super(leaveRequestDao);
		this.leaveRequestDao = leaveRequestDao;
	}

	public ResultVo validateBeforeAdd(Integer employeeId, Integer leaveTypeId, List<ViewLeaveLeaveVo> viewLeaveLeaveVos) {
		ResultVo resultVo = new ResultVo();
		Hashtable<Integer, Float> hashTotalLeaveEachYear = new Hashtable<Integer, Float>();
		SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);
		List<Date> listDate =   new ArrayList<Date>();
		try {
			for (ViewLeaveLeaveVo viewLeaveLeaveVo : viewLeaveLeaveVos) {
				int year = Integer.parseInt(viewLeaveLeaveVo.getDate().substring(6));
				float totalLeave = 0;
				if(hashTotalLeaveEachYear.get(year) != null){
					totalLeave = hashTotalLeaveEachYear.get(year);
				}
				if(viewLeaveLeaveVo.getAm()){
					totalLeave += 0.5;
				}
				if(viewLeaveLeaveVo.getPm()){
					totalLeave += 0.5;
				}
				
				hashTotalLeaveEachYear.put(year, totalLeave);
				
				listDate.add(format.parse(viewLeaveLeaveVo.getDate()));		
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(Collections.min(listDate));
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
			Date minDate = cal.getTime();
	
			cal.setTime(Collections.max(listDate));
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
			cal.set(Calendar.SECOND,59);
			Date maxDate = cal.getTime();
			
			List<LeaveRequestStatus> statuses = new ArrayList<LeaveRequestStatus>();
			statuses.add(LeaveRequestStatus.NEW);
			statuses.add(LeaveRequestStatus.VIEWED);
			statuses.add(LeaveRequestStatus.APPROVED);
	
			List<Leave> leaves = leaveDao.searchByEmployeeIdAndDateAndStatuses(employeeId, minDate, maxDate, statuses);
			for (Leave leave : leaves) {
				String leaveDateDB = format.format(leave.getDate());
				for (ViewLeaveLeaveVo viewLeaveLeaveVo : viewLeaveLeaveVos) {
					if(leaveDateDB.equals(viewLeaveLeaveVo.getDate()) && ((leave.getAm() && viewLeaveLeaveVo.getAm()) || (leave.getPm() && viewLeaveLeaveVo.getPm()))){
						resultVo.setMessage("This request has time duplicate with other request. Please refresh page.");
						resultVo.setError(true);
						
						return resultVo;
					}
				}
			}
			
			Iterator<Entry<Integer, Float>> it = hashTotalLeaveEachYear.entrySet().iterator();
		    while (it.hasNext()) {
		    	Entry<Integer, Float> pairs = (Entry<Integer, Float>)it.next();
		    	if (leaveService.calcRemainLeaveDays(employeeId, leaveTypeId,pairs.getKey()).floatValue() < pairs.getValue()) {
					resultVo.setMessage(leaveTypeDao.findById(leaveTypeId).getName() + " not enough in " + pairs.getKey() + ".");
					resultVo.setError(true);
					break;
				}
		    }
		} catch (ParseException e) {
			resultVo.setMessage("error : " + e.getMessage());
			resultVo.setError(true);
		}
		return resultVo;
	}

	public ResultVo validateBeforeUpdate(LeaveRequest leaveRequest, Integer newLeaveStatus, Employee loginEmployee) {
		ResultVo resultVo = new ResultVo();
		if (leaveRequest.getStatus().getId() != newLeaveStatus.intValue()) {
			switch (newLeaveStatus.intValue()) {
			case -1:
				if (leaveRequest.getStatus().getId() != 0 && leaveRequest.getStatus().getId() != 1 && leaveRequest.getStatus().getId() != 4) {
					resultVo.setError(true);
					resultVo.setMessage("Leave status is not NEW or VIEWED or CANCELLED.");
				} else if (loginEmployee.getRole().getId().intValue() != 3 && leaveRequest.getRequestEmployee().getId().intValue() != loginEmployee.getId().intValue()) {
					resultVo.setError(true);
					resultVo.setMessage("No permission");
				}
				break;
			case 1:
				if (leaveRequest.getStatus().getId() != 0) {
					resultVo.setError(true);
					resultVo.setMessage("Leave status is not NEW.");
				} else if (loginEmployee.getRole().getId().intValue() != 2) {
					resultVo.setError(true);
					resultVo.setMessage("No permission");
				}
				break;
			case 2:
				if (leaveRequest.getStatus().getId() == 4) {
					resultVo.setError(true);
					resultVo.setMessage("Can't change leave status because this leave cancelled.");
				} else if (loginEmployee.getRole().getId().intValue() != 2) {
					resultVo.setError(true);
					resultVo.setMessage("No permission");
				}
				leaveRequest.setApproveEmployee(loginEmployee);
				break;
			case 3:
				if (leaveRequest.getStatus().getId() == 4) {
					resultVo.setError(true);
					resultVo.setMessage("Can't change leave status because this leave cancelled.");
				} else if (loginEmployee.getRole().getId().intValue() != 2) {
					resultVo.setError(true);
					resultVo.setMessage("No permission");
				}
				leaveRequest.setRejectEmployee(loginEmployee);
				break;
			case 4:
				if (loginEmployee.getRole().getId().intValue() != 3){
					resultVo.setError(true);
					resultVo.setMessage("No permission");
				}
				leaveRequest.setCancelEmployee(loginEmployee);
				break;
			default:
				break;
			}
		}
		return resultVo;
	}
	
	@Override
	public List<LeaveRequest> searchByEmployeeNameAndLeaveTypeAndLeaveDateAndLeaveStatus(String employeeName,
			Integer leaveTypeId, Date leaveDateStart , Date leaveDateEnd,Integer leaveStatus){
		return leaveRequestDao.searchByEmployeeNameAndLeaveTypeAndLeaveDateAndLeaveStatus(employeeName, leaveTypeId,leaveDateStart, leaveDateEnd,leaveStatus);
	}

	@Override
	public List<ViewLeaveQuery> searchViewLeave(Date startDate, Date endDate,List<LeaveRequestStatus> leaveRequestStatuses,List<EmployeeStatus> employeeStatuses) {
		return leaveRequestDao.searchViewLeave( startDate,  endDate, leaveRequestStatuses, employeeStatuses);
	}

	@Override
	public List<LeaveRequest> searchByEmployeeIdAndDateAndStatuses(Integer employeeId, Date startDate, Date endDate,
			List<LeaveRequestStatus> leaveRequestStatuses) {
		return leaveRequestDao.searchByEmployeeIdAndDateAndStatuses(employeeId, startDate, endDate, leaveRequestStatuses);
	}

}

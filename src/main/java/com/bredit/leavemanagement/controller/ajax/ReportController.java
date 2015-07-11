package com.bredit.leavemanagement.controller.ajax;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bredit.leavemanagement.bean.export.HistoryLogExport;
import com.bredit.leavemanagement.controller.vo.HistoryLogDeailVo;
import com.bredit.leavemanagement.controller.vo.HistoryLogVo;
import com.bredit.leavemanagement.controller.vo.StatusResponse;
import com.bredit.leavemanagement.model.Leave;
import com.bredit.leavemanagement.model.LeaveRequest;
import com.bredit.leavemanagement.service.LeaveRequestService;
import com.bredit.leavemanagement.service.impl.DownloadService;
import com.bredit.leavemanagement.service.impl.TokenService;
import com.bredit.leavemanagement.util.DateUtil;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private LeaveRequestService leaveRequestService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private TokenService tokenService;


	@RequestMapping(value="/download/progress")
	public @ResponseBody StatusResponse checkDownloadProgress(@RequestParam String token) {
		return new StatusResponse(true, tokenService.check(token));
	}
	
	@RequestMapping(value="/download/token")
	public @ResponseBody StatusResponse getDownloadToken() {
		return new StatusResponse(true, tokenService.generate());
	}
	
	@RequestMapping(value="/historyLog")
	public void downloadHistoryLog(
			@RequestParam(required = false) String employeeName,
			@RequestParam(required = false) String leaveDate, 
			@RequestParam(required = false) Integer leaveTypeId , 
			@RequestParam(required = false) Integer leaveStatus,
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String token,
			HttpSession session,
			HttpServletResponse response) throws ParseException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		
		SimpleDateFormat sf = new SimpleDateFormat(DateUtil.DATE_PATTERN_VIEW_DDMMYYYY);

		Date minDate = null;
		Date maxDate = null;
		if (!StringUtils.trimToEmpty(leaveDate).isEmpty()) {
			minDate = sf.parse(leaveDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(minDate);
			calendar.set(Calendar.HOUR_OF_DAY,0); 
			calendar.set(Calendar.MINUTE,0); 
			calendar.set(Calendar.SECOND,0); 
			minDate = calendar.getTime();
			
			calendar.set(Calendar.HOUR_OF_DAY,23); 
			calendar.set(Calendar.MINUTE,59); 
			calendar.set(Calendar.SECOND,59); 
			maxDate = calendar.getTime();
		}
		List<LeaveRequest> leaveRequests = leaveRequestService.searchByEmployeeNameAndLeaveTypeAndLeaveDateAndLeaveStatus(
				employeeName, leaveTypeId, minDate, maxDate , leaveStatus);

		List<HistoryLogExport> historyLogExports = new ArrayList<HistoryLogExport>();
		for (LeaveRequest leaveRequest : leaveRequests) {
			HistoryLogExport historyLogExportTemp = new HistoryLogExport();
			historyLogExportTemp.setEmployeeId(leaveRequest.getRequestEmployee().getId());
			historyLogExportTemp.setEmployeeName(leaveRequest.getRequestEmployee().getFullName());
			historyLogExportTemp.setEmployeeStatus(leaveRequest.getRequestEmployee().getStatus() == null?null:leaveRequest.getRequestEmployee().getStatus().getName());
			historyLogExportTemp.setLeaveRequestStatus(leaveRequest.getStatus().getName());
			historyLogExportTemp.setLeaveRequestId(leaveRequest.getId());
			
			if(leaveRequest.getLeaveType().getParent() != null){
				historyLogExportTemp.setLeaveTypeName(leaveRequest.getLeaveType().getParent().getName() + " (" + leaveRequest.getLeaveType().getName() + ")");
			}else{
				historyLogExportTemp.setLeaveTypeName(leaveRequest.getLeaveType().getName());
			}
			
			historyLogExportTemp.setRequestNote(StringUtils.trimToEmpty(leaveRequest.getRequestNote()));
			historyLogExportTemp.setRejectNote(leaveRequest.getRejectNote());
			if (leaveRequest.getRequestDate() != null) {
				historyLogExportTemp.setRequestDate(leaveRequest.getRequestDate());
			}
			
			for (Leave leave : leaveRequest.getLeaves()) {
				HistoryLogExport historyLogExport = (HistoryLogExport)BeanUtils.cloneBean(historyLogExportTemp);
				historyLogExport.setLeaveDate(leave.getDate());
				historyLogExport.setAm(leave.getAm());
				historyLogExport.setPm(leave.getPm());
				historyLogExport.setFromTime(leave.getFromTime().toString());
				historyLogExport.setToTime(leave.getToTime().toString());
				historyLogExports.add(historyLogExport);
			}
		}
		downloadService.download(
				type, 
				"historyLog", 
				session.getServletContext().getRealPath("/report/historyLog"), 
				new HashMap(), 
				historyLogExports,
				token, 
				response);
	}
}
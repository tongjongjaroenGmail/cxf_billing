/**
 * 
 */
package com.bredit.leavemanagement.controller.ajax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bredit.leavemanagement.controller.vo.HistoryLogDeailVo;
import com.bredit.leavemanagement.controller.vo.HistoryLogVo;
import com.bredit.leavemanagement.model.Leave;
import com.bredit.leavemanagement.model.LeaveRequest;
import com.bredit.leavemanagement.service.LeaveRequestService;
import com.bredit.leavemanagement.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class HistoryLogAjaxController extends BaseAjaxController {
	@Autowired
	private LeaveRequestService leaveRequestService;

	@RequestMapping(value = "/history-log/historyLogList", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody
	String historyLogList(Model model, @RequestParam(required = false) String employeeName,
			@RequestParam(required = false) String leaveDate, @RequestParam(required = false) Integer leaveTypeId , @RequestParam(required = false) Integer leaveStatus)
			throws ParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
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

		List<HistoryLogVo> historyLogVos = new ArrayList<HistoryLogVo>();
		for (LeaveRequest leaveRequest : leaveRequests) {
			HistoryLogVo historyLogVo = new HistoryLogVo();
			historyLogVo.setEmployeeName(leaveRequest.getRequestEmployee().getFullName());
			historyLogVo.setEmployeeStatus(leaveRequest.getRequestEmployee().getStatus() == null?null:leaveRequest.getRequestEmployee().getStatus().getId());
			historyLogVo.setLeaveRequestStatus(leaveRequest.getStatus().getName());
			historyLogVo.setLeaveRequestStatusId(leaveRequest.getStatus().getId());
			
			if(leaveRequest.getLeaveType().getParent() != null){
				historyLogVo.setLeaveTypeName(leaveRequest.getLeaveType().getParent().getName() + " (" + leaveRequest.getLeaveType().getName() + ")");
			}else{
				historyLogVo.setLeaveTypeName(leaveRequest.getLeaveType().getName());
			}
			
			historyLogVo.setRequestNote(StringUtils.trimToEmpty(leaveRequest.getRequestNote()));
			historyLogVo.setRejectNote(leaveRequest.getRejectNote());
			if (leaveRequest.getRequestDate() != null) {
				historyLogVo.setRequestDate(sf.format(leaveRequest.getRequestDate()));
			}
			
			historyLogVo.setHistoryLogDetailVos(new ArrayList<HistoryLogDeailVo>());
			for (Leave leave : leaveRequest.getLeaves()) {
				HistoryLogDeailVo detailVo = new HistoryLogDeailVo();
				detailVo.setDate(sf.format(leave.getDate()));
				detailVo.setAm(leave.getAm());
				detailVo.setPm(leave.getPm());
				detailVo.setFromTime(leave.getFromTime().toString());
				detailVo.setToTime(leave.getToTime().toString());
				historyLogVo.getHistoryLogDetailVos().add(detailVo);
			}

			historyLogVos.add(historyLogVo);
		}

		String json2 = gson.toJson(historyLogVos);
		return json2;
	}
}

package com.metasoft.claim.service.report;


import com.google.gson.Gson;
import com.metasoft.claim.controller.vo.TrackingSearchCriteriaVo;
import com.metasoft.claim.controller.vo.TrackingSearchResultVo;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.claim.ReportService;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExportExcel extends HttpServlet {
    
	//private TrackingServiceImpl trackingService;
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String tableName = req.getParameter("tableName");
            String fileName = req.getParameter("fileName");
            String filterData =  new String(req.getParameter("filterData").getBytes("ISO-8859-1"), "UTF-8"); ;

            String contentType = "application/vnd.ms-excel";
                    
            if (tableName.equalsIgnoreCase("TrackingRPT")) {
                List<TrackingSearchResultVo> dataList;
                if (filterData != null && !filterData.isEmpty()) {
                    Gson gson = new Gson();
                    TrackingSearchCriteriaVo modelSearch = gson.fromJson(filterData, TrackingSearchCriteriaVo.class);
                  //  dataList = trackingService.searchPaging(jobDateStart, jobDateEnd, partyInsurance, maturityDate, claimType, claimNumber, jobStatus, start, length)
                } else {
                    
                }
 
//                req.setAttribute("rowCount", dataList.size() + 5);
//                req.setAttribute("deviceModelList", dataList);

                String headerName = "attachment; filename=\"" + fileName + "\"";
                headerName = new String(headerName.getBytes("TIS620"), "ISO8859-1");
                resp.setContentType(contentType);
                //resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                resp.addHeader("Content-Disposition", headerName);
                RequestDispatcher view = getServletContext().getRequestDispatcher("/jsp/claim/report/trackingRPT.jsp");
                view.forward(req, resp);
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid parameter : table name not match.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect("index.jsp");

        RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp");
        view.forward(req, resp);
    }
}


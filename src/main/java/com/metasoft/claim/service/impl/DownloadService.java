package com.metasoft.claim.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.claim.util.DateToolsUtil;
import com.metasoft.claim.util.ZipUtil;
import com.mysql.jdbc.Connection;

@Service
public class DownloadService {

	protected static Logger logger = Logger.getLogger(DownloadService.class);

	@Autowired
	private ExporterService exporter;

	@Autowired
	private TokenService tokenService;

	public void download(String type, String fileName, String fileJasper, HashMap<String, Object> parameterMap, List<?> listData,
			String token, HttpServletResponse response) {
		try {
			File rptFile = new File(fileJasper + ".jasper");

			JasperReport rtfReport = (JasperReport) JRLoader.loadObject(rptFile);
			if(ExporterService.EXTENSION_TYPE_EXCEL.equals(type)){
				parameterMap.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
			}
			
			JasperPrint rtfPrint = JasperFillManager.fillReport(rtfReport, parameterMap, createDataSource(listData));

			// 6. Create an output byte stream where data will be written
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			// 7. Export report
			exporter.export(type, fileName, rtfPrint, response, baos);

			// 8. Write to reponse stream
			write(token, response, baos);

			baos.close();
		} catch (JRException jre) {
			logger.error("Unable to process download");
			throw new RuntimeException(jre);
		} catch (IOException e) {
			logger.error("Unable to process download");
			throw new RuntimeException(e);
		}
	}

	/**
	 * Writes the report to the output stream
	 */
	private void write(String token, HttpServletResponse response, ByteArrayOutputStream baos) {

		try {
			logger.debug(baos.size());

			// Retrieve output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();

			// Remove download token
			tokenService.remove(token);

			outputStream.close();
		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Writes the report to the output stream
	 */
	public void writeZipFile(List<String> fileList, List<InputStream> inputStreams, OutputStream out,String token) {

		try {
			ZipUtil.zipFileStreams(fileList, inputStreams, out);
			
			// Remove download token
			tokenService.remove(token);
		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
			throw new RuntimeException(e);
		}
	}

	private JRDataSource createDataSource(Collection<?> reportRows) {
		JRBeanCollectionDataSource dataSource;
		dataSource = new JRBeanCollectionDataSource(reportRows);

		return dataSource;
	}
	
	public ByteArrayOutputStream generateReportXLS(Connection conn, String fileJasper, String fileType,
			HashMap parameterMap, String fileName, List lstData) throws ServletException, IOException, JRException,
			Exception {
		ByteArrayOutputStream rtfOutput = new ByteArrayOutputStream();
		File rptFile = null;
		fileName = fileName + fileType;
		try {

			JRDataSource jrDataSource;

			rptFile = new File(fileJasper + ".jasper");
			
			parameterMap.put(JRParameter.REPORT_LOCALE, DateToolsUtil.LOCALE_TH); 

			JasperReport rtfReport = (JasperReport) JRLoader.loadObject(rptFile);
			JasperPrint rtfPrint = null;

			parameterMap.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
			if (lstData == null) {
				rtfPrint = JasperFillManager.fillReport(rtfReport, parameterMap, conn);
			} else {
				jrDataSource = createDataSource(lstData);
				rtfPrint = JasperFillManager.fillReport(rtfReport, parameterMap, jrDataSource);
			}

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rtfPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, rtfOutput);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
			exporter.exportReport();

		} catch (JRException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return rtfOutput;
	}
}

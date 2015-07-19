package com.metasoft.claim.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipUtil {
	private static Logger logger = Logger.getLogger(ZipUtil.class);

	/**
	 * Zip File Input Streams
	 * 
	 * @param inputStreams
	 * @param out
	 * @return
	 */
	public static ZipOutputStream zipFileStreams(List<String> fileList, List<InputStream> inputStreams, OutputStream out) {
		logger.info("Create Zip ...");
		ZipOutputStream zos = null;
		ZipEntry ze;
		InputStream in;
		byte[] buffer = new byte[1024];
		int i = 0;
		try {
			zos = new ZipOutputStream(out);
			for (String file : fileList) {
				logger.info("Add Entry .." + file);
				ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				in = inputStreams.get(i);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
				i++;
			}
			zos.closeEntry();
			// remember close it
			zos.close();
			logger.info("Zip Done!!!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return zos;
	}
}

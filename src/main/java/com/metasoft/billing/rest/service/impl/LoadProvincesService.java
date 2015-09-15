package com.metasoft.billing.rest.service.impl;

import java.io.File;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.billing.bean.xml.Provinces;

@Service
public class LoadProvincesService {
	private ServletContext context;
	private Provinces provinces;

	final static Logger logger = Logger.getLogger(LoadProvincesService.class);

	@Autowired
	public LoadProvincesService(ServletContext context) {
		provinces = new Provinces();
		this.setContext(context);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Provinces.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File folder = new File(context.getRealPath("/WEB-INF/data"));
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				Provinces localProvinces = (Provinces) jaxbUnmarshaller.unmarshal(file);

				provinces.getProvinceMap().putAll(localProvinces.getProvinceMap());
			}
		} catch (JAXBException e) {
			logger.error(e.getMessage());
		}
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public Provinces getProvinces() {
		return provinces;
	}

	public void setProvinces(Provinces provinces) {
		this.provinces = provinces;
	}

}
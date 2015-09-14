package com.metasoft.billing.rest.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.billing.rest.model.DhipRequest;
import com.metasoft.billing.rest.model.DhipResponse;
import com.metasoft.billing.rest.service.DhipManager;

@Service("dhipManagerService")
public class DhipManagerService implements DhipManager{
	final static Logger logger = Logger.getLogger(DhipManagerService.class);
	
	@Autowired
	private LoadProvincesService loadProvincesService;
	
	@Override
	public DhipResponse calcDhip(DhipRequest request) {
		DhipResponse response = new DhipResponse();
		try{
		response.setSurClaim(calcSurClaim(request)); 
		response.setSurDaily(calcSurDaily(request));
		response.setSurInsure(calcSurInsure(request));
		response.setSurInvest(calcSurInvest(request));
		response.setSurOther(calcSurOther(request));
		response.setSurPhoto(calcSurPhoto(request));
		response.setSurTel(calcSurTel(request));
		response.setSurTrans(calcSurTrans(request));
		}catch(Exception e){
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
	private Float calcSurInvest(DhipRequest request){
		Float surInvest = null;
		String areaType = request.getAreaType();
		String claimType = request.getClaimType();
		String serviceType = request.getServiceType();
		String disperse = request.getDisperse();
		String amphur = request.getAmphur();
		String province = request.getProvince();
	
		boolean branchProvince = StringUtils.isBlank(loadProvincesService.getProvinces().getProvinceMap().get(province).getMainProvinceName());
		
		if("กทม".equals(areaType))
		{									
			if(!"ติดตาม".equals(claimType))								
			{								
				if("หน้าร้าน".equals(serviceType) || "ต่อเนื่อง".equals(serviceType) || "บริการ".equals(serviceType))							
				{ surInvest = 300f;}	
				else if ("Y".equals(disperse))							
				{ surInvest = 200f;}							
				else							
				{ surInvest = 600f;}							
			}								
			else								
			{								
				surInvest = 500f;							
			}								
		}									
		else if("ปริมณฑล".equals(areaType))										
		{									
			if(!"ติดตาม".equals(claimType))								
			{								
				if("หน้าร้าน".equals(serviceType) || "บริการ".equals(serviceType))				
				{ surInvest = 350f;}							
				else if ("Y".equals(disperse) || "ต่อเนื่อง".equals(serviceType))						
				{ surInvest = 300f;}							
				else							
				{ surInvest = 700f;}							
			}								
			else								
			{								
				surInvest = 500f;							
			}								
		}									
		else if("ตจว.".equals(areaType))											
		{									
		    if ("Y".equals(disperse))									
			{								
		    	surInvest = 300f;								
		    	return surInvest;								
			}								
											
		    if("หน้าร้าน".equals(serviceType) || "ต่อเนื่อง".equals(serviceType) || "บริการ".equals(serviceType))						
			{								
		    	surInvest = 200f;							
			}								
		    else 								
			{								
				if("เมือง".equals(amphur) && branchProvince)							
				{ surInvest = 400f;}							
				else if (!"เมือง".equals(amphur) && branchProvince)							
				{ surInvest = 500f;}							
				else //นอกจังหวัดที่สาขาตั้งอยู่							
				{ surInvest = 600f;}							
			}															
		}										
		return surInvest;
	}
	
	private Float calcSurTrans(DhipRequest request){
		Float surTrans = null;
		String areaType = request.getAreaType();
		String serviceType = request.getServiceType();
		String province = request.getProvince();
		String amphur = request.getAmphur();
		
		if("กทม".equals(areaType) || "ปริมณฑล".equals(areaType))								
		{								
			surTrans = 0f;							
		}								
		else if ("ตจว.".equals(areaType))									
		{								
			if ("พื้นที่เดียวกัน".equals(serviceType))							
			{							
				surTrans = 200f;						
			}							
			else if ("ต่อเนื่อง".equals(serviceType) || "หน้าร้าน".equals(serviceType) || "บริการ".equals(serviceType)) 							
			{							
				surTrans = 0f;						
			}							
			else							
			{			
				surTrans = loadProvincesService.getProvinces().getProvinceMap().get(province).getAmphorMap().get(amphur).getSurTrans();				
			}							
		}								
		
		return surTrans;
	}
	
	private Float calcSurDaily(DhipRequest request){
		Float surDaily = null;
		String areaType = request.getAreaType();
		Integer dailyCount = request.getDailyCount();
		if("กทม".equals(areaType) || "ปริมณฑล".equals(areaType))									
		{						
			surDaily = (float) (dailyCount * 100); //ปจว.ข้อละ 100 บาท					
		}						
		else if ("ตจว.".equals(areaType))							
		{						
			if(dailyCount.intValue() > 1)					
			{ surDaily = 200f;}					
			else					
			{ surDaily = (float) (dailyCount*100);}					
		}						
		return surDaily;					
	}
	
	private Float calcSurPhoto(DhipRequest request){
		Float surPhoto = null;
		String areaType = request.getAreaType();
		Integer photoCount = request.getPhotoCount();
		if("กทม".equals(areaType) || "ปริมณฑล".equals(areaType))									
		{						
			surPhoto = 0f;			
		}						
		else if ("ตจว.".equals(areaType))							
		{						
			if(photoCount.intValue() > 5)					
			{ surPhoto = 50f;}					
			else					
			{ surPhoto = (float) (photoCount*10);}					
		}						
		return surPhoto;					
	}
	
	private Float calcSurClaim(DhipRequest request){
		Float claimFee = request.getClaimFee();
	
		return (float) (claimFee * 0.2);					
	}
	
	private Float calcSurTel(DhipRequest request){
		Float surTel = null;
		String areaType = request.getAreaType();
		String claimType = request.getClaimType();
		String serviceType = request.getServiceType();
	
		if("กทม".equals(areaType) || "ปริมณฑล".equals(areaType))									
		{						
			surTel = 0f;			
		}						
		else if ("ตจว.".equals(areaType))							
		{			
			if("ติดตาม".equals(claimType) || "หน้าร้าน".equals(serviceType) || "ต่อเนื่อง".equals(serviceType)) 											
			{ surTel = 0f;}					
			else					
			{ surTel = 30f;}					
		}						
		return surTel;					
	}
	
	private Float calcSurInsure(DhipRequest request){
		return request.getInsureFee();				
	}
	
	private Float calcSurOther(DhipRequest request){
		return request.getOtherFee();		
	}
}

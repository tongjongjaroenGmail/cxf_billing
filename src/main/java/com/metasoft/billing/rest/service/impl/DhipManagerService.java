package com.metasoft.billing.rest.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.billing.bean.cont.AreaType;
import com.metasoft.billing.bean.cont.ClaimType;
import com.metasoft.billing.bean.cont.ServiceType;
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
			response.getDhip().setSurClaim(calcSurClaim(request)); 
			response.getDhip().setSurDaily(calcSurDaily(request));
			response.getDhip().setSurInsure(calcSurInsure(request));
			response.getDhip().setSurInvest(calcSurInvest(request));
			response.getDhip().setSurOther(calcSurOther(request));
			response.getDhip().setSurPhoto(calcSurPhoto(request));
			response.getDhip().setSurTel(calcSurTel(request));
			response.getDhip().setSurTrans(calcSurTrans(request));
		}catch(Exception e){
			logger.error(e.toString());
			
			response.getResponseMessage().setSuccess(false);
			response.getResponseMessage().setErrorMessage(e.getMessage() == null?e.toString():e.getMessage());
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
		
		if(AreaType.bkk.getValue().equals(areaType))
		{									
			if(!ClaimType.follow.getValue().equals(claimType))								
			{								
				if(ServiceType.home.getValue().equals(serviceType) || ServiceType.cont.getValue().equals(serviceType) || ServiceType.service.getValue().equals(serviceType))							
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
		else if(AreaType.perimeter.getValue().equals(areaType))										
		{									
			if(!ClaimType.follow.getValue().equals(claimType))								
			{								
				if(ServiceType.home.getValue().equals(serviceType) || ServiceType.service.getValue().equals(serviceType))				
				{ surInvest = 350f;}							
				else if ("Y".equals(disperse) || ServiceType.cont.getValue().equals(serviceType))						
				{ surInvest = 300f;}							
				else							
				{ surInvest = 700f;}							
			}								
			else								
			{								
				surInvest = 500f;							
			}								
		}									
		else if(AreaType.country.getValue().equals(areaType))											
		{									
		    if ("Y".equals(disperse))									
			{								
		    	surInvest = 300f;								
		    	return surInvest;								
			}								
											
		    if(ServiceType.home.getValue().equals(serviceType) || ServiceType.cont.getValue().equals(serviceType) || ServiceType.service.getValue().equals(serviceType))						
			{								
		    	surInvest = 200f;							
			}								
		    else 								
			{			
		    	boolean branchProvince = StringUtils.isBlank(loadProvincesService.getProvinces().getProvinceMap().get(province).getMainProvinceName());
		    	
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
		
		if(AreaType.bkk.getValue().equals(areaType) || AreaType.perimeter.getValue().equals(areaType))								
		{								
			surTrans = 0f;							
		}								
		else if (AreaType.country.getValue().equals(areaType))									
		{								
			if ("พื้นที่เดียวกัน".equals(serviceType))							
			{							
				surTrans = 200f;						
			}							
			else if (ServiceType.cont.getValue().equals(serviceType) || ServiceType.home.getValue().equals(serviceType) || ServiceType.service.getValue().equals(serviceType)) 							
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
		if(AreaType.bkk.getValue().equals(areaType) || AreaType.perimeter.getValue().equals(areaType))									
		{						
			surDaily = (float) (dailyCount * 100); //ปจว.ข้อละ 100 บาท					
		}						
		else if (AreaType.country.getValue().equals(areaType))							
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
		if(AreaType.bkk.getValue().equals(areaType) || AreaType.perimeter.getValue().equals(areaType))									
		{						
			surPhoto = 0f;			
		}						
		else if (AreaType.country.getValue().equals(areaType))							
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
	
		if(AreaType.bkk.getValue().equals(areaType) || AreaType.perimeter.getValue().equals(areaType))									
		{						
			surTel = 0f;			
		}						
		else if (AreaType.country.getValue().equals(areaType))							
		{			
			if(ClaimType.follow.getValue().equals(claimType) || ServiceType.home.getValue().equals(serviceType) || ServiceType.cont.getValue().equals(serviceType)) 											
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

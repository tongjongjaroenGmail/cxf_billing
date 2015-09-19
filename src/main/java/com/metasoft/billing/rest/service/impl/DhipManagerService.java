package com.metasoft.billing.rest.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metasoft.billing.dao.AmphurDao;
import com.metasoft.billing.dao.ProvinceDao;
import com.metasoft.billing.model.AreaType;
import com.metasoft.billing.model.ClaimType;
import com.metasoft.billing.model.ServiceType;
import com.metasoft.billing.rest.model.Dhip;
import com.metasoft.billing.rest.model.DhipRequest;
import com.metasoft.billing.rest.model.DhipResponse;
import com.metasoft.billing.rest.service.DhipManager;

@Service("dhipManagerService")
public class DhipManagerService implements DhipManager{
	final static Logger logger = Logger.getLogger(DhipManagerService.class);
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Autowired
	private AmphurDao amphurDao;

	@Override
	public DhipResponse calcDhip(DhipRequest request) {
		DhipResponse response = new DhipResponse();
		try{
			response.getDhip().setSurClaim(calcSurClaim(request)); 
			response.getDhip().setSurDaily(calcSurDaily(request));
			response.getDhip().setSurInsure(calcSurInsure(request));
			response.getDhip().setSurInvest(calcSurInvest(request));
			response.getDhip().setSurTow(calcSurTow(request));
			response.getDhip().setSurOther(calcSurOther(request));
			response.getDhip().setSurPhoto(calcSurPhoto(request));
			response.getDhip().setSurTel(calcSurTel(request));
			response.getDhip().setSurTrans(calcSurTrans(request));
			
			response.getDhip().setTotal(calcTotal(response.getDhip()));
			response.getDhip().setIncVat(calcIncVat(response.getDhip().getTotal()));
			response.getDhip().setTotalAmount(calcTotalAmount(response.getDhip().getTotal(), response.getDhip().getIncVat()));
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
		int amphur = request.getAmphur();
		int province = request.getProvince();

		if(AreaType.bkk.getName().equals(areaType))
		{					
			if ("Yes".equals(disperse))							
			{ 
				surInvest = 200f;
				return surInvest;
			}	
			if(!ClaimType.follow.getName().equals(claimType))								
			{				
				if(ServiceType.home.getName().equals(serviceType) || ServiceType.cont.getName().equals(serviceType) 
						|| ServiceType.service.getName().equals(serviceType) || ServiceType.sameArea.getName().equals(serviceType))							
				{ surInvest = 300f;}					
				else							
				{ surInvest = 600f;}							
			}								
			else								
			{								
				surInvest = 500f;							
			}								
		}									
		else if(AreaType.perimeter.getName().equals(areaType))										
		{		
			if ("Yes".equals(disperse))							
			{ 
				surInvest = 300f;
				return surInvest;
			}	
			if(!ClaimType.follow.getName().equals(claimType))								
			{								
				if(ServiceType.home.getName().equals(serviceType)) 
				{ surInvest = 350f;}			
				else if (ServiceType.service.getName().equals(serviceType) || ServiceType.cont.getName().equals(serviceType) || ServiceType.sameArea.getName().equals(serviceType))									
				{ surInvest = 300f;}							
				else							
				{ surInvest = 700f;}							
			}								
			else								
			{								
				surInvest = 500f;							
			}								
		}									
		else if(AreaType.country.getName().equals(areaType))											
		{									
		    if ("Yes".equals(disperse))									
			{								
		    	surInvest = 300f;								
		    	return surInvest;								
			}								
											
		    if(ServiceType.home.getName().equals(serviceType) || ServiceType.cont.getName().equals(serviceType) || ServiceType.service.getName().equals(serviceType))						
			{								
		    	surInvest = 200f;							
			}								
		    else 								
			{			
		    	boolean branchProvince = provinceDao.findById(province).getMainBranch();
		    	String amphurName = amphurDao.findById(amphur).getName();
		    	
				if("เมือง".equals(amphurName) && branchProvince)							
				{ surInvest = 400f;}							
				else if (!"เมือง".equals(amphurName) && branchProvince)							
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
		int amphur = request.getAmphur();
		
		if(AreaType.bkk.getName().equals(areaType) || AreaType.perimeter.getName().equals(areaType))								
		{								
			surTrans = 0f;							
		}								
		else if (AreaType.country.getName().equals(areaType))									
		{								
			if (ServiceType.sameArea.getName().equals(serviceType))							
			{							
				surTrans = 200f;						
			}							
			else if (ServiceType.cont.getName().equals(serviceType) || ServiceType.home.getName().equals(serviceType) || ServiceType.service.getName().equals(serviceType)) 							
			{							
				surTrans = 0f;						
			}							
			else							
			{			
				surTrans = amphurDao.findById(amphur).getSurTrans();				
			}							
		}								
		
		return surTrans;
	}
	
	private Float calcSurDaily(DhipRequest request){
		Float surDaily = null;
		String areaType = request.getAreaType();
		Integer dailyCount = request.getDailyCount();
		if(AreaType.bkk.getName().equals(areaType) || AreaType.perimeter.getName().equals(areaType))									
		{						
			surDaily = (float) (dailyCount * 100); //ปจว.ข้อละ 100 บาท					
		}						
		else if (AreaType.country.getName().equals(areaType))							
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
		if(AreaType.bkk.getName().equals(areaType) || AreaType.perimeter.getName().equals(areaType))									
		{						
			surPhoto = 0f;			
		}						
		else if (AreaType.country.getName().equals(areaType))							
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
	
		if(AreaType.bkk.getName().equals(areaType) || AreaType.perimeter.getName().equals(areaType))									
		{						
			surTel = 0f;			
		}						
		else if (AreaType.country.getName().equals(areaType))							
		{			
			if(ClaimType.follow.getName().equals(claimType) || ServiceType.home.getName().equals(serviceType) || ServiceType.cont.getName().equals(serviceType)) 											
			{ surTel = 0f;}					
			else					
			{ surTel = 30f;}					
		}						
		return surTel;					
	}
	
	private Float calcSurInsure(DhipRequest request){
		return request.getInsureFee();				
	}
	
	private Float calcSurTow(DhipRequest request){
		return request.getTowFee();		
	}
	
	private Float calcSurOther(DhipRequest request){
		return request.getOtherFee();		
	}
	
	private Float calcTotal(Dhip dhip){
		Float total = dhip.getSurClaim() + dhip.getSurDaily() + dhip.getSurInsure() + 
			dhip.getSurInvest() + dhip.getSurTow() + dhip.getSurOther() + 
			dhip.getSurPhoto() + dhip.getSurTel() + dhip.getSurTrans();
		return total;		
	}
	
	private Float calcIncVat(Float total){
		return total * 0.7f;		
	}
	
	private Float calcTotalAmount(Float total , Float incVat){
		return total + incVat;		
	}
}

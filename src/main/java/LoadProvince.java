import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.metasoft.billing.bean.xml.Province;
import com.metasoft.billing.bean.xml.Provinces;


public class LoadProvince {

	public static void main(String[] args) throws JAXBException {

		 JAXBContext jaxbContext = JAXBContext.newInstance(Provinces.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    Provinces empMap = (Provinces) jaxbUnmarshaller.unmarshal( new File("E:\\work\\metasoft\\workspace\\cxf_billing\\src\\main\\resources\\data\\branch1.xml") );
		     
		    for(String empId : empMap.getProvinceMap().keySet())
		    {
		    	Province province = empMap.getProvinceMap().get(empId);
		    	province.getAmphorMap();
		        System.out.println(empMap.getProvinceMap().get(empId).getMainProvinceName());
		    }
	}
}

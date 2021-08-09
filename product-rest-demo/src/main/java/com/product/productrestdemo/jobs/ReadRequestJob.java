package com.product.productrestdemo.jobs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.product.productrestdemo.model.Event;
import com.product.productrestdemo.model.Product;
import com.product.productrestdemo.model.Request;
import com.product.productrestdemo.model.RequestDetails;
import com.product.productrestdemo.services.SaveRequestService;

@Component
@ComponentScan(basePackages={"jobs"})
//public class ReadRequestJob implements Job {
public class ReadRequestJob extends QuartzJobBean {
	
	//@Autowired
	//private List<Product> allProductsList = null;
	
	public ReadRequestJob() {
		super();
	}

	private Map<String, Map<String, List<Product>>> parseRequest (Request req) {
		RequestDetails reqDetails = req.getRequestDetails();
		List<Event> events = req. getEvents();
		Map<String, Map<String, List<Product>>> productData = new HashMap<>();
		Map<String, List<Product>>  eventProducts = new HashMap<>();
		//List<Product>  allProductsList = new ArrayList<>();
		for (Event event : events) {
			eventProducts.put(event.getId(), event.getProducts());
			//allProductsList.addAll(event.getProducts());
		}
		productData.put(reqDetails.getSourceCompany(), eventProducts);
		
		return productData;
	}
		   
	private void saveRequest (Request req, JobDataMap jobDataMap) {
		/*ApplicationContext springContext =
				WebApplicationContextUtils.getWebApplicationContext(ContextLoaderListener.getCurrentWebApplicationContext().getServletContext());*/
		List<Product>  allProductsList = new ArrayList<>();
		List<Event> events = req. getEvents();
		for (Event event : events) {
			allProductsList.addAll(event.getProducts());
		}
		SaveRequestService service = (SaveRequestService) jobDataMap.get("service");
		service.setReq(req);
		service.setAllProductsList(allProductsList);
		service.saveProducts();
		service.saveRequestDetails();
		service.saveEvents();
	}
	
    //public void execute(JobExecutionContext context) throws JobExecutionException {
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); 

        Map<String, Map<String, List<Product>>> productData = null;
    	try {  
    		Request req = null;
    		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
    		String filePath = jobDataMap.getString("filePath");
    		File[] files = new File(filePath).listFiles();

    		for (File file : files) {
    		    if (file.isFile()) {
    		    	req = convertXmlToPojo (file);
    		    	convertXMLtoJson(new FileInputStream(file));
    	            productData = parseRequest(req);
    	            saveRequest(req, jobDataMap);
    	    	    System.out.println(productData);
    		    }
    		}
          } catch (Exception e) {  
            e.printStackTrace();  
          }  
       
     }

    private Request convertXmlToPojo (File xmlFile) {
    	Request req = null;
    	JAXBContext jaxbContext;
    	try {
    	    jaxbContext = JAXBContext.newInstance(Request.class);              
    	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();    	 
    	    req = (Request) jaxbUnmarshaller.unmarshal(xmlFile);  	     
    	    //System.out.println(req);
    	} catch (JAXBException e) {
    	    e.printStackTrace();
    	}
    	return req;
    }
    
    public static String convertXMLtoJson(InputStream is) {
		String json="";
        String line;
		StringBuilder sb = new StringBuilder();
		try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }
	        br.close();

			ObjectMapper objectMapper = new XmlMapper();
			ObjectMapper jsonMapper = new ObjectMapper();
			Request req = objectMapper.readValue(sb.toString(), Request.class);
			json =jsonMapper.writeValueAsString(req);
			System.out.println(jsonMapper.writeValueAsString(req));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static String convertXMLtoJson(String inputxml) {
		String json="";
		try {
			ObjectMapper objectMapper = new XmlMapper();
			ObjectMapper jsonMapper = new ObjectMapper();
			Request req = objectMapper.readValue(inputxml, Request.class);
			json =jsonMapper.writeValueAsString(req);
			System.out.println(jsonMapper.writeValueAsString(req));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
    
}
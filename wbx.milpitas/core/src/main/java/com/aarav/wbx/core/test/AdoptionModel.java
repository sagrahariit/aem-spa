package com.aarav.wbx.core.test;

/*
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class AdoptionModel { 
 
    Logger logger = LoggerFactory.getLogger(this.getClass());
 
    private String message;
 
    @SlingObject
    private SlingHttpServletRequest request;
 
    @Inject @Via("resource")
    private String firstName;
 
    @Inject  @Via("resource")
    private String lastName;
           
    @PostConstruct
    protected void init() {
         
        message = "Hello World\n";
               
        if (request != null) {
            this.message += "Request Path: "+request.getRequestPathInfo().getResourcePath()+"\n";
        }
 
        message += "First Name: "+ firstName +" \n";
        message += "Last Name: "+ lastName + "\n";
         
        logger.info("inside post construct");
    }
 
    public String getMessage() {
        return message;
    }
 
    public String getFirstName() {
        return firstName;
    }
     
    public String getLastName() {
        return lastName;
    }
}

/*
@Model(adaptables= {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AdoptationModelTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String message;
	
	@SlingObject
	private SlingHttpServletRequest request;
	
	@Inject @Via("resource")
	private String firstName;
	
	@Inject @Via("resource")
	private String lastName;
	
	@PostConstruct
	protected void init() {
		message = "Hello World";
		
		if(request != null) {
			this.message += "Request path: "+request.getRequestPathInfo().getResourcePath()+"\n";
		}
		message += "First name: "+ firstName+"\n";
		message += "Last name: "+ lastName+"\n";
		
		log.info("Inside post construct");
	}

	public String getMessage() {
		return message;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}*/

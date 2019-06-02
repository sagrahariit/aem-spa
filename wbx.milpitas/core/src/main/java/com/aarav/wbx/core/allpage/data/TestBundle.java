package com.aarav.wbx.core.allpage.data;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.jcr.resource.api.JcrResourceConstants;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class TestBundle { 
 
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
@Model(adaptables=Resource.class)
public class TestBundle {

    @Inject
    private SlingSettingsService settings;

    @Inject @Named(JcrResourceConstants.SLING_RESOURCE_TYPE_PROPERTY) @Default(values="No resourceType")
    protected String resourceType;

    private String message;

    @PostConstruct
    protected void init() {
        message = "\tHello World!\n";
        message += "\tThis is instance: " + settings.getSlingId() + "\n";
        message += "\tResource type is: " + resourceType + "\n";
    }

    public String getMessage() {
        return message;
    }
}*/


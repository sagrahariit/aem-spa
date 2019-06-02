package com.aarav.wbx.core.allpage.data;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.designer.Style;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, adapters = {
		SampleSlingModel.class }, resourceType = "agrahari/components/content/pageinformation", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleSlingModelImpl implements SampleSlingModel {

	Logger log = LoggerFactory.getLogger(this.getClass());
	private String message;

	@SlingObject
	private SlingHttpServletRequest request;

	@Inject
	@Source("script-bindings")
	private Style currentStyle;

	@Inject
	@Via("resource")
	private String firstName;

	@Inject
	@Via("resource")
	private String lastName;

	@Inject
	@Via("resource")
	private String email;

	@PostConstruct
	protected void postConstruct() {
		log.info("inside post construct");
		message = "This is sample project created for demonstrate sling model assignments...";
		if (request != null) {
			this.message += "Request Path: " + request.getRequestPathInfo().getResourcePath() + "\n";
		}
		message += "First Name: " + firstName + " \n";
		message += "Last Name: " + lastName + "\n";
		message += "Email Id: " + email + "\n";
	}

	@Override
	public String getMessage() {
		log.error("****************I am in message Shailesh function*********");
		return message;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getLastName() {
		return lastName;
	}
	
}
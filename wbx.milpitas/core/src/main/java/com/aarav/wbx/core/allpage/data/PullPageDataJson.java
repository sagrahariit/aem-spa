package com.aarav.wbx.core.allpage.data;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Exporters;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.designer.Style;

@Model(adaptables = Resource.class, resourceType = {
		"agrahari/components/content/pageinformation" }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporters({
	@Exporter(name = "jackson", extensions = "json"),
	@Exporter(name = "gson", selector = "hello",	extensions = "json")
})
//http://localhost:8502/content/aarav/en/self/_jcr_content/root/pageinformation.model.hello.json
//@Exporter(name = "jackson", extensions = "json", options = {
//		@ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })
public class PullPageDataJson {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private String message;

	@SlingObject
	private SlingHttpServletRequest request;

	@Inject
	private Style currentStyle;

	@Inject
	private String firstName;

	@Inject
	private String lastName;

	@Inject
	private String email;

	@PostConstruct
	protected void postConstruct() {
		logger.info("inside post construct");
		message = "This is sample project created for demonstrate sling model assignments...";
		if (request != null) {
			this.message += "Request Path: " + request.getRequestPathInfo().getResourcePath() + "\n";
		}
		message += "First Name: " + firstName + " \n";
		message += "Last Name: " + lastName + "\n";
		message += "Email Id: " + email + "\n";
	}

	
	public String getMessage() {
		return message;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
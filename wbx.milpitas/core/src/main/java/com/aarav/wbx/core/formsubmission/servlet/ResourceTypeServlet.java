package com.aarav.wbx.core.formsubmission.servlet;

import java.util.UUID;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.jackrabbit.oak.commons.json.JsonObject;
//import org.apache.jackrabbit.oak.commons.json.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.PageManager;


@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.resourceTypes="+ "agrahari/components/content/claimsubmit",
        "sling.servlet.extensions=" + "submit"
})

@Designate(ocd = ResourceTypeServlet.Configurations.class)
public class ResourceTypeServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Session session;
	
	@Reference
	ResourceResolver resourceResolver;
	
	@Reference
	PageManager pageManager;
	
	@Reference
	private SlingRepository repository;
	
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		
		try {
			String id = UUID.randomUUID().toString();
			String fName = request.getParameter("name");
			String lName = request.getParameter("lastname");
			
			JsonObject obj = new JsonObject();
			
			
		}catch(Exception e) {
			
		}
	}
	
	
	
	
	@ObjectClassDefinition(name = "Resource Type Demo Servlet")
	public @interface Configurations {
		@AttributeDefinition(name = "Enter Path", description = "Page or node path")
		String getPath();
	}
	
	
	
	
	
	/*
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.write("<html><body>");
		out.write("<h1>Welcome Shailesh</h1>");
		out.write("</html></body>");
		out.flush();
		out.close();
	}

	@Activate
	@Modified
	protected void Activate(Configurations config) {

	}*/

}
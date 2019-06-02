package com.aarav.wbx.core.addnode.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrUtil;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=To create node in Page",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/createnode",
		"sling.servlet.extensions=" + "txt"

})
public class CreateNodeInPage extends SlingAllMethodsServlet {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private Session session;

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	String rootPagePath = "/content/aarav/fr";
	String nodeName = "alka";

	@Override
	public void doPost(SlingHttpServletRequest req, SlingHttpServletResponse res) {

	/************	Creating node thru JCR api  ************ */
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, "datawrite");
			ResourceResolver resolver = null;
			try {
				resolver = resolverFactory.getServiceResourceResolver(param);
			} catch (LoginException e) {
				log.error("Error :" + e);
			}
			session = resolver.adaptTo(Session.class);
			log.info("Created session");
			Node pageNode = JcrUtil.createPath(rootPagePath + "/" + nodeName, "cq:Page", session);
			Node pageContentNode = pageNode.addNode("jcr:content");
			pageContentNode.setPrimaryType("cq:PageContent");
			pageContentNode.setProperty("sling:resourceType", "my/resource/type");
			session.save();
		} catch (RepositoryException e) {
			log.error("Error while creating node " + nodeName, e);
		} finally {
			session.logout();
			log.info("--- In doPost of create Node---- before try---");

		}
		
		/************	Creating node thru Sling api  ************ */	
		
		

	}
}

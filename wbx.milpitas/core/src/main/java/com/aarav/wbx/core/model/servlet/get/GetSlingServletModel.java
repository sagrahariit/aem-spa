package com.aarav.wbx.core.model.servlet.get;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model( adaptables = { SlingHttpServletRequest.class }, resourceType = "agrahari/components/content/wbx2-home-body", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GetSlingServletModel {
 //static final String RESOURCE_TYPE = "acs-samples/components/content/sling-model/component-exporter";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	

	@Self
	private SlingHttpServletRequest request;

	@Self
	@Via("resource")
	private Resource resource;

	@ValueMapValue
	@Named("jcr:title")
	@Required
	private String title;

	@ValueMapValue
	@Optional
	private String pageTitle;

	@ValueMapValue
	@Optional
	private String navTitle;

	@ValueMapValue
	@Named("jcr:created")
	private Calendar createdAt;

	@ValueMapValue
	@Named("jcr:description")
	@Default(values = "No Descriptioon Provided Shaielsh")
	private String description;

	@ValueMapValue
	@Default(booleanValues = false)
	boolean navRoot;

	@Inject
	private QueryBuilder quesryBuilder;

	@SlingObject
	private ResourceResolver resourceResolver;

	private long size;

	private Page page;

	@PostConstruct
	private void init() {
		
		log.info("----------------Agrahari-------------in init");

		page = resourceResolver.adaptTo(PageManager.class).getContainingPage(resource);

		final Map<String, String> map = new HashMap<>();
		map.put("path", page.getPath());
		map.put("type", "cq:Page");

		Query query = quesryBuilder.createQuery(PredicateGroup.create(map), resourceResolver.adaptTo(Session.class));
		final SearchResult result = query.getResult();
		this.size = result.getHits().size();
	}
	public String getTitle() {
		
		return StringUtils.defaultString(pageTitle, title);
	}

	  public String getDescription(int truncateAt) {
	       if (this.description != null && this.description.length() > truncateAt) {
	           return StringUtils.substring(this.description, truncateAt) + "...";
	       } else {
	           return this.description;
	       }
	   }

	   public String getDescription() {
	       return this.getDescription(10);
	   }
	   public long getSize() {
	       return this.size;
	   }
	   public Calendar getCreatedAt() {
	       return createdAt;
	   }
	   public String getPath() {
	       return page.getPath();

	   }
	   public String getMessage() {
		   log.info("----------------Agrahari-------------it");

        return String.format("Hi! from %s! I'm Content Services compatible!", resource.getPath());
    }
	}

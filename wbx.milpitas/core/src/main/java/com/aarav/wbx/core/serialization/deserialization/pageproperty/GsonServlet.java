package com.aarav.wbx.core.serialization.deserialization.pageproperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component(service = Servlet.class,

		property = {
				Constants.SERVICE_DESCRIPTION
						+ "=Shailesh Serialization and Deserialization servlet to pull page data in json",
				"sling.servlet.methods=" + HttpConstants.METHOD_GET, 
				"sling.servlet.paths=" + "/bin/shailesh",
				"sling.servlet.extensions=" + "text"

		})
public class GsonServlet extends SlingSafeMethodsServlet {

	@Override
	public void doGet(final SlingHttpServletRequest req, SlingHttpServletResponse res) throws ServletException, IOException {
		Resource resource = req.getResourceResolver().getResource("/content/we-retail");
		Page page = resource.adaptTo(Page.class);
		Iterator<Page> itr = page.listChildren();
		List<PageModel> pageList = new ArrayList<>();
		List<TagAttribute> tagList = new ArrayList<>();
		while(itr.hasNext()) {
			Page childPage = itr.next();
			Tag [] tags = childPage.getTags();
				for(Tag tag:tags) {
					tagList.add(new TagAttribute(tag.getName(), tag.getPath()));
				}
				pageList.add(new PageModel(childPage.getPath(), childPage.getName(),tagList));
			}
		//Sereialization
		Gson gson = new GsonBuilder().setPrettyPrinting().create();		
		res.getWriter().print(gson.toJson(pageList));
		
		//Deserialization
		String json = gson.toJson(pageList);
		PageModel pageModel[]=gson.fromJson(json, PageModel[].class);
		
		res.getWriter().print("The size is "+ pageModel.length);
		
	}

}
/*

[{
	"pagePath": "xyz",
	"pageName": "test",
	"tagAttributes": [{
			"tagPath": "testTagPath",
			"tagName": "testTagName"

		},
		{
			"tagPath": "testTagPath2",
			"tagName": "testTagName2"
		}
	]
},
{
	"pagePath": "xyz",
	"pageName": "test",
	"tagAttributes": [{
			"tagPath": "testTagPath",
			"tagName": "testTagName"

		},
		{
			"tagPath": "testTagPath2",
			"tagName": "testTagName2"
		}
	]
}
]
*/
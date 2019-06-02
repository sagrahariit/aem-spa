package com.aarav.wbx.core.read.subpages;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.sling.models.annotations.Model;

@Model(adaptables=Resource.class)
public class ReadSubPages {
	
	@Inject
	public Resource readsubpages;

}


//java -Xmx512m -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n -jar cq-quickstart-6502.jar
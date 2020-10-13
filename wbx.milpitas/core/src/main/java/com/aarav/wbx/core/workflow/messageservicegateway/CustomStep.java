//https://helpx.adobe.com/experience-manager/using/message_service_gateway_api_64.html
package com.aarav.wbx.core.workflow.messageservicegateway;

import javax.jcr.Session;

import org.apache.commons.mail.SimpleEmail;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.day.cq.mcm.emailprovider.types.Email;

@Component(service = WorkflowProcess.class, property = "process.label=My Email Custom Step")
public class CustomStep implements WorkflowProcess {

	private static final Logger log = LoggerFactory.getLogger(CustomStep.class);

	@Reference
	private ResourceResolverFactory resolverFactory;

	@Reference
	private MessageGatewayService messgaeGatewayService;

	private Session session;

	@Override
	public void execute(WorkItem arg0, WorkflowSession arg1, MetaDataMap arg2) throws WorkflowException {
		try {
			MessageGateway<Email> messageGateway;
			Email email = (Email) new SimpleEmail();

			((org.apache.commons.mail.Email) email).addTo("sagrahariit@gmail.com");
			((org.apache.commons.mail.Email) email).addCc("shailesh.agrahari.it@gmail.com");
			email.setSubject("Shailesh AEM Workflow");
			((org.apache.commons.mail.Email) email).setFrom("agrahari.shailesh.it@gmail.com");
			((SimpleEmail) email).setMsg("This message is to inform you that the AEM content has been deleted");

			messageGateway = messgaeGatewayService.getGateway(Email.class);

			messageGateway.send((Email) email);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
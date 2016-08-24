package com.mulesoft.dejim;

import java.util.HashMap;
import java.util.Map;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.mule.DefaultMuleEvent;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleEvent;
import org.mule.api.MuleEventContext;
import org.mule.api.context.notification.ServerNotification;
import org.mule.api.lifecycle.Callable;

import com.mulesoft.mule.tracking.event.AbstractEventNotificationFiringMessageProcessor;
import com.mulesoft.mule.tracking.event.EventNotification;

public class CustomBusinessEventComponent extends AbstractEventNotificationFiringMessageProcessor implements Callable{

	//private static Log logger = LogFactory.getLog(CustomBusinessEventComponent.class);
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		DefaultMuleEvent muleEvent = new DefaultMuleEvent(eventContext.getMessage(), MessageExchangePattern.REQUEST_RESPONSE, eventContext.getFlowConstruct());
		
		final ServerNotification notification = this.createNotification(muleEvent);	 		
		eventContext.getMuleContext().fireNotification(notification);
		
		return eventContext;
	}

	@Override
	protected EventNotification createNotification(final MuleEvent muleEvent) {
		
		// Create KPIs (Key, Value)
		Map<String, String> metaData = new HashMap<String, String>();
		metaData.put("Test","Component");
		
		return createNotification(muleEvent, "custom-event", null, metaData);
	}

}
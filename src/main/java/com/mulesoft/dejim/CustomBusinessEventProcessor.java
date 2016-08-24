package com.mulesoft.dejim;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.MuleEvent;

import com.mulesoft.mule.tracking.event.AbstractEventNotificationFiringMessageProcessor;
import com.mulesoft.mule.tracking.event.EventNotification;

public class CustomBusinessEventProcessor extends AbstractEventNotificationFiringMessageProcessor {

	@Override
	protected EventNotification createNotification(final MuleEvent muleEvent) {
		
		Map<String, String> metaData = new HashMap<String, String>();
		metaData.put("Name","Expression_Value");
		
		return createNotification(muleEvent, "custom-event", null, metaData);	
	}
}

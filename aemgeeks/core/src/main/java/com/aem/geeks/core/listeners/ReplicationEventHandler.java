package com.aem.geeks.core.listeners;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component(service = EventHandler.class,
            immediate = true,
            property = {
                    EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC,
            })
public class ReplicationEventHandler implements EventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ReplicationEventHandler.class);
    @Override
    public void handleEvent(Event event) {
        try{
            LOG.info("\n Event Type: {} ", event.getTopic());
            if (ReplicationAction.fromEvent(event).getType().equals(ReplicationActionType.ACTIVATE)){
                LOG.info("\n Page Published: {}", ReplicationAction.fromEvent(event).getPath());
            }
            if (ReplicationAction.fromEvent(event).getType().equals(ReplicationActionType.DEACTIVATE)){
                LOG.info("\n Page Deactivated: {}", ReplicationAction.fromEvent(event).getPath());
            }
        }
        catch (Exception e){
            LOG.info("\n Error while activating/deactivating - {} ", e.getMessage());
        }
    }
}

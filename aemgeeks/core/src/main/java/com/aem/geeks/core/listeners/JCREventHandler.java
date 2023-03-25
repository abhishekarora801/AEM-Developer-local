package com.aem.geeks.core.listeners;

import org.apache.poi.ss.formula.functions.Even;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
@Component(service = EventListener.class, immediate = true)
public class JCREventHandler implements EventListener {
    private static final Logger LOG = LoggerFactory.getLogger(JCREventHandler.class);
    private Session session;

    @Reference
    SlingRepository slingRepository;

    @Activate
    public void activate() throws Exception{
        try{
            String[] nodeTypes={"cq:PageContent"};
            session = slingRepository.loginService("geeksserviceuser", null);
            session.getWorkspace().getObservationManager().addEventListener(
                    this,
                    Event.NODE_ADDED | Event.PROPERTY_ADDED,
                    "/content/aemgeeks/us/en/accordion",
                    true,
                    null,
                    nodeTypes,
                    false);

        }
        catch (Exception e){
            LOG.info("\n Error while adding Event listener: {} ", e.getMessage());
        }
    }
    @Override
    public void onEvent(EventIterator eventIterator) {
        try{
            while (eventIterator.hasNext()){
                if(eventIterator.nextEvent()!=null){
                }
                LOG.info("\n Type : {}, Path : {} ", eventIterator.nextEvent().getType(), eventIterator.nextEvent().getPath());
            }
        }
        catch (Exception e){
            LOG.error("\n Error while processing events : {} ", e.getMessage());
        }
    }
}

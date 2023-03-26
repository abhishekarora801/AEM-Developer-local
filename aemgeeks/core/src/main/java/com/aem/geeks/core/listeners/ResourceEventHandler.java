package com.aem.geeks.core.listeners;

import com.aem.geeks.core.utils.ResolverUtil;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.util.List;
@Component(service = ResourceChangeListener.class,
            immediate = true,
            property = {
                    ResourceChangeListener.PATHS+"=/content/aemgeeks/us/en/sling-event-handling",
                    ResourceChangeListener.CHANGES+"=ADDED",
                    ResourceChangeListener.CHANGES+"=REMOVED",
                    ResourceChangeListener.CHANGES+"=CHANGED",
            })
public class ResourceEventHandler implements ResourceChangeListener {
    private static final Logger log = LoggerFactory.getLogger(ResourceEventHandler.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Override
    public void onChange(List<ResourceChange> list) {
            for (ResourceChange rc : list){
                try{
                    log.info("\n Event: {} , Resource: {} ", rc.getType(), rc.getPath());
                    ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
                    Resource resource = resourceResolver.getResource(rc.getPath());
                    Node node = resource.adaptTo(Node.class);
                    node.setProperty("resourceeventhandlertask", "Event "+rc.getType()+" by "+resourceResolver.getUserID());
                    resourceResolver.commit();
                }
                catch (Exception e){
                    log.info("\n Exception: {} ", e.getMessage());
                }
            }
    }
}

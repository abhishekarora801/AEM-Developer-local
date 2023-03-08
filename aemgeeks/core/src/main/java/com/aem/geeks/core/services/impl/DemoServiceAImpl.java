package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.DemoService;
import com.aem.geeks.core.utils.ResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Component(service = DemoService.class, immediate = true)
public class DemoServiceAImpl implements DemoService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceAImpl.class);
    @Activate
    public void activate(ComponentContext componentContext){
        LOG.info("\n =====INSIDE ACTIVATE=====");
        LOG.info("\n {} = {} ", componentContext.getBundleContext().getBundle().getBundleId(),componentContext.getBundleContext().getBundle().getSymbolicName());
    }

    @Deactivate
    public void deactivate(){
        LOG.info("\n =====INSIDE DEACTIVATE=====");
    }

    @Modified
    public void modified(ComponentContext componentContext){
        LOG.info("\n =====INSIDE MODIFIED=====");
    }

    @Override
    public Iterator<Page> getPages() {
        try{
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/aemgeeks/us/en");
            Iterator<Page> pages = page.listChildren();
            return pages;
        }
        catch (LoginException e){
            LOG.info("\n Login Exception {} ", e.getMessage());
        }
        return null;
    }
}

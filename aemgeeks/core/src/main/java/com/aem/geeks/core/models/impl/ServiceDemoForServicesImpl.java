package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.ServiceDemoForServices;
import com.aem.geeks.core.services.DemoService;
import com.aem.geeks.core.services.DemoServiceB;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = ServiceDemoForServices.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoForServicesImpl implements ServiceDemoForServices {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceDemoForServicesImpl.class);

    @OSGiService
    DemoService demoService;

    @Inject
    DemoServiceB demoServiceB;

    @Override
    public Iterator<Page> getPagesList() {
        return demoService.getPages();
    }

    @Override
    public List<String> getPageTitleList() {
        return demoServiceB.getPages();
    }

    @Override
    public String getNameFromServiceB() {
        return demoServiceB.getNameWithReference();
    }

    @PostConstruct
    protected void init(){}
}

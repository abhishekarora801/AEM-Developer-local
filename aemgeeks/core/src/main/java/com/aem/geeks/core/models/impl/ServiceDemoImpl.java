package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.ServiceDemo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = ServiceDemo.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ServiceDemoImpl implements ServiceDemo {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceDemoImpl.class);

    @Override
    public List<String> getPagesTitle() {
        return null;
    }

    @PostConstruct
    protected void init(){
//        LOG.info("\n =====PRINTING LOGS Latest====="); //info is the log level

        LOG.trace("\n =====PRINTING LOGS from TRACE=====");
        LOG.debug("\n =====PRINTING LOGS from DEBUG=====");
        LOG.info("\n =====PRINTING LOGS from INFO=====");
        LOG.warn("\n =====PRINTING LOGS from WARN=====");
        LOG.error("\n =====PRINTING LOGS from ERROR=====");
    }
}

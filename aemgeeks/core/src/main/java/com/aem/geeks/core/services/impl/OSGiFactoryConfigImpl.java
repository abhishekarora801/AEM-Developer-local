package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.config.GeeksOSGiFactoryConfig;
import com.aem.geeks.core.services.OSGiFactoryConfig;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Component(service = OSGiFactoryConfig.class, immediate = true)
@Designate(ocd = GeeksOSGiFactoryConfig.class, factory = true)
public class OSGiFactoryConfigImpl implements OSGiFactoryConfig {
    private static final Logger LOG = LoggerFactory.getLogger(OSGiFactoryConfigImpl.class);

    private int configID;
    private String serviceName;
    private String serviceURL;
    private List<OSGiFactoryConfig> configsList;

    @Activate
    @Modified
    protected void activate(final GeeksOSGiFactoryConfig geeksOSGiFactoryConfig){
        configID = geeksOSGiFactoryConfig.configID();
        serviceName = geeksOSGiFactoryConfig.serviceName();
        serviceURL = geeksOSGiFactoryConfig.serviceURL();
    }
    @Reference(service = OSGiFactoryConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindOSGiFactoryConfig(final OSGiFactoryConfig config){
        if (configsList == null){
            configsList = new ArrayList<>();
        }
        configsList.add(config);
    }

    //if you remove the configuration, this unbind method will execute.
    public void unbindOSGiFactoryConfig(final OSGiFactoryConfig config){
        configsList.remove(config);
    }
    @Override
    public int getConfigID() {
        return configID;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }

    @Override
    public List<OSGiFactoryConfig> getAllConfigs() {
        return configsList;
    }
}

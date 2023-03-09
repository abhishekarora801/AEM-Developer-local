package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.OSGiFactoryConfigSlingModel;
import com.aem.geeks.core.services.OSGiFactoryConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.List;
@Model(adaptables = SlingHttpServletRequest.class,
adapters = OSGiFactoryConfigSlingModel.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiFactoryConfigSlingModelImpl implements OSGiFactoryConfigSlingModel {

    @OSGiService
    OSGiFactoryConfig osGiFactoryConfig;
    @Override
    public List<OSGiFactoryConfig> getAllOSGiConfigs() {
        return osGiFactoryConfig.getAllConfigs();
    }
}

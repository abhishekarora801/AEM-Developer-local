package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.OSGiConfigSlingModel;
import com.aem.geeks.core.services.OSGiConfigService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = OSGiConfigSlingModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigSlingModelImpl implements OSGiConfigSlingModel {

    @OSGiService
    OSGiConfigService osGiConfigService;

    @Override
    public String getServiceName() {
        return osGiConfigService.getServiceName();
    }

    @Override
    public int getServiceCount() {
        return osGiConfigService.getServiceCount();
    }

    @Override
    public boolean isLiveData() {
        return osGiConfigService.isLiveData();
    }

    @Override
    public String[] getCountries() {
        return osGiConfigService.getCountries();
    }

    @Override
    public String getRunModes() {
        return osGiConfigService.getRunModes();
    }
}

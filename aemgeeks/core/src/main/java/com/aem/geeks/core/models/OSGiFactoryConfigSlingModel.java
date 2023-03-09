package com.aem.geeks.core.models;

import com.aem.geeks.core.services.OSGiFactoryConfig;

import java.util.List;

public interface OSGiFactoryConfigSlingModel {
    public List<OSGiFactoryConfig> getAllOSGiConfigs();
}

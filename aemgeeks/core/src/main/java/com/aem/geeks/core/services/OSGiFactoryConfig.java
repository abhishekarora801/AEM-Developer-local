package com.aem.geeks.core.services;

import java.util.List;

public interface OSGiFactoryConfig {
    public int getConfigID();
    public String getServiceName();
    public String getServiceURL();
    public List<OSGiFactoryConfig> getAllConfigs();
}

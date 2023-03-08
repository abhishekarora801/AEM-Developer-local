package com.aem.geeks.core.models;

public interface OSGiConfigSlingModel {
    public String getServiceName();
    public int getServiceCount();
    public boolean isLiveData();
    public String[] getCountries();
    public String getRunModes();
}

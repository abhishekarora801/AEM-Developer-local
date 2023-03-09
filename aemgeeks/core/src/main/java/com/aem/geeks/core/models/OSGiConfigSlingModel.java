package com.aem.geeks.core.models;

public interface OSGiConfigSlingModel {
    public String getServiceName();
    public int getServiceCount();
    public boolean isLiveData();
    public String[] getCountries();
    public String getRunModes();

    //for the module video - where we are declaring the OSGi config in a separate class.

    public int getServiceId();
    public String getServiceNameModule();
    public String getServiceURL();
}

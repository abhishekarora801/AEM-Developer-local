package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.OSGiConfigService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.*;

@Component(service = OSGiConfigService.class, immediate = true)
@Designate(ocd = OSGiConfigServiceImpl.ServiceConfig.class)
public class OSGiConfigServiceImpl implements OSGiConfigService {

    @ObjectClassDefinition(name = "AEM Geeks - OSGi Configuration",
                        description = "OSGi Configuration Demo")
    public @interface ServiceConfig{

        @AttributeDefinition(
                name = "Service Name",
                description = "Enter service name",
                type = AttributeType.STRING)
        public String getServiceName() default "AEM Geeks Service";

        @AttributeDefinition(
                name = "Service Count",
                description = "Add Service Count",
                type = AttributeType.INTEGER)
        int getServiceCount() default 5;

        @AttributeDefinition(
                name = "Live Data",
                description = "Check this to get live data",
                type = AttributeType.BOOLEAN)
        boolean getLiveData() default false;

        @AttributeDefinition(
                name = "Countries",
                description = "Add countries locales for which you want to run this service",
                type = AttributeType.STRING)
        String[] getCountries() default {"de","in"};

        @AttributeDefinition(
                name = "Run Modes",
                description = "Select run mode",
                options = {
                        @Option(label = "Author", value = "author"),
                        @Option(label = "Publish", value = "publish"),
                        @Option(label = "Both", value = "both")
                },
                type = AttributeType.STRING)
        String getRunModes() default "both";
    }

    private String serviceName;
    private int serviceCount;
    private boolean liveData;
    private String[] countries;
    private String runModes;

    @Activate
    protected void activate(ServiceConfig serviceConfig){
        serviceName = serviceConfig.getServiceName();
        serviceCount = serviceConfig.getServiceCount();
        liveData = serviceConfig.getLiveData();
        countries = serviceConfig.getCountries();
        runModes = serviceConfig.getRunModes();
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getServiceCount() {
        return serviceCount;
    }

    @Override
    public boolean isLiveData() {
        return liveData;
    }

    @Override
    public String[] getCountries() {
        return countries;
    }

    @Override
    public String getRunModes() {
        return runModes;
    }

}

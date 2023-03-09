package com.aem.geeks.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM Geeks - Modular OSGi Configuration",
        description = "Modular OSGi Configuration demo")
public @interface GeeksOSGiConfig {

    @AttributeDefinition(
            name = "Service ID",
            description = "Enter service ID",
            type = AttributeType.INTEGER)
    int serviceID() default 5;

    @AttributeDefinition(
            name = "Service Name",
            description = "Enter service name",
            type = AttributeType.STRING)
    public String serviceName() default "AEM Geeks Modular Service";

    @AttributeDefinition(
            name = "Service URL",
            description = "Add Service URL",
            type = AttributeType.STRING)
    public String serviceURL() default "localhost";

}

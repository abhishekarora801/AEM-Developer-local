package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.MultiServiceSlingModel;
import com.aem.geeks.core.services.MultiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = MultiServiceSlingModel.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiServiceSlingModelImpl implements MultiServiceSlingModel {
    private static final Logger LOG = LoggerFactory.getLogger(MultiServiceSlingModelImpl.class);

    @OSGiService(filter = "(component.name=serviceA)")
    //filter annotation helps us call that specific service, no matter what the service ranking is!
    MultiService multiService;

    @OSGiService(filter = "(component.name=com.aem.geeks.core.services.impl.MultiServiceBImpl)")
    MultiService multiServiceB;
    @Override
    public String getNameFromService() {
        return multiService.getName();
    }

    @Override
    public String getNameFromServiceB() {
        return multiServiceB.getName();
    }
}

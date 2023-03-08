package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.MultiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = MultiService.class, immediate = true)
@ServiceRanking(1001)
public class MultiServiceBImpl implements MultiService {
    private static final Logger LOG = LoggerFactory.getLogger(MultiServiceBImpl.class);
    @Override
    public String getName() {
        return "MultiService-B-Impl";
    }
}

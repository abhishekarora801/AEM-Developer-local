package com.aem.geeks.core.jmx.impl;

import com.adobe.granite.jmx.annotation.AnnotatedStandardMBean;
import com.aem.geeks.core.jmx.GeeksMBean;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;

@Component(
        immediate = true,
        service = DynamicMBean.class,
        property = {
                "jmx.objectname = com.aem.geeks.core.jmx:type=Geeks MBean"
        }
)
public class GeeksMBeanImpl extends AnnotatedStandardMBean implements GeeksMBean {
    private static final Logger LOG = LoggerFactory.getLogger(GeeksMBeanImpl.class);
    public GeeksMBeanImpl() throws NotCompliantMBeanException {
        super(GeeksMBean.class);
    }

    @Override
    public String getAuthorName(String authorName) {
        LOG.info("\n =====CALLING GeeksMBean");
        return authorName;
    }
}

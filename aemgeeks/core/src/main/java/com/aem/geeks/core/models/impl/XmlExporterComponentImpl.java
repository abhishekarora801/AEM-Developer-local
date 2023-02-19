package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Author;
import com.aem.geeks.core.models.XmlExporterComponent;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@XmlRootElement(name = "geeks-exporter") //providing a name, so that it appear proper on xml
@Exporter(name = "geeksxml", extensions = "xml", selector = "geeks") //calling the GeeksXmlExporter java class through Exporter annotation. Also using geeks selector to call the xml
@Model(adaptables = SlingHttpServletRequest.class,
        adapters = XmlExporterComponent.class,
        resourceType = XmlExporterComponentImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class XmlExporterComponentImpl implements XmlExporterComponent {
    static final String RESOURCE_TYPE = "aemgeeks/components/xml-exporter-component";
    private static final Logger LOG = LoggerFactory.getLogger(XmlExporterComponentImpl.class);

    @Inject
    @Via("resource")
    String xmlTitle;

    @Inject
    @Via("resource")
    String xmlDescription;

    @Inject
    @Via("resource")
    Date xmlPublishedDate;

    @ValueMapValue
    private List<String> xmlBooks;

    @Override
    @XmlElement(name = "xml-title") //always use on methods, can also be used on variables.
    public String getXmlTitle() {
        return xmlTitle;
    }

    @Override
    @XmlElement(name = "xml-description")
    public String getXmlDescription() {
        return xmlDescription;
    }

    @Override
    @XmlElement(name = "xml-date")
    public Date getXmlPublishedDate() {
        return xmlPublishedDate;
    }

    @Override
    @XmlElement(name = "xml-books")
    @XmlElementWrapper(name = "books") //so that a root element for book multifield values, will be created.
    public List<String> getXmlAuthorBooks() {
        if (xmlBooks!=null){
            return new ArrayList<String>(xmlBooks);
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    @XmlElement(name = "xml-author-name") //this is a non-getter method, using @XmlElement annotation to display it in Xml response.
    public String getXmlAuthorName() {
        return "Abhishek Arora";
    }
}
//To see this Xml response, use this link ( http://localhost:4502/content/aemgeeks/us/en/hello-page1/jcr:content/root/container/container/xml_exporter_compone.geeks.xml )
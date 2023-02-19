package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.MultiExporter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//if we want to export content in more than one format, use @Exporters annotation, used below.
@Exporters({
        @Exporter(name = "jackson", extensions = "json", selector = "geeksjson",
        options = {
                @ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true") //by using this annotation, we will create the root node for json exporter
        }),
        @Exporter(name = "geeksxml", extensions = "xml", selector = "geeksxml")
})
@Model(adaptables = SlingHttpServletRequest.class,
        adapters = MultiExporter.class,
        resourceType = MultiExporterImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@JsonRootName("json-exporter")
@XmlRootElement(name = "xml-exporter")
public class MultiExporterImpl implements MultiExporter {
    static final String RESOURCE_TYPE = "aemgeeks/components/multi-exporter-component";
    private static final Logger LOG = LoggerFactory.getLogger(MultiExporterImpl.class);


    @Inject
    @Via("resource")
    String multiExporterTitle;

    @Inject
    @Via("resource")
    String multiExporterDescription;

    @Inject
    @Via("resource")
    Date multiExporterPublishedDate;

    @ValueMapValue
    private List<String> multiExporterBooks;
    @Override
    @JsonProperty(value = "multi-exporter-json-title")
    @XmlElement(name = "multi-exporter-xml-title")
    public String getMultiExporterTitle() {
        return multiExporterTitle;
    }

    @Override
    @JsonProperty(value = "multi-exporter-json-description")
    @XmlElement(name = "multi-exporter-xml-description")
    public String getMultiExporterDescription() {
        return multiExporterDescription;
    }

    @Override
    @JsonProperty(value = "multi-exporter-json-date")
    @XmlElement(name = "multi-exporter-xml-date")
    public Date getMultiExporterPublishedDate() {
        return multiExporterPublishedDate;
    }

    @Override
    @JsonProperty(value = "multi-exporter-json-books")
    @XmlElement(name = "multi-exporter-xml-books")
    public List<String> getMultiExporterAuthorBooks() {
        if (multiExporterBooks!=null){
            return new ArrayList<String>(multiExporterBooks);
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    @JsonProperty(value = "multi-exporter-json-author-name")
    @XmlElement(name = "multi-exporter-xml-author-name")
    public String getMultiExporterAuthorName() {
        return "Abhishek Arora";
    }
}

/*
To see xml on page:
http://localhost:4502/content/aemgeeks/us/en/hello-page1/jcr:content/root/container/container/multi_exporter_compo.geeksxml.xml

To see json on page:
http://localhost:4502/content/aemgeeks/us/en/hello-page1/jcr:content/root/container/container/multi_exporter_compo.geeksjson.json

 */

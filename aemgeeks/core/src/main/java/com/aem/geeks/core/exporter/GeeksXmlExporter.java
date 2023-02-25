package com.aem.geeks.core.exporter;

import org.apache.sling.models.export.spi.ModelExporter;
import org.apache.sling.models.factory.ExportException;
import org.osgi.service.component.annotations.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ModelExporter.class)
public class GeeksXmlExporter implements ModelExporter{
    private static final Logger LOG = LoggerFactory.getLogger(GeeksXmlExporter.class);
    @Override
    public boolean isSupported(Class<?> aClass) {
        return true; //true means it supports the content, you want to export.
    }

    @Override
    public <T> T export(Object model, Class<T> aClass, Map<String, String> options) throws ExportException {
        StringWriter stringWriter = new StringWriter();
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(model, stringWriter);
        }
        catch (JAXBException e){
            LOG.info("\n Marshell error : {} ", e.getMessage());
        }
        return (T) stringWriter.toString();
    }

    @Override
    public String getName() {
        return "geeksxml"; //defines the name of the custom sling model exporter.
    }
}

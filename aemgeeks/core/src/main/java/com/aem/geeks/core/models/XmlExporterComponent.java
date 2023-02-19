package com.aem.geeks.core.models;

import java.util.Date;
import java.util.List;

public interface XmlExporterComponent {

    String getXmlTitle();
    String getXmlDescription();
    Date getXmlPublishedDate();
    List<String> getXmlAuthorBooks();
    String getXmlAuthorName();

}

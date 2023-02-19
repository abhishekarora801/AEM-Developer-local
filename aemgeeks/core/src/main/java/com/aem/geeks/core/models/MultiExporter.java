package com.aem.geeks.core.models;

import java.util.Date;
import java.util.List;

public interface MultiExporter {
    String getMultiExporterTitle();
    String getMultiExporterDescription();
    Date getMultiExporterPublishedDate();
    List<String> getMultiExporterAuthorBooks();
    String getMultiExporterAuthorName();
}

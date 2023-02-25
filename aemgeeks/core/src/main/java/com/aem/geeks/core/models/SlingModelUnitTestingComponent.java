package com.aem.geeks.core.models;

import java.util.List;
import java.util.Map;

public interface SlingModelUnitTestingComponent {
    String getFirstName();
    String getLastName();
    boolean getIsProfessor();
    List<String> getAuthorBooks();
    List<Map<String,String>> getBookDetailsWithMap();
    String getPageTitle();
    String getRequestAttribute();
    String getHomePageName();
    String getLastModifiedBy();
}

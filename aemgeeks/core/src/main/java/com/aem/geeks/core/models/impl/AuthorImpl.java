package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Author;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = Author.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl implements Author{
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);

    @Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy")
    String modifiedBy;

    @OSGiService
    QueryBuilder queryBuilder;

    @ResourcePath(path ="/content/aemgeeks/us/en/home")
    @Via("resource")
    Resource resource;

    @SlingObject
    ResourceResolver resourceResolver;

    @Self //can inject also via SlingObject
    SlingHttpServletRequest slingHttpServletRequest;

    @RequestAttribute(name ="rAttribute")
    private String reqAttribute;

    @ScriptVariable
    Page currentPage;

    @Inject
    @Default(values = "AEM")
    @Via("resource")
    String fname;

    @ValueMapValue //can also use ValueMapValue instead of Inject and Via
    @Required
    @Default(values = "GEEKS")
    String lname;

    @Inject
    @Via("resource")
    boolean professor;

    @Inject
    @Via("resource")
    String expath;
    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public boolean getIsProfessor() {
        return professor;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getTitle();
    }

    @Override
    public String getRequestAttribute() {
        return reqAttribute;
    }

    @Override
    public String getHomePageName() {
        return resource.getName();
    }

    @Override
    public String getLastModifiedBy() {
        return modifiedBy;
    }

    @Override
    public String getLastModifiedTimestamp() {
        return null;
    }

    @PostConstruct
    protected void init(){
        LOG.info("\n Inside INIT {} : {} ",currentPage.getTitle(),resource.getPath());

    }
}

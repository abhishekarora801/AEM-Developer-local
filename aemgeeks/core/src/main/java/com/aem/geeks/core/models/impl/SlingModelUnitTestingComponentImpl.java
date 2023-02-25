package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.SlingModelUnitTestingComponent;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = SlingModelUnitTestingComponent.class,
        resourceType = SlingModelUnitTestingComponentImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SlingModelUnitTestingComponentImpl implements SlingModelUnitTestingComponent {

    private static final Logger LOG = LoggerFactory.getLogger(SlingModelUnitTestingComponentImpl.class);
    final protected static String RESOURCE_TYPE = "aemgeeks/components/sling-model-unit-testing-component";

    @Inject
    Resource resource;

    @SlingObject
    ResourceResolver resourceResolver;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

    @RequestAttribute(name = "rAttribute")
    private String reqAttribute;

    @ResourcePath(path = "/content/aemgeeks/us/en/hello-page1")@Via("resource")
    Resource resourcePage;

    @ScriptVariable
    Page currentPage;

    @Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy")
    String modifiedBy;

    @Inject
    Resource componentResource;

    @Inject
    @Via("resource")
    String fname;

    @ValueMapValue //can also use ValueMapValue instead of Inject and Via
    String lname;

    @Inject
    @Via("resource")
    boolean professor;

    @ValueMapValue
    private List<String> books;

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
    public List<String> getAuthorBooks() {
        if (books!=null){
            return new ArrayList<String>(books);
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Map<String, String>> getBookDetailsWithMap() {
        List<Map<String,String>> bookDetailsMap=new ArrayList<>();
        try{
            Resource bookDetail = componentResource.getChild("bookdetailswithmap");
            if (bookDetail!=null){
                for (Resource book : bookDetail.getChildren()){
                    Map<String,String> bookMap = new HashMap<>();
                    bookMap.put("bookname",book.getValueMap().get("bookname",String.class));
                    bookMap.put("booksubject", book.getValueMap().get("booksubject",String.class));
                    bookMap.put("publishyear", book.getValueMap().get("publishyear",String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        }
        catch (Exception e){
            LOG.info("\n ERROR while getting book details {}", e.getMessage());
        }
        LOG.info("\n SIZE {}",bookDetailsMap.size());
        return bookDetailsMap;
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
        return resourcePage.getName();
    }

    @Override
    public String getLastModifiedBy() {
        return modifiedBy;
    }

    public String authorName() {
        return "Abhishek AEM Developer";
    }
}

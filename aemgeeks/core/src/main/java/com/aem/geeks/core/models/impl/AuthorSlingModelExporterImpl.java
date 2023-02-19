package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.AuthorSlingModelExporter;
import com.aem.geeks.core.models.Author;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
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
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = AuthorSlingModelExporter.class,
        resourceType = AuthorSlingModelExporterImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = "jackson", extensions = "json", selector = "geeks",
options = {
        @ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true"), //this is required, only then JSONRootName annotation will work.
        @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true") //to sort the JSON properties in alphabetical order.
})
@JsonRootName("author-details") //used to create a parent, and all the properties exported inside, will be inside this object.
public class AuthorSlingModelExporterImpl implements AuthorSlingModelExporter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorSlingModelExporterImpl.class);
    static final String RESOURCE_TYPE="aemgeeks/components/author-sling-model-exporter";

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

    @ValueMapValue
    private List<String> books;

    @Inject
    Resource componentResource;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

    @SlingObject
    ResourceResolver resourceResolver;

    @ScriptVariable
    Page currentPage;

    @ResourcePath(path ="/content/aemgeeks/us/en/home")
    @Via("resource")
    Resource resource;

    @RequestAttribute(name ="rAttribute")
    private String reqAttribute;

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
    @JsonProperty(value = "books-details")
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
    @JsonIgnore //used to ignore a content in JSON, will not be present in JSON!
    public String getRequestAttribute() {
        return reqAttribute;
    }

    @Override
    public String getHomePageName() {
        return resource.getName();
    }

    //This is a non-getter method, we will use JsonProperty annotation, to show this method in JSON
    @Override
    @JsonProperty(value = "author-name") //used to show non-getter methods in JSON, can also use to change names, like did for getBookDetailsWithMap() method above.
    public String authorName() {
        return "ABHISHEK ARORA";
    }
}

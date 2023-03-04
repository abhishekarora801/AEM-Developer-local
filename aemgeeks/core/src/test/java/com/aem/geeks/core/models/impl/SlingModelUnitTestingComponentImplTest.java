package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.SlingModelUnitTestingComponent;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SlingModelUnitTestingComponentImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(SlingModelUnitTestingComponentImplTest.class);
    private final AemContext aemContext = new AemContext();
    SlingModelUnitTestingComponent slingModelUnitTestingComponent;
    @BeforeEach //this method will execute everytime before each method!
    void setUp() {
        aemContext.addModelsForClasses(SlingModelUnitTestingComponentImpl.class);
        // we can load it whole json using the /component, mentioned in below line. E.g. Called inside getAuthorBooks() method.
        aemContext.load().json("/com/aem/geeks/core/models/impl/SlingModelUnitTestingComponent.json", "/component");
        aemContext.load().json("/com/aem/geeks/core/models/impl/Page.json", "/page");
        aemContext.load().json("/com/aem/geeks/core/models/impl/Resource.json", "/resource");
    }

    @Test
    void getFirstName() {
        aemContext.currentResource("/component/author");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        final String expected = "ABHISHEK";
        String actual = slingModelUnitTestingComponent.getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    void getLastName() {
        aemContext.currentResource("/component/author");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        final String expected = "Arora";
        String actual = slingModelUnitTestingComponent.getLastName();
        assertEquals(expected, actual);
    }

    @Test
    void getIsProfessor() {
        aemContext.currentResource("/component/author");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        assertEquals(true, slingModelUnitTestingComponent.getIsProfessor());
    }

    @Test
    void getAuthorBooks() {
        aemContext.currentResource("/component/author");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
//        System.out.println(slingModelUnitTestingComponent.getAuthorBooks());
        assertEquals(2, slingModelUnitTestingComponent.getAuthorBooks().size());
        assertEquals("2 States", slingModelUnitTestingComponent.getAuthorBooks().get(0));
    }

    @Test
    void getAuthorBooksWithNull(){
        aemContext.currentResource("/component/author-empty-books");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        assertEquals(0, slingModelUnitTestingComponent.getAuthorBooks().size());
    }

    @Test
    void getBookDetailsWithMap() {
        aemContext.currentResource("/component/author");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
//        assertEquals(2, slingModelUnitTestingComponent.getBookDetailsWithMap().size());
//        assertEquals("1995", slingModelUnitTestingComponent.getBookDetailsWithMap().get(0).get("publishyear"));
//        assertEquals("Let Us JavaScript", slingModelUnitTestingComponent.getBookDetailsWithMap().get(1).get("booksubject"));
    }

    @Test
    void getBookDetailsWithMapWithNull() {
        aemContext.currentResource("/component/author-empty-books");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        assertEquals(0, slingModelUnitTestingComponent.getBookDetailsWithMap().size());
    }

    @Test
    void getPageTitle() {
        aemContext.currentPage("/page/currentPage");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        assertEquals("For Sling Model Exporter", slingModelUnitTestingComponent.getPageTitle());
    }

    @Test
    void getRequestAttribute() {
        aemContext.request().setAttribute("rAttribute", "TestAttribute");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        assertEquals("TestAttribute", slingModelUnitTestingComponent.getRequestAttribute());
    }

    @Test
    void getHomePageName() {
        Resource resource = aemContext.currentResource("/resource/resourcePage");
        SlingModelUnitTestingComponentImpl slingModelUnitTestingComponentImpl = aemContext.registerService(new SlingModelUnitTestingComponentImpl());
        slingModelUnitTestingComponentImpl.resourcePage = resource;
        assertEquals("resourcePage", slingModelUnitTestingComponentImpl.getHomePageName());
    }

    @Test
    void getLastModifiedBy() {
        aemContext.currentResource("/component/author");
        slingModelUnitTestingComponent = aemContext.request().adaptTo(SlingModelUnitTestingComponent.class);
        final String expected = "admin";
        String actual = slingModelUnitTestingComponent.getLastModifiedBy();
        assertEquals(expected, actual);
    }

    @Test
    void authorName() {
        //In this test case, we can't use the interface, because this method was not present in the interface.
        assertEquals("Abhishek AEM Developer", aemContext.registerService(new SlingModelUnitTestingComponentImpl()).authorName());
    }
}
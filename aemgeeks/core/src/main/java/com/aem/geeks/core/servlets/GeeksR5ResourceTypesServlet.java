package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_EXTENSIONS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                SLING_SERVLET_METHODS+"="+HttpConstants.METHOD_POST,
                "sling.servlet.resourceTypes="+ "aemgeeks/components/structure/home",
                "sling.servlet.selectors="+ "geeks",
//                "sling.servlet.selectors"+ "ds",
                SLING_SERVLET_EXTENSIONS+"="+"xml",
//                "sling.servlet.extensions"+"txt"
        })
public class GeeksR5ResourceTypesServlet extends SlingAllMethodsServlet {
        private static final Logger LOG = LoggerFactory.getLogger(GeeksR5ResourceTypesServlet.class);

        @Override
        protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
                throws ServletException, IOException {
                final ResourceResolver resourceResolver = req.getResourceResolver();
                String reqSelectors ="SELECTORS => ";
                String reqExtension = req.getRequestPathInfo().getExtension();
                try{
                        String[] selectors = req.getRequestPathInfo().getSelectors();
                        for (String selector : selectors){
                                reqSelectors=reqSelectors + " "+selector;
                        }
                }
                catch (Exception e){
                        LOG.info("\n ERROR {} ", e.getMessage());
                }
                resp.setContentType("application/json");
                resp.getWriter().write(reqSelectors+ " # " +reqExtension);
        }
}

//Path used for checking this functionality -->
// http://localhost:4502/content/aemgeeks/us/en/servlet-demo-for-r5-annotation-resource-type/jcr:content.geeks.xml

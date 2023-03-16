package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(
        service = {Servlet.class},
        property = {
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                SLING_SERVLET_METHODS+"="+HttpConstants.METHOD_POST,
                "sling.servlet.resourceTypes="+ "aemgeeks/components/structure/home",
                SLING_SERVLET_PATHS+"="+ "/geeks/r5servlet",
                SLING_SERVLET_SELECTORS+"="+"geeks",
//                "sling.servlet.selectors"+ "ds",
                SLING_SERVLET_EXTENSIONS+"="+"xml",
//                "sling.servlet.extensions"+"txt"
        })
public class GeeksR5PathTypeServlet extends SlingSafeMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GeeksR5PathTypeServlet.class);

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

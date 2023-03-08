package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.DemoService;
import com.aem.geeks.core.services.DemoServiceB;
import com.aem.geeks.core.services.MultiService;
import com.day.cq.wcm.api.Page;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component(service = DemoServiceB.class, immediate = true)
public class DemoServiceBImpl implements DemoServiceB {
    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceBImpl.class);

    @Reference
    DemoService demoService;

    @Reference(target = "(component.name=serviceA)")
    //if some name is already given, like component.name in serviceA, then we have to use that only, otherwise, it'll not work.
    MultiService multiService;

    @Override
    public String getNameWithReference(){
        return "Response coming from "+multiService.getName();
    }

    @Override
    public List<String> getPages() {
        List<String> listPages = new ArrayList<String>();
        try{
            Iterator<Page> pages = demoService.getPages();
            while(pages.hasNext()){
                listPages.add(pages.next().getTitle());
            }
            return listPages;
        }
        catch (Exception e){
            LOG.info("\n Exception {} ", e.getMessage());
        }
        return null;
    }
}

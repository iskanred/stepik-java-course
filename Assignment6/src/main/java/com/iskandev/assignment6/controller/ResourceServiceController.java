package com.iskandev.assignment6.controller;

import com.iskandev.assignment6.model.TestResource;
import com.iskandev.assignment6.xmlparse.TestResourceXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.*;

@ManagedResource(objectName = "Admin:type=ResourceServerController")
@RestController
@RequestMapping("/resources")
public class ResourceServiceController implements ResourceServiceControllerMBean {
    @Autowired
    private TestResource resource;
    @Autowired
    private TestResourceXMLParser resourceXMLParser;

    @ManagedAttribute
    @Override
    public int getAge() {
        return resource.getAge();
    }

    @ManagedAttribute
    @Override
    public String getName() {
        return resource.getName();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestParam("path") String resourcePath) {
        TestResource resource = resourceXMLParser.parseTestResourceFrom(resourcePath);
        setResource(resource);
    }

    private void setResource(TestResource resource) {
        this.resource = resource;
    }
}

package com.iskandev.assignment6.xmlparse;

import com.iskandev.assignment6.model.TestResource;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TestResourceHandler extends DefaultHandler {
    private final String CLASS_TAG_NAME = "class";
    private final String AGE_TAG_NAME = "age";
    private final String NAME_TAG_NAME = "name";

    private TestResource resource;
    private String elementValue;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue != null) {
            switch (elementValue) {
                case AGE_TAG_NAME:
                    resource.setAge(Integer.parseInt(String.valueOf(ch, start, length)));
                    break;
                case NAME_TAG_NAME:
                    resource.setName(String.valueOf(ch, start, length));
                    break;
            }
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!qName.equals(CLASS_TAG_NAME)) {
            elementValue = qName;
        } else {
            resource = new TestResource();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        elementValue = null;
    }

    public TestResource getResource() {
        return resource;
    }
}

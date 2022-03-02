package com.iskandev.assignment6.xmlparse;

import com.iskandev.assignment6.model.TestResource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

@Component
public class TestResourceXMLParser {
    public TestResource parseTestResourceFrom(String xmlFilePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            TestResourceHandler handler = new TestResourceHandler();
            parser.parse(xmlFilePath, handler);

            return handler.getResource();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

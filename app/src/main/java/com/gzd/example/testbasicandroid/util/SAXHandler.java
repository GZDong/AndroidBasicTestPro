package com.gzd.example.testbasicandroid.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

/**
 * Created by gzd on 2019/2/2 0002
 */
public class SAXHandler extends DefaultHandler {

    private HashMap<String,String> data = new HashMap<>();
    String keyTemp;
    @Override
    public void startDocument() throws SAXException {
        //初始化操作...
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //localName就是节点名称
        keyTemp = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.put(keyTemp,ch.toString());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        keyTemp = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public HashMap<String, String> getData() {
        return data;
    }
}

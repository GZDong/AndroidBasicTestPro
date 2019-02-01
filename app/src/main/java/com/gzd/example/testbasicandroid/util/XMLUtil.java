package com.gzd.example.testbasicandroid.util;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by gzd on 2019/2/2 0002
 */
public class XMLUtil {
    public HashMap<String,String> parseXMLWithPull(String response){
        HashMap<String,String> data = new HashMap<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(response));   //从网络流里直接获取也是可以的
            int eventType = parser.getEventType();  //pull的方式，在获取输入流之后，默认的eventType就是最外层标签的
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:{
                        if ("id".equals(nodeName)){
                             data.put(nodeName,parser.nextText()) ;
                        }else {
                            //...
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG:{
                        //解析完成
                    }
                    break;
                    default:
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public HashMap<String,String> parseXMLWithSAX(String response){
        HashMap<String,String> data = null;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            SAXHandler handler = new SAXHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(response)));
            data = handler.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

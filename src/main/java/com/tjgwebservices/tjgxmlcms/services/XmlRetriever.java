package com.tjgwebservices.tjgxmlcms.services;

import com.tjgwebservices.tjgxmlcms.model.DisplayMap;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlRetriever {

	private String dataPath;
	private String configurationName;
	private XPath xpath;
	private InputSource inputSource;
	private Reader reader;

	public XmlRetriever (){
		this.xpath = XPathFactory.newInstance().newXPath();
		this.inputSource = new InputSource(dataPath);
		this.reader = inputSource.getCharacterStream();

	}

	public XmlRetriever (String dataPath){
		this.dataPath = dataPath;
		this.inputSource = new InputSource(dataPath);
		this.reader = inputSource.getCharacterStream();

	}


	public String retrieveValue(String value) {
            String xpathstring = "//"+value;
            String responseValue;
            try {
                responseValue = xpath.evaluate(xpathstring,inputSource);
            return responseValue;
            } catch (XPathExpressionException ex) {
                Logger.getLogger(XmlRetriever.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
	}

	public List<List<String>> retrieveLinks(String value) {
		List<List<String>> l = new ArrayList<List<String>>();
		String xpathstring = "//"+value+"//links";
		NodeList nodeList;
                try {
                    nodeList = (NodeList) xpath.evaluate(xpathstring,inputSource, XPathConstants.NODESET);
                    if (nodeList.getLength()==0) {
                        return l;
                    }
                    for (int i=0;i < nodeList.getLength(); i++) {
                            Node linksNode = nodeList.item(i);
                            List<String> ll = new ArrayList<String>();

                            String linksname = (String) xpath.evaluate("title/text()", XPathConstants.STRING);
                            String linksurl = (String) xpath.evaluate("description/text()", XPathConstants.STRING);
                            ll.add(linksname);
                            ll.add(linksurl);
                            l.add(ll);
                    }
                } catch (XPathExpressionException ex) {
                    Logger.getLogger(XmlRetriever.class.getName()).log(Level.SEVERE, null, ex);
                }
                return l;
                
	}

	public List<DisplayMap> retrieveFigures() {
                List<DisplayMap> l = new ArrayList<DisplayMap>();
		String xpathstring = "//fiigures";
		NodeList nodeList;
                try {
                    nodeList = (NodeList) xpath.evaluate(xpathstring,inputSource, XPathConstants.NODESET);
                    if (nodeList.getLength()==0) {
                        return l;
                    }
                    for (int i=0;i < nodeList.getLength(); i++) {
                            Node linksNode = nodeList.item(i);
                            List<String> ll = new ArrayList<String>();

                            String title = (String) xpath.evaluate("title/text()", XPathConstants.STRING);
                            String description = (String) xpath.evaluate("description/text()", XPathConstants.STRING);
                            NodeList elementList = (NodeList) xpath.evaluate(xpathstring,inputSource, XPathConstants.NODESET);
                            List<String> jl = new ArrayList<String>();
                            for (int j=0;j<elementList.getLength(); j++) {
                                String element = (String) xpath.evaluate("description/text()", XPathConstants.STRING);
                                jl.add(element);
                            }
                            DisplayMap dm = new DisplayMap();
                            dm.setTitle(title);
                            dm.setDescription(description);
                            dm.setElements(jl);
                            l.add(dm);
                    }
                } catch (XPathExpressionException ex) {
                    Logger.getLogger(XmlRetriever.class.getName()).log(Level.SEVERE, null, ex);
                }
                return l;
                
	}

	public String getDataPath() {

		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}


	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

}



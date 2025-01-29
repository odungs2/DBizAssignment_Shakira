/**
 * @(#)XMLUpdate.java
 * Copyright (c) 2022-2023
 
 * Description: To Update existing testNG xml based on the config.json details
 * author shakira 
 * @version 00:00:01
 * @see <com.SeleniumUtilities.XMLUpdate>
 * usage : <  >;
*/

package com.SeleniumUtilities;

import com.google.common.collect.ImmutableMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.function.BiConsumer;

	public class AllureEnvironmentXMLWriter {
		/**
		 * Class used for writing environment.xml file.
		 */

	    public void allureEnvironmentWriter(ImmutableMap<String, String> environmentValuesSet)  {
	        try {
	            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	            final Document doc = docBuilder.newDocument();
	            final Element environment = doc.createElement("environment");
	            doc.appendChild(environment);
	            environmentValuesSet.forEach(new BiConsumer<String, String>() {
					public void accept(String k, String v) {
					    Element parameter = doc.createElement("parameter");
					    Element key = doc.createElement("key");
					    Element value = doc.createElement("value");
					    key.appendChild(doc.createTextNode(k));
					    value.appendChild(doc.createTextNode(v));
					    parameter.appendChild(key);
					    parameter.appendChild(value);
					    environment.appendChild(parameter);
					}
				});

	            // Write the content into xml file
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            File allureResultsDir = new File("./allure-results");
	            if (!allureResultsDir.exists()) allureResultsDir.mkdirs();
	            StreamResult result = new StreamResult(
	                    new File( "./allure-results/environment.xml"));
	            
	            /*
	            File allureResultsDir = new File( System.getProperty("user.dir")
	                    + "/target/allure-results");
	            if (!allureResultsDir.exists()) allureResultsDir.mkdirs();
	            StreamResult result = new StreamResult(
	                    new File( System.getProperty("user.dir")
	                            + "/target/allure-results/environment.xml"));
	                            */
	            transformer.transform(source, result);
	            System.out.println("Allure environment data saved.");
	        } catch (ParserConfigurationException pce) {
	            pce.printStackTrace();
	        } catch (TransformerException tfe) {
	            tfe.printStackTrace();
	        }
	    }
	}


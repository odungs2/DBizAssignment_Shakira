/**
 * Initialize the test from here..
 */

/**
 * @(#)InitializeTest.java
 * 
 * Copyright (c) 2025-2026
 
 * Description: Initialize the test from here by executing testng.xml file using runner.
 * author shakira 
 * @version 00:00:01
 * @see <com.SeleniumTestInit.InitializeTest>
 * 
*/

package com.SeleniumTestInit_test;

import org.testng.TestNG;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;


public class InitializeTest_test {
	
	public static void main(String[] args) throws XPathExpressionException, IOException, TransformerFactoryConfigurationError, TransformerException{
		
		
		String TestNGXMLDataFile = "./src/main/resources/testng.xml"; 
	    
	  	//Start the execution by running testng.xml file.
		// Create object of TestNG Class
		TestNG runner=new TestNG();

		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();

		// Add xml file which you have to execute 
		suitefiles.add(TestNGXMLDataFile);

		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();		    
		}
		
}


	
	
	
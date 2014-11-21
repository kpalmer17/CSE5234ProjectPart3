import java.io.BufferedReader;
import java.io.FileReader;
//import java.util.Scanner;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import errorhandler.MyErrorHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;


public class XMLloadValidator {

	
	public boolean validate (String XMLFile, String XMLXSD ){
		
		try {
			//Get the path to the xml file, schema, and dtd
			//Scanner scanner = new Scanner(System.in);
			//System.out.println("Enter the path of the xml file to be validated:");
			//String xmlFileName = scanner.next();
			//System.out.println("Enter the path of the xml schema to use for validation:");
			//String schemaFileName = scanner.next();
			//scanner.close();
		
			//validate against dtd
			//boolean dtdValid = validateXMLwithDTD(XMLFile);
			//System.out.println("XML Validated Against DTD: "+ dtdValid);
			
			//Validate against Schema
			boolean schemaValid = validateXMLwithSchema(XMLFile, XMLXSD);
			//System.out.println("XML Validated Against Schema: " + schemaValid);
			return (schemaValid);
		}
		catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean validateXMLwithDTD(String xmlFileName) {
		try {
			System.out.println("Validating against DTD");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new MyErrorHandler());
			
			//Generate DOM Tree
			Document xmlDocument = builder.parse(new InputSource(xmlFileName));
			
			return true;
		}
		catch (SAXParseException e) {
			return false;
		}
		catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean validateXMLwithSchema(String xmlFileName, String schemaFileName) {
		try {
			
			//System.out.println("Validating against Schema");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	factory.setValidating(false);
        	factory.setNamespaceAware(true);
        	
        	SchemaFactory schemfact = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        	factory.setSchema(schemfact.newSchema(new StreamSource(new BufferedReader(new FileReader(schemaFileName)))));

        	DocumentBuilder builder = factory.newDocumentBuilder();
        	builder.setErrorHandler(new MyErrorHandler());
        	// Generates a Document object tree
        	Document document = builder.parse(new InputSource(xmlFileName));

            return true;
		}
		catch (SAXParseException e) {
			return false;
		}
		catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}

package errorhandler;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
       System.out.print("Warning at line " + e.getLineNumber() + ": ");
       System.out.println(e.getMessage());
       throw e;
    }

    public void error(SAXParseException e) throws SAXException {
        System.out.print("Error at line " + e.getLineNumber() + ": ");
        System.out.println(e.getMessage());
        throw e;
    }

    public void fatalError(SAXParseException e) throws SAXException {
       System.out.print("Fatal error at line " + e.getLineNumber() + ": ");
       System.out.println(e.getMessage());
       throw e;
    }
}
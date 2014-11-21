import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;

public class DOMManipulationParser {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Document theDocument = FileRootDocumentGet("C:/School/CSE5234/sample projects/CSE5234ProjectPart3/src/docs/ManipulationDoc.xml");
			//validate
			
			XMLloadValidator validator = new XMLloadValidator();
			
			boolean tf = validator.validate("C:/School/CSE5234/sample projects/CSE5234ProjectPart3/src/docs/ManipulationDoc.xml", "C:/School/CSE5234/sample projects/CSE5234ProjectPart3/src/docs/ManipulationDoc.xsd");
			
			if (tf == false) System.out.println("The XML is not valid");
			if (tf == true) System.out.println("The XML is valid");
			
			
			Node documentNode = DocumentNodeGet(theDocument);
		   	System.out.println("Document node name = " + documentNode.getNodeName());
		   	
		   	
			Node[] workflowArray = NodeElementSelectManyGivenName(documentNode, "WORKFLOW");
			for(int i = 0; i < workflowArray.length; i++){
				String workflowID = NodeAttributeValueGet(workflowArray[i], "ID");
				System.out.println("WORKFLOW = " + workflowID);
				Node[] activityArray = NodeElementSelectManyGivenName(workflowArray[i], "ACTIVITY");
				for(int j = 0; j < activityArray.length; j++){
					String activityID = NodeAttributeValueGet(activityArray[j], "ID");
					System.out.println("ACTIVITY = " + activityID);
				}
			}
			System.out.println("Parsing complete");
		} catch (Exception E){
			System.out.println("Error in parsing");
		}
	}
	
    public static Document FileRootDocumentGet(String FileName) 
    	      throws Exception{
    		Document TheDocument=null;
    		DocumentBuilderFactory factory =
    				    DocumentBuilderFactory.newInstance();
    		DocumentBuilder parser = factory.newDocumentBuilder();
    		TheDocument = parser.parse(FileName);
    		return(TheDocument);
    }
    
    public static Node DocumentNodeGet(Document TheDocument){
    	Node theNode=null;
    	theNode = TheDocument.getDocumentElement();
    	return(theNode);
    }
    
    public static Node[] NodeElementSelectManyGivenName(Node TheNode,
    		String TheName){
    	int count = 0;
    	Node[] SelectedNodes=null;

    	NodeList children = TheNode.getChildNodes();

    	if (children != null) {
    		int len = children.getLength();
    		for (int i = 0; i < len; i++) {
    			short nodeType=children.item(i).getNodeType();
    			Node oneChild=children.item(i);
    			if (nodeType == Node.ELEMENT_NODE){
    				String nodeName = oneChild.getNodeName();
    				if(nodeName.equals(TheName)){
    					count++;
    				}
    			}
    		}

    		SelectedNodes = new Node[count];
    		count = 0;
    		for (int i = 0; i < len; i++) {
    			short nodeType=children.item(i).getNodeType();
    			Node oneChild=children.item(i);
    			if (nodeType == Node.ELEMENT_NODE){
    				String nodeName = oneChild.getNodeName();
    				if(nodeName.equals(TheName)){
    					SelectedNodes[count] = oneChild; count++;
    				}
    			}
    		}

    	}

    	return(SelectedNodes);
    }

    public static String NodeAttributeValueGet(Node TheNode, 
    		String AttributeName){
    	String theValue = null;
    	NamedNodeMap attrs = TheNode.getAttributes();
    	Attr nameAttribute = (Attr)attrs.getNamedItem(AttributeName);
    	if(nameAttribute != null) theValue = nameAttribute.getValue();
    	return theValue;
    }

    public static Node[] NodeSelectManyGivenAttributeValue(Node[] Nodes, 
    		String AttributeName, 
    		String Value){
    	Node[] selectedNodes = null;
    	ArrayList<Node> selectedNodesList = new ArrayList<Node>();

    	int len = Nodes.length;
    	for(int i = 0; i < len; i++){
    		String AttributeValue = NodeAttributeValueGet(Nodes[i],
    				AttributeName);
    		if(AttributeValue.equals(Value)){
    			selectedNodesList.add(Nodes[i]);
    		}
    	}
    	selectedNodes = new Node[len = selectedNodesList.size()];

    	for(int i = 0; i < len; i++){
    		selectedNodes[i] = (Node)selectedNodesList.get(i);
    	}

    	return selectedNodes;
    }

}

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
			
			ActivityGateway activityGateway = new ActivityGateway();
			BarGateway bargateway = new BarGateway();
			EventGateway eventgateway = new EventGateway();
			ItemGateway itemgateway = new ItemGateway();
			MenuGateway menugateway = new MenuGateway();
			SpecialGateway specialGateway = new SpecialGateway();
			
			
			Node documentNode = DocumentNodeGet(theDocument);
		   	System.out.println("Document node name = " + documentNode.getNodeName());
		   	
		   	
		   	Node[] insertArray = NodeElementSelectManyGivenName(documentNode, "insert");
		   	System.out.println("INSERT");
		   	for (int i = 0; i < insertArray.length; i++){
		   		Node insert = insertArray[i];
		   		NodeList children = insert.getChildNodes();
		   		//there is only one child
		   		Node child = children.item(1);
		   		
		   		String name1 = child.getNodeName();
		   		System.out.println(name1);
		   		
		   		String title,start,end,description,date,name,type,address,open,close,attribute = null;
				double cost;
				int price, day, attrib;
		   		
		   		switch (name1) {
		   		case "bar":
		   			//get the child elements
		   			NodeList barchildren = child.getChildNodes();
		   			name = barchildren.item(1).getTextContent();
		   			type = barchildren.item(3).getTextContent();
					price = Integer.parseInt(barchildren.item(5).getTextContent());
					address = barchildren.item(7).getTextContent();
					open =barchildren.item(9).getTextContent();
					close =barchildren.item(11).getTextContent();
					Bar bar1 = new Bar(name, type, price, address, open, close);
					bargateway.insert(bar1);
		   			break;
		   		case "menu":
		   			//get the attribute
		   			attribute = NodeAttributeValueGet(child, "BarID");
			   		attrib = Integer.parseInt(attribute);
		   			//get the child elements
		   			NodeList menuchildren = child.getChildNodes();
		   			title = menuchildren.item(1).getTextContent();
					day = Integer.parseInt(menuchildren.item(3).getTextContent());
					start = menuchildren.item(5).getTextContent();
					end = menuchildren.item(7).getTextContent();
					Menu menu1 = new Menu(title,day, start, end, attrib);
					menugateway.insert (menu1);
		   			break;
		   		case "special":
		   			//get the attribute
		   			attribute = NodeAttributeValueGet(child, "BarID");
			   		attrib = Integer.parseInt(attribute);
		   			//get the child elements
		   			NodeList specialchildren = child.getChildNodes();
		   			title = specialchildren.item(1).getTextContent();
					day = Integer.parseInt(specialchildren.item(3).getTextContent());
					start = specialchildren.item(5).getTextContent();
					end = specialchildren.item(7).getTextContent();
					Special special2 = new Special(title, day,start,end,attrib);
					specialGateway.insert(special2);
		   			break;
		   		case "activity":
		   			//get the attribute
		   			attribute = NodeAttributeValueGet(child, "BarID");
			   		attrib = Integer.parseInt(attribute);
		   			//get the child elements
		   			NodeList activitychildren = child.getChildNodes();
		   			name = activitychildren.item(1).getTextContent();
					type = activitychildren.item(3).getTextContent();
					day = Integer.parseInt(activitychildren.item(5).getTextContent());
					start = activitychildren.item(7).getTextContent();
					end = activitychildren.item(9).getTextContent();
					cost = Double.parseDouble(activitychildren.item(11).getTextContent());
					
					Activity activity = new Activity(name,type,day,start,end,cost,attrib);
					activityGateway.insert(activity);
		   			break;
		   		case "event":
		   			//get the attribute
		   			attribute = NodeAttributeValueGet(child, "BarID");
			   		attrib = Integer.parseInt(attribute);
		   			//get the child elements
		   			NodeList eventchildren = child.getChildNodes();
		   			name = eventchildren.item(1).getTextContent();
					description = eventchildren.item(3).getTextContent();
					date = eventchildren.item(5).getTextContent();
					start = eventchildren.item(7).getTextContent();
					end = eventchildren.item(9).getTextContent();
					Event event1 = new Event(name,description,date,start,end,attrib);
					eventgateway.insert(event1);
		   			break;
		   		case "item":
		   			//get the attribute, from two choices
		   			int flag = 0;
		   			NamedNodeMap attrs = child.getAttributes();
		   	    	Attr nameAttribute = (Attr)attrs.getNamedItem("MenuID");
		   	    	if(nameAttribute != null) {
		   	    		attribute = nameAttribute.getValue();
		   	    		flag = 1;
		   	    	}
		   	    	nameAttribute = (Attr)attrs.getNamedItem("SpecialID");
		   	    	if(nameAttribute != null){
		   	    		attribute = nameAttribute.getValue();
		   	    		flag = 2;
		   	    	}
		   	    	attrib = Integer.parseInt(attribute);
			   		
		   			//get the child elements
		   			NodeList itemchildren = child.getChildNodes();
		   			name = itemchildren.item(1).getTextContent();
					type = itemchildren.item(3).getTextContent();
					double price1 = Double.parseDouble(itemchildren.item(5).getTextContent());
		   			
					if (flag == 1){
						 Item item1 = new Item(name, type, price1, attrib, -1);
						 itemgateway.insert(item1);
					} else if (flag == 2){
						 Item item1 = new Item(name, type, price1, -1, attrib);
						 itemgateway.insert(item1);
					} else {
						 Item item1 = new Item(name, type, price1, -1, -1);
						 itemgateway.insert(item1);
					}
					
					
					break;
		   		default: 
	                System.out.println("ELEMENT NAME NOT FOUND");
	                break;
		   		
		   		}
		   	}
		   	
		   	Node[] updateArray = NodeElementSelectManyGivenName(documentNode, "update");
		   	System.out.println("UPDATE");
		   	for (int i = 0; i < updateArray.length; i++){
		   		Node update = updateArray[i];
		   		NodeList children = update.getChildNodes();
		   		//there is only one child
		   		Node child = children.item(1);
		   		
		   		String name = child.getNodeName();
		   		System.out.println(name);
		   		String attribute = NodeAttributeValueGet(child, "ID");
		   		int attrib = Integer.parseInt(attribute);
		   		
		   		switch (name) {
		   		case "bar":
		   			Bar bar = bargateway.find(attrib);
		   			
		   			//get the child elements
		   			NodeList barchildren = child.getChildNodes();
		   			
		   			bar.setName(barchildren.item(1).getTextContent()); 
					bar.setType(barchildren.item(3).getTextContent());
					bar.setPrice(Integer.parseInt(barchildren.item(5).getTextContent()));
					bar.setAddress(barchildren.item(7).getTextContent());
					bar.setOpen(barchildren.item(9).getTextContent());
					bar.setClose(barchildren.item(11).getTextContent());
		   			//update the bar
		   			bargateway.update(bar);
		   			break;
		   		case "menu":
		   			System.out.println(attrib);
		   			Menu menu = menugateway.find(attrib);
		   			//get the child elements
		   			NodeList menuchildren = child.getChildNodes();
		   			menu.setTitle(menuchildren.item(1).getTextContent());
		   			menu.setDay(Integer.parseInt(menuchildren.item(3).getTextContent()));
		   			menu.setStart(menuchildren.item(5).getTextContent());
		   			menu.setEnd(menuchildren.item(7).getTextContent());
		   			//update the menu
		   			menugateway.update(menu);
		   			break;
		   		case "special":
		   			Special special = specialGateway.find(attrib);
		   			//get the child elements
		   			NodeList specialchildren = child.getChildNodes();
		   			special.setTitle(specialchildren.item(1).getTextContent());
		   			special.setDay(Integer.parseInt(specialchildren.item(3).getTextContent()));
		   			special.setStart(specialchildren.item(5).getTextContent());
		   			special.setEnd(specialchildren.item(7).getTextContent());
		   			//update the special
		   			specialGateway.update(special);
		   			break;
		   		case "activity":
		   			Activity activity = activityGateway.find(attrib);
		   			//get the child elements
		   			NodeList activitychildren = child.getChildNodes();
		   			activity.setName(activitychildren.item(1).getTextContent());
		   			activity.setType(activitychildren.item(3).getTextContent());
		   			activity.setDay(Integer.parseInt(activitychildren.item(5).getTextContent()));
		   			activity.setStart(activitychildren.item(7).getTextContent());
		   			activity.setEnd(activitychildren.item(9).getTextContent());
		   			activity.setCost(Double.parseDouble(activitychildren.item(11).getTextContent()));
		   			//update the activity
		   			activityGateway.update(activity);
		   			break;
		   		case "event":
		   			Event event = eventgateway.find(attrib);
		   			//get the child elements
		   			NodeList eventchildren = child.getChildNodes();
		   			event.setName(eventchildren.item(1).getTextContent());
		   			event.setDescription(eventchildren.item(3).getTextContent());
		   			event.setDate(eventchildren.item(5).getTextContent());
		   			event.setStart(eventchildren.item(7).getTextContent());
		   			event.setEnd(eventchildren.item(9).getTextContent());
		   			//update the event
		   			eventgateway.update(event);
		   			break;
		   		case "item":
		   			Item item = itemgateway.find(attrib);
		   			//get the child elements
		   			NodeList itemchildren = child.getChildNodes();
		   			item.setName(itemchildren.item(1).getTextContent());
		   			item.setType(itemchildren.item(3).getTextContent());
		   			item.setPrice(Double.parseDouble(itemchildren.item(5).getTextContent()));
		   			//update the item
		   			itemgateway.update(item);
		   			break;
		   		default: 
	                System.out.println("ELEMENT NAME NOT FOUND");
	                break;
		   		
		   		}
		   	}
		   	
		   	Node[] deleteArray = NodeElementSelectManyGivenName(documentNode, "delete");
		   	System.out.println("DELETE");
		   	for (int i = 0; i < deleteArray.length; i++){
		   		Node delete = deleteArray[i];
		   		NodeList children = delete.getChildNodes();
		   		//there is only one child
		   		Node child = children.item(1);
		   		
		   		String name = child.getNodeName();
		   		System.out.println(name);
		   		String attribute = NodeAttributeValueGet(child, "ID");
		   		int attrib = Integer.parseInt(attribute);
		   		
		   		switch (name) {
		   		case "bar":
		   			Bar bar = bargateway.find(attrib);
		   			bargateway.destroy(bar);
		   			break;
		   		case "menu":
		   			Menu menu = menugateway.find(attrib);
		   			menugateway.destroy(menu);
		   			break;
		   		case "special":
		   			Special special = specialGateway.find(attrib);
		   			specialGateway.destroy(special);
		   			break;
		   		case "activity":
		   			Activity activity = activityGateway.find(attrib);
		   			activityGateway.destroy(activity);
		   			break;
		   		case "event":
		   			Event event = eventgateway.find(attrib);
		   			eventgateway.destroy(event);
		   			break;
		   		case "item":
		   			Item item = itemgateway.find(attrib);
		   			itemgateway.destroy(item);
		   			break;
		   		default: 
	                System.out.println("ELEMENT NAME NOT FOUND");
	                break;
		   		
		   		}
		   		
		   		
		   	}
		   	
			System.out.println("Parsing complete");
		} catch (Exception E){
			System.out.println("Error in parsing");
			E.printStackTrace();
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

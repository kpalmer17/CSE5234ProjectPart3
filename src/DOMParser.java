import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;

public class DOMParser {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			Document theDocument = FileRootDocumentGet("C:/School/CSE5234/sample projects/CSE5234ProjectPart3/src/docs/BarExample1.xml");
			//validate
			
			XMLloadValidator validator = new XMLloadValidator();
			
			boolean tf = validator.validate("C:/School/CSE5234/sample projects/CSE5234ProjectPart3/src/docs/BarExample1.xml", "C:/School/CSE5234/sample projects/CSE5234ProjectPart3/src/docs/bardoc.xsd");
			
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
			Node[] barArray = NodeElementSelectManyGivenName(documentNode, "bar");
			
			   /////////////////////////////////////////////////////////////////////////////////////////////////////
				
				String title,start,end,description,date,name,type,address,open,close;
				double cost;
				int price, day;
				
				
				///////////////////////////////////Array of bars//////////////////////////////////////////////////
			for(int i = 0; i < barArray.length; i++){
				Node bar = barArray[i];
				int barid = bargateway.nextID;
				NodeList children = bar.getChildNodes();
				 
				 name = children.item(1).getTextContent();
				 type = children.item(3).getTextContent();
				 price = Integer.parseInt(children.item(5).getTextContent());
				 address = children.item(7).getTextContent();
				 open =children.item(9).getTextContent();
				 close =children.item(11).getTextContent();
				 Bar bar1 = new Bar(name, type, price, address, open, close);
				 bargateway.insert(bar1);
			
			
				//////////////////////////////////for menus//////////////////////////////////////////////////////
				Node[] menuArray = NodeElementSelectManyGivenName(barArray[i], "menu");
				for(int j = 0; j < menuArray.length; j++){
				Node menu = menuArray[j];
				NodeList children1 = menu.getChildNodes();
				  title = children1.item(1).getTextContent();
				  day = Integer.parseInt(children1.item(3).getTextContent());
				  start = children1.item(5).getTextContent();
				  end = children1.item(7).getTextContent();
				  Menu menu1 = new Menu(title,day, start, end, barid);
				  menugateway.insert (menu1);
				  
				
					///////////////////////////////for items in menu////////////////////////////////////////////
				 	Node[] itemArray = NodeElementSelectManyGivenName(menuArray[j], "item");
					for(j = 0; j < itemArray.length; j++){
					 Node item = itemArray[j];
					 children = item.getChildNodes();
					 name = children.item(1).getTextContent();
					 type = children.item(3).getTextContent();
					 double price1 = Double.parseDouble(children.item(5).getTextContent());
					 int menuid = menugateway.nextID-1;
					 int specialid  = specialGateway.nextID;
					 Item item1 = new Item(name, type, price1, menuid, specialid);
					 itemgateway.insert(item1);
					}
				}
				
				////////////////////////////////for Specials///////////////////////////////////////
				Node[] SpecialsArray = NodeElementSelectManyGivenName(barArray[i], "special");
				for(int j = 0; j < SpecialsArray.length; j++){
				Node special = SpecialsArray[j];
				 children = special.getChildNodes();
				   title = children.item(1).getTextContent();
				   day = Integer.parseInt(children.item(3).getTextContent());
				   start = children.item(5).getTextContent();
				   end = children.item(7).getTextContent();
				   Special special2 = new Special(title, day,start,end,barid);
				   specialGateway.insert(special2);
				  
					///////////////////////////////for items in specials////////////////////////////////////////////
				 	Node[] itemArray = NodeElementSelectManyGivenName(SpecialsArray[j], "item");

					for(int k = 0; k < itemArray.length;k++){
					 Node item1 = itemArray[k];
					 children = item1.getChildNodes();
					 name = children.item(1).getTextContent();
					 type = children.item(3).getTextContent();
					 double price1 = Double.parseDouble(children.item(5).getTextContent());
					 int menuid = -1;
					 int specialid  = specialGateway.nextID-1;
					 Item item2 = new Item(name, type, price1, menuid, specialid);
					 itemgateway.insert(item2);;
					}	
				}
				
				////////////////////////////////////for activities/////////////////////////////////////////
				Node[] Activities_Array = NodeElementSelectManyGivenName(barArray[i], "activity");
				for(int j = 0; j < Activities_Array.length; j++){
				Node activites = Activities_Array[j];
				children = activites.getChildNodes();
				name = children.item(1).getTextContent();
				type = children.item(3).getTextContent();
				day = Integer.parseInt(children.item(5).getTextContent());
				start = children.item(7).getTextContent();
				end = children.item(9).getTextContent();
				cost = Double.parseDouble(children.item(11).getTextContent());
				
				Activity activity = new Activity(name,type,day,start,end,cost,barid);
				activityGateway.insert(activity);
				}
				
				////////////////////////////////////////////event////////////////////////////////////////////// 
				//for menus
				Node[] Event_Array = NodeElementSelectManyGivenName(barArray[i], "event");
				for(int j = 0; j < Event_Array.length; j++){
				Node event = Event_Array[j];
				children = event.getChildNodes();
				name = children.item(1).getTextContent();
				description = children.item(3).getTextContent();
				date = children.item(5).getTextContent();
				start = children.item(7).getTextContent();
				end = children.item(9).getTextContent();
				Event event1 = new Event(name,description,date,start,end,barid);
				eventgateway.insert(event1);
			}
				}//end of bar loop
			System.out.println("Parsing complete");
		} catch (Exception E)
			{
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
    
    public static Node[] NodeElementSelectManyGivenName(Node TheNode,String TheName){
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
    	return SelectedNodes;
    }
    
}

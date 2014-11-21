import java.sql.*;
public class SetupDB {
    public static void main(String[] a) throws Exception {
        Connection conn = null;
        Statement stmt = null;    	
        try {
        	//connect to h2 DB
        	Class.forName("org.h2.Driver");
        	conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        	System.out.println("Database Connection Successful");
        
        	//Create Tables
        	System.out.println("Creating Tables");
        	stmt = conn.createStatement();
        	
        	//Create Bar Table
        	System.out.println("Creating Bar Table");
        	
        	 String sql = "CREATE TABLE IF NOT EXISTS BAR " +
                     "(barid INTEGER not NULL, " +
                     " name VARCHAR(255), " + 
                     " type VARCHAR(255), " +
                     " price INTEGER, " +
                     " address VARCHAR(255), " + 
                     " openhour VARCHAR(255), " +
                     " closehour VARCHAR(255), " +
                     " PRIMARY KEY ( barid ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Bar Table");
        	//Create Menu Table
        	System.out.println("Creating Menu Table"); 
        	
        	sql = "CREATE TABLE IF NOT EXISTS MENU " +
                    "(menuid INTEGER not NULL, " +
                    " title VARCHAR(255), " + 
                    " day VARCHAR(255), " + 
                    " starttime VARCHAR(255), " +
                    " endtime VARCHAR(255), " +
                    " barid INTEGER not NULL," +
                    " FOREIGN KEY ( barid ) REFERENCES BAR(barid)," +
                    " PRIMARY KEY ( menuid ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Menu Table");
        	//Create Specials Table
        	System.out.println("Creating Specials Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS SPECIAL " +
                     "(specialid INTEGER not NULL, " +
                     " title VARCHAR(255), " + 
                     " day VARCHAR(255), " + 
                     " starttime VARCHAR(255), " +
                     " endtime VARCHAR(255), " +
                     " barid INTEGER not NULL," +
                     " FOREIGN KEY ( barid ) REFERENCES BAR(barid), " +
                     " PRIMARY KEY ( specialid ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Special Table");
        	//Create Item Table
        	System.out.println("Creating Item Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS ITEM " +
                     "(itemid INTEGER not NULL, " +
                     " name VARCHAR(255), " + 
                     " type VARCHAR(255), " +
                     " price FLOAT, " +
                     " menuid INTEGER, " + 
                     " specialid INTEGER, " +
                     " FOREIGN KEY ( menuid ) REFERENCES MENU(menuid)," +
        			 " FOREIGN KEY ( specialid ) REFERENCES SPECIAL(specialid)," +
                     " PRIMARY KEY ( itemid ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Item Table");
        	//Create Activity Table
        	System.out.println("Creating Activity Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS ACTIVITY " +
                     "(activityid INTEGER not NULL, " +
                     " name VARCHAR(255), " + 
                     " type VARCHAR(255), " +
                     " day VARCHAR(255), " + 
                     " starttime VARCHAR(255), " +
                     " endtime VARCHAR(255), " +
                     " cost FLOAT, " +
                     " barid INTEGER not NULL, " +
                     " FOREIGN KEY ( barid ) REFERENCES BAR(barid), " +
                     " PRIMARY KEY ( activityid ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Activity Table");
        	 
         	//Create Account Table
         	System.out.println("Creating Account Table");
         	
         	sql = "CREATE TABLE IF NOT EXISTS ACCOUNT " +
                      "(userid INTEGER not NULL, " +
                      " username VARCHAR(255), " + 
                      " password VARCHAR(255), " +
                      " PRIMARY KEY ( userid ))";
         	 
         	 stmt.executeUpdate(sql);
         	 System.out.println("Created Account Table");
         	 
        	//Create Comment table
        	System.out.println("Creating Rating Table");
        	
        	sql = "CREATE TABLE  IF NOT EXISTS RATING " +
                     "(ratingid INTEGER not NULL, " +
                     " rating INTEGER, " +
                     " comment VARCHAR(255), " + 
                     " date VARCHAR(255), " +
                     " userid INTEGER not NULL, " +
                     " barid INTEGER not NULL, " + 
                     " FOREIGN KEY ( barid ) REFERENCES BAR(barid), " +
        			 " FOREIGN KEY ( userid ) REFERENCES ACCOUNT(userid), " +
                     " PRIMARY KEY ( ratingid ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Rating Table");
    
        	//Create Event Table
        	System.out.println("Creating Event Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS EVENT " +
                     "(eventid INTEGER not NULL, " +
                     " name VARCHAR(255), " + 
                     " description VARCHAR(255), " +
                     " date VARCHAR(255), " + 
                     " starttime VARCHAR(255), " +
                     " endtime VARCHAR(255), " +
                     " barid INTEGER not null, " +
                     " FOREIGN KEY ( barid ) REFERENCES BAR(barid), " +
                     " PRIMARY KEY ( eventid ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Event Table");
        	//Create BarEvent Table
        	System.out.println("Creating BarEvents Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS BAREVENTS " +
                    "(barid INTEGER not NULL, " +
                    " eventid INTEGER not NULL, " + 
                    " PRIMARY KEY ( barid, eventid ), " + 
                    " FOREIGN KEY ( barid ) REFERENCES BAR(barid), " +
       		   	    " FOREIGN KEY ( eventid ) REFERENCES EVENT(eventid))";
       	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created BarEvent Table");
        	//Create UserEvent Table
        	System.out.println("Creating UserEvents Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS USEREVENTS " +
                    "(userid INTEGER not NULL, " +
                    " eventid INTEGER not NULL, " + 
                    " PRIMARY KEY ( userid, eventid ), " + 
                    " FOREIGN KEY ( eventid ) REFERENCES EVENT(eventid), " +
       			    " FOREIGN KEY ( userid ) REFERENCES ACCOUNT(userid))";
       	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created UserEvents Table");
        	//Create Favorite Table
        	System.out.println("Creating Favorites Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS FAVORITES " +
                     "(userid INTEGER not NULL, " +
                     " barid INTEGER not NULL, " + 
                     " PRIMARY KEY ( userid, barid ), " + 
                     " FOREIGN KEY ( barid ) REFERENCES BAR(barid), " +
        			 " FOREIGN KEY ( userid ) REFERENCES ACCOUNT(userid))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Favorites Table");
        	
        } catch (SQLException se){
        	//Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
        	//Handle Other Errors
        	e.printStackTrace();
        } finally{
            //finally block used to close resources
            try{
               if(stmt!=null)
                  conn.close();
                  System.out.println("Closing DB Connection");
            }catch(SQLException se){
            }// do nothing
            try{
               if(conn!=null)
                  conn.close();
               	  System.out.println("Closing DB Connection");
            }catch(SQLException se){
               se.printStackTrace();
            }//end finally try
         }//end try
    }
}

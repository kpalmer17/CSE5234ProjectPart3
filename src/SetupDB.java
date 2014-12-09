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
                     "(BARID INTEGER not NULL, " +
                     " ADDRESS VARCHAR(255), " + 
                     " CLOSE VARCHAR(255), " +
                     " NAME VARCHAR(255), " +
                     " OPEN VARCHAR(255), " + 
                     " PRICE INTEGER, " +
                     " TYPE VARCHAR(255), " +
                     " PRIMARY KEY ( BARID ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Bar Table");
        	//Create Menu Table
        	System.out.println("Creating Menu Table"); 
        	
        	sql = "CREATE TABLE IF NOT EXISTS MENU " +
                    "(MENUID INTEGER not NULL, " +
                    " BARID INTEGER not NULL, " + 
                    " DAY VARCHAR(255), " + 
                    " END VARCHAR(255), " +
                    " START VARCHAR(255), " +
                    " TITLE VARCHAR(255)," +
                    " FOREIGN KEY ( BARID ) REFERENCES BAR(BARID)," +
                    " PRIMARY KEY ( MENUID ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Menu Table");
        	//Create Specials Table
        	System.out.println("Creating Specials Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS SPECIAL " +
                     "(SPECIALID INTEGER not NULL, " +
                     " BARID INTEGER not NULL, " + 
                     " DAY VARCHAR(255), " + 
                     " END VARCHAR(255), " +
                     " START VARCHAR(255), " +
                     " TITLE VARCHAR(255)," +
                     " FOREIGN KEY ( BARID ) REFERENCES BAR(BARID)," +
                     " PRIMARY KEY ( SPECIALID ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Special Table");
        	//Create Item Table
        	System.out.println("Creating Item Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS ITEM " +
                     "(ITEMID INTEGER not NULL, " +
                     " MENUID INTEGER, " + 
                     " NAME VARCHAR(255), " +
                     " price FLOAT, " +
                     " SPECIALID INTEGER, " + 
                     " TYPE VARCHAR(255), " +
                     " PRIMARY KEY ( ITEMID ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Item Table");
        	//Create Activity Table
        	System.out.println("Creating Activity Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS ACTIVITY " +
                     "(ACTIVITYID INTEGER not NULL, " +
                     " BARID INTEGER not NULL, " + 
                     " COST FLOAT, " +
                     " DAY VARCHAR(255), " + 
                     " END VARCHAR(255), " +
                     " NAME VARCHAR(255), " +
                     " START VARCHAR(255), " +
                     " TYPE VARCHAR(255), " +
                     " FOREIGN KEY ( BARID ) REFERENCES BAR(BARID), " +
                     " PRIMARY KEY ( ACTIVITYID ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Activity Table");
        	 
         	//Create Account Table
         	System.out.println("Creating Account Table");
         	
         	sql = "CREATE TABLE IF NOT EXISTS ACCOUNT " +
                      "(USERID INTEGER not NULL, " +
                      " PASSWORD VARCHAR(255), " + 
                      " USERNAME VARCHAR(255), " +
                      " PRIMARY KEY ( USERID ))";
         	 
         	 stmt.executeUpdate(sql);
         	 System.out.println("Created Account Table");
         	 
        	//Create Comment table
        	System.out.println("Creating Rating Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS RATING " +
                     "(RATINGID INTEGER not NULL, " +
                     " BARID INTEGER not NULL, " +
                     " COMMENT VARCHAR(255), " + 
                     " DATE VARCHAR(255), " +
                     " RATING INTEGER, " +
                     " USERID INTEGER not NULL, " + 
                     " FOREIGN KEY ( BARID ) REFERENCES BAR(BARID), " +
        			 " FOREIGN KEY ( USERID ) REFERENCES ACCOUNT(USERID), " +
                     " PRIMARY KEY ( RATINGID ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Rating Table");
    
        	//Create Event Table
        	System.out.println("Creating Event Table");
        	
        	sql = "CREATE TABLE IF NOT EXISTS EVENT " +
                     "(EVENTID INTEGER not NULL, " +
                     " BARID INTEGER not null, " + 
                     " DATE VARCHAR(255), " +
                     " DESCRIPTION VARCHAR(255), " + 
                     " END VARCHAR(255), " +
                     " NAME VARCHAR(255), " +
                     " START VARCHAR(255), " +
                     " FOREIGN KEY ( BARID ) REFERENCES BAR(BARID), " +
                     " PRIMARY KEY ( EVENTID ))";
        	 
        	 stmt.executeUpdate(sql);
        	 System.out.println("Created Event Table");
        	
        	
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

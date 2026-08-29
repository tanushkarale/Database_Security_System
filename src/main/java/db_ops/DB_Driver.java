
package db_ops;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


public class DB_Driver {
     public Connection con=null;
    public Statement st=null;
  
    public Statement getDbStatement(){
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         con =DriverManager.getConnection("jdbc:mysql://localhost:3306/db_security_tbm","root","Root");
         st =con.createStatement();
        }
        catch(Exception e){
            System.out.println("Exception At Class DbDriver In Function Is getDbStatement"+e);
        }
    return st;
    }
       
    public Statement getCulpritDbStatement(String username, String password){
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         con =DriverManager.getConnection("jdbc:mysql://localhost:3306/db_security_tbm",username,password);
         st =con.createStatement();
        }
        catch(Exception e){
            System.out.println("Exception At Class DbDriver In Function Is getCulpritDbStatement"+e);
        }
    return st;
    } 
    
}



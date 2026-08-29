
package client_operation;

import db_ops.DB_Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


public class Client_Data_View_Download_DBOP {
    
    public ArrayList<String> getClientData(String client_username)
    {
        ArrayList<String> client_data = new ArrayList<String>();
        
        try 
        {
            Statement st =  new DB_Driver().getDbStatement();
            String query ="select * from client_data_info where client_username ='"+client_username+"'";
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
             String tablename=  rs.getString("tablename");  
             client_data.add(tablename);
             
            }
            
        } 
        catch (Exception e) 
        {
            System.out.println("exception at class Client_Data_View_Download_DBOP in function getClientData(): "+e);
            
        }
        
        
        return client_data;
    }
    
    public String[] getTableColumnName(String tablename)
    { 
        String columnname[]=null;
        try
        {
          Statement st =  new DB_Driver().getDbStatement();
          String query ="select * from "+tablename;
          ResultSet rs = st.executeQuery(query);
          ResultSetMetaData rsmd = rs.getMetaData();
          int noc=rsmd.getColumnCount();
            System.out.println("number of column:"+noc);
          columnname = new String[noc];
             
            for (int i = 0; i < noc; i++)
            {
               columnname[i]=rsmd.getColumnName(i+1);
               
            }
          
            
        } 
        catch (Exception e)
        {
            System.out.println("exception at class Client_Data_View_Download_DBOP in function getTableColumnName: "+e);  
        }
     return columnname;
    }
    
    public  String[][] getTableData(String tablename,int noc){
        
        String data_matrix[][] = null;
        try
        {
          Statement st1 =  new DB_Driver().getDbStatement();
          Statement st2 =  new DB_Driver().getDbStatement();
          String query ="select * from "+tablename;
          
          ResultSet rs1=st1.executeQuery(query);
          ResultSet rs2=st2.executeQuery(query);
          int rowcount=0;
          while(rs1.next())
                rowcount++;      
                
          data_matrix=new String[rowcount][noc];
          int i=0;
          while(rs2.next())
          {
              for (int j = 0; j < noc; j++) 
              {
                  data_matrix[i][j]=rs2.getString(j+1);
              }
               i++;
          }
          
          
          
            
        } 
        catch (Exception e)
        {
            System.out.println("exception at class Client_Data_View_Download_DBOP in function getTableColumnName: "+e);  
        }
        
        
        
        return data_matrix;
    }
    
}

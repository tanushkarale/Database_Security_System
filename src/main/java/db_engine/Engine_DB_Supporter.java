
package db_engine;


import db_ops.DB_Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


public class Engine_DB_Supporter {
    
    
    public ArrayList getClientInfo(){
        
        ArrayList client_data = new ArrayList();
        
        try {
            Statement st = new DB_Driver().getDbStatement();
            String query = "select * from client_data_info";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                ArrayList single_row =new ArrayList();
               String client_name= rs.getString(1);
               String table_name= rs.getString(2);
                
                single_row.add(client_name);
                single_row.add(table_name);
                client_data.add(single_row);
            }
            
            
        }
        catch (Exception e)
        {
            System.out.println("Exception At Class Engine DB Supporter in Function getClientInfo:"+e);
        }
        
        return client_data;
        
    }
    
       public ArrayList<String> getTableColumnNames(String tablename)
    {
        ArrayList<String> column_name = new ArrayList<String>();
        try
        {
            Statement st = new DB_Driver().getDbStatement();
            String query ="select * from "+tablename;
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int noc=rsmd.getColumnCount();
            System.out.println("number of column:"+noc);
          
             
            for (int i = 0; i < noc; i++)
            {
               String cn=rsmd.getColumnName(i+1);
               column_name.add(cn);
               
            }        
        }
        catch(Exception  ex)
        {
            System.out.println("Exception at class Engine_DB_Supporter in function getTableColmnNames "+ex);
        
        }
        return column_name;
    }
    public ArrayList getCompleteTableDataforRoot(String tablename ,int columncount)
    {
        ArrayList complete_data = new ArrayList();
        try
        {
            Statement st = new DB_Driver().getDbStatement();
            String query = "select * from "+tablename;
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                ArrayList single_row = new ArrayList();
                for (int i = 0; i <columncount; i++) {
                    single_row.add(rs.getString(i+1));
                }
                complete_data.add(single_row);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Engine_DB_Supporter in function getCompleteTableData "+ex);
        }
        return complete_data;
    }
    
    public ArrayList getCompleteTableDataforCulprit(String tablename ,int columncount,String username,String password)
    {
        ArrayList complete_data = new ArrayList();
        try
        {
            Statement st = new DB_Driver().getCulpritDbStatement(username, password);
            String query = "select * from "+tablename;
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
                ArrayList single_row = new ArrayList();
                for (int i = 0; i <columncount; i++) {
                    single_row.add(rs.getString(i+1));
                }
                complete_data.add(single_row);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Engine_DB_Supporter in function getCompleteTableData "+ex);
        }
        return complete_data;
    }
    public String getClientEmailID(String clientname)
    {
        String email_id=null;
        try
        {
             Statement st = new DB_Driver().getDbStatement();
             String query = "select * from client_info where username='"+clientname+"'";
             ResultSet rs = st.executeQuery(query);
             while(rs.next())
             {
                 email_id = rs.getString("email_id");
             }
        }
        catch(Exception e)
        {
            System.out.println("Exception at class Engine_DB_Suppoter in function getClientEmailID "+e);
        }
        return email_id;
    }
}

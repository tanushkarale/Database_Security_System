
package client_operation;


import db_ops.DB_Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Client_Db_Operation {
    
    
    public  boolean isClientRegistered(String username, String client_name,String client_type,String client_type_name,String email_id,String mobile_no,String password )
    {   
       
        boolean flag = false;
        
        try {
            
           Statement st =  new DB_Driver().getDbStatement();
           String query = "Insert into client_info values('"+username+"','"+client_name+"','"+client_type+"','"+client_type_name+"','"+email_id+"','"+mobile_no+"','"+password+"')";
           int nor = st.executeUpdate(query);
           if(nor>0)
               flag=true;
            
        } 
        catch (Exception e)
        {
            flag = false;
            System.out.println("Exception At In The Class ClientDbOperation In The Function isClientRegister "+e); 
            
        }
        return flag;
    }
    
    public boolean isClientRegisterExisted(String username , String password)
    {
        boolean flag = false;
        
        try
        {
             Statement st =  new DB_Driver().getDbStatement();
             String query = "select * from client_info where username ='"+username+"' and password= '"+password+"'";
             ResultSet rs = st.executeQuery(query);
             
             if(rs.next())
             {
                 flag = true;
             }
        }
        catch(Exception ex)
        {
            System.out.println("Exception At Class In Client_Db_Operation Function isClientRegisterExstied() :"+ex);
        }
        return flag;
    }
    public ArrayList getClientinfo(String un)
    {
        ArrayList<String> client_data = new ArrayList<String>();
        try
        {
            Statement st =  new DB_Driver().getDbStatement();
            String query ="select * from client_info where username ='"+un+"'";
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {

                String username = rs.getString("username");
                String client_name = rs.getString("client_name");
                String client_type = rs.getString("client_type");
                String client_type_name = rs.getString("client_type_name");
                String email_id = rs.getString("email_id");
                String mobile_no = rs.getString("mobile_no");
                String password = rs.getString("password");

                
                client_data.add(client_name);
                client_data.add(client_type);
                client_data.add(client_type_name);
                client_data.add(email_id);
                client_data.add(mobile_no);
                client_data.add(username);
                client_data.add(password);
               
            }
              
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Client_Db_Operation in function getClientinfo() "+ex);
        }
        return client_data;
    }
    
    public boolean isDataUpdated(String uname,String client_name,String client_type, String client_type_name, String email_id,  String mobile_no, String password)
    {
        boolean flag = false;
        try
        {
            //username,client_name,client_type, client_type_name, email_id, mobile_no, password
            Statement st =  new DB_Driver().getDbStatement();
            String query ="update client_info set client_name='"+client_name+"',client_type = '"+client_type+"',client_type_name='"+client_type_name+"',email_id = '"+email_id+"',mobile_no = '"+mobile_no+"',password = '"+password+"'where username = '"+uname+"'";
            int nor = st.executeUpdate(query);
            if(nor>0)
                flag=true;
            
        }
        catch(Exception ex)
        {
            
        }
        return flag;
    }
}

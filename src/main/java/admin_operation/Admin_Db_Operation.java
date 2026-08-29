
package admin_operation;

import db_ops.DB_Driver;
import java.sql.ResultSet;
import java.sql.Statement;


public class Admin_Db_Operation {
    
    
    public boolean isAdminExisted(String un , String pwd)
    {
        boolean flag = false;
        
        try
        {
             Statement st =  new DB_Driver().getDbStatement();
             String query = "select * from admin_info where username ='"+un+"' and password= '"+pwd+"'";
             ResultSet rs = st.executeQuery(query);
             
             if(rs.next())
             {
                 flag = true;
             }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Admin_Db_Operation in function isAdminExisted() : "+ex);
        }
        return flag;
    }
    
    public boolean isPasswordExisted(String pwd)
    {
        boolean flag = false;
        
        try
        {
             Statement st =  new DB_Driver().getDbStatement();
             String query = "select * from admin_info where password= '"+pwd+"'";
             ResultSet rs = st.executeQuery(query);
             
             if(rs.next())
             {
                 flag = true;
             }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Admin_Db_Operation in function ispasswordExisted() : "+ex);
        }
        return flag;
    }

  public boolean isProfileUpdate(String username,String password){
        
        boolean flag = false;
        try {
                   
            Statement st =  new DB_Driver().getDbStatement();
            String query ="update admin_info set password='"+password+"' where username='"+username+"'";
            int nor = st.executeUpdate(query);
            if(nor>0)
                flag=true;
            
            
            
        } 
        catch (Exception e)
        {
            
            System.out.println("exception at class Admin_Db_Operation in function isProfileUpdate: "+e);
        }
 
        return flag;
    }
  

}


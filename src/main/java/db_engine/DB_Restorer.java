/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db_engine;

import db_ops.DB_Driver;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author TANUSH
 */
public class DB_Restorer {
    
    
    public boolean isDBRestored(String pkey_field ,String pkey_value , ArrayList tamper_column , ArrayList original_column ,String tablename,String username,String password){
        boolean flag = false;
        
        try {
                       
            Statement st = new DB_Driver().getCulpritDbStatement(username, password); 
            String query1="update "+tablename+" set ";
            String query2="";
            for (int i = 0; i <tamper_column.size() ; i++) 
            {
                
                String column_name=(String) tamper_column.get(i);
                String column_orginal_value=(String) original_column.get(i);
                String temp=column_name+"='"+column_orginal_value+"',";
                query2=query2+temp;
                
            }
            query2=query2.substring(0, query2.length()-1);
            String query3=" where "+pkey_field+" ='"+pkey_value+"'";
            String finalquery=query1+query2+query3;
            System.out.println("final query :"+finalquery);
            int nor=st.executeUpdate(finalquery);
            if(nor>0)
                flag=true;
            
            
            
        } 
        catch (Exception e) 
        {
          
            System.out.println("Exception At Class DB_Restorer in function isDBRestored(): "+e);
            
        }
        
        return flag;
    }
    
}

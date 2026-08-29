/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db_engine;

import admin_operation.DB_Tamper_Detection_Frame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author TANUSH
 */
public class Tamper_Detection_Thread extends Thread
{
    
   public String client_name= null;
   public String table_name =null;
    public boolean flag;
    
    public void run()
    {
        Engine_DB_Supporter edbs = new Engine_DB_Supporter();
        ArrayList<String> column_name = edbs.getTableColumnNames(table_name);
       // System.out.println("column name"+column_name);
        ArrayList intial_data = edbs.getCompleteTableDataforRoot(table_name, column_name.size());
        //System.out.println("intial data"+intial_data);
        String emailid = edbs.getClientEmailID(client_name);
        System.out.println("email id "+emailid);
        try 
        {
            while(flag)
          {
              Thread.sleep(5000);
            Date dt = new Date();
            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-YYYY  hh:mm:ss");
            String current_time = sdf.format(dt);
            
            ArrayList creadintial = new Culprit_Finder().getDBMCredential(); 
            String username = (String) creadintial.get(0);
            String password = (String) creadintial.get(1);
              System.out.println("cc: "+creadintial);
            
       
                if (!username.equals("root")) 
                {
                    ArrayList current_data = edbs.getCompleteTableDataforCulprit(table_name, column_name.size(),username,password);
                    if (!intial_data.equals(current_data)) 
                    {
                        ArrayList tamper_info = new TamperFieldIdentifier().getTamperFieldinfo(intial_data, current_data, column_name);
                        for (int i = 0; i < tamper_info.size(); i++)
                        {
                            ArrayList tamper_row = (ArrayList) tamper_info.get(i);
                            String id = (String) tamper_row.get(0);
                            ArrayList fieldname = (ArrayList) tamper_row.get(1);
                            ArrayList original_fieldname = (ArrayList) tamper_row.get(2);
                            
                            if (new DB_Restorer().isDBRestored(column_name.get(0), id, fieldname, original_fieldname, table_name,username,password)) 
                            {
                                String message1 = "Alert!!!! TAMPER HAS  DETECTED A TABLE : " + table_name + " OF CLIENT " + client_name + " AT  " + current_time+" MANAGER "+ username;
                                String message2 = " ON PRIMARY KEY " + column_name.get(0) + " : " + id + " AT FOLLOWING FIELD " + fieldname + " DATABASE RESTORE SUCCESFUL IN THE TABLE \n";
                                String finalmessage = message1 + message2 + "\n";
                                String temp = DB_Tamper_Detection_Frame.jTextArea1.getText();
                                temp = temp + "\n" + finalmessage + "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
                                DB_Tamper_Detection_Frame.jTextArea1.setText(temp);
                                
                                String emailmessage1="Dear "+client_name+"\n";
                                String emailmessage2=finalmessage+"\n\n";
                                String emailmessage3="Thanks and Regard\n Automatic Data Restoretion System "; 
                                String email_finalmessage=emailmessage1+emailmessage2+emailmessage3;
                                new SendEmail().isEmailSent(email_finalmessage, emailid);
                            }

                        }

                    }
                    else
                    {
                        System.out.println("Visited on table " + table_name + " of Client " + client_name + " at " + current_time + "  Data is safe");
                    }
                }
            }
        }
        catch (Exception e) 
        {   
            System.out.println("Exception At Class Tamper_Detection_Thread in function run(): "+e);
        }
    }
    
}

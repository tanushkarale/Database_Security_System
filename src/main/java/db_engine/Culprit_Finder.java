package db_engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Culprit_Finder 
{
    public ArrayList<String> getDBMCredential()
    {
        ArrayList credentail = new ArrayList();
        
        try
        {
            String logfilepath ="C:\\Users\\TANUSH\\AppData\\Roaming\\MySQL\\Workbench\\log\\wb.log";
            FileReader fr = new FileReader(logfilepath);
            BufferedReader br = new BufferedReader(fr);
            String requiredline=null;
            //while(!(br.readLine().contains("SQL IDE UI is ready")))
          while((requiredline=br.readLine())!=null)
            {
       
                if(requiredline.contains("Opened connection "))
                {
                    if(requiredline.contains("_"))
                    {
                    System.out.println(requiredline);
                    String temp[] = requiredline.split("Opened connection '");
                    String t1 = temp[1];
                    String t2[] = t1.split("_");
                    String username = t2[0];
                    //System.out.println("Username is: "+username);
                    String firstletter = username.substring(0, 1).toUpperCase();
                    String password = firstletter + username.substring(1, username.length()) + "@123";
                    //System.out.println("Password is: "+password);
                    credentail.add(username);
                    credentail.add(password);
                    
                    }
                    
                     else
                {
                    credentail.add("root");
                    credentail.add("Root");
                }
                 
                }
              
            }
           
           
            
        }
        catch(Exception e)
        {
            System.out.println("Exception at class Culprit_Finder in function getDBMCredential() "+e);
        }
        
        return credentail;
    }
}
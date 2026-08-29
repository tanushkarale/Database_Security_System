package db_engine;

import java.util.ArrayList;

public class TamperFieldIdentifier 
{
    public ArrayList getTamperFieldinfo(ArrayList original_data,ArrayList current_data,ArrayList<String> columnname)
    {
        ArrayList tamperlist = new ArrayList();
        
        for (int i = 0; i < original_data.size(); i++) 
        {
           ArrayList result = new ArrayList(); 
           ArrayList original_row = (ArrayList) original_data.get(i);
           ArrayList current_row = (ArrayList) current_data.get(i); 
           
           
           if(!original_row.equals(current_row))
           {
               System.out.println("Modified Data "+current_row);
               String id = (String)current_row.get(0);
               
               ArrayList fieldname = new ArrayList();
               ArrayList original_name = new ArrayList();
                for (int j = 1; j < original_row.size(); j++) 
                {
                    String original_value = (String)original_row.get(j);
                    String current_value = (String)current_row.get(j);
                        if(!original_value.equals(current_value))
                        {
                            fieldname.add(columnname.get(j)); 
                            original_name.add(original_value);
                        }
                }
                result.add(id);
                result.add(fieldname);
                result.add(original_name);
                tamperlist.add(result);
            }
            
        } 
        return tamperlist;
    }
    
}
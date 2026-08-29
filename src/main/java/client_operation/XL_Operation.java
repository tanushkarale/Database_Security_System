
package client_operation;


import db_ops.DB_Driver;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.Statement;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class XL_Operation {
    
    public  ArrayList<String> getColumn(String filepath){
        
        
         ArrayList<String> columnname = new ArrayList<String>();
         try {
             
             File file =new File(filepath);
             Workbook wobk =  Workbook.getWorkbook(file);
             Sheet sht = wobk.getSheet(0);
             int row = sht.getRows();
             int column =sht.getColumns();
             
             for (int i = 0; i < column; i++) 
             {
                 Cell c = sht.getCell(i, 0);
                 String content = c.getContents();
                 columnname.add(content);
                 
             }
             
             wobk.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Exception at Class XL_Operation in method getColumn():"+e);
            
        }
         
         
         return columnname;
    }
    
   
    public  boolean  isTableCreated(String tablename ,ArrayList columnname){
        boolean flag = false;
        
        try {
            
             String query = "";
             //create table if not exists emp (c1 varchar(45),c2 varchar(45),primary key(c1));
             String q1 ="create table if not exists "+tablename+" ( ";
             String q2 ="";
             for (int i = 0; i < columnname.size(); i++) 
             {
                String colum_name = (String) columnname.get(i);
                q2=q2+colum_name+" varchar(100),";
            }
             
             String q3=" primary key("+columnname.get(0)+"))";
             
             
            System.out.println("final query :"+query);
            
            query = q1+q2+q3;
            Statement st =  new DB_Driver().getDbStatement(); 
            st.executeUpdate(query);
            
             flag=true;
            
        }
        catch (Exception e) {
            System.out.println("exception at class XL_Operation in method  isTableCreated :"+e);
        }
        
        
        
        return flag;
    }
    
    public boolean isClientDataStored(String filepath,ArrayList<String> columnname,String tablename){
        boolean flag = false;
        
        try {
            
            String q1="insert into "+tablename+"(";
            String q2="";
            String q3=") values(";
            
            for (int i = 0; i < columnname.size(); i++) {
                String singel_column_name = columnname.get(i);
                q2=q2+singel_column_name+", ";
                q3=q3+"?, ";
                
            }
            q2=q2.substring(0, q2.length()-2);
            q3=q3.substring(0, q3.length()-2)+")";
            
            System.out.println("q1: "+q1);
            System.out.println("q2: "+q2);
            System.out.println("q3: "+q3);
            
            String final_query=q1+q2+q3;
            System.out.println("final query:"+final_query);
            
            DB_Driver db = new DB_Driver();
            db.getDbStatement();
            Connection con = db.con;
            PreparedStatement ps = con.prepareStatement(final_query);
            
             File file =new File(filepath);
             Workbook wobk =  Workbook.getWorkbook(file);
             Sheet sht = wobk.getSheet(0);
             int row = sht.getRows();
             int column =sht.getColumns();
             
             for (int i = 1; i < row; i++) 
             {
                 for (int j = 0; j < column; j++) 
                 {  
                    Cell c = sht.getCell(j, i);
                    String content = c.getContents();
                    ps.setString(j+1, content);
                    
                    
                 } 
                  ps.addBatch();
             }
            ps.executeBatch();
            wobk.close();
            con.close();
            
            flag=true;

            
            
        }
        catch (Exception e)
        {
            System.out.println("exception at class Xl_operation in function isClientDataStored:"+e);
        
        }
        
        
        return flag;
    }
    
    public  boolean isClientDataInfoStored(String username ,String tablename,String date)
    {
        boolean flag= false;
        
        try {
            
           Statement st =  new DB_Driver().getDbStatement();
           String query = "Insert into client_data_info values('"+username+"','"+tablename+"','"+date+"')";
           int nor = st.executeUpdate(query);
           if(nor>0)
               flag=true;
            
        } 
        catch (Exception e) 
        {
            System.out.println("exception at class Xl_Operation in function isClientDataInfoStored:"+e);
            
        }
        
        
        return flag;
    }
    
    
    public boolean isFileDownloaded(ArrayList download_data,String download_path){
        boolean flag = false;
        
        try 
        {
            
            File file = new  File(download_path);
            WritableWorkbook wwb = Workbook.createWorkbook(file);
            WritableSheet wsh= wwb.createSheet("Sheet 1", 0);
            WritableFont cell_font = new WritableFont(WritableFont.ARIAL,11);
            WritableCellFormat cell_format = new WritableCellFormat(cell_font);
            
            Label lbl=null;
               
            for (int i = 0; i <download_data.size() ; i++) {
                
                ArrayList single_row = (ArrayList) download_data.get(i);
                
                for (int j = 0; j < single_row.size(); j++) {
                    
                    String cell_content = (String) single_row.get(j);
                    lbl= new Label(j, i, cell_content, cell_format);
                    wsh.addCell(lbl);
                }
                
            }
            wwb.write();
            wwb.close();
            
            flag = true;
            
            
        }
        catch (Exception e)
        {
            System.out.println("Exception At Class XL_Operation In Function IsFileDownloaded:"+e);
        }
        
        return flag;
    }      
    
}

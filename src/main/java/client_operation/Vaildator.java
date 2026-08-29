
package client_operation;

import javax.swing.JOptionPane;


public class Vaildator {
    
    public String isEmailIdValidated(String email_id)
    {
        String result ="perfect";
        
        if(email_id.isEmpty()){
            result ="Please Enter Email Id";
        }
        else if(!email_id.contains(".") && !email_id.contains("@"))
        {
            result = ". And @ Is Missing In The Email Id";
        }
        else if(!email_id.contains("."))
        {
           result=". Is Missing In The Email Id"; 
        }
        else if(!email_id.contains("@")){
            result="@ Is Missing In The Email Id";
        }
        else
        {
            result= "perfect";
        }
        
        return result;
    }
    
    
    public String isMobileNoValidated(String mobile_no)
    {
        String result = "perfect";
        
        if(mobile_no.isEmpty()){
            result="Please Enter Mobile Number";
        }
        else if(mobile_no.length() !=10){
            if(mobile_no.length()>10)
                result ="Mobile Number Is Greater Then 10 Digit";
            else
                result ="Mobile Number Is Less Then 10 Digit";
        }
        else if(!isDigit(mobile_no)){
            result ="Mobile Number Contain Character";
        }
        else
        {
            result="perfect";
        }

        return result;
    }
    
    
    public  boolean isDigit(String mobile_no){
        boolean flag = false;
        
        
        try 
        {
          long value = Long.parseLong(mobile_no);
            flag = true; 
            
        } catch (Exception e) 
        {
            System.out.println(e);
        }
        return flag;
    }
    
}

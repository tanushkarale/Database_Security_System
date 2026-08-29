   
package db_security_tbm;

import client_operation.*;
import java.awt.Dimension;
import java.awt.Toolkit;


public class Db_security_tbm {

    public static void main(String[] args) {
        Client_Login_Frame clf = new Client_Login_Frame();
        clf.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        clf.setSize(dim);
        
      
        
    }
}

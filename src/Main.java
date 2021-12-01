import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame {
	public static void main(String[] args) {
        try {
        	
        	JFrame f= new JFrame("PBKDF2 Demo"); 
        	JLabel l1 = new JLabel("Please enter password : ");
        	l1.setBounds(30,0, 200,30);
        	
        	JPasswordField tpf=new JPasswordField(); 
        	tpf.setBounds(30,30, 200,30);  
        	JLabel l2 = new JLabel("Please enter SALT : ");
        	l2.setBounds(30,60, 200,30);
        	JTextField tf = new JTextField();
        	tf.setBounds(30,90, 200,30);  
        	
        	
        	    
        	JButton b = new JButton("Generate Hash");
        	b.setBounds(30,130, 200,30); 
        	    
        	JTextArea ta = new JTextArea();  
        	ta.setBounds(30,175, 200,100);  
        	f.add(l1);
        	f.add(tpf); 
        	f.add(l2);
        	f.add(tf);
        	f.add(b);
        	f.add(ta); 
        	f.setLayout(null);  
        	f.setBounds(450, 140, 280, 350);
        	f.setVisible(true);  

            b.addActionListener(new ActionListener ( ){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
	                char[] passwordText = tpf.getPassword();
	               // SecureRandom random = new SecureRandom();
	                String saltString =tf.getText();
	                byte[] salt = saltString.getBytes();
	                //random.nextBytes(salt);
	                KeySpec keySpec = new PBEKeySpec(passwordText, salt, 65536, 128);
	                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	                byte[] hash = factory.generateSecret(keySpec).getEncoded();
	                String hashedString = Hex.encodeHexString(hash);
	               
				    ta.setText(hashedString);    
					} catch (Exception ex) {
						ex.printStackTrace();
					}
		           
				}
			});
     
        } catch (Exception e) {
            e.printStackTrace();
            
        }   
        
	}
	
	
}


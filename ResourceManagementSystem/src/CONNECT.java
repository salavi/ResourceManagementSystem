import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
 
public class CONNECT implements ActionListener {
 
    Connection con;
    Statement st;
    ResultSet rs;
 
    JFrame f;
    JLabel user;
    JLabel pass;
    JButton b;
    JButton b1;
    JTextField t;
    JTextField t1;
    int count;
 
   public CONNECT(){
       connect();
       frame();
   }
    public void connect(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1","root","");
        st= con.createStatement();
 
 
    }catch(Exception e){
 
    }
    }
    public void frame(){
 
            f=new JFrame("User Login");
            f.setSize(600,400);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
            user = new JLabel("UserName");
            pass = new JLabel("Password");
            b = new JButton("Login");
            b1=new JButton("Cancel");
            t=new JTextField(10);
            t1=new JTextField(10);
            JPanel p=new JPanel();
            p.add(user);
            p.add(t);
            p.add(pass);
            p.add(t1);
            b.addActionListener(this);
            p.add(b);
            b1.addActionListener(this);
            p.add(b1);
 
            f.add(p);
 
    }
               public void actionPerformed(ActionEvent e)
               {
                   if(e.getSource()==b){
                       call();
 
 
             }   
                   if(e.getSource()==b1){
                       JOptionPane.showMessageDialog(null,"Thank You!");
                   }
 
            }
 
            public void call(){
                try{
                   String use=t.getText();
                   String pas=t1.getText();
                   String query="select * from persons1";// where user ='"+user+"'and pass ='"+pass+"'";
                   rs=st.executeQuery(query);
                   count =0;
                   while(rs.next()){
                       String uname=rs.getString("user");
                       String ps = rs.getString("pass");
 
                       if(uname.equalsIgnoreCase(use)&&ps.equalsIgnoreCase(pas))
                       {
                          count ++;
                       }
                   }
 
 
 
                   }catch(Exception ex){
 
               }
                if (count ==1){
                    JOptionPane.showMessageDialog(null,"User Found And Access Granted");
                }
                else if(count >1){
                    JOptionPane.showMessageDialog(null,"Double Accounts Not Allowed");
                }
                else
                    JOptionPane.showMessageDialog(null, "User Not Found.");
            }
       public static void main(String[] args) {
    	   try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        new CONNECT();
       }
 
 
}

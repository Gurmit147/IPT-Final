
package practicaltestf1022;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;


public class PracticalTestF1022 {
    JFrame f;
    JLabel title,lbname,lbid,lbmail,lbprogramlevel,lbprogramname,lbacademicsession,lboutput;
    JButton submit,view,reset;
    JTextField txtname,txtid,txtmail;
    JComboBox programlevel,programname,academicsession;
    JTextArea txoutput;
    String program[] = {"Please choose" ,"Degree ","Diploma"};
    String diploma[] = {"Diploma in Business Administration", "Diploma in Information Technology", "Diploma in Electrical Engineering", "Diploma in Mechanical Engineering","Diploma in Manufacturing Technology"};
    String degree[] = {"Degree in Manufacturing Technology" , "Degree in Business Administration"};
    String session[] = {"Session 1 2023", "Session 2 2023"};
    String choose[] = {"Please choose program"};
    PracticalTestF1022(){
        
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbfirareg?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "";
        
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            
            String sql = "CREATE DATABASE IF NOT EXISTS enrollment";
            stm.execute(sql);
            
            String sqluse = "USE enrollment";
            stm.executeUpdate(sqluse);
            
            String sql2 = "CREATE TABLE IF NOT EXISTS enrollment_data (name VARCHAR(100),student_id VARCHAR(20) PRIMARY KEY,email VARCHAR(100),program_level VARCHAR(20),program_name VARCHAR(50),session VARCHAR(20))";
            stm.execute(sql2);
        
        }catch(Exception w){
            JOptionPane.showMessageDialog(null, "Failed to create database", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        
        f = new JFrame();
        f.setSize(600,700);
        f.setVisible(true);
        f.setLayout(null);
        f.setTitle("STUDENT ENROLLMENT SYSTEM SUNWAY UNIVERSITY");
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        title = new JLabel("ENROLLMENT DATA");
        title.setBounds(130,20,400,40);
        title.setFont(new Font("ARIAL",Font.BOLD,30));
        title.setForeground(Color.BLUE);
        f.add(title);
        
        lbname = new JLabel("Name");
        lbname.setBounds(70, 70, 70, 30);
        lbname.setFont(new Font("ARIAL",Font.BOLD,16));
        f.add(lbname);
        
        txtname = new JTextField();
        txtname.setBounds(220,73,250,20);
        f.add(txtname);
        
        lbid = new JLabel("Student ID");
        lbid.setBounds(70, 110, 100, 30);
        lbid.setFont(new Font("ARIAL",Font.BOLD,16));
        f.add(lbid);
        
        txtid = new JTextField();
        txtid.setBounds(220,113,250,20);
        f.add(txtid);
        
        lbmail = new JLabel("Email");
        lbmail.setBounds(70, 150, 100, 30);
        lbmail.setFont(new Font("ARIAL",Font.BOLD,16));
        f.add(lbmail);
        
        txtmail = new JTextField();
        txtmail.setBounds(220,153,250,20);
        f.add(txtmail);
        
        lbprogramlevel = new JLabel("Program Level");
        lbprogramlevel.setBounds(70, 190, 150, 30);
        lbprogramlevel.setFont(new Font("ARIAL",Font.BOLD,16));
        f.add(lbprogramlevel);
        
        programlevel = new JComboBox(program);
        programlevel.setBounds(220,193,250,20);
        f.add(programlevel);
        
        lbprogramname = new JLabel("Program Name");
        lbprogramname.setBounds(70, 230, 150, 30);
        lbprogramname.setFont(new Font("ARIAL",Font.BOLD,16));
        f.add(lbprogramname);
        
        programname = new JComboBox();
        programname.setBounds(220,233,250,20);
        f.add(programname);
        
        lbacademicsession = new JLabel("Academic Session");
        lbacademicsession.setBounds(70, 270, 150, 30);
        lbacademicsession.setFont(new Font("ARIAL",Font.BOLD,16));
        f.add(lbacademicsession);
        
        academicsession = new JComboBox(session);
        academicsession.setBounds(220,273,250,20);
        f.add(academicsession);
        
        submit = new JButton("Submit");
        submit.setBounds(70,310,90,30);
        f.add(submit);
        
        view = new JButton("View");
        view.setBounds(200,310,90,30);
        f.add(view);
        
        reset = new JButton("Reset");
        reset.setBounds(330,310,90,30);
        f.add(reset);
        
        lboutput = new JLabel("Output");
        lboutput.setBounds(70, 350, 150, 30);
        lboutput.setFont(new Font("ARIAL",Font.BOLD,16));
        f.add(lboutput);
        
        txoutput = new JTextArea();
        txoutput.setBounds(70,390,400,250);
        JScrollPane sp = new JScrollPane(txoutput);
        sp.setBounds(70,390,400,250);
        f.add(sp);
        
        programlevel.addItemListener(e->{
            int selectedprogram =programlevel.getSelectedIndex();
            if(selectedprogram == 2){
                programname.removeAllItems();
                for(String item : diploma){
                    programname.addItem(item);
                }
                
            }else if(selectedprogram == 1){
                programname.removeAllItems();
                for(String item2 : degree){
                    programname.addItem(item2);
                }
                
            }else if(selectedprogram == 0){
                programname.removeAllItems();
                for(String item3 : choose){
                    programname.addItem(item3);
                }
                
            }
        });
        txtname.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isSpaceChar(c)&&!Character.isAlphabetic(c) ){
                    e.consume();
                }
            }
        });
        
        txtmail.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(Character.isWhitespace(c)){
                    e.consume();
                }
            }
        });
        
        submit.addActionListener(e->{
            String name = txtname.getText();
            String studentid = txtid.getText();
            String studentmail = txtmail.getText();
            String studentprogramlevel = (String) programlevel.getSelectedItem();
            String studentprogram = (String) programname.getSelectedItem();
            String studentacademicsession = (String) academicsession.getSelectedItem();
            
            if (!studentmail.contains("@")) {
                JOptionPane.showMessageDialog(null, "Invalid email format", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            
            try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            
            String sqluse = "USE enrollment";
            stm.executeUpdate(sqluse);
            
            
            String sqlinsert = String.format("INSERT INTO enrollment_data (name,student_id,email,program_level,program_name,session) VALUES ('%s','%s','%s','%s','%s','%s')", name,studentid,studentmail,studentprogramlevel,studentprogram,studentacademicsession);
            stm.executeUpdate(sqlinsert);
            JOptionPane.showMessageDialog(null, "Data has been saved successfully", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        
        }catch(Exception w){
            JOptionPane.showMessageDialog(null, "EEROR" + w.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
        }
        });
        
        reset.addActionListener(e->{
            txtname.setText("");
            txtid.setText("");
            txtmail.setText("");
            programlevel.setSelectedIndex(0);
            programname.removeAllItems();
            for(String item2 : choose){
                programname.addItem(item2);
            }
            txoutput.setText("");
        });
        
        view.addActionListener(e->{
            try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            
            String sqluse = "USE enrollment";
            stm.executeUpdate(sqluse);
            
            
            String sqlview = "SELECT * FROM enrollment_data";
            
            try(ResultSet rs = stm.executeQuery(sqlview)){
                while(rs.next()){
                String name = rs.getString("name");
                String studentid = rs.getString("student_id");
                String studentmail = rs.getString("email");
                String studentprogramlevel = rs.getString("program_level");
                String studentprogram = rs.getString("program_name");
                String studentacademicsession = rs.getString("session");
                
                txoutput.append("NAME:" + name + "\n");
                txoutput.append("STUDENT ID:" + studentid + "\n");
                txoutput.append("EMAIL:" + studentmail + "\n");
                txoutput.append("PROGRAM LEVEL:" + studentprogramlevel + "\n");
                txoutput.append("PROGRAM NAME:" + studentprogram + "\n");
                txoutput.append("SESSION:" + studentacademicsession + "\n");
                txoutput.append("\n");
                }
            
            }catch(Exception q){
              JOptionPane.showMessageDialog(null, "Failed to get data into database", "ERROR", JOptionPane.WARNING_MESSAGE);

            }
            
        
        }catch(Exception w){
            JOptionPane.showMessageDialog(null, "Failed to insert data into database", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        });
    
    }
    

   
    public static void main(String[] args) {
        PracticalTestF1022 g = new PracticalTestF1022();
    }
    
}

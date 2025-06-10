package com.mycompany.facility_reservation;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;

public class CommonMethods implements CoreMethods{  
    public static void setBackgroundImage(JPanel panel, String imagePath) {
    ImageIcon icon = new ImageIcon(imagePath);
    Image image = icon.getImage();

    panel.setLayout(null); // Optional: only if you're using absolute positioning

    // Create a component that paints the image
    JComponent background = new JComponent() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), this);
        }
    };
    background.setBounds(0, 0, panel.getWidth(), panel.getHeight());
    panel.add(background);
    panel.setComponentZOrder(background, panel.getComponentCount() - 1); // send to back
    }
    
    @Override
     public void scaleImageLogo(String loc, JLabel label){
        ImageIcon logoIcon = new ImageIcon(loc);
        //scaling image to fit in label
        Image img1 = logoIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(img1);
        label.setIcon(scaledIcon1);
    }
    
    @Override
    public void addHoverOutline(JLabel label) {
        Border originalBorder = label.getBorder();

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBorder(BorderFactory.createLineBorder(new Color(65, 105, 225), 3)); // Royal Blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBorder(originalBorder); // Restore original border
            }
        });
    }
      
    public void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/programlogs","root","root");

            System.out.println("Database connection successful");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentLogIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentLogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI4 {
   private static void run(){
       JFrame frame = new JFrame();
       frame.setVisible(true);
       frame.setSize(600,600);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocation(200,200);

       frame.setLayout(new BorderLayout());
       JButton bLeft = new JButton("Up");
       JButton bRight = new JButton("Down");
       frame.add(bLeft,BorderLayout.WEST);
       frame.add(bRight,BorderLayout.EAST);
       bLeft.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int width = frame.getSize().width;
               width +=100;
               frame.setSize(width,frame.getSize().height);
           }
       });

       bRight.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int width = frame.getSize().width;
               width -=100;
               frame.setSize(width,frame.getSize().height);
           }
       });

   }

    public static void main(String[] args) {
        UI4.run();
    }
}

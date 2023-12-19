import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIex {
    public static void main(String[] args) {
        JFrame pcScreen = new JFrame("Run ex");
        pcScreen.setSize(500,200);

        Label inputa = new Label("Nhap a:");
        Label inputb = new Label("Nhap b:");

        JButton btn1 = new JButton("+");
        Button btn2 = new Button("-");
        Button btn3 = new Button("*");
        Button btn4 = new Button("/");
        Button btn5 = new Button("%");

        JPanel container1 = new JPanel(new GridLayout(1,3));
        container1.add(btn1);
        container1.add(btn2);
        container1.add(btn3);

        JPanel container2 = new JPanel(new GridLayout(1,2));
        container2.add(btn4);
        container2.add(btn5);

        JPanel container3 = new JPanel(new GridLayout(1,2));
        container3.add(new JLabel("Ket qua: "));
        JTextField textIn = new JTextField();
        textIn.setEditable(false);
        container3.add(textIn);

        TextField input1 = new TextField();
        TextField input2 = new TextField();
        pcScreen.setLayout(new GridLayout(4,2));

        pcScreen.add(inputa);
        pcScreen.add(input1);

        pcScreen.add(inputb);
        pcScreen.add(input2);
        pcScreen.add(new JLabel("Ket qua: "));
        pcScreen.add(textIn);
        pcScreen.add(container1);
        pcScreen.add(container2);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value1 = input1.getText();
                String value2 = input2.getText();
               try{
                   float num1 = Float.parseFloat(value1);
                   float num2 = Float.parseFloat(value2);
                   textIn.setText(Float.toString(num1+num2));
               }catch (NumberFormatException ex){
                   textIn.setText("Ket qua loi!");
               }
            }
        });
        pcScreen.setLocation(200,200);
        pcScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pcScreen.setVisible(true);
    }

}

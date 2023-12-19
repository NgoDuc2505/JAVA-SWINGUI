package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TH1 {

    private static String[] contentList = {"1","2","3","+","4","5","6","-","7","8","9","*","0",".","=","/"};
    private static List<JButton> btnList = new ArrayList<>();

    private static List<String> inputArr = new ArrayList<>();

    private static void convertedDot(JTextField resultOutput){
        for (int i = 0; i < inputArr.size(); i++) {
            if(inputArr.get(i).equalsIgnoreCase(".")){
                String combine = "";
                String prev = inputArr.get(i-1);
                String next = inputArr.get(i+1);
                String current = inputArr.get(i);
                combine = prev+current+next+"";
                System.out.println(combine);
                inputArr.set(i-1,""+combine);
                inputArr.remove(i);
                inputArr.remove(i);
                resultOutput.setText(inputArr.toString());
                System.out.println(inputArr.toString());
            }
        }
    }
    public static void run() {
        for (int i = 0; i < contentList.length; i++) {
            btnList.add(new JButton(contentList[i]));
        }
        JFrame frmPc = new JFrame("Calculator");
        frmPc.setSize(300,300);

        JPanel container = new JPanel(new GridLayout(4,4));
        for (int i = 0; i < contentList.length; i++) {
            container.add(btnList.get(i));
        }

        JTextField resultOutput = new JTextField(" ");
        frmPc.setLayout(new BorderLayout());
        frmPc.add(resultOutput,BorderLayout.NORTH);
        frmPc.add(container,BorderLayout.CENTER);

        frmPc.setLocation(200,200);
        frmPc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPc.setVisible(true);

        for (int i = 0; i < contentList.length; i++) {
            int index = i;
            btnList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!contentList[index].equalsIgnoreCase("=")){
                        if (inputArr.size() == 0){
                            inputArr.add(contentList[index]);
                        }else {
                        if (contentList[index] != "+" && (inputArr.get(inputArr.size()-1)) != "+"){
                            String combine =""+inputArr.get(inputArr.size()-1);
                            combine += contentList[index];
                            inputArr.remove(inputArr.size()-1);
                            inputArr.add(combine);
                        }else {
                            inputArr.add(contentList[index]);
                        }
                        }
                        resultOutput.setText(inputArr.toString());
                    }
                }
            });
        }

        btnList.get(14).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float result = 0;
                while (inputArr.size() != 1){
                    convertedDot(resultOutput);
                    // multiple and devide first
                    for (int i = 0; i < inputArr.size(); i++) {
                        switch (inputArr.get(i)){
                            case "*":
                            {
                                float prevNum = Float.parseFloat(inputArr.get(i-1));
                                float nextNum = Float.parseFloat(inputArr.get(i+1));
                                result = prevNum * nextNum;
                                inputArr.set(i-1,""+result);
                                inputArr.remove(i);
                                inputArr.remove(i);
                            }
                            break;
                            case "/":
                            {
                                float prevNum = Float.parseFloat(inputArr.get(i-1));
                                float nextNum = Float.parseFloat(inputArr.get(i+1));
                                result = prevNum / nextNum;
                                inputArr.set(i-1,""+result);
                                inputArr.remove(i);
                                inputArr.remove(i);
                            }
                            break;
                        }
                    }
                    // plus and minus later
                    for (int i = 0; i < inputArr.size(); i++) {
                        switch (inputArr.get(i)){
                            case "+":
                            {
                                float prevNum = Float.parseFloat(inputArr.get(i-1));
                                float nextNum = Float.parseFloat(inputArr.get(i+1));
                                result = prevNum + nextNum;
                                inputArr.set(i-1,""+result);
                                inputArr.remove(i);
                                inputArr.remove(i);
                            }
                            break;
                            case "-":
                            {
                                float prevNum = Float.parseFloat(inputArr.get(i-1));
                                float nextNum = Float.parseFloat(inputArr.get(i+1));
                                result = prevNum - nextNum;
                                inputArr.set(i-1,""+result);
                                inputArr.remove(i);
                                inputArr.remove(i);
                            }
                            break;

                        }
                    }
                }
                resultOutput.setText(inputArr.toString());
            }
        });
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        TH1.run();
    }
}

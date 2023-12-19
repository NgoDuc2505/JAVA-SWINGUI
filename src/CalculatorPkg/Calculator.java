package CalculatorPkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    private static List<String> inputString = new ArrayList<>();

    private static void showUp(JLabel screen){
        String content = "";
        for (int i = 0; i < inputString.size(); i++) {
            content = content + inputString.get(i);
        }
        screen.setText(content);
    }

    private static void pressBtn(String str,JLabel screen){
        inputString.add(str);
        showUp(screen);
    }
    public static void run() {
        JFrame scrPc = new JFrame("Calculator");
        scrPc.setSize(700,300);

        JButton n1 = new JButton("1");
        JButton n2 = new JButton("2");
        JButton n3 = new JButton("3");
        JButton n4 = new JButton("4");
        JButton n5 = new JButton("5");
        JButton n6 = new JButton("6");
        JButton n7 = new JButton("7");
        JButton n8 = new JButton("8");
        JButton n9 = new JButton("9");
        JButton n0 = new JButton("0");

        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton multi = new JButton("x");
        JButton dev = new JButton("/");
        JButton del = new JButton("DEL");
        JButton devN = new JButton("%");
        JButton dotFloat = new JButton(".");
        JButton equal = new JButton("=");

        JButton clr = new JButton("AC");

        JLabel screen = new JLabel(".0");

        JPanel container1 = new JPanel(new GridLayout(1,2));
        container1.add(del);
        container1.add(dev);

        JPanel conta0 = new JPanel(new GridLayout(1,2));
        conta0.add(new JLabel(" "));
        conta0.add(screen);

        JPanel conta2 = new JPanel(new GridLayout(1,2));
        conta2.add(n7);
        conta2.add(n8);
        JPanel conta3 = new JPanel(new GridLayout(1,2));
        conta3.add(n9);
        conta3.add(multi);
        JPanel conta4 = new JPanel(new GridLayout(1,2));
        conta4.add(n4);
        conta4.add(n5);
        JPanel conta5 = new JPanel(new GridLayout(1,2));
        conta5.add(n6);
        conta5.add(minus);
        JPanel conta6 = new JPanel(new GridLayout(1,2));
        conta6.add(n1);
        conta6.add(n2);
        JPanel conta7 = new JPanel(new GridLayout(1,2));
        conta7.add(n3);
        conta7.add(plus);
        JPanel conta8 = new JPanel(new GridLayout(1,2));
        conta8.add(devN);
        conta8.add(n0);
        JPanel conta9 = new JPanel(new GridLayout(1,2));
        conta9.add(dotFloat);
        conta9.add(equal);



        scrPc.setLayout(new GridLayout(6,2));
        scrPc.add(new JLabel(" "));
        scrPc.add(screen);
        scrPc.add(clr);
        scrPc.add(container1);
        scrPc.add(conta2);
        scrPc.add(conta3);
        scrPc.add(conta4);
        scrPc.add(conta5);
        scrPc.add(conta6);
        scrPc.add(conta7);
        scrPc.add(conta8);
        scrPc.add(conta9);




        scrPc.setLocation(200,200);
        scrPc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scrPc.setVisible(true);

        //1
        n1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("1",screen);
            }
        });

        //2
        n2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("2",screen);
            }
        });

        //3
        n3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("3",screen);
            }
        });

        //4
        n4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("4",screen);
            }
        });

        //5
        n5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("5",screen);
            }
        });

        //6
        n6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("6",screen);
            }
        });

        //7
        n7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("7",screen);
            }
        });

        //8
        n8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("8",screen);
            }
        });

        //9
        n9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("9",screen);
            }
        });
        //0
        n0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn("0",screen);
            }
        });

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn(" + ",screen);
            }
        });

        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn(" - ",screen);
            }
        });

        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn(" x ",screen);
            }
        });

        dev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn(" / ",screen);
            }
        });

        devN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn(" % ",screen);
            }
        });

        dotFloat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressBtn(".",screen);
            }
        });

        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lstIndex = inputString.size() - 1;
                inputString.remove(lstIndex);
                showUp(screen);
            }
        });

        clr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputString.clear();
                pressBtn(".0",screen);
            }
        });

        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullContent = "";
                for (int i = 0; i < inputString.size(); i++) {
                    fullContent = fullContent + inputString.get(i);
                }
//                System.out.println(Arrays.toString(fullContent.split(" ")));
                String mathFormat[] = fullContent.split(" ");
                float result = 0;
                int lengthGet = mathFormat.length;
                int count = 0;
                for (int i = 0; i < mathFormat.length; i++) {
                        if(mathFormat[i].equalsIgnoreCase("+")){
                            result += Float.parseFloat(mathFormat[i-1]);
                        }else if(mathFormat[i].equalsIgnoreCase("-")){
                            result -= Float.parseFloat(mathFormat[i-1]);
                        }else if(mathFormat[i].equalsIgnoreCase("x")){
                            result *= Float.parseFloat(mathFormat[i-1]);
                        }else if(mathFormat[i].equalsIgnoreCase("/")){
                            result /= Float.parseFloat(mathFormat[i-1]);
                        }
                }
                switch (mathFormat[lengthGet-2]){
                    case "+":
                        result += Float.parseFloat(mathFormat[lengthGet-1]);
                        break;
                    case "x":
                        result *= Float.parseFloat(mathFormat[lengthGet-1]);
                        break;
                    case "-":
                        result -= Float.parseFloat(mathFormat[lengthGet-1]);
                        break;
                    case "/":
                        result /= Float.parseFloat(mathFormat[lengthGet-1]);
                        break;
                }
                screen.setText(Float.toString(result));
            }
        });

    }



    public static void main(String[] args) {
        Calculator.run();
    }
}

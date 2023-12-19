package UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cal2 {

    private static String[] contentList = {"1","2","3","4","5","6","7","8","9","0","start","stop"};
    private static List<JButton> listBtn = new ArrayList<>();

    public static void run() {
        for (int i = 0; i < contentList.length; i++) {
            listBtn.add(new JButton(contentList[i]));
        };
        JFrame frame = new JFrame("Lo vi song");
        frame.setSize(400,300);
        frame.setLayout(new GridLayout(1,2));
        JLabel area = new JLabel("Food to be place here");

        JPanel textCenter = new JPanel(new GridBagLayout());
        textCenter.add(area);
        textCenter.setBorder(new LineBorder(Color.black));

        JPanel containerMain = new JPanel(new BorderLayout());
        JPanel containerSubs = new JPanel(new GridLayout(4,3));
        for (int i = 0; i < contentList.length; i++) {
            containerSubs.add(listBtn.get(i));
        };
        containerMain.add(new JTextField("text "),BorderLayout.NORTH);
        containerMain.add(containerSubs,BorderLayout.CENTER);

        frame.add(textCenter);
        frame.add(containerMain);

        frame.setLocation(200,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Cal2.run();
    }
}

package QLSV_DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UXUI {
    private static String[] btnContent = {"Add","Edit","Delete","Search","Clear","Cancel"};

    private static List<JButton> btnList = new ArrayList<>();

    private static String[] labelContent = {"StudentID","Name","Department"};

    private static  String[] major = { "Khoa hoc may tinh", "Ky thuat may tinh", "Bao mat thong tin",
            "Vi mach", "He thong IOT" };

    private static void renderBtn(){
        for (int i = 0; i < btnContent.length; i++) {
            btnList.add(new JButton(btnContent[i]));
        }
    }

    private static void renderData(List<Object[]> listStudent,DefaultTableModel model){
        for (int i = 0; i < listStudent.size(); i++) {
            model.addRow(listStudent.get(i));
        }
        model.fireTableDataChanged();
    }

    private static void connectionDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentUi","root","2505");
            System.out.println("Connected !");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery("SELECT * FROM Sinhvien");
            getData(rs, statement);
            connection.close();
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Error !");
            System.out.println(e);
        }
    }

    private static void getData(ResultSet rs,Statement statement) throws SQLException {
        rs = statement.executeQuery("SELECT * FROM Sinhvien");
        while (rs.next()){
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            Student currentSt = new Student(rs.getString(1),rs.getString(2),rs.getString(3));
            MStudent.addNew(currentSt);
        }
    }


    public static void run(){
        renderBtn();
        connectionDB();
        JFrame frm = new JFrame("bt");
        frm.setSize(500,220);
        frm.setLocation(200,200);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(new BorderLayout());
        JLabel title = new JLabel("STUDENT MANAGEMENT");
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Arial",Font.BOLD,26));
        JPanel titleCenter = new JPanel(new GridBagLayout());
        titleCenter.add(title);
        JPanel leftContainer = new JPanel(new BorderLayout());
        JPanel labelContainer = new JPanel(new GridLayout(3,1));
        JPanel TextFContainer = new JPanel(new GridLayout(3,1));
        JLabel lbId = new JLabel(labelContent[0]);
        JLabel lbName = new JLabel(labelContent[1]);
        JLabel lbDep = new JLabel(labelContent[2]);
        labelContainer.add(lbId);
        labelContainer.add(lbName);
        labelContainer.add(lbDep);
        JTextField tfId = new JTextField();
        JTextField tfName = new JTextField();
        JComboBox selection = new JComboBox(major);
        TextFContainer.add(tfId);
        TextFContainer.add(tfName);
        TextFContainer.add(selection);
        JPanel bottomRight = new JPanel(new BorderLayout());
        bottomRight.add(TextFContainer,BorderLayout.CENTER);
        labelContainer.setPreferredSize(new Dimension(85,200));
        bottomRight.add(labelContainer,BorderLayout.WEST);
        JPanel underRight = new JPanel(new BorderLayout());
        JPanel addSearch = new JPanel(new GridLayout(2,1));
        JPanel restBtn = new JPanel(new GridLayout(2,2));
        addSearch.add(btnList.get(0));
        addSearch.setPreferredSize(new Dimension(85,20));
        System.out.println(btnList.get(0).getSize());
        addSearch.add(btnList.get(3));
        restBtn.add(btnList.get(1));
        restBtn.add(btnList.get(2));
        restBtn.add(btnList.get(4));
        restBtn.add(btnList.get(5));
        underRight.add(addSearch,BorderLayout.WEST);
        underRight.add(restBtn,BorderLayout.CENTER);
        leftContainer.add(underRight,BorderLayout.SOUTH);
        leftContainer.add(bottomRight,BorderLayout.CENTER);
        //for table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("StuID");
        model.addColumn("Name");
        model.addColumn("Department");
        JTable table = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JScrollPane tableScroll = new JScrollPane(table);
        JPanel mainContent = new JPanel(new GridLayout(1,2));
        mainContent.add(leftContainer);
        mainContent.add(tableScroll);
        frm.add(mainContent,BorderLayout.CENTER);
        frm.add(titleCenter,BorderLayout.NORTH);
        List<Object[]> listData = MStudent.getData();
        renderData(listData,model);
        frm.setVisible(true);
    }

    public static void main(String[] args) {
        UXUI.run();
    }

}

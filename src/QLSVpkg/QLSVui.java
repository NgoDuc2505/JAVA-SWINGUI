package QLSVpkg;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class QLSVui {

    private static String[] btnContent = {"Add","Delete","Edit","Search","Clear","Cancel"};
    private static List<JButton> listBtn = new ArrayList<>();

    private static int currentIndex = -1;

    private static boolean isActiveSearch = false;


    private static void renderBtn(){
        for (int i = 0; i < btnContent.length; i++) {
            listBtn.add(new JButton(btnContent[i]));
        }
    }

    private static void clearTbl(DefaultTableModel model){
        int rows = model.getRowCount();
        for(int i = rows - 1; i >=0; i--)
        {
            model.removeRow(0);
        }
    }

    private static void printList(){
        List<Object[]> list = StudentManage.getData();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("{ "+list.get(i)[0]+" : "+list.get(i)[1]+" : "+list.get(i)[2]+" }");
        }
    }

    private static void printList(List<Object[]> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println("{ "+list.get(i)[0]+" : "+list.get(i)[1]+" : "+list.get(i)[2]+" }");
        }
    }

    private static void renderData(List<Object[]> listStudent,DefaultTableModel model){
        for (int i = 0; i < listStudent.size(); i++) {
            model.addRow(listStudent.get(i));
        }
    }

    private static void setAllEmpty(JTextField idInput,JTextField nameInput,JTextField adressInput){
        idInput.setText("");
        nameInput.setText("");
        adressInput.setText("");
        idInput.grabFocus();
    }

    private static void clearAllData(DefaultTableModel model){
        model.setRowCount(0);
    }
    public static void run() {
        renderBtn();
        JFrame frame = new JFrame("QLSV");
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200,200);

        frame.setLayout(new BorderLayout());

        JPanel controlAndInput = new JPanel(new GridLayout(6,2));
        JLabel studentIdlb = new JLabel("StuId:");
        JLabel namelb = new JLabel("Name:");
        JLabel adresslb = new JLabel("Adress:");
        JTextField idInput = new JTextField();
        JTextField nameInput = new JTextField();
        JTextField adressInput = new JTextField();

        JLabel title = new JLabel("STUDENT MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.BLUE);
        JPanel titleContain = new JPanel(new GridBagLayout());
        titleContain.add(title);

        controlAndInput.add(studentIdlb);
        controlAndInput.add(idInput);
        controlAndInput.add(namelb);
        controlAndInput.add(nameInput);
        controlAndInput.add(adresslb);
        controlAndInput.add(adressInput);
        for (int i = 0; i < btnContent.length; i++) {
            controlAndInput.add(listBtn.get(i));
        }


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("StuId");
        model.addColumn("Name");
        model.addColumn("Address");
        JTable table = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());


        JScrollPane scrollTable = new JScrollPane(table);
        
        JPanel mainContent = new JPanel(new GridLayout(1,2));
        mainContent.add(controlAndInput);
        mainContent.add(scrollTable);
        frame.add(titleContain,BorderLayout.NORTH);
        frame.add(mainContent,BorderLayout.CENTER);

        JButton doneBtn = new JButton("Done");

        JLabel labelForSearch = new JLabel("Input ID: ");
        JTextField searchBar = new JTextField();
        JPanel containSearch = new JPanel(new GridLayout(1,2));
        containSearch.add(labelForSearch);
        containSearch.add(searchBar);

        doneBtn.setBackground(Color.pink);

        listBtn.get(1).setBackground(Color.PINK);

        frame.setVisible(true);

        //====ADD NEW====
        listBtn.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student newStu = new Student(idInput.getText(), nameInput.getText(), adressInput.getText());
                clearTbl(model);
                List<Object[]> listStudent = StudentManage.addNew(newStu);
                renderData(listStudent, model);
                model.fireTableDataChanged();
                setAllEmpty(idInput,nameInput,adressInput);
            }
        });

        //====DELETE====
        listBtn.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentId = idInput.getText();
                List<Object[]> listStudent = StudentManage.getData();
                for (int i = 0; i < listStudent.size(); i++) {
                    if(currentId.equalsIgnoreCase((String) listStudent.get(i)[0])){
                        model.removeRow(i);
                        StudentManage.deleteStu(i);
                    }
                }
                model.fireTableDataChanged();
                setAllEmpty(idInput,nameInput,adressInput);
            }
        });


        //====FINISH EDIT====
        doneBtn.addActionListener((ActionEvent e)->{
            String currentGetterId = idInput.getText();
            String newName = nameInput.getText();
            String newAdress = adressInput.getText();
            Student updatedStu = new Student(currentGetterId,newName,newAdress);
            List<Object[]> listStudent = StudentManage.updateData(currentIndex,updatedStu);
            for (int i = 0; i < listStudent.size(); i++) {
                model.removeRow(0);
            }
            renderData(listStudent, model);
            model.fireTableDataChanged();
            setAllEmpty(idInput,nameInput,adressInput);
            frame.remove(doneBtn);
            frame.revalidate();
            frame.repaint();
            printList();
        });


        //====EDIT MODE====
        listBtn.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentId = idInput.getText();
                List<Object[]> listStudent = StudentManage.getData();
                Object[] current;
                for (int i = 0; i < listStudent.size(); i++) {
                    if(currentId.equalsIgnoreCase((String) listStudent.get(i)[0])){
                        printList();
                        current = listStudent.get(i);
                        currentIndex = i;
                        idInput.setText((String) current[0]);
                        nameInput.setText((String) current[1]);
                        adressInput.setText((String) current[2]);

                        frame.add(doneBtn,BorderLayout.SOUTH);
                        frame.revalidate();
                        frame.repaint();
                    }
                }
            }
        });


        //====DETECTING CHANGE INPUT SEARCH====
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchBar.getText();
                if (text.trim().length() == 0) {
                    clearAllData(model);
                    List<Object[]> listCurrent = StudentManage.searchId(text);
                    renderData(listCurrent,model);
                } else {
                    clearAllData(model);
                    List<Object[]> listCurrent = StudentManage.searchId(text);
                    renderData(listCurrent,model);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchBar.getText();
                if (text.trim().length() == 0) {
                    clearAllData(model);
                    List<Object[]> listCurrent = StudentManage.searchId(text);
                    renderData(listCurrent,model);
                } else {
                    clearAllData(model);
                    List<Object[]> listCurrent = StudentManage.searchId(text);
                    renderData(listCurrent,model);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        //====SWITCH SEARCH MODE====
        listBtn.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isActiveSearch = !isActiveSearch;
                if(isActiveSearch){
                    listBtn.get(3).setBackground(Color.green);
                    title.setText("STUDENT MANAGEMENT [search mode]");
                    frame.add(containSearch,BorderLayout.SOUTH);
                    frame.revalidate();
                    frame.repaint();
                }else {
                    Color defaultColor = UIManager.getColor("Panel.background");
                    listBtn.get(3).setBackground(defaultColor);
                    title.setText("STUDENT MANAGEMENT");
                    List<Object[]> listOfDef = StudentManage.getData();
                    clearAllData(model);
                    renderData(listOfDef,model);
                    frame.remove(containSearch);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        //====CLEAR====
        listBtn.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllData(model);
                setAllEmpty(idInput,nameInput,adressInput);
                List<Object[]> newList = StudentManage.clearData();
                renderData(newList,model);
            }
        });

        //====CANCEL====
        listBtn.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Exit.", "Keep it."};
                int option = JOptionPane.showOptionDialog(frame,"All data will be lost ! Are you sure ?", "Exit ?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                         );
                if(option == 0){
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }

            }
        });
    }

    public static void main(String[] args) {
        QLSVui.run();
    }
}

package UI_B8;

import Validation.ValidationC;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class UiB8 {
    private static String[] btnContent = {"Add","Delete","Edit","Search","Clear","Cancel"};
    private static List<JButton> listBtn = new ArrayList<>();

    private static int currentIndex = -1;

    private static boolean isActiveSearch = false;

    private static final String getAllQuery = "SELECT * FROM Sinhvien";

    private static Statement stateMain;

    private static void cleanInput(JTextField idInput, JTextField nameInput, JTextField adressInput){
        idInput.setText("");
        nameInput.setText("");
        adressInput.setText("");
        idInput.grabFocus();
    }
    private static void switchEditDelMode(String btn1, String btn2){
        for (int i = 0; i < listBtn.size(); i++) {
            if(!btnContent[i].equalsIgnoreCase(btn1) && !btnContent[i].equalsIgnoreCase(btn2) && !btnContent[i].equalsIgnoreCase("Cancel")){
                listBtn.get(i).setEnabled(false);
            }
        }
    }

    private static void switchEditDelMode(String btn1){
        for (int i = 0; i < listBtn.size(); i++) {
            if(!btnContent[i].equalsIgnoreCase(btn1)){
                listBtn.get(i).setEnabled(false);
            }
        }
    }

    private static void switchEditDelMode(){
        for (int i = 0; i < listBtn.size(); i++) {
            listBtn.get(i).setEnabled(true);
        }
    }

    private static void setValueToInput(String id, String name, String adress,JTextField idInput, JTextField nameInput, JTextField adressInput){
        idInput.setText(id);
        nameInput.setText(name);
        adressInput.setText(adress);
    }

    private static void setEditableInputForAll(boolean canEdit,JTextField idInput, JTextField nameInput, JTextField adressInput){
        if(canEdit){
            idInput.setEditable(true);
            nameInput.setEditable(true);
            adressInput.setEditable(true);
        }else{
            idInput.setEditable(false);
            nameInput.setEditable(false);
            adressInput.setEditable(false);
        }
    }
    private static void setEditableInput(boolean canEdit,JTextField input){
        if(canEdit){
            input.setEditable(true);
        }else{
            input.setEditable(false);
        }
    }
    private static void renderBtn(){
        for (int i = 0; i < btnContent.length; i++) {
            listBtn.add(new JButton(btnContent[i]));
        }
    }
    private static void renderopup(String msg,JFrame frame){
        JOptionPane.showMessageDialog(frame,msg,"Alert",JOptionPane.WARNING_MESSAGE);
    }
    private static void renderopupInvalid(String msg,JFrame frame){
        JOptionPane.showMessageDialog(frame,msg);
    }
    private static int findIndexByID(String id){
        int index = -1;
        List<Object[]> listFromManage = MStudentB8.getData();
        for (int i = 0; i < listFromManage.size(); i++) {
            String currID = (String) listFromManage.get(i)[0];
            boolean isTrue = currID.equalsIgnoreCase(id);
            if(isTrue){
                index = i;
            }
        }
        return index;
    }
    private static void clearTbl(DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
    }
    private static void connectionDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentUi8","root","2505");
            System.out.println("Succes !");
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stateMain = state;
            getDataFromDB(state);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Error connect !");
            System.out.println(e);
        }
    }

    private static void getDataFromDB(Statement state) throws SQLException {
        String query = "SELECT * FROM Sinhvien";
        ResultSet result = state.executeQuery(query);
        while (result.next()){
            System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
            StudentB8 currentStu = new StudentB8(result.getString(1),result.getString(2),result.getString(3));
            MStudentB8.addNew(currentStu);
        }
    }

    private static void addStudentToDB(StudentB8 stud,DefaultTableModel model,JFrame frame){
       try{
           String query = "INSERT INTO Sinhvien(StuId,StuName,Address)" + "VALUES ('"+stud.getStuId() +"','"+stud.getNameStu()+"','"+stud.getAddressStu()+"');";
           int result = stateMain.executeUpdate(query);
           if(result == 1){
               System.out.println("Add successful");
           }
           List<Object[]> newList = MStudentB8.addNew(stud);
           clearTbl(model);
           renderData(newList,model);
       }catch(SQLException ex){
           System.out.println(ex);
           System.out.println("Trung id !!!!!!!");
           renderopupInvalid("Hay nhap mot ID khac",frame);
       }
    }

    private static void setColorBtnDefault(String btnName){
        for (int i = 0; i < listBtn.size(); i++) {
            if(btnContent[i].equalsIgnoreCase(btnName)){
                listBtn.get(i).setBackground(null);
            }
        }
    }

    private static void delStudentFromDB(String id,DefaultTableModel model) {
        try{
            String query = "DELETE FROM Sinhvien WHERE StuId='"+id+"';";
            stateMain.executeUpdate(query);
            int index = findIndexByID(id);
            List<Object[]> newList = MStudentB8.deleteStu(index);
            clearTbl(model);
            renderData(newList,model);
        } catch (SQLException e) {
            System.out.println("Invalid Id catch !");
        }

    }

    private static void editStuIntoDB(String id,DefaultTableModel model,StudentB8 newStu){
        try{
            String query = "UPDATE Sinhvien SET StuName = '"+newStu.getNameStu()+"' , Address = '"+newStu.getAddressStu()+"' WHERE StuId = '"+newStu.getStuId()+"';";
            stateMain.executeUpdate(query);
            int index = findIndexByID(id);
            List<Object[]> newList = MStudentB8.updateData(index,newStu);
            clearTbl(model);
            renderData(newList,model);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    private static void renderData(List<Object[]> listStudent,DefaultTableModel model){
        for (int i = 0; i < listStudent.size(); i++) {
            model.addRow(listStudent.get(i));
        }
        model.fireTableDataChanged();
    }
    public static void runUI(){
        renderBtn();
        connectionDb();
        JFrame frame = new JFrame("QLSV");
        frame.setSize(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200,200);

        frame.setLayout(new BorderLayout());

        JPanel controlAndInput = new JPanel(new GridLayout(6,2));
        JPanel centerStudentIdlb = new JPanel(new GridBagLayout());
        JPanel centerNamelb = new JPanel(new GridBagLayout());
        JPanel centerAdresslb = new JPanel(new GridBagLayout());
        JLabel studentIdlb = new JLabel("StuId:");
        JLabel namelb = new JLabel("Name:");
        JLabel adresslb = new JLabel("Adress:");
        centerStudentIdlb.add(studentIdlb);
        centerAdresslb.add(adresslb);
        centerNamelb.add(namelb);
        JTextField idInput = new JTextField();
        JTextField nameInput = new JTextField();
        JTextField adressInput = new JTextField();

        JLabel title = new JLabel("STUDENT MANAGEMENT");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.BLUE);
        JPanel titleContain = new JPanel(new GridBagLayout());
        titleContain.add(title);

        controlAndInput.add(centerStudentIdlb);
        controlAndInput.add(idInput);
        controlAndInput.add(centerNamelb);
        controlAndInput.add(nameInput);
        controlAndInput.add(centerAdresslb);
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


        JLabel labelForSearch = new JLabel("Input ID: ");
        JTextField searchBar = new JTextField();
        JPanel containSearch = new JPanel(new BorderLayout());
        containSearch.add(labelForSearch,BorderLayout.WEST);
        containSearch.add(searchBar,BorderLayout.CENTER);



        List<Object[]> listData = MStudentB8.getData();
        renderData(listData,model);
        //ADD NEW
        listBtn.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idInput.getText();
                String name = nameInput.getText();
                String address = adressInput.getText();
                ValidationC validate = ValidationC.isEmpty(id,name,address);
                ValidationC validForID = ValidationC.isValidID(id,MStudentB8.getData());
                System.out.println(validate.isValid() + " "+ validate.getMsg());
                if(validate.isValid()){
                    StudentB8 newStu = new StudentB8(id,name,address);
                    addStudentToDB(newStu,model,frame);
                    cleanInput(idInput,nameInput,adressInput);
                    String msgRender = validForID.getMsg().equalsIgnoreCase("ok!") ? validate.getMsg() : validForID.getMsg();
                    renderopup(msgRender,frame);
                }else {
                    String msgRender = validForID.getMsg().equalsIgnoreCase("ok!") ? validate.getMsg() : validForID.getMsg();
                    renderopupInvalid(msgRender,frame);
                }
            }
        });
        //DELETE
        listBtn.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idInput.getText();
                try {
                    delStudentFromDB(id,model);
                    cleanInput(idInput,nameInput,adressInput);
                    renderopupInvalid("Success !",frame);
                    switchEditDelMode();
                    setColorBtnDefault("Delete");
                    setColorBtnDefault("Edit");
                    idInput.setEditable(true);
                }catch (Exception ex){
                    renderopup("Invalid Id !",frame);
                }
            }
        });
        //Search
        listBtn.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isActiveSearch = !isActiveSearch;
                if(isActiveSearch){
                    frame.add(containSearch,BorderLayout.SOUTH);
                    listBtn.get(3).setBackground(Color.cyan);
                    title.setText("SEARCH MODE");
                    switchEditDelMode("Search");
                    setEditableInputForAll(false,idInput,nameInput,adressInput);
                }else{
                    switchEditDelMode();
                    setColorBtnDefault("Search");
                    setEditableInputForAll(true,idInput,nameInput,adressInput);
                    frame.remove(containSearch);
                    title.setText("STUDENT MANAGEMENT");
                }
                frame.repaint();
                frame.revalidate();
            }
        });
        //Row selection
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isActiveSearch){
                    isActiveSearch = false;
                    setColorBtnDefault("Search");
                    frame.remove(containSearch);
                    title.setText("STUDENT MANAGEMENT");
                    setEditableInput(true,nameInput);
                    setEditableInput(true,adressInput);
                    switchEditDelMode();
                    switchEditDelMode("Delete","Edit");
                }
                try{
                    int rowIndex = table.getSelectedRow();
                    System.out.println(e);
                    System.out.println("rowIndex: "+rowIndex);
                    String idRow = (String) model.getValueAt(rowIndex,0);
                    String nameRow = (String) model.getValueAt(rowIndex,1);
                    String adrRow = (String) model.getValueAt(rowIndex,2);
                    setValueToInput(idRow,nameRow,adrRow,idInput,nameInput,adressInput);
                    listBtn.get(2).setBackground(Color.green);
                    listBtn.get(1).setBackground(Color.PINK);
                    switchEditDelMode("Delete","Edit");
                    idInput.setEditable(false);
                }catch (Exception ex){
                    System.out.println("Click again !");
                    renderopup("Click again to this student to continue !",frame);
                }
            }
        });
        //Cancel action
        listBtn.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listBtn.get(2).setBackground(null);
                listBtn.get(1).setBackground(null);
                switchEditDelMode();
                cleanInput(idInput,nameInput,adressInput);
                table.setSelectionBackground(null);
                idInput.setEditable(true);
                // re-render data if current row count not equals to list size.
                List<Object[]> data = MStudentB8.getData();
                if(model.getRowCount() < data.size()){
                    renderData(data,model);
                }
            }
        });
        //Edit
        listBtn.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String curId = idInput.getText();
                    String newName = nameInput.getText();
                    String newAdress = adressInput.getText();
                    StudentB8 currentStudent = new StudentB8(curId,newName,newAdress);
                    ValidationC valid = ValidationC.isEmpty(newName,newAdress);
                    if(valid.isValid()){
                        editStuIntoDB(curId,model,currentStudent);
                        renderopup("Updated !",frame);
                        listBtn.get(5).doClick();
                    }else{
                        renderopupInvalid(valid.getMsg(),frame);
                    }
                }catch (Exception ex){
                    renderopupInvalid("Can not update !", frame);
                }
            }
        });
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String curId = searchBar.getText();
                if(curId.trim().length() == 0){
                    clearTbl(model);
                    List<Object[]> getList = MStudentB8.searchId(curId);
                    renderData(getList,model);
                }else {
                    clearTbl(model);
                    List<Object[]> getList = MStudentB8.searchId(curId);
                    renderData(getList,model);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String curId = searchBar.getText();
                if(curId.trim().length() == 0){
                    clearTbl(model);
                    List<Object[]> getList = MStudentB8.searchId(curId);
                    renderData(getList,model);
                }else {
                    clearTbl(model);
                    List<Object[]> getList = MStudentB8.searchId(curId);
                    renderData(getList,model);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        frame.setVisible(true);
    }

}

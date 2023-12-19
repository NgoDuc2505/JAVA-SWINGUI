package UI_B8;

import java.util.ArrayList;
import java.util.List;

public class MStudentB8 {
    private static List<Object[]> listStu = new ArrayList<>();

    public static List<Object[]> addNew(StudentB8 stud) {
        Object[] setCurrent = {stud.getStuId(),stud.getNameStu(),stud.getAddressStu()};
        listStu.add(setCurrent);
        return listStu;
    }

    public static List<Object[]> deleteStu(int index){
        listStu.remove(index);
        return listStu;
    }

    public static List<Object[]> updateData(int index, StudentB8 stud){
        listStu.remove(index);
        Object[] setCurrent = {stud.getStuId(),stud.getNameStu(),stud.getAddressStu()};
        listStu.add(setCurrent);
        return listStu;
    }

    public static List<Object[]> searchId(String id){
        List<Object[]> listCopy = new ArrayList<>();
        for (int i = 0; i < listStu.size(); i++) {
            if(listStu.get(i)[0].toString().contains(id)){
                listCopy.add(listStu.get(i));
            }
        }
        if(listCopy.size() == 0){
            System.out.println("Khong thay");
        }
        return listCopy;
    }

    public static List<Object[]> clearData(){
        listStu = new ArrayList<>();
        return listStu;
    }


    public static List<Object[]> getData() {
        return listStu;
    }
}

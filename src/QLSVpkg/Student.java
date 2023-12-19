package QLSVpkg;

public class Student {
    private String stuId;
    private String nameStu;
    private String addressStu;


    public Student(String stuId, String nameStu, String addressStu) {
        this.stuId = stuId;
        this.nameStu = nameStu;
        this.addressStu = addressStu;
    }

    public String getStuId() {
        return stuId;
    }

    public String getNameStu() {
        return nameStu;
    }

    public String getAddressStu() {
        return addressStu;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setNameStu(String nameStu) {
        this.nameStu = nameStu;
    }

    public void setAddressStu(String addressStu) {
        this.addressStu = addressStu;
    }
}

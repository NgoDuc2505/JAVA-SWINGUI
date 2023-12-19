package UI_B8;

public class StudentB8 {
    private String stuId;
    private String nameStu;
    private String addressStu;

    public StudentB8(String stuId, String nameStu, String addressStu) {
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

    public void setNameStu(String nameStu) {
        this.nameStu = nameStu;
    }

    public void setAddressStu(String addressStu) {
        this.addressStu = addressStu;
    }
}

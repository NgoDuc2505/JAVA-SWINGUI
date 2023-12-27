package Validation;

import java.util.List;

public class ValidationC {
    private String msg = "OK!";
    private boolean isValid = true;

    private ValidationC(String msg, boolean isValid) {
        this.msg = msg;
        this.isValid = isValid;
    }

    public static ValidationC isEmpty(String id,String name,String address){
      String msg = "OK!";
      boolean isValid = true;
        String[] listVal = {id,name,address};
        for (int i = 0; i < listVal.length; i++) {
            if (listVal[i].isEmpty()){
                switch (i){
                    case 0:
                        msg += " ID Khong De Trong";
                        isValid = false;
                        break;
                    case 1:
                        msg += " Ten Khong De Trong";
                        isValid = false;
                        break;
                    case 2:
                        msg += " Dia chi Khong De Trong";
                        isValid = false;
                        break;
                }
            }
        }
        String finalMsg = msg.length() == 3 ? msg : msg.split("!")[1];
        return new ValidationC(finalMsg,isValid);
    }

    public static ValidationC isEmpty(String name,String address){
        String msg = "OK!";
        boolean isValid = true;
        String[] listVal = {name,address};
        for (int i = 0; i < listVal.length; i++) {
            if (listVal[i].isEmpty()){
                switch (i){
                    case 0:
                        msg += " Ten Khong De Trong";
                        isValid = false;
                        break;
                    case 1:
                        msg += " Dia chi Khong De Trong";
                        isValid = false;
                        break;
                }
            }
        }
        String finalMsg = msg.length() == 3 ? msg : msg.split("!")[1];
        return new ValidationC(finalMsg,isValid);
    }

    public static ValidationC isValidID(String id, List<Object[]> listUser){
        String msgs = "OK!";
        boolean isValidIDCheck = true;
        for (int i = 0; i < listUser.size(); i++) {
            if(listUser.get(i)[0].equals(id)){
                msgs = "ID da ton tai !";
                isValidIDCheck = false;
                break;
            }
        }

        return new ValidationC(msgs,isValidIDCheck);
    }

    public String getMsg() {
        return msg;
    }

    public boolean isValid() {
        return isValid;
    }
}

package hw001.taskfive.entity;

public class Employee {
    private String mobile;
    private String adress;
    private String bday;

    public Employee(String mobile, String adress, String bday) {
        this.mobile = mobile;
        this.adress = adress;
        this.bday = bday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    @Override
    public String toString() {
        return "|\t" + mobile + "\t|\t" + adress + "\t|\t" + bday + "\t|";
    }
}
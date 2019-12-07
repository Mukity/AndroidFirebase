package com.example.hbmis;

public class userDetails {
    private String fname, lName,empNo,IDNo,Email;

    public userDetails(){
        //Constructor
    }
    public String getfname(){
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lname) {
        this.lName = lname;
    }

    public String getEmpNo() {
        return empNo;
    }
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getIDNo() {
        return IDNo;
    }
    public void setIDNo(String IDNo) {
        this.IDNo = IDNo;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
}

package com.springboot.demo.demoapi.entities;

public class Book {

    private int id;
    private String name;
    private String mobileNo;

    
    public Book() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + "]";
    }
    public Book(int id, String name, String mobileNo) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
    }

    

    

}

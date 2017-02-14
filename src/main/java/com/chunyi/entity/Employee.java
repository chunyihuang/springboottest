package com.chunyi.entity;

/**
 * Created by 黄春怡 on 2017/2/9.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee{

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String phone;

    @Column
    private int location;

    @Column
    private String name;

    @Column
    private  String job;

    protected Employee() {
    }

    public Employee(int id,String phone, int location,String name,String job) {

        this.id = id;
        this.phone = phone;
        this.location = location;
        this.name = name;
        this.job = job;
    }

    public int getId(){return this.id;}

    public String getPhone() {
        return this.phone;
    }

    public int getLocation() {
        return this.location;
    }

    public String getName(){
        return this.name;
    }

    public String getJob(){
        return this.job;
    }


    public void setId(int id){this.id = id;}
    public void setPhone(String phone){this.phone = phone;}
    public void setLocation(int location){this.location = location;}
    public void setName(String name){this.name = name;}
    public void setJob(String job){this.job = job;}


}



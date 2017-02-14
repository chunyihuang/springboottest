package com.chunyi.entity;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 黄春怡 on 2017/2/10.
 */
public class EmployeeLoc {
    //@Autowired
    private Employee employee;
    //@Autowired
    private String roomname;

    public EmployeeLoc(Employee employee,String roomname){
        this.employee = employee;
        this.roomname = roomname;

    }
    public String getRoomname(){return this.roomname;}
    public Employee getEmployee(){return this.employee;}
    public void setEmployee(Employee employee){this.employee = employee;}
    public void setRoomname(String roomname){this.roomname =roomname;}
}

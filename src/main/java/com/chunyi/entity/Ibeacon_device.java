package com.chunyi.entity;

/**
 * Created by 黄春怡 on 2017/2/9.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ibeacon_device{

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String mac;

    @Column
    private String roomname;

    protected Ibeacon_device() {
    }

    public Ibeacon_device(int id,String mac, String roomname) {

        this.id = id;
        this.mac = mac;
        this.roomname = roomname;
    }

    public int getId(){return this.id;}

    public String getMac() {
        return this.mac;
    }

    public String getRoomname() {
        return this.roomname;
    }


    public void setId(int id){this.id = id;}
    public void setMac(String mac){this.mac = mac;}
    public void setRoomname(String roomname){this.roomname = roomname;}


}

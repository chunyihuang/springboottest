package com.chunyi.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by 黄春怡 on 2017/2/9.
*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
    public class Locate_log{

        @Id
        @GeneratedValue
        private int id;

        @Column
        private int employee_id;

        @Column
        private int device_id;

        @Column
        private Date record_date;

        @Column
        private Time start_time;

        @Column
        private Time end_time;


        protected Locate_log() {
        }

        public Locate_log(int employee_id, int device_id,Date record_date,Time start_time,Time end_time) {


            this.employee_id = employee_id;
            this.device_id = device_id;
            this.record_date = record_date;
            this.start_time = start_time;
            this.end_time = end_time;
        }

        public int getId(){return this.id;}

        public int getEmployee_id() {
            return this.employee_id;
        }

        public int getDevice_id() {
            return this.device_id;
        }

        public Date getRecord_date(){
            return this.record_date;
        }

        public Time getStart_time(){
            return this.start_time;
        }

        public Time getEnd_time(){
            return this.end_time;
        }


        public void setId(int id){this.id = id;}
        public void setEmployee_id(int employee_id){this.employee_id = employee_id;}
        public void setDevice_id(int device_id){this.device_id = device_id;}
        public void setRecord_date(Date record_date){this.record_date = record_date;}
        public void setStart_time(Time start_time){this.start_time = start_time;}
        public void setEnd_time(Time end_time){this.end_time = end_time;}

    }





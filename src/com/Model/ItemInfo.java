package com.Model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class ItemInfo {

    private Integer item_id;
    private String item_name;
    private String item_type;
    private Integer item_number;
    private java.sql.Date item_date;
    private String item_adder;
    private String item_lastUser;
    private Integer item_lastOps;
    private String item_notes;
    private java.sql.Date item_add_date;

    public ItemInfo() {
    }

    public ItemInfo(boolean bool){
        Random r = new Random();
        int n = r.nextInt(10) + 1;
         item_id =  n;
         item_name = "name";
        item_type = "type";
        item_number = 1;
         item_date = new java.sql.Date(System.currentTimeMillis());
         item_adder = "adder";
         item_lastUser = "lUser";
        item_lastOps = 1;
         item_notes = Integer.toString(n);
    }

    public Integer getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_type() {
        return item_type;
    }

    public Integer getItem_number() {
        return item_number;
    }

    public java.sql.Date getItem_date() { return item_date; }

    public String getItem_adder() {
        return item_adder;
    }

    public String getItem_lastUser() {
        return item_lastUser;
    }

    public Integer getItem_lastOps() {
        return item_lastOps;
    }

    public String getItem_notes() {
        return item_notes;
    }

    public Date getItem_add_date() {
        return item_add_date;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public void setItem_number(Integer item_number) {
        this.item_number = item_number;
    }

    public void setItem_date(java.sql.Date item_date) { this.item_date = item_date; }

    public void setItem_adder(String item_adder) {
        this.item_adder = item_adder;
    }

    public void setItem_lastUser(String item_lastUser) {
        this.item_lastUser = item_lastUser;
    }

    public void setItem_lastOps(Integer item_lastOps) {
        this.item_lastOps = item_lastOps;
    }

    public void setItem_notes(String item_notes) {
        this.item_notes = item_notes;
    }

    public void setItem_add_date(Date item_add_date) {
        this.item_add_date = item_add_date;
    }

    public String getItem_expDay(){
        if(item_date == null){
            return "Not available";
        }
        Long value = ((item_date.getTime() - System.currentTimeMillis()) / (24*3600*1000));
        if(value <= 0){
            return "Expired!";
        }
        return String.valueOf(value);}
}

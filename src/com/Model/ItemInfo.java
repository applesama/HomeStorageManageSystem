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
    private String item_notes;
    private java.sql.Date item_add_date;

    public ItemInfo() {
    }

   /* public ItemInfo(String item_name, String item_type, int item_number, java.sql.Date item_date, String item_adder, String item_notes, Date item_add_date){
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_number = item_number;
        this.item_date = item_date;
        this.item_adder =
    }*/


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


    public String getItem_notes() {
        return item_notes;
    }

    public java.sql.Date getItem_add_date() {
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

    public void setItem_notes(String item_notes) {
        this.item_notes = item_notes;
    }

    public void setItem_add_date(java.sql.Date item_add_date) {
        this.item_add_date = item_add_date;
    }

    public String getItem_expDay(){
        if(item_date == null){
            return "Not available";
        }
        long value = ((item_date.getTime() - System.currentTimeMillis()) / (24*3600*1000));
        if(value <= 0){
            return "Expired!";
        }
        return String.valueOf(value);}
}

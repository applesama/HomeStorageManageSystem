package com.Service;

import com.DAO.ItemDAO;
import com.Model.ItemInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public ArrayList<String> getTypeOptions(){
        return (ArrayList<String>) itemDAO.queryMultiScalar("SELECT DISTINCT item_type FROM item");

    }

    public List<ItemInfo> getSelectedItem(String selection){
        if((selection.equals("Not Selected"))||(selection.equals("未选择"))){
            return itemDAO.queryMulti("SELECT * FROM item", ItemInfo.class);
        }
        return itemDAO.queryMulti("SELECT * FROM item WHERE item_type = ?", ItemInfo.class, selection);
    }

    public boolean addItem(String item_name, String item_type, int item_number, java.sql.Date item_date, String item_adder, String item_notes, java.sql.Date item_add_date){
        return itemDAO.dmlUpdate("INSERT INTO item VALUES(null, ?, ?, ?, ?, ? , ?, ?)", item_name, item_type, item_number, item_date, item_adder, item_notes, item_add_date) > 0;
    }

    public boolean onlyOneLeft(int id){
        Long temp = (Long)itemDAO.queryScalar("SELECT item_number FROM item where item_id = ?", id);
        return temp == 1;
    }

    public boolean deleteItem(int id){
        return itemDAO.dmlUpdate("DELETE FROM item WHERE item_id = ?", id) > 0;
    }

    public boolean changeItemNumber(int id, int number){

        return itemDAO.dmlUpdate("UPDATE item set item_number = item_number + ? WHERE item_id = ?", number, id) > 0;
    }

    public boolean ifItemExist(String selection){
        return itemDAO.queryMulti("SELECT item_type FROM item WHERE item_type = ?", ItemInfo.class, selection) != null;
    }
}

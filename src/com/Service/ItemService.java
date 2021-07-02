package com.Service;

import com.DAO.ItemDAO;
import com.Model.ItemInfo;


import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public ArrayList<String> getTypeOptions(){
        return (ArrayList<String>) itemDAO.queryMultiScalar("SELECT DISTINCT item_type FROM item");

    }

    public List<ItemInfo> getSelectedItem(String selection){
        if(selection == "Not Selected"){
            return itemDAO.queryMulti("SELECT * FROM item", ItemInfo.class);
        }
        return itemDAO.queryMulti("SELECT * FROM item WHERE item_type = ?", ItemInfo.class, selection);
    }
}

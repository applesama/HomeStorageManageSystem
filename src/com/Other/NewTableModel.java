package com.Other;

import com.Model.ItemInfo;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NewTableModel extends AbstractTableModel {

    private List<ItemInfo> itemInfo;
    private String[] column = new String[]{"Id","Name","Type","Number","Exp_Date","Adder","Exp.(day)","Added_Date"};

    public NewTableModel(List<ItemInfo> itemInfo){
        this.itemInfo = itemInfo;
    }

    public void setItemInfo(List<ItemInfo> itemInfo) {
        this.itemInfo = itemInfo;
    }

    @Override
    public int getRowCount() {
        return itemInfo.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Object data = "";
        switch (c){
            case 0:
                data = itemInfo.get(r).getItem_id().toString();
                break;
            case 1:
                data = itemInfo.get(r).getItem_name();
                break;
            case 2:
                data = itemInfo.get(r).getItem_type();
                break;
            case 3:
                data = itemInfo.get(r).getItem_number().toString();
                break;
            case 4:
                if(itemInfo.get(r).getItem_date() == null){
                    return "Not Available";
                }
                data = itemInfo.get(r).getItem_date().toString();
                break;
            case 5:
                data = itemInfo.get(r).getItem_adder();
                break;
            case 6:
                data = itemInfo.get(r).getItem_expDay();
                break;
            case 7:
                data = itemInfo.get(r).getItem_add_date();
        }
        return data;

    }

    @Override
    public String getColumnName(int column) {
        return this.column[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public String getNote(int row){
        return itemInfo.get(row).getItem_notes();
    }

    public int getId(int row){
        return itemInfo.get(row).getItem_id();
    }


}

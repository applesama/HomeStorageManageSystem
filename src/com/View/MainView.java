package com.View;

import com.Model.ItemInfo;
import com.Model.UserInfo;
import com.Other.NewTableModel;
import com.Service.ItemService;
import com.Service.UserService;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainView extends JFrame {

    private ItemService itemService = new ItemService();
    private UserInfo userInfo = null;
    private JTable table;
    private JScrollPane tableScrollPane;
    private NewTableModel model = new NewTableModel(null);
    private JTextArea textArea = new JTextArea();
    private JComboBox<String> comboBox = new JComboBox<>();
    private String lang;
    private Font font;
    private String loginTip1;
    private String loginTip2;
    private String loginTip3;
    private String loginTip4;
    private String loginTip5;
    private String loginTip6;
    private String loginTip7;
    private String loginTip8;

    private JMenu menu1 = new JMenu("Basic Settings");
    private JMenu menu2 = new JMenu("Help");
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem item1 = new JMenuItem("Password Reset");
    private JMenuItem item2 = new JMenuItem("Add New User");
    private JMenuItem item3 = new JMenuItem("Change Authorities");

    private JButton jb1 = new JButton("Add Item");
    private JButton jb2 = new JButton("Delete Item");
    private JButton jb3 = new JButton("Refresh");
    private JLabel typeLabel = new JLabel("Type:");
    private JButton jb4 = new JButton("+");
    private JButton jb5 = new JButton("-");


    public MainView() {

    }

    public MainView(UserInfo userInfo, String lang) {
        this.userInfo = userInfo;
        this.lang = lang;
    }

    public void addFrm() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 550);
        setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Main.png"));
        setLang(lang.equals("中文"));

        setJMenuBar(menuBar);
        menuBar.add(menu1);
        menuBar.add(menu2);
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);

        JMenuItem item4 = new JMenuItem("中文");
        JMenuItem item5 = new JMenuItem("English");
        menu2.add(item4);
        menu2.add(item5);

        jb1.setBounds(50, 450, 100, 30);
        add(jb1);
        jb2.setBounds(170, 450, 100, 30);
        add(jb2);
        jb3.setBounds(290, 450, 100, 30);
        add(jb3);
        jb4.setBounds(410, 450,40, 30);
        add(jb4);
        jb5.setBounds(460, 450,40, 30);
        add(jb5);
        jb4.setFont(new Font("times new roman",Font.PLAIN,10));
        jb5.setFont(new Font("times new roman",Font.PLAIN,10));
        item3.setEnabled(false);

        model = new NewTableModel(itemService.getSelectedItem(loginTip1));
        table = new JTable(model);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);//set select mode is row selected only

        table.setAutoCreateRowSorter(true);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(10, 20, 700, 400);

        add(tableScrollPane);

        textArea = new JTextArea(8, 40);
        textArea.setLineWrap(true);//Auto-line-wrap
        textArea.setAutoscrolls(true);

        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setBounds(750, 20, 400, 400);
        add(areaScrollPane);

        add(typeLabel);

        comboBox.setEditable(false);
        comboBox.setBounds(1000, 450, 150, 30);
        add(comboBox);

        ArrayList<String> receivedItem = itemService.getTypeOptions();
        comboBox.addItem(loginTip1);
        for (String s : receivedItem) {
            comboBox.addItem(s);//may have bad performance when adding too much items, should be override
        }


        setVisible(true);



        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {

                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    update(3);
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if ((table.getSelectedRow() > table.getRowCount()) || (table.getSelectedRow() < 0)) {
                        textArea.setText("");
                    }//every time after the table changed, the model will be changed, sometimes the row that user selected before the table might become unavailable which could cause invalid index
                    else {
                        textArea.setText(model.getNote(table.convertRowIndexToModel(table.getSelectedRow())));
                    }
                }
            }
        });

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditView editView = new EditView(userInfo,lang);
                editView.addFrm();
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditView editView = new EditView(true, lang);
                editView.addFrm();
            }
        });

        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lang = "中文";
                setLang(true);
                update(2);
            }
        });

        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lang = "English";
                setLang(false);
                update(2);
            }
        });

        jb1.addActionListener(new ActionListener() {//Add item
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddView addView = new AddView(userInfo.getUser_name(), lang);
                addView.addFrm();
                addView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        update(4, addView.getCata());
                        super.windowClosing(e);
                    }
                });

            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean ifSuccess;
                if(JOptionPane.showConfirmDialog(null, loginTip6,loginTip5, JOptionPane.YES_NO_OPTION) == 0){
                    ifSuccess = itemService.deleteItem(model.getId(table.convertRowIndexToModel(table.getSelectedRow())));
                }else {
                    ifSuccess = true;
                }

                if(ifSuccess){
                    update(1);
                }else {
                    JOptionPane.showMessageDialog(null, loginTip3, loginTip2, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                update(2);
            }
        });

        jb4.addActionListener(new ActionListener() {// "+"
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = model.getId(table.convertRowIndexToModel(table.getSelectedRow()));
                if(itemService.changeItemNumber(row,1)){
                    update(1);

                    return;
                }
                JOptionPane.showMessageDialog(null, loginTip3, loginTip2, JOptionPane.ERROR_MESSAGE);

            }
        });

        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = model.getId(table.convertRowIndexToModel(table.getSelectedRow()));
                boolean ifSuccess = false;
                if(itemService.onlyOneLeft(row)){
                    if(JOptionPane.showConfirmDialog(null, loginTip8, loginTip7, JOptionPane.YES_NO_OPTION) == 0){
                        ifSuccess = itemService.deleteItem(row);
                    }else {
                        ifSuccess = true;
                    }

                }
                else if(itemService.changeItemNumber(row,-1)){
                    ifSuccess = true;
                }
                if(ifSuccess){
                    update(1);

                }else {
                    JOptionPane.showMessageDialog(null, loginTip4, loginTip2, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void update(int type, String ... ifExist){
        boolean bool1 = false;
        boolean bool2 = false;
        switch (type){
            case 1:
                bool1 = true;
                bool2 = true;
                break;
            case 2:
                bool1 = true;
                bool2 = false;
                break;
            case 3:
                bool1 = false;
                bool2 = false;
            break;
            case 4:
                bool1 = false;
                bool2 = true;
                break;
        }
        String item = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
        List<ItemInfo> list = itemService.getSelectedItem(item);
        table.clearSelection();
        model.setItemInfo(list);
        model.fireTableDataChanged();
        model.fireTableStructureChanged();
        table.setModel(model);
        table.validate();
        table.updateUI();
        tableScrollPane.validate();
        tableScrollPane.updateUI();
        table.clearSelection();
        if(bool1){
            ArrayList<String> receivedItem = itemService.getTypeOptions();
            comboBox.insertItemAt(loginTip1, 0);
            if(bool2){
                int row = comboBox.getItemCount();
                for(int i = 1; i < row; i ++){
                    comboBox.removeItemAt(i);
                }
                for (String s : receivedItem) {
                    comboBox.addItem(s);
                }
            }
            if(!bool2){
                comboBox.removeItemAt(1);
            }
        }else if((!bool1)&&(bool2)){
                if(ifExist[0] != null){
                    comboBox.addItem(ifExist[0]);
                }
        }

    }

    public void setLang(boolean zhOrEn){
        if(zhOrEn){
            setTitle("主界面");
            font = new Font("微软雅黑",Font.PLAIN,10);
            menu1.setText("设置");
            menu2.setText("语言");
            item1.setText("重置密码");
            item2.setText("添加用户");
            item3.setText("变更权限");
            jb1.setText("添加物品");
            jb2.setText("删除物品");
            jb3.setText("刷新");
            typeLabel.setText("分类");
            typeLabel.setBounds(960, 450, 40, 30);

            loginTip1 = "未选择";
            loginTip2 = "警告";
            loginTip3 = "物品删除失败";
            loginTip4 = "减少物品";
            loginTip5 = "删除物品";
            loginTip6 = "你希望删除该物品吗？";
            loginTip7 = "该物品只有一个了";
            loginTip8 = "该物品只有一个了，继续将删除该物品";

        }else {
            setTitle("Main");
            font = new Font("times new roman",Font.PLAIN,10);
            menu1.setText("Basic Settings");
            menu2.setText("language");
            item1.setText("Password Reset");
            item2.setText("Add New User");
            item3.setText("Change Authorities");
            jb1.setText("Add Item");
            jb2.setText("Delete Item");
            jb3.setText("Refresh");
            typeLabel.setText("Categories:");
            typeLabel.setBounds(920, 450, 70, 30);

            loginTip1 = "Not Selected";
            loginTip2 = "Warning";
            loginTip3 = "Deleting item failed";
            loginTip4 = "Removing item failed";
            loginTip5 = "Deleting item";
            loginTip6 = "Would you like to delete this item?";
            loginTip7 = "Only one left";
            loginTip8 = "This item has only one left, reducing would delete it, continue?";
        }
        menu1.setFont(font);
        menu2.setFont(font);
        item1.setFont(font);
        item2.setFont(font);
        item3.setFont(font);
        jb1.setFont(font);
        jb2.setFont(font);
        jb3.setFont(font);

    }
}

package com.View;

import com.Model.ItemInfo;
import com.Model.UserInfo;
import com.Other.NewTableModel;
import com.Service.ItemService;
import com.Service.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainView extends JFrame {

    private UserService userService = new UserService();
    private ItemService itemService = new ItemService();
    private UserInfo userInfo = null;
    private static JTable table;
    private JScrollPane tableScrollPane;
    private static NewTableModel model = new NewTableModel(null);
    private JTextArea textArea = new JTextArea();

    public MainView() {

    }

    public MainView(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void addFrm() {
        Font font = new Font("times new roman", Font.PLAIN, 12);
        setTitle("Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 550);
        setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Main.png"));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu1 = new JMenu("Basic Settings");
        JMenu menu2 = new JMenu("Help");

        menu1.setFont(font);
        menu2.setFont(font);

        menuBar.add(menu1);
        menuBar.add(menu2);

        JMenuItem item1 = new JMenuItem("Password Reset");
        JMenuItem item2 = new JMenuItem("Add New User");
        JMenuItem item3 = new JMenuItem("Change Authorities");

        item1.setFont(font);
        item2.setFont(font);
        item3.setFont(font);

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);

        JButton jb1 = new JButton("Add Item");
        jb1.setFont(font);
        jb1.setBounds(50, 450, 100, 30);
        add(jb1);

        JButton jb2 = new JButton("Delete Item");
        jb2.setFont(font);
        jb2.setBounds(170, 450, 100, 30);
        add(jb2);

        JButton jb3 = new JButton("Refresh");
        jb3.setFont(font);
        jb3.setBounds(290, 450, 100, 30);
        add(jb3);


        item3.setEnabled(false);

        NewTableModel model = new NewTableModel(itemService.getSelectedItem("Not Selected"));
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

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(font);
        typeLabel.setBounds(570, 450, 100, 30);
        add(typeLabel);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(false);
        comboBox.setFont(font);
        comboBox.setBounds(610, 450, 150, 30);
        add(comboBox);

        ArrayList<String> receivedItem = itemService.getTypeOptions();

        comboBox.addItem("Not Selected");
        for (String s : receivedItem) {
            comboBox.addItem(s);//may have bad performance when adding too much items, should be override
        }


        setVisible(true);

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {

                    String item = itemEvent.getItem().toString();
                    List<ItemInfo> list = itemService.getSelectedItem(item);
                    model.setItemInfo(list);
                    model.fireTableDataChanged();
                    //model.fireTableStructureChanged();
                    table.setModel(model);
                    table.validate();
                    table.updateUI();
                    tableScrollPane.validate();
                    tableScrollPane.updateUI();
                    table.clearSelection();
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
                EditView editView = new EditView(userInfo);
                editView.addFrm();
            }
        });

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

    }


}

package com.View;

import com.Service.ItemService;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AddView extends JFrame {
    private String user = "";
    private ItemService itemService = new ItemService();
    private String lang;
    Font font;
    JLabel nameLabel = new JLabel("Name");
    JLabel typeLabel = new JLabel("Type");
    JLabel dateLabel = new JLabel("Date");
    JLabel numberLabel = new JLabel("Number");
    JButton confirmButton = new JButton("Confirm");
    JComboBox<String> typeField = new JComboBox<>();
    JTextField nameField = new JTextField(30);
    JTextArea textArea = new JTextArea();
    JTextField numberField = new JTextField(30);
    private String loginTip1;
    private String loginTip2;
    private String loginTip3;
    private String loginTip4;
    private String loginTip5;

    public AddView(String user, String lang) {
        this.user = user;
        this.lang = lang;
    }

    public void addFrm(){
        setLang(lang.equals("中文"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 300);
        setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Main.png"));

        nameLabel.setBounds(20, 20 , 70, 30);
        add(nameLabel);

        nameField.setBounds(100, 20, 150, 30);
        add(nameField);

        typeLabel.setBounds(20, 60 , 70, 30);
        add(typeLabel);

        ArrayList<String> receivedItem = itemService.getTypeOptions();
        typeField.addItem("");
        typeField.setEditable(true);
        for (String s : receivedItem) {
            typeField.addItem(s);//may have bad performance when adding too much items, should be override
        }
        typeField.setBounds(100, 60, 150, 30);
        add(typeField);

        dateLabel.setBounds(20, 100 , 70, 30);
        add(dateLabel);
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setBounds(100, 100, 150, 30);
        add(datePicker);

        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
            @Override
            public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException
            {
                fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
        });
        //using to make sure number field can only be put digits

        numberLabel.setBounds(20, 140 , 70, 30);
        add(numberLabel);

        numberField.setBounds(100, 140, 150, 30);
        numberField.setDocument(doc);
        add(numberField);

        confirmButton.setBounds(40, 180, 150, 30);
        add(confirmButton);

        textArea.setBounds(300, 20, 200, 200);
        textArea.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        add(textArea);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(nameField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, loginTip3, loginTip2, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int number;
                if(numberField.getText().equals("")||numberField.getText().equals("0")){
                    number = 1;
                }else {
                    number = Integer.parseInt(numberField.getText());
                }
                if(itemService.addItem(nameField.getText(), Objects.requireNonNull(typeField.getSelectedItem()).toString(), number, new java.sql.Date(datePicker.getDate().getTime()), user, textArea.getText(),new java.sql.Date(new Date().getTime()))) {
                    JOptionPane.showMessageDialog(null,  loginTip4, loginTip1, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, loginTip5, loginTip2, JOptionPane.ERROR_MESSAGE);
            }
        });
        setVisible(true);
    }
    public String getCata(){
        if(itemService.ifItemExist(Objects.requireNonNull(typeField.getSelectedItem()).toString())){
            return Objects.requireNonNull(typeField.getSelectedItem()).toString();
        }else {
            return null;
        }
    }

    public void setLang(boolean zhOrEn){
        if(zhOrEn){
            setTitle("添加物品");
            font = new Font("微软雅黑",Font.PLAIN,10);
            nameLabel.setText("名称");
            typeLabel.setText("密码");
            dateLabel.setText("保质期");
            numberLabel.setText("数量");
            confirmButton.setText("确认");
            loginTip1 = "消息";
            loginTip2 = "警告";
            loginTip3 = "物品名称不能为空";
            loginTip4 = "物品已添加";
            loginTip5 = "物品添加失败";

        }else {
            setTitle("Add item");
            font = new Font("times new roman",Font.PLAIN,10);
            nameLabel.setText("Name");
            typeLabel.setText("Categories");
            dateLabel.setText("Expiry date");
            numberLabel.setText("Number");
            confirmButton.setText("Enter");
            loginTip1 = "Message";
            loginTip2 = "Warning";
            loginTip3 = "Item name can't be empty";
            loginTip4 = "Item added";
            loginTip5 = "Adding item failed";
        }
        textArea.setFont(font);
        numberField.setFont(font);
        nameLabel.setFont(font);
        typeLabel.setFont(font);
        typeField.setFont(font);
        dateLabel.setFont(font);
        numberLabel.setFont(font);
        confirmButton.setFont(font);
    }
}

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

    public AddView(String user) {
        this.user = user;
    }

    public void addFrm(){
        Font font = new Font("times new roman", Font.PLAIN, 12);
        setTitle("Add item");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 300);
        setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Main.png"));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(font);
        nameLabel.setBounds(20, 20 , 70, 30);
        add(nameLabel);
        JTextField nameField = new JTextField(30);
        nameField.setBounds(100, 20, 150, 30);
        add(nameField);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(font);
        typeLabel.setBounds(20, 60 , 70, 30);
        add(typeLabel);
        JComboBox<String> typeField = new JComboBox<>();
        typeField.setFont(font);
        ArrayList<String> receivedItem = itemService.getTypeOptions();
        typeField.addItem("");
        typeField.setEditable(true);
        for (String s : receivedItem) {
            typeField.addItem(s);//may have bad performance when adding too much items, should be override
        }
        typeField.setBounds(100, 60, 150, 30);
        add(typeField);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setFont(font);
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

        JLabel numberLabel = new JLabel("Number");
        numberLabel.setFont(font);
        numberLabel.setBounds(20, 140 , 70, 30);
        add(numberLabel);
        JTextField numberField = new JTextField(30);
        numberField.setFont(font);
        numberField.setBounds(100, 140, 150, 30);
        numberField.setDocument(doc);
        add(numberField);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(font);
        confirmButton.setBounds(40, 180, 150, 30);
        add(confirmButton);

        JTextArea textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setBounds(300, 20, 200, 200);
        textArea.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        add(textArea);



        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(nameField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Item name can't be empty", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int number;
                if(numberField.getText().equals("")||numberField.getText().equals("0")){
                    number = 1;
                }else {
                    number = Integer.parseInt(numberField.getText());
                }
                if(itemService.addItem(nameField.getText(), Objects.requireNonNull(typeField.getSelectedItem()).toString(), number, new java.sql.Date(datePicker.getDate().getTime()), user, textArea.getText(),new java.sql.Date(new Date().getTime()))) {
                    JOptionPane.showMessageDialog(null, "Item added", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Item addition failed", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);

    }
}

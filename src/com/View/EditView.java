package com.View;

import com.Model.UserInfo;
import com.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditView extends JFrame {

    private UserInfo userInfo = null;
    private int adjustmentValue1 = 0;
    private int adjustmentValue2 = 0;
    private boolean addUser = false;


    public EditView(){

    }

    public EditView(boolean addUser){
        this.addUser = addUser;
        adjustmentValue2 = 50;

    }

    public EditView(UserInfo userInfo){
        this.userInfo = userInfo;
        adjustmentValue1 = 50;
    }

    public void addFrm(){
        Font font = new Font("times new roman",Font.PLAIN,10);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if(addUser){
            setTitle("Register");
        }else {
            setTitle("Reset password");
        }

        setResizable(false);
        setSize(350,300 - adjustmentValue1 - adjustmentValue2);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Manage.png"));




        JTextField userInput1 = new JTextField(15);
        if(userInfo == null) {
            JLabel userName = new JLabel("User name");
            userName.setFont(font);
            userName.setBounds(20, 20, 130, 30);
            add(userName);

            userInput1.setBounds(160, 20, 130, 30);
            add(userInput1);
        }

        JPasswordField userInput2 = new JPasswordField(15);
        if(!addUser){
            JLabel userPass1 = new JLabel("Old password");
            userPass1.setFont(font);
            userPass1.setBounds(20,70 - adjustmentValue1,130,30);
            add(userPass1);

            userInput2.setBounds(160, 70 - adjustmentValue1, 130 , 30);
            add(userInput2);
        }



        JLabel userPass2 = new JLabel("New password");
        userPass2.setFont(font);
        userPass2.setBounds(20,120 - adjustmentValue1 - adjustmentValue2, 130,30);
        add(userPass2);

        JPasswordField userInput3 = new JPasswordField(15);
        userInput3.setBounds(160,120 - adjustmentValue1 - adjustmentValue2,130,30);
        add(userInput3);


        JLabel userPass3 = new JLabel("Repeat new password");
        userPass3.setFont(font);
        userPass3.setBounds(20,170 - adjustmentValue1 - adjustmentValue2, 130,30);
        add(userPass3);

        JPasswordField userInput4 = new JPasswordField(15);
        userInput4.setBounds(160,170 - adjustmentValue1 - adjustmentValue2,130,30);
        add(userInput4);



        JButton enterInterFace = new JButton("Enter");
        enterInterFace.setFont(font);
        enterInterFace.setBounds(100, 220 - adjustmentValue1 - adjustmentValue2, 130, 30);
        add(enterInterFace);

        setVisible(true);

        enterInterFace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(addUser){
                    addVerify(userInput1,userInput3, userInput4);
                }else {
                    editVerify(userInput1, userInput2, userInput3, userInput4);
                }

            }
        });

    }

    public void addVerify(JTextField userInput1, JPasswordField userInput3, JPasswordField userInput4){
        if((String.valueOf(userInput3.getPassword()).equals(""))
                ||(String.valueOf(userInput4.getPassword()).equals(""))||((userInfo == null)&&(userInput1.getText().equals("")))) {
            JOptionPane.showMessageDialog(null, "One or more of the fields are empty", "Warning", JOptionPane.ERROR_MESSAGE);
        }
        else if(!String.valueOf(userInput3.getPassword()).equals(String.valueOf(userInput4.getPassword()))){
            JOptionPane.showMessageDialog(null, "Two new password is not equal", "Warning", JOptionPane.ERROR_MESSAGE);
        }else{
            addAct(userInput1.getText(), String.valueOf(userInput3.getPassword()));
        }
    }

    public void addAct(String uid, String password){
        UserService userService = new UserService();
        if(userService.ifUserExist(uid)){
            JOptionPane.showMessageDialog(null, "This user is already exist", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(userService.addUser(uid, password, UserInfo.addSalt())){
            JOptionPane.showMessageDialog(null, "User added", "Message", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            return;
        }
        JOptionPane.showMessageDialog(null, "User adding failed", "Warning", JOptionPane.ERROR_MESSAGE);
    }


    public void editVerify(JTextField userInput1, JPasswordField userInput2, JPasswordField userInput3, JPasswordField userInput4){
        if((String.valueOf(userInput2.getPassword()).equals(""))||(String.valueOf(userInput3.getPassword()).equals(""))
                ||(String.valueOf(userInput4.getPassword()).equals(""))||((userInfo == null)&&(userInput1.getText().equals("")))) {
            JOptionPane.showMessageDialog(null, "one or more of the fields are empty", "Warning", JOptionPane.ERROR_MESSAGE);
        }
        else if(!String.valueOf(userInput3.getPassword()).equals(String.valueOf(userInput4.getPassword()))){
            JOptionPane.showMessageDialog(null, "Two new password is not equal", "Warning", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if(userInfo == null){
                editAct(userInput1.getText(), String.valueOf(userInput2.getPassword()), String.valueOf(userInput4.getPassword()));
            }else {
                editAct(String.valueOf(userInput2.getPassword()), String.valueOf(userInput4.getPassword()));
            }
        }
    }

    public void editAct(String passOld, String passNew){
        UserService userService = new UserService();
        if(userService.getUserByIdAndPwd(userInfo.getUser_name(), passOld) == null){
            JOptionPane.showMessageDialog(null, "Please enter the correct password or user ID", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(userService.setUserPwd(userInfo.getUser_name(), passNew + userInfo.getUser_salt())){
            JOptionPane.showMessageDialog(null, "Password edited", "Message", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            return;
        }
        JOptionPane.showMessageDialog(null, "Password editing failed", "Warning", JOptionPane.ERROR_MESSAGE);
    }
    public void editAct(String uid, String passOld, String passNew){
        UserService userService = new UserService();
        userInfo = userService.getUserByIdAndPwd(uid, passOld);
        if(userInfo == null){
            JOptionPane.showMessageDialog(null, "Please enter the correct password or user ID", "Warning", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(userService.setUserPwd(uid, passNew + userInfo.getUser_salt())){
            JOptionPane.showMessageDialog(null, "Password edited", "Message", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            return;
        }
        JOptionPane.showMessageDialog(null, "Password editing failed", "Warning", JOptionPane.ERROR_MESSAGE);

    }
}

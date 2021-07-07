package com.View;

import com.Model.UserInfo;
import com.Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditView extends JFrame {

    private UserInfo userInfo = null;
    private int adjustmentValue1 = 0;
    private int adjustmentValue2 = 0;
    private boolean addUser = false;
    private String lang;
    private Font font;
    private JTextField userInput1 = new JTextField(15);
    private JPasswordField userInput2 = new JPasswordField(15);
    private JLabel userName = new JLabel();
    private JLabel userPass1 = new JLabel();
    private JLabel userPass2 = new JLabel();
    private JPasswordField userInput3 = new JPasswordField(15);
    private JLabel userPass3 = new JLabel();
    private JPasswordField userInput4 = new JPasswordField(15);
    private JButton enterInterFace = new JButton();
    private String loginTip1;
    private String loginTip2;
    private String loginTip3;
    private String loginTip4;
    private String loginTip5;
    private String loginTip6;
    private String loginTip7;
    private String loginTip8;
    private String loginTip9;
    private String loginTip10;

    public EditView(){

    }

    public EditView(boolean addUser, String lang){
        this.addUser = addUser;
        this.lang = lang;
        adjustmentValue2 = 50;

    }

    public EditView(UserInfo userInfo, String lang){
        this.userInfo = userInfo;
        this.lang = lang;
        adjustmentValue1 = 50;
    }

    public void addFrm(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(350,300 - adjustmentValue1 - adjustmentValue2);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Manage.png"));
        if(userInfo == null) {
            userName.setBounds(20, 20, 130, 30);
            add(userName);
            userInput1.setBounds(160, 20, 130, 30);
            add(userInput1);
        }

        if(!addUser){
            userPass1.setBounds(20,70 - adjustmentValue1,130,30);
            add(userPass1);
            userInput2.setBounds(160, 70 - adjustmentValue1, 130 , 30);
            add(userInput2);
        }
        userPass2.setBounds(20,120 - adjustmentValue1 - adjustmentValue2, 130,30);
        add(userPass2);
        userInput3.setBounds(160,120 - adjustmentValue1 - adjustmentValue2,130,30);
        add(userInput3);
        userPass3.setBounds(20,170 - adjustmentValue1 - adjustmentValue2, 130,30);
        add(userPass3);
        userInput4.setBounds(160,170 - adjustmentValue1 - adjustmentValue2,130,30);
        add(userInput4);
        enterInterFace.setBounds(100, 220 - adjustmentValue1 - adjustmentValue2, 130, 30);
        add(enterInterFace);
        setLang(lang.equals("中文"));
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
            JOptionPane.showMessageDialog(null, loginTip1, loginTip2, JOptionPane.ERROR_MESSAGE);
        }
        else if(!String.valueOf(userInput3.getPassword()).equals(String.valueOf(userInput4.getPassword()))){
            JOptionPane.showMessageDialog(null, loginTip5, loginTip2, JOptionPane.ERROR_MESSAGE);
        }else{
            addAct(userInput1.getText(), String.valueOf(userInput3.getPassword()));
        }
    }

    public void addAct(String uid, String password){
        UserService userService = new UserService();
        if(userService.ifUserExist(uid)){
            JOptionPane.showMessageDialog(null, loginTip8, loginTip2, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(userService.addUser(uid, password, UserInfo.addSalt())){
            JOptionPane.showMessageDialog(null, loginTip9, loginTip6, JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            return;
        }
        JOptionPane.showMessageDialog(null, loginTip10, loginTip2, JOptionPane.ERROR_MESSAGE);
    }

    public void editVerify(JTextField userInput1, JPasswordField userInput2, JPasswordField userInput3, JPasswordField userInput4){
        if((String.valueOf(userInput2.getPassword()).equals(""))||(String.valueOf(userInput3.getPassword()).equals(""))
                ||(String.valueOf(userInput4.getPassword()).equals(""))||((userInfo == null)&&(userInput1.getText().equals("")))) {
            JOptionPane.showMessageDialog(null, loginTip1, loginTip2, JOptionPane.ERROR_MESSAGE);
        }
        else if(!String.valueOf(userInput3.getPassword()).equals(String.valueOf(userInput4.getPassword()))){
            JOptionPane.showMessageDialog(null, loginTip7, loginTip2, JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, loginTip3, loginTip2, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(userService.setUserPwd(userInfo.getUser_name(), passNew + userInfo.getUser_salt())){
            JOptionPane.showMessageDialog(null, loginTip4, loginTip6, JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            return;
        }
        JOptionPane.showMessageDialog(null, loginTip7, loginTip2, JOptionPane.ERROR_MESSAGE);
    }

    public void editAct(String uid, String passOld, String passNew){
        UserService userService = new UserService();
        userInfo = userService.getUserByIdAndPwd(uid, passOld);
        if(userInfo == null){
            JOptionPane.showMessageDialog(null, loginTip3, loginTip2, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(userService.setUserPwd(uid, passNew + userInfo.getUser_salt())){
            JOptionPane.showMessageDialog(null, loginTip4, loginTip6, JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            return;
        }
        JOptionPane.showMessageDialog(null, loginTip7, loginTip2, JOptionPane.ERROR_MESSAGE);

    }

    public void setLang(boolean zhOrEn){
        if(zhOrEn){
            if(addUser){
                setTitle("登录");
            }else {
                setTitle("重置密码");
            }
            font = new Font("微软雅黑",Font.PLAIN,10);
            userName.setText("用户名");
            userPass1.setText("旧密码");
            userPass2.setText("新注册");
            userPass3.setText("重复新密码");
            enterInterFace.setText("确认");
            loginTip1 = "一个或多个输入框为空";
            loginTip2 = "警告";
            loginTip3 = "密码或用户名错误";
            loginTip4 = "密码已修改";
            loginTip5 = "两个新密码不匹配";
            loginTip6 = "消息";
            loginTip7 = "密码修改失败";
            loginTip8 = "该用户已存在";
            loginTip9 = "用户已添加";
            loginTip10 = "添加用户失败";

        }else {
            if(addUser){
                setTitle("Register");
            }else {
                setTitle("Reset password");
            }
            font = new Font("times new roman",Font.PLAIN,10);
            userName.setText("User Name");
            userPass1.setText("Old password");
            userPass2.setText("New password");
            userPass3.setText("Repeat new password");
            enterInterFace.setText("Enter");
            loginTip1 = "one or more of the fields are empty";
            loginTip2 = "Warning";
            loginTip3 = "Password or username might be wrong";
            loginTip4 = "Password edited";
            loginTip5 = "Two new password are not match";
            loginTip6 = "Message";
            loginTip7 = "Editing password failed";
            loginTip8 = "This user has already exist";
            loginTip9 = "User added";
            loginTip10 = "Adding user failed";
        }
        userName.setFont(font);
        userPass1.setFont(font);
        userPass2.setFont(font);
        userPass3.setFont(font);
        enterInterFace.setFont(font);
    }
}

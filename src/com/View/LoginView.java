package com.View;
import com.Main;
import com.Model.UserInfo;
import com.Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.Objects;

public class LoginView extends JFrame {

    private Font font;
    private UserInfo userInfo = null;

    private JLabel userName = new JLabel();
    private JTextField userInput = new JTextField(15);
    private JLabel userPass = new JLabel();
    private JPasswordField passInput = new JPasswordField(15);
    private JButton newUser = new JButton();
    private JButton editUser = new JButton();
    private JButton enterInterFace = new JButton();
    private JLabel userLang = new JLabel();
    private JComboBox<String> comboBox = new JComboBox<>();
    private String loginTip1;
    private String loginTip2;
    private String loginTip3;


    public LoginView(){

    }

    public void addFrm(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setSize(370,200);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Login.png"));

        userName.setBounds(20,20,70,30);
        add(userName);
        userInput.setBounds(85, 20 , 100 , 30);
        add(userInput);
        userPass.setBounds(20,70, 70,30);
        add(userPass);
        passInput.setBounds(85,70,100,30);
        add(passInput);
        newUser.setBounds(20, 120, 100, 30);
        add(newUser);
        editUser.setBounds(130, 120, 100, 30);
        add(editUser);
        enterInterFace.setBounds(240, 120, 100, 30);
        add(enterInterFace);
        userLang.setBounds(200,20, 70,30);
        add(userLang);
        add(comboBox);


        Locale locale = Locale.getDefault();
        String lang = locale.getLanguage();
        setLang(lang.equals("zh"));

        userName.setFont(font);
        userPass.setFont(font);
        newUser.setFont(font);
        editUser.setFont(font);
        enterInterFace.setFont(font);
        userLang.setFont(font);
        comboBox.addItem("中文");
        comboBox.addItem("English");

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    if(comboBox.getSelectedItem()!= null) {
                        String item = comboBox.getSelectedItem().toString();
                        setLang(item.equals("中文"));
                    }
                }
            }
        });


        setVisible(true);

        enterInterFace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loginAct(String.valueOf(passInput.getPassword()), userInput.getText());
            }
        });

        editUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditView editView = new EditView();
                editView.addFrm();
            }
        });

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditView editView = new EditView(true, Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                editView.addFrm();
            }
        });

    }

    public void loginAct(String passInput, String userInput){
        if((passInput.equals(""))||(userInput.equals(""))) {
            JOptionPane.showMessageDialog(null, loginTip1, loginTip2, JOptionPane.ERROR_MESSAGE);
        }
        else{

            UserService userService = new UserService();
            userInfo = userService.getUserByIdAndPwd(userInput, passInput);
            if(userInfo == null){
                JOptionPane.showMessageDialog(null, loginTip3, loginTip2, JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.dispose();
            MainView mainView = new MainView(userInfo, Objects.requireNonNull(comboBox.getSelectedItem()).toString());
            mainView.addFrm();
        }
    }

    public void setLang(boolean zhOrEn){
        if(zhOrEn){
            comboBox.setSelectedItem("中文");
            setTitle("登录");
            font = new Font("微软雅黑",Font.PLAIN,10);
            userName.setText("用户名");
            userPass.setText("密码");
            newUser.setText("注册");
            editUser.setText("修改密码");
            enterInterFace.setText("确认");
            userLang.setText("语言");
            comboBox.setBounds(225, 20, 70, 30);
            loginTip1 = "密码或用户名不得为空";
            loginTip2 = "警告";
            loginTip3 = "密码或用户名错误";

        }else {
            comboBox.setSelectedItem("English");
            setTitle("Login");
            font = new Font("times new roman",Font.PLAIN,10);
            userName.setText("User Name");
            userPass.setText("Password");
            newUser.setText("Register");
            editUser.setText("Edit Password");
            enterInterFace.setText("Enter");
            userLang.setText("Language");
            comboBox.setBounds(265, 20, 70, 30);
            loginTip1 = "Password or username can not be empty";
            loginTip2 = "Warning";
            loginTip3 = "Password or username might be wrong";
        }
        userName.setFont(font);
        userPass.setFont(font);
        newUser.setFont(font);
        editUser.setFont(font);
        enterInterFace.setFont(font);
        userLang.setFont(font);
    }


}

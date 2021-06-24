package com.View;
import com.Model.UserInfo;
import com.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {


    private UserInfo userInfo = null;
    public LoginView(){

    }

    public void addFrm(){

        Font font = new Font("times new roman",Font.PLAIN,10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);
        setSize(370,200);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Login.png"));



        JLabel userName = new JLabel("User");
        userName.setFont(font);
        userName.setBounds(20,20,70,30);
        add(userName);

        JTextField userInput = new JTextField(15);
        userInput.setBounds(85, 20 , 100 , 30);
        add(userInput);

        JLabel userPass = new JLabel("Password");
        userPass.setFont(font);
        userPass.setBounds(20,70, 70,30);
        add(userPass);

        JPasswordField passInput = new JPasswordField(15);
        passInput.setBounds(85,70,100,30);
        add(passInput);

        JButton newUser = new JButton("New User");
        newUser.setFont(font);
        newUser.setBounds(20, 120, 100, 30);
        add(newUser);

        JButton editUser = new JButton("Edit Password");
        editUser.setFont(font);
        editUser.setBounds(130, 120, 100, 30);
        add(editUser);

        JButton enterInterFace = new JButton("Enter");
        enterInterFace.setFont(font);
        enterInterFace.setBounds(240, 120, 100, 30);
        add(enterInterFace);



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
                EditView editView = new EditView(true);
                editView.addFrm();
            }
        });

    }

    public void loginAct(String passInput, String userInput){
        if((passInput.equals(""))||(userInput.equals(""))) {
            JOptionPane.showMessageDialog(null, "Password or username can not be empty", "Warning", JOptionPane.ERROR_MESSAGE);
        }
        else{

            UserService userService = new UserService();
            userInfo = userService.getUserByIdAndPwd(userInput, passInput);
            System.out.println(userInfo);
            if(userInfo == null){
                JOptionPane.showMessageDialog(null, "Password or username may wrong", "Warning", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.dispose();
            MainView mainView = new MainView(userInfo);
            mainView.addFrm();
        }
    }


}

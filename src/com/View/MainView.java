package com.View;

import com.Model.UserInfo;
import com.UserService;
import com.View.EditView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainView extends JFrame {

    private UserService userService = new UserService();
    UserInfo userInfo = null;

    public MainView(){

    }

    public MainView(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public void addFrm(){
        Font font = new Font("times new roman",Font.PLAIN,10);
        setTitle("Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
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
        jb1.setBounds(50, 650, 100, 30);
        add(jb1);

        JButton jb2 = new JButton("Delete Item");
        jb2.setFont(font);
        jb2.setBounds(170, 650, 100, 30);
        add(jb2);

        JButton jb3 = new JButton("Refresh");
        jb3.setFont(font);
        jb3.setBounds(290, 650, 100, 30);
        add(jb3);

        JButton jb4 = new JButton("Nearly Expired");
        jb4.setFont(font);
        jb4.setBounds(410, 650, 100, 30);
        add(jb4);

        item3.setEnabled(false);

        setVisible(true);

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EditView editView = new EditView(userInfo);
                editView.addFrm();
            }
        });

    }

}

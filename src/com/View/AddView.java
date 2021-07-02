package com.View;

import com.Model.UserInfo;

import javax.swing.*;
import java.awt.*;

public class AddView extends JFrame {
    private UserInfo userInfo = null;
    public AddView() {

    }
    public AddView(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void addFrm(){
        Font font = new Font("times new roman", Font.PLAIN, 12);
        setTitle("Add");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Main.png"));
    }
}

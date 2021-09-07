package com.jingjing.window;


import com.jingjing.action.Ping;
import com.jingjing.action.search.Baidu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    public void open(){
        JFrame jFrame = new JFrame();
        jFrame.setAlwaysOnTop(true);
        GridLayout layout = new GridLayout(5, 4);
        JPanel jPanel = new JPanel(layout);

        jFrame.setSize(500,400);
        jFrame.setContentPane(jPanel);
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);

        Font font=new Font("宋体",Font.BOLD,20);

        JButton searchButton = new JButton("百度");
        jPanel.add(searchButton);
        searchButton.setFont(font);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Baidu baudu = new Baidu();
                baudu.search();
            }
        });


        JButton pingButton = new JButton("ping");
        jPanel.add(pingButton);
        pingButton.setFont(font);
        pingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ping ping = new Ping();
                ping.ping();
            }
        });


        jPanel.add(new JButton("提取文件"));
        jPanel.add(new JButton("ping3"));
        jPanel.add(new JButton("ping4"));
        jPanel.add(new JButton("ping5"));
        jPanel.add(new JButton("ping6"));
        jPanel.add(new JButton("ping7"));
        jPanel.add(new JButton("ping8"));

    }
}

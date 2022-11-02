package com.jingjing;

import com.jingjing.action.search.Baidu;
import com.jingjing.action.ExtractFile;
import com.jingjing.action.Ping;
import com.jingjing.action.search.CSDN;
import com.jingjing.window.WindowLocationListener;
import com.jingjing.window.WindowMouseListener;
import com.jingjing.window.WindowMover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoHiddenFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public static final int NORMAL = 0;			//窗体的普通状态
    public static final int CANHIDD = 1;		//窗体位于屏幕边缘,可以隐藏的状态
    public static final int HIDDEN = 2;			//窗体处于隐藏状态

    private int state = NORMAL;			//窗体的状态,让它初始化为普通状态
    private Point hiddenPoint;			//隐藏窗体时,窗体的位置
    private Point visiblePoint;			//窗体处于显示状态时的位置

    private JLabel infoLabel;				//用于显示信息的JLabel;
    public AutoHiddenFrame() {
        JPanel jPanel = new JPanel( new GridLayout(4, 4));

        setAlwaysOnTop(true);
        setResizable(false);
        setContentPane(jPanel);	//替换掉原来的ContentPane,换上一个带有Insets的,至于为什么去看WindowMouseListener类
        //infoLabel = new JLabel();
        //add(infoLabel,BorderLayout.SOUTH);
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        Font font=new Font("宋体",Font.PLAIN,15);

        JButton searchButton = new JButton("百度");
        jPanel.add(searchButton);
        searchButton.setFont(font);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Baidu baidu = new Baidu();
                baidu.search();
            }
        });

        JButton csdnButton = new JButton("CSDN");
        jPanel.add(csdnButton);
        csdnButton.setFont(font);
        csdnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CSDN csdn = new CSDN();
                csdn.search();
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


        JButton extractFileButton = new JButton("提取文件");
        extractFileButton.setFont(font);
        jPanel.add(extractFileButton);
        extractFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExtractFile extractFile = new ExtractFile();
                extractFile.extract();
            }
        });


        JButton javaTest = new JButton("JavaTest");
        extractFileButton.setFont(font);


        jPanel.add(new JButton("ping4"));
        jPanel.add(new JButton("ping5"));
        jPanel.add(new JButton("ping6"));
        jPanel.add(new JButton("ping7"));
        jPanel.add(new JButton("ping8"));

        new WindowLocationListener(this);
        new WindowMouseListener(this);
        WindowLocationListener.checkAutoHiddenState(this);//刚出来就检查一下窗体的位置
    }


    /**
     * @param newState 新的状态
     * 一定要是此类中定义的3中状态之一
     */
    public void setStates(int newState)
    {
        if(newState == NORMAL || newState == CANHIDD || newState == HIDDEN)
        {
            state = newState;
        }
    }

    /*
     * 返回状态,注意此方法和setStates方法区别与JFrame中的setState()和getState()方法
     */
    public int getStates()
    {
        return state;
    }
    /*
     * 设置要显示时窗体的坐标
     */
    public void setVisiblePoint(Point point)
    {
        visiblePoint = point;
    }
    /*
     * 设置要隐藏是窗体的坐标
     */
    public void setHiddenPoint(Point point)
    {
        hiddenPoint = point;
    }


    public void moveToVisible()
    {
        if(visiblePoint!=null)
        {
            //System.out.println("visible");
            WindowMover.moveToPoint(this, visiblePoint);
            setStates(CANHIDD);
        }
    }

    public void moveToHidden()
    {
        if(hiddenPoint!=null)
        {
            //System.out.println("hide");
            WindowMover.moveToPoint(this, hiddenPoint);
            setStates(HIDDEN);
        }
    }

    public void showInfo(String info)
    {
        infoLabel.setText(info);
    }
    public void clearInfo()
    {
        //infoLabel.setText("");
    }
}



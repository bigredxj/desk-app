package com.jingjing.window;

import com.jingjing.AutoHiddenFrame;

import java.awt.Container;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.SwingUtilities;

public class WindowMouseListener extends MouseAdapter implements WindowFocusListener {

    private AutoHiddenFrame frame;

    Container container;

    public WindowMouseListener(AutoHiddenFrame a) {
        frame = a;
        container = frame.getContentPane();
        container.addMouseListener(this);
        // 注册鼠标侦听器到ContentPane上,因为我们可以加大它的Insets以提高鼠标进入和离开的灵敏度
        frame.addWindowFocusListener(this);

        new Thread(){
             public void run(){
                while(true){
                    try{
                        Thread.sleep(3000);
                        checkMousePoint();
                    }catch (Exception e){

                    }
                }
            }
        }.start();
    }

    public void mouseEntered(MouseEvent e) {
        //System.out.println("鼠标进入1");
        // 当鼠标进入,就显示窗体
        if (frame.getStates() == AutoHiddenFrame.HIDDEN) {
            //System.out.println("鼠标进2");
            frame.moveToVisible();
        } else {
            frame.clearInfo();
        }

    }

 /*   public void mouseExited(MouseEvent e) {
        System.out.println("鼠标离开1");
        System.out.println(frame.getStates());
        // 当鼠标离开,启动计时器
        if (frame.getStates() == AutoHiddenFrame.CANHIDD) {

            if (!container.contains(e.getPoint())) {
                //frame.showInfo(timer.getDelay() / 1000 + "秒后自动隐藏窗口!");
                // System.out.println("Mouse has exited the Dialog!");
                frame.moveToHidden();
            }

        }

    }*/

    public boolean containsPoint(Point point,int x1,int y1,int x2,int y2){
        boolean result = false;
        if(point.x>=x1 && point.y>=y1 && point.x<=x2 && point.y<=y2){
            return true;
        }
        return result;
    }

    public void checkMousePoint() {
        // 计时器到期,检查鼠标是不是还在此窗体里面,不再的话,再开始隐藏
        Point p = MouseInfo.getPointerInfo().getLocation();
        int y =p.y;
        //System.out.println(p);
        //System.out.println(frame.getStates());
        SwingUtilities.convertPointFromScreen(p, container);
        p.y=y;
       // System.out.println(p);
        if (!container.contains(p)
                && frame.getStates() == AutoHiddenFrame.CANHIDD) {
            frame.moveToHidden();
        } else {

        }
    }

    public void windowGainedFocus(WindowEvent e) {
        // 得到焦点检查鼠标是不是在窗体上
        //System.out.println("windowGainedFocus-1");
        Point p = MouseInfo.getPointerInfo().getLocation();
        if (container.contains(p) && frame.getStates() == AutoHiddenFrame.HIDDEN) {
            //System.out.println("windowGainedFocus-2");
            frame.moveToVisible();
        }
    }

    public void windowLostFocus(WindowEvent e) {
        // 失去焦点,启动计时器
        /*if (frame.getStates() == AutoHiddenFrame.CANHIDD) {
           // frame.showInfo("2秒后自动隐藏窗口!");
            frame.moveToHidden();
        }*/
    }
}
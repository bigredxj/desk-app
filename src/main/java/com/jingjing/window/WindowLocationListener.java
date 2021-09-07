package com.jingjing.window;

import com.jingjing.AutoHiddenFrame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class WindowLocationListener extends ComponentAdapter {
    public static final int HIDDEN_BOUND = 3; // 当窗体进入到屏幕边缘3像素以内就可以隐藏

    public static final int VISIBLE_BOUND = 10; // 当窗体隐藏后要有5像素的部分露出来,不能完全隐藏
    public static final int VISIBLE_HIDEN_BOUND = 0;

    AutoHiddenFrame frame;

    public WindowLocationListener(AutoHiddenFrame a) {
        frame = a;
        frame.addComponentListener(this);
    }

    public void componentMoved(ComponentEvent e) {
        checkAutoHiddenState(frame);
        // 当窗体移动就调用检查方法;
    }

    public static void checkAutoHiddenState(AutoHiddenFrame frame) {
        // 当窗体状态不是隐藏的,再进行检查
        if (frame.getStates() != AutoHiddenFrame.HIDDEN) {
            // 首先获得屏幕的大小和窗体的坐标
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Point hiddenPoint = frame.getLocation();

            Point visiblePoint = null;
            boolean canhidden = false;
            // 当窗体位于左边边缘
            if (hiddenPoint.x <= HIDDEN_BOUND) {
                /*System.out.println(hiddenPoint.x);
                hiddenPoint.move(VISIBLE_BOUND - frame.getWidth(),
                        hiddenPoint.y);
                visiblePoint = new Point(0, hiddenPoint.y);
                canhidden = true;*/
            }
            // 当窗体位于上边
            else if (hiddenPoint.y <= VISIBLE_HIDEN_BOUND) {
                //System.out.println(hiddenPoint.y);
                hiddenPoint.setLocation(hiddenPoint.x, VISIBLE_BOUND
                        - frame.getHeight());
                visiblePoint = new Point(hiddenPoint.x, 0);
                canhidden = true;
            }
            // 当窗体位于右边
            else if (hiddenPoint.x + frame.getWidth() >= screenSize.width
                    - HIDDEN_BOUND) {
                /*System.out.println(hiddenPoint.x);
                hiddenPoint.setLocation(screenSize.width - VISIBLE_BOUND,
                        hiddenPoint.y);
                visiblePoint = new Point(screenSize.width - frame.getWidth(),
                        hiddenPoint.y);
                canhidden = true;*/
            }
            if (canhidden) {
                // 如果符合以上几种情况的一种就可以隐藏
                frame.setVisiblePoint(visiblePoint);
                frame.setHiddenPoint(hiddenPoint);
                frame.setStates(AutoHiddenFrame.CANHIDD);
               // frame.showInfo("进入可隐藏区域!");
            } else {
                // 如果不可以隐藏,那就是离开了边缘了
                if (frame.getStates() == AutoHiddenFrame.CANHIDD) {
                    //frame.showInfo("离开可应藏区域!");
                }
                frame.setVisiblePoint(frame.getLocation());
                frame.setStates(AutoHiddenFrame.NORMAL);
            }
        }
    }
}

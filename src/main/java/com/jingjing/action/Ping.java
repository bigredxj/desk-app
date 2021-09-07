package com.jingjing.action;


import com.jingjing.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ping {
    public void ping(){
        try {
            String ip = CommonUtil.getClipboardString();
            String cmd = "cmd /c start cmd.exe /k   ping " + ip;
            log.info(cmd);
            Process process = Runtime.getRuntime().exec(cmd);

        }catch (Exception e){
            log.error("",e);
        }
    }
}

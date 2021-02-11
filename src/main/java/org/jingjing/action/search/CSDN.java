package org.jingjing.action.search;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.jingjing.util.CommonUtil;
import org.jingjing.util.PropertiesUtil;

@Slf4j
public class CSDN{

    public void search() {
        try {
            String browser = PropertiesUtil.getProperty("browser");
            log.info("browser is: " + browser);
            String domain = PropertiesUtil.getProperty("csdn.domain");
            String browserPath = PropertiesUtil.getProperty(browser.toLowerCase()+".path").replaceAll(" ","\" \"");
            String context = CommonUtil.getClipboardString();

            if(StrUtil.isEmpty(context)){
                context = "java";
            }
            log.info("search context is:" + context);
            String cmd ="cmd /c start "+ browserPath +" "+domain+"/so/search/all?q="+"\""+context +"\"";
            log.info(cmd);
            Runtime.getRuntime().exec(cmd);
        }catch (Exception e){
            log.error("",e);
        }
    }
}

package com.jingjing.action;

import cn.hutool.core.util.StrUtil;
import com.jingjing.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class ExtractFile {
    public void extract(){
        try {
            String path = CommonUtil.getClipboardString();
            log.info("path is: "+path);
            String extractedPath =null;
            if(StrUtil.isNotEmpty(path)){
                File file = new File(path.trim());
                if(file.isDirectory()){
                    extractedPath = getUniqueDirPath(path + File.separator + "extract");
                    new File(extractedPath).mkdir();
                    moveFile(file,extractedPath);
                }else {
                    log.error("path is not a directory");
                }
            }else {
                log.error("path is empty");
            }

        }catch (Exception e){
            log.error("",e);
        }
    }

    public String getUniqueDirPath(String path){
        String uniquePath = null;
        String tmpPath = path;
        if(StrUtil.isNotEmpty(path)){
            for(int i=0;i<Integer.MAX_VALUE;i++){
                if(i!=0){
                    tmpPath = path  + "-"+i;
                }
                if(!new File(tmpPath).exists()){
                    uniquePath = tmpPath;
                    break;
                }
            }
        }else {
            log.error("path is empty");
        }
        return uniquePath;
    }

    public String getUniqueFilePath(String path){
        String uniquePath = null;
        String tmpPath = path;
        if(StrUtil.isNotEmpty(path)){
            for(int i=0;i<Integer.MAX_VALUE;i++){
                if(i!=0){
                    if(new File(path).getName().contains(".")){
                        int index = path.lastIndexOf(".");
                        tmpPath = path.substring(0,index)+"-"+i+path.substring(index);
                    }else {
                        tmpPath = path  + "-"+i;
                    }

                }
                if(!new File(tmpPath).exists()){
                    uniquePath = tmpPath;
                    break;
                }
            }
        }else {
            log.error("path is empty");
        }
        return uniquePath;
    }

    public void moveFile(File file,String targetPath){
        //log.info(file.getAbsolutePath());
        if(!file.getAbsolutePath().equals(targetPath)){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File f:files){
                    moveFile(f,targetPath);
                }
            }else {
                String filePath = getUniqueFilePath(targetPath+File.separator + file.getName());
                //log.info("******"+filePath);

                file.renameTo(new File(filePath));
            }
        }
    }
}

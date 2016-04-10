package com.iamaction.tools.testdo.inter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huuuuxin
 */
public class FileUtil {
    private BufferedWriter bw;
    private OutputStream os;
    private OutputStreamWriter osw;
    
    public static StringBuilder read(String fileName){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        FileReader filer = null;
        try{
            File file = new File(fileName);
            filer = new FileReader(file);
            reader= new BufferedReader(filer);
            String lineCons = null;
            while((lineCons=reader.readLine())!=null){
                sb.append(lineCons);
                lineCons = null;
            }
        }catch(Exception e){
                e.printStackTrace();
        }finally{
                close(reader,filer);
        }
        return sb;
    }
    
    public static List<String> readFile2List(String fileName){
        List<String> sbList = new  ArrayList<String>();
        BufferedReader reader = null;
        FileReader filer = null;
        try{
            File file = new File(fileName);
            filer = new FileReader(file);
            reader= new BufferedReader(filer);
            String lineCons = null;
            while((lineCons=reader.readLine())!=null){
                sbList.add(lineCons);
                lineCons = null;
            }
        }catch(Exception e){
                e.printStackTrace();
        }finally{
                close(reader,filer);
        }
        return sbList;
    }
    
    public static StringBuilder readStream(String fileName){
        StringBuilder sb = new StringBuilder();
        FileInputStream filer = null;
        try{
                filer = new FileInputStream(fileName);
                byte[] rb = new byte[1024];
                int k = 0;
                while((k=filer.read(rb)) != -1 ){
                        sb.append(new String(rb,0,k,"UTF-8"));
                }
        }catch(Exception e){
                e.printStackTrace();
        }finally{
                close(filer);
        }
        return sb;
    }
    
    
    public static boolean createFile(String fileUrl){
        File pidFile = new File(fileUrl);
        boolean end = false;
        if(pidFile.exists()){
                end = false;
        }else{
                try {
                        pidFile.createNewFile();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                end = true;
        }
        return end;
    }
    
    
    public static boolean isExite(String fileUrl){
        File pidFile = new File(fileUrl);
        boolean end = false;
        if(pidFile.exists()){
                end = true;
        }
        return end;
    }
    
    
    public void getFileWriter(String fileName,String charsetName,boolean isAppend) throws Exception{
        os= new FileOutputStream(fileName,isAppend);
        osw = new OutputStreamWriter(os,charsetName);
        bw = new BufferedWriter(osw);
    }
        
        
    public void writer(String fileContent)throws Exception{
        bw.write(fileContent);
        bw.flush();
    }
    
    public static void updateFileContent(String fileName,String fileContent)throws Exception{
        FileUtil fp = new FileUtil();
        fp.getFileWriter(fileName,"UTF-8",false);
        fp.bw.write(fileContent);
        fp.bw.flush();
        fp.closeFileWriter();
    }
    
    public void closeFileWriter()throws Exception{
        if(bw != null){
                bw.close();
                bw = null;
        }
        if(osw != null){
                osw.close();
                osw = null;
        }
        if(os != null){
                os.close();
                os = null;
        }
    }

    public static void close(BufferedReader reader,FileReader filer){
        if(reader != null){
            try {
                    reader.close();
            } catch (Exception e) {
                    e.printStackTrace();
            }
            reader = null;
        }
        if(filer != null){
            try {
                    filer.close();
            } catch (Exception e) {
                    e.printStackTrace();
            }
            filer = null;
        }
    }
     
    public static void close(FileInputStream  filer){
        if(filer != null){
            try {
                    filer.close();
            } catch (Exception e) {
                    e.printStackTrace();
            }
            filer = null;
        }
    }
}

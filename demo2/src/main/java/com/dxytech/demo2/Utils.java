package com.dxytech.demo2;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 用绝对路径存储
 * Created by Administrator on 2015/7/10.
 */
public class Utils {
    public static boolean saveUserInfo(String number,String password){

        //写出文件
        try {
            String path = "data/data/com.dxytech.demo2/data.txt";
           // String path2 = path + "data.txt";
            FileOutputStream fos = new FileOutputStream(path);

            String data = number + "##" + password;

            fos.write(data.getBytes());
            fos.flush();
            fos.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Map<String,String> getUserInfo(){
        //读入文件
        try {
            String path = "data/data/com.dxytech.demo2/data.txt";
            //String path2 = path + "data.txt";

            FileInputStream fis = new FileInputStream(path);
            //字符流对象
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();

            if(TextUtils.isEmpty(line)){
                String [] split = line.split("##");

                Map<String,String> userInfoMap = new HashMap<String,String>();
                userInfoMap.put("number",split[0]);
                userInfoMap.put("password",split[1]);

                return userInfoMap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}

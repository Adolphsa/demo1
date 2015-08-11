package com.dxytech.demo2;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 存储在手机的SDCard 233333
 * Created by Administrator on 2015/7/13.
 */
public class UtilsOfSdcard {
    //保存用户数据
    public static Boolean saveUserInfo(Context context,String number,String password){

        try {
            //判断当前手机是否有SD卡
            String state = Environment.getExternalStorageState();

            if(!Environment.MEDIA_MOUNTED.equals(state)){
                return false;
            }
            File file = Environment.getExternalStorageDirectory();
            File path = new File(file,"shuju.txt");

            FileOutputStream fos = new FileOutputStream(path);

            //用##分割账号和密码
            String data = number + "##" + password;
            //写数据
            fos.write(data.getBytes());
            fos.flush();
            fos.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //到SD卡得到用户信息
    public static Map<String,String> getUserInfo(Context context){
        try {

            File file = Environment.getExternalStorageDirectory();
            File path = new File(file,"shuju.txt");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path)));

            String line = br.readLine();

            if (line != null){
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

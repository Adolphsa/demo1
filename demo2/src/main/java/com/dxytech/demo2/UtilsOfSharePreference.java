package com.dxytech.demo2;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/13.
 */
public class UtilsOfSharePreference {
    //保存用户信息
    public static Boolean saveUserInfo(Context context,String number,String password){
        try {
            SharedPreferences sharedPre = context.getSharedPreferences("data3", Context.MODE_PRIVATE);
            //获得一个编辑对象
            SharedPreferences.Editor editor = sharedPre.edit();
            //存数据
            editor.putString("number", number);
            editor.putString("password", password);
            //提交
            editor.commit();

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //获取用户信息
    public static Map<String,String> getUserInfo(Context context){

        SharedPreferences sharedPre = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String number = sharedPre.getString("number", null);
        String password = sharedPre.getString("password", null);

        if(!TextUtils.isEmpty(number) && !TextUtils.isEmpty(password)){

            Map<String,String> userInfoMap = new HashMap<String,String>();
            userInfoMap.put("number",number);
            userInfoMap.put("Password",password);

            return userInfoMap;
        }

        return null;
    }
}

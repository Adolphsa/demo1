package com.dxytech.demo2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/10.
 */
public class login extends Activity implements View.OnClickListener{

    private EditText et_number;
    private EditText et_password;
    private Button bt_login;
    private CheckBox cb_remerber_pwd;

    private TextView tv_sdState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        et_number = (EditText) findViewById(R.id.et_number);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_remerber_pwd = (CheckBox)findViewById(R.id.cb_remerber_pwd);
        bt_login = (Button)findViewById(R.id.bt_login);

        tv_sdState = (TextView)findViewById(R.id.tv_sdState);


        bt_login.setOnClickListener(this);

//        if (cb_remerber_pwd.isChecked()) {
//            SharedPreferences sharedPre = getSharedPreferences("config", 0);
//            String number = sharedPre.getString("et_number", "");
//            String password = sharedPre.getString("et_password", "");
//
//            et_number.setText(number);
//            et_password.setText(password);
//        }else{
//            Toast.makeText(this,"请手动输入账号和密码",Toast.LENGTH_SHORT).show();
//        }

        //回显数据
        Map<String,String> userInfoMap = UtilsOfSdcard.getUserInfo(this);
        if (userInfoMap != null){
            et_number.setText(userInfoMap.get("number"));
            et_password.setText(userInfoMap.get("password"));
        }

        //获得SD卡内存状态
        File sdcardFileDir = Environment.getExternalStorageDirectory();
        String sdcardMemory = UtilsOfStorageState.getMemory(this,sdcardFileDir);

        //获得手机内部存储空间的状态
        File dataFileDir = Environment.getDataDirectory();
        String dataMemory = UtilsOfStorageState.getMemory(this,dataFileDir);

        tv_sdState.setText("sd卡：" + sdcardMemory + "\n手机内存:" + dataMemory);

        //写数据
        //私用文件
        writeToLocal("private", Context.MODE_PRIVATE);
        //可读文件
        writeToLocal("readable", Context.MODE_WORLD_READABLE);
        //可写文件
        writeToLocal("writeable", Context.MODE_WORLD_WRITEABLE);
        //可读可写
        writeToLocal("readabe_writeable", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
    }

    @Override
    public void onClick(View v) {
        String number = et_number.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"请正确输入账号和密码",Toast.LENGTH_SHORT).show();
            return;
        }

//        if (cb_remerber_pwd.isChecked()){
//            Log.d("TGA", "记住密码");
//
//            SharedPreferences sharedPre =getSharedPreferences("config",0);
//            SharedPreferences.Editor editor = sharedPre.edit();
//            editor.putString("et_number", number);
//            editor.putString("et_password",password);
//            editor.commit();
//            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
//
//
//
//        }
        if (cb_remerber_pwd.isChecked()){
            Log.i("TGA","记住密码" + number + "," + password);

            Boolean isSuccess = UtilsOfSdcard.saveUserInfo(this,number,password);
            if (isSuccess){
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
            }
        }

        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    public  void writeToLocal(String fileName,int mode){
        try {
            FileOutputStream fos = openFileOutput(fileName,mode);
            fos.write(("第一个程序写的数据" + fileName).getBytes());

            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

package com.dxytech.demo2;

import android.app.Activity;

import java.io.FileOutputStream;

/**
 * Created by Administrator on 2015/7/13.
 */
public class RWFile extends Activity{
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

package com.dxytech.demo2;

import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by Administrator on 2015/7/9.
 */
public class test3 extends InstrumentationTestCase {

    public void test(){
        int a = 2;
        int b= 2;

        assertEquals(a,b);

        Log.d("tga", "测试程度...");
    }
}

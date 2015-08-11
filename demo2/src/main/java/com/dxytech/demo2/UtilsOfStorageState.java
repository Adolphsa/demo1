package com.dxytech.demo2;

import android.content.Context;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Created by Administrator on 2015/7/13.
 */
public class UtilsOfStorageState {
    public static String getMemory(Context context,File path){

        //获得一个磁盘状态对象
        StatFs stat = new StatFs(path.getPath());
        //获得一个扇区的大小
        int blockSize = stat.getBlockSize();
        //获得扇区的总数
        int totalBlocks = stat.getBlockCount();
        //获得可用扇区的数量
        int availableBlocks = stat.getAvailableBlocks();

        //总空间
        String totalMemory = android.text.format.Formatter.formatFileSize(context,blockSize*totalBlocks);
        //可用空间
        String availableMemory = Formatter.formatFileSize(context,availableBlocks * blockSize);

        return "总空间" + totalMemory + "\n可用空间" + availableMemory;
    }
}

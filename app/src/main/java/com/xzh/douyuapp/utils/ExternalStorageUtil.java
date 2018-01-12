package com.xzh.douyuapp.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ExternalStorageUtil {

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    public static String getExternalStoragePath() {
        if (isExternalStorageWritable())
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        else
            return null;
    }


    public static String getExternalDownloadPath() {
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }


    public static boolean write(String fileName, String content) {
        return write("/", fileName, content);
    }


    public static boolean writeBytes(String fileName, byte[] bytes) {
        return writeBytes("/", fileName, bytes);
    }


    public static boolean write(String path, String fileName, String content) {
        return writeBytes(path, fileName, content.getBytes());
    }


    public static boolean writeBytes(String path, String fileName, byte bytes[]) {
        boolean flag = false;
        if (!path.equals("/")) {
            File dir = new File(getExternalStoragePath() + path);
            if (!dir.exists()) {
                if (!(dir.mkdir() || dir.isDirectory())) {
                    // 文件目录创建失败或者不是一个目录
                    return false;
                }
            }
        }
        File file = new File(getExternalStoragePath() + path + fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, false);
            fos.write(bytes);
            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return flag;
    }


    public static byte[] readBytes(String fileName) {
        return readBytes("/", fileName);
    }


    public static byte[] readBytes(String path, String fileName) {
        File file = new File(getExternalStoragePath() + path + fileName);
        if (!file.isFile()) {
            return null;
        } else {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int length = fis.available();
                byte[] buffer = new byte[length];
                fis.read(buffer);
                return buffer;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

    }


    public static String read(String fileName) {
        return read("/", fileName);
    }


    public static String read(String path, String fileName) {
        try {
            byte[] readBytes = readBytes(path, fileName);
            if (readBytes == null) {
                return null;
            }
            return new String(readBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean delete(String fileName) {
        return delete("/", fileName);
    }


    public static boolean delete(String path, String fileName) {
        File file = new File(getExternalStoragePath() + path + fileName);
        if (file.exists())
            return file.delete();
        else
            return true;
    }


    public static double getSDcardAvailableSize() {

        long blocksize = 0;
        long availbleblocks = 0;

        if (isExternalStorageWritable()) {
            StatFs stat = new StatFs(getExternalStoragePath());

            if(AndroidVersionUtil.isKitkat()){
                blocksize = stat.getBlockSizeLong();
                availbleblocks = stat.getAvailableBlocksLong();
            }else{
                blocksize = stat.getBlockSize();
                availbleblocks = stat.getAvailableBlocks();
            }
        }

        return ((availbleblocks * blocksize * 1.0) / 1024 / 1024);
    }

    /**
     * 判断是否存在有相应的文件
     * @param absolutePath 文件的绝对路径
     * @return
     */
    public static boolean fileIsExists(String absolutePath){
        try{
            File f = new File(absolutePath);

            if(!f.exists()){
                return false;
            }

        }catch (Exception e) {
            return false;
        }
        return true;
    }



}

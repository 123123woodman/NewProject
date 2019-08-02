package wangjie.com.newproject.test;

import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2018/5/9.
 * 308278
 * 307258
 */

public class ReadBmp {
    private static byte[] byy;

    public static byte[] readBmp() {

        try {
            File file = new File(getReadFilePath());
            FileInputStream is = new FileInputStream(file);
            byte[] bytes = new byte[is.available()];
            int readedlen = 0;
            int available = is.available();
            while (readedlen < is.available()) {
                readedlen += is.read(bytes);
            }
            return cutBmp(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取文件路径
    public static String getReadFilePath() {

        String savePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            savePath = Environment.getExternalStorageDirectory().toString() + File.separator + "Android" + File.separator + "data";
        }
        String savePathDir = savePath + File.separator + "com.tcptest.hnlockfc" + File.separator + "test.bmp";
        return savePathDir;
    }

    //获取文件路径
    public static String getWriteFilePath() {
        String savePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            savePath = Environment.getExternalStorageDirectory().toString() + File.separator + "Android" + File.separator + "data";
        }
        File savePathDir = new File(savePath + File.separator + "com.tcptest.hnlockfc" + File.separator + "yjw.bmp");
        if (!savePathDir.exists()) {
            // 先得到文件的上级目录，并创建上级目录，在创建文件
            savePathDir.getParentFile().mkdir();
            try {
                savePathDir.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return savePathDir.toString();
    }

    //将字节数组转换成16进制输出
    public static String bytes2hex03(byte[] bytes) {
        final String HEX = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            if (i == 13) {
                sb.append(" 位图文件头 ");
            } else if (i == 53){
                sb.append(" 位图信息头 ");
            } else {
                sb.append(" ");
            }

            sb.append(HEX.charAt((bytes[i] >> 4) & 0x0F));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(bytes[i] & 0x0F));
        }
        return sb.toString();
    }

    public static byte[] getLastBytes() {

        byte[] bytes = readBmp();

        return bytes;
    }

    public static void convert8bit() {

        byte[] data = getLastBytes();

        byte[] bmpData = BmpTest.getBmpHead(data, data.length, 640, -480);

        OutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            os = new FileOutputStream(getWriteFilePath());
            bos = new BufferedOutputStream(os);
            bos.write(bmpData);
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //截取数组
    private static  byte[] cutBmp(byte[] bytes) {
        byte[] by = new byte[bytes.length - 58];
        System.arraycopy(bytes, 58, by, 0, by.length);
        return by;
    }

    //拼接
    private static byte[] concat(byte[] a, byte[] b, int length) {
        if (length == 0) {
            byte[] c = new byte[a.length + b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, b.length);
            return c;
        } else {
            byte[] c = new byte[a.length + length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, length);
            return c;
        }
        //return c;
    }
}

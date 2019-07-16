package wangjie.com.newproject.test;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wangjie.com.library.utils.DebugInfo;

/**
 * Created by Administrator on 2018/4/28.
 */

public class TcpTest {

    Socket socket = null;
    private boolean initSocket()
    {
        try
        {
            this.socket = new Socket();
            SocketAddress remoteAddr = new InetSocketAddress("192.168.1.185",15001);
            this.socket.setSoTimeout(15000);
            this.socket.setSoLinger(true, 0);
            this.socket.connect(remoteAddr,5000);
            return true;
        }catch (SocketTimeoutException e)
        {
            //logger.info(ip+"网络不通");
        }catch (ConnectException e)
        {
            //logger.info(ip+"网络不通");
        }catch (Exception e)
        {
            //logger.info(e.toString());
        }
        return false;
    }


    /**
     * 1010 获取注册成功后的注册信息
     * @param id
     * @param EYE_TYPE
     * @return
     */
    public Map<String,Object> getEnrollInfo(String id, String EYE_TYPE) throws Exception
    {
        Map<String,Object> rsMap = new HashMap<String,Object>();
        JSONObject jo = new JSONObject();
        jo.put("user_id", id);
        jo.put("eye_type", EYE_TYPE);
        boolean net = initSocket();
        if(!net)
        {
            jo = new JSONObject();
            jo.put("net", false);
            return jo;
        }
        OutputStream os = null;
        InputStream is = null;
        try
        {
            os = this.socket.getOutputStream();
            is = this.socket.getInputStream();
            List<byte[]> btsList = PackUtil.packing(jo.toJSONString().getBytes(), 1010, 0, "0000");
            for (byte[] bs : btsList)
            {
                os.write(bs);
                os.flush();
            }
            jo.clear();

            byte[] head = new byte[21];
            is.read(head, 0, 21);
            jo = PackUtil.analyHead(head);

            int length = jo.getIntValue("length");
            int flow = jo.getIntValue("flow");
            String error = jo.getString("error");

            if(ErrorCode.SUCCESS.equals(error))
            {
                //先处理第一个包
                byte[] body = new byte[length-21];
                is.read(body, 0, length-21);
                String json = new String(body,"UTF-8");
                jo = JSON.parseObject(json);
                rsMap.put("result", jo);//将结果返回给rsmap

                long file_len = jo.getLongValue("file_len");//文件长度
                String md5 = jo.getString("md5").toUpperCase();

                byte[] mdbts = new byte[(int)file_len];
                int copyindex = 0;
                //读取文件
                while(flow==1 && ErrorCode.SUCCESS.equals(error))
                {
                    int readedlen = 0;
                    while(readedlen<21)
                    {
                        int w = is.read(head,readedlen,21-readedlen);
                        readedlen += w;
                        Log.i("OOOO", "w------------->" + w);
                    }
                    JSONObject joh = PackUtil.analyHead(head);

                    length = joh.getIntValue("length");
                    flow = joh.getIntValue("flow");
                    error = joh.getString("error");
                    body = new byte[length-21];
                    readedlen = 0;
                    while(readedlen<=length-21)
                    {
                        int q = is.read(body, readedlen, length-21-readedlen);//读出socket的文件包体
                        readedlen += q;
                        Log.i("OOOO", new DebugInfo() + "" + readedlen + "|" + length + "|" + q);
                        Thread.sleep(1000);
                    }
                    Log.i("OOOO", new DebugInfo() + "----->" + readedlen);
                    System.arraycopy(body, 0, mdbts, copyindex, body.length);
                    copyindex += body.length;
                    //fos.write(body);//写入文件
                    //fos.flush();
                }
                //File file = File.createTempFile(id+"_mode", "mode");
                String filePath = getFilePath();
//                FileOutputStream fos = new FileOutputStream(file);
//                fos.write(mdbts, 1024, mdbts.length - 1024);
                byte[] bytes = new byte[mdbts.length - 1024];
                System.arraycopy(mdbts, 1024, bytes, 0, bytes.length);
                convert8bit(bytes, bytes.length, 360, -360, filePath);
                //rawTobmp(bytes, 360, 360, filePath);
                //convert24bit(bytes, 360, 360, filePath);
                //fos.write(mdbts);
//                fos.flush();
//                fos.close();
//                long new_file_len = file.length();
               // String new_md5 = FileMD5.MD5(file).toUpperCase();
//                JSONObject jos = new JSONObject();
//                jos.put("file_len", new_file_len+"");
//                jos.put("dfile_len", file_len+"");
//                jos.put("md5", new_md5);
//                jos.put("dmd5", md5);
//                if(file_len>0&&new_file_len==file_len&&new_md5.equals(md5))
//                {
//                    jos.put("rs", "OK");
//                    Log.i("RRRR", new DebugInfo() + "OK");
//                }else
//                {
//                    jos.put("rs", "ERROR");
//                    Log.i("RRRR", new DebugInfo() + "ERROR");
//                }
//                //logger.debug("-->>获取注册完成后的注册信息::"+jos);
//                rsMap.put("fileInfo", jos);
//                rsMap.put("md", mdbts);
            }
            return rsMap;
        } catch (Exception e)
        {
            throw e;
        } finally {
            this.freeIO(is, os);
            Log.i("OOOO", new DebugInfo() + "" + socket.isBound());
            Log.i("OOOO", new DebugInfo() + "" + socket.isClosed());
            Log.i("OOOO", new DebugInfo() + "" + socket.isConnected());
            Log.i("OOOO", new DebugInfo() + "" + socket.isInputShutdown());
            Log.i("OOOO", new DebugInfo() + "" + socket.isOutputShutdown());
        }
    }

    /**
     * 释放io操作
     * @author Tian
     * @date 2015-8-17
     * @param is
     * @param os
     */
    private void freeIO(InputStream is,OutputStream os) {
        try {
            if (is != null)
                is.close();

            if (os != null)
                os.close();

            if (socket != null)
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();

        } catch (IOException e) {

            //logger.warn("操作设备释放InputStream错误",e);

        }
    }

    //获取文件路径
    public String getFilePath() {
        String savePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            savePath = Environment.getExternalStorageDirectory().toString() + File.separator + "Android" + File.separator + "data";
        }
        File savePathDir = new File(savePath + File.separator + "com.tcptest.hnlockfc" + File.separator + "iris6.bmp");
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
    public String bytes2hex03(byte[] bytes) {
        final String HEX = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(" ");
            sb.append(HEX.charAt((b >> 4) & 0x0F));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0F));
        }
        return sb.toString();
    }

    public Bitmap convert24bit(byte[] data, int width, int height, String localPath) {
        String HEX = "0123456789ABCDEF";
        byte[] Bits = new byte[data.length * 4]; //RGBA 数组
        int i;
        // data.length / 3 表示 3位为一组
        for (i = 0; i < data.length / 3; i++) {
            // 原理：24位是有彩色的，所以要复制3位，最后一位Alpha = 0xff;
            Bits[i * 4] = data[i * 3];
            Bits[i * 4 + 1] = data[i * 3 + 1];
            Bits[i * 4 + 2] = data[i * 3 + 2];
            Bits[i * 4 + 3] = -1;
        }
        // Bitmap.Config.ARGB_8888 表示：图像模式为8位
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmp.copyPixelsFromBuffer(ByteBuffer.wrap(Bits));

        OutputStream os = null;
        try {
            os = new FileOutputStream(localPath);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            boolean b = false;
            if (bmp != null) {
                b = bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmp;
    }


//    @TargetApi(26)
//    public static Bitmap convert8bit(byte[] data, int width, int height, String localPath) {
//        byte[] Bits = new byte[data.length * 4]; //RGBA 数组
//        int i;
//        for (i = 0; i < data.length; i++) {
//            if (1024 < i && i < 2048) {
//                Bits[i * 4] = (byte) 0x00;
//                Bits[i * 4 + 1] = (byte)0xFF;
//                Bits[i * 4 + 2] = (byte)0x00;
//                //Bits[i * 4] = Bits[i * 4 + 1] = Bits[i * 4 + 2] = (byte) 0x99;
//                Bits[i * 4 + 3] = -1; //0xff
//            } else {
//                Bits[i * 4] = Bits[i * 4 + 1] = Bits[i * 4 + 2] = data[i];
//                Bits[i * 4 + 3] = -1; //0xff
//            }
//        }
//
//        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGBA_F16);
//
//        bmp.copyPixelsFromBuffer(ByteBuffer.wrap(data));
//
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(localPath);
//            BufferedOutputStream bos = new BufferedOutputStream(os);
//            boolean b = false;
//            if (bmp != null) {
//                b = bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return bmp;
//    }

    public static void convert8bit(byte[] data, int bmpSize, int width, int height, String localPath) {

        byte[] bmpData = BmpTest.getBmpHead(data, bmpSize, width, height);

        OutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            os = new FileOutputStream(localPath);
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

    public void rawTobmp(byte[] data, int width, int height, String localPath) {
        // Bitmap.Config.ARGB_8888 表示：图像模式为8位
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Buffer buffer = ByteBuffer.wrap(data);
        //bmp.copyPixelsFromBuffer(ByteBuffer.wrap(data));
        Log.i("GGGG", new DebugInfo() + "" + bmp.getByteCount());
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(localPath);
//            BufferedOutputStream bos = new BufferedOutputStream(os);
//            boolean b = false;
//            if (bmp != null) {
//                b = bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

}

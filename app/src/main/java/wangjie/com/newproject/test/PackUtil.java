package wangjie.com.newproject.test;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hnkj
 */
public class PackUtil
{
    /**
     * 解析包头
     * @author hnkj
     * @param bts
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static JSONObject analyHead(byte[] bts) throws UnsupportedEncodingException
    {
        String head = new String(bts,"UTF-8");
        JSONObject json = new JSONObject();
        json.put("v", head.substring(0, 2).trim());
        json.put("length", head.substring(2, 12).trim());
        json.put("type", head.substring(12, 16).trim());
        json.put("flow", head.substring(16,17).trim());
        json.put("error", head.substring(17, 21).trim());
        return json;
    }
    /**
     * 
     * @author hnkj
     * @param version
     * @param length
     * @param command
     * @param error
     * @return
     */
    public static String fomartHead(int version,int length,int command,int flow,String error)
    {
        String rs = version+"";
        rs += String.format("%1$10s", length);
        rs += command;
        rs += flow;
        rs += error;
        return rs;
    }
    
    public static List<byte[]> packing(byte[] bodaybts,int cmd,int flow, String error)
    {
        List<byte[]> packList = new ArrayList<byte[]>();
      //数据包中包体的最大长度是10240-21 = 10219
        int packCount = 0;//总包数
        if(bodaybts.length>0 && bodaybts.length%10219==0)
        {
            packCount = bodaybts.length/10219;
        }else//包长有不是10219长的时候
        {
            packCount = bodaybts.length/10219+1;
        }
        
        for (int i = 0; i < packCount; i++)
        {
            
            int length = 0;
            if(i<packCount-1)//不是最后一个包
            {
                length = 10219;
                flow = 1;
            }else
            {
                length = bodaybts.length-i*10219;
            }
            byte[] tempbts = new byte[length];
            System.arraycopy(bodaybts, 10219*i, tempbts, 0, length);
            
            String headStr = fomartHead(11, length+21, cmd, flow, error);
            packList.add(headStr.getBytes());//先有包头
            packList.add(tempbts);//后有包体，不能颠倒
        }
        return packList;
    }
    
    public static void main(String[] args)
    {
        byte[] bts = {11,12,13,14};
        byte[] btss = new byte[10];
        System.arraycopy(bts, 0, btss, 0, 10);
        System.err.println(btss.length);
    }
}


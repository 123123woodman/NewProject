package wangjie.com.newproject.test;

/**
 * Created by Administrator on 2018/5/3.
 */

public class BmpTest {

    private static byte[] headInfo;
    private static byte[] sumBytePacket;
    public static byte[] getBmpHead(byte[] bmpData, int bmpSize, int width, int height) {
        sumBytePacket = new byte[0];
        sumBytePacket = concat(sumBytePacket, headInfo(bmpSize, width, height),0);
        sumBytePacket = concat(sumBytePacket, bmp8Pallet(), 0);
        sumBytePacket = concat(sumBytePacket, bmpData, 0);
        return sumBytePacket;
    }

    public static byte[] headInfo(int bmpSize, int width, int height) {

        headInfo = new byte[54];

        headInfo[0] = 0x42;
        headInfo[1] = 0x4d;

        //bfSize
        setNum(1078 + bmpSize, 5, 4, 3, 2);
//        setNum(54 + bmpSize, 5, 4, 3, 2);

        //bfReserved1
        headInfo[6] = 0x00;
        headInfo[7] = 0x00;

        //bfReserved2
        headInfo[8] = 0x00;
        headInfo[9] = 0x00;

        //bfOffBits
        setNum(1078, 13, 12, 11, 10);

        //biSize
        headInfo[14] = 0x28;
        headInfo[15] = 0x00;
        headInfo[16] = 0x00;
        headInfo[17] = 0x00;

        //biWidth
        setNum(width, 21, 20, 19, 18);

        //biHeight
        setNum(height, 25, 24, 23, 22);

        //biPlanes
        headInfo[26] = 0x01;
        headInfo[27] = 0x00;

        //biBitCount
        headInfo[28] = 0x08;
        headInfo[29] = 0x00;

        //biCompression
        headInfo[30] = 0x00;
        headInfo[31] = 0x00;
        headInfo[32] = 0x00;
        headInfo[33] = 0x00;

        //biSizeImage 去头数据大小
        setNum(bmpSize, 37, 36, 35, 34);

        //biXPelsPerMeter
        headInfo[38] = 0x00;
        headInfo[39] = 0x00;
        headInfo[40] = 0x00;
        headInfo[41] = 0x00;

        //biYPelsPerMeter
        headInfo[42] = 0x00;
        headInfo[43] = 0x00;
        headInfo[44] = 0x00;
        headInfo[45] = 0x00;

        //biClrUsed
        headInfo[46] = 0x00;
        headInfo[47] = 0x00;
        headInfo[48] = 0x00;
        headInfo[49] = 0x00;

        //biClrImportant
        headInfo[50] = 0x00;
        headInfo[51] = 0x00;
        headInfo[52] = 0x00;
        headInfo[53] = 0x00;


        return headInfo;
    }

    public static void setNum(int i, int one, int two, int three, int fore) {

        headInfo[one] = (byte) ((i >> 24) & 0xFF);
        headInfo[two] = (byte) ((i >> 16) & 0xFF);
        headInfo[three] = (byte) ((i >> 8) & 0xFF);
        headInfo[fore] = (byte) (i & 0xFF);
    }

    /**
     * 8位位图调色板
     * @return
     */
    private static byte[] bmp8Pallet() {
        byte[] colorInfo = new byte[256 * 4];
        //生成颜色表
        for (int i = 0; i < 256; i++) {
            colorInfo[4 * i] = (byte) i;   //Blue
            colorInfo[1 + 4 * i] = (byte) i;   //Green
            colorInfo[2 + 4 * i] = (byte) i;   //Red
            colorInfo[3 + 4 * i] = (byte) 0x00;
        }

        return colorInfo;
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

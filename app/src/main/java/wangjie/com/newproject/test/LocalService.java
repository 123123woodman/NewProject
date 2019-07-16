package wangjie.com.newproject.test;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static wangjie.com.newproject.test.Client.MSG_FROM_CLIENTMSG;
import static wangjie.com.newproject.test.Client.MSG_FROM_SERVICE;
import static wangjie.com.newproject.test.Client.MSG_FRO_CLIENT;

/**
 * Created by Administrator on 2018/5/7.
 */

public class LocalService extends Service{

    private String TAG = getPackageName() + getClass().getSimpleName();

    private Messenger clientMessage;

    class ServerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FRO_CLIENT:

                    String msgClient = msg.getData().getString("wangjie");

                    Log.i(TAG, "收到了客户端发送的" + msgClient + "消息");

                    Message message = Message.obtain(null, MSG_FROM_SERVICE);

                    message.arg1 = 200;

                    try {
                        clientMessage.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                case MSG_FROM_CLIENTMSG:

                    clientMessage = msg.replyTo;

                    break;
            }

        }
    }

    final Messenger serverMessenger = new Messenger(new ServerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "与客户端建立了连接", Toast.LENGTH_SHORT).show();
        return serverMessenger.getBinder();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Toast.makeText(getApplicationContext(), "客户端断开了连接", Toast.LENGTH_SHORT).show();
    }
}

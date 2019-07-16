package wangjie.com.library.net;

import android.content.Context;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import wangjie.com.library.R;


/**
 * Created by Administrator on 2019/5/20.
 */

public class SSLFactory {
    public static int[] certificates = {R.raw.server_cert};
    public static SSLSocketFactory getSSLSocketFactory(Context context) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        CertificateFactory certificateFactory;
        SSLContext sslContext=null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));

                if (certificate != null) {
                    certificate.close();
                }
            }
            sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

        }catch (Exception e){
            e.printStackTrace();
        }
        return sslContext.getSocketFactory();
    }



    public  static String urls[] = {"192.168.1.242"};

    public static HostnameVerifier getHostnameVerifier() {

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                boolean verifier = false;
                for (String host : urls) {
                    if (host.equalsIgnoreCase(hostname)) {
                        verifier = true;
                    }
                }
                return verifier;
            }
        };
        return hostnameVerifier;
    }

}

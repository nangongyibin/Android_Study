package com.ngyb.https;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ngyb.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void https(View view) {
        new Thread() {

            @Override
            public void run() {
                method();
            }
        }.start();
    }

    private void method() {
        try {
            String requestUrl = "https://47.105.71.243:443/mobicop.json";
            URL url = new URL(requestUrl);
            Log.e(TAG, "method: 1");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            Log.e(TAG, "method: 2");
            //通过信任管理器工厂生成管理器
            TrustManagerFactory tfm = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            Log.e(TAG, "method: 3");
            //初始化密钥库对象
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
//        KeyStore ks = KeyStore.getInstance("jks");
//            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            Log.e(TAG, "method: 4");
            //清空默认证书信息
            ks.load(null);
            Log.e(TAG, "method: 5");
            //返回指定证书类型的证书工厂对象
//        CertificateFactory cf = CertificateFactory.getInstance("X.509");
            CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
            Log.e(TAG, "method: 6");
            InputStream is = getAssets().open("3758543_sptth.nangongyibin.com.jks");
            Log.e(TAG, "method: 7");
            //生成证书对象
            Certificate cert = cf.generateCertificate(is);
            Log.e(TAG, "method: 8");
            //将给定的可信证书分配给给定的别名。
//        ks.load(is,"P0eS7PjG".toCharArray());
//        Log.e(TAG, "method: 81" );
            ks.setCertificateEntry("tomcat", cert);
//        ks.setKeyEntry("tomcat",);
            Log.e(TAG, "method: 9");
            //初始化信任管理器工厂
            tfm.init(ks);
            Log.e(TAG, "method: 10");
            //管理信任管理器
            TrustManager[] tm = tfm.getTrustManagers();
            Log.e(TAG, "method: 11");
            //创建安全上下文对象
//            SSLContext context = SSLContext.getDefault();
            SSLContext context = SSLContext.getInstance("TLS");
//            SSLContext context = SSLContext.getInstance("TLSv1","AndroidOpenSSL");
            Log.e(TAG, "method: 12");
            //初始化此上下文
//            context.init(null, tm, new SecureRandom());
            context.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain,
                                                       String authType)
                                throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,
                                                       String authType)
                                throws CertificateException {
                            for (X509Certificate cert : chain) {
                                // Make sure that it hasn't expired.
                                cert.checkValidity();
                                // Verify the certificate's public key chain.
                                try {
                                    cert.verify(((X509Certificate) cert).getPublicKey());
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                } catch (InvalidKeyException e) {
                                    e.printStackTrace();
                                } catch (NoSuchProviderException e) {
                                    e.printStackTrace();
                                } catch (SignatureException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, null);
            Log.e(TAG, "method: 13");
            //使用信任管理器
            conn.setSSLSocketFactory(context.getSocketFactory());
            Log.e(TAG, "method: 14");
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            Log.e(TAG, "method: 15");
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            Log.e(TAG, "method: 16");
            Log.e(TAG, "method: 16"+conn.getResponseCode());
            InputStream inputStream = conn.getInputStream();
            Log.e(TAG, "method: 17");
            String result = StreamUtils.convertStreamToString(inputStream);
            Log.e(TAG, "method: " + result);
        } catch (MalformedURLException e) {
            Log.e(TAG, "method: 1=" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "method: 2=" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "method: 3=" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (KeyStoreException e) {
            Log.e(TAG, "method: 4=" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            Log.e(TAG, "method: 4=" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (CertificateException e) {
            Log.e(TAG, "method: 5=" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (KeyManagementException e) {
            Log.e(TAG, "method: 13=" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}

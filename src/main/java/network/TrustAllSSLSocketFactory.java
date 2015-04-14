package network;

/**
 *  Used Source: https://code.google.com/p/meneameandroid/source/browse/#svn/trunk/src/com/dcg/auth
 *  Author B.Thax.DCG
 *
 *  Used for ease of using https protocol
 */

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class TrustAllSSLSocketFactory extends SSLSocketFactory {
    private javax.net.ssl.SSLSocketFactory factory;

    public TrustAllSSLSocketFactory() throws KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException,
            UnrecoverableKeyException {
        super(null);
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { new TrustAllManager() }, null);
            factory = sslcontext.getSocketFactory();
            setHostnameVerifier(new AllowAllHostnameVerifier());
        } catch (Exception ex) {
            Log.i("SSL Exception", ex.toString());
        }
    }

    @SuppressWarnings("unused")
    public static SocketFactory getDefault() throws KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException,
            UnrecoverableKeyException {
        return new TrustAllSSLSocketFactory();
    }

    @Override
    @SuppressWarnings("unused")
    public Socket createSocket() throws IOException {
        return factory.createSocket();
    }

    @Override
    @SuppressWarnings("unused")
    public Socket createSocket(Socket socket, String s, int i, boolean flag)
            throws IOException {
        return factory.createSocket(socket, s, i, flag);
    }

    @SuppressWarnings("unused")
    public Socket createSocket(InetAddress inaddr, int i, InetAddress inaddr1,
                               int j) throws IOException {
        return factory.createSocket(inaddr, i, inaddr1, j);
    }

    @SuppressWarnings("unused")
    public Socket createSocket(InetAddress inaddr, int i) throws IOException {
        return factory.createSocket(inaddr, i);
    }

    @SuppressWarnings("unused")
    public Socket createSocket(String s, int i, InetAddress inaddr, int j)
            throws IOException {
        return factory.createSocket(s, i, inaddr, j);
    }

    @SuppressWarnings("unused")
    public Socket createSocket(String s, int i) throws IOException {
        return factory.createSocket(s, i);
    }

    @SuppressWarnings("unused")
    public String[] getDefaultCipherSuites() {
        return factory.getDefaultCipherSuites();
    }

    @SuppressWarnings("unused")
    public String[] getSupportedCipherSuites() {
        return factory.getSupportedCipherSuites();
    }
}

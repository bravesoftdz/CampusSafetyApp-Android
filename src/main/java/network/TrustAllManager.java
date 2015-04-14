package network;

/**
 *  Used Source: https://code.google.com/p/meneameandroid/source/browse/#svn/trunk/src/com/dcg/auth
 *  Author B.Thax.DCG
 *
 *  Used for ease of using https protocol
 */

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class TrustAllManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] cert, String authType)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] cert, String authType)
            throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}


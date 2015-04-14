package network;

import org.apache.http.HttpVersion;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;


public class CHttpClient {
    private static DefaultHttpClient c_httpClient = null;

    private CHttpClient() {
    }

    public static synchronized DefaultHttpClient getHttpClient() {
        if( c_httpClient == null ) {
            HttpParams params = new BasicHttpParams();

            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
            HttpProtocolParams.setUseExpectContinue(params, true);

            ConnPerRoute connPerRoute = new ConnPerRouteBean(12);
            ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
            ConnManagerParams.setTimeout(params, (10 * 1000)); // 10 seconds

            HttpConnectionParams.setConnectionTimeout(params, (10 * 1000));
            HttpConnectionParams.setSoTimeout(params, (10 * 1000));
            HttpConnectionParams.setSocketBufferSize(params, 8192);
            HttpConnectionParams.setStaleCheckingEnabled(params, false);

            HttpClientParams.setRedirecting(params, false);

            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            try {
                schReg.register(new Scheme("https", TrustAllSSLSocketFactory.getDefault(), 443));
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException e) {
                e.printStackTrace();
            }
            ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
            c_httpClient = new DefaultHttpClient(conMgr, params);
        }

        return c_httpClient;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        throw new CloneNotSupportedException();
    }
}

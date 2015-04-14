package network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BasicNetwork extends AsyncTask<URL, Integer, String> {

    private HttpClient http_client = null;
    private ArrayList<NameValuePair> http_parameters = null;
    private String request_type = "post";
    private JSONObject json_object = null;
    private IGeneralRun general_run = null;
    private Object obj = null;
    private Activity activity = null;
    private ProgressDialog progress_dialog = null;
    private boolean useProgress = false;

    private HttpClient getHttpClient() {
        if( http_client == null )
            http_client = CHttpClient.getHttpClient();

        return http_client;
    }

    private String executeHttpPost(String url, ArrayList<NameValuePair> post_parameters) throws Exception  {
        BufferedReader in = null;
        try {

            HttpClient client = getHttpClient();
            HttpPost request = new HttpPost(url);
            UrlEncodedFormEntity form_entity = new UrlEncodedFormEntity(post_parameters);
            request.setEntity(form_entity);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuilder sb = new StringBuilder("");
            String line;
            String NL = System.getProperty("line.seperator");
            while( (line = in.readLine()) != null ) {
                sb.append(line).append(NL);
            }
            in.close();

            String result = sb.toString();
            json_object = JsonData.Instance().parseString( result );
            Log.i("Http Result (Post)", result);

            return result;

        }
        finally {
            if( in != null ) {
                try {
                    in.close();
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String executeHttpPut(String url, ArrayList<NameValuePair> put_parameters) throws Exception  {
        BufferedReader in = null;
        try {

            HttpClient client = getHttpClient();
            HttpPut request = new HttpPut(url);
            UrlEncodedFormEntity form_entity = new UrlEncodedFormEntity(put_parameters);
            request.setEntity(form_entity);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuilder sb = new StringBuilder("");
            String line;
            String NL = System.getProperty("line.seperator");
            while( (line = in.readLine()) != null ) {
                sb.append(line).append(NL);
            }
            in.close();

            String result = sb.toString();
            json_object = JsonData.Instance().parseString( result );
            Log.i("Http Result (PUT)", result);

            return result;

        }
        finally {
            if( in != null ) {
                try {
                    in.close();
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected String doInBackground(URL... params)  {
        String result = "";
        Log.i("Working", "sending...");

        // Use with Parameters being sent at the moment for PUT and POST!

        int HTTP_TIMEOUT = 10000;
        if( (request_type.compareTo("post")==0) && (http_parameters != null)) {
            URL m_url = params[0];
            URL c_url = params[1];
            HttpURLConnection huc;

            boolean is_ok = false;

            try {
                    if(NetworkDef.PROTOCAL_STANDARD.compareTo("http://") == 0) {
                        huc = (HttpURLConnection) m_url.openConnection();
                        huc.setConnectTimeout(HTTP_TIMEOUT);
                        huc.setRequestMethod("POST");
                        huc.connect();
                        is_ok = (huc.getResponseCode() == HttpURLConnection.HTTP_OK);
                        huc.disconnect();
                    } else
                        is_ok = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if( is_ok ) {
                try {
                    Log.i("NetworkThread", "URL1 OK");
                    Log.i("URLString", m_url.toString());
                    result = executeHttpPost(m_url.toString(), http_parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    if(NetworkDef.PROTOCAL_STANDARD.compareTo("http://") == 0) {
                        huc = (HttpURLConnection) c_url.openConnection();
                        huc.setConnectTimeout(HTTP_TIMEOUT);
                        huc.setRequestMethod("POST");
                        huc.connect();
                        is_ok = (huc.getResponseCode() == HttpURLConnection.HTTP_OK);
                        huc.disconnect();
                    } else
                        is_ok = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if( is_ok )
                    try {
                        Log.i("NetworkThread","URL2 OK");
                        Log.i("URLString",c_url.toString());
                        result = executeHttpPost(c_url.toString(), http_parameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

        }

        if( (request_type.compareTo("put")==0) && (http_parameters != null)) {
            URL m_url = params[0];
            URL c_url = params[1];
            HttpURLConnection huc;

            boolean is_ok = false;

            try {
                if(NetworkDef.PROTOCAL_STANDARD.compareTo("http://") == 0) {
                    huc = (HttpURLConnection) m_url.openConnection();
                    huc.setConnectTimeout(HTTP_TIMEOUT);
                    huc.setRequestMethod("PUT");
                    huc.connect();
                    is_ok = (huc.getResponseCode() == HttpURLConnection.HTTP_OK);
                    huc.disconnect();
                } else
                    is_ok = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if( is_ok ) {
                try {
                    Log.i("NetworkThread", "URL1 OK");
                    Log.i("URLString", m_url.toString());
                    result = executeHttpPut(m_url.toString(), http_parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    if (NetworkDef.PROTOCAL_STANDARD.compareTo("http://") == 0) {
                        huc = (HttpURLConnection) c_url.openConnection();
                        huc.setConnectTimeout(HTTP_TIMEOUT);
                        huc.setRequestMethod("PUT");
                        huc.connect();
                        is_ok = (huc.getResponseCode() == HttpURLConnection.HTTP_OK);
                        huc.disconnect();
                    } else
                        is_ok = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if( is_ok ) {
                    try {
                        Log.i("NetworkThread", "URL2 OK");
                        Log.i("URLString", c_url.toString());
                        result = executeHttpPut(c_url.toString(), http_parameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        Log.i("Done", result);
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(useProgress) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progress_dialog = ProgressDialog.show(activity, "", "Sending...", false);
                }
            });
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(useProgress) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progress_dialog.cancel();
                }
            });
        }

        json_object = JsonData.Instance().parseString(result);
        Log.i("Http Result", result);
        if(general_run != null)
            general_run.execute(this, obj);
    }



    /// constructor
    public BasicNetwork (String request_type) {
        this.request_type = request_type.toLowerCase();
        Log.i("Request Type", this.request_type);
    }

    /// set parameters
    public void setParameters( ArrayList<NameValuePair> http_parameters ) {
        this.http_parameters = http_parameters;
    }

    /// Function/Object Ran during the execution of request
    public void postResultRun(IGeneralRun r, Object obj) {
        general_run = r;
        this.obj = obj;
    }

    public void enableProgressDialog(boolean e, Activity a) {
        this.useProgress = e;
        this.activity    = a;
    }

    /// return data as a jsonobject
    public JSONObject getJsonObject() {
        return json_object;
    }

}

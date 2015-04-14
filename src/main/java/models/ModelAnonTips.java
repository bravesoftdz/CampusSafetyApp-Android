package models;

import android.app.Activity;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import network.BasicNetwork;
import network.IGeneralRun;
import network.NetworkDef;
import network.SimpleNetwork;

/**
 * Model class that handles the form data to be stored via database
 * Sanitize is done server side.
 */

public class ModelAnonTips {

    private String subject;
    private String message;
    private IGeneralRun after_save;
    private boolean success_save = false;

    public ModelAnonTips(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    @SuppressWarnings("unused")
    public void setFields(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    @SuppressWarnings("unused")
    public void save() {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("subject", subject));
        http_parameters.add( new BasicNameValuePair("message", message));
        http_parameters.add( new BasicNameValuePair("test_email", NetworkDef.TEST_EMAIL));

        SimpleNetwork.enableProgress(false, null);
        SimpleNetwork.send("PUT", "tips/send/format/json", http_parameters, null, "");
    }

    @SuppressWarnings("unused")
    public void save(IGeneralRun run) {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("subject", subject));
        http_parameters.add( new BasicNameValuePair("message", message));
        http_parameters.add( new BasicNameValuePair("test_email", NetworkDef.TEST_EMAIL));

        after_save = run;
        SimpleNetwork.enableProgress(false, null);
        SimpleNetwork.send("PUT", "tips/send/format/json", http_parameters, onAfter, "");

    }

    /**
     * Creates a progress dialog box
     * [Activity a] is used to be able to create the progress dialog box.
     */
    public void save(IGeneralRun run, boolean enableProgress, Activity a) {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("subject", subject));
        http_parameters.add( new BasicNameValuePair("message", message));
        http_parameters.add( new BasicNameValuePair("test_email", NetworkDef.TEST_EMAIL));

        after_save = run;
        SimpleNetwork.enableProgress(enableProgress, a);
        SimpleNetwork.send("PUT", "tips/send/format/json", http_parameters, onAfter, "");

    }

    private IGeneralRun onAfter = new IGeneralRun() {
        @Override
        public void execute(BasicNetwork request, Object o) {

            JSONObject json_result = request.getJsonObject();
            try {
                if( json_result.getInt("result") == 1 ) {
                    Log.i("ModelAnonTips(Save)", "Success!");
                    success_save = true;
                }
                else {
                    Log.i("ModelAnonTips(Save)", "Failed!");
                    success_save = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            after_save.execute(null, success_save);
        }
    };
}

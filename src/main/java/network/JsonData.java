package network;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonData {
    private static JsonData instance = null;

    protected JsonData() {}

    public static JsonData Instance() {
        if( instance == null )
            instance = new JsonData();

        return instance;
    }

    public JSONObject parseString(String content) {
        JSONObject json_obj = null;

        try {
            json_obj = new JSONObject( content );
        } catch (JSONException e) {
            try {
                json_obj = new JSONObject("{result:0, error_msg: \"Unable to result from response. Connection parameters may be improperly set or data format may be incorrect. Contact mobile developer.\"}");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            Log.e("JSON Parser", "Error parsing data: " + e.toString());
        }

        return json_obj;
    }

}

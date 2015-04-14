package models;


import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import network.BasicNetwork;
import network.IGeneralRun;
import network.SimpleNetwork;

public class ModelStudentLocation {

    private String first_name;
    private String last_name;
    private String phone_number;
    private String latitude;
    private String longitude;
    private String services;
    private String vehicle_license_number;
    private int    vehicle_year;
    private String vehicle_make;
    private IGeneralRun after_save;
    private boolean success_save = false;

    public ModelStudentLocation(String first_name, String last_name, String phone_number, String latitude,String longitude, String services, String vehicle_license_number,
                                int vehicle_year, String vehicle_make) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.latitude = latitude;
        this.longitude = longitude;
        this.services = services;
        this.vehicle_license_number = vehicle_license_number;
        this.vehicle_year = vehicle_year;
        this.vehicle_make = vehicle_make;
    }

    @SuppressWarnings("unused")
    public void save() {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("first_name", first_name));
        http_parameters.add( new BasicNameValuePair("last_name" , last_name));
        http_parameters.add( new BasicNameValuePair("phone"     , phone_number));
        http_parameters.add( new BasicNameValuePair("latitude"  , latitude));
        http_parameters.add( new BasicNameValuePair("longitude" , longitude));
        http_parameters.add( new BasicNameValuePair("service"   , services));
        http_parameters.add( new BasicNameValuePair("license"   , vehicle_license_number));
        http_parameters.add( new BasicNameValuePair("vehicle_year", Integer.toString(vehicle_year)));
        http_parameters.add( new BasicNameValuePair("vehicle_make", vehicle_make));

        SimpleNetwork.send("PUT", "student_location/send/format/json", http_parameters, null, "");
    }

    @SuppressWarnings("unused")
    public void save(IGeneralRun run) {
        ArrayList<NameValuePair> http_parameters;
        http_parameters = new ArrayList<>();

        http_parameters.add( new BasicNameValuePair("first_name", first_name));
        http_parameters.add( new BasicNameValuePair("last_name" , last_name));
        http_parameters.add( new BasicNameValuePair("phone"     , phone_number));
        http_parameters.add( new BasicNameValuePair("latitude"  , latitude));
        http_parameters.add( new BasicNameValuePair("longitude"  , longitude));
        http_parameters.add( new BasicNameValuePair("service"   , services));
        http_parameters.add( new BasicNameValuePair("license"   , vehicle_license_number));
        http_parameters.add( new BasicNameValuePair("vehicle_year", Integer.toString(vehicle_year)));
        http_parameters.add( new BasicNameValuePair("vehicle_make", vehicle_make));

        after_save = run;
        SimpleNetwork.send("PUT", "student_location/send/format/json", http_parameters, onAfter, "");
    }

    private IGeneralRun onAfter = new IGeneralRun() {
        @Override
        public void execute(BasicNetwork request, Object o) {

            JSONObject json_result = request.getJsonObject();
            try {
                if( json_result.getInt("result") == 1 ) {
                    Log.i("(Save)ModelStudentLoc", "Success!");
                    success_save = true;
                }
                else {
                    Log.i("(Save)ModelStudentLoc", "Failed!");
                    success_save = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            after_save.execute(null, success_save);
        }
    };
}

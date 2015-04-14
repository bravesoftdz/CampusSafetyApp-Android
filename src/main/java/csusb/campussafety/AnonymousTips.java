package csusb.campussafety;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import models.ModelAnonTips;
import network.BasicNetwork;
import network.IGeneralRun;
import utility.SimpleDialog;

public class AnonymousTips extends Activity {

    private EditText et_subject = null;
    private EditText et_message = null;
    private ModelAnonTips model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymoustips_page); /// Set activity page

        SimpleDialog.show("Anonymous Tips", "Together we can make our campus safer." +
                "If you have any information of suspicious activity please report it." +
                "All information is passed to the campus detective.  All information is confidential.", this);

        /** Initialize variables to get data from text-fields and get action from button */
        et_subject = (EditText) findViewById(R.id.et_anonymoustips_subject);
        et_message = (EditText) findViewById(R.id.et_anonymoustips_message);
        Button btn_submit = (Button) findViewById(R.id.btn_anonymoustips_submit);

        /** Set click/touch listener for when the user presses down on button */
        btn_submit.setOnClickListener(ocl_submit);
    }

    /** Set click/touch listener for when the user presses down on button.
     * This will make sure the user enters a subject and message before the
     * submit will take. */
    private View.OnClickListener ocl_submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            boolean exist_subject = true;
            boolean exist_message = true;

            // Check if something is typed
            if( et_subject.length() <= 0 ) {
                Log.e("TextField:Subject", "Subject is empty!");
                exist_subject = false;
            }

            // Check if something is typed
            if( et_message.length() <= 0 ) {
                Log.e("TextField:Message", "Message is empty!");
                exist_message = false;
            }

            // Check if both text fields have data then send data to server.
            if( exist_subject && exist_message ) {
                /**
                 *  Create a continue/stop dialog box for the user to choose from.  The dialog box
                 *  confirms the users intention to send the message. The Model handles the data
                 *  asynchronously the moment "continue" is chosen and displays that the send is
                 *  in progress. When the send is complete the user is prompt that the task has
                 *  finished and exits the to the main activity.
                 */

                AlertDialog.Builder user_alert_tosend = new AlertDialog.Builder(AnonymousTips.this);
                model = new ModelAnonTips(et_subject.getText().toString(), et_message.getText().toString());
                user_alert_tosend.setMessage("Tap \"Continue\" to send")
                        .setPositiveButton("Continue", alertdialog_oncontinue)
                        .setNegativeButton("Stop", null)
                        .setCancelable(false).show(); // User can't cancel by touching off dialog box
            }
            else {
                Log.e("Button:Submit", "Unable to submit data!");
            }
        }
    };

    // Handles when a user chooses "Continue" when trying to submit tip.
    private DialogInterface.OnClickListener alertdialog_oncontinue = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(model != null) {
                model.save(after_save, true, AnonymousTips.this);
                Log.i("Button:Submit", "Submitted data!");
            }
        }
    };

    /**
     * Handles post or what happens after the send has completed and displays a message depending on
     * whether the data was sent correctly or not.
     */
    private IGeneralRun after_save = new IGeneralRun() {
        @Override
        public void execute(BasicNetwork request, Object o) {
            // request = null
            boolean success = (boolean)o;
            final AlertDialog.Builder user_alert = new AlertDialog.Builder(AnonymousTips.this);

            if(success) {
                Log.i("(Success)Going to...", "AnonymousTips");
                user_alert.setMessage("Your tip has been successfully sent!")
                        .setNeutralButton("Continue", alertdialog_onsucess)
                        .setCancelable(false).show();
            }
            else {
                Log.i("(Failed)Going to...", "AnonymousTips");
                user_alert.setMessage("Your tip was not successfully sent! This could be a server error sorry for the inconvenience. Try again!")
                        .setNeutralButton("Continue", alertdialog_onfail)
                        .setCancelable(false).show();
            }
        }
    };

    // Goes to the next activity if send was successful.
    private DialogInterface.OnClickListener alertdialog_onsucess = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            final Intent page = new Intent( AnonymousTips.this, csusb.campussafety.Menu.class);
            startActivity( page );
            finish();
        }
    };

    // Goes to the next activity if send failed.
    private DialogInterface.OnClickListener alertdialog_onfail = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            final Intent page = new Intent( AnonymousTips.this, AnonymousTips.class );
            startActivity( page );
            finish();
        }
    };

    /**
     * No intention to implement (atm) but this is the sub-menu for the activity page
     */
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /// Handle action bar item clicks here. The action bar will
        /// automatically handle clicks on the Home/Up button, so long
        /// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        ///noinspection SimplifiableIfStatement
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }
    */
}

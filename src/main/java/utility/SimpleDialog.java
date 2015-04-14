package utility;

import android.app.AlertDialog;
import android.content.Context;

public class SimpleDialog {
    public static void show(String header, String content, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(header)
                .setMessage(content)
                .setNeutralButton("OK",null)
                .show();
    }
}

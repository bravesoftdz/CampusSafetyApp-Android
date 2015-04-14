package customactivities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import csusb.campussafety.R;

public class NavigationGeneralActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_general);
    }

    public View setLayout(int layout_id) {
        RelativeLayout r = (RelativeLayout) findViewById(R.id.rl_inclusion_space);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(layout_id, null, false);
        r.addView(v, 1);

        return v;
    }
}

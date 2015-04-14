package csusb.campussafety;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class MenuPage extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        RelativeLayout btn_aboutus   = (RelativeLayout) findViewById(R.id.aboutus);
        RelativeLayout btn_services  = (RelativeLayout) findViewById(R.id.services);
        RelativeLayout btn_resources = (RelativeLayout) findViewById(R.id.resources);
        RelativeLayout btn_tips      = (RelativeLayout) findViewById(R.id.tips);

        btn_aboutus.setOnClickListener(this);
        btn_services.setOnClickListener(this);
        btn_resources.setOnClickListener(this);
        btn_tips.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.services:
                startActivity(new Intent(this, Services.class));
                break;
            case R.id.resources:
                startActivity(new Intent(this, Resources.class));
                break;
            case R.id.aboutus:
                startActivity(new Intent(this, AboutUs.class));
                break;
            case R.id.tips:
                startActivity(new Intent(this, AnonymousTips.class));
                break;
        }
    }
}

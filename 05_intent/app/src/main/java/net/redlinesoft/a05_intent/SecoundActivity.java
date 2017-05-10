package net.redlinesoft.a05_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);

        Bundle bundle = getIntent().getExtras();

        TextView txtView = (TextView) findViewById(R.id.textView);

        if (bundle!=null) {
            String value = bundle.getString("TEXT_FORM");
            txtView.setText(value);
        }


    }
}

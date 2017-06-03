package net.redlinesoft.a04_basicform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get bundle
        Bundle bundle = getIntent().getExtras();
        String extName = bundle.getString("extName").toString();
        String extBirthday = bundle.getString("extBirthday").toString();
        String extGender = bundle.getString("extGender").toString();
        String extSubscribe = bundle.getString("extSubscribe").toString();
        String extEmail = bundle.getString("extEmail").toString();
        String extPassword = bundle.getString("extPassword").toString();

        // binding
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        TextView textViewBirthday = (TextView) findViewById(R.id.textViewBirthday);
        TextView textViewGender = (TextView) findViewById(R.id.textViewGender);
        TextView textViewSubscribe = (TextView) findViewById(R.id.textViewSubscribe);
        TextView textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        TextView textViewPassword = (TextView) findViewById(R.id.textViewPassword);

        // set value to textview
        textViewName.setText(extName);
        textViewBirthday.setText(extBirthday);
        textViewGender.setText(extGender);
        textViewSubscribe.setText(extSubscribe);
        textViewEmail.setText(extEmail);
        textViewPassword.setText(extPassword);

    }
}

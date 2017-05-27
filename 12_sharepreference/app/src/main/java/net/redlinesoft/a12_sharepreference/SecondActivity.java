package net.redlinesoft.a12_sharepreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String APP_PREFS = "APP_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);

        int prefs_number_column = sharedPreferences.getInt("NUMBER_COLUMN", 0);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(prefs_number_column));


    }
}

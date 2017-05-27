package net.redlinesoft.a12_sharepreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String APP_PREFS = "APP_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get SharedPreferences
        final SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);

        // Edit SharedPreferences
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("NUMBER_COLUMN", 4);
        editor.apply();
        editor.commit();

        Button buttonShow = (Button) findViewById(R.id.bntShowValue);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Button buttonClear = (Button) findViewById(R.id.bntClearValue);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
            }
        });


    }

}

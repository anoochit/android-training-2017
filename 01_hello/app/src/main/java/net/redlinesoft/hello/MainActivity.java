package net.redlinesoft.hello;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static String TAG="HelloApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Note: code with console log for debug, info, error and watch your log in logcat
        Log.d(TAG,"this is debug message, watch me in debug mode in logcat");



    }



}


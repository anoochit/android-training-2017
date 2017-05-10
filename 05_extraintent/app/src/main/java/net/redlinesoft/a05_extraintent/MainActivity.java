package net.redlinesoft.a05_extraintent;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CALL = 0;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bntCall = (Button) findViewById(R.id.bntCall);
        bntCall.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:089123456"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CALL_PHONE},0);
                    return;
                }
                startActivity(i);
            }
        });

        Button bntDial = (Button) findViewById(R.id.bntDial);
        bntDial.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:089123456"));
                startActivity(i);
            }
        });

        Button bntExtraText = (Button) findViewById(R.id.bntExtraText);
        bntExtraText.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT,"News for You!");
                i.setType("text/plain");
                startActivity(Intent.createChooser(i,"x"));
            }
        });

        Button bntUrl = (Button) findViewById(R.id.bntUrl);
        bntUrl.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.google.com"));
                startActivity(i);
            }
        });

        Button bntSearchMap = (Button) findViewById(R.id.bntSearchMap);
        bntSearchMap.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:0,0?q=13.901720, 100.532805 (PIM)"));
                startActivity(i);
            }
        });

        Button bntShowMap = (Button) findViewById(R.id.bntShowMap);
        bntShowMap.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:0,0?q=Central Plaza Chaengwattana"));
                startActivity(i);
            }
        });


    }

}

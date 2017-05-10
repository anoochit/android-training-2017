package net.redlinesoft.a05_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Intent.ACTION_SEND;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.editText);


        Button bntSubmit = (Button) findViewById(R.id.bntSubmit);
        bntSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecoundActivity.class);
                String textValue = String.valueOf(editText.getText());
                intent.putExtra("TEXT_FORM",textValue);
                startActivity(intent);
            }
        });

        Button bntSecoundActivity = (Button) findViewById(R.id.bntGoSecound);
        bntSecoundActivity.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecoundActivity.class);
                startActivity(intent);
            }
        });

        Button bntShare = (Button) findViewById(R.id.bntShare);
        bntShare.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "geo:0,0?q=13.9017218,100.5240226 (PIM)";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}

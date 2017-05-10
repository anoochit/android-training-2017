package net.redlinesoft.a04_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static  String TAG="Calculator";

    private double valueOne = Double.NaN;
    private double valueTwo;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtResult.setText("");

        Button bnt1 = (Button) findViewById(R.id.bnt1);
        bnt1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultText = txtResult.getText().toString();
                txtResult.setText(resultText+"1");
            }
        });

        Button bnt2 = (Button) findViewById(R.id.bnt2);
        bnt2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultText = txtResult.getText().toString();
                txtResult.setText(resultText+"2");
            }
        });

        Button bntPlus = (Button) findViewById(R.id.bntPlus);
        bntPlus.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                CURRENT_ACTION=ADDITION;
                txtResult.setText("");
                Log.d(TAG,"operation = " + CURRENT_ACTION);
            }
        });

        Button bntEq = (Button) findViewById(R.id.bntEq);
        bntEq.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                CURRENT_ACTION = '0';
            }
        });

        Button bntClear = (Button) findViewById(R.id.bntClear);
        bntClear.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtResult.setText("");
                valueOne=Double.NaN;
                valueTwo=Double.NaN;
            }
        });

    }

    public void calculate(){
        // if has valueOne
        if(!Double.isNaN(valueOne)) {
            valueTwo=Double.parseDouble(txtResult.getText().toString());
            Log.d(TAG,"value 2 = " + valueTwo);

            Double result=0.0;

            if (CURRENT_ACTION==ADDITION) {
                result=valueOne+valueTwo;
            }

            txtResult.setText(Double.toString(result));

        } else {
            try {
                valueOne = Double.parseDouble(txtResult.getText().toString());
                Log.d(TAG,"value 1 = " + valueOne);
            }
            catch (Exception e){
                Log.d(TAG,e.getMessage());
            }
        }
    }

}

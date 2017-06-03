package net.redlinesoft.a04_basicform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText editTextName,editTextBirthday,editTextEmail,editTextPassword;
    CheckBox checkboxNews,checkboxArticle,checkboxInterview;
    RadioButton radioButtonMale,radioButtonFemale;
    RadioGroup radioGroupGender;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding
        editTextName = (EditText) findViewById(R.id.edtName);
        editTextBirthday = (EditText) findViewById(R.id.edtBirthday);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
        checkboxNews = (CheckBox) findViewById(R.id.chkNews);
        checkboxArticle = (CheckBox) findViewById(R.id.chkArticle);
        checkboxInterview = (CheckBox) findViewById(R.id.chkInterview);
        editTextEmail = (EditText) findViewById(R.id.edtEmail);
        editTextPassword = (EditText) findViewById(R.id.edtPassword);
        buttonSubmit = (Button) findViewById(R.id.bntSubmit);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get value
                String txtName = editTextName.getText().toString();
                String txtBirthday = editTextBirthday.getText().toString();

                // use radio button group
                String txtGender="";
                int radioButtonId = radioGroupGender.getCheckedRadioButtonId();
                RadioButton radioButtonGender = (RadioButton) findViewById(radioButtonId);
                txtGender=radioButtonGender.getText().toString();

                // OR use radio button directly
                //if (radioButtonMale.isChecked()) {
                //    txtGender="Male";
                //} else {
                //    txtGender="Female";
                //}

                // initial subscribe string with space prevent error while substring
                String txtSubscribe=" ";
                if (checkboxNews.isChecked()) txtSubscribe +="News,";
                if (checkboxArticle.isChecked()) txtSubscribe +="Article,";
                if (checkboxInterview.isChecked()) txtSubscribe +="Interview,";
                txtSubscribe=txtSubscribe.substring(0,txtSubscribe.length()-1);

                String txtEmail = editTextEmail.getText().toString();
                String txtPassword = editTextPassword.getText().toString();

                // send to log cat
                Log.d(TAG, "Name -> "+txtName);
                Log.d(TAG, "Birthday -> "+txtBirthday);
                Log.d(TAG, "Gender -> "+txtGender);
                Log.d(TAG, "Subscribe -> "+txtSubscribe);
                Log.d(TAG, "Email -> "+txtEmail);
                Log.d(TAG, "Password -> "+txtPassword);

                // sent to second activity
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("extName",txtName);
                intent.putExtra("extBirthday",txtBirthday);
                intent.putExtra("extGender",txtGender);
                intent.putExtra("extSubscribe",txtSubscribe);
                intent.putExtra("extEmail",txtEmail);
                intent.putExtra("extPassword",txtPassword);
                startActivity(intent);

            }
        });











    }
}

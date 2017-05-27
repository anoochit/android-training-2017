package net.redlinesoft.a12_sugarorm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.orm.SugarContext;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private static final String TAG = "AddNoteActivity";
    private TextView edtTitle, edtNote;
    private String title, note;
    private Long time;
    private  int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        SugarContext.init(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        edtTitle = (TextView) findViewById(R.id.edtTitle);
        edtNote = (TextView) findViewById(R.id.edtNote);

        final Boolean isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (isEdit) {
            position = getIntent().getIntExtra("position",-1);
            title = getIntent().getStringExtra("title");
            note = getIntent().getStringExtra("note");
            time = getIntent().getLongExtra("time", 0);

            edtTitle.setText(title);
            edtNote.setText(note);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = edtTitle.getText().toString();
                String newNote = edtNote.getText().toString();
                long newTime = System.currentTimeMillis();

                if (isEdit) {
                    // update item
                    List<Note> notes = Note.find(Note.class, "title = ?", title);
                    if (notes.size() > 0) {
                        Note note = notes.get(0);
                        note.title = newTitle;
                        note.note = newNote;
                        note.time = newTime;
                        note.save();
                        Log.d(TAG,"edit");
                    }
                } else {
                    // add item
                    Note note = new Note(newTime, newTitle, newNote);
                    note.save();
                    Log.d(TAG,"add");
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",position);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

}

package net.redlinesoft.a12_sugarorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;
    RecyclerView recyclerView;
    Long initialCount;
    int modifyPos = -1;

    List<Note> notes = new ArrayList<>();
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(getApplicationContext());

        // create table if not exists
        SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv_note);
        int colNum = Integer.parseInt(getResources().getString(R.string.recyclerview_column));
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(colNum, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        recyclerView.setLayoutManager(gridLayoutManager);
        initialCount = Note.count(Note.class);

        Log.d(TAG,"count = "+initialCount);

        if (initialCount>=0) {
            notes = Note.listAll(Note.class);
            noteAdapter = new NoteAdapter(MainActivity.this, notes);
            recyclerView.setAdapter(noteAdapter);

            if (notes.isEmpty())
                Toast.makeText(getApplicationContext(),"No Data!",Toast.LENGTH_SHORT).show();


        }

        noteAdapter.SetOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "click "+position);
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("title", notes.get(position).title);
                intent.putExtra("note", notes.get(position).note);
                intent.putExtra("time", notes.get(position).time);
                modifyPos = position;
                startActivity(intent);
            }
        });

        noteAdapter.SetOnItemLongClickListener(new NoteAdapter.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClicked(View view, int position) {
                Log.d(TAG, "long click "+position);
                return true;
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Note note = notes.get(viewHolder.getAdapterPosition());
                notes.remove(viewHolder.getAdapterPosition());
                noteAdapter.notifyItemRemoved(position);

                note.delete();
                initialCount -= 1;

                Snackbar.make(recyclerView, "Note deleted", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                note.save();
                                notes.add(position, note);
                                noteAdapter.notifyItemInserted(position);
                                initialCount += 1;

                            }
                        })
                        .show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        final long newCount = Note.count(Note.class);
        if (newCount > initialCount) {
            // A note is added
            Log.d(TAG, "Adding new note");
            // Just load the last added note (new)
            Note note = Note.last(Note.class);
            notes.add(note);
            noteAdapter.notifyItemInserted((int) newCount);
            initialCount = newCount;
        }

        if (modifyPos != -1) {
            notes.set(modifyPos, Note.listAll(Note.class).get(modifyPos));
            noteAdapter.notifyItemChanged(modifyPos);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int postion = data.getIntExtra("result",-1);
        if (postion!=-1) {
            Log.d(TAG,"position = "+postion);
            Note note = Note.findById(Note.class,postion);
            noteAdapter.notifyItemChanged(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

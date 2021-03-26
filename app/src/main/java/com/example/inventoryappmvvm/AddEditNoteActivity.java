package com.example.inventoryappmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.PrimaryKey;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ResourceCursorAdapter;
import android.widget.Toast;

import java.io.File;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID="com.example.inventoryappmvvm.EXTRA_ID";
    public static final String EXTRA_TITLE="com.example.inventoryappmvvm.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION="com.example.inventoryappmvvm.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.example.inventoryappmvvm.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(1000);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent=getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));}
        else{
            setTitle("Add note");

        }

    }

    private void saveNote(){
        String title=editTextTitle.getText().toString();
        String description=editTextDescription.getText().toString();
        int priority=numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this,"Please insert a title and description",Toast.LENGTH_SHORT).show();
            return;

        }
        Intent data =new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);


        int id =getIntent().getIntExtra(EXTRA_ID,-1);
        if (id !=-1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);

        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
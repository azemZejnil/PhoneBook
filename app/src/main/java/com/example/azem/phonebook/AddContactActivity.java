package com.example.azem.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class AddContactActivity extends AppCompatActivity {

    public static final String EXTRA_NAME=
        "com.example.azem.phonebook.EXTRA_NAME";

    public static final String EXTRA_NUMBER=
        "com.example.azem.phonebook.EXTRA_NUMBER";


    private EditText editTextname, editTextnumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextname=(EditText)findViewById(R.id.edit_text_name);
        editTextnumber=(EditText)findViewById(R.id.edit_text_number);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add contact");
    }

    private void saveContact(){
        String name = editTextname.getText().toString();
        int number = Integer.parseInt(editTextnumber.getText().toString());

        if(name.trim().isEmpty() || number<0){
            Toast.makeText(this, "Please insert valid data", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_NAME,name);
        i.putExtra(EXTRA_NUMBER,number);

        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.add_contact_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_contact:
                saveContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.example.azem.phonebook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.azem.phonebook.viewmodel.ContactsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_CONTACT_REQUEST= 1;
    private ContactsViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton buttonAddContact = findViewById(R.id.fb_add);
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(i, ADD_CONTACT_REQUEST);
            }
        });

        RecyclerView recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final ContactsAdapter contactsAdapter= new ContactsAdapter();
        recyclerView.setAdapter(contactsAdapter);

        viewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        viewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                contactsAdapter.setContacts(contacts);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_CONTACT_REQUEST && resultCode==RESULT_OK){
            String name = data.getStringExtra(AddContactActivity.EXTRA_NAME);
            int number = data.getIntExtra(AddContactActivity.EXTRA_NUMBER,387);

            Contact contact= new Contact(name,number);
            viewModel.insert(contact);
        }
    }
}

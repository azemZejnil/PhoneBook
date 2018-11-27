package com.example.azem.phonebook;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {

    private ContactsRepository repository;
    private LiveData<List<Contact>> allContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        repository= new ContactsRepository(application);
        allContacts= repository.getAllContacts();
    }

    public void insert(Contact contact){
        repository.insert(contact);
    }

    public void update(Contact contact){
        repository.update(contact);
    }

    public void delete(Contact contact){
        repository.delete(contact);
    }

    public void deleteAllContacts(){
        repository.deleteAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }


}

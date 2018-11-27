package com.example.azem.phonebook.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.azem.phonebook.Contact;
import com.example.azem.phonebook.db.ContactDao;
import com.example.azem.phonebook.db.ContactsDatabase;

import java.util.List;

public class ContactsRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactsRepository(Application application) {
        ContactsDatabase contactsDatabase= ContactsDatabase.getInstace(application);
        contactDao = contactsDatabase.contactDao();
        allContacts= contactDao.getAllContacts();
    }

    public void insert( Contact contact ){
        new InsertContactAsyncTask(contactDao).execute(contact);
    }

    public void update( Contact contact ){
        new UpdateContactAsyncTask(contactDao).execute(contact);
    }

    public void delete( Contact contact ){
        new UpdateContactAsyncTask(contactDao).execute(contact);
    }

    public void deleteAllContacts(){
        new DeleteContactAsyncTask(contactDao).execute();
    }

    public LiveData<List<Contact>> getAllContacts(){
        return allContacts;
    }


    private static class InsertContactAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactDao contactDao;
        private InsertContactAsyncTask(ContactDao contactDao){
            this.contactDao= contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }


    private static class UpdateContactAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactDao contactDao;
        private UpdateContactAsyncTask(ContactDao contactDao){
            this.contactDao= contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.update(contacts[0]);
            return null;
        }
    }


    private static class DeleteContactAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactDao contactDao;
        private DeleteContactAsyncTask(ContactDao contactDao){
            this.contactDao= contactDao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }


    private static class DeleteAllContactAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDao contactDao;
        private DeleteAllContactAsyncTask(ContactDao contactDao){
            this.contactDao= contactDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.deleteAllContacts();
            return null;
        }
    }
}

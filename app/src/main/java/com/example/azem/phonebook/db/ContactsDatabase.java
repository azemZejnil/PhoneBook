package com.example.azem.phonebook.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.azem.phonebook.Contact;

@Database(entities = Contact.class, version = 1)
public abstract class ContactsDatabase extends RoomDatabase {


    private static ContactsDatabase instace;

    public abstract ContactDao contactDao();

    public static synchronized ContactsDatabase getInstace(Context context){
        if(instace==null){
            instace= Room.databaseBuilder(context.getApplicationContext(),
                    ContactsDatabase.class, "contacts_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instace;
    }

    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instace).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDao contactDao;

        private PopulateDbAsyncTask(ContactsDatabase db){
            contactDao= db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.insert(new Contact("Name 1", 432143214));
            contactDao.insert(new Contact("Name 2", 432143212));
            contactDao.insert(new Contact("Name 3", 432143215));
            return null;
        }
    }
}

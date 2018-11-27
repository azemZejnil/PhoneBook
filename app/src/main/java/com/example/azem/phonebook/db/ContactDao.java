package com.example.azem.phonebook.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.azem.phonebook.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert( Contact contact );

    @Update
    void update( Contact contact );

    @Delete
    void delete( Contact contact );

    @Query("DELETE FROM contacts_table")
    void deleteAllContacts();

    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contact>> getAllContacts();
}

package com.omlotecommerce.databindingexample.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.omlotecommerce.databindingexample.two_way_binding.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(Contact contact);

    @Query("SELECT * FROM contacts")
    List<Contact> getAllContacts();

    @Delete
    void delete(Contact contact);


}

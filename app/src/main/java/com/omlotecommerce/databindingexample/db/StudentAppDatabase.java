package com.omlotecommerce.databindingexample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.omlotecommerce.databindingexample.two_way_binding.Contact;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class StudentAppDatabase extends RoomDatabase {

    public abstract ContactDao getContactsDao();

}

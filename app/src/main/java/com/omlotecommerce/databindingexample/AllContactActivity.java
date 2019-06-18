package com.omlotecommerce.databindingexample;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.omlotecommerce.databindingexample.adapter.ContactsDataAdapter;
import com.omlotecommerce.databindingexample.databinding.ActivityAllContactBinding;
import com.omlotecommerce.databindingexample.db.StudentAppDatabase;
import com.omlotecommerce.databindingexample.two_way_binding.Contact;
import com.omlotecommerce.databindingexample.two_way_binding.ContactsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllContactActivity extends AppCompatActivity {

    private StudentAppDatabase studentAppDatabase;
    private ArrayList<Contact> contacts;
    private ContactsDataAdapter contactsDataAdapter;
    public static final int NEW_STUDENT_ACTIVITY_REQUEST_CODE = 1;

    private ActivityAllContactBinding activityAllContactBinding;
    private AllContactsActivityClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAllContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_all_contact);

        handlers = new AllContactsActivityClickHandlers(this);
        activityAllContactBinding.setClickHandler(handlers);

        RecyclerView recyclerView = activityAllContactBinding.rvStudents;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        contactsDataAdapter = new ContactsDataAdapter(this);
        recyclerView.setAdapter(contactsDataAdapter);

        studentAppDatabase = Room.databaseBuilder(getApplicationContext(), StudentAppDatabase.class, "StudentDB")
                .build();

        loadData();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Contact contactToDelete = contacts.get(viewHolder.getAdapterPosition());
                deleteStudent(contactToDelete);
            }
        }).attachToRecyclerView(recyclerView);
    }

    public class AllContactsActivityClickHandlers {

        Context context;

        public AllContactsActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onFABClicked(View view) {
            Intent intent = new Intent(AllContactActivity.this, ContactsActivity.class);
            startActivityForResult(intent, NEW_STUDENT_ACTIVITY_REQUEST_CODE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_STUDENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String name = data.getStringExtra("NAME");
            String email = data.getStringExtra("EMAIL");
            String country = data.getStringExtra("COUNTRY");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
            String currentDate = simpleDateFormat.format(new Date());

            Contact contact = new Contact();
            contact.setName(name);
            contact.setEmail(email);
            contact.setCountry(country);

            addNewStudent(contact);


        }


    }

    void loadData() {

        new GetAllStudentsAsyncTask().execute();

    }

    private class GetAllStudentsAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            contacts = (ArrayList<Contact>) studentAppDatabase.getContactsDao().getAllContacts();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactsDataAdapter.setContacts(contacts);
        }
    }


    private void deleteStudent(Contact contact) {

        new DeleteStudentAsyncTask().execute(contact);

    }

    private class DeleteStudentAsyncTask extends AsyncTask<Contact, Void, Void> {


        @Override
        protected Void doInBackground(Contact... contact) {

            studentAppDatabase.getContactsDao().delete(contact[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }


    private void addNewStudent(Contact contact) {

        new AddNewStudentAsyncTask().execute(contact);

    }

    private class AddNewStudentAsyncTask extends AsyncTask<Contact, Void, Void> {


        @Override
        protected Void doInBackground(Contact... contacts) {

            studentAppDatabase.getContactsDao().insert(contacts[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

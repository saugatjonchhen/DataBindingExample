package com.omlotecommerce.databindingexample.two_way_binding;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.omlotecommerce.databindingexample.R;
import com.omlotecommerce.databindingexample.databinding.ActivityContactsBinding;

public class ContactsActivity extends AppCompatActivity {

    private ActivityContactsBinding activityContactsBinding;
    private ContactsActivityClickHandlers contactsActivityClickHandlers;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        activityContactsBinding = DataBindingUtil.setContentView(this, R.layout.activity_contacts);

        contact = new Contact();
        contactsActivityClickHandlers = new ContactsActivityClickHandlers(this);

        activityContactsBinding.setContact(contact);
        activityContactsBinding.setClickHandler(contactsActivityClickHandlers);


    }

    public class ContactsActivityClickHandlers {

        private Context context;

        public ContactsActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSubmitButtonClicked(View view) {
            if (contact.getName() == null || contact.getEmail() == null || contact.getCountry() == null)
                Toast.makeText(context, "Fields Empty!", Toast.LENGTH_SHORT).show();
            else {
                if (contact.getName().equalsIgnoreCase("") || contact.getEmail().equalsIgnoreCase("") || contact.getCountry().equalsIgnoreCase(""))
                    Toast.makeText(context, "Fields Empty!", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(context, "Submit Clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("NAME", contact.getName());
                    intent.putExtra("EMAIL", contact.getEmail());
                    intent.putExtra("COUNTRY", contact.getCountry());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
    }

}

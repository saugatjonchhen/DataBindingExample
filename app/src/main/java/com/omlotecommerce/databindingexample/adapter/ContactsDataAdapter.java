package com.omlotecommerce.databindingexample.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omlotecommerce.databindingexample.R;
import com.omlotecommerce.databindingexample.databinding.ItemContactBinding;
import com.omlotecommerce.databindingexample.two_way_binding.Contact;

import java.util.ArrayList;

public class ContactsDataAdapter extends RecyclerView.Adapter<ContactsDataAdapter.ItemContactViewHolder> {

    private Context context;
    private ArrayList<Contact> contacts;

    public ContactsDataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

//        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
//        return new StudentViewHolder(itemView);
        ItemContactBinding itemContactBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_contact, viewGroup, false);
        return new ItemContactViewHolder(itemContactBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemContactViewHolder studentViewHolder, int i) {
        Contact currentContact = contacts.get(i);
        studentViewHolder.itemContactBinding.setContact(currentContact);
        ContactDataClickHandler contactDataClickHandler = new ContactDataClickHandler(context, currentContact);
        studentViewHolder.itemContactBinding.setClickHandler(contactDataClickHandler);
    }

    @Override
    public int getItemCount() {

        if (contacts != null) {
            return contacts.size();
        }
        return 0;

    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    class ItemContactViewHolder extends RecyclerView.ViewHolder {

        //        private TextView name, email, country, date;
        private ItemContactBinding itemContactBinding;

        public ItemContactViewHolder(@NonNull ItemContactBinding itemContactBinding) {
            super(itemContactBinding.getRoot());

            this.itemContactBinding = itemContactBinding;

//            name = itemView.findViewById(R.id.text_view_contact_name);
//            email = itemView.findViewById(R.id.text_view_contact_email);
//            country = itemView.findViewById(R.id.text_view_contact_country);

        }
    }

    public class ContactDataClickHandler {
        private Context context;
        private Contact contact;

        public ContactDataClickHandler(Context context, Contact contact) {
            this.context = context;
            this.contact = contact;
        }

        public void onViewClicked(View view) {
            Toast.makeText(context, contact.getEmail(), Toast.LENGTH_SHORT).show();
        }

    }
}

package com.example.azem.phonebook;

import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactHolder> {
    private List<Contact> contactList= new ArrayList<>();


    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_item,viewGroup,false);
        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder contactHolder, int i) {
        Contact currentContact = contactList.get(i);
        contactHolder.index.setText(String.valueOf(i+1));
        contactHolder.name.setText(currentContact.getName());
        contactHolder.number.setText(String.valueOf(currentContact.getNumber()));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setContacts(List<Contact> contacts){
        this.contactList= contacts;
        notifyDataSetChanged();
    }


    class ContactHolder extends RecyclerView.ViewHolder{
        private TextView index;
        private TextView name;
        private TextView number;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            index= (TextView)itemView.findViewById(R.id.text_view_index);
            name= (TextView)itemView.findViewById(R.id.text_view_name);
            number= (TextView)itemView.findViewById(R.id.text_view_number);
        }
    }
}

package com.example.lab5;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ViewContact extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getPhoneContacts();

    }
    private static class Contact {
        public final String name;
        public final String number;

        public Contact(String name, String number) {
            this.name = name;
            this.number = number;
        }
    }

    private void getPhoneContacts(){
        Intent i = getIntent();
        String text = i.getStringExtra("text");
        String[] selectionArgs = {"%" + text + "%"};
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_CONTACTS},0);
        }
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = contentResolver.query(uri, null,
                ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?", selectionArgs,null);
        Log.i("Contact","Total # of Contacts :::"+Integer.toString(cursor.getCount()));
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String contactName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactPhone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.i("Contact", "Contact Name : " + contactName + "  Contact Phone : " + contactPhone);
                contacts.add(new Contact (contactName,contactPhone));
            }
            cursor.close();
        }

        ListView contactList = findViewById(R.id.ListContacts);
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        contactList.setAdapter(adapter);
    }
    private class ContactAdapter extends ArrayAdapter<Contact> {
        private List<Contact> contacts;
        private Context context;

        public ContactAdapter(Context context,List<Contact> contacts) {
            super(context, 0, contacts);
            this.context = context;
            this.contacts = contacts;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Contact contact = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(android.R.layout.simple_list_item_2, parent,false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(contact.name);
            ((TextView) convertView.findViewById(android.R.id.text2))
                    .setText(contact.number);
            return convertView;
        }
    }
}
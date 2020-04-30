package com.example.intentimplicito2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText email = null;
    private EditText phoneNumber = null;
    private EditText Firstname = null;
    private EditText Lastname = null;
    private Button   addContact;
    public String opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContact = (Button) findViewById(R.id.addcontact);
        addContact.setOnClickListener(this);

        ArrayList<String> list = new ArrayList<>();

        list.add("Home"); //add the item
        list.add("Mobile");
        list.add("Work");

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        //CREATE INTENT
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        //GET DE INFORMATION
        Firstname = (EditText) findViewById(R.id.name);
        Lastname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phone);
        //phonetype = (EditText) findViewById(R.id.phonetype);

        int Type = 0;
        if(opcion.equals("Home")){
            Type = 1;
        }
        if(opcion.equals("Mobile")){
            Type = 2;
        }
        if(opcion.equals("Work")){
            Type = 3;
        }

        intent
                .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.getText())
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, Type)
                .putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText())
                .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                .putExtra(ContactsContract.Intents.Insert.NAME, Firstname.getText() + " " + Lastname.getText());

        //SEND INFORMATION FOR THE FORM
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        opcion = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

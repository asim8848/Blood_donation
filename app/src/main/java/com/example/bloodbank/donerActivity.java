package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.NavigableMap;

public class donerActivity extends AppCompatActivity {
    EditText Name,City,Contect;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner);
        Name=findViewById(R.id.Name);
        City=findViewById(R.id.city);
        Contect=findViewById(R.id.contect);
        submit=findViewById(R.id.submit);
        Spinner bloodgrp=findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.bloodType, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        bloodgrp.setAdapter(adapter);

        FirebaseDatabase database=FirebaseDatabase.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Donor");
                DatabaseReference usersRef = ref.child("users");
                HashMap<String, Object>m=new HashMap<String,Object>();
                m.put("Name", Name.getText().toString());
                m.put("City", City.getText().toString());
                m.put("Contact", Contect.getText().toString());
                m.put("blood group", bloodgrp.getSelectedItem().toString());
                usersRef.child("User").push().setValue(m);
            }
        });

    }
}
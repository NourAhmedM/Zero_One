package com.example.android.secrethands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;

public class AddSession extends AppCompatActivity {
    DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
       // picker=(DatePicker)findViewById(R.id.datePicker);
    }
}

package com.example.android.secrethands;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.secrethands.datastructures.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSession extends AppCompatActivity {
    private DatePicker picker;
    private TimePicker timePicker;
    private EditText price;
    private EditText editText;
    private Button button;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid()).child("Sessions");
        picker=(DatePicker)findViewById(R.id.datapicker);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        editText=(EditText)findViewById(R.id.duration);
        price=(EditText)findViewById(R.id.price);
        button=(Button)findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.length()==0){
                    editText.setError("Please Insert Duration");
                    return;
                }
                if(price.length()==0){
                    price.setError("Please Insert Duration");
                    return;
                }
               String sessionUId= databaseReference.push().getKey();
                Session session =new Session(Integer.valueOf(editText.getText().toString()),getCurrentDate()+"  "+getCurrenTime(),false,firebaseAuth.getCurrentUser().getUid().toString(),null,sessionUId,Integer.valueOf(price.getText().toString()));
                databaseReference.child(sessionUId).setValue(session).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            finish();
                        }else{
                            Toast.makeText(AddSession.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append((picker.getMonth() + 1)+"/");//month is 0 based
        builder.append(picker.getDayOfMonth()+"/");
        builder.append(picker.getYear());
        return builder.toString();
    }
    public String getCurrenTime(){
        StringBuilder builder=new StringBuilder();
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        builder.append(String.valueOf(hour));
        builder.append(":"+String.valueOf(min));
        return builder.toString();
    }
}

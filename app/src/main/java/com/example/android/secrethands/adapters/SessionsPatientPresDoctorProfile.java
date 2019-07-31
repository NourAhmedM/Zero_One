package com.example.android.secrethands.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.secrethands.R;
import com.example.android.secrethands.VideoCall;
import com.example.android.secrethands.datastructures.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SessionsPatientPresDoctorProfile extends ArrayAdapter<Session> {


    private FirebaseDatabase mfirebase;
    private DatabaseReference mdataReference;
    private DatabaseReference mdataReferenceUsername;

    public SessionsPatientPresDoctorProfile(Context context, int resource, List<Session> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.sessions_userpres_doctorprofile, parent, false);
        }

        Session Current = getItem(position);

        TextView duration = (TextView) convertView.findViewById(R.id.durationViewN);
        final TextView docUsername = (TextView) convertView.findViewById(R.id.doctorUsernameViewN);
        TextView startingTime = (TextView) convertView.findViewById(R.id.startingTimeViewN);
        final CircleImageView doctorImage=(CircleImageView) convertView.findViewById(R.id.doctorImageViewN);
        ImageView book=(ImageView) convertView.findViewById(R.id.booknow);
        final TextView price = (TextView) convertView.findViewById(R.id.price);
        price.setText(String.valueOf(Current.getPrice()));

        duration.setText(String.valueOf(Current.getDuration()));
        startingTime.setText(Current.getStartTime());

        mfirebase=FirebaseDatabase.getInstance();
        mdataReference=mfirebase.getReference().child("Users");
        mdataReferenceUsername=mfirebase.getReference().child("Users");

        mdataReference=mdataReference.child(Current.getDoctorId()).child("photoURL");
        mdataReferenceUsername=mdataReferenceUsername.child(Current.getDoctorId()).child("username");

        mdataReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String urlphoto = dataSnapshot.getValue(String.class);
                Glide.with(doctorImage.getContext())
                        .load(urlphoto)
                        .into(doctorImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        mdataReferenceUsername.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String usernam = dataSnapshot.getValue(String.class);
                docUsername.setText(usernam);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), VideoCall.class);
                getContext().startActivity(intent);
            }
        });




        return convertView;
}}

package com.example.android.secrethands.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.secrethands.R;
import com.example.android.secrethands.datastructures.Chat;
import com.example.android.secrethands.datastructures.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nour Ahmed on 7/29/2019.
 */

public class SessionAdapter extends ArrayAdapter<Session> {

    private FirebaseDatabase mfirebase;
    private DatabaseReference mdataReference;
    private DatabaseReference mdataReferenceUsername;

    public SessionAdapter(Context context, int resource, List<Session> objects) {
        super(context, 0, objects);
        mfirebase=FirebaseDatabase.getInstance();
        mdataReference=mfirebase.getReference().child("Users");
        mdataReferenceUsername=mfirebase.getReference().child("Users");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.session_list_item, parent, false);
        }

        Session Current = getItem(position);

        TextView duration = (TextView) convertView.findViewById(R.id.durationViewN);
        final TextView docUsername = (TextView) convertView.findViewById(R.id.doctorUsernameViewN);
        TextView startingTime = (TextView) convertView.findViewById(R.id.startingTimeViewN);
        final CircleImageView doctorImage=(CircleImageView) convertView.findViewById(R.id.doctorImageViewN);

        duration.setText(String.valueOf(Current.getDuration()));
        startingTime.setText(Current.getStartTime());


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



        return convertView;

    }
}

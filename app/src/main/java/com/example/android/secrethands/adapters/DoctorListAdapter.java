package com.example.android.secrethands.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.secrethands.R;
import com.example.android.secrethands.datastructures.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Aly on 7/30/2019.
 */

public class DoctorListAdapter extends ArrayAdapter<Doctor> {

    private FirebaseDatabase mfirebase;
    private DatabaseReference mdataReference;


    public DoctorListAdapter(@NonNull Context context, @NonNull List<Doctor> objects) {
        super(context, 0, objects);
        mfirebase=FirebaseDatabase.getInstance();
        mdataReference=mfirebase.getReference().child("Users");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null) {
            view= LayoutInflater.from(getContext()).inflate(R.layout.doctor_list_item,parent,false);
        }
        Doctor current= (Doctor) getItem(position);
        TextView doctorname =(TextView) view.findViewById(R.id.doctor_name);
        String content = current.getUsername();
        doctorname.setText(content);

        final CircleImageView doctorImageView = (CircleImageView) view.findViewById(R.id.doctor_image_list);
       // Toast.makeText(getContext(), current.getPhotoURL(), Toast.LENGTH_SHORT).show();

                Glide.with(doctorImageView.getContext())
                        .load(current.getPhotoURL())
                        .into(doctorImageView);



        return view;
    }
}

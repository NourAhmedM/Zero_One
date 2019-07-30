package com.example.android.secrethands.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.secrethands.R;
import com.example.android.secrethands.datastructures.Session;

import java.util.List;

/**
 * Created by Aly on 7/30/2019.
 */

public class SessionAdapterDoctor extends ArrayAdapter<Session> {
    public SessionAdapterDoctor(@NonNull Context context, @NonNull List<Session> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null) {
            view= LayoutInflater.from(getContext()).inflate(R.layout.session_doctor_list_item,parent,false);
        }

        Session Current = getItem(position);

        TextView duration = (TextView) view.findViewById(R.id.duration);
        duration.setText(String.valueOf(Current.getDuration()));

        TextView startDate = (TextView) view.findViewById(R.id.start_time);
        startDate.setText(String.valueOf(Current.getDuration()));


        ImageView booked = (ImageView) view.findViewById(R.id.booked);
        if(Current.isBooked()){
            booked.setImageResource(R.drawable.correct_doctor);
        }
        else {
            booked.setImageResource(R.drawable.wrong_doctor);
        }

        return view;
    }
}

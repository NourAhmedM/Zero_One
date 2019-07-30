package com.example.android.secrethands.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.secrethands.R;
import com.example.android.secrethands.datastructures.Chat;
import com.example.android.secrethands.datastructures.Session;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Nour Ahmed on 7/29/2019.
 */

public class SessionAdapter extends ArrayAdapter<Session> {

    public SessionAdapter(Context context, int resource, List<Session> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.session_list_item, parent, false);
        }
        return convertView;

    }
}

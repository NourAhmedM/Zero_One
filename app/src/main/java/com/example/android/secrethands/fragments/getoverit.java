package com.example.android.secrethands.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.secrethands.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class getoverit extends Fragment {
    OnImageClickListener mCallback;
    public interface OnImageClickListener
    {
        void onImageSelected(int i);
    }
    public void initCallback(OnImageClickListener onImageClickListener){
        this.mCallback=onImageClickListener;


    }


    public getoverit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_getoverit, container, false);
        TextView routine=(TextView) view.findViewById(R.id.routinemeditation);
        routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onImageSelected(3);
            }
        });

        return view;
    }

}

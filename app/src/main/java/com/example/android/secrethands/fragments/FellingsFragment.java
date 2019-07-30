package com.example.android.secrethands.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.secrethands.R;
import com.example.android.secrethands.adapters.FeelingdAdapter;
import com.example.android.secrethands.datastructures.Disorders;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FellingsFragment extends Fragment {
    FellingsFragment.OnImageClickListener mCallback;
    public interface OnImageClickListener
    {
        void onImageSelected();
    }
    public void initCallback(FellingsFragment.OnImageClickListener onImageClickListener){
        this.mCallback=onImageClickListener;


    }


    public FellingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fellings, container, false);
        ArrayList<Disorders> disorders = new ArrayList<Disorders>();
        disorders.add(new Disorders("Depression - الاكتئاب",R.drawable.depression, 0, R.string.dep_description, R.string.dep_symptoms, R.string.dep_treatments));
        disorders.add(new Disorders("ِAutism - التوحد",R.drawable.autism, 0, R.string.aut_description, R.string.aut_symptoms, R.string.aut_treatments));
        disorders.add(new Disorders("Anxiety - القلق",R.drawable.anxiety, 0, R.string.anx_description, R.string.anx_symptoms, R.string.anx_treatments));
        FeelingdAdapter mAdapter = new FeelingdAdapter(getContext(), disorders);
        ListView list = view.findViewById(R.id.list);
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.onImageSelected();
            }
        });
        return view;
    }

}

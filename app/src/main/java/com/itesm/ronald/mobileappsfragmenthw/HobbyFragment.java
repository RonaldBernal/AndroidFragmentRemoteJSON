package com.itesm.ronald.mobileappsfragmenthw;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HobbyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String hobbyStr;
    private OnFragmentInteractionListener mListener;
    private HobbyFragment fragment;

    public static HobbyFragment newInstance() {
        HobbyFragment fragment = new HobbyFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public HobbyFragment() {
        fragment = this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void onButtonPressed(View v) {
        if (mListener != null) {}
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hobby, container, false);

        final EditText hobby = (EditText)v.findViewById(R.id.hobbyText);
        Button saveHobby = (Button)v.findViewById(R.id.SaveHobbyBttn);
        Button loadHobby = (Button)v.findViewById(R.id.LoadHobbyBttn);

        saveHobby.setOnClickListener(new View.OnClickListener() {
            // TODO: validate hobby !=null
            @Override
            public void onClick(View v) {mListener.onHobbySave(hobby.getText().toString(), fragment);}
        });

        loadHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hobby.setText(hobbyStr);
            }
        });

        return v;

    }

    public void getHobbyStr(String hobby){
        hobbyStr = hobby;
    }

    public interface OnFragmentInteractionListener {
        public void onHobbySave(String message, HobbyFragment fragment);
    }

}

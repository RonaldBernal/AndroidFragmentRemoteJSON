package com.itesm.ronald.mobileappsfragmenthw;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class FriendFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ListView list;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private FriendAdapter adapter;
    private static Activity activity;
    private OnFragmentInteractionListener mListener;

    public static FriendFragment newInstance(Activity act) {
        FriendFragment fragment = new FriendFragment();
        Bundle args = new Bundle();
        activity = act;
        return fragment;
    }

    public FriendFragment() {

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
        if (mListener != null) {

        }
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
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        final Button friendsBttn = (Button)view.findViewById(R.id.LoadFriendsBttn);

        list = (ListView)view.findViewById(R.id.listView);
        adapter = new FriendAdapter(activity, friends);
        list.setAdapter(this.adapter);

        friendsBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFriendClick(adapter,friends,list);
                list.setLayoutParams(new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
                ));
                friendsBttn.setVisibility(View.GONE);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.toogleVisibility(list, position);
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        public void onFriendClick(FriendAdapter adapter, ArrayList<Friend> friends, ListView list);
    }

}

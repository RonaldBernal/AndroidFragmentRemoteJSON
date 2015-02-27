package com.itesm.ronald.mobileappsfragmenthw;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ronald on 2/16/2015.
 */
public class FriendAdapter extends BaseAdapter implements ListAdapter{
    private ArrayList<Friend> friends;
    private Activity activity;

    public FriendAdapter(Activity activity, ArrayList<Friend> friends){
        this.activity = activity;
        this.friends = friends;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.textName);
        TextView hobby = (TextView) convertView.findViewById(R.id.textHobby);
        TextView age = (TextView) convertView.findViewById(R.id.textAge);
        TextView phone = (TextView) convertView.findViewById(R.id.textPhone);
        TextView address = (TextView) convertView.findViewById(R.id.textAddress);

        Friend f = friends.get(position);
        name.setText(f.getName());
        hobby.setText("Hobby: " + f.getHobby());
        age.setText("Age: " + f.getAge());
        phone.setText("Phone: " + f.getPhone());
        address.setText("Address: " + f.getAddress());

        age.setVisibility(View.GONE);
        phone.setVisibility(View.GONE);
        address.setVisibility(View.GONE);

        return convertView;
    }

    public void toogleVisibility(ListView list, int position){
        View item = list.getChildAt(position);
        Boolean visible = this.friends.get(position).getVisible();
        if(!visible) {
            item.findViewById(R.id.textAge).setVisibility(View.VISIBLE);
            item.findViewById(R.id.textPhone).setVisibility(View.VISIBLE);
            item.findViewById(R.id.textAddress).setVisibility(View.VISIBLE);
        }else {
            item.findViewById(R.id.textAge).setVisibility(View.GONE);
            item.findViewById(R.id.textPhone).setVisibility(View.GONE);
            item.findViewById(R.id.textAddress).setVisibility(View.GONE);
        }
        friends.get(position).setVisible(!visible);
    }
}













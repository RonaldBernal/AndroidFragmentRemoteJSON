package com.itesm.ronald.mobileappsfragmenthw;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class MainActivity extends ActionBarActivity implements FriendFragment.OnFragmentInteractionListener,
        HobbyFragment.OnFragmentInteractionListener {
    private Properties p;
    private boolean friendFragment;
    private boolean hobbyFragment;
    private String hobbyStr;
    private String jsonURL = "https://raw.githubusercontent.com/RonaldBernal/AmdroidRemoteJSON/master/Contacts.json";
    private final String FILENAME = "properties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.tec_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        loadHobby();

        friendFragment = false;
        hobbyFragment = false;
    }

    public void addFriendFragment(View v) {
        if (!friendFragment && !hobbyFragment) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            FriendFragment fragment = FriendFragment.newInstance(this);
            transaction.add(R.id.FragmentContainer, fragment, "fragment");
            transaction.commit();

            friendFragment = true;
        } else if (!friendFragment && hobbyFragment) {
            removeFragment("fragment");
            addFriendFragment(v);
        } else {
            removeFragment("fragment");
        }
    }

    public void addHobbyFragment(View v) {
        if (!friendFragment && !hobbyFragment) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            HobbyFragment fragment = HobbyFragment.newInstance();
            fragment.getHobbyStr(hobbyStr);
            transaction.add(R.id.FragmentContainer, fragment, "fragment");
            transaction.commit();

            hobbyFragment = true;
        } else if (friendFragment && !hobbyFragment) {
            removeFragment("fragment");
            addHobbyFragment(v);
        } else {
            removeFragment("fragment");
        }
    }

    public void removeFragment(String tag) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment frag = manager.findFragmentByTag(tag);

        transaction.remove(frag);
        transaction.commit();

        friendFragment = false;
        hobbyFragment = false;
    }

    public void loadHobby(){
        hobbyStr = "";
        File file = new File(getFilesDir(), FILENAME);
        p = new Properties();

        try {
            if (file.exists()) {
                FileInputStream fis = openFileInput(FILENAME);
                p.loadFromXML(fis);
                hobbyStr = p.getProperty("hobby");
                fis.close();
            }
        }catch(IOException e){
            Log.e("MAIN", "Properties error ");
            e.printStackTrace();
        }
    }

    @Override
    public void onFriendClick(FriendAdapter adapter, ArrayList<Friend> friends, ListView list) {
        JsonRequest jreq = new JsonRequest(this, adapter, friends, list);
        jreq.execute(jsonURL);
    }

    @Override
    public void onHobbySave(String message, HobbyFragment fragment) {
        p.setProperty("hobby", message);
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            p.storeToXML(fos, null);
            fos.close();
            Toast.makeText(getApplicationContext(),
                    "Hobby saved successfully",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {

            e.printStackTrace();
        }
        loadHobby();
        fragment.getHobbyStr(hobbyStr);
    }

}
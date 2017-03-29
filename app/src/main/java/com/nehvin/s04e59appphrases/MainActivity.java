package com.nehvin.s04e59appphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int childCount;
    Field[] fields = null;
    Map<String, String> mapOfStrings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fields = R.raw.class.getFields();
        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridLayout);
        childCount = gridlayout.getChildCount();
        Map<String, String> mapOfStrings = new HashMap<>(childCount+childCount/2);
        for(int i = 0; i<childCount; i++)
        {
            Log.i(gridlayout.getChildAt(i).getTag().toString(), fields[i].getName());
            mapOfStrings.put(gridlayout.getChildAt(i).getTag().toString(), fields[i].getName());
        }
    }

    public void buttonTapped(View view)
    {
        int id = view.getId();
        String label = view.getResources().getResourceEntryName(id);
        int mediaID = getResources().getIdentifier(label, "raw", "com.nehvin.s04e59appphrases");
        final MediaPlayer mplayer = MediaPlayer.create(this,mediaID);

        mplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mplayer.release();
            }
        });

        mplayer.start();
   }
}
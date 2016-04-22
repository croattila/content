package com.example.hp.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.support.v4.widget.SimpleCursorAdapter;

public class MainActivity extends ActionBarActivity {
    private static final String[] COLUMNS_TO_BE_BOUND = new String[]{
            UserDictionary.Words.WORD,
            UserDictionary.Words.FREQUENCY,

    };
    private static final int[] LAYOUT_ITEMS_TO_FILL = new int[]{
            android.R.id.text1,
            android.R.id.text2
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView dictListView = (ListView) findViewById(R.id.dictionary_list_view);

        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        Log.e("TEST", UserDictionary.Words.CONTENT_URI.toString());

        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                cursor,
                COLUMNS_TO_BE_BOUND,
                LAYOUT_ITEMS_TO_FILL,
                0);

    dictListView.setAdapter(adapter);
//        cursor.close();


    }
}

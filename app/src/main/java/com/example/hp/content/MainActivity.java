package com.example.hp.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView dictTextView = (TextView) findViewById(R.id.dictionary_text_view);

        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);
        Log.e("TEST", UserDictionary.Words.CONTENT_URI.toString());


        try {
            dictTextView.setText(" A Szotarban van " + cursor.getCount() + " szo\n");
            dictTextView.append("Sorok: " + UserDictionary.Words._ID + " - " + UserDictionary.Words.FREQUENCY
                    + " - " + UserDictionary.Words.WORD);

            int idColumn = cursor.getColumnIndex(UserDictionary.Words._ID);
            int frequencyColumn = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
            int wordColumn = cursor.getColumnIndex(UserDictionary.Words.WORD);

            while (cursor.moveToNext()) {

                int id = cursor.getInt(idColumn);
                int frequency = cursor.getInt(frequencyColumn);
                String word = cursor.getString(wordColumn);

                dictTextView.append(("\n" + id + " - " + frequency + " - " + word));
            }
        } finally {
            //cursor.close();
        }


    }
}

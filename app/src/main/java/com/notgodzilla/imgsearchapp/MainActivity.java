package com.notgodzilla.imgsearchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_TEXT_EXTRA = "search_text_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button submitButton = (Button)findViewById(R.id.img_search_button);

        submitButton.setEnabled(false);
        final EditText searchBox = (EditText)findViewById(R.id.search_box);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 5 ) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchResultsIntent = new Intent(getApplicationContext(), SearchResults.class);
                searchResultsIntent.putExtra(SEARCH_TEXT_EXTRA, searchBox.getText().toString());
                startActivity(searchResultsIntent);
            }
        });

    }

}

package com.yuskay.createdatabase;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Word;
import helper.MyHelper;

public class SearchWordActivity extends AppCompatActivity {

    EditText etSearch;
    Button btnSearch;
    ListView  lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch =findViewById(R.id.etSearch);

        btnSearch = findViewById(R.id.btnSearch);

        lstView = findViewById(R.id.lstView);


        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Word> wordList = new ArrayList<>();
                wordList = myHelper.GetWordByName(etSearch.getText().toString(),sqLiteDatabase);

                HashMap<String, String> hashMap = new HashMap<>();

                for (int i = 0; i < wordList.size(); i++)
                {
                    hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
                }

                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(SearchWordActivity.this,
                        android.R.layout.simple_list_item_1, new ArrayList<String>(hashMap.keySet())

                );
                lstView.setAdapter(stringArrayAdapter);
            }
        });
    }
}

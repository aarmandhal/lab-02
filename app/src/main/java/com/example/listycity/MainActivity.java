package com.example.listycity;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        Button addButton = (Button) findViewById(R.id.add);
        Button deleteButton = (Button) findViewById(R.id.delete);
        Button confirmButton = (Button) findViewById(R.id.confirm_button);
        EditText getEntry = findViewById(R.id.new_entry);

        String []cities = {"Edmonton","Vancouver","Moscow","Sydney","Berlin","Vienna","Tokyo","Beijing","Osaka","New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEntry.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = getEntry.getText().toString().trim();
                if(!newCity.isEmpty()) {
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    getEntry.setText("Enter a City");
                    getEntry.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);
                }
            }
        });


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedPosition != -1) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}
package com.example.apcw2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apcw2.Adapter.APAdapter;
import com.example.apcw2.Database.DatabaseHelper;
import com.example.apcw2.Model.APModel;
import com.example.apcw2.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    // Declare variables
    APAdapter accountAdapter;
    Button goback_btn;
    RecyclerView listview;
    ArrayList<APModel> accountModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        fetchAccountData();
        goBack();
    }


    public void fetchAccountData(){
        listview = findViewById(R.id.listview);
        DatabaseHelper dpHelper = new DatabaseHelper(this);
        accountModels = dpHelper.fetchAccountData();
        accountAdapter = new APAdapter(accountModels, this);
        listview.setAdapter(accountAdapter);
        listview.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goBack() {
        goback_btn = findViewById(R.id.backbtn);
        goback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

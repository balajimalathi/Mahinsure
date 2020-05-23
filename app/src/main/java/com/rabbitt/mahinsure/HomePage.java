package com.rabbitt.mahinsure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rabbitt.mahinsure.adapter.DemoAdapter;
import com.rabbitt.mahinsure.model.demo;
import com.rabbitt.mahinsure.model.inspection;
import com.rabbitt.mahinsure.prefs.PrefsManager;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements DemoAdapter.OnRecyleItemListener {

    private static final String TAG = "malu";

    RecyclerView recyclerView;

    DemoAdapter recycleadapter;
    private List<demo> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextpage);

        PrefsManager prefsManager = new PrefsManager(this);
        prefsManager.setFirstTimeLaunch(true);

        recyclerView = findViewById(R.id.pending_recycler);

        for (int i = 0; i <= 2; i++) {
            demo model = new demo();
            model.setData("Reference No: " + i);
            data.add(model);
        }

        SharedPreferences shrp = getSharedPreferences(Config.TOKEN_PREF, MODE_PRIVATE);
        Log.i(TAG, "Token>>>>>>>>: "+shrp.getString("token","Null"));
        updaterecyclershit(data);
    }

    private void updaterecyclershit(List<demo> data) {
        recycleadapter = new DemoAdapter(data, this,  this);
        LinearLayoutManager reLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(reLayout);
        reLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(recycleadapter);
        recycleadapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnItemClick(int position) {
        Log.i(TAG, "OnItemClick: "+position);
        Log.i(TAG, "pos " + position);
        demo model = data.get(position);
        String data = model.getData();

        Log.i(TAG, "pos " + data);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("ref_no", data);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed: ");
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    public void settingpage(View view) {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }


}

package com.rabbitt.mahinsure.notification;

import android.content.Context;
import android.util.Log;

import com.rabbitt.mahinsure.R;
import com.rabbitt.mahinsure.model.alert;
import com.rabbitt.mahinsure.model.inspection;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    public static final String TAG = "realm";

    Realm realm;
    Context context;

    public RealmHelper(Context context) {
        this.context = context;
    }

    public void realminsert(final JSONObject json) {
        realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NotNull Realm realm) {
                try {
                    inspection ins = realm.createObject(inspection.class, json.get("ref_no"));
                    ins.setCus_name(json.get("cus_name").toString());
                    ins.setV_no(json.get("v_no").toString());
                    ins.setDate(json.get("date").toString());
                    ins.setMonth(json.get("month").toString());
                    ins.setYear(json.get("year").toString());
                    ins.setRec(json.get("rec").toString());
                    ins.setSub("NULL");
                    ins.setApp("NULL");
                    ins.setContent("NIL");
                    ins.setColor(1);
                    Log.i(TAG, "execute: " +  json.get("date_").toString());
                } catch (Exception e) {
                    Log.i(TAG, "Exception: " + e.getMessage());
                }

            }
        });

    }

    public void realmupdate(final JSONObject json) {
        try {

            Log.i(TAG, "realmupdate: " + json.get("ref_no").toString());

            final int i = Integer.parseInt(json.get("color").toString());
            realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NotNull Realm realm) {
                    try {
                        RealmResults<inspection> persons = realm.where(inspection.class).equalTo("ref_no", json.get("ref_no").toString()).findAll();
                        if (i == 0)
                        {
                            persons.setString("app", json.get("date_").toString());
                        }
                        persons.setInt("color", Integer.parseInt(json.get("color").toString()));
                    } catch (Exception e) {
                        Log.i(TAG, "Exception: " + e.getMessage());
                    }

                }
            });

        } catch (Exception e) {
            Log.i(TAG, "realmupdate: " + e.toString());
        }
    }

    public void realmcorrection(final JSONObject json) {
        try {

            Log.i(TAG, "realmupdate: " + json.get("ref_no"));

            realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NotNull Realm realm) {
                    try {
                        RealmResults<inspection> insp = realm.where(inspection.class).equalTo("ref_no", json.get("ref_no").toString()).findAll();
                        insp.setInt("color", Integer.parseInt(json.get("color").toString()));
                        insp.setString("content", json.get("content").toString());
                    } catch (Exception e) {
                        Log.i(TAG, "Exception: " + e.getMessage());
                    }

                }
            });

        } catch (Exception e) {
            Log.i(TAG, "realmupdate: " + e.toString());
        }
    }
}

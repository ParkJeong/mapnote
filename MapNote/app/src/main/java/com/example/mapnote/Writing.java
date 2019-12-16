package com.example.mapnote;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Writing extends AppCompatActivity {

    private EditText mEditPlace, mEditTitle, mEditContent;

    static final String DB_THEPLACE = "ThePlace.db";
    static final String TABLE_PLACE = "Place";
    static final int DB_VERSION = 2;
    private DBHelper dbHelper;
    private LocationManager mLocMgr;
    private int mLocCnt = 0;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        mEditPlace = (EditText) findViewById(R.id.editPlace);
        mEditTitle = (EditText) findViewById(R.id.editTitle);
        mEditContent = (EditText) findViewById(R.id.editContent);
        mLocMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        ab = getSupportActionBar();
        ab.setTitle("글쓰기");
    }

    @Override
    protected void onStart(){
        super.onStart();



    }

    @Override
    protected void onStop(){
        super.onStop();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu) ;

        return super.onCreateOptionsMenu(menu);

    }


    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.btnListMode:
                setContentView(R.layout.activity_list_mode);
                ab.setTitle("시간순");


                break;
            case R.id.btnSettingsMode:
                setContentView(R.layout.activity_settings_mode);
                ab.setTitle("설정");
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save :
                // TODO : process the click event for action_search item.
                Toast.makeText(this,"저장", Toast.LENGTH_SHORT).show();


                SQLiteDatabase db;
                String sql;
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String getTime = sdf.format(date);

                String place = mEditPlace.getText().toString();
                String title = mEditTitle.getText().toString();
                String content = mEditContent.getText().toString();

//                db = dbHelper.getWritableDatabase();
//                sql = String.format("INSERT INTO " + TABLE_PLACE + "VALUES('"
//                        + place + "','" + title + "'," + content + "'," + date + "',"
//                        + section + "'," location + "'," center + "'," radius + "',0);");

                return true ;
            // ...
            // ...
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }


    static class DBHelper extends SQLiteOpenHelper {

        //생성자 - database 파일을 생성한다.
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //DB 처음 만들때 호출. - 테이블 생성 등의 초기 처리.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_PLACE + " (placename TEXT, title TEXT, content TEXT, date TEXT, section TEXT, location TEXT, center TEXT, radius INTEGER);");
//result.append("\nt3 테이블 생성 완료.");
        }

        //DB 업그레이드 필요 시 호출. (version값에 따라 반응)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
            onCreate(db);
        }

    }


    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

}

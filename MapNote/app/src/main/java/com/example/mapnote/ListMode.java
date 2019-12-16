package com.example.mapnote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListMode extends AppCompatActivity {

    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mode);

        ab = getSupportActionBar();
        ab.setTitle("시간순");
    }


    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.btnWritingMode:
                Intent intent = new Intent(this, Writing.class);
                startActivity(intent);
                break;
            case R.id.btnSettingsMode:
                intent = new Intent(this, SettingsMode.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_listmode, menu) ;

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save :
                // TODO : process the click event for action_search item.
                Toast.makeText(this,"게시글 검색", Toast.LENGTH_SHORT).show();
                return true ;
            // ...
            // ...
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }


}

package com.example.harry_000.icshop;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Activity;
import android.widget.TextView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);

        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        configureImageButton();
        configureImageButton1();
        configureImageButton3();
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.layout_left_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(MainActivity.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_info:
                Intent intent2 = new Intent(MainActivity.this, info4creator.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    /*all store button, imageButton1*/
    private void configureImageButton() {
        Button btn = (Button) findViewById(R.id.MainButton3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StoreListActivity.class);
                startActivity(intent);
            }
        });
    }

    /*all brand button, imageButton2*/
    private void configureImageButton1() {
        Button btn = (Button) findViewById(R.id.MainButton2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrandListActivity.class);
                startActivity(intent);
            }
        });
    }


    /*Category button, imageButton4*/
    private void configureImageButton3() {
        Button btn = (Button) findViewById(R.id.MainButton1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryExpandActivity.class);
                startActivity(intent);
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu:
                SlidingMenu menu = new SlidingMenu(this);
                menu.setMode(SlidingMenu.RIGHT);
                menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                menu.setShadowWidthRes(R.dimen.shadow_width);
                menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
                menu.setFadeDegree(0.35f);
                menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
                menu.setMenu(R.layout.layout_left_menu);
                menu.showMenu();
                return true;
            default:
                return true;
        }

    }
}

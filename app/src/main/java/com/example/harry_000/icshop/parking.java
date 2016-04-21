package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by JIYUAN JIN on 2016/4/21.
 */
public class parking extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking);
        initComponent();

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(parking.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(parking.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_parking:
                Intent intent2 = new Intent(parking.this, parking.class);
                startActivity(intent2);
                break;
            case R.id.tab_info:
                Intent intent3 = new Intent(parking.this, info4creator.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    private void initComponent() {
        imageView = (ImageView) findViewById(R.id.imgaeview);
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

package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class BrandListActivity extends AppCompatActivity {
    private List<Brand> Brands = new ArrayList<Brand>();
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);

        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.layout_left_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        db = new MyDatabase(this);
        Brands = db.getAllBrands(); // you would not typically call this on the main thread
        ArrayAdapter<Brand> adapter = new ArrayAdapter<Brand>(this,
                android.R.layout.simple_list_item_1, Brands);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);

        //listening to single list item on click
        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                Brand clickedObject = (Brand) parent.getAdapter().getItem(position);

                String strid = String.valueOf(clickedObject.getID());
                String strBrand = clickedObject.getName();
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), BrandStoresActivity.class);
                // sending data to new activity
                i.putExtra("brandID", strid);
                i.putExtra("brandName", strBrand);
                startActivity(i);

            }
        });
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(BrandListActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(BrandListActivity.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_parking:
                Intent intent2 = new Intent(BrandListActivity.this, parkingActivity.class);
                startActivity(intent2);
                break;
            case R.id.tab_info:
                Intent intent3 = new Intent(BrandListActivity.this, info4creator.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
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
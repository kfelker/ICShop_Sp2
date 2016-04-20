package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weirun on 2015/11/17.
 */
public class StoreListActivity  extends AppCompatActivity {

    private List<Store> Store = new ArrayList<Store>();
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        Intent i = getIntent();
        String id = i.getStringExtra("storeID");
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
        Store = db.getAllBusiness(); // you would not typically call this on the main thread
        ArrayAdapter<Store> adapter = new ArrayAdapter<Store>(this,
                android.R.layout.simple_list_item_1, Store);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);

        //listening to single list item on click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                Store clickedObject = (Store) parent.getAdapter().getItem(position);

                String strid = String.valueOf(clickedObject.getID());
                String strStore = clickedObject.getName();
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), StoreDetailActivity.class);
                // sending data to new activity
                i.putExtra("storeID", strid);
                i.putExtra("storeName", strStore);
                startActivity(i);

            }
        });
    }
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(StoreListActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(StoreListActivity.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_info:
                Intent intent2 = new Intent(StoreListActivity.this, info4creator.class);
                startActivity(intent2);
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
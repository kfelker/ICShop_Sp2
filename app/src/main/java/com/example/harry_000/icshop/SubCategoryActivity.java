package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;



public class SubCategoryActivity extends AppCompatActivity {
    private List<Store> Stores = new ArrayList<Store>();
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_stores);

        Intent i= getIntent();
        String SubCategoryID = i.getStringExtra("ProductCategoryID");
        String SubCategoryName = i.getStringExtra("Description");
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

        //Toast.makeText(getApplicationContext(), id,
        //    Toast.LENGTH_LONG).show();


        db = new MyDatabase(this);
        Stores = db.getStoresByProductCategory(SubCategoryID); // you would not typically call this on the main thread

        StoresListAdapter Adapter = new StoresListAdapter(this, Stores);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(Adapter);

        TextView SubText = (TextView) this.findViewById(R.id.headertext);
        SubText.setText(SubCategoryName);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                Store clickedObject = (Store) parent.getAdapter().getItem(position);

                String strId = String.valueOf(clickedObject.getID());
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), StoreDetailActivity.class);
                // sending data to new activity
                i.putExtra("storeID", strId);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(SubCategoryActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(SubCategoryActivity.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_parking:
                Intent intent2 = new Intent(SubCategoryActivity.this, parking.class);
                startActivity(intent2);
                break;
            case R.id.tab_info:
                Intent intent3 = new Intent(SubCategoryActivity.this, info4creator.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
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

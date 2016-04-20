package com.example.harry_000.icshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class CategoryExpandActivity extends AppCompatActivity {

    private List<MainRetailCategory> mainList = new ArrayList<MainRetailCategory>();
    // private List<Store> Stores = new ArrayList<Store>();
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_expand);
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);

        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.layout_left_menu);

        mainList = createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        CategoryExpandAdapter adapter = new CategoryExpandAdapter(this,mainList);
        listView.setAdapter(adapter);

        ////////////////////////////////////////////////////////////////////////////////////////////


        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // selected item
                //Store clickedObject = (Store) parent.getAdapter().getItem(childPosition);

                // String description = String.valueOf(clickedObject.getName());

                MainRetailCategory headerInfo = mainList.get(groupPosition);
                //get the child info
                SubRetailCategory detailInfo =  headerInfo.getSubCatList().get(childPosition);



                String strid = String.valueOf(detailInfo.getID());


                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), SubCategoryActivity.class);
                // sending data to new activity
                i.putExtra("ProductCategoryID", strid);
                i.putExtra("Description", detailInfo.getDesc());
                startActivity(i);

                return true;


            }
        });


    }

    /////////////////////////////////////////////////////////////////////////////////////////////




    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(CategoryExpandActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(CategoryExpandActivity.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_info:
                Intent intent2 = new Intent(CategoryExpandActivity.this, info4creator.class);
                startActivity(intent2);
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

    public List<MainRetailCategory> createData() {
        List<MainRetailCategory> mList = new ArrayList<MainRetailCategory>();
        List<SubRetailCategory> subList = new ArrayList<SubRetailCategory>();
        db = new MyDatabase(getApplicationContext());
        mList = db.getAllMainCategories();

        for (int i = 0; i < mList.size(); i++) {
            String id = String.valueOf(mList.get(i).getID());
            subList = db.getAllSubCategories(id);
            mList.get(i).setSubCatList(subList);


        /*for (int j = 0; j < 5; j++) {
            Group group = new Group("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }*/
        }
        return mList;
    }
}
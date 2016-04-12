package com.example.harry_000.icshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class StoreDetailActivity extends AppCompatActivity{
    private MyDatabase db;
    private Store myStore;
    private String strBrands;
    private String strCategories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        Intent i = getIntent();
        String id = i.getStringExtra("storeID");
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.layout_left_menu);




        db = new MyDatabase(this);
        myStore = db.getStore(id);
        TextView store = (TextView) this.findViewById(R.id.textViewStore);
        store.setText(myStore.getName());

        final TextView address = (TextView) this.findViewById(R.id.textViewAddress);
        SpannableString spanStr = new SpannableString(myStore.getAddress());
        spanStr.setSpan(new UnderlineSpan(), 0, spanStr.length(), 0);
        address.setText(spanStr);
        final Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+ address.getText().toString()));


        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(geoIntent);
            }
        });


        TextView phone = (TextView) this.findViewById(R.id.textViewPhone);
        phone.setText(myStore.getPhone());

        db.close();

        TabHost tabHost = (TabHost) findViewById(R.id.mytabhost);
        tabHost.setup();
        TabSpec tab1 = tabHost.newTabSpec("TAB_1");
        tab1.setIndicator("BRANDS");
        tab1.setContent(R.id.Brands);
        tabHost.addTab(tab1);

        TabSpec tab2 = tabHost.newTabSpec("TAB_2");
        tab2.setIndicator("CATEGORIES");
        tab2.setContent(R.id.Categories);
        tabHost.addTab(tab2);

        db = new MyDatabase(this);
        strBrands = db.getStrBrandsForStore(id);
        TextView brands = (TextView) this.findViewById(R.id.textViewBrands);
        brands.setText(strBrands);

        db.close();

        db = new MyDatabase(this);
        strCategories = db.getStrCategoriesForStore(id);
        TextView cats = (TextView) this.findViewById(R.id.textViewCategories);
        cats.setText(strCategories);


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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(StoreDetailActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(StoreDetailActivity.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_info:
                Intent intent2 = new Intent(StoreDetailActivity.this, info4creator.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
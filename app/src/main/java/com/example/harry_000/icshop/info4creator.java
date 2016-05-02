package com.example.harry_000.icshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;



/**
 * Created by JIYUAN JIN on 2016/4/5.
 */
public class info4creator extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        init();

        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);

        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.layout_left_menu);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tab_home:
                Intent intent = new Intent(info4creator.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tab_search:
                Intent intent1 = new Intent(info4creator.this, searchActivity.class);
                startActivity(intent1);
                break;
            case R.id.tab_parking:
                Intent intent2 = new Intent(info4creator.this, parkingActivity.class);
                startActivity(intent2);
                break;
            case R.id.tab_info:
                Intent intent3 = new Intent(info4creator.this, info4creator.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    private void init(){
        mTextView=(TextView) findViewById(R.id.textView);
        mTextView.setText(getHandSetInfo());
    }
    private String getHandSetInfo(){
        String handSetInfo=
                "The Iowa City Downtown District is comprised of property owners and businesses that are geographically represented in two distinct neighborhoods:  Downtown Iowa City and the Northside Marketplace.\n" +
                        "\n" +
                        "Established in 2012, the Iowa City Downtown District was formed as a 501(c)6 non-profit organization to serve as a champion for this geographic region.  The Downtown District provides a leadership directive that advocates for the District mission and serves as a mechanism to more efficiently implement District-wide marketing, programs, events, and projects to the benefit of all the businesses within it, the University of Iowa, community members, and the region at large (the Creative Corridor).  Since its inception, the Downtown District continues to forge an exciting and deliberate path forward towards cultural vibrancy, resiliency, and sustainability.\n";

        return handSetInfo;
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

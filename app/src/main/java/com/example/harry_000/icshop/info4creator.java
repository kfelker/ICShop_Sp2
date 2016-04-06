package com.example.harry_000.icshop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;



/**
 * Created by JIYUAN JIN on 2016/4/5.
 */
public class info4creator extends Activity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        init();

        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
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
            case R.id.tab_info:
                Intent intent2 = new Intent(info4creator.this, info4creator.class);
                startActivity(intent2);
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
                "Phone model: " + android.os.Build.MODEL +
                        "\nSDK version: " + android.os.Build.VERSION.SDK +
                        "\nSystem version: " + android.os.Build.VERSION.RELEASE+
                        "\nSoftware version: 1.1";
        return handSetInfo;
    }

}

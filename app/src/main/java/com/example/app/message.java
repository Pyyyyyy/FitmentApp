package com.example.app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.bingoogolapple.badgeview.BGABadgeTextView;
public class message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        final BGABadgeTextView badgeTextView = (BGABadgeTextView) findViewById(R.id.text);
        badgeTextView.showCirclePointBadge();

        //初始化
        badgeTextView.showTextBadge("3");
        badgeTextView.getBadgeViewHelper().setBadgeTextSizeSp(15);
        badgeTextView.getBadgeViewHelper().setBadgeTextColorInt(Color.WHITE);
        badgeTextView.getBadgeViewHelper().setBadgeBgColorInt(Color.RED);
        badgeTextView.getBadgeViewHelper().setDragable(true);
        badgeTextView.getBadgeViewHelper().setBadgePaddingDp(6);
        badgeTextView.getBadgeViewHelper().setBadgeBorderWidthDp(2);
        badgeTextView.getBadgeViewHelper().setBadgeBorderColorInt(Color.WHITE);

        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (badgeTextView.isShowBadge())
                    badgeTextView.hiddenBadge();
                else {
                    badgeTextView.showCirclePointBadge();

                    //注意带上这个显示数字，否则将变成空
                    badgeTextView.showTextBadge("3");
                }
            }
        });


        final BGABadgeTextView badgeTextView2 = (BGABadgeTextView) findViewById(R.id.text2);
        badgeTextView2.showCirclePointBadge();

        //初始化
        badgeTextView2.showTextBadge("4");
        badgeTextView2.getBadgeViewHelper().setBadgeTextSizeSp(15);
        badgeTextView2.getBadgeViewHelper().setBadgeTextColorInt(Color.WHITE);
        badgeTextView2.getBadgeViewHelper().setBadgeBgColorInt(Color.RED);
        badgeTextView2.getBadgeViewHelper().setDragable(true);
        badgeTextView2.getBadgeViewHelper().setBadgePaddingDp(6);
        badgeTextView2.getBadgeViewHelper().setBadgeBorderWidthDp(2);
        badgeTextView2.getBadgeViewHelper().setBadgeBorderColorInt(Color.WHITE);

        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (badgeTextView2.isShowBadge())
                    badgeTextView2.hiddenBadge();
                else {
                    badgeTextView2.showCirclePointBadge();

                    //注意带上这个显示数字，否则将变成空
                    badgeTextView2.showTextBadge("4");
                }
            }
        });




        final BGABadgeTextView badgeTextView3 = (BGABadgeTextView) findViewById(R.id.text3);
        badgeTextView3.showCirclePointBadge();

        //初始化
        badgeTextView3.showTextBadge("1");
        badgeTextView3.getBadgeViewHelper().setBadgeTextSizeSp(15);
        badgeTextView3.getBadgeViewHelper().setBadgeTextColorInt(Color.WHITE);
        badgeTextView3.getBadgeViewHelper().setBadgeBgColorInt(Color.RED);
        badgeTextView3.getBadgeViewHelper().setDragable(true);
        badgeTextView3.getBadgeViewHelper().setBadgePaddingDp(6);
        badgeTextView3.getBadgeViewHelper().setBadgeBorderWidthDp(2);
        badgeTextView3.getBadgeViewHelper().setBadgeBorderColorInt(Color.WHITE);

        findViewById(R.id.text3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (badgeTextView3.isShowBadge())
                    badgeTextView3.hiddenBadge();
                else {
                    badgeTextView3.showCirclePointBadge();

                    //注意带上这个显示数字，否则将变成空
                    badgeTextView3.showTextBadge("1");
                }
            }
        });





    }
}

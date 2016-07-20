package com.example.yegor.testsoftteco.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yegor.testsoftteco.R;
import com.example.yegor.testsoftteco.adapters.PagerSelectedListener;
import com.example.yegor.testsoftteco.adapters.SectionsPagerAdapter;
import com.example.yegor.testsoftteco.view.GridPagerItemAttributes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int dotsCount;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pagerBullet = (ViewPager) findViewById(R.id.pager);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), getContent());

        pagerBullet.setAdapter(adapter);
        setUiPageViewController(adapter.getCount());

        pagerBullet.addOnPageChangeListener(new PagerSelectedListener() {
            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotsCount; i++)
                    dots[i].setTextColor(getResources().getColor(R.color.divider));

                dots[position].setTextColor(getResources().getColor(android.R.color.white));
            }
        });

    }

    private void setUiPageViewController(int dotsCount) {

        this.dotsCount = dotsCount;

        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.pager_dots);
        dots = new TextView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(30);
            dots[i].setTextColor(getResources().getColor(R.color.divider));
            dotsLayout.addView(dots[i]);
        }

        dots[0].setTextColor(getResources().getColor(android.R.color.white));
    }

    private List<GridPagerItemAttributes> getContent() {

        List<GridPagerItemAttributes> itemParams = new ArrayList<>();

        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_card_giftcard_black_24dp, "Phone accelerator 1"));
        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_power_settings_new_black_24dp, "Phone accelerator 2"));
        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_star_black_24dp, "Phone accelerator 3"));
        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_power_settings_new_black_24dp, "Phone accelerator 4"));
        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_info_black_24dp, "Phone accelerator 5"));
        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_power_settings_new_black_24dp, "Phone accelerator 6"));

        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_star_black_24dp, "Phone accelerator 7"));
        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_power_settings_new_black_24dp, "Phone accelerator 8"));
        itemParams.add(new GridPagerItemAttributes(R.drawable.ic_card_giftcard_black_24dp, "Phone accelerator 9"));

        return itemParams;
    }

}

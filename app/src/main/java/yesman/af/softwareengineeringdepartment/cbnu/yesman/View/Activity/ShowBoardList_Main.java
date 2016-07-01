package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.SharedPreference.SharedPreference;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.View.AdapterAndFragment.Fragment_BoardList_inMain;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;


public class ShowBoardList_Main extends AppCompatActivity {

    public static int matchingcount = 0;
        public static int seletedtab = 0;  // 0은 재능기부 1은 재능받기
        private SharedPreference sharedPreference;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_showboardlist_main_biglayout);

        sharedPreference = new SharedPreference(this);
        initUser();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setBackgroundResource(R.drawable.messenger_button_white_bg_round);


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.titleA, Fragment_BoardList_inMain.class)
                .add(R.string.titleB, Fragment_BoardList_inMain.class)
                .create());





        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        getSupportActionBar().setTitle("TEST");
        // ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));


        final LinearLayout lyTabs = (LinearLayout) viewPagerTab.getChildAt(0);
        changeTabsTitleTypeFace(lyTabs, 0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                System.out.println("각 탭 포지션 : "+position);
                seletedtab = position;
                ServerManager serverManager = ServerManager.getInstance();
                if(seletedtab==0) serverManager.getDonation_BoardList();
                else serverManager.getRequest_BoardList();

                changeTabsTitleTypeFace(lyTabs, position); // 글자 굵게 하기
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }


    public void changeTabsTitleTypeFace(LinearLayout ly, int position) {
        for (int j = 0; j < ly.getChildCount(); j++) {
            TextView tvTabTitle = (TextView) ly.getChildAt(j);
            tvTabTitle.setTypeface(null, Typeface.NORMAL);
            if (j == position) tvTabTitle.setTypeface(null, Typeface.BOLD);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actiontab, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mypage_btn) {
            Intent intent = new Intent(this,MyPage.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.myBoardList_btn) {
            Intent intent = new Intent(this,MyBoardList.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUser(){

        float ux = sharedPreference.getValue(sharedPreference.user_x,0);
        float uy = sharedPreference.getValue(sharedPreference.user_y,0);
        String uxs = String.valueOf(ux);
        String uys = String.valueOf(uy);

        User.getInstance().setUserID(sharedPreference.getValue(sharedPreference.user_id,"userId"));
        User.getInstance().setUserName(sharedPreference.getValue(sharedPreference.user_name,"username"));
        User.getInstance().setX(Double.parseDouble(uxs));
        User.getInstance().setY(Double.parseDouble(uys));
        User.getInstance().setDomain_computer(sharedPreference.getValue(sharedPreference.domain4,0));
        User.getInstance().setDomain_document(sharedPreference.getValue(sharedPreference.domain2,0));
        User.getInstance().setDomain_dsign(sharedPreference.getValue(sharedPreference.domain0,0));
        User.getInstance().setDomain_marketing(sharedPreference.getValue(sharedPreference.domain3,0));
        User.getInstance().setDomain_music(sharedPreference.getValue(sharedPreference.domain5,0));
        User.getInstance().setDomain_play(sharedPreference.getValue(sharedPreference.domain7,0));
        User.getInstance().setDomain_service(sharedPreference.getValue(sharedPreference.domain6,0));
        User.getInstance().setDomain_translate(sharedPreference.getValue(sharedPreference.domain1,0));
    }

}
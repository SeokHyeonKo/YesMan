package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.View.AdapterAndFragment.ListViewAdapter;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;


public class MyBoardList extends ActionBarActivity {
    public static int opponetReliability  = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_boardlist);

        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar_myboardlist, null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled (false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(v);

        ArrayList<Board> data = new ArrayList<>();
        ListView listView=(ListView)findViewById(R.id.my_boardlist_ListView);

        Board item1=new Board("#1","리스트뷰로 바꿔줘", new Date(2016,02,03),3,3);
        Board item2=new Board("#2","배고파", new Date(2016,02,03),3,3);
        Board item3=new Board("#3","i'm hungry", new Date(2016,02,03),3,3);
        Board item4=new Board("#4","우옹~~", new Date(2016,02,03),3,3);
        Board item5=new Board("#5","스크롤 된당", new Date(2016,02,03),3,3);
        Board item6=new Board("#6","얍삐", new Date(2016,02,03),3,3);
        Board item7=new Board("#7","호호호호홍", new Date(2016,02,03),3,3);

        data.add(item1);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        data.add(item5);
        data.add(item6);
        data.add(item7);
        System.out.println("들어옴?");
        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.content_listview_showboarlist_main, data);
        listView.setAdapter(adapter);

        // ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mypage_backbtn) {
            Intent intent = new Intent(this,MyPage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
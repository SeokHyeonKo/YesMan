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
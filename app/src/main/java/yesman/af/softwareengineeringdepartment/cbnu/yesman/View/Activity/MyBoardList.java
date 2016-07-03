package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.View.AdapterAndFragment.MyBoardListViewAdapter;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.CategoryDomainManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;


public class MyBoardList extends ActionBarActivity implements MyBoardListViewAdapter.ListBtnClickListener{
    public static int opponetReliability  = 4;
    private TextView textview9;
    public static ListView listView;
    public static Context context;
    public static Activity activity;
    private FButton okbtn;
    private FButton cancelbtn;
    public static MyBoardListViewAdapter adapter;
    private Handler handelr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_boardlist);
        context = getApplicationContext();
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = inflator.inflate(R.layout.custom_actionbar_myboardlist, null);
        textview9 = (TextView) findViewById(R.id.textView9);
        activity = MyBoardList.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled (false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setCustomView(v);


        listView=(ListView)findViewById(R.id.my_boardlist_ListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
            }
        }) ;

        User.getInstance().setBoardList(new ArrayList<Board>());

        ServerManager.getInstance().checkMyBoardList();



        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                adapter = new MyBoardListViewAdapter(context, R.layout.content_listview_inmyboardlist, User.getInstance().getBoardList(), MyBoardList.this);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }, 600);




        // ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        getSupportActionBar().setTitle("나의 게시판 현황");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.myBoardList_btn) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    private void DialogOk(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(MyBoardList.this);
        final RatingBar rating = new RatingBar(this);

        View root = ((LayoutInflater)MyBoardList.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.rating_bar, null);
        alt_bld.setView(root);

        final RatingBar rat = (RatingBar)root.findViewById(R.id.ratingbar);
        rat.setNumStars(5);
        alt_bld.setTitle("상대방의 신뢰도");

        alt_bld.setMessage("상대방에 대한 평가를 부탁드립니다").setCancelable(
                false).setPositiveButton("확 인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                        opponetReliability = rating.getNumStars();
                        ServerManager serverManager = ServerManager.getInstance();
                        serverManager.registerReliAbility();
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                              ServerManager.getInstance().checkMyBoardList();
                            }
                        }, 100);

                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                adapter = new MyBoardListViewAdapter(context, R.layout.content_listview_inmyboardlist, User.getInstance().getBoardList(), MyBoardList.this);
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        }, 600);
                    }
                });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        // Icon for AlertDialog
        alert.show();
    }

    private void Dialogcancle(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setTitle("취 소");

        alt_bld.setMessage("수락을 취소 하시겠습니까?").setCancelable(
                false).setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                        ServerManager serverManager = ServerManager.getInstance();
                        serverManager.cancelboard();
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                ServerManager.getInstance().checkMyBoardList();
                            }
                        }, 100);

                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                adapter = new MyBoardListViewAdapter(context, R.layout.content_listview_inmyboardlist, User.getInstance().getBoardList(), MyBoardList.this);
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        }, 600);
                        dialog.cancel();
                        Toast.makeText(context,"수락이 취소 되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        // Icon for AlertDialog
        alert.show();
    }


    public void onclick_ok(){
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final RatingBar rating = new RatingBar(this);
        rating.setMax(5);

        popDialog.setView(rating);
        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //textview9.setText(String.valueOf(rating.getProgress()));
                        opponetReliability = rating.getNumStars();
                        ServerManager serverManager = ServerManager.getInstance();
                        serverManager.registerReliAbility();
                        dialog.dismiss();
                    }
                });
        // Button Cancel
        popDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        popDialog.create();
        popDialog.show();

    }
    public void onclick_cancle(){
        new MaterialDialog.Builder(context)
                .title("xx")
                .content("xx")
                .positiveText("xx")
                .negativeText("xx")
                .show();
    }

    @Override
    public void onListBtnClick(int position) {
        User user = User.getInstance();
        user.setCurrentBoard(user.getBoardList().get(position));
        //Toast.makeText(this, CategoryDomainManager.isOk + "번 아이템이 선택되었습니다.", Toast.LENGTH_SHORT).show() ;

        if(user.getBoardList().get(position).getIsmatching()==1){
            if(CategoryDomainManager.isOk==1)DialogOk();
            else if(CategoryDomainManager.isOk==0){
                Dialogcancle();
            }else{
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+User.getInstance().getCurrentBoard().getUserId()));
                startActivity(intent);
            }
        }

    }


}
package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.View.AdapterAndFragment.MyFadingActionBarHelper;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.CategoryDomainManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;


/**
 * Created by Jo on 2016-06-30.
 */
public class ContentBoard extends ActionBarActivity {

    private Board selectedBoard;
    TextView title;
    TextView id;
    TextView location;
    TextView content;
    ImageView topimg;
    CircleImageView domain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentboard_layout);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        selectedBoard = User.getInstance().getCurrentBoard();

        MyFadingActionBarHelper helper = new MyFadingActionBarHelper()
                .actionBarBackground(new ColorDrawable(Color.parseColor("#6799FF")))
                .headerLayout(R.layout.header_contentboard)
                .contentLayout(R.layout.activity_contentboard_layout);
            setContentView(helper.createView(this));

        //이미지 변경
        domain = (CircleImageView) findViewById(R.id.board_userImage);
        setDomainView();



        helper.initActionBar(this);

        setView();

        FButton okbtn = (FButton)findViewById(R.id.okbtn_incontentboard);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerManager serverManager = ServerManager.getInstance();
                serverManager.acceptBoard();

                DialogOk();
            }
        });
        FButton cancelbtn = (FButton)findViewById(R.id.cancelbtn_incontentboard);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setView(){
        title = (TextView) findViewById(R.id.title_incontentboard);
        id = (TextView) findViewById(R.id.user_idincontentboard);
        //location = (TextView) findViewById(R.id.detail_location);
        content = (TextView) findViewById(R.id.detail_content);

        title.setText(selectedBoard.getTitle());
        id.setText(selectedBoard.getRequestID());
        content.setText(selectedBoard.getContent());

    }

    public void setDomainView(){

        if(selectedBoard.getDomain()== CategoryDomainManager.COMPUTER){
            domain.setImageResource(R.drawable.computer);
        }else if(selectedBoard.getDomain()== CategoryDomainManager.DESIGN){
            domain.setImageResource(R.drawable.design);
        }else if(selectedBoard.getDomain()== CategoryDomainManager.DOCUMENT){
            domain.setImageResource(R.drawable.document);
        }else if(selectedBoard.getDomain()== CategoryDomainManager.TRANSLATE){
            domain.setImageResource(R.drawable.translate);
        }else if(selectedBoard.getDomain()== CategoryDomainManager.LIFE){
            domain.setImageResource(R.drawable.lifestyle);
        }else if(selectedBoard.getDomain()== CategoryDomainManager.ENTERTAINMENT){
            domain.setImageResource(R.drawable.entertainment);
        }else if(selectedBoard.getDomain()== CategoryDomainManager.MARKETING){
            domain.setImageResource(R.drawable.marketing);
        }else{
            domain.setImageResource(R.drawable.musicvideo);
        }


    }

    private void DialogOk(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(ContentBoard.this);

        alt_bld.setTitle("매칭 완료");

        alt_bld.setMessage("매칭 된 사람의 페이스북으로 가보시겠습니까?").setCancelable(
                false).setPositiveButton("네",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+User.getInstance().getCurrentBoard().getUserId()));
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'NO' Button
                        Toast.makeText(ContentBoard.this,"나의 게시판 페이지에 가시면 해당 보드에서 상대방 페이스북에 연결됩니다.",Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        // Icon for AlertDialog
        alert.show();
    }
}
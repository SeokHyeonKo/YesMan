package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.SharedPreference.SharedPreference;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.CategoryDomainManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

/**
 * Created by User on 2016-07-01.
 */
public class Write extends AppCompatActivity {

    private EditText content_text;
    private EditText title_text;

    private RadioButton donation_btn;
    private RadioButton request_btn;
    private Button cancelbtn;
    private Button registerbtn;
    private Context context;

    private String title;
    private String content;

    private int category = 0; // 0은 재능기부 1은 재능 요청
    private int domain = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_layout);
        context = getApplicationContext();

        content_text = (EditText)findViewById(R.id.writeContent);
        title_text = (EditText)findViewById(R.id.writeTitle);

        donation_btn = (RadioButton)findViewById(R.id.donation_rbtn_write);
        request_btn = (RadioButton)findViewById(R.id.request_rbtn_write);

        registerbtn = (Button)findViewById(R.id.registerbtn_write);
        cancelbtn = (Button)findViewById(R.id.cancel_btn_write);

        request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = title_text.getText().toString();
                content = content_text.getText().toString();

                if(title.length()<0 || content.length()<0){
                    Toast.makeText(getApplicationContext(),
                            "내용을 입력해 주세요!",
                            Toast.LENGTH_SHORT).show();
                }else{
                        if(donation_btn.isChecked()) category = CategoryDomainManager.GIVE;
                        else category = CategoryDomainManager.REQUEST;

                        DialogSimple();


                }

            }
        });




        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Spinner s = (Spinner)findViewById(R.id.write_category);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Toast.makeText(parent.getContext(),
                        "'" + parent.getSelectedItem() + "'을 선택하였습니다.",
                        Toast.LENGTH_SHORT).show();
                if(parent.getSelectedItem().equals("컴퓨터")){
                    domain = CategoryDomainManager.COMPUTER;
                }else if(parent.getSelectedItem().equals("디자인")){
                    domain = CategoryDomainManager.DESIGN;
                }else if(parent.getSelectedItem().equals("문서")){
                    domain = CategoryDomainManager.DOCUMENT;
                }else if(parent.getSelectedItem().equals("예체능")){
                    domain = CategoryDomainManager.ENTERTAINMENT;
                }else if(parent.getSelectedItem().equals("동영상")){
                    domain = CategoryDomainManager.MOVIE_MUSIC;
                }else if(parent.getSelectedItem().equals("마케팅")){
                    domain = CategoryDomainManager.MARKETING;
                }else if(parent.getSelectedItem().equals("라이프스타일")){
                    domain = CategoryDomainManager.LIFE;
                }else{ // 번역
                    domain = CategoryDomainManager.TRANSLATE;
                }


                ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void DialogSimple(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("자신의 위치가 아닌 다른 위치를 사용하시겠습니까?").setCancelable(
                false).setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'Yes' Button
                        Intent intent = new Intent(Write.this,GoogleMap.class);
                        intent.putExtra("set",1);

                    }
                }).setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'NO' Button

                        SharedPreference sharedPreference = new SharedPreference(context);
                        Board board = new Board(title,content,sharedPreference.getValue(sharedPreference.user_x,0.0),sharedPreference.getValue(sharedPreference.user_y,0.0));
                        User.getInstance().setCurrentBoard(board);
                        ServerManager serverManager = ServerManager.getInstance();
                        serverManager.registerBoard();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        alert.setTitle("Title");
        // Icon for AlertDialog
        alert.show();
    }
}

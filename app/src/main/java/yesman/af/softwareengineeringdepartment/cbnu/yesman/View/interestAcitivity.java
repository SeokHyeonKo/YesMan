package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import info.hoang8f.widget.FButton;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

public class interestAcitivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox domain0;   //디자인
    private CheckBox domain1;   //번역
    private CheckBox domain2;   //문서
    private CheckBox domain3;   //마케팅
    private CheckBox domain4;   //컴퓨터
    private CheckBox domain5;   //음악, 동영상
    private CheckBox domain6;   //생활서비스
    private CheckBox domain7;   //엔터테인먼트

    private FButton success_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
       success_button = (FButton) findViewById(R.id.success_button);
        success_button.setButtonColor(getResources().getColor(R.color.color_concrete));
        success_button.setShadowColor(getResources().getColor(R.color.color_asbestos));
        success_button.setShadowEnabled(true);
        success_button.setShadowHeight(5);
        success_button.setCornerRadius(5);



         domain0 = (CheckBox)findViewById(R.id.domain_c0);
         domain1 = (CheckBox)findViewById(R.id.domain_c1);
         domain2 = (CheckBox)findViewById(R.id.domain_c2);
         domain3 = (CheckBox)findViewById(R.id.domain_c3);
         domain4 = (CheckBox)findViewById(R.id.domain_c4);
         domain5 = (CheckBox)findViewById(R.id.domain_c5);
         domain6 = (CheckBox)findViewById(R.id.domain_c6);
         domain7 = (CheckBox)findViewById(R.id.domain_c7);

        pref = getSharedPreferences("PrefName", MODE_PRIVATE);  // 관심분야 저장
        editor = pref.edit();




    }
    public void Onclick_next(View v) {

        editor.putInt("domain0", 0);
        editor.putInt("domain1", 0);
        editor.putInt("domain2", 0);
        editor.putInt("domain3", 0);
        editor.putInt("domain4", 0);
        editor.putInt("domain5", 0);
        editor.putInt("domain6", 0);
        editor.putInt("domain7", 0);

        if(domain0.isChecked() == true){
            editor.putInt("domain0", 1);
        }
        else if(domain1.isChecked() == true){
            editor.putInt("domain1", 1);

        }else if(domain2.isChecked() == true){
            editor.putInt("domain2", 1);

        }else if(domain3.isChecked() == true){
            editor.putInt("domain3", 1);

        }else if(domain4.isChecked() == true){
            editor.putInt("domain4", 1);

        }else if(domain5.isChecked() == true){
            editor.putInt("domain5", 1);

        }else if(domain6.isChecked() == true){
            editor.putInt("domain6", 1);

        }else if(domain7.isChecked() == true){
            editor.putInt("domain7", 1);

        }
        User user = User.getInstance();
        user.setDomain_dsign(pref.getInt("domain0", 0));
        user.setDomain_translate(pref.getInt("domain1", 0));
        user.setDomain_document(pref.getInt("domain2", 0));
        user.setDomain_marketing(pref.getInt("domain3", 0));
        user.setDomain_computer(pref.getInt("domain4", 0));
        user.setDomain_music(pref.getInt("domain5", 0));
        user.setDomain_service(pref.getInt("domain6", 0));
        user.setDomain_play(pref.getInt("domain7", 0));

        editor.commit();
        //ServerManager severmanager = ServerManager.getInstance();
        //severmanager.joinUser();

        startActivity(new Intent(this, mypageAcitivity.class));
    }
}

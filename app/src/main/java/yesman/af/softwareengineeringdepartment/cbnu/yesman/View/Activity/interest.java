package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import info.hoang8f.widget.FButton;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.GCM.GCMValue;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.SharedPreference.SharedPreference;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

public class interest extends AppCompatActivity {

    private SharedPreference sharedPreference;
    private CheckBox domain0;   //디자인
    private CheckBox domain1;   //번역
    private CheckBox domain2;   //문서
    private CheckBox domain3;   //마케팅
    private CheckBox domain4;   //컴퓨터
    private CheckBox domain5;   //음악, 동영상
    private CheckBox domain6;   //생활서비스
    private CheckBox domain7;   //엔터테인먼트

    private FButton success_button;

    private GoogleCloudMessaging gcm;
    private String regid;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_layout);
        success_button = (FButton) findViewById(R.id.success_button);
        context = getApplicationContext();
        domain0 = (CheckBox) findViewById(R.id.domain_c0);
        domain1 = (CheckBox) findViewById(R.id.domain_c1);
        domain2 = (CheckBox) findViewById(R.id.domain_c2);
        domain3 = (CheckBox) findViewById(R.id.domain_c3);
        domain4 = (CheckBox) findViewById(R.id.domain_c4);
        domain5 = (CheckBox) findViewById(R.id.domain_c5);
        domain6 = (CheckBox) findViewById(R.id.domain_c6);
        domain7 = (CheckBox) findViewById(R.id.domain_c7);

        sharedPreference = new SharedPreference(this);



    }

    public void Onclick_next(View v) {

        sharedPreference.put(sharedPreference.domain0, 0);
        sharedPreference.put(sharedPreference.domain1, 0);
        sharedPreference.put(sharedPreference.domain2, 0);
        sharedPreference.put(sharedPreference.domain3, 0);
        sharedPreference.put(sharedPreference.domain4, 0);
        sharedPreference.put(sharedPreference.domain5, 0);
        sharedPreference.put(sharedPreference.domain6, 0);
        sharedPreference.put(sharedPreference.domain7, 0);




        if (domain0.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain0, 1);
            //User.getInstance().setDomain_dsign(1);
        }
        if (domain1.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain1, 1);
        }
        if (domain2.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain2, 1);
        }
        if (domain3.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain3, 1);

        }
        if (domain4.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain4, 1);
        }

        if (domain5.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain5, 1);
        }
        if (domain6.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain6, 1);

        }
        if (domain7.isChecked() == true) {
            sharedPreference.put(sharedPreference.domain7, 1);

        }


        if(getIntent().hasExtra("set")){ // 관심사 변경
            System.out.println("관심사 set실행");

            changeInterstedUserInfo();
            ServerManager serverManager = ServerManager.getInstance();
            serverManager.changeInterested();

            finish();

        }else{ // 회원가입중
            ServerManager severmanager = ServerManager.getInstance();
            initUser(); // 초기 user setting;
            User user = User.getInstance();
            System.out.println("-------유저확인--------");
            System.out.println(user.getUserID()+","+user.getUserName());



            gcm = GoogleCloudMessaging.getInstance(context);
            System.out.println("************************************************* gcm 발급");
            registerInBackground();
            storeRegistrationId(context, regid);

            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    ServerManager a = new ServerManager();
                    a.joinUser();
                }
            }, 300);



            // 체크할때 http 통신을 기달려 줘야한다.
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    User.getInstance().setRegID(getRegistrationId(context)); // 기존에 발급받은 등록 아이디를 가져온다
                    startActivity(new Intent(interest.this, ShowBoardList_Main.class));
                }
            }, 600);
        }



    }

    private void changeInterstedUserInfo(){
        User.getInstance().setDomain_computer(sharedPreference.getValue(sharedPreference.domain4,1));
        User.getInstance().setDomain_document(sharedPreference.getValue(sharedPreference.domain2,1));
        User.getInstance().setDomain_dsign(sharedPreference.getValue(sharedPreference.domain0,1));
        User.getInstance().setDomain_marketing(sharedPreference.getValue(sharedPreference.domain3,1));
        User.getInstance().setDomain_music(sharedPreference.getValue(sharedPreference.domain5,1));
        User.getInstance().setDomain_play(sharedPreference.getValue(sharedPreference.domain7,1));
        User.getInstance().setDomain_service(sharedPreference.getValue(sharedPreference.domain6,1));
        User.getInstance().setDomain_translate(sharedPreference.getValue(sharedPreference.domain1,1));
    }


    private void initUser(){

        String uxs =  sharedPreference.getValue(sharedPreference.user_x,"userx");
        String uys = sharedPreference.getValue(sharedPreference.user_y,"usery");
        System.out.println("ux "+uxs);
        System.out.println("uy" +uys);

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

    // 저장된 reg id 조회
    private String getRegistrationId(Context context) {
       // final SharedPreferences prefs = getGCMPreferences(context); // 이전에 저장해둔 등록 아이디를 SharedPreferences에서 가져온다.
        String registrationId = sharedPreference.getValue(GCMValue.PROPERTY_REG_ID, ""); // 저장해둔 등록 아이디가 없으면 빈 문자열을 반환한다.
        if (registrationId.isEmpty()) {
            System.out.println("************************************************* Registration not found.");

            registrationId = "";
        }

        // 앱이 업데이트 되었는지 확인하고, 업데이트 되었다면 기존 등록 아이디를 제거한다.
        // 새로운 버전에서도 기존 등록 아이디가 정상적으로 동작하는지를 보장할 수 없기 때문이다.
        int registeredVersion = sharedPreference.getValue(GCMValue.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) { // 이전에 등록 아이디를 저장한 앱의 버전과 현재 버전을 비교해 버전이 변경되었으면 빈 문자열을 반환한다.
            System.out.println("************************************************* App version changed.");
            registrationId = "";
        }
        return registrationId;
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    // reg id 발급
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    User.getInstance().setRegID(gcm.register(GCMValue.SENDER_ID));
                    msg = "Device registered, registration ID=" + regid;

                    // 서버에 발급받은 등록 아이디를 전송한다.
                    // 등록 아이디는 서버에서 앱에 푸쉬 메시지를 전송할 때 사용된다.
                    //sendRegistrationIdToBackend();

                    // 등록 아이디를 저장해 등록 아이디를 매번 받지 않도록 한다.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                System.out.println("****************************************************************************** msg : " + msg);
            }

        }.execute(null, null, null);
    }

    // SharedPreferences에 발급받은 등록 아이디를 저장해 등록 아이디를 여러 번 받지 않도록 하는 데 사용
    private    void storeRegistrationId(Context context, String regid) {
       // final SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = getAppVersion(context);
        System.out.println("************************************************* Saving regId on app version " + appVersion);
       // SharedPreferences.Editor editor = prefs.edit();
        sharedPreference.put(GCMValue.PROPERTY_REG_ID, regid);
        sharedPreference.put(GCMValue.PROPERTY_APP_VERSION, appVersion);
    }
    public void onclick_domain0(View v){
        domain0.setChecked(true);
    }
    public void onclick_domain1(View v){
        domain1.setChecked(true);
    }
    public void onclick_domain2(View v){
        domain2.setChecked(true);
    }
    public void onclick_domain3(View v){
        domain3.setChecked(true);
    }
    public void onclick_domain4(View v){
        domain4.setChecked(true);
    }
    public void onclick_domain5(View v){
        domain5.setChecked(true);
    }
    public void onclick_domain6(View v){
        domain6.setChecked(true);
    }
    public void onclick_domain7(View v){
        domain7.setChecked(true);
    }



}

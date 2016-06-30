package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.Date;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.GCM.GCMValue;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

public class RegisterBoardActivity extends Activity
{

    GoogleCloudMessaging gcm;
    String regid;
    Context context;
    User user;


    EditText contentEditText;
    EditText titleEditText;
    Button registerBtn;
    String userID;
    Date date;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.test);
                    context = getApplicationContext();


                    user = User.getInstance();

                    user.setUserID("2222222" +
                            "");
                    user.setX(100);
                    user.setY(125);
                    Board board = new Board("두번째","사람입니다",new Date(),123.124,231.23);
                    user.setCurrentDashBoard(board);



                    //앱 버전이 바뀌었거나 등록된 아이디가 없다면 다시 발급
                    //서버 접근하여 아이디가 없는지 있는지 확인하여야함
                    //잠재적 오류 나중에 코딩해야함!!!!!


                    System.out.println("유저를 체크합니다----------------");
                    ServerManager a = new ServerManager();
                    a.checkUser();

                    // 체크할때 http 통신을 기달려 줘야한다.
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(user.isExist_already()==true) System.out.println("유저가 존재 합니다........");
                            else System.out.println("유저가 존재 하지 않습니다........");

                            if(user.isExist_already()==false){
                                gcm = GoogleCloudMessaging.getInstance(context);
                                System.out.println("************************************************* gcm 발급");
                                registerInBackground();
                                ServerManager a = new ServerManager();
                                a.joinUser();
                                storeRegistrationId(context, regid);
                            }
                            user.setRegID(getRegistrationId(context)); // 기존에 발급받은 등록 아이디를 가져온다

                        }
                    }, 500);



                    System.out.println("regid : "+regid);



                    context = getApplicationContext();
                    contentEditText = (EditText)findViewById(R.id.contentEditText);
                    contentEditText.setBackgroundColor(Color.CYAN);
                    titleEditText = (EditText)findViewById(R.id.titleEditText);
                    registerBtn = (Button)findViewById(R.id.registerbtn);

                    registerBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ServerManager a  = ServerManager.getInstance();


                            // Board dashBoard = new Board(title,content,date,x,y);



                            a.registerBoard();

                     }
                 });



                    //notification 시 해당하는 화면을 호출하기 위한 부분
                    if(getIntent().hasExtra(GCMValue.NOTIFICATION)){
                            Bundle extras = getIntent().getExtras();
                            String value = extras.getString(GCMValue.NOTIFICATION);
                            Intent intent = new Intent(this, GCMtestActivity.class);
                            GCMValue.IS_NOTIFICATION="FALSE";
                            startActivity(intent);
                            System.out.println("인텐트");
                    }



                }

    // 저장된 reg id 조회
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context); // 이전에 저장해둔 등록 아이디를 SharedPreferences에서 가져온다.
        String registrationId = prefs.getString(GCMValue.PROPERTY_REG_ID, ""); // 저장해둔 등록 아이디가 없으면 빈 문자열을 반환한다.
        if (registrationId.isEmpty()) {
            System.out.println("************************************************* Registration not found.");

            registrationId = "";
        }

        // 앱이 업데이트 되었는지 확인하고, 업데이트 되었다면 기존 등록 아이디를 제거한다.
        // 새로운 버전에서도 기존 등록 아이디가 정상적으로 동작하는지를 보장할 수 없기 때문이다.
        int registeredVersion = prefs.getInt(GCMValue.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) { // 이전에 등록 아이디를 저장한 앱의 버전과 현재 버전을 비교해 버전이 변경되었으면 빈 문자열을 반환한다.
            System.out.println("************************************************* App version changed.");
            registrationId = "";
        }
        return registrationId;
    }

    private SharedPreferences getGCMPreferences(Context context) {
        return getSharedPreferences(RegisterBoardActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
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
                    user.setRegID(gcm.register(GCMValue.SENDER_ID));
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
        final SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = getAppVersion(context);
        System.out.println("************************************************* Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(GCMValue.PROPERTY_REG_ID, regid);
        editor.putInt(GCMValue.PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }





}

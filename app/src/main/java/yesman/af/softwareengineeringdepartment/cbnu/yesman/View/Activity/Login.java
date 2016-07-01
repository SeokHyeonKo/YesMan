package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.SharedPreference.SharedPreference;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

public class Login extends Activity {

    private CallbackManager callbackManager;
    /* facebook profile */
    private Profile profile;
    private Button facebookbtn;
    private SharedPreference sharedPreference;
    private String user_Id;
    private String user_password;
    private String user_name;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_layout);
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                //
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                Log.d("FaceBook :::", "id : " + newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        sharedPreference = new SharedPreference(this);

        facebookbtn = (Button) findViewById(R.id.facebookbtn);
//dd
        facebookbtn.setOnClickListener(facebookLoginListener);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener facebookLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //LoginManager - 요청된 읽기 또는 게시 권한으로 로그인 절차를 시작합니다.
            LoginManager.getInstance().logInWithReadPermissions(Login.this,
                    Arrays.asList("public_profile", "user_friends"));
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            User user = User.getInstance();
                            AccessToken.setCurrentAccessToken(loginResult.getAccessToken());
                                /* 페이스북 프로필을 가져온다*/
                            Handler mHandler = new Handler();
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    profile = Profile.getCurrentProfile();
                                    user_Id = profile.getId();
                                    user_name = profile.getName();
                                    sharedPreference.put(sharedPreference.user_id, user_Id);
                                    sharedPreference.put (sharedPreference.user_name, user_name);
                                    Log.d("FaceBook :::", "id : " + user_Id);
                                    Log.d("FaceBook :::", "name : " + user_name);
                                    User.getInstance().setUserID(user_Id);

                                    ServerManager servermanager = ServerManager.getInstance();
                                    System.out.println("유저를 체크합니다----------------");
                                    servermanager.checkUser();

                                    // 체크할때 http 통신을 기달려 줘야한다.
                                    new Handler().postDelayed(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            if(User.getInstance().isExist_already()==true) System.out.println("유저가 존재 합니다........");
                                            else System.out.println("유저가 존재 하지 않습니다........");

                                            if(User.getInstance().isExist_already()==false){
                                                Intent mainIntent = new Intent(Login.this, GoogleMap.class);
                                                startActivity(mainIntent);
                                                finish();
                                            }else{
                                                Intent mainIntent = new Intent(Login.this, ShowBoardList_Main.class);
                                                startActivity(mainIntent);
                                                finish();
                                            }

                                        }
                                    }, 500);




                                }
                            }, 500);

                                /*
                                //사용 디바이스 핸드폰 번호 알아오기
                                TelephonyManager systemService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                                String user_phone = systemService.getLine1Number();
                                user_phone = user_phone.substring(user_phone.length() - 10, user_phone.length());
                                user_phone = "0" + user_phone;
                                */
                                /*
                                //안드로이드 기기 구글 동기화 이메일 주소 가져오기
                                Pattern emailPattern = Patterns.EMAIL_ADDRESS;
                                AccountManager am = (AccountManager) getSystemService(Context.ACCOUNT_SERVICE);
                                Account[] accounts = am.getAccounts();
                                String user_email = "";
                                for (Account account : accounts) {
                                    if (emailPattern.matcher(account.name).matches()) {
                                        user_email = account.name;
                                    }
                                }
                                */
                            /** SharedPreferences로 Facbook 정보 저장*/
                                /* 로그인될 시 세션을 유지하기 위해 환경 변수에 facebook login session 관리를 저장한다. */
                            sharedPreference.put(sharedPreference.facebook_login, "LOGIN");


                        }
                        @Override
                        public void onCancel() {
                            Toast.makeText(Login.this, "페이스북 로그인이 취소 되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onError(FacebookException exception) {
                            Toast.makeText(Login.this, "페이스북 로그인이 취소 되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    };
}



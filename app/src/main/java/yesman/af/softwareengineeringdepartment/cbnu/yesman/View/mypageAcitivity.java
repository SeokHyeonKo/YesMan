package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;

public class mypageAcitivity extends ActionBarActivity {

    private Profile profile;
    private TextView tv_home_user_id;
    private TextView tv_home_user_name;
    private ProfilePictureView profilePictureView;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyFadingActionBarHelper helper = new MyFadingActionBarHelper()
                .actionBarBackground(R.drawable.computer)
                .headerLayout(R.layout.myapge_header)
                .contentLayout(R.layout.mypage_activity);

        setContentView(helper.createView(this));
        helper.initActionBar(this);



        //s

        /*
        FacebookSdk.sdkInitialize(getApplicationContext()); // SDK 초기화 (setContentView 보다 먼저 실행
        setContentView(R.layout.mypage);

        profile = Profile.getCurrentProfile();
        SharedPreferences pref = getSharedPreferences("PrefName", MODE_PRIVATE);
        String id = pref.getString("USER_ID", "NULL");
        //String name = profile.getName();

        //tv_home_user_id = (TextView)findViewById(R.id.home_user_id);
        //tv_home_user_name = (TextView)findViewById(R.id.home_user_name);

        //tv_home_user_id.setText(id);
        //tv_home_user_name.setText(name);

        profilePictureView = (ProfilePictureView)findViewById(R.id.home_profile_image_facebook);
        profilePictureView.setProfileId(id);
        */
    }





    public void Onclick_interest(View v) {
        Intent intent = new Intent(getApplicationContext(), interestAcitivity.class);
        startActivity(intent);
    }

    public void Onclick_GPS(View v) {
        Intent intent = new Intent(getApplicationContext(), MapAcitivity.class);
        intent.putExtra("set", 1);
        startActivity(intent);
    }

    public void Onclick_mylist(View v) {
        //
    }
    /*
    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FacebookSdk.sdkInitialize(view.getContext());
        profile = Profile.getCurrentProfile();

        //mProgressDialog = new ProgressDialog(getActivity());
        //mProgressDialog.setMessage("Log out ...");

        String id = profile.getId();
        String name = profile.getName();

        tv_home_user_id = (TextView)view.findViewById(R.id.home_user_id);
        tv_home_user_name = (TextView)view.findViewById(R.id.home_user_name);

        tv_home_user_id.setText(id);
        tv_home_user_name.setText(name);

        profilePictureView = (ProfilePictureView) view.findViewById(R.id.home_profile_image_facebook);
        profilePictureView.setProfileId(id);


        /* Facebook logout
        Button logoutButton = (Button) view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();

                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /* -- switch -> Button R.id.
                        LoginManager.getInstance().logOut();
                        SharedPreferences prefs = getActivity().getSharedPreferences("login", 0);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("FACEBOOK_LOGIN", "LOGOUT");
                        editor.commit();

                        Intent mainIntent = new Intent(getActivity(), SplashActivity.class);
                        startActivity(mainIntent);
                        getActivity().finish();
                        if (mProgressDialog != null && mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                }, 1000);
            }
        });

        return view;

    }
       */
}

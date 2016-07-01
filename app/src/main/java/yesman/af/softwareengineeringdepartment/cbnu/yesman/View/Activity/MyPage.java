package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.SharedPreference.SharedPreference;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.View.AdapterAndFragment.MyFadingActionBarHelper;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

public class MyPage extends ActionBarActivity {

    private Profile profile;
    private TextView tv_home_user_id;
    private TextView tv_home_user_name;
    private ProfilePictureView profilePictureView;
    private ProgressDialog mProgressDialog;
    private  RatingBar rating;
    private TextView point_txt;
    private CardView changeInterstedbtn;
    private CardView changeLocationbtn;

    private ImageView computer;
    private  ImageView marketing;
    private ImageView music_video;
    private ImageView design;
    private ImageView document;
    private ImageView translate;
    private ImageView life;
    private ImageView entertainment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        profile = Profile.getCurrentProfile();


        MyFadingActionBarHelper helper = new MyFadingActionBarHelper()
                .actionBarBackground(R.drawable.computer)
                .headerLayout(R.layout.header_mypage)
                .contentLayout(R.layout.activity_mypage_layout);

        setContentView(helper.createView(this));
        helper.initActionBar(this);

        ProfilePictureView profilePictureView = (ProfilePictureView)findViewById(R.id.home_profile_image_facebook);
        SharedPreferences pref = getSharedPreferences("PrefName", MODE_PRIVATE);
        String id = pref.getString("USER_ID", "NULL");
        SharedPreference sharedPreference = new SharedPreference(this);

        System.out.println("facebook 아이디 : "+id);
        profilePictureView.setProfileId(id);


        TextView user_txt = (TextView)findViewById(R.id.user_name);
        user_txt.setText(User.getInstance().getUserName());
        point_txt = (TextView)findViewById(R.id.mypage_pointtextview);
        rating = (RatingBar)findViewById(R.id.ratingBaruser);

        changeInterstedbtn = (CardView)findViewById(R.id.chage_interestetedbtn_mypage);
        changeInterstedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPage.this,interest.class);
                intent.putExtra("set",1);
                startActivity(intent);
            }
        });

        changeLocationbtn = (CardView)findViewById(R.id.change_mylocationbtn_mypage);
        changeLocationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPage.this,GoogleMap.class);
                intent.putExtra("set",2);
                startActivity(intent);
            }
        });


        ServerManager serverManager = ServerManager.getInstance();
        serverManager.getMyInformation();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                int reliability = User.getInstance().getReliability();
                rating.setNumStars(reliability);
                int point = User.getInstance().getPoint();
                point_txt.setText(String.valueOf(point));

            }
        }, 500);



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
        Intent intent = new Intent(getApplicationContext(), interest.class);
        startActivity(intent);
    }

    public void Onclick_GPS(View v) {
        Intent intent = new Intent(getApplicationContext(), GoogleMap.class);
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

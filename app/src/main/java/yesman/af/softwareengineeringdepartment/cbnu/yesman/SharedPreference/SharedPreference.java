package yesman.af.softwareengineeringdepartment.cbnu.yesman.SharedPreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by son on 2016-06-30.
 */

public class SharedPreference{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "PrefName";
    static Context mContext;

    public String domain0 = "domain0";   //디자인
    public String domain1 = "domain1";   //번역
    public String domain2 = "domain2";   //문서
    public String domain3 = "domain3";   //마케팅
    public String domain4 = "domain4";   //컴퓨터
    public String domain5 = "domain5";   //음악, 동영상
    public String domain6 = "domain6";   //생활서비스
    public String domain7 = "domain7";   //엔터테인먼트
    public String first_login = "FACEBOOK_FIRST_LOGIN";    //처음 로그인
    public String facebook_login = "FACEBOOK_LOGIN";   //페이스북 로그인
    public String user_id = "USER_ID"; //유저 아이디
    public String user_name = "USER_NAME";    //유저 y좌표
    public String user_x = "x";    //유저 x좌표
    public String user_y = "y";    //유저 y좌표
    public String reg_id = "USER_REG";

    public SharedPreference(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void put(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public String getValue(String key, String dftValue) {
        try {
            return sharedPreferences.getString(key, dftValue);
        } catch (Exception e) {
            // TODO: handle exception
            return dftValue;
        }
    }

    public int getValue(String key, int dftValue) {
        try {
            return sharedPreferences.getInt(key, dftValue);
        } catch (Exception e) {
            // TODO: handle exception
            return dftValue;
        }
    }

    public boolean getValue(String key, boolean dftValue) {
        try {
            return sharedPreferences.getBoolean(key, dftValue);
        } catch (Exception e) {
            // TODO: handle exception
            return dftValue;
        }
    }

    public void clear(){
        editor.clear();
    }
}

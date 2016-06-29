package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.GPS.GpsInfo;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

public class MapAcitivity extends AppCompatActivity {
    static final LatLng Center = new LatLng( 35.846777, 127.129375);
    private GoogleMap map;
    public String which;
    // 알림창에 들어가는 변수
    final Context context = this;
    private AlertDialog.Builder alert;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private double x;
    private double y;

                        @Override
                        protected void onCreate(Bundle savedInstanceState) {
                            super.onCreate(savedInstanceState);
                            setContentView(R.layout.activity_map);

                            MapsInitializer.initialize(getApplicationContext());
                            GooglePlayServicesUtil.isGooglePlayServicesAvailable(MapAcitivity.this);
                            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

                            pref = getSharedPreferences("PrefName", MODE_PRIVATE);  // ID,GPS정보 저장
                            editor = pref.edit();

                            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(LatLng arg0) {
                                    // TODO Auto-generated method stub

                        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(arg0.latitude, arg0.longitude ));
                        editor.putFloat("x", (float)arg0.latitude); //서버에 넘겨줄 좌표값
                        editor.putFloat("y", (float)arg0.longitude);
                        map.addMarker(markerOptions).showInfoWindow();
                    }
                 });

                map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.clear();
            }
        });



        MarkerOptions markerOptions = new MarkerOptions().position(Center);
        map.addMarker(markerOptions).showInfoWindow();

        alert = new AlertDialog.Builder(context);
        alert.setTitle("GPS설정정보");
        alert.setMessage("현재위치를 사용하시려면 아니오를\n위치를 새로 설정하시려면 예를\n눌러주세요.");

        alert.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //사용자 위치 선택 받기
            }
        });
        alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                init();
            }
        });
        alert.show();




    }
    private void init() {

        GpsInfo gps = new GpsInfo(MapAcitivity.this);
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            x = gps.getLatitude();
            y = gps.getLongitude();
            editor.putFloat("x", (float)x); //서버에 넘겨줄 좌표값
            editor.putFloat("y", (float)y);
            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(x, y);
            
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            map.animateCamera(CameraUpdateFactory.zoomTo(17));

            // 마커 설정.
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(latLng);// 위도 • 경도
            optFirst.title("회원님의 위치입니다.");// 제목 미리보기
            optFirst.snippet("요기!");
            optFirst.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
            map.addMarker(optFirst).showInfoWindow();
        } else {
            // GPS 를 사용할수 없으므로
            gps.showSettingsAlert();
        }
    }
    public void Onclick_next(View v) {
        Intent intent = getIntent();
        int set = intent.getIntExtra("set", 0);
        if(set == 1){
            finish();
        }
        else{
            startActivity(new Intent(this, interestAcitivity.class));
        }
        User user = User.getInstance();
        user.setX(x);
        user.setX(y);
        editor.commit();
    }

}
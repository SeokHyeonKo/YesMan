package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.GPS.GpsInfo;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO.ServerManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.SharedPreference.SharedPreference;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.CategoryDomainManager;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

public class GoogleMap extends AppCompatActivity {
    private com.google.android.gms.maps.GoogleMap map;
    public String which;
    // 알림창에 들어가는 변수
    final Context context = this;
    private AlertDialog.Builder alert;
    private double x;
    private double y;
    SharedPreference sharedPreference;
    GpsInfo gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap_layout);
        gps = new GpsInfo(GoogleMap.this);
        sharedPreference = new SharedPreference(this);
        MapsInitializer.initialize(getApplicationContext());
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(GoogleMap.this);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        map.setOnMapClickListener(new com.google.android.gms.maps.GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub

                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(arg0.latitude, arg0.longitude));
                Log.d("GPS수신......X : ", String.valueOf(arg0.latitude));
                Log.d("GPS수신......Y : ", String.valueOf(arg0.longitude));
                map.addMarker(markerOptions).showInfoWindow();
                x = arg0.latitude;
                y = arg0.longitude;
                CategoryDomainManager.x = x;
                CategoryDomainManager.y = y;
               // sharedPreference.put(sharedPreference.user_x, String.valueOf(arg0.latitude)); //서버에 넘겨줄 좌표값
                //sharedPreference.put(sharedPreference.user_y, String.valueOf(arg0.longitude));
            }
        });

        map.setOnMapLongClickListener(new com.google.android.gms.maps.GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.clear();
            }
        });

        alert = new AlertDialog.Builder(context);
        alert.setTitle("GPS설정정보");
        alert.setMessage("현재위치를 사용하시려면 아니오를\n위치를 새로 설정하시려면 예를\n눌러주세요.");

        alert.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //사용자 위치 선택 받기
                if (gps.isGetLocation()) {
                    x = gps.getLatitude();
                    y = gps.getLongitude();
                    CategoryDomainManager.x = x;
                    CategoryDomainManager.y = y;
                    LatLng latLng = new LatLng(x, y);
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    map.animateCamera(CameraUpdateFactory.zoomTo(17));
                }
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
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            x = gps.getLatitude();
            y = gps.getLongitude();
            CategoryDomainManager.x = x;
            CategoryDomainManager.y = y;
            //sharedPreference.put(sharedPreference.user_x, String.valueOf(x)); //서버에 넘겨줄 좌표값
            //sharedPreference.put(sharedPreference.user_y, String.valueOf(y));
            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(x, y);
            Log.d("GPS수신......X : ", String.valueOf(x));
            Log.d("GPS수신......Y : ", String.valueOf(y));
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
        int set = intent.getIntExtra("set",0);
        System.out.println("set : "+set);
        if(set == 1) {

            Board board = new Board(CategoryDomainManager.title,CategoryDomainManager.content, CategoryDomainManager.x, CategoryDomainManager.y);
            board.setCategory(CategoryDomainManager.category);
            User.getInstance().setCurrentBoard(board);

            ServerManager serverManager = ServerManager.getInstance();
            serverManager.registerBoard();
            finish();
        }else if(set==2) { // mypage에서 보내는 경우.
            User.getInstance().setX(x);
            User.getInstance().setY(y);
            ServerManager serverManager = ServerManager.getInstance();
            serverManager.changeMyLocation();
            finish();
        }
        else{
            sharedPreference = new SharedPreference(this);
            sharedPreference.put(sharedPreference.user_x,String.valueOf(CategoryDomainManager.x));
            sharedPreference.put(sharedPreference.user_y,String.valueOf(CategoryDomainManager.y));
            System.out.println("user x값 : "+x);
            System.out.println("user y값 : "+y);
            startActivity(new Intent(this, interest.class));
        }


    }

}
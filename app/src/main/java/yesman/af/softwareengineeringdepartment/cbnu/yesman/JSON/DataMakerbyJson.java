package yesman.af.softwareengineeringdepartment.cbnu.yesman.JSON;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

/**
 * Created by seokhyeon on 2016-06-25.
 */
public class DataMakerbyJson {

    private static DataMakerbyJson datamaker = null;


    // 요청 응답 다 사용가능.
    public ArrayList<Board> getBoardList(String response){
        ArrayList<Board> boardlist = new ArrayList<>();

       JSONObject jobj = null;


        try {
            jobj = new JSONObject(response);
            JSONArray jarr = jobj.getJSONArray("data");

            for(int i=0;i<jarr.length();i++){
                JSONObject tempobj = jarr.getJSONObject(i);
                int boardserial = tempobj.getInt("boardserialnumber");
                double x = tempobj.getDouble("x");
                double y = tempobj.getDouble("y");
                String title = tempobj.getString("title");
                String content = tempobj.getString("content");
                int domain = tempobj.getInt("domain");
                String requestID  = tempobj.getString("requestID");
                String acceptID = tempobj.getString("acceptID");
                int category = tempobj.getInt("category");
                int ischeckrequest = tempobj.getInt("ischeckrequest");
                int ischeckaccept = tempobj.getInt("ischeckaccept");
                int ismatching = tempobj.getInt("ismatching");
                System.out.println(ismatching);
                String userID = tempobj.getString("UserId");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date  = simpleDateFormat.parse(tempobj.getString("date"));

                Board board = new Board(boardserial,x,y,title,content,domain,requestID,acceptID,category,ischeckrequest,ischeckaccept,ismatching,date,userID);
                boardlist.add(board);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return boardlist;
    }

    public boolean chekUser(String response){

        boolean isExist = false;
        System.out.println(response);

        try{
            JSONObject json = new JSONObject(response);
            Log.w("그전 값 확인 : ",Boolean.toString(json.getBoolean("exist")));
            if(json.getBoolean("exist")) isExist = true;
            User.getInstance().setExist_already(true);

            Log.w("값 확인 ",Boolean.toString(isExist));
         } catch (JSONException e) {
            e.printStackTrace();
        }


        return isExist;
    }

    public int getPoint(String response){
        JSONObject json = null;
        int point = -10;
        try{
            json = new JSONObject(response);
            Log.w("포인트  -10일 경우 오류: ",String.valueOf(json.getInt("point")));
            point = json.getInt("point");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return point;

    }

    public int getReliability(String response){
        JSONObject json = null;
        int reliablity = -10;
        try{
            json = new JSONObject(response);
            Log.w("신뢰도 -10일 경우 오류 : ",String.valueOf(json.getInt("reliability")));
            reliablity = json.getInt("reliability");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reliablity;
    }

    public int chekMatchingCount(String response){
        JSONObject json = null;
        int count = -10;
        try{
            json = new JSONObject(response);
            Log.w("카운트 -10일 경우 오류 : ",String.valueOf(json.getInt("count")));
            count = json.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void getAllUserInfo(String response){ // User에 세팅한후 sharedpreference에도 저장해야함
        JSONObject json = null;
        User user = User.getInstance();

        try{
            json = new JSONObject(response);
            user.setDomain_dsign(json.getInt("domain_design"));
            user.setDomain_translate(json.getInt("domain_translate"));
            user.setDomain_document(json.getInt("domain_document"));
            user.setDomain_marketing(json.getInt("domain_marketing"));
            user.setDomain_computer(json.getInt("domain_computer"));
            user.setDomain_music(json.getInt("domain_music"));
            user.setDomain_service(json.getInt("domain_service"));
            user.setDomain_play(json.getInt("domain_play"));
            user.setX(Double.parseDouble(json.getString("x")));
            user.setY(Double.parseDouble(json.getString("y")));
            user.setRegID(json.getString("regID"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static synchronized DataMakerbyJson getDataMaker(){
        if(datamaker==null){
            datamaker = new DataMakerbyJson();
        }
        return datamaker;
    }


}

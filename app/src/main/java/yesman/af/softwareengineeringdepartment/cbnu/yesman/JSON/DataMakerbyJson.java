package yesman.af.softwareengineeringdepartment.cbnu.yesman.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;

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

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date  = simpleDateFormat.parse(tempobj.getString("date"));

                Board board = new Board(boardserial,x,y,title,content,domain,requestID,acceptID,category,ischeckrequest,ischeckaccept,ismatching,date);
                boardlist.add(board);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return boardlist;
    }


    public static synchronized DataMakerbyJson getDataMaker(){
        if(datamaker==null){
            datamaker = new DataMakerbyJson();
        }
        return datamaker;
    }


}

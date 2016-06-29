package yesman.af.softwareengineeringdepartment.cbnu.yesman.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

/**
 * Created by seokhyeon on 2016-06-24.
 */
public class JsonMaker {


    private static JsonMaker jsonmaker = null;
    public static int JOIN = 0,GET_REQUSET_LIST = 1,GET_DONATION_LIST = 2, REGISTER_BOARD = 3, CHANGE_LOCATION = 4,CHECK_MYBOARDLIST = 5;
    private JSONObject jsonobj;
    private JSONArray jsonarr;
    private int selected;

    public JSONObject makeJson(){
        JSONObject tempobj = null;
        User user = User.getInstance();
        if(selected==JOIN){
            tempobj = joinUserMakeJson(user);
        }else if(selected==GET_REQUSET_LIST || selected == GET_DONATION_LIST){
            tempobj = getRequsetList(user);
        }else if(selected==REGISTER_BOARD){
            tempobj = registerBoard(user);
        }else if(selected==CHANGE_LOCATION){
            tempobj = changeLocationJSON(user);
        }else if(selected==CHECK_MYBOARDLIST){
            tempobj = checkMyBoardListJSON(user);
        }


        return  tempobj;
    }

    public JSONObject joinUserMakeJson(User user){
        jsonobj = new JSONObject();
        try {

            jsonobj.put("UserId", user.getUserID());
            jsonobj.put("domain_dsign", user.getDomain_dsign());
            jsonobj.put("domain_translate", user.getDomain_translate());
            jsonobj.put("domain_document", user.getDomain_document());
            jsonobj.put("domain_marketing", user.getDomain_marketing());
            jsonobj.put("domain_computer", user.getDomain_computer());
            jsonobj.put("domain_music", user.getDomain_music());
            jsonobj.put("domain_service", user.getDomain_service());
            jsonobj.put("domain_play", user.getDomain_play());
            jsonobj.put("x", user.getX());
            jsonobj.put("y", user.getY());
            jsonobj.put("regID",user.getRegID());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonobj;
    }

    public JSONObject getRequsetList(User user){
        jsonobj = new JSONObject();
        try {
            jsonobj.put("x", user.getX());
            jsonobj.put("y", user.getY());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonobj;
    }

    public JSONObject registerBoard(User user){
        Board board = user.getCurrentBoard();
        //현재로 저장된 위치를 사용 할 것인지 새롭게 저장할 위치로 할 것인지 임시로 만들어놓은 변수
        boolean iscurrent = true;
        jsonobj = new JSONObject();
        try {
            jsonobj.put("title", board.getTitle());
            jsonobj.put("content", board.getContent());
            jsonobj.put("userid",user.getUserID());
            if(iscurrent){
                jsonobj.put("x",user.getX());
                jsonobj.put("y",user.getY());
            }else{
                jsonobj.put("x",board.getX());
                jsonobj.put("y",board.getY());
            }
            jsonobj.put("domain",board.getDomain());
            jsonobj.put("category",board.getCategory());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonobj;

    }


    //위치변경시 반드시 먼저 USER 객체의 userx usery 를 세팅해준후 보내야함
    public JSONObject changeLocationJSON(User user){
        jsonobj = new JSONObject();
        try {
            jsonobj.put("userID",user.getUserID());
            jsonobj.put("x", user.getX());
            jsonobj.put("y", user.getY());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonobj;
    }

    public JSONObject checkMyBoardListJSON(User user){
        jsonobj = new JSONObject();
        try {
            jsonobj.put("userID",user.getUserID());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonobj;
    }



    public void setSeleted(int seleted){
        this.selected = seleted;
    }

    public int getSeleted(){
        return selected;
    }

    public static synchronized JsonMaker getInstance(){
        if(jsonmaker==null){
            jsonmaker = new JsonMaker();
        }
        return jsonmaker;
    }

}

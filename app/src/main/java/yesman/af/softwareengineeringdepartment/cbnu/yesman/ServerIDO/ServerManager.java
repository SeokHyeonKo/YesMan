package yesman.af.softwareengineeringdepartment.cbnu.yesman.ServerIDO;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.JSON.JsonMaker;

/**
 * Created by seokhyeon on 2016-06-22.
 */
public class ServerManager {

    private static ServerManager serverManager;
    private ServerConnection server;
    private String path;
    private String url = "http://kossi.iptime.org:2000/YesManProject";

    public ServerConnection getServerConnection(){
        return server;
    }


    public void deleteBoard(){
        path="/deleteboard";
        JsonMaker.getInstance().setSeleted(JsonMaker.MODFIY_BOARD);
        checkTask();
        server.execute(url+path);
    }



    public void modifyBoard(){
        path ="/checkMatching";
        JsonMaker.getInstance().setSeleted(JsonMaker.MODFIY_BOARD);
        checkTask();
        server.execute(url+path);
    }

    public void checkMatching(){
        path ="/checkMatching";
        JsonMaker.getInstance().setSeleted(JsonMaker.CHECK_MATCHING);
        checkTask();
        server.execute(url+path);
    }

    public void changeInterested(){
        path ="/changeinterested";
        JsonMaker.getInstance().setSeleted(JsonMaker.CHANGE_INTERESTED);
        checkTask();
        server.execute(url+path);
    }

    public void getAllUserInfo(){
        path ="/getalluserinfo";
        JsonMaker.getInstance().setSeleted(JsonMaker.GETALLINFO);
        checkTask();
        server.execute(url+path);
    }


    public String cancelboard(){
        path ="/cancelboard";
        JsonMaker.getInstance().setSeleted(JsonMaker.ACCEPT_BOARD);
        //checkTask();
        return url+path;
    }

    public void acceptBoard(){
        path ="/acceptboard";
        JsonMaker.getInstance().setSeleted(JsonMaker.ACCEPT_BOARD);
        checkTask();
        server.execute(url+path);
    }


    public String registerReliAbility(){
        path ="/registerreliability";
        JsonMaker.getInstance().setSeleted(JsonMaker.REGISTER_RELIABILITY);
        //checkTask();
        return url+path;
    }

    public void getMyInformation(){
        path="/getmyinformation";
        JsonMaker.getInstance().setSeleted(JsonMaker.GET_MY_INFORMATION);
        checkTask();
        server.execute(url+path);
    }


    public void checkUser(){
        path="/checkuser";
        JsonMaker.getInstance().setSeleted(JsonMaker.CHECK_USER);
        checkTask();
        server.execute(url+path);
    }

    public void pushTest(){
        path="/push";
        JsonMaker.getInstance().setSeleted(JsonMaker.TEST);
        checkTask();
        server.execute(url+path);
    }

    public void checkMyBoardList(){
        path = "/getMyBoardList";
        JsonMaker.getInstance().setSeleted(JsonMaker.CHECK_MYBOARDLIST);
        checkTask();
        server.execute(url+path);
    }

    public void changeMyLocation(){
        path = "/changelocation";
        JsonMaker.getInstance().setSeleted(JsonMaker.CHANGE_LOCATION);
        checkTask();
        server.execute(url+path);
    }

    public void registerBoard(){
        path = "/registerBoard";
        JsonMaker.getInstance().setSeleted(JsonMaker.REGISTER_BOARD);
        checkTask();
        server.execute(url+path);
    }

    public void joinUser(){
        path = "/joinuser";
        JsonMaker.getInstance().setSeleted(JsonMaker.JOIN);
        checkTask();
        server.execute(url+path);
    }

    public void getRequest_BoardList(){
        path = "/getRequestList";
        JsonMaker.getInstance().setSeleted(JsonMaker.GET_REQUSET_LIST);
        checkTask();
        server.execute(url+path);
    }

    public void getDonation_BoardList(){
        path = "/getDonationList";
        JsonMaker.getInstance().setSeleted(JsonMaker.GET_DONATION_LIST);
        checkTask();
        server.execute(url+path);
    }

    public void checkTask(){
        if(server != null) {
            server.cancel(true);
            server = null;
        }
        server = new ServerConnection();
    }

    public static synchronized ServerManager getInstance(){
        if(serverManager==null){
            serverManager = new ServerManager();
        }
        return serverManager;
    }


}

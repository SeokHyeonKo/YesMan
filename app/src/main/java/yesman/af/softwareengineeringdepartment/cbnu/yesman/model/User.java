package yesman.af.softwareengineeringdepartment.cbnu.yesman.model;

import java.util.ArrayList;

/**
 * Created by seokhyeon on 2016-06-22.
 */
public class User {
    private static User user = null;

    private String userName;
    private String userID;
    private double x;
    private double y;
    private int domain_dsign;
    private int domain_translate;
    private int domain_document;
    private int domain_marketing;
    private int domain_computer;
    private int domain_music;
    private int domain_service;
    private int domain_play;

    private int point;
    private int reliability;
    private ArrayList<Board> BoardList = new ArrayList<Board>();
    private Board currentBoard = null;

    public boolean isExist_already() {
        return exist_already;
    }

    public void setExist_already(boolean exist_already) {
        this.exist_already = exist_already;
    }

    private String regID;
    private boolean exist_already = false;

    public int getDomain_dsign() {
        return domain_dsign;
    }

    public void setDomain_dsign(int domain_sign) {
        this.domain_dsign = domain_sign;
    }

    public int getDomain_translate() {
        return domain_translate;
    }

    public void setDomain_translate(int domain_translate) {
        this.domain_translate = domain_translate;
    }

    public int getDomain_document() {
        return domain_document;
    }

    public void setDomain_document(int domain_document) {
        this.domain_document = domain_document;
    }

    public int getDomain_marketing() {
        return domain_marketing;
    }

    public void setDomain_marketing(int domain_marketing) {
        this.domain_marketing = domain_marketing;
    }

    public int getDomain_computer() {
        return domain_computer;
    }

    public void setDomain_computer(int domain_computer) {
        this.domain_computer = domain_computer;
    }

    public int getDomain_music() {
        return domain_music;
    }

    public void setDomain_music(int domain_music) {
        this.domain_music = domain_music;
    }

    public int getDomain_service() {
        return domain_service;
    }

    public void setDomain_service(int domain_service) {
        this.domain_service = domain_service;
    }

    public int getDomain_play() {
        return domain_play;
    }

    public void setDomain_play(int domain_play) {
        this.domain_play = domain_play;
    }



    public String getRegID() {
        return regID;
    }

    public void setRegID(String regID) {
        this.regID = regID;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public User(){

    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }



    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public ArrayList<Board> getBoardList() {
        return BoardList;
    }

    public void setBoardList(ArrayList<Board> dashBoardList) {
        this.BoardList = dashBoardList;
    }



    public User(String userID){
        this.userID = userID;
    }

    public void setCurrentDashBoard(Board dashBoard){
        this.currentBoard = dashBoard;
    }

    public Board getCurrentBoard(){
        return currentBoard;
    }


    public String getUserID(){
        return userID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public static synchronized User getInstance(){
        if(user==null){
            user = new User();
        }
        return user;
    }

}

package yesman.af.softwareengineeringdepartment.cbnu.yesman.model;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;

/**
 * Created by seokhyeon on 2016-06-24.
 */
public class CategoryDomainManager {

    public static int REQUEST = 0, GIVE = 1;
    public static final int COMPUTER = 0, DESIGN = 1, DOCUMENT = 2, ENTERTAINMENT = 3, MOVIE_MUSIC =4, MARKETING = 5, LIFE = 6, TRANSLATE = 7;
    public static String title;
    public static String content;
    public static double x;
    public static double y;
    public static int category;
    public static boolean iscurrent;
    public static int domain;
    public static int isOk = 0; // 1이면 ok, 0이면 cancle , 2이면 연락, 3이면 보드 수정, 4이면 보드 삭제
    public static int ISOK = 1, ISCANCEL = 0, ISCONTACT=2, ISMODIFY=3,ISDELETE =4;
    public static int ismatching = R.color.fbutton_color_orange;
    public static int currenting = R.color.fbutton_color_turquoise;
    public static int finish = R.color.fbutton_color_green_sea;

}

package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;


/**
 * Created by Jo on 2016-06-30.
 */
public class DetailBoardActivity extends ActionBarActivity {

    private Board selectedBoard;
    TextView title;
    TextView id;
    TextView location;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_board);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        selectedBoard = (Board) intent.getSerializableExtra("selected");

        MyFadingActionBarHelper helper = new MyFadingActionBarHelper()
                .actionBarBackground(new ColorDrawable(Color.parseColor("#6799FF")))
                .headerLayout(R.layout.board_header)
                .contentLayout(R.layout.detail_board);
            setContentView(helper.createView(this));

        //이미지 변경
        ImageView domain = (ImageView)findViewById(R.id.header_image);
        domain.setImageResource(R.drawable.cat2);

        helper.initActionBar(this);

        setView();
    }

    public void setView(){
        title = (TextView) findViewById(R.id.detail_title);
        id = (TextView) findViewById(R.id.detail_id);
        location = (TextView) findViewById(R.id.detail_location);
        content = (TextView) findViewById(R.id.detail_content);

        title.setText(selectedBoard.getTitle());
        id.setText(selectedBoard.getRequestID());
        content.setText(selectedBoard.getContent());
    }
}
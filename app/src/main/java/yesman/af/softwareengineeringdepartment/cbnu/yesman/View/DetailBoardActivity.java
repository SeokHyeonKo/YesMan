package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;


/**
 * Created by Jo on 2016-06-30.
 */
public class DetailBoardActivity extends AppCompatActivity {

    private Board selectedBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        selectedBoard = (Board) intent.getSerializableExtra("selected");

        Toast.makeText(this, selectedBoard.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;

/**
 * Created by User on 2016-07-01.
 */
public class Write extends AppCompatActivity {
//ㅇㅇ
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_layout);

        Spinner s = (Spinner)findViewById(R.id.write_category);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Toast.makeText(parent.getContext(),
                        "'" + parent.getSelectedItem() + "'을 선택하였습니다.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

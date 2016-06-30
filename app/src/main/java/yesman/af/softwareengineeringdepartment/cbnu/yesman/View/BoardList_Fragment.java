package yesman.af.softwareengineeringdepartment.cbnu.yesman.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;

/**
 * Created by Jo on 2016-06-26.
 */
public class BoardList_Fragment extends Fragment {

    ArrayList<Board> data=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        ListView listView=(ListView)view.findViewById(R.id.board_list);

        Board item1=new Board("#1","리스트뷰로 바꿔줘", new Date(2016,02,03),3,3);
        Board item2=new Board("#2","배고파", new Date(2016,02,03),3,3);
        Board item3=new Board("#3","i'm hungry", new Date(2016,02,03),3,3);
        Board item4=new Board("#4","우옹~~", new Date(2016,02,03),3,3);
        Board item5=new Board("#5","스크롤 된당", new Date(2016,02,03),3,3);
        Board item6=new Board("#6","얍삐", new Date(2016,02,03),3,3);
        Board item7=new Board("#7","호호호호홍", new Date(2016,02,03),3,3);

        data.add(item1);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        data.add(item5);
        data.add(item6);
        data.add(item7);
        System.out.println("들어옴?");
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), R.layout.list_content, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        return view;
    }

    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub

            Intent detailBoard = new Intent(getActivity(), DetailBoardActivity.class);
            detailBoard.putExtra("selected", data.get(position));
            getContext().startActivity(detailBoard);
        }
    };
}

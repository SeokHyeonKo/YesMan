package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.AdapterAndFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.View.Activity.ContentBoard;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.User;

/**
 * Created by Jo on 2016-06-26.
 */
public class Fragment_BoardList_inMain extends Fragment {

    ArrayList<Board> data=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_fragment, container, false);

        ListView listView=(ListView)view.findViewById(R.id.board_list);

        data = User.getInstance().getBoardList();
        System.out.println("들어옴?");

        ListViewAdapter adapter = new ListViewAdapter(getActivity(), R.layout.content_listview_showboarlist_main, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);

        return view;
    }

    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub

            Intent detailBoard = new Intent(getActivity(), ContentBoard.class);
            detailBoard.putExtra("selected", data.get(position));
            getContext().startActivity(detailBoard);
        }
    };
}

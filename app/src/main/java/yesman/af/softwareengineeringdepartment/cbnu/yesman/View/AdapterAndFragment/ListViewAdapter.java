package yesman.af.softwareengineeringdepartment.cbnu.yesman.View.AdapterAndFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import yesman.af.softwareengineeringdepartment.cbnu.yesman.R;
import yesman.af.softwareengineeringdepartment.cbnu.yesman.model.Board;

/**
 * Created by Jo on 2016-06-30.
 */
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Board> data;
    private int layout;

    public ListViewAdapter(Context context, int layout, ArrayList<Board> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
    }
    @Override
    public int getCount(){return data.size();}
    @Override
    public String getItem(int position){return data.get(position).getTitle();}
    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        Board listviewitem = data.get(position);

        ImageView icon = (ImageView) convertView.findViewById(R.id.board_image);
        icon.setImageResource(R.drawable.bestseller_circle);

        TextView name = (TextView) convertView.findViewById(R.id.title);
        name.setText(listviewitem.getTitle());

        TextView content = (TextView) convertView.findViewById(R.id.content);
        content.setText(listviewitem.getContent());
        return convertView;
    }
}
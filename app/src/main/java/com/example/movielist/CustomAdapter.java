package com.example.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> titles;
    ArrayList<Integer> ids;

    public CustomAdapter(Context context, ArrayList<String> titles, ArrayList<Integer> ids){
        this.context = context;
        this.titles = titles;
        this.ids = ids;
    }

    @Override
    public int getCount() {
        return this.titles.size();
    }

    @Override
    public Object getItem(int position) {
        return this.titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.ids.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list, null);
            TextView title = (TextView)convertView.findViewById(R.id.item_text);

            title.setText((position + 1) + ". " + titles.get(position));

            return convertView;
    }
}

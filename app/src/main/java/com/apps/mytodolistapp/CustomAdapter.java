package com.apps.mytodolistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataBaseConnections.ToDOItemListFields> {
    public CustomAdapter(Context context, ArrayList<DataBaseConnections.ToDOItemListFields> todolists) {
        super(context, 0, todolists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataBaseConnections.ToDOItemListFields todolists = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }
        // Lookup view for data population
        TextView todoname = (TextView) convertView.findViewById(R.id.todoBody);
        //***TextView todoitemid = (TextView) convertView.findViewById(R.id.todoPriority);
        // Populate the data into the template view using the data object
        todoname.setText(todolists.ToDoItemName);
        //***todoitemid.setText(Integer.toString(todolists.id));
        //String strI = Integer.toString(todolists.id);
        // Return the completed view to render on screen
        return convertView;
    }

}
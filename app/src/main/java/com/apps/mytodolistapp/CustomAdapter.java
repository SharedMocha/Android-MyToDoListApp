package com.apps.mytodolistapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataBaseConnections.ToDOItemListFields> {
    public CustomAdapter(Context context, ArrayList<DataBaseConnections.ToDOItemListFields> todolists) {
        super(context, 0, todolists);
    }
    //TextView sam  = (TextView) findViewById(R.id.todoListView);
    private final int REQUEST_CODE = 30;


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        // Get the data item for this position
        DataBaseConnections.ToDOItemListFields todolists = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }
        // Lookup view for data population
        final TextView todoname = (TextView) convertView.findViewById(R.id.todoBody);
        final TextView tododate = (TextView) convertView.findViewById(R.id.dateBody);

        //***TextView todoitemid = (TextView) convertView.findViewById(R.id.todoPriority);
        // Populate the data into the template view using the data object
        todoname.setText(todolists.ToDoItemName);
        tododate.setText(todolists.DateObtained);

        //handle date-if blank set visibility to GONE-- --INVISIBLE DIDN'T WORK
        //final TextView tododate = (TextView) convertView.findViewById(R.id.dateBody);
        /**
        if(tododate.getText().toString().trim()=="")
        {

            //tododate.setText("pack");
            tododate.setVisibility(View.GONE);


        }else
        {
            tododate.setText(todolists.DateObtained);
        }
        //final tododate dataModel = tododate.get(position);

*/

        //***todoitemid.setText(Integer.toString(todolists.id));
        //String strI = Integer.toString(todolists.id);
        // Return the completed view to render on screen
        Button editdate = (Button) convertView.findViewById(R.id.buttontoadddate);
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateCalltoActivity(position,tododate.getText().toString(),todoname.getText().toString());

            }

            public void initiateCalltoActivity(int position,String DatetoModify,String ItemtoEditName){
                //Toast.makeText(this,item+editableitemno, Toast.LENGTH_SHORT).show();
                Intent callEditItemActivity = new Intent(parent.getContext(),EditDateActivityandSelectPriority.class);
                callEditItemActivity.putExtra("DateToEdit",DatetoModify);
                callEditItemActivity.putExtra("PositiontoEdit",position);
                callEditItemActivity.putExtra("EditItemName",ItemtoEditName);

                //--callEditItemActivity.putExtra("ItemName",position);

                //startActivityForResult(callEditItemActivity, REQUEST_CODE);
                //startActivityForResult(callEditItemActivity,REQUEST_CODE);
                //callEditItemActivity.putExtra()
                //parent.getContext().startActivity(callEditItemActivity);
                //parent.getContext().startActivityForResult(callEditItemActivity,REQUEST_CODE);
                //Nw WAY OF CALLING ACTIVITY ON RSULTS--or else its not visible
                ((Activity) parent.getContext()).startActivityForResult(callEditItemActivity, REQUEST_CODE);

            }

            //@Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
/**
                // REQUEST_CODE is defined above
                //Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();
                if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
                    // Extract name value from result extras
                    String name = data.getExtras().getString("ModifiedText");
                    int code = data.getExtras().getInt("code", 0);
                    //Toast the name to display temporarily on screen
                    //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                    //Update array list and then call adapater
                    //Check if user is removing the item-Exception Case
                    if (name.trim().length() != 0) {
                        ToDoItemsList.set(editableitemno, name);
                        ToDoAdapater.notifyDataSetChanged();
                        //writeToFile();
                        DataBaseConnections savedbconnection = new DataBaseConnections(MainActivity.this,"null",null,1);
                        //savedbconnection.savedatbacktoDB(arrayOftodoitems);
                    }else {
                        ToDoItemsList.remove(editableitemno);
                        ToDoAdapater.notifyDataSetChanged();
                    }
                }
 */
            }

        });



        return convertView;
    }


}
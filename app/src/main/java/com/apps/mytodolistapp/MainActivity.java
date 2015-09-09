package com.apps.mytodolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//public class MainActivity extends AppCompatActivity implements EditNameDialog.EditNameDialogListener {
public class MainActivity extends AppCompatActivity implements EditItemFragment.EditNameDialogListener{

    //Declare Needed Variables
    public  ArrayList<String> ToDoItemsList;
    public ArrayAdapter<String> ToDoAdapater;

    public ListView ToDoMainList;
    private EditText newItemDetails;
    private final int REQUEST_CODE = 20;
    public int editableitemno;
    Button editformore;

    static ArrayList<DataBaseConnections.ToDOItemListFields> arrayOftodoitems;
    //ArrayList<String> extractedItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //showEditDialog();
        // Call additemtoList Method to add items to ListView
        // Initalize this Array List -or else app crashes.
        ToDoItemsList = new ArrayList<String>();
        // extractedItemList = new ArrayList<ToDoItemsList>();
        //readFromFile();
        //addItemstoList();
        arrayOftodoitems = new ArrayList<DataBaseConnections.ToDOItemListFields>();
        final DataBaseConnections dbconnection = new DataBaseConnections(MainActivity.this,"null",null,1);
        dbconnection.fetchDatafromDB();
        arrayOftodoitems = dbconnection.todoItemListFields;
        //addItemstoList();
        //ToDoMainList = (ListView) findViewById(R.id.todoListView);
        //ToDoMainList.setAdapter(ToDoAdapater);
        final CustomAdapter adapter = new CustomAdapter(this, arrayOftodoitems);

        // Attach the adapter to a ListView
        ToDoMainList = (ListView) findViewById(R.id.todoListView);
        ToDoMainList.setAdapter(adapter);
        newItemDetails = (EditText) findViewById(R.id.enterItem);
        editformore = (Button) findViewById(R.id.buttontoadddate);
        ToDoMainList.setItemsCanFocus(false);



        //DataBaseConnections savedbconnection = new DataBaseConnections(MainActivity.this,"null",null,1);
        /**
        extractedItemList = dbconnection.todoListItemDetails;
        ToDoItemsList = dbconnection.todoListItemDetails;
        ToDoItemsList  = dbconnection.todoListItemDetails;
        ToDoMainList = (ListView) findViewById(R.id.todoListView);
        ToDoMainList.setAdapter(ToDoAdapater);
        newItemDetails = (EditText) findViewById(R.id.enterItem);
        //addItemstoList();
         */
        //Using Inner Class as it's helpful

        ToDoMainList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ToDoItemsList.remove(i);
                //arrayOftodoitems.remove(i);
                //ToDoMainList.remove(i);
                arrayOftodoitems.remove(i);
                //adapterView.remove(arrayOftodoitems.get(i));
                //adapterView.removeViewAt(i);
                //adapterView.not
                //adapter.remove();
                //adapter.notifyDataSetChanged();
                //arrayOftodoitems.notify();

                //arrayOftodoitems.remove(i);
                DataBaseConnections savedbconnection = new DataBaseConnections(MainActivity.this,"null",null,1);
                  savedbconnection.savedatbacktoDB(arrayOftodoitems);

                //added below method--if not when we try to remve 9after edit) its giving error saying list and adapater is not in sync
                callAdapter();



                //--VV IML--Here we need to attach the adapter to LISTVIEW or else we will get no lock exception due to scope changes from activity to activity...Notifydatcahnged on adapter will not work
                //ToDoMainList.setAdapter(adapter);
                //dapter.notifyDataSetChanged();

                //writeToFile();
                //dbconnection.savedatbacktoDB(arrayOftodoitems);
                //Intent iii = new Intent(MainActivity.this,EditItemActivity.class);
                //startActivity(iii);
                //ToDoAdapater.remove(ToDoItemsList.get(1).toString());
                return true;
            }
        });
        // ToDoMainList.setOnClickListener(setIntent
        // (Intent i = new Intent(MainActivity.this,EditItemActivity.class))
       ToDoMainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String itemdetails = arrayOftodoitems.get(i).ToDoItemName;
               String date = arrayOftodoitems.get(i).DateObtained;



               //Comment below step--use ADAPATERVIEW only when using OOTB ARRAYLIST--If using custom adapater call the arraylist as above
               //String itemdetails = (String) adapterView.getItemAtPosition(i);
               //editableitemno = (int) adapterView.getItemIdAtPosition(i);
               //initiateCalltoActivity(itemdetails,editableitemno);
               adapter.notifyDataSetChanged();

               onitemClicktoFragment(itemdetails,i,date);

           }
       });

    }
        // Used to add items to list
    /**
    public void addItemstoList() {
        //ToDoAdapater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ToDoItemsList);
        //ToDoAdapater = new ArrayAdapter<DataBaseConnections.ToDOItemListFields>(this, R.layout.item_todo, arrayOftodoitems);

        //Instantiate Array List and Item Items to It
        //ToDoItemsList = new ArrayList<String>();
         //ToDoItemsList.add(0, "One");
        //ToDoItemsList.add(1, "Two");
        //ToDoAdapater.notifyDataSetChanged();
        //Instantiate Array Adapter and Tue List View to it for display of values
        //Added below adapter for practise/testing
        //ToDoAdapater = new ArrayAdapter<String>(this, R.layout.additemlayout, ToDoItemsList);
        //ToDoAdapater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, ToDoItemsList);
    }
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Used in adding items to list and display Toast if no todo item to add
    public void addtoTODOList(View view) {
        if (newItemDetails.getText().toString().trim().length() != 0) {
            CustomAdapter adapter = new CustomAdapter(this, arrayOftodoitems);
            arrayOftodoitems.add(new DataBaseConnections.ToDOItemListFields(newItemDetails.getText().toString(), 0,""));
            adapter.notifyDataSetChanged();
            //ToDoAdapater.notifyDataSetChanged();
             //ToDoItemsList.add(newItemDetails.getText().toString());
             //ToDoAdapater.notifyDataSetChanged();
            newItemDetails.setText("");
            //writeToFile();
            //ToDoMainList
            DataBaseConnections savedbconnection = new DataBaseConnections(MainActivity.this,"null",null,1);
            savedbconnection.savedatbacktoDB(arrayOftodoitems);


        }else {
            Toast.makeText(this, "No Item to Add", Toast.LENGTH_SHORT).show();
        }

        //ToDoItemsList.add(newItemDetails.getText().toString());
        //ToDoAdapater.notifyDataSetChanged();
        //newItemDetails.setText("");
        //ToDoAdapater.notify();
        //Toast.makeText(this,newItemDetails.getText().toString(),Toast.LENGTH_SHORT).show();
    }
    /*
    //Read from File called during app launch to display items
    private void readFromFile(){
        File filedir = getFilesDir();
        File file = new File(filedir,"ToDoListMaster.txt");
        try{
            ToDoItemsList = new ArrayList<String>(FileUtils.readLines(file));
        }catch(IOException e){
            Log.e("Caused from TO-DO App", "Unable to Open File");
        }
    }

    //Write to File called when user performs add and delete actions
    public void writeToFile(){
        File filedir = getFilesDir();
        File file = new File(filedir,"ToDoListMaster.txt");
        try{
            FileUtils.writeLines(file,ToDoItemsList);
        }catch(IOException e){
            Toast.makeText(this,"To-Do Item Not added to list.Please try again by relaunching the app", Toast.LENGTH_SHORT).show();
            Log.e("Caused from TO-DO App", "Unable to Write to File");
        }
    }
    */
    public void initiateCalltoActivity(String item,int editableitemno ){
        //Toast.makeText(this,item+editableitemno, Toast.LENGTH_SHORT).show();
         Intent callEditItemActivity = new Intent(MainActivity.this,EditItemActivity.class);
         callEditItemActivity.putExtra("texttoEdit",item);
         callEditItemActivity.putExtra("editableitemno",editableitemno);
         startActivityForResult(callEditItemActivity,REQUEST_CODE);
        //callEditItemActivity.putExtra()
        //startActivity(callEditItemActivity);
    }
    // ActivityOne.java, time to handle the result of the sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

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
        }else if (resultCode == RESULT_OK && requestCode == 30){

            String EditItemNameObtained = data.getExtras().getString("ItemEditName");
            //Log.e("1",EditItemNameObtained);
            String dateobtained = data.getExtras().getString("DateCombined");
            //Log.e("2",dateobtained);
            int code = data.getExtras().getInt("ItemtoEdit", 0);
            //Log.e("3",Integer.toString(code));
            //Log.e("4", Integer.toString(code));
            arrayOftodoitems.set(code, new DataBaseConnections.ToDOItemListFields(EditItemNameObtained, code, dateobtained));
            CustomAdapter adapter = new CustomAdapter(this, arrayOftodoitems);
            //--VV IML--Here we need to attach the adapter to LISTVIEW or else we will get no lock exception due to scope changes from activity to activity...Notifydatcahnged on adapter will not work
            ToDoMainList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            DataBaseConnections savedbconnection = new DataBaseConnections(MainActivity.this,"null",null,1);
            savedbconnection.savedatbacktoDB(arrayOftodoitems);
            callAdapter();


        }
        else
        {

        }
    }
    /** This below code is imp as we learn't from it and shows how to launch a simple dialog fragment--
     * IMP READ : Used intially to leaRn---uncoment this to use EditNameDialog-Learning.java and fragment_edit_name.xml

     public void showEditDialog(){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        LearningEditNameDialog end = LearningEditNameDialog.newInstance("Somessssss Title");
        end.show(fm, "learning_edit_item_dialog_fragment");



    }
     */
//Calling the Interface Method from EditItemFragment.java
    public void onitemClicktoFragment(String itemdetails,int editableitemno,String date)
    {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        //Log.e("tooditemname",itemdetails);
        //Log.e("id",Integer.toString(editableitemno));
        //Instansiation and calling--USE THIS -DONT use new and then call the method on top of it...as it didnt work when passing title
        EditItemFragment eit = EditItemFragment.newInstance("Edit To-Do Item",itemdetails,editableitemno,date);
        //int passtheclickeditem = editableitemno;
        //EditItemFragment eit = new EditItemFragment();
        //eit.newInstance("Edit To-Do Item");
        eit.show(fm, "edit_item_dialog_fragment");

    }

    @Override
    public void onFinishEditDialogcontrol(String inputText,int Location,String Date) {
        //arrayOftodoitems.set(Location, new DataBaseConnections.ToDOItemListFields(inputText, Location));
        //CustomAdapter adapter = new CustomAdapter(this, arrayOftodoitems);
        //arrayOftodoitems.add(new DataBaseConnections.ToDOItemListFields("inputText", 2));
        //arrayOftodoitems.add()
        //adapter.notifyDataSetChanged();
        //arrayOftodoitems
        //arrayOftodoitems.add(new DataBaseConnections.ToDOItemListFields("ppppppppppppppp", 0));
        arrayOftodoitems.set(Location, new DataBaseConnections.ToDOItemListFields(inputText, Location,Date));
        CustomAdapter adapter = new CustomAdapter(this, arrayOftodoitems);
        //--VV IML--Here we need to attach the adapter to LISTVIEW or else we will get no lock exception due to scope changes from activity to activity...Notifydatcahnged on adapter will not work
        ToDoMainList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //arrayOftodoitems.set(Location,)
        //arrayOftodoitems.notify();
        //adapter.notifyDataSetChanged();
        //ToDoAdapater.notifyDataSetChanged();
        //ToDoItemsList.add(newItemDetails.getText().toString());
        //ToDoAdapater.notifyDataSetChanged();
        //newItemDetails.setText("");
        //writeToFile();
        //ToDoMainList
        DataBaseConnections savedbconnection = new DataBaseConnections(MainActivity.this,"null",null,1);
        savedbconnection.savedatbacktoDB(arrayOftodoitems);

    }

    public void callAdapter() {
        CustomAdapter adapter = new CustomAdapter(this, arrayOftodoitems);
        ToDoMainList.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }







}


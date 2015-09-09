package com.apps.mytodolistapp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by htammare on 9/2/2015.
 */
public class EditItemFragment extends DialogFragment implements View.OnClickListener {

    public interface EditNameDialogListener {
        void onFinishEditDialogcontrol(String inputText,int Location,String Date);
    }

    public EditItemFragment() {
        //blank cosntructor needed for for dialog fragment
    }

    EditText edittodoitem;
    Button cancelbutton;
    Button savebutton;

    public  static EditItemFragment newInstance(String title, String ItemtoEdit,int editableitemno,String date) {
        EditItemFragment editnamemaster = new EditItemFragment();
        Bundle bund = new Bundle();
        bund.putString("title", title);
        bund.putString("itemtoedit", ItemtoEdit);
        bund.putInt("locationofToDoItem", editableitemno);
        bund.putString("date", date);
        editnamemaster.setArguments(bund);
        return editnamemaster;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_item_dialog_fragment, container);
        edittodoitem = (EditText) view.findViewById(R.id.editToDoItem);
        cancelbutton = (Button) view.findViewById(R.id.cancelbutton);
        savebutton = (Button) view.findViewById(R.id.savebutton);

        cancelbutton.setOnClickListener(this);
        //cancelbutton.se
        savebutton.setOnClickListener(this);
        String title = null;
        String Date = null;
        title = getArguments().getString("title", "Edit Item");
        Date = getArguments().getString("date","date");
        edittodoitem.setText(getArguments().getString("itemtoedit", "Editable to-do item"));
        //edittodoitem.setText(getArguments().getString("Date", "Editable to-do item"));
        //Log.e("date",getArguments().getString("date"));

        getDialog().setTitle("                 " + title);
        //getDialog().tit
        //

//        /getDialog().
        edittodoitem.requestFocus();
        return view;


    }

    public void updateitem(View view) {
    }


    @Override
    public void onClick(View view) {
        if (view == view.findViewById(R.id.cancelbutton)) {
            getDialog().cancel();


        } else {
            /**
//READ THIS--VV IMP --Here comes interface
            We tried 3 options as below but only one worked
                    1.)Create a instance of MainActivity and then call the method we wanted :This looks good but all old data is lost as we are creating a new isntance-causing our arraylist of data to vanish
                    2.)Made this method and method in MainActivity as STATIC ...this also no work as we need to call adapater after array update .now we need to pass "this" content to array adapter and u can't send "this" in static"
                    3.)Create interface in this activiv and then call lsitner as below and make mainactivity. extend out interface--this worked
                    */
            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
            //Log.e("!!!!!!!!!!!!!!!!!", Integer.toString(actionId));

            listener.onFinishEditDialogcontrol(edittodoitem.getText().toString(), getArguments().getInt("locationofToDoItem"),getArguments().getString("date"));
            getDialog().dismiss();


            //MainActivity.onFinishEditDialogcontrol();
            //MainActivity.onFinishEditDialog();
            //MainActivity.
            //MainActivity.updateToDoListFromDialogFragment(edittodoitem.getText().toString(), getArguments().getInt("locationofToDoItem"));
            //MainActivity callupdate = new MainActivity();
            //callupdate.updateToDoListFromDialogFragment(edittodoitem.getText().toString(),getArguments().getInt("locationofToDoItem"));




        }
    }
}

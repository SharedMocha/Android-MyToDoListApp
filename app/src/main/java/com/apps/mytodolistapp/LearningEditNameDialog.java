package com.apps.mytodolistapp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by htammare on 9/1/2015.
 */
public class LearningEditNameDialog extends DialogFragment implements TextView.OnEditorActionListener {
    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }
    private EditText TempET;



    //constructor req for dialog fragemnt--empty constructor
    public LearningEditNameDialog(){

    }


    public static LearningEditNameDialog newInstance(String title){

        LearningEditNameDialog editnamemaster = new LearningEditNameDialog();
        Bundle bund = new Bundle();
        bund.putString("title", title);
        editnamemaster.setArguments(bund);
        return editnamemaster;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_fragment_edit_name, container);
        TempET = (EditText) view.findViewById(R.id.txt_your_name);
        //String title = getArguments().getString("title", "Enter Name");
        String title = getArguments().getString("title");
        getDialog().setTitle(title);
        // Show soft keyboard automatically
        TempET.requestFocus();
        TempET.setText("test");
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        TempET.setOnEditorActionListener(this);
        //TempET.setonED

        //onEditorAction(TempET, TempET.getImeActionId(), null);
        return view;
    }


    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        Log.e("we", Integer.toString(actionId));
        //Log.e("we",Integer.toString(EditorInfo.IME_ACTION_DONE)));

        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
            listener.onFinishEditDialog(TempET.getText().toString());
            //listener.onFinishEditDialog("sam");
            //String sammm = listener.toString();
            dismiss();
            return true;
        }
        else{
            Log.e("wsssssssssssssssse", Integer.toString(actionId));

        }
        return false;
    }

}

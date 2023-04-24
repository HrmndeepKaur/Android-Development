package com.example.quiz_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class Ques_Num extends DialogFragment {
    interface DialogClickListener{
        void Ok_btn_Click(int n);
        void Cancel_btn_Click();
    }

    private static final String arg = "arg1";
    private int total_ques;
    public static final String tag ="tag";
    public DialogClickListener handler;

    public Ques_Num(){}

    public static Ques_Num newInstance(int arg1)
    {
        Ques_Num fragment = new Ques_Num ();
        Bundle args = new Bundle();
        args.putInt(arg, arg1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            total_ques = getArguments().getInt(arg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        TextView textView;
        EditText editText;
        Button okbtn;
        Button cancelbtn;

        View view = inflater.inflate(R.layout.num_ques, container, false);
        textView = view.findViewById(R.id.txt_Info);
        editText = view.findViewById(R.id.enter_num);
        okbtn = view.findViewById(R.id.btn_Okay);
        cancelbtn = view.findViewById(R.id.btn_Cancel);

        textView.setText(getResources().getString(R.string.number_of_ques) + " " + total_ques);

        okbtn.setOnClickListener(new View.OnClickListener(){
            int num;
            @Override
            public void onClick(View view){
                if(!editText.getText().toString().isEmpty()){
                    try {
                        num = Integer.parseInt(editText.getText().toString());
                        if(num > total_ques || num <= 0){
                            num = -1;
                        }
                    } catch(Exception e){
                        num = -1;
                    }
                }
                else{
                    num = -1;
                }
                handler.Ok_btn_Click(num);
                dismiss();
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.Cancel_btn_Click();
                dismiss();
            }
        });
        return view;
    }
}

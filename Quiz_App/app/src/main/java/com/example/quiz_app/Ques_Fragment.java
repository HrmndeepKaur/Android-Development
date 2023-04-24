package com.example.quiz_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Ques_Fragment extends Fragment {

private static final String arg1 = "arg1";
private static final String arg2 = "arg2";
private static final String arg3 = "arg3";

private int ques, color;
public boolean ans;

public Ques_Fragment(){}

    public static Ques_Fragment createInstance(int ques, int color, boolean ans)
    {
        Ques_Fragment quesfrag = new Ques_Fragment();
        Bundle args = new Bundle();

        args.putInt(arg1, ques);
        args.putInt(arg2, color);
        args.putBoolean(arg3, ans);
        quesfrag.setArguments(args);
        return quesfrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            ques = getArguments().getInt(arg1);
            color = getArguments().getInt(arg2);
            ans = getArguments().getBoolean(arg3);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

    View view = inflater.inflate(R.layout.ques_fragment, container, false);

    TextView tview = view.findViewById(R.id.ques_txt);
    tview.setText(ques);

    view.setBackgroundColor(getResources().getColor(color));
    return view;
    }

}

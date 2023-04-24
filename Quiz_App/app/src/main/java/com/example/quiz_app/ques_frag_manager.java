package com.example.quiz_app;

import java.util.ArrayList;

public class ques_frag_manager {
    ArrayList<Ques_Fragment> fragment_manager = new ArrayList<Ques_Fragment>(0);

    public void addFragment(Ques_Fragment qf){
        fragment_manager.add(qf);
    }
}

package com.example.quiz_app;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
    File_Storage file_storage = new File_Storage();
    ques_frag_manager manager = new ques_frag_manager();
    ArrayList<Integer> backColor = new ArrayList<Integer>() {{

        add(R.color.red);
        add(R.color.Light_brown);
        add(R.color.teal_700);
        add(R.color.purple_200);
        add(R.color.purple_500);
        add(R.color.teal_200);
        add(R.color.purple_700);
        add(R.color.grey);
        add(R.color.yellow);
        add(R.color.peach);

    }};
    ArrayList<Question> qBank = new ArrayList<Question>(){{
        add(new Question(R.string.q1, true));
        add(new Question(R.string.q2, false));
        add(new Question(R.string.q3, false));
        add(new Question(R.string.q4, true));
        add(new Question(R.string.q5, true));
        add(new Question(R.string.q6, true));
        add(new Question(R.string.q7, true));
        add(new Question(R.string.q8, false));
        add(new Question(R.string.q9, true));
        add(new Question(R.string.q10, false));
    }};
}

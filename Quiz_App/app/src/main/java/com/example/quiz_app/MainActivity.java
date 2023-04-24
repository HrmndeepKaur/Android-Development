package com.example.quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Ques_Num.DialogClickListener {

    Button true_btn;
    Button false_btn;
    ProgressBar progressBar;
    ArrayList<Ques_Fragment> quiz;
    int counter;
    int no_of_correct_ans;
    int total_Ques;
    int no_of_bankques;
    FragmentManager fm = getSupportFragmentManager();
    File_Storage file_Storage;
    AlertDialog.Builder builder;
    AlertDialog.Builder infobuilder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        true_btn = findViewById(R.id.btn_true);
        false_btn = findViewById(R.id.btn_false);
        progressBar = findViewById(R.id.prgress_bar);
        file_Storage = ((MyApp)getApplication()).file_storage;
        no_of_bankques = ((MyApp)getApplication()).qBank.size();
        builder = new AlertDialog.Builder(this);
        infobuilder = new AlertDialog.Builder(this);
        true_btn.setOnClickListener(this);
        false_btn.setOnClickListener(this);

        if(savedInstanceState != null){
            no_of_correct_ans = savedInstanceState.getInt("correctAnswers");
            counter = savedInstanceState.getInt("counter");
            total_Ques = savedInstanceState.getInt("totalQues");
            quiz = ((MyApp)getApplication()).manager.fragment_manager;

            if(counter < total_Ques){
                fm.beginTransaction().replace(R.id.fragment_container, quiz.get(counter)).commit();
                progressBar.setMax(total_Ques);
                progressBar.setProgress(counter);
            }
            else{
                showALertDialogForQuizResult();
            }
        }
        else{
            int default_no_of_ques = ((MyApp)getApplication()).qBank.size();
            quiz = createQuiz(default_no_of_ques);
            counter = 0;
            no_of_correct_ans = 0;
            total_Ques = default_no_of_ques;
            fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.beginTransaction().add(R.id.fragment_container, quiz.get(0)).commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
        outState.putInt("correctAnswers", no_of_correct_ans);
        outState.putInt("totalQues", total_Ques);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        no_of_correct_ans = savedInstanceState.getInt("correctAnswers");
        counter = savedInstanceState.getInt("counter");
        total_Ques = savedInstanceState.getInt("totalQues");
    }

    private void showALertDialogForTotalResult() {
        ArrayList<Integer> result = file_Storage.read_result(MainActivity.this);
        infobuilder.setMessage(getResources().getString(R.string.totalResult) + result.get(0) + getResources().getString(R.string.div) + result.get(1)).setCancelable(true).show();
    }

    public ArrayList<Ques_Fragment> createQuiz(int num_QUES) {
        progressBar.setMax(num_QUES);
        progressBar.setProgress(0);

        Random random = new Random();
        int number;
        int qBanknum = ((MyApp)getApplication()).qBank.size();
        int qbackcolor = ((MyApp)getApplication()).backColor.size();

        ArrayList<Integer> quesNumbers = new ArrayList<>(num_QUES);
        ArrayList<Question> questions = ((MyApp)getApplication()).qBank;
        ArrayList<Integer> colors = ((MyApp)getApplication()).backColor;
        ArrayList<Integer> ques_range = new ArrayList<>(qBanknum);

        for(int i =0; i<qBanknum; i++){
            ques_range.add(i);
        }
        for(int i = 0; i<num_QUES; i++){
            number = random.nextInt(ques_range.size());
            quesNumbers.add(ques_range.get(number));
            ques_range.remove(number);
        }
        ((MyApp)getApplication()).manager.fragment_manager = new ArrayList<Ques_Fragment>(num_QUES);

        for(int i = 0; i < num_QUES; i++){
            Ques_Fragment ques_fragment = Ques_Fragment.createInstance(questions.get(quesNumbers.get(i)).ques, colors.get(random.nextInt(qbackcolor)),questions.get(quesNumbers.get(i)).ans);
            ((MyApp)getApplication()).manager.fragment_manager.add(ques_fragment);
        }
        return ((MyApp)getApplication()).manager.fragment_manager;
    }

    private void showALertDialogForQuizResult() {
        builder.setMessage(getResources().getString(R.string.score) + no_of_correct_ans +" "+ getResources().getString(R.string.out_of) + total_Ques).setCancelable(false).setPositiveButton(getResources().getString(R.string.save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList<Integer> result = file_Storage.read_result(MainActivity.this);
                int new_correct_answer = result.get(0) + no_of_correct_ans;
                int new_total_quess = result.get(1) + total_Ques;
                file_Storage.write_new_result_to_file(MainActivity.this, new_correct_answer, new_total_quess);
                counter =0;
                no_of_correct_ans = 0;
                quiz = createQuiz(total_Ques);
                fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fm.beginTransaction().replace(R.id.fragment_container, quiz.get(0)).commit();
            }
        }).setNegativeButton(getResources().getString(R.string.ignore), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                counter = 0;
                no_of_correct_ans = 0;
                quiz = createQuiz(total_Ques);
                fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fm.beginTransaction().replace(R.id.fragment_container, quiz.get(0)).commit();
            }
        }).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_true:
                if(quiz.get(counter).ans){
                    no_of_correct_ans++;
                    counter++;
                    Toast.makeText(this, getResources().getString(R.string.correctAns), Toast.LENGTH_SHORT).show();
                }
                else{
                    counter++;
                    Toast.makeText(this, getResources().getString(R.string.wrongAns), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_false:
                if(!quiz.get(counter).ans){
                    no_of_correct_ans++;
                    counter++;
                    Toast.makeText(this, getResources().getString(R.string.correctAns), Toast.LENGTH_SHORT).show();
                }
                else{
                    counter++;
                    Toast.makeText(this, getResources().getString(R.string.wrongAns), Toast.LENGTH_SHORT).show();
                }
                break;

        }

        progressBar.setProgress(counter);

        if(counter < quiz.size()){
            fm.beginTransaction().replace(R.id.fragment_container, quiz.get(counter)).commit();
        }
        else{
            showALertDialogForQuizResult();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        switch(id){
            case R.id.menu_average:
                showALertDialogForTotalResult();
                break;
            case R.id.menu_num_ques:
                Ques_Num new_no_Of_ques = Ques_Num.newInstance(no_of_bankques);
                new_no_Of_ques.handler = this;
                new_no_Of_ques.show(fm, Ques_Num.tag);
                break;
            case R.id.menu_reset:
                file_Storage.delete_results(MainActivity.this);
                Toast.makeText(this, getResources().getString(R.string.reset_the_saved_result), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void Ok_btn_Click(int n) {
        if(n != -1){
            quiz = createQuiz(n);
            no_of_correct_ans = 0;
            total_Ques = n;
            counter = 0;
            fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.beginTransaction().replace(R.id.fragment_container, quiz.get(0)).commit();
        }
        else{
            infobuilder.setMessage("Invalid input! please enter a number less than or equal " + no_of_bankques).setCancelable(true).show();
        }
    }

    @Override
    public void Cancel_btn_Click() {
        return;
    }
}
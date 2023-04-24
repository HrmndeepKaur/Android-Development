package com.example.quiz_app;

import android.app.Activity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class File_Storage {
    static String fileName = "results.txt";
    FileOutputStream fos;
    FileInputStream fis;

    public void write_new_result_to_file(Activity context, int correct_ans, int total_ques){
        try{
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            fos.write((Integer.toString(correct_ans) + "-" + Integer.toString(total_ques)).getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete_results(Activity context){
        try{
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            fos.write(("0-0").getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Integer> read_result(Activity context){
        ArrayList<Integer> result = new ArrayList<>(2);
        StringBuilder st_buid = new StringBuilder();

        try{
            fis = context.openFileInput(fileName);
            InputStreamReader ipStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int read;
            while((read = ipStreamReader.read()) != -1){
                st_buid.append((char)read);
            }
            result = String_to_result_array(st_buid.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private ArrayList<Integer> String_to_result_array(String fileContent){
        ArrayList<Integer> result_array = new ArrayList<>(2);

        for(int i = 0; i < fileContent.toCharArray().length; i++){
            if(fileContent.toCharArray()[i] == '-'){
                int correct_ans = Integer.parseInt(fileContent.substring(0,i));
                int total_ques = Integer.parseInt(fileContent.substring(i + 1, fileContent.toCharArray().length));

                result_array.add(correct_ans);
                result_array.add(total_ques);
                break;
            }
        }
        return result_array;
    }
}


package com.example.recipeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {
    private final String recipeURL1 = "https://api.edamam.com/api/recipes/v2?type=public&q=";
    private final String recipeURL2 = "&app_id=43eedff3&app_key=e6e9d9e64b9b409604a9127e39c5c12e";

    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkHandler = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void dataListener(String jsonRecipeString);
        void imageListener(Bitmap image);
    }
    NetworkingListener listener;

    public void getRecipeData(String recipe_name) {
        searchForRecipes(recipe_name);
    }

    public void searchForRecipes(String recipe_name) {
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection;

                try{
                    StringBuilder jsonData = new StringBuilder();

                    String completeURL = recipeURL1 + recipe_name + recipeURL2;

                    URL urlOBJ = new URL(completeURL);

                    httpURLConnection = (HttpURLConnection)urlOBJ.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
                    httpURLConnection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
                    httpURLConnection.setRequestProperty("authority", "api.edamam.com");

                    InputStream in = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    BufferedReader bR = new BufferedReader(reader);

                    String textLine;
                    while((textLine = bR.readLine()) != null){
                        jsonData.append(textLine);
                    }
                    final String finalData = jsonData.toString();

                    networkHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.dataListener(finalData);
                        }
                    });
                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getImageData(String imageURL) {
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    URL urlOBJ = new URL(imageURL);

                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream) urlOBJ.getContent());
                    networkHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.imageListener(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

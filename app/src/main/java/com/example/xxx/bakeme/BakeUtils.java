package com.example.xxx.bakeme;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class BakeUtils {

    private BakeUtils() {

    }

    private static URL createURL(String string) {

        URL url = null;

        try {

            url = new URL(string);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        return url;
    }

    private static String getResponseFromHttp(URL url) throws IOException {

        String response = "";

        if (url == null) {

            return response;
        } else {

            InputStream inputStream = null;
            HttpsURLConnection httpURLConnection = null;

            try {

                httpURLConnection = (HttpsURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200) {

                    inputStream = httpURLConnection.getInputStream();
                    response = getDataFromJson(inputStream);


                } else {

                    Log.d("TAG", "Error with response code: " + httpURLConnection.getResponseCode());
                }
            } catch (IOException e) {

                e.printStackTrace();
            } finally {

                if (httpURLConnection != null) {

                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {

                    inputStream.close();
                }


            }

        }
        return response;
    }

    private static String getDataFromJson(InputStream inputStream) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {

                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

        }
        return stringBuilder.toString();
    }

    private static List<Recipe> extractBakeRecipesFromJson(String string) {

        List<Recipe> list = new ArrayList<>();
        List<Steps> stepsList = new ArrayList<>();
        List<Ingredients> ingredientsList = new ArrayList<>();

        Ingredients ingredients = new Ingredients(0, "", "");
        Steps steps = new Steps("", "", "");

        double quantity = 0;
        String measure = "";
        String ingredient = "";
        String shortDescription = "";
        String description = "";
        String videoUrl = "";

        try {

            JSONArray jsonArray = new JSONArray(string);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                int servings = jsonObject.getInt("servings");
                JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");

                for (int k = 0; k < ingredientsArray.length(); k++) {
                    JSONObject ingredientsObject = ingredientsArray.getJSONObject(k);
                    quantity = ingredientsObject.getDouble("quantity");
                    measure = ingredientsObject.getString("measure");
                    ingredient = ingredientsObject.getString("ingredient");

                    ingredients = new Ingredients(quantity, measure, ingredient);
                    ingredientsList.add(ingredients);

                }

                JSONArray stepsArray = jsonObject.getJSONArray("steps");

                for (int j = 0; j < stepsArray.length(); j++) {

                    JSONObject stepsObject = stepsArray.getJSONObject(j);
                    shortDescription = stepsObject.getString("shortDescription");
                    description = stepsObject.getString("description");
                    videoUrl = stepsObject.getString("videoURL");

                    steps = new Steps(shortDescription, description, videoUrl);
                    stepsList.add(steps);
                }

                Recipe recipe = new Recipe(name, servings, ingredientsList, stepsList);
                list.add(i, recipe);
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }

        return list;
    }

    public static List<Recipe> fetchBakeData(String string) {

        URL url = createURL(string);
        String response = null;

        try {

            response = getResponseFromHttp(url);
        } catch (IOException e) {

            e.printStackTrace();
        }

        List<Recipe> list = extractBakeRecipesFromJson(response);
        return list;

    }


}

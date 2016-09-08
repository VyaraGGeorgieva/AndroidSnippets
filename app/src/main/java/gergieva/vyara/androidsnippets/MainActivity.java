package gergieva.vyara.androidsnippets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Get Data From Text Resource File Contains Json Data.
        InputStream inputStream = getResources().openRawResource(R.raw.questions);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("Text Data", byteArrayOutputStream.toString());

        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(
                    byteArrayOutputStream.toString());

            JSONObject jObjectResult = jObject.getJSONObject("Questions");
            JSONArray jArray = jObjectResult.getJSONArray("Question");
            String quest_id = "";
            String quest_title = "";
            String quest_info = "";

            ArrayList<String[]> data = new ArrayList<String[]>();
            for (int i = 0; i < jArray.length(); i++) {
                quest_id = jArray.getJSONObject(i).getString("quest_id");
                quest_title = jArray.getJSONObject(i).getString("quest_title");
                quest_info= jArray.getJSONObject(i).getString("quest_info");

                Log.v("Qest id", quest_id);
                Log.v("Quest title", quest_title);
                Log.v("Quest info", quest_info);

                data.add(new String[]{quest_id, quest_title,quest_info});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

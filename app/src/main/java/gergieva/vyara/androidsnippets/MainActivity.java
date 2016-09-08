package gergieva.vyara.androidsnippets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView)findViewById(R.id.title);
        info = (TextView) findViewById(R.id.info);

        //Get Data From Text Resource File Contains Json Data.
        InputStream inputStream = getResources().openRawResource(R.raw.cats_array);
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

            JSONObject jObjectResult = jObject.getJSONObject("Categories");
            JSONArray jArray = jObjectResult.getJSONArray("Category");
            String cat_Id = "";
            String cat_name = "";

            ArrayList<String[]> data = new ArrayList<String[]>();
            for (int i = 0; i < jArray.length(); i++) {
                cat_Id = jArray.getJSONObject(i).getString("cat_id");
                cat_name = jArray.getJSONObject(i).getString("cat_name");
                Log.v("Cat ID", cat_Id);
                Log.v("Cat Name", cat_name);
                data.add(new String[]{cat_Id, cat_name});

                info.setText(cat_name);
                title.setText(cat_Id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

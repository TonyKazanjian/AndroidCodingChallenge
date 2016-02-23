package fishermanlabs.androidcodingchallenge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NameInputDialogFragment.NameInputListener {

    RecyclerView mRecyclerView;
    NameAdapter mNameAdapter;

    private NameAssets mNameAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNameAssets = new NameAssets(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.name_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mNameAdapter = new NameAdapter(createMockNames());
        mRecyclerView.setAdapter(mNameAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NameInputDialogFragment nameInputDialogFragment = new NameInputDialogFragment();
                nameInputDialogFragment.setListener(MainActivity.this);
                nameInputDialogFragment.show(getSupportFragmentManager(), NameInputDialogFragment.class.getSimpleName());
            }
        });
    }

    public List<Name> createMockNames() {
        List<Name> mockNames = new ArrayList<>();
        try {
            parseItems(mockNames, new JSONObject(loadJSONFromAsset()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mockNames;
    }

    public String loadJSONFromAsset() {
        String json = null;
        InputStream is = null;
        try {
            is = mNameAssets.getAssets().open("NameDummyData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public void parseItems(List<Name> names, JSONObject jsonBody)
            throws IOException, JSONException {

        JSONObject namesJsonObject = jsonBody.getJSONObject("namelist");
        JSONArray namesJsonArray = namesJsonObject.getJSONArray("nameArray");

        for (int i = 0; i < namesJsonArray.length(); i++) {
            JSONObject nameJsonObject = namesJsonArray.getJSONObject(i);

            Name name = new Name();
            name.setFirstName(nameJsonObject.getString("firstname"));
            name.setLastName(nameJsonObject.getString("lastname"));

            names.add(name);
        }
    }

    @Override
    public void onOkayClicked(Name name) {
        if(mNameAdapter != null) {
            mNameAdapter.addName(name);
        }
    }
}

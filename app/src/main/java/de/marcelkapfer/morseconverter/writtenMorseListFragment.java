package de.marcelkapfer.morseconverter;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class writtenMorseListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset;
    private static final String TAG = "RecyclerViewFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_written_morse_list, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.wm_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getDataset();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    private void getDataset(){
         ArrayList<String> dataset = new ArrayList<>();
        String datastring = "";
        AssetManager astmgr = getActivity().getAssets();
        try {
            InputStream data = astmgr.open("codes.txt");
            StringBuilder buf = new StringBuilder();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(data, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
            datastring =buf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int c = 0; datastring.length() > 1; c++){
            int pos = datastring.indexOf(";");
            dataset.add(datastring.substring(0, pos));
            datastring = datastring.substring(pos + 1);
            myDataset = dataset.toArray(new String[dataset.size()]);
        }
    }

}

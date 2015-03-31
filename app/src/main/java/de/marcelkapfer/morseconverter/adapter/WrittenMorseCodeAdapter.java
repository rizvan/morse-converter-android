package de.marcelkapfer.morseconverter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.marcelkapfer.morseconverter.R;

/**
 * Created by mmk on 3/30/15.
 */
public class WrittenMorseCodeAdapter extends RecyclerView.Adapter<WrittenMorseCodeAdapter.ViewHolder> {
    private String[] mLetter;
    private String[] mCode;
    private static String mType = "LETTER";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mLetterView;
        public TextView mCodeView;
        public ViewHolder(View v) {
            super(v);
            mLetterView = (TextView) v.findViewById(R.id.letterfield);
            mCodeView = (TextView) v.findViewById(R.id.codefield);
        }

    }

    public WrittenMorseCodeAdapter(String[] myLetter, String[] myCode) {
        mLetter = myLetter;
        mCode = myCode;
    }

    @Override
    public WrittenMorseCodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.codeview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mLetterView.setText(mLetter[position]);
        holder.mCodeView.setText(mCode[position]);

    }

    @Override
    public int getItemCount() {
        return mCode.length;
    }
}

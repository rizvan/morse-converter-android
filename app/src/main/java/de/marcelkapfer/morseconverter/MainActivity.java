package de.marcelkapfer.morseconverter;

/*
    This is a Android application for converting writtenMorse and normal morse code.
    Copyright (C) 2014-2015  Marcel Michael Kapfer

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Marcel Michael Kapfer
    marcelmichaelkapfer@yahoo.co.nz

 */

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import de.marcelkapfer.morseconverter.engine.DecodeNormalMorseManager;
import de.marcelkapfer.morseconverter.engine.DecodeWrittenMorseManager;
import de.marcelkapfer.morseconverter.engine.EncodeNormalMorseManager;
import de.marcelkapfer.morseconverter.engine.EncodeWrittenMorseManager;
import de.marcelkapfer.morseconverter.fragments.AboutFragment;
import de.marcelkapfer.morseconverter.fragments.MainFragment;
import de.marcelkapfer.morseconverter.fragments.MorseFragment;
import de.marcelkapfer.morseconverter.fragments.writtenMorseListFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.neokree.materialnavigationdrawer.util.MaterialActionBarDrawerToggle;


public class MainActivity extends MaterialNavigationDrawer implements BillingProcessor.IBillingHandler {

    //Declaring the Material Sections
    private MaterialSection writtenMorse, normalMorse, writtenMorseList, donate, about;
    private MaterialActionBarDrawerToggle mDrawerToggle;

    BillingProcessor bp;

    //The MaterialNavigationDrawer init() methode replaces the normal  onCreate() methode
    @Override
    public void init(Bundle savedInstanceState) {

        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkmhshG72hHv9OmduVGxio5jyhC9M4CRGp099vtYHmZGaVCq/hpzUhgu7z/H3ioPSc325W13o3qYGpY4GLwe7MAtnSfTIT2fBu6l3kv9lgyYG0qSnDxZVOikf4Bfj7LE/g1OEr/++MqcD2hg1EBMqIgVyB6qOXgXkrHBSj2pf2Rko1SXNmeZ/MiTFx1VRB0PPRf01hPWU1bxZUizh3hdgWiATuTJCCYR0vpfb4IlQDF5wGS4AGHgIz5Qhh5ZZ+XQDTHv7SDdodSdLc02a/Zy0/9bxTIh8yy/Lg1JbPdh5rvWK/HeEH/wAYmwc8xQoQL264wjTQqKUZ+7iisHwS9ZtowIDAQAB", this);

        // Restore purchases
        bp.loadOwnedPurchasesFromGoogle();

        Resources res = getResources();
        //Declaring the Material Sections
        writtenMorse = this.newSection("writtenMorse", new MainFragment());
        normalMorse = this.newSection(res.getString(R.string.normalMorse), new MorseFragment());
        writtenMorseList = this.newSection("writtenMorse Codes", new writtenMorseListFragment());
        about = this.newSection(res.getString(R.string.about), new AboutFragment());
        donate = this.newSection(res.getString(R.string.donate_title), new MaterialSectionListener() {
                    @Override
                    public void onClick(MaterialSection materialSection) {
                        bp.purchase(MainActivity.this, "donate");
                        donate.unSelect();
                    }
                }
        );
        //Adding the Sections
        this.addSection(writtenMorse);
        this.addSection(normalMorse);
        this.addDivisor();
        this.addSection(writtenMorseList);
        this.addBottomSection(donate);
        this.addBottomSection(about);
        //set drawer image
        this.setDrawerHeaderImage(this.getResources().getDrawable(R.drawable.feature_graphics));
        allowArrowAnimation(); //Drawer Arrow rotations
        this.disableLearningPattern(); //Doesn't open the drawer always when the app starts
        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);

        mDrawerToggle = new MaterialActionBarDrawerToggle(this, null, null, 0, 0){

            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                // This closes the soft keyboard, when the drawer is opened
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (getCurrentSection() == writtenMorse) {
                        EditText myEditText = (EditText) findViewById(R.id.editTextWrittenMorse);
                        imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
                    } else if (getCurrentSection() == normalMorse) {
                        EditText myEditText = (EditText) findViewById(R.id.editTextNormalMorse);
                        imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        };

        this.setDrawerListener(mDrawerToggle);

    }

    // IBillingHandler implementation

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBillingInitialized() {
        /*
         * Called then BillingProcessor was initialized and its ready to purchase
         */
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        /*
         * Called then requested PRODUCT ID was successfully purchased
         */
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        /*
         * Called then some error occured. See Constants class for more details
         */
    }

    @Override
    public void onPurchaseHistoryRestored() {
        /*
         * Called then purchase history was restored and the list of all owned PRODUCT ID's
         * was loaded from Google Play
         */
    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();

        super.onDestroy();
    }

    public void normalMorseEncode(View view){
        try{
            EditText input = (EditText) findViewById(R.id.editTextNormalMorse);
            TextView output = (TextView) findViewById(R.id.outputNormalMorse);
            CardView cardView = (CardView) findViewById(R.id.cardViewNormalMorseOutput);
            if(cardView.getVisibility() == View.INVISIBLE){
                cardView.setVisibility(View.VISIBLE);
            }
            output.setText(EncodeNormalMorseManager.getEncodedString(input.getText().toString()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void normalMorseDecode(View view){
        try {
            EditText input = (EditText) findViewById(R.id.editTextNormalMorse);
            TextView output = (TextView) findViewById(R.id.outputNormalMorse);
            CardView cardView = (CardView) findViewById(R.id.cardViewNormalMorseOutput);
            if(cardView.getVisibility() == View.INVISIBLE){
                cardView.setVisibility(View.VISIBLE);
            }
            output.setText(DecodeNormalMorseManager.getDecodedString(input.getText().toString()));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writtenMorseEncode(View view){
        try {
            EditText input = (EditText) findViewById(R.id.editTextWrittenMorse);
            TextView output = (TextView) findViewById(R.id.outputWrittenMorse);
            CardView cardView = (CardView) findViewById(R.id.cardViewWrittenMorseOutput);
            if(cardView.getVisibility() == View.INVISIBLE){
                cardView.setVisibility(View.VISIBLE);
            }
            output.setText(EncodeWrittenMorseManager.getEncodedString(input.getText().toString()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writtenMorseDecode(View view){
        try {
            EditText input = (EditText) findViewById(R.id.editTextWrittenMorse);
            TextView output = (TextView) findViewById(R.id.outputWrittenMorse);
            CardView cardView = (CardView) findViewById(R.id.cardViewWrittenMorseOutput);
            if(cardView.getVisibility() == View.INVISIBLE){
                cardView.setVisibility(View.VISIBLE);
            }
            output.setText(DecodeWrittenMorseManager.getDecodedString(input.getText().toString()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    //called when clicking on the version entry in the about fragment
    //opens the app page (de.marcelkapfer.morseconverter)
    public void versionEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=de.marcelkapfer.morseconverter"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //called when clicking on the developer entry in the about fragment
    //opens the gplus profile of +MarcelMichaelKapfer
    public void developerEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+MarcelMichaelKapfer/posts"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //called when clicking on the website entry in the about fragment
    //opens marcel-kapfer.de/projects/morse/
    public void websiteEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://marcel-kapfer.de/projects/morse/"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //called when clicking on the contact entry in the about fragment
    //opens mail app with mail to marcelmichaelkapfer@yahoo.co.nz
    public void contactEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:marcelmichaelkapfer@yahoo.co.nz"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //called when clicking on the license entry in the about fragment
    //opens the license web page
    public void licenseEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gnu.org/copyleft/gpl.html"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //called when clicking on the developer entry in the about fragment
    //opens mail app with mail to marcelmichaelkapfer@yahoo.co.nz
    public void bugEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:marcelmichaelkapfer@yahoo.co.nz?subject=Bug Report"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //called when clicking on the developer entry in the about fragment
    //opens the howto webpage
    public void howtoEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://marcel-kapfer.de/projects/morse/index.html#howto_android"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //called when clicking on the developer entry in the about fragment
    //opens mail app with mail to marcelmichaelkapfer@yahoo.co.nz
    public void missingCodeEntry(View view){
        try{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:marcelmichaelkapfer@yahoo.co.nz?subject=Missing Code"));
            startActivity(browserIntent);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void shareWrittenMorse(View view){
        TextView message = (TextView) findViewById(R.id.outputWrittenMorse);
        share(message.getText().toString());
    }

    public void shareNormalMorse(View view){
        TextView message = (TextView) findViewById(R.id.outputNormalMorse);
        share(message.getText().toString());
    }

    public void share(String string){
		Intent intent = getIntent();
	    String message = intent.getStringExtra(string);
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, message);
		sendIntent.setType("text/plain");
		startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
	}

    public void copyWrittenMorse(View view){
        TextView message = (TextView) findViewById(R.id.outputWrittenMorse);
        copy(message.getText().toString());
    }

    public void copyNormalMorse(View view){
        TextView message = (TextView) findViewById(R.id.outputNormalMorse);
        copy(message.getText().toString());
    }

    public void copy(String string){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Message", string);
        clipboard.setPrimaryClip(clip);
    }

}

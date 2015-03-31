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
import android.widget.EditText;
import android.widget.TextView;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;


public class MainActivity extends MaterialNavigationDrawer {

    //Declaring the Material Sections
    private MaterialSection writtenMorse, normalMorse, writtenMorseList, about;

    //The MaterialNavigationDrawer init() methode replaces the normal  onCreate() methode
    @Override
    public void init(Bundle savedInstanceState) {
        Resources res = getResources();
        //Declaring the Material Sections
        writtenMorse = this.newSection("writtenMorse", new MainFragment());
        normalMorse = this.newSection(res.getString(R.string.normalMorse), new MorseFragment());
        writtenMorseList = this.newSection("writtenMorse Codes", new writtenMorseListFragment()); //TODO rename
        about = this.newSection(res.getString(R.string.about), new AboutFragment());
        //Adding the Sections
        this.addSection(writtenMorse);
        this.addSection(normalMorse);
        this.addDivisor();
        this.addSection(writtenMorseList);
        this.addBottomSection(about);
        //set drawer image
        this.setDrawerHeaderImage(this.getResources().getDrawable(R.drawable.feature_graphics));
        allowArrowAnimation(); //Drawer Arrow rotations
        this.disableLearningPattern(); //Doesn't open the drawer always when the app starts
        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);
    }

    public void normalMorseEncode(View view){
        try{
            EditText input = (EditText) findViewById(R.id.editTextNormalMorse);
            TextView output = (TextView) findViewById(R.id.outputNormalMorse);
            CardView cardView = (CardView) findViewById(R.id.cardViewNormalMorseOutput);
            if(cardView.getVisibility() == View.INVISIBLE){
                cardView.setVisibility(View.VISIBLE);
            }
            EncodeNormalMorseManager message = new EncodeNormalMorseManager(input.getText().toString());
            output.setText(message.getEncodedString());
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
            DecodeNormalMorseManager message = new DecodeNormalMorseManager(input.getText().toString());
            output.setText(message.getDecodedString());
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
            EncodeWrittenMorseManager message = new EncodeWrittenMorseManager(input.getText().toString());
            output.setText(message.getEncodedString());
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
            DecodeWrittenMorseManager message = new DecodeWrittenMorseManager(input.getText().toString());
            output.setText(message.getDecodedString());
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

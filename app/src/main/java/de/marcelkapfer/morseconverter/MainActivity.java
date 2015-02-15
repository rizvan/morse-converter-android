package de.marcelkapfer.morseconverter;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;


public class MainActivity extends MaterialNavigationDrawer {

    //Declaring the Material Sections
    private MaterialSection writtenMorse, normalMorse, about;

    //The MaterialNavigationDrawer init() methode replaces the normal  onCreate() methode
    @Override
    public void init(Bundle savedInstanceState) {
        Resources res = getResources();
        //Declaring the Material Sections
        writtenMorse = this.newSection("writtenMorse", new MainFragment());
        normalMorse = this.newSection(res.getString(R.string.normalMorse), new MorseFragment());
        about = this.newSection(res.getString(R.string.about), new AboutFragment());
        //Adding the Sections
        this.addSection(writtenMorse);
        this.addSection(normalMorse);
        this.addDivisor();
        this.addSection(about);
        //set drawer image
        //TODO create 16:9 image
        this.setDrawerHeaderImage(this.getResources().getDrawable(R.drawable.feature_graphics));
        allowArrowAnimation(); //Drawer Arrow rotations
        this.disableLearningPattern(); //Doesn't open the drawer always when the app starts
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
}

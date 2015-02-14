package de.marcelkapfer.morseconverter;

import android.content.res.Resources;
import android.os.Bundle;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;


public class MainActivity extends MaterialNavigationDrawer {

    private MaterialSection writtenMorse, normalMorse, about;

    @Override
    public void init(Bundle savedInstanceState) {
        Resources res = getResources();
        writtenMorse = this.newSection("writtenMorse", new MainFragment());
        normalMorse = this.newSection(res.getString(R.string.normalMorse), new MorseFragment());
        about = this.newSection(res.getString(R.string.about), new AboutFragment());
        this.addSection(writtenMorse);
        this.addSection(normalMorse);
        this.addDivisor();
        this.addSection(about);
        this.setDrawerHeaderImage(this.getResources().getDrawable(R.drawable.feature_graphics));
        allowArrowAnimation();
        this.disableLearningPattern();
    }
}

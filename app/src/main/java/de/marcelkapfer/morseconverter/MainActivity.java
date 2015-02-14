package de.marcelkapfer.morseconverter;

import android.os.Bundle;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;


public class MainActivity extends MaterialNavigationDrawer {

    private MaterialSection writtenMorse, normalMorse, about;

    @Override
    public void init(Bundle savedInstanceState) {
        writtenMorse = this.newSection("writtenMorse", new MainFragment());
        normalMorse = this.newSection("Normal Morse", new MorseFragment());
        about = this.newSection("About", new AboutFragment());
        this.addSection(writtenMorse);
        this.addSection(normalMorse);
        this.addBottomSection(about);
        this.setDrawerHeaderImage(this.getResources().getDrawable(R.drawable.feature_graphics));
        allowArrowAnimation();
        this.disableLearningPattern();
    }
}

package de.marcelkapfer.morseconverter;

import android.os.Bundle;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;


public class MainActivity extends MaterialNavigationDrawer {

    private MaterialSection writtenMorse;

    @Override
    public void init(Bundle savedInstanceState) {
        writtenMorse = this.newSection("writtenMorse", new MainFragment());
        this.addSection(writtenMorse);
        this.setDrawerHeaderImage(this.getResources().getDrawable(R.drawable.feature_graphics));
        allowArrowAnimation();
    }
}

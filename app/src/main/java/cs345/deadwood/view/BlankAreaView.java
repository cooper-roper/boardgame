package cs345.deadwood.view;

import cs345.deadwood.model.BlankArea;
import cs345.deadwood.model.IArea;
import cs345.deadwood.model.Player;


import javax.swing.*;


public class BlankAreaView extends JLabel implements IModelObserver {

    private BlankArea blankArea;

    public BlankAreaView(BlankArea blankArea) {
        this.blankArea = blankArea;
        setLocation(blankArea.getX(), blankArea.getY());
        setSize(blankArea.getW(), blankArea.getH());
        blankArea.registerObserver(this);
    }

    @Override
    public void updateResult() {
        if (blankArea.getPlayer() != null) {
            //blankArea.getPlayer().getDice()
            setIcon(new ImageIcon(getClass().getClassLoader().getResource(blankArea.getPlayer().getDice()).getPath().replace("%20", " ")));
        }
        else setIcon(null);
    }


}

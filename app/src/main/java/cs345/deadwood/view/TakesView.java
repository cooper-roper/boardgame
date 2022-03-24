package cs345.deadwood.view;

import cs345.deadwood.model.BlankArea;
import cs345.deadwood.model.IArea;
import cs345.deadwood.model.Player;
import cs345.deadwood.model.Take;


import javax.swing.*;


public class TakesView extends JLabel implements IModelObserver {

    private Take take;

    public TakesView(Take take) {
        this.take = take;
        setLocation(take.getX(), take.getY());
        setSize(take.getW(), take.getH());
        setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/shot.png").getPath().replace("%20", " ")));
        take.registerObserver(this);
    }


    @Override
    public void updateResult() {
        if (take.getActed()) {
            setIcon(null);
        }
        else setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/shot.png").getPath().replace("%20", " ")));
    }
}
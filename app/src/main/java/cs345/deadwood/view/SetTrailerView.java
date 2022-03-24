package cs345.deadwood.view;

import cs345.deadwood.model.BlankArea;
import cs345.deadwood.model.GameLog;
import cs345.deadwood.model.IArea;
import cs345.deadwood.model.ISet;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SetTrailerView implements MouseListener {

    private final JFrame board;
    private ISet set;
    private JPanel cardPanel;
    private GameLog Log;

    public SetTrailerView(JFrame parentContainer, ISet set) {
        board = parentContainer;
        this.set = set;
        Log = GameLog.getInstance();
    }

    public void drawSet() {

        /*
         * Create a JPanel to render things on the card.
         */
        cardPanel = new JPanel();
        cardPanel.setLocation(set.getArea().getX(), set.getArea().getY()); // x,y values from board.xml, set name "Train Station", area element
        cardPanel.setSize(set.getArea().getW(), set.getArea().getH()); // height and width from board.xml, set name "Train Station", area element
        cardPanel.setLayout(null); // set layout to null so we can render roles on the card (x-y values in roles in cards.xml). The x-y values for roles in cards.xml are relative to the card.
        cardPanel.setOpaque(false);
        board.add(cardPanel);

        for (IArea blank : set.getBlankAreas()){
            BlankAreaView blankArea = new BlankAreaView((BlankArea) blank);
            board.add(blankArea);
        }

        cardPanel.addMouseListener(this); // uncomment this to list to clicks on this set
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Log.log("Set " + set.getName() + " clicked.\n");
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.BlankArea;
import cs345.deadwood.model.GameLog;
import cs345.deadwood.model.IRole;
import cs345.deadwood.model.Role;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoleView extends JLabel implements MouseListener, IModelObserver {
    private final IRole model;
    private final GameLog Log;


//    public RoleView(IRole model, GameController controller) {
    public RoleView(IRole model) {
        this.model = model;
        Log = GameLog.getInstance();
        setLocation(model.getArea().getX(), model.getArea().getY());
        setSize(model.getArea().getW(), model.getArea().getH());
        model.registerObserver(this);

        addMouseListener(this);
    }

    @Override
    public void updateResult() {
        if (model.getPlayer() != null) {
            //System.out.println("Role: " + model.getName());
            //System.out.println("X: " + model.getArea().getX() + "  Y: " + model.getArea().getX());
            //System.out.println("Player on role: " + model.getPlayer().getPlayerNumber());
            //if (model.getIsCardRole()) {
             //   cardPanel.setIcon(new ImageIcon(getClass().getClassLoader().getResource(model.getPlayer().getDice()).getPath().replace("%20", " ")));
           // } else {
                setIcon(new ImageIcon(getClass().getClassLoader().getResource(model.getPlayer().getDice()).getPath().replace("%20", " ")));
            //}
            //System.out.println("Dice icon added");
        }
        else setIcon(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Log.log("Role " + model.getName() + " clicked.\n");
        if (model.getIsCardRole()) {
            System.out.println("Role: " + model.getName() + " is a card role");
        }
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
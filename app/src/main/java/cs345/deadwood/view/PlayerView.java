package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.GameEngine;
import cs345.deadwood.model.Player;
import cs345.deadwood.model.IModel;
import cs345.deadwood.view.IModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerView extends JPanel implements IModelObserver{
    private JLabel playerDice;
    private JLabel money;
    private Player player;
    private final int VERTICAL_PADDING = 5;
    private final int HORIZONTAL_PADDING = 5;


    public PlayerView(Player player) {
        this.player = player;
        setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 50));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        playerDice= new JLabel(new ImageIcon(getClass().getClassLoader().getResource(player.getDice()).getPath().replace("%20", " ")));
        this.add(playerDice);
        this.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        money = new JLabel("P" + player.getPlayerNumber() + " " + player.getPlayerLocationName() + ": $" + player.getMoney() + " C" + player.getCredits() + " Pc" + player.getPChips() + "; S=" + player.getScore()); // 2 dollars and 3 credits.
        this.add(money);
        this.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding
        player.registerObserver(this);

        if (player.getIsActive()) {
            this.setBackground(Color.YELLOW);
        } else {
            this.setBackground(null);
        }
    }

    @Override
    public void updateResult() {
        playerDice.setIcon(new ImageIcon(getClass().getClassLoader().getResource(player.getDice()).getPath().replace("%20", " ")));
        player.calculateScore();
        money.setText("P" + player.getPlayerNumber() + " " + player.getPlayerLocationName() + ": $" + player.getMoney() + " C" + player.getCredits() + " Pc" + player.getPChips() + "; S=" + player.getScore()); // 2 dollars and 3 credits.
        if (player.getIsActive()) {
            this.setBackground(Color.YELLOW);
        } else {
            this.setBackground(null);
        }
    }

}

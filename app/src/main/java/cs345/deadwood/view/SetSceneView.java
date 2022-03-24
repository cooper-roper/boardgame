package cs345.deadwood.view;

import cs345.deadwood.model.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetSceneView implements MouseListener, IModelObserver{

    private final JFrame board;
    private ISetScene set;
    private JPanel cardPanel;
    private JLabel cardLabel;
    private GameLog Log;
    private GameEngine model;
    private List<RoleView> cardRoles = new ArrayList<>();

    public SetSceneView(JFrame parentContainer, ISetScene set, GameEngine model) {
        board = parentContainer;
        this.set = set;
        this.model = model;
        Log = GameLog.getInstance();
        set.registerObserver(this);
    }

    public void drawSet() {

        /*
         * Create a JPanel to render things on the card.
         */
        cardPanel = new JPanel();
        cardPanel.setLocation(set.getArea().getX(), set.getArea().getY()); // x,y values from board.xml, set name "Train Station", area element
        cardPanel.setSize(set.getArea().getW(), set.getArea().getH()); // height and width from board.xml, set name "Train Station", area element
        //System.out.println("Set name: " + set.getName() + "\tX: " + set.getArea().getX() + "\tY: " + set.getArea().getY() + "\tW: " + set.getArea().getW() + "\tH: " + set.getArea().getH());
        cardPanel.setLayout(null); // set layout to null so we can render roles on the card (x-y values in roles in cards.xml). The x-y values for roles in cards.xml are relative to the card.
        cardPanel.setOpaque(false);
        board.add(cardPanel);


        cardPanel.addMouseListener(this); // uncomment this to list to clicks on this set


        cardLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/cardback.png").getPath().replace("%20", " ")));
        cardLabel.setLocation(0, 0); // x,y values from board.xml, set name "Train Station", area element
        cardLabel.setSize(set.getArea().getW(), set.getArea().getH()); // height and width from board.xml, set name "Train Station", area element
        cardPanel.add(cardLabel);


        //draw cards here


        // sample code showing how to place player dice on a role
        // Role 1 is Crusty Prospector
       for (IArea blank : set.getBlankAreas()){
           BlankAreaView blankArea = new BlankAreaView((BlankArea) blank);
           board.add(blankArea);
       }


        // sample code showing how to place the shot icon on a take
        for (IArea take : set.getTakes()) {
            TakesView setTake = new TakesView((Take) take);
            board.add(setTake);
        }



    }

    @Override
    public void updateResult() {
        if (set.getNextDay()) {
            cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/cardback.png").getPath().replace("%20", " ")));
            cardPanel.add(cardLabel);
            set.setNextDay(false);
        } else if (!set.getHasWrapped()) {
            if (set.getSceneCard() == null && model.getCards().size() != 0) {
                for (RoleView roleView : cardRoles) {
                    cardPanel.remove(roleView);
                }

                if (model.getShuffle() == "Random") {
                    Random random = new Random();
                    int num = random.nextInt(model.getCards().size());
                    set.setSceneCard(model.getCards().get(num));
                    for (IRole role : set.getSceneCard().getRoles()) {
                        role.setIsCardRole(true);
                        RoleView rView = new RoleView(role);
                        cardPanel.add(rView);
                        cardRoles.add(rView);
                    }

                    cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + model.getCards().get(num).getImageName()).getPath().replace("%20", " ")));
                    cardPanel.add(cardLabel);

                    model.getCards().remove(num);


                } else if (model.getShuffle() == "By-Budget") {
                    System.out.println("HERE");
                    boolean run = true;
                    if (run) {
                        for (ICard card : model.getCards()) {
                            if (card.getBudget() == 2) {
                                set.setSceneCard(card);
                                for (IRole role : set.getSceneCard().getRoles()) {
                                    role.setIsCardRole(true);
                                    RoleView rView = new RoleView(role);
                                    cardPanel.add(rView);
                                    cardRoles.add(rView);
                                }

                                cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + card.getImageName()).getPath().replace("%20", " ")));
                                cardPanel.add(cardLabel);

                                model.getCards().remove(card);
                                run = false;
                                break;
                            }
                        }
                    }
                    if (run) {
                        for (ICard card : model.getCards()) {
                            if (card.getBudget() == 3) {
                                set.setSceneCard(card);
                                for (IRole role : set.getSceneCard().getRoles()) {
                                    role.setIsCardRole(true);
                                    RoleView rView = new RoleView(role);
                                    cardPanel.add(rView);
                                    cardRoles.add(rView);
                                }

                                cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + card.getImageName()).getPath().replace("%20", " ")));
                                cardPanel.add(cardLabel);

                                model.getCards().remove(card);
                                run = false;
                                break;
                            }
                        }
                    }
                    if (run) {
                        for (ICard card : model.getCards()) {
                            if (card.getBudget() == 4) {
                                set.setSceneCard(card);
                                for (IRole role : set.getSceneCard().getRoles()) {
                                    role.setIsCardRole(true);
                                    RoleView rView = new RoleView(role);
                                    cardPanel.add(rView);
                                    cardRoles.add(rView);
                                }

                                cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + card.getImageName()).getPath().replace("%20", " ")));
                                cardPanel.add(cardLabel);

                                model.getCards().remove(card);
                                run = false;
                                break;
                            }
                        }
                    }
                    if (run) {
                        for (ICard card : model.getCards()) {
                            if (card.getBudget() == 5) {
                                set.setSceneCard(card);
                                for (IRole role : set.getSceneCard().getRoles()) {
                                    role.setIsCardRole(true);
                                    RoleView rView = new RoleView(role);
                                    cardPanel.add(rView);
                                    cardRoles.add(rView);
                                }

                                cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + card.getImageName()).getPath().replace("%20", " ")));
                                cardPanel.add(cardLabel);

                                model.getCards().remove(card);
                                run = false;
                                break;
                            }
                        }
                    }
                    if (run) {
                        for (ICard card : model.getCards()) {
                            if (card.getBudget() == 6) {
                                set.setSceneCard(card);
                                for (IRole role : set.getSceneCard().getRoles()) {
                                    role.setIsCardRole(true);
                                    RoleView rView = new RoleView(role);
                                    cardPanel.add(rView);
                                    cardRoles.add(rView);
                                }

                                cardLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/" + card.getImageName()).getPath().replace("%20", " ")));
                                cardPanel.add(cardLabel);

                                model.getCards().remove(card);
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            for (RoleView roleView : cardRoles) {
                cardPanel.remove(roleView);
            }
            cardLabel.setIcon(null);
            cardPanel.add(cardLabel);
        }
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
        if (model.isEnabled()) {
            board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            try {
                board.setCursor(Cursor.getSystemCustomCursor("Invalid.32x32"));
            } catch (AWTException awtException) {
                awtException.printStackTrace();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        board.setCursor(Cursor.getDefaultCursor());
    }
}

package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.*;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;


public class BoardView implements MouseListener, IModelObserver, ActionListener {

    private final GameController controller;
    private final GameEngine model;
    private GameLog GLog;
    private ArrayList<String> log = new ArrayList<>();
    private JFrame frame;
    private final int VERTICAL_PADDING = 5;
    private final int HORIZONTAL_PADDING = 5;
    private JTextArea gameLog;
    private JScrollPane gameLogScrollPane;
    private boolean move = false;

    public BoardView(GameEngine model, GameController controller) {
        this.model = model;
        this.controller = controller;
        this.GLog = GameLog.getInstance();
        GLog.registerObserver(this);
    }


    public void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 930));
        // board img is 1200 x 900. The control panel is 300 x 900, so we want the frame to be 1500 x 900
        // The top bar on the frame is about 30 pixels in height. To account for that, we increase frame height by 30, so 930.

        // Set layout to null, so we can place widgets based on x-y coordinates.
        frame.setLayout(null);

        for (ISet set: model.getSets()) {
            if (set instanceof ISetScene) {
                SetSceneView setView = new SetSceneView(frame, (ISetScene) set, model);
                setView.drawSet();
                ISetScene set2 = (ISetScene) set;
                if (set2.getRoles() != null) {
                    for (IRole role : set2.getRoles()) {
                        RoleView rView = new RoleView(role);
                        frame.add(rView);
                    }
                }
            } else if ("Trailer".equals(set.getName())) {
                SetTrailerView setView = new SetTrailerView(frame, set);
                setView.drawSet();
            } else if ("Office".equals(set.getName())) {
                SetCastingOfficeView setView = new SetCastingOfficeView(frame, set);
                setView.drawSet();
            } else {
                throw new RuntimeException("Found unexpected set name");
            }
        }


        URL boardImg = getClass().getClassLoader().getResource("img/board.png");
        JLabel board = new JLabel(new ImageIcon(boardImg.getPath().replace("%20", " ")));
        board.setLocation(0, 0);
        board.setSize(1200, 900);
        frame.add(board);

        JPanel controlPanel = createControlPanel();
        controlPanel.setLocation(1200, 0);
        controlPanel.setSize(300, 900);
        frame.add(controlPanel);

        frame.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);

        // init players to trailer
        for (Player player: model.getPlayers()) {
            SetTrailer setTrailer = SetTrailer.getInstance();
            setTrailer.addPlayer(player);
        }
        GLog.log("The " + model.getShuffle() + " deck shuffle strategy will be used.\n");

    }

    private JPanel createControlPanel() {
        //System.out.println("allPlayers size: " + model.getPlayers().size());
        //System.out.println("BoardVIew list of players: " + model.numberOfPlayers);
        //Player newPlayer = new Player("Player1", "Trailer?", 100, 1, 10);
        //System.out.println("Player money: " + model.getPlayers().get(1).getPlayerNumber());




        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(300, 900));
        // Set height same as the board image. board image dimensions are 1200 x 900

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add padding around edges

        JLabel team = new JLabel("Team Name");
        team.setFont(new Font("TimesRoman", Font.BOLD, 20));
        controlPanel.add(team);
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        JLabel playerInfoLabel = new JLabel("Players");
        playerInfoLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
        controlPanel.add(playerInfoLabel);
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        // Show players
        for (Player player : model.getPlayers()) {
            PlayerView thisPlayerView = new PlayerView(player);
            controlPanel.add(thisPlayerView);
            controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
        }


        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(getMovePanel());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(miscInteraction());


        return controlPanel;
    }

    /*
    private JPanel showPlayerInfo(int i, String area, int cash, int credit, String dice) {
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        panel.add(new JLabel("Player " + i + ": "));
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel playerDice= new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/" + dice).getPath().replace("%20", " ")));
        panel.add(playerDice);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel playerLocation = new JLabel(area);
        panel.add(playerLocation);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel money = new JLabel("$" + cash + " C" + credit); // 2 dollars and 3 credits.
        panel.add(money);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        return panel;
    }

    */

    private JPanel getMovePanel() {
        JPanel movePanel = new JPanel();
        movePanel.setLayout(new GridLayout(0, 2));

        String[] moves = {"Move", "Take Role", "Act", "Rehearse", "Upgrade", "End Turn"};

        for (String move : moves) {
            JButton button = new JButton(move);
            movePanel.add(button);
            button.addActionListener(this);
        }

        return movePanel;
    }


    private JPanel miscInteraction() {
        // free space to use for comments or any game related stuff. E.g., show rolling die or show game log.

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 250));

        JLabel panelTitle = new JLabel("Game Log");
        panelTitle.setFont(new Font("TimesRoman", Font.BOLD, 18));
        panel.add(panelTitle);

        gameLog = new JTextArea("Game setup complete\n");
        gameLog.setLineWrap(true);
        gameLogScrollPane = new JScrollPane(gameLog);
        gameLogScrollPane.setPreferredSize(panel.getPreferredSize());
        gameLogScrollPane.setAutoscrolls(true);
        panel.add(gameLogScrollPane);


        return panel;
    }




    @Override
    public void updateResult(){
        this.log = GLog.getLog();
        gameLog.append(log.get(log.size()-1));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("button " +  e.getActionCommand() +" clicked");
        if (e.getActionCommand().equals("End Turn")) {
            GLog.setAction("end turn");
        } else if (e.getActionCommand().equals("Upgrade")) {
            GLog.setAction("upgrade");
        } else if (e.getActionCommand().equals("Move")) {
            GLog.setAction("move");
        } else if (e.getActionCommand().equals("Take Role")) {
            GLog.setAction("role");
        } else if (e.getActionCommand().equals("Rehearse")) {
            GLog.setAction("rehearse");
        } else if (e.getActionCommand().equals("Act")) {
            GLog.setAction("act");
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // The top bar of the frame is about 30 pixels in height. So to get the x,y values on the board, subtract 30 from the y value.
        //System.out.println("Mouse clicked at X = " + e.getX() + ", Y = " + (e.getY() - 30));
        gameLog.append("Mouse clicked at " + e.getX() + " \n");
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
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

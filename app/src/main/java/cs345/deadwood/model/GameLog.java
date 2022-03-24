package cs345.deadwood.model;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.view.IModelObserver;

import java.util.ArrayList;
import java.util.List;


public class GameLog implements IModel {

    private static GameLog instance;
    private ArrayList<String> log = new ArrayList<>();
    private List<IModelObserver> observers = new ArrayList<>();
    private String actionListener = "";
    private GameController controller;

    private GameLog() {}


    public static GameLog getInstance() {
        if (instance == null)
            instance = new GameLog();
        return instance;
    }

    public void GameLog(GameController controller) {this.controller = controller; }

    public void setAction(String string){
        this.actionListener = string;
        if(actionListener == "rehearse"){
            controller.clickedRehearsed();
            this.actionListener = "";
        }
        if(actionListener == "end turn"){
            controller.clickedEnd();
            this.actionListener = "";
        }
        if(actionListener == "act"){
            controller.clickedAct();
            this.actionListener = "";
        }
        if(actionListener == "upgrade"){
            controller.clickedUpgrade();
            this.actionListener = "";
        }
    }

    public void log(String message){
        System.out.println(message);
        log.add(message);
        notifyObservers();
        if(actionListener == "move"){
            controller.clickedMove(getLog().get(log.size()-1));
            this.actionListener = "";
        }
        if(actionListener == "role"){
            controller.clickedRole(getLog().get(log.size()-1));
            this.actionListener = "";
        }
    }

    public ArrayList<String> getLog(){ return log; }

    @Override
    public void registerObserver(IModelObserver ob) { observers.add(ob); }

    @Override
    public void removeObserver(IModelObserver ob) {
        observers.remove(ob);
    }

    @Override
    public void notifyObservers() {
        for (IModelObserver ob : observers) {
            ob.updateResult();
        }
    }

}

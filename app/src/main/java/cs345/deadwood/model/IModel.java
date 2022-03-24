package cs345.deadwood.model;


import cs345.deadwood.view.IModelObserver;

import java.util.ArrayList;
import java.util.List;

public interface IModel {
    void registerObserver(IModelObserver ob);


    void removeObserver(IModelObserver ob);


    void notifyObservers();

}

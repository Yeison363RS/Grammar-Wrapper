package controllers;

import models.ManagerTree;
import models.Grammar;


public class Controller {

    private ManagerTree manager;

    public void init(){
        this.manager = new ManagerTree();
    }
    public String startDerivationForRight(boolean right,Grammar grammar,String wordUser){
        return manager.startSynthesis(right,grammar,wordUser);
    }
    
    public String startDerivationForBothExtremes(Grammar grammar,String wordUser){
        return manager.startProcessingGrammar(grammar,wordUser);
    }
}

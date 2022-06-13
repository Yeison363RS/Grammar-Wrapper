package models;

import java.util.ArrayList;

public class Grammar {
    private ArrayList<RuleProduction> rulesProduction;
    private ArrayList<String> terminals;
    private ArrayList<Character> variables;
    private char initialAxiom;

    public Grammar(ArrayList<RuleProduction> rulesProduction, ArrayList<String> terminals,
            ArrayList<Character> variables, char initialAxiom) {
        this.initialAxiom = initialAxiom;
        this.rulesProduction = rulesProduction;
        this.variables = variables;
        this.terminals = terminals;
    }

    public ArrayList<RuleProduction> getRuleProductions() {
        return this.rulesProduction;
    }

    public ArrayList<String> getTerminals() {
        return this.terminals;
    }

    public ArrayList<Character> getVariables() {
        return this.variables;
    }

    public boolean isAVariable(char simbol) {
        return variables.contains((Character) simbol);
    }

    public char getInitialAxiom() {
        return initialAxiom;
    }
}
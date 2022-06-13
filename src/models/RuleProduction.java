package models;

public class RuleProduction {
    private String production;
    private char variable;

    public RuleProduction(char variable, String production) {
        this.production = production;
        this.variable = variable;
    }

    public String getProduction() {
        return production;
    }

    public char getVariable() {
        return variable;
    }
}
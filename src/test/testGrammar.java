package test;

import java.util.ArrayList;

import controllers.Controller;
import models.Grammar;
import models.RuleProduction;

public class testGrammar {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.init();
        ArrayList<RuleProduction> rules = new ArrayList<>();
        rules.add(new RuleProduction('S', "BA"));
        rules.add(new RuleProduction('B', "b"));
        rules.add(new RuleProduction('B', "AB"));
        rules.add(new RuleProduction('A', "a"));
        rules.add(new RuleProduction('A', "BA"));
        rules.add(new RuleProduction('A', "λ"));
        ArrayList<String> terminals = new ArrayList<>();
        terminals.add("a");
        terminals.add("b");
        ArrayList<Character> variables = new ArrayList<>();
        variables.add('S');
        variables.add('A');
        variables.add('B');
        Grammar grammar = new Grammar(rules, terminals, variables, 'S');
        System.out.println(controller.startDerivationForRight(true, grammar, "bababba"));
    }
}

package models;

import java.util.ArrayList;

import structures.Node;

public class ManagerTree {
    private Node head;
    private Grammar grammar;

    public Grammar getGrammar() {
        return grammar;
    }

    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public String startSynthesis(boolean right, Grammar grammar, String wordUser) {
        this.head = new Node(wordUser);
        ArrayList<String> history = new ArrayList<String>();
        if (right) {
            return head.derivationRigth(grammar, String.valueOf(grammar.getInitialAxiom()), history);
        }
        return head.derivationLeft(grammar, String.valueOf(grammar.getInitialAxiom()), history);
    }

    public String startProcessingGrammar(Grammar grammar, String wordUser) {
        String derivationnRigth = startSynthesis(true, grammar, wordUser);
        if (derivationnRigth == null) {
            return startSynthesis(false, grammar, wordUser);
        }
        return derivationnRigth;
    }

}

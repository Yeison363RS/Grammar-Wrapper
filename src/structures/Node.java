package structures;

import java.util.ArrayList;
import models.Grammar;
import models.RuleProduction;

public class Node {
    private String userWord;
    private ArrayList<Node> chilList;
    private char actualchar;

    public Node(String userWord) {
        this.userWord = userWord;
        this.chilList = new ArrayList<Node>();
    }

    public boolean isPossibleToContinueDriftingRight(Grammar grammar, String word) {
        if (word.length() > userWord.length()+2) {
            return false;
        }
        for (int i = word.length() - 1, j = userWord.length() - 1; i >= 0; i--, j--) {
            if (i >= 0 && j >= 0) {
                if(word.charAt(i)!= userWord.charAt(j) && grammar.isAVariable(word.charAt(i))){
                    this.actualchar = word.charAt(i);
                    return true;    
                }else if(word.charAt(i)!= userWord.charAt(j) && !grammar.isAVariable(word.charAt(i))){
                    return false;
                }
            }else{
                if (grammar.isAVariable(word.charAt(i))) {
                    this.actualchar = word.charAt(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPossibleToContinueDriftingLeft(Grammar grammar, String word) {
        if (word.length() > userWord.length()+2) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (i< userWord.length()) {
                if(word.charAt(i)!= userWord.charAt(i) && grammar.isAVariable(word.charAt(i))){
                    this.actualchar = word.charAt(i);
                    return true;    
                }else if(word.charAt(i)!= userWord.charAt(i) && !grammar.isAVariable(word.charAt(i))){
                    return false;
                }
            }else{
                if (grammar.isAVariable(word.charAt(i))) {
                    this.actualchar = word.charAt(i);
                    return true;
                }
            }
        }
        return false;
    }
    public String derivationLeft(Grammar grammar, String word,ArrayList<String> history) {
        if (userWord.equals(word)) {
            return word;
        }
        if(history.contains(word)){
            return null;
        }
        history.add(word);
        if (isPossibleToContinueDriftingLeft(grammar, word)) {
            return applyRuleProductions(false, grammar, word,history);
        }
        return null;
    }

    public String derivationRigth(Grammar grammar, String word,ArrayList<String> history) {
        if (userWord.equals(word)) {
            return word;
        }
        if(history.contains(word)){
            return null;
        }
        history.add(word);
        if (isPossibleToContinueDriftingRight(grammar, word)) {
            return applyRuleProductions(true, grammar, word,history);
        }
        return null;
    }

    public String applyRuleProductions(boolean right,Grammar grammar, String currentWord,ArrayList<String> history) {
        ArrayList<RuleProduction> rules = grammar.getRuleProductions();
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).getVariable() == this.actualchar) {
                Node child = new Node(this.userWord);
                this.chilList.add(child);
                if(!right){
                    String result = child.derivationLeft(grammar, 
                    extractVariableForLeft(currentWord,rules.get(i).getVariable(),rules.get(i).getProduction()),history);
                    if(result != null){
                        return currentWord+" -> "+result;
                    }
                }else{
                    String result = child.derivationRigth(grammar,
                    extractVariableForRight(currentWord,rules.get(i).getVariable(),rules.get(i).getProduction()),history);
                    if(result!=null){
                        return currentWord+" -> "+result;
                    }
                }
            }
        }
        return null;
    }

    public String extractVariableForLeft(String word, char variable, String production) {
        String newWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == variable) {
                if(production.charAt(0) == 'λ'){
                    newWord = newWord +word.substring(i + 1);    
                }else{
                    newWord = newWord + production + word.substring(i + 1);
                }
                return newWord;
            } else {
                newWord += word.charAt(i);
            }
        }
        return newWord;
    }

    public String extractVariableForRight(String word, char variable, String production) {
        String newWord = "";
        for (int i = word.length()-1; i >= 0; i--) {
            if (word.charAt(i) == variable) {
                if(production.charAt(0) == 'λ'){
                    newWord = word.substring(0, i) + newWord;    
                }else{
                    newWord = word.substring(0, i) + production + newWord;
                }
                return newWord;
            } else {
                newWord = word.charAt(i) + newWord;
            }
        }
        return newWord;
    }

}

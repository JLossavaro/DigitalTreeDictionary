/** *************************************************
 * 2 *                                                  *
 * 3 * Júlio Cézar Lossavaro                            *
 * 4 * 2018.0743.029.4                                  *
 * 5 * Implementacão 5                                  *
 * 6 * Disciplina: Estruturas de Dados e Programação I  *
 * 7 * Professor: Ronaldo Fiorilo                       *
 * 8 *                                                  *
 * 9 ************************************************** */


import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    
    boolean isFinal;
    char lang; 
    String word;
    HashMap<Character, Node> letras;
    ArrayList<String> synonyms;
    
    
    public Node() {
        this.isFinal = false;
        this.letras = new HashMap<>();
    }

}

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
import java.util.Collections;

public class Tree {

    private Node root;

    //Chama a função recursiveAdd com seus devidos parametros
    //Atribui um valor a raiz, caso nula
    public void recursiveAdd(String[] elementos) {
        int i = -1;
        if (root == null) {
            root = new Node();
        }
        root = recursiveAdd(this.root, elementos[2].toCharArray(), elementos[1].charAt(0), elementos[4], i);
        root = recursiveAdd(this.root, elementos[4].toCharArray(), elementos[3].charAt(0), elementos[2], i);
    }

    //Adiciona recursivamente uma cadeia de caracteres a estrutura
    public Node recursiveAdd(Node no, char[] element, char lang, String sym, int iterator) {
        iterator++;
        if (iterator == element.length - 1) {

            //caso o nó ja exista
            if (no.letras.containsKey(element[iterator])) {
                Node aux = no.letras.get(element[iterator]);
                aux.lang = lang;
                aux.isFinal = true;
                aux.word = String.valueOf(element);

                if (aux.synonyms == null) {
                    aux.synonyms = new ArrayList<>();
                    aux.synonyms.add(sym);
                } else if (!aux.synonyms.contains(sym)) {
                    aux.synonyms.add(sym);
                    Collections.sort(aux.synonyms);
                }

                no.letras.replace(element[iterator], no, aux);
                return no;
            }
            //caso não exista
            Node node = new Node();
            node.isFinal = true;
            node.lang = lang;
            node.word = String.valueOf(element);;
            node.synonyms = new ArrayList<>();
            node.synonyms.add(sym);
            no.letras.put(element[iterator], node);
            return no;
        }

        if (no.letras.containsKey(element[iterator])) {
            no.letras.replace(element[iterator], recursiveAdd(no.letras.get(element[iterator]), element, lang, sym, iterator));
        } else {
            no.letras.put(element[iterator], new Node());
            no.letras.replace(element[iterator], recursiveAdd(no.letras.get(element[iterator]), element, lang, sym, iterator));
        }
        return no;
    }

    //Chama a função findWord e alimenta inOrder com o seu resultado(partindo do nó devolvido)
    public void inOrder(char lang, String pre) {
        Node n = findWord(this.root, pre.toCharArray());
        inOrder(n, lang);
    }

    //Percorre a arvore imprimindo de maneira crescente as palavras
    public void inOrder(Node node, char lang) {
        if (node != null) {
            if (node.isFinal == true && node.lang == lang) {
                System.out.print(node.word + " : ");
                for (int i = 0; i < node.synonyms.size(); i++) {
                    if (i + 1 < node.synonyms.size()) {
                        System.out.print(node.synonyms.get(i) + ", ");
                    } else {
                        System.out.println(node.synonyms.get(i));
                    }
                }

            }

            for (Node no : node.letras.values()) {
                inOrder(no, lang);
            }
        }

    }

    //Procura uma palavra na estrutura
    public void find(String element) {
        Node node = findWord(this.root, element.toCharArray());

        if (node == null || node.isFinal == false) {
            System.out.println("hein?");
        } else {
            for (int i = 0; i < node.synonyms.size(); i++) {
                System.out.println(node.synonyms.get(i));
            }

        }
    }

    //Procura uma palavra na estrutura e retorna o seu nó final correspondente 
    public Node findWord(Node node, char[] arr) {

        if (node == null) {
            return node;
        }

        for (int i = 0; i < arr.length; i++) {
            if (node.letras.containsKey(arr[i])) {
                node = node.letras.get(arr[i]);
            } else {
                return null;
            }
        }

        return node;

    }

    //Chama a função remove, dada uma string
    public void remove(String element) {
        root = remove(this.root, element.toCharArray(), 0);
    }

    //Analisa uma cadeia de caracteres e remove os caracteres sem filhos
    public Node remove(Node node, char[] element, int depth) {

        if (node == null) {
            return node;

        }
        if (depth == element.length) {

            if (node.isFinal) {
                node.isFinal = false;
                node.word = null;
            }

            if (node.letras.isEmpty() && !root.equals(node)) {
                node = null;
            }
            return node;
        }
        node.letras.replace(element[depth], remove(node.letras.get(element[depth]), element, depth + 1));

        if (node.letras.size() <= 1 && node.isFinal == false) {
            if (node.letras.isEmpty() || node.letras.size() == 1 && node.letras.get(element[depth]) == null) {
                node = null;
            }
        } else if (node.letras.get(element[depth]) == null) {
            node.letras.remove(element[depth]);

        }
        return node;

    }

    //Recebe duas palavras e remove os seus sinonimos, caso não haja mais sinonimos
    //remove a palavra
    public void remove(String wordOne, String wordTwo) {
        Node w = findWord(this.root, wordOne.toCharArray());
        Node w2 = findWord(this.root, wordTwo.toCharArray());

        if (w != null) {
            w.synonyms.remove(wordTwo);
            if (w.synonyms.isEmpty()) {
                root = remove(this.root, w.word.toCharArray(), 0);
            }
        }
        if (w2 != null) {
            w2.synonyms.remove(wordOne);
            if (w2.synonyms.isEmpty()) {
                root = remove(this.root, w2.word.toCharArray(), 0);
            }
        }

    }

}

/** *************************************************
 * 2 *                                                  *
 * 3 * Júlio Cézar Lossavaro                            *
 * 4 * 2018.0743.029.4                                  *
 * 5 * Implementacão 5                                  *
 * 6 * Disciplina: Estruturas de Dados e Programação I  *
 * 7 * Professor: Ronaldo Fiorilo                       *
 * 8 *                                                  *
 * 9 ************************************************** */


import java.util.Scanner;

public class BinaryTreeWords {

    public static void main(String[] args) {

        Tree tree = new Tree();
        Scanner s1 = new Scanner(System.in);
        String commands = "";
        String[] comandos = new String[5];

        while (commands.compareTo("fim") != 0) {

            commands = s1.nextLine();
            comandos = commands.split(" ");
            char c = comandos[0].charAt(0);

            switch (c) {
                case 'i' ->
                    tree.recursiveAdd(comandos);
                case 'l' ->
                    tree.inOrder(comandos[1].charAt(0), comandos[2]);
                case 'b' ->
                    tree.find(comandos[1]);
                case 'r' -> {
                    if (comandos.length == 3) {
                        tree.remove(comandos[1], comandos[2]);
                    } else {
                        tree.remove(comandos[1]);
                    }

                }

            }
        }
    }
}

package UE03_Labyrinth;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;


/**
 * Author: Karanbir Guron 5AX
 */
public class Labyrinth {
    public static String[][] maps = {{
            "############",
            "#  #     # #",
            "## # ### # #",
            "#  # # # # #",
            "## ### # # #",
            "#        # #",
            "## ####### #",
            "#          #",
            "# ######## #",
            "# #   #    #",
            "#   #   # ##",
            "######A#####"
    }, {
            "################################",
            "#                              #",
            "# ############################ #",
            "# # ###       ##  #          # #",
            "# #     ##### ### # ########## #",
            "# #   ##### #     # #      ### #",
            "# # ##### #   ###   # # ## # # #",
            "# # ### # ## ######## # ##   # #",
            "# ##### #  # #   #    #    ### #",
            "# # ### ## # # # # ####### # # #",
            "# #        # #   #     #     # #",
            "# ######## # ######### # ### # #",
            "# ####     #  # #   #  # ##### #",
            "# # #### #### # # # # ## # ### #",
            "#                      # #     #",
            "###########################A####"
    }, {
            "###########################A####",
            "#   #      ## # # ###  #     # #",
            "# ###### #### # # #### ##### # #",
            "# # ###  ## # # # #          # #",
            "# # ### ### # # # # # #### # # #",
            "# #     ### # # # # # ## # # # #",
            "# # # # ### # # # # ######## # #",
            "# # # #     #          #     # #",
            "# ### ################ # # # # #",
            "# #   #             ## # #   # #",
            "# # #### ############# # #   # #",
            "# #                    #     # #",
            "# # #################### # # # #",
            "# # #### #           ###     # #",
            "# # ## # ### ### ### ### # ### #",
            "# #    #     ##  ##  # ###   # #",
            "# ####   ###### #### # ###  ## #",
            "###########################A####"
    }, {
            "#############",
            "#           #",
            "#           #",
            "#           #",
            "###########A#"
    }};

    /**
     * Wandelt (unveränderliche) Strings in Char-Arrays
     *
     * @param map der Plan, ein String je Zeile
     * @return char[][] des Plans
     */
    public static char[][] fromStrings(String[] map) {
        return Arrays.stream(map).map(String::toCharArray).toArray(char[][]::new);
    }


    /**
     * Ausgabe des Layrinths
     *
     * @param lab
     */
    public static void printLabyrinth(char[][] lab) {
        Arrays.stream(lab).forEach(System.out::println);
    }

    /**
     * Suche den Weg
     *
     * @param zeile  aktuelle Position
     * @param spalte aktuelle Position
     * @param lab
     * @throws InterruptedException für die verlangsamte Ausgabe mit sleep()
     */
    public static boolean suchen(int zeile, int spalte, char[][] lab) throws InterruptedException {
        if (lab[zeile][spalte] == 'A') return true;
        if (lab[zeile][spalte] != ' ') return false;

        lab[zeile][spalte] = '.';
        printLabyrinth(lab);
        sleep(500);

        int[][] values = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] i : values) {
            boolean step = suchen(zeile + i[0], spalte + i[1], lab);
            if (step) return true;
        }
        lab[zeile][spalte] = ' ';

        return false;
    }

    /**
     * Suche alle Wege
     *
     * @param zeile  aktuelle Position
     * @param spalte aktuelle Position
     * @param lab    labyrinth
     * @throws InterruptedException für die verlangsamte Ausgabe mit sleep()
     */
    public static int sucheAlle(int zeile, int spalte, char[][] lab) throws InterruptedException {
        if (lab[zeile][spalte] == 'A') return 1;
        if (lab[zeile][spalte] == '#' || lab[zeile][spalte] == '.') return 0;

        lab[zeile][spalte] = '.';
        //printLabyrinth(lab);
        //sleep(50);

        int[][] values = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        int res = 0;
        for (int[] i : values) {
            res += sucheAlle(zeile + i[0], spalte + i[1], lab);
        }
        lab[zeile][spalte] = ' ';

        return res;
    }

    /**
     * Reading Maze from txt File
     *
     * @param file Path to the txt file
     * @return List of strings, each representing a line in the file
     * @throws IOException If an input or output exception occurred
     */
    public static String[] readMaze(String file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        }
        return lines.toArray(new String[0]);
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        //char[][] labyrinth = fromStrings(maps[2]);
        char[][] labyrinth = fromStrings(readMaze("src/UE03_Labyrinth/l1.txt"));

        printLabyrinth(labyrinth);
       // System.out.println("Ausgang gefunden: " + (suchen(5, 5, labyrinth) ? "ja" : "nein"));
        System.out.println("Ausgang gefunden: " + (sucheAlle(5, 5, labyrinth)));
    }
}

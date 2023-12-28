package UE03_Labyrinth;
//TODO: Mein Name in der Javadoc

import java.util.Arrays;

import static java.lang.Thread.sleep;

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
	 * @param map  der Plan, ein String je Zeile
	 * @return char[][] des Plans
	 */
	public static char[][] fromStrings(String[] map) {
		return Arrays.stream(map).map(String::toCharArray).toArray(char[][]::new);
	}


	/**
	 * Ausgabe des Layrinths
	 * @param lab
	 */
	public static void printLabyrinth(char[][] lab) {
		Arrays.stream(lab).forEach(System.out::println);
	}

	/**
	 * Suche den Weg
	 * @param zeile     aktuelle Position
	 * @param spalte     aktuelle Position
	 * @param lab
	 * @throws InterruptedException    für die verlangsamte Ausgabe mit sleep()
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

	public static int sucheAlle(int zeile, int spalte, char[][] lab) throws InterruptedException {
		if (lab[zeile][spalte] == 'A') return 1;
		if (lab[zeile][spalte] == '#' || lab[zeile][spalte] == '.') return 0;

		lab[zeile][spalte] = '.';
		printLabyrinth(lab);
		sleep(50);

		int[][] values = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

		int res = 0;
		for (int[] i : values) {
			res += sucheAlle(zeile + i[0], spalte + i[1], lab);
		}
		lab[zeile][spalte] = ' ';

		return res;
	}

	public static void main(String[] args) throws InterruptedException {
		char[][] labyrinth = fromStrings(maps[2]);
		printLabyrinth(labyrinth);
		System.out.println("Ausgang gefunden: " + (suchen(5, 5, labyrinth) ? "ja" : "nein"));
		// TODO: System.out.println("Anzahl Wege: " + suchenAlle(5, 5, labyrinth));
	}
}

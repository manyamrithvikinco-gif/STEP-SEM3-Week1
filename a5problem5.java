import java.util.Arrays;

class Player implements Comparable<Player> {

    private String name;
    private int matchesPlayed;
    private double battingAverage;
    private boolean injured;

    public Player(String name, int matchesPlayed,
                  double battingAverage, boolean injured) {

        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    // Rule 1: Experience-only
    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    // Rule 2: Experience + fitness
    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    // Sort by batting average in descending order
    @Override
    public int compareTo(Player other) {
        return Double.compare(other.battingAverage,
                              this.battingAverage);
    }

    static String draftAndRank(Player[] players) {

        Player[] draftable = new Player[players.length];

        int count = 0;

        for (int i = 0; i < players.length; i++) {

            if (isDraftable(players[i].matchesPlayed) ||
                isDraftable(players[i].matchesPlayed,
                            players[i].injured)) {

                draftable[count] = players[i];
                count++;
            }
        }

        // Create array containing only draftable players
        Player[] finalList = Arrays.copyOf(draftable, count);

        // Sort using compareTo()
        Arrays.sort(finalList);

        String result = "";

        for (int i = 0; i < finalList.length; i++) {

            result = result + (i + 1) + ". "
                    + finalList[i].name;

            if (i < finalList.length - 1) {
                result = result + " | ";
            }
        }

        return result;
    }
}

public class Main {

    public static void main(String[] args) {

        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };

        System.out.println(
            Player.draftAndRank(players)
        );
    }
}
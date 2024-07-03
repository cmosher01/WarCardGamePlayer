package nu.mine.mosher.war;



import org.fusesource.jansi.AnsiConsole;

import java.security.SecureRandom;
import java.util.*;

import static nu.mine.mosher.war.BattleFront.*;



public class War {
    public static final int WAR = 3;

    public static void main(final String... args) {
        System.setProperty("jansi.force", Boolean.TRUE.toString());
        AnsiConsole.systemInstall();

        final WarPrinter printer;

        if (args.length > 0) {
            if (args[0].equals("-m")) {
                int t = Integer.parseInt(args[1], 10);
                if (10 < t) {
                    printer = new PrinterNull();
                } else {
                    printer = new PrinterStdout();
                }
                final List<GameStats> rStats = new ArrayList<>(t);
                while (0 < t--) {
                    final GameStats stats = play(printer);
                    rStats.add(stats);
                }

                System.out.println("-------------------------------------------------");
                rStats.sort(Comparator.comparingInt(GameStats::battles).reversed());
                for (int i = 0; i < 3; ++i) {
                    System.out.printf("%25d : %6d battles\n", rStats.get(i).seed(), rStats.get(i).battles());
                }
                System.out.println("......");
                rStats.sort(Comparator.comparingInt(GameStats::battles));
                for (int i = 2; i >= 0; --i) {
                    System.out.printf("%25d : %6d battles\n", rStats.get(i).seed(), rStats.get(i).battles());
                }
                System.out.println("-------------------------------------------------");
                for (int i = 0; i < rStats.getFirst().wars().length; ++i) {
                    final int j = i;
                    rStats.sort((a,b) -> Integer.compare(b.wars()[j], a.wars()[j]));
                    if (0 < rStats.get(i).wars()[i]) {
                        System.out.printf("%25d : wars %d : %3d\n", rStats.get(i).seed(), i + 1, rStats.get(i).wars()[i]);
                        System.out.println("-------------------------------------------------");
                    }
                }
            } else {
                printer = new PrinterStdout();
                play(Long.parseLong(args[0]), printer);
            }
        } else {
            printer = new PrinterStdout();
            play(printer);
        }
    }

    public static GameStats play(final WarPrinter printer) {
        final SecureRandom boot = createRandom();
        final long seed = boot.nextLong();
        return play(seed, printer);
    }

    public static GameStats play(final long seed, final WarPrinter printer) {
        final GameStats stats = new GameStats(seed);

        printer.printSeed(stats.seed());
        final Random rnd = new Random(stats.seed());

        final Deck deck = Deck.create(rnd);
        deck.shuffle();

        final Deck x = new Deck(rnd);
        final Deck y = new Deck(rnd);

        while (deck.any()) {
            x.addToBottom(deck.deal());
            y.addToBottom(deck.deal());
        }

        printer.printHeader();
        while (x.any() && y.any()) {
            stats.incBattles();
            printer.printSizes(stats.battles(), x, y);
            battle(x, new Deck(rnd), y, new Deck(rnd), 0, stats, printer);
            printer.printNewLine();
        }

        if (x.any()) {
            stats.winner(0);
        } else if (y.any()) {
            stats.winner(1);
        }

        printer.printSizes(0, x, y);
        if (x.any()) {
            printer.printWinner(0);
        } else if (y.any()) {
            printer.printWinner(1);
        }
        printer.printNewLine();

        return stats;
    }

    private static void battle(final Deck x, final Deck bootyX, final Deck y, final Deck bootyY, final int wars, final GameStats stats, final WarPrinter printer) {
        deploy(x, bootyX, y, bootyY, FACE_UP, printer);

        if (bootyY.last().rank() < bootyX.last().rank()) {
            printer.printFlagX();
            bootyX.addDeck(bootyY);
            x.addDeck(bootyX);
            if (0 < wars) {
                stats.incWar(wars-1);
            }
        } else if (bootyX.last().rank() < bootyY.last().rank()) {
            printer.printFlagY();
            bootyY.addDeck(bootyX);
            y.addDeck(bootyY);
            if (0 < wars) {
                stats.incWar(wars-1);
            }
        } else {
            printer.printFlagWar();
            war(x, bootyX, y, bootyY, wars+1, stats, printer);
        }
    }

    private static void war(final Deck x, final Deck bootyX, final Deck y, final Deck bootyY, final int wars, final GameStats stats, final WarPrinter printer) {
        final int s = bootySize(x, y);
        printer.printWarSize(s);

        for (int i = 0; i < s; ++i) {
            deploy(x, bootyX, y, bootyY, FACE_DOWN, printer);
        }

        if (!x.any() || !y.any()) {
            printer.printDraw();
            x.addDeck(bootyY);
            y.addDeck(bootyX);
        } else {
            battle(x, bootyX, y, bootyY, wars, stats, printer);
        }
    }

    private static void deploy(final Deck x, final Deck bootyX, final Deck y, final Deck bootyY, final BattleFront battlefront, final WarPrinter printer) {
        bootyX.addToBottom(x.deal());
        bootyY.addToBottom(y.deal());

        printer.printDeployment(bootyX, bootyY, battlefront);
    }

    private static int bootySize(final Deck x, final Deck y) {
        return
            Math.max(
                0,
                Math.min(
                    WAR,
                    Math.min(
                        x.size() - 1,
                        y.size() - 1)));
    }





    private static SecureRandom createRandom() {
        try {
            return SecureRandom.getInstance("NativePRNGNonBlocking");
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

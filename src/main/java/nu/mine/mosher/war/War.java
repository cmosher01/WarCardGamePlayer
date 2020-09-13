package nu.mine.mosher.war;



import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import static org.fusesource.jansi.Ansi.ansi;



public class War {
    private static final int WAR = 3;

    public static void main(final String... args) throws NoSuchAlgorithmException {
        System.setProperty("jansi.force", Boolean.TRUE.toString());
        AnsiConsole.systemInstall();

        final long seed;
        if (args.length > 0) {
            seed = Long.parseLong(args[0]);
        } else {
            final SecureRandom boot = SecureRandom.getInstance("NativePRNGNonBlocking");
            seed = boot.nextLong();
            System.out.printf("random seed: %d%n", seed);
        }
        final Random rnd = new Random(seed);

        final Deck deck = Deck.create(rnd);
        deck.shuffle();

        final Deck x = new Deck(rnd);
        final Deck y = new Deck(rnd);

        while (deck.any()) {
            x.addToBottom(deck.deal());
            y.addToBottom(deck.deal());
        }

        printHeader();
        int battles = 0;
        while (x.any() && y.any()) {
            System.out.printf("%6d : ", ++battles);
            printSizes(x, y);
            battle(x, new Deck(rnd), y, new Deck(rnd));
            System.out.println();
        }
        System.out.print("WINNER : ");
        printSizes(x, y);

        if (x.any()) {
            System.out.print(ansi().fg(Ansi.Color.BLUE ).a("X!        --> X").reset());
        } else if (y.any()) {
            System.out.print(ansi().fg(Ansi.Color.GREEN).a("       !Y --> Y").reset());
        }


        System.out.println();
        System.out.flush();
    }

    private static void printHeader() {
        System.out.println("ROUND# : X=cards(                 players' standings                  )cards=Y :  battle   --> winner,  or  War[size] cards...");
        System.out.println("------ : --------------------------------------------------------------------- : --------- --> -------------------------------------------- --> -");
    }

    private static void printSizes(final Deck x, final Deck y) {
        System.out.print(ansi().fg(Ansi.Color.BLUE).a(String.format("X=[%02d]=(", x.size())).reset());

        if (x.size() < y.size()) {
            System.out.print(" ".repeat(26));
            System.out.print("|");
            System.out.print(" ".repeat(25-x.size()));
            System.out.print(ansi().fg(Ansi.Color.WHITE).bg(winnerColor(x.size(), y.size())).a(winnerPlot(x.size(), y.size())).reset());
            System.out.print(" ".repeat(x.size()));
        } else if (y.size() < x.size()) {
            System.out.print(" ".repeat(y.size()));
            System.out.print(ansi().fg(Ansi.Color.WHITE).bg(winnerColor(x.size(), y.size())).a(winnerPlot(x.size(), y.size())).reset());
            System.out.print(" ".repeat(25-y.size()));
            System.out.print("|");
            System.out.print(" ".repeat(26));
        } else {
            System.out.print(" ".repeat(y.size()));
            System.out.print(ansi().fg(Ansi.Color.WHITE).bg(winnerColor(x.size(), y.size())).a(winnerPlot(x.size(), y.size())).reset());
            System.out.print(" ".repeat(x.size()));
        }

        System.out.print(ansi().fg(Ansi.Color.GREEN).a(String.format(")=[%02d]=Y", y.size())).reset());
        System.out.print(" : ");
    }

    private static String winnerPlot(final int sizeX, final int sizeY) {
        return sizeX == sizeY ? "=" : "*";
    }

    private static Ansi.Color winnerColor(final int sizeX, final int sizeY) {
        if (sizeX < sizeY) {
            return Ansi.Color.GREEN;
        }
        if (sizeY < sizeX) {
            return Ansi.Color.BLUE;
        }
        return Ansi.Color.BLACK;
    }

    private static void battle(final Deck x, final Deck bootyX, final Deck y, final Deck bootyY) {
        deploy(x, bootyX, y, bootyY, "|");

        if (bootyY.last().rank() < bootyX.last().rank()) {
            System.out.print(ansi().fg(Ansi.Color.BLUE ).a("--> X").reset());
            bootyX.addDeck(bootyY);
            x.addDeck(bootyX);
        } else if (bootyX.last().rank() < bootyY.last().rank()) {
            System.out.print(ansi().fg(Ansi.Color.GREEN).a("--> Y").reset());
            bootyY.addDeck(bootyX);
            y.addDeck(bootyY);
        } else {
            System.out.print(ansi().fg(Ansi.Color.RED  ).a("--> W").reset());
            war(x, bootyX, y, bootyY);
        }
    }

    private static void war(final Deck x, final Deck bootyX, final Deck y, final Deck bootyY) {
        final int s = bootySize(x, y);
        System.out.print(ansi().fg(Ansi.Color.RED).a(String.format("[%d] ", s)).reset());

        for (int i = 0; i < s; ++i) {
            deploy(x, bootyX, y, bootyY, "-");
        }

        if (!x.any() || !y.any()) {
            System.out.print(ansi().fg(Ansi.Color.BLACK).a("DRAW").reset());
            x.addDeck(bootyY);
            y.addDeck(bootyX);
        } else {
            battle(x, bootyX, y, bootyY);
        }
    }

    private static void deploy(final Deck x, final Deck bootyX, final Deck y, final Deck bootyY, final String battlefront) {
        bootyX.addToBottom(x.deal());
        System.out.print(ansi().fg(Ansi.Color.BLUE ).a(String.format("X>%02d", bootyX.last().rank())).reset());

        System.out.print(battlefront);

        bootyY.addToBottom(y.deal());
        System.out.print(ansi().fg(Ansi.Color.GREEN).a(String.format("%02d<Y", bootyY.last().rank())).reset());

        System.out.print(" ");
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
}

package nu.mine.mosher.war;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

public class PrinterStdout implements WarPrinter {
    public void printHeader() {
        System.out.println("ROUND : X=cards(                 players' standings                  )cards=Y :  battle   --> winner,  or  War[size] cards...");
        System.out.println("----- : --------------------------------------------------------------------- : --------- --> -------------------------------------------- --> -");
    }

    public void printSizes(final int round, final Deck x, final Deck y) {
        if (round <= 0) {
            System.out.printf("%5s : ", "FINAL");
        } else {
            System.out.printf("%5d : ", round);
        }
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

    public void printFlagWar() {
        System.out.print(ansi().fg(Ansi.Color.RED  ).a("--> W").reset());
    }

    public void printFlagY() {
        System.out.print(ansi().fg(Ansi.Color.GREEN).a("--> Y").reset());
    }

    public void printFlagX() {
        System.out.print(ansi().fg(Ansi.Color.BLUE ).a("--> X").reset());
    }

    public void printDraw() {
        System.out.print(ansi().fg(Ansi.Color.BLACK).a("DRAW").reset());
    }

    public void printWarSize(int s) {
        System.out.print(ansi().fg(Ansi.Color.RED).a(String.format("[%d] ", s)).reset());
    }

    public void printDeployment(final Deck x, final Deck y, final BattleFront battlefront) {
        System.out.print(ansi().fg(Ansi.Color.BLUE ).a(String.format("X>%02d", x.last().rank())).reset());
        System.out.print(battlefront);
        System.out.print(ansi().fg(Ansi.Color.GREEN).a(String.format("%02d<Y", y.last().rank())).reset());
        System.out.print(" ");
    }

    public void printWinner(final int w) {
        if (w == 0) {
            System.out.print(ansi().fg(Ansi.Color.BLUE ).a("X!        --> X").reset());
        } else if (w == 1) {
            System.out.print(ansi().fg(Ansi.Color.GREEN).a("       !Y --> Y").reset());
        }
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printSeed(final long seed) {
        System.out.printf("seed: %d%n", seed);
    }

    @Override
    public void printDeck(final Deck deck) {
        System.out.print("deck: ");
        System.out.println(deck);
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
}

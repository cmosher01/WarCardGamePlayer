package nu.mine.mosher.war;

public class PrinterNull implements WarPrinter {
    public void printHeader() {}
    public void printSizes(final int round, final Deck x, final Deck y) {}
    public void printFlagWar() {}
    public void printFlagY() {}
    public void printFlagX() {}
    public void printDraw() {}
    public void printWarSize(final int s) {}
    public void printDeployment(final Deck bootyX, final Deck bootyY, final BattleFront battlefront) {}
    public void printWinner(final int w) {}
    public void printNewLine() {}
    public void printSeed(final long seed) {}
    public void printDeck(final Deck deck) {}
}

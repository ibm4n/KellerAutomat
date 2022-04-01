public class PushDownItem {

    private char value;
    private PushDownItem nextItem;


    public PushDownItem(char value, PushDownItem item) {
        this.value = value;
        this.nextItem = item;

    }

    public PushDownItem getNextItem() {
        return this.nextItem;
    }


    public char getValue() {
        return value;
    }


}

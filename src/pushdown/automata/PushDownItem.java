package pushdown.automata;

public class PushDownItem {

    private String value;
    private PushDownItem nextItem;


    public PushDownItem(String value, PushDownItem item) {
        this.value = value;
        this.nextItem = item;

    }

    public PushDownItem getNextItem() {
        return this.nextItem;
    }


    public String getValue() {
        return value;
    }


}

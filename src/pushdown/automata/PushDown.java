package pushdown.automata;

public class PushDown {

    private PushDownItem currentItem;
    private static final String EMPTY_STACK = "$";

    public PushDown() {
        this.currentItem = new PushDownItem(EMPTY_STACK, null);
    }


    public void push(String value) {
        currentItem = new PushDownItem(value, currentItem);
    }

    public void push(int value){
        push(String.valueOf(value));
    }


    public String popAndReturn() {
        String returnItem;
        try {
            returnItem = currentItem.getValue();
            this.pop();
        } catch (NullPointerException e) {
            returnItem = "$";
        }
        return returnItem;
    }


    public void pop() {
        this.currentItem = this.currentItem.getNextItem();
    }

    public void printPushDown() {
        var currentItemForIter = this.currentItem;
        do {
            System.out.println(" " + currentItemForIter.getValue());
            currentItemForIter = currentItemForIter.getNextItem();
        }
        while (currentItemForIter.getNextItem() != null);


    }

}


public class PushDown {

    private PushDownItem currentItem;
    private final static char EMPTY_STACK = '$';

    public PushDown() {
        this.currentItem = new PushDownItem(EMPTY_STACK, null);
    }


    public void push(char value) {
        var newCurrentItem = new PushDownItem(value, currentItem);
        currentItem = newCurrentItem;
    }


    public char popAndReturn() {
        char returnitem = currentItem.getValue();
        this.pop();
        return returnitem;
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


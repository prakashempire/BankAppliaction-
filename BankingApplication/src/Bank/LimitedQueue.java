package Bank;

import java.util.LinkedList;

class LimitedQueue extends LinkedList<Float> {
    private int limit;
    LimitedQueue(){}
    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(Float val) {
        super.add(val);
        while (size() > limit) { 
            super.remove();
        }
        return true;
    }
}

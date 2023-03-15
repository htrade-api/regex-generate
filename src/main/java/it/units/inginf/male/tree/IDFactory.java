package it.units.inginf.male.tree;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author nvhuy9527
 */
public class IDFactory {

   private static final IDFactory instance = new IDFactory();
    private final AtomicLong id = new AtomicLong();

    private IDFactory() {
    }

    /**
     * This method return a new unique ID to identificate individuals
     * @return the ID
     */
    public long nextID(){
        return id.getAndIncrement();
    }

    public static IDFactory getInstance(){
        return instance;
    }
}

package com.ljheee.util.javabase;

import sun.misc.Unsafe;



public class AtomicLazySet {

    // setup to use Unsafe.compareAndSwapLong for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;


    private volatile long value;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicLazySet.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }



    public final void lazySetVolatile(long newValue) {
        // lazySet
        unsafe.putOrderedLong(this, valueOffset, newValue);
    }


    public static void main(String[] args) {

        AtomicLazySet test = new AtomicLazySet();
        test.lazySetVolatile(89);
        System.out.println(test.value);
    }


}

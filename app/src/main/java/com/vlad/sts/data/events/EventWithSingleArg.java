package com.vlad.sts.data.events;

public abstract class EventWithSingleArg<T> {
    public T getArg() {
        return arg;
    }

    final T arg;

    public EventWithSingleArg(T arg) {
        this.arg = arg;
    }
}

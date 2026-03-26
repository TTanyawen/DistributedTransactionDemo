package com.angela.tcc;

public interface TccAction<T> {
    // Try
    boolean prepare(T param);

    // Confirm
    boolean commit(T param);

    // Cancel
    boolean rollback(T param);
}

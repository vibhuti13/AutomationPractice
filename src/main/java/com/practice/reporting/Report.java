package com.practice.reporting;

/**
 * A contract: "anything that can report test progress".
 * It says WHAT must be possible, not HOW. Each implementation decides how.
 */
public interface Report {

void info(String message);
void pass(String message);
void fail(String message);

/** Default method: shared behavior that implementations get for free. */

default void step(String message) {
    info("STEP: " + message);
}
}

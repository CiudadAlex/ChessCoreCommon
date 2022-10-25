package org.leviatan.chess.tools.platform;

/**
 * Function2Arg.
 *
 * @author Alejandro
 *
 */
@FunctionalInterface
public interface Function2Arg<A1, A2, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param a1
     *            the function argument 1
     * @param a2
     *            the function argument 2
     * @return the function result
     */
    R apply(A1 a1, A2 a2);
}

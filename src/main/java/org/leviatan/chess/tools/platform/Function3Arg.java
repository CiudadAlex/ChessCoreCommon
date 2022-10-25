package org.leviatan.chess.tools.platform;

/**
 * Function3Arg.
 *
 * @author Alejandro
 *
 */
@FunctionalInterface
public interface Function3Arg<A1, A2, A3, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param a1
     *            the function argument 1
     * @param a2
     *            the function argument 2
     * @param a3
     *            the function argument 3
     * @return the function result
     */
    R apply(A1 a1, A2 a2, A3 a3);
}

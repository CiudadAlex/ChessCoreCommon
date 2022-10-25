package org.leviatan.chess.tools.profiler;

/**
 * GarbageCollector.
 *
 * @author Alejandro
 */
public final class GarbageCollector {

    private static final double PORUNAJE_ACEPTABLE = 0.2;

    private GarbageCollector() {
    }

    /** Ejecuta la recoleccion de basura si es necesario. */
    public static void runGarbageCollectorIfNecessary() {

        if (sobrepasaPorunajeDeMemoriaAceptable()) {
            runGarbageCollector();
        }
    }

    /** Devuelve true si sobrepasa el porcentaje aceptable de memoria. */
    private static boolean sobrepasaPorunajeDeMemoriaAceptable() {

        final Runtime runtime = Runtime.getRuntime();

        final double freeMem = runtime.freeMemory();
        final double maxMem = runtime.maxMemory();

        final boolean sobrepasa = freeMem / maxMem < PORUNAJE_ACEPTABLE;

        return sobrepasa;
    }

    /** Ejecuta la recoleccion de basura. */
    private static void runGarbageCollector() {

        final Cronometro cronometro = new Cronometro(false);
        cronometro.tic("GarbageCollector...");

        System.runFinalization();
        System.gc();

        cronometro.toc("La recolleccion de basura");
    }
}

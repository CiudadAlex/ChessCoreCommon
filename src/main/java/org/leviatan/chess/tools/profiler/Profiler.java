package org.leviatan.chess.tools.profiler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.tools.log.Logger;

/**
 * Profiler.
 *
 * @author Alejandro
 */
public final class Profiler {

    private Profiler() {
    }

    /** Mantiene la cuenta del numero de instancias por clase. */
    private static Map<Class<? extends Object>, LongCount> numInstancesCreatedPerClass = new HashMap<Class<? extends Object>, LongCount>();

    /**
     * Increases instance count.
     *
     * @param clazz
     *            clazz
     */
    public static void increaseInstanceCount(final Class<? extends Object> clazz) {

        if (numInstancesCreatedPerClass.containsKey(clazz)) {
            final LongCount longCount = numInstancesCreatedPerClass.get(clazz);
            longCount.addOne();

        } else {
            final LongCount longCount = new LongCount();
            numInstancesCreatedPerClass.put(clazz, longCount);
        }
    }

    /** Limpia el conteo. */
    public static void limpiarInstanceCount() {
        numInstancesCreatedPerClass = new HashMap<Class<? extends Object>, LongCount>();
    }

    /** Limpia el conteo. */
    public static void vuelcaResultadosInstanceCount() {

        if (Configuracion.IS_PROFILING_ENABLED) {

            final Set<Class<? extends Object>> keySet = numInstancesCreatedPerClass.keySet();
            Logger.log("**********************************");

            for (final Class<? extends Object> clazz : keySet) {

                final LongCount longCount = numInstancesCreatedPerClass.get(clazz);
                Logger.log(clazz.getName() + " = " + longCount.getCount());
            }

            Logger.log("**********************************");
        }
    }
}

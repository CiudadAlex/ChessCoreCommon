package org.leviatan.chess.tools.profiler;

import org.leviatan.chess.config.Configuracion;

/**
 * ProfiledObject.
 *
 * @author Alejandro
 */
public class ProfiledObject {

    /** Constructor para ProfiledObject. */
    public ProfiledObject() {

        if (Configuracion.IS_PROFILING_ENABLED) {
            Profiler.increaseInstanceCount(this.getClass());
        }
    }
}

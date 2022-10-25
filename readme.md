Para hacer un reinforcement learning el entrenamiento se haria haciendo partidas y calculando amenazas.
Si en un movimiento obtenemos una alta amenaza en los anteriores posiciones de la partida le aplicaremos como amenaza el maximo entre la amenaza
a corto plazo (calculada con el evaluador a corto) y la amenaza del movimiento posterior (que tenia una gran amenaza) atenuada segun
vamos atras en el numero de movimientos.

Si T(i) es el tablero en el instante i y Ac(i) es la amenaza a corto plazo que representa el tablero T(i). La amenaza con la que se entrenará ala red neuronal A(i) será:

Siendo "f" el factor atenuador entre [0,1]

A(i) = MAX[Ac(i), f * Ac(i+1), f^2 * Ac(i+2), f^3 * Ac(i+3), f^4 * Ac(i+4), f^5 * Ac(i+5) ...]

A(i) = MAX(j[0..n])[f^j * Ac(i+j)]


package androidsamples.java.dicegames

import java.util.*

/**
 * An implementation of a six-faced [Die] using [Random].
 */
class Die6 : Die {
    var rng: Random
    var value = 0

    init {
        rng = Random()
    }

    override fun roll() {
        value = 1 + rng.nextInt(6)
    }

    override fun value(): Int {
        return value
    }
}
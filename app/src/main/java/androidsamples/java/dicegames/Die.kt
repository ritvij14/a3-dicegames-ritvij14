package androidsamples.java.dicegames

/**
 * Interface for a die with arbitrary number of faces.
 *
 */
interface Die {
    /**
     * Rolls the die.
     */
    fun roll()

    /**
     * Reports the value of the top face of the die.
     *
     * @return number of dots on the top face of the die
     */
    fun value(): Int
}
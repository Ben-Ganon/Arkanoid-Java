//Ben Ganon 318731007

package game;

/**
 * Counter defines a number that we can update as the game progresses.
 */
public class Counter {
    private int number;

    /**
     * standard builder with an int.
     * @param num - the number to initialize with.
     */
    public Counter(int num) {
        this.number = num;
    }

    /**
     * @return - returns the number in the counter as a string.
     */
    public String toString() {
        return Integer.toString(this.number);
    }

    /**
     * increases the number in Counter by the parameter.
     * @param num - the number to add to the counter.
     */
    public void increase(int num) {
        this.number += num;
    }
    /**
     * decreases the number in Counter by the parameter.
     * @param num - the number to remove from the counter.
     */
    public void decrease(int num) {
        this.number -= num;
    }

    /**
     * @return - returns the int value of the counter.
     */
    public int getValue() {
        return this.number;
    }
}

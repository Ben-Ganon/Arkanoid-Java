//Ben Ganon 318731007

package game;

import java.awt.Color;
import java.util.Random;

/**
 * the Game.GenerateRandomColor class handles generating a random color.
 *
 *  @version 1.0
 *  @author Ben Ganon 318731007
 *  @since 2021-03-30
 */
public class GenerateRandomColor {
    /**
     * @return (Color) returns a random color as a Color object.
     */
    public static Color get() {
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        return new Color(red, green, blue);
    }
}

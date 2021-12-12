//Ben Ganon 318731007

package spritesandcollidables;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * SpritesAndCollidables.SpriteCollection defines behaviour of a list of sprites to draw to a game.
 */
public class SpriteCollection {
    private List<Sprite> collection = new LinkedList<>();

    /**
     * adds a sprite to the collection.
     * @param s - sprite to add.
     */
    public void addSprite(Sprite s) {
        this.collection.add(s);
    }

    /**
     * @return - returns the list of sprites in this collection.
     */
    public List<Sprite> getCollection() {
        return this.collection;
    }

    /**
     * removes sprite s from this sprite collection.
     * @param s - the desired sprite to find and remove.
     */
    public void removeSprite(Sprite s) {
            this.collection.remove(s);
    }

    /**
     * notifies all sprites in the collection they should calculate an additional frame.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<>(this.collection);
        for (Sprite sprite : copySprites) {
            sprite.timePassed();
        }
    }

    /**
     * draws all sprite in the collection to a drawSurface.
     * @param d - drawSurface to draw to.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : collection) {
            sprite.drawOn(d);
        }
    }
}

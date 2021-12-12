//Ben Ganon 318731007

package game.listeners;

/**
 * interface that defines methods for object that have listeners to update.
 */
public interface HitNotifier {
    /**
     * adds a new hitListener to this Notifier.
     * @param hl - the listener we want to add.
     */
    void addHitListener(HitListener hl);
    /**
     * removes a hitListener from this Notifier.
     * @param hl - the listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}

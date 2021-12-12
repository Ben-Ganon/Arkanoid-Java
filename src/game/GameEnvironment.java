//Ben Ganon 318731007

package game;

import basicshapes.Line;
import basicshapes.Point;
import spritesandcollidables.Collidable;
import spritesandcollidables.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Game.GameEnvironment holds the collidable objects of a game and defines their collision with a line.
 */
public class GameEnvironment {
    private List<Collidable> gameObjects = new ArrayList<>();

    /**
     * inserts a list of game objects to the game environment upon creating an instance.
     * @param gameObj - the array of collidable objects that should be in the game.
     */
    public GameEnvironment(List<Collidable> gameObj) {
        gameObjects = gameObj;
    }

    /**
     * @return - returns the list of game objects in this environment.
     */
    public List<Collidable> getGameObjects() {
        return this.gameObjects;
    }
    /**
     * adds a collidable object to the game environment.
     * @param c - the object to insert into the game.
     */
    public void addCollidable(Collidable c) {
        gameObjects.add(c);
    }

    /**
     * removes the collidable c from the environment.
     * @param c - desired collidable to remove.
     */
    public void removeCollidable(Collidable c) {
            this.gameObjects.remove(c);
    }

    /**
     * returns the closest collision point to the start of the line to any of the game environments objects.
     * if no object intersects this line, returns null.
     * @param trajectory - the line to check for collisions.
     * @return - the closest point to the start of the line that intersects a game object.
     */
    public CollisionInfo getClosestCollison(Line trajectory) {
        //setting a boolean to remember if we found an object that has a  collision with trajecctory.
        boolean foundCollision = false;
        //setting an int to remember the index of the intersecting object in the game object array.
        int firstInterFound = 0;
        //first loop searches for a any collision point.
        for (Collidable obj : gameObjects) {
            if (trajectory.closestIntersectionToStartOfLine(obj.getCollisionRectangle()) != null) {
                foundCollision = true;
                firstInterFound = gameObjects.indexOf(obj);
            }
        }
        //if we havent found any collisions, we can already return null.
        if (!foundCollision) {
            return null;
        }
        /*
         * defining the closest collision point as the first one we found in the first loop
         * we can assume that there is one because if we didnt, we would have returned null and exited the method.
         */
        Collidable closest = gameObjects.get(firstInterFound);
        Point closestPoint = trajectory.closestIntersectionToStartOfLine(closest.getCollisionRectangle());
        Double closestDist = closestPoint.distance(trajectory.start());
        //second loop to find the closest point of collision, similar to finding minimum/maximum.
        for (Collidable obj : gameObjects) {
            //checking first if the current object in the list even has a collision point with the trajectory.
            if (trajectory.closestIntersectionToStartOfLine(obj.getCollisionRectangle()) != null) {
                Collidable curr = obj;
                Point currPoint = trajectory.closestIntersectionToStartOfLine(curr.getCollisionRectangle());
                Double currDist = currPoint.distance(trajectory.start());
                /*
                 * if the distance between the current point and the start of the line is shorter than the minimum,
                 * the current point becomes the new minimum.
                 */
                if (currDist < closestDist) {
                    closest = curr;
                    currPoint = closestPoint;
                    closestDist = currDist;
                }
            }
        }
        //return the info about the closest collision.
        return new CollisionInfo(closestPoint, closest);
    }
}
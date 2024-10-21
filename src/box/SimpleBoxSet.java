package box;

//import org.jetbrains.annotations.NotNull;

import java.util.Vector;

/**
 * This class represents a  simple box with attributes such as abcissa, ordinate, width, height.
 */
public class SimpleBoxSet implements BoxSet {

  /**
   * This variable vector represents the whole set of rectangles.
   */
  private Vector<Vector<Integer>> rect = new Vector<>();

  /**
   * Construct a box object when this class object is defined.
   */
  public SimpleBoxSet() {
    this.rect = new Vector<>();
  }

  /**
   * This method checks if the new rectangle added is already present in the set or not.
   *
   * @param a parameters of referred rectangle.
   * @return boolean about duplicacy;
   */
  private boolean checkDuplicate(Vector<Integer> a) {
    for (Vector<Integer> check : this.rect) {
      if (check.equals(a)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method checks that the passed rectangles overlaps or not.
   *
   * @param rect1 rectangle.
   * @param rect2 rectangle.
   * @return boolean about overlapping.
   */
  private boolean overlap(Vector<Integer> rect1, Vector<Integer> rect2) {

    int x1min = rect1.get(0);
    int y1min = rect1.get(1);
    int x1max = rect1.get(0) + rect1.get(2);
    int y1max = rect1.get(1) + rect1.get(3);


    int x2min = rect2.get(0);
    int y2min = rect2.get(1);
    int x2max = rect2.get(0) + rect2.get(2);
    int y2max = rect2.get(1) + rect2.get(3);

    return (x2min < x1max && y2max > y1min) && (x1min < x2max && y1max > y2min);
  }

  /**
   * This method subtracts the intersecting rectangle from the reference rectangle.
   *
   * @param a rectangle.
   * @param b rectangle.
   *
   * @return the set of rectangles after subtraction of intersecting rectangle.
   */
  private Vector<Vector<Integer>> containedSubtract(Vector<Integer> a, Vector<Integer> b) {
    Vector<Vector<Integer>> ans = new Vector<>();

    int x1 = a.get(0);
    int y1 = a.get(1);
    int w1 = a.get(2);
    int l1 = a.get(3);

    int x2 = b.get(0);
    int y2 = b.get(1);
    int w2 = b.get(2);
    int l2 = b.get(3);


    //rectangle 1
    if ((x2 - x1) > 0 && (l1) > 0) {
      Vector<Integer> temprect = new Vector<>();
      temprect.add(x1);
      temprect.add(y1);
      temprect.add(x2 - x1);
      temprect.add(l1);
      ans.add(temprect);
    }

    //rectangle 2
    if (w2 > 0 && (y1 + l1 - y2 - l2) > 0) {
      Vector<Integer> temprect = new Vector<>();
      temprect.add(x2);
      temprect.add(y2 + l2);
      temprect.add(w2);
      temprect.add(y1 + l1 - y2 - l2);
      ans.add(temprect);
    }

    //rectangle 3
    if ((x1 + w1 - x2 - w2) > 0 && l1 > 0) {
      Vector<Integer> temprect = new Vector<>();
      temprect.add(x2 + w2);
      temprect.add(y1);
      temprect.add(x1 + w1 - x2 - w2);
      temprect.add(l1);
      ans.add(temprect);
    }

    //rectangle 4
    if (w2 > 0 && (y2 - y1) > 0) {
      Vector<Integer> temprect = new Vector<>();
      temprect.add(x2);
      temprect.add(y1);
      temprect.add(w2);
      temprect.add(y2 - y1);
      ans.add(temprect);
    }
    return ans;
  }

  /**
   * This method provides a vector with parameters of intersecting rectangle.
   *
   * @param a rectangle.
   * @param b rectangle.
   *
   * @return vector with parameters of intersecting rectangle.
   */
  private Vector<Integer> intersectRect(Vector<Integer> a, Vector<Integer> b) {

    int x1min = a.get(0);
    int y1min = a.get(1);
    int x1max = a.get(0) + a.get(2);
    int y1max = a.get(1) + a.get(3);

    int x2min = b.get(0);
    int y2min = b.get(1);
    int x2max = b.get(0) + b.get(2);
    int y2max = b.get(1) + b.get(3);

    Vector<Integer> ans = new Vector<>();

    ans.add(Math.max(x1min, x2min));
    ans.add(Math.max(y1min, y2min));
    int width;
    int height;

    width = Math.min(x2max, x1max) - Math.max(x2min, x1min);
    height = Math.min(y2max, y1max) - Math.max(y2min, y1min);
    ans.add(width);
    ans.add(height);

    return ans;
  }

  /**
   * This method is classed when we try to add a new rectangle to the set.
   *
   * @param x      coordinate.
   * @param y      coordinate.
   * @param width  of the rectangle.
   * @param height of the rectangle.
   */
  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("height or width cannot be negative");
    }

    Vector<Integer> addnew = new Vector<>();
    addnew.add(x);
    addnew.add(y);
    addnew.add(width);
    addnew.add(height);

    if (checkDuplicate(addnew)) {
      return; // Duplicate, do not add
    }

    // Create a new list to hold the rectangles that will be kept
    Vector<Vector<Integer>> newRectangles = new Vector<>();

    for (int i = 0; i < this.rect.size(); i++) {
      Vector<Integer> currentRect = this.rect.get(i);
      if (overlap(currentRect, addnew)) {
        Vector<Integer> intsec = intersectRect(currentRect, addnew);
        Vector<Vector<Integer>> subtractedRects = containedSubtract(currentRect, intsec);
        newRectangles.addAll(subtractedRects);
      } else {
        newRectangles.add(currentRect);
      }
    }

    // Add the new rectangle to the list
    newRectangles.add(addnew);

    this.rect = newRectangles;
  }

  /**
   * This method subtracts the passed rectangle from the set of rectangles.
   *
   * @param x      coordinate.
   * @param y      coordinate.
   * @param width  of the rectangle.
   * @param height of the rectangle.
   */
  @Override
  public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("height or width cannot be negative");
    }

    if (this.rect.isEmpty()) {
      return;
    }

    Vector<Integer> tosubtract = new Vector<>();
    tosubtract.add(x);
    tosubtract.add(y);
    tosubtract.add(width);
    tosubtract.add(height);

    // Create a new list to hold the rectangles that will be kept
    Vector<Vector<Integer>> newRectangles = new Vector<>();

    for (int i = 0; i < this.rect.size(); i++) {
      Vector<Integer> current = this.rect.get(i);
      if (!overlap(current, tosubtract)) {
        newRectangles.add(current);
      } else {
        if (current.equals(tosubtract)) {
          continue; // Exact match, do not add
        } else {
          Vector<Integer> intsec = intersectRect(current, tosubtract);
          Vector<Vector<Integer>> subtractedRects = containedSubtract(current, intsec);
          newRectangles.addAll(subtractedRects);
        }
      }
    }
    this.rect = newRectangles;
  }

  /**
   * This method makes an array of rectangles from the vector and returns the array.
   *
   * @return array of boxes.
   */
  @Override
  public int[][] getBoxes() {
    int[][] boxes = new int[this.rect.size()][4];
    for (int i = 0; i < this.rect.size(); i++) {
      for (int j = 0; j < this.rect.get(i).size(); j++) {
        boxes[i][j] = this.rect.get(i).get(j);
      }
    }
    return boxes;
  }

  /**
   * Returns the number of rectangles in the set.
   *
   * @return the number of rectangles in this set
   */
  @Override
  public int size() {
    return this.rect.size();
  }
}

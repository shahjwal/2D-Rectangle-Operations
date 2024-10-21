package box;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the class SimpleBoxSet.
 */
public class SimpleBoxSetTest {

  private BoxSet rect;

  /**
   * This is the constructor test case.
   */
  @Before
  public void setUp() {
    rect = new SimpleBoxSet();
  }

  /**
   * This tests if the width is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test1() {
    rect.add(1, 2, -3, 4);
  }

  /**
   * This tests if the width is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test2() {
    rect.add(1, 2, 0, 4);
  }

  /**
   * This tests if the height is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test3() {
    rect.add(1, 2, 3, -4);
  }

  /**
   * This tests if the height is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test4() {
    rect.add(1, 2, 3, 0);
  }

  /**
   * This tests if the height and width is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test5() {
    rect.add(1, 2, -3, -4);
  }

  /**
   * This tests if the height is 0 and width is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test6() {
    rect.add(1, 2, -3, 0);
  }

  /**
   * This tests if the height is negative and width is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test7() {
    rect.add(1, 2, -3, 0);
  }

  /**
   * This tests if the width is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test8() {
    rect.subtract(1, 2, -3, 4);
  }

  /**
   * This tests if the width is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test9() {
    rect.subtract(1, 2, 0, 4);
  }

  /**
   * This tests if the height is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test10() {
    rect.subtract(1, 2, 3, -4);
  }

  /**
   * This tests if the height is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test11() {
    rect.subtract(1, 2, 3, 0);
  }

  /**
   * This tests if the height and width is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test12() {
    rect.subtract(1, 2, -3, -4);
  }

  /**
   * This tests if the height is 0 and width is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test13() {
    rect.subtract(1, 2, -3, 0);
  }

  /**
   * This tests if the height is negative and width is 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test14() {
    rect.subtract(1, 2, -3, 0);
  }

  /**
   * This checks the size after adding one rectangle.
   */
  @Test
  public void test15() {
    rect.add(1, 2, 3, 4);
    int x = rect.size();
    assertEquals(1, x);
  }

  /**
   * Checks the size if the rectangles added are non overlapping.
   */
  @Test
  public void test16() {
    rect.add(1, 2, 3, 4);
    rect.add(5, 6, 10, 11);
    int x = rect.size();
    assertEquals(2, x);
  }

  /**
   * Checks if the duplicate rectangle adds or not.
   */
  @Test
  public void test17() {
    rect.add(1, 2, 3, 4);
    rect.add(1, 2, 3, 4);
    int x = rect.size();
    assertEquals(1, x);
  }

  /**
   * checks if overlapped rectangle adds.
   * It makes other rectangles referring it ot not.
   */
  @Test
  public void test18() {
    rect.add(1, 1, 3, 4);
    rect.add(0, 0, 2, 3);
    int x = rect.size();
    assertEquals(3, x);
  }

  /**
   * Checks the coordinates of the new boxes are right ot not.
   */
  @Test
  public void test19() {
    rect.add(1, 1, 3, 4);
    rect.add(0, 0, 2, 3);
    rect.add(2, 3, 5, 6);
    int x = rect.size();
    int[][] output = {{1, 3, 1, 2}, {2, 1, 2, 2}, {0, 0, 2, 3}, {2, 3, 5, 6}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the new rectangle is within the existing rectangle.
   */
  @Test
  public void test20() {
    rect.add(0, 0, 5, 5);
    rect.add(1, 1, 1, 1);
    int x = rect.size();
    assertEquals(5, x);
    int[][] output = {{0, 0, 1, 5}, {1, 2, 1, 3}, {2, 0, 3, 5}, {1, 0, 1, 1}, {1, 1, 1, 1}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * If the rectangle lies on the edge of existing rectangle.
   */
  @Test
  public void test21() {
    rect.add(0, 0, 5, 5);
    rect.add(3, 3, 3, 3);
    int x = rect.size();
    assertEquals(3, x);
    int[][] output = {{0, 0, 3, 5}, {3, 0, 2, 3}, {3, 3, 3, 3}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }

  }

  /**
   * if the new rectangle cuts the existing rectangle in half horizontally.
   */
  @Test
  public void test22() {
    rect.add(0, 0, 5, 5);
    rect.add(0, 0, 5, 2);
    int x = rect.size();
    assertEquals(2, x);
    int[][] output = {{0, 2, 5, 3}, {0, 0, 5, 2}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the new rectangle cuts the existing rectangle in half vertically.
   */
  @Test
  public void test23() {
    rect.add(0, 0, 5, 5);
    rect.add(3, 0, 2, 5);
    int x = rect.size();
    assertEquals(2, x);
    int[][] output = {{0, 0, 3, 5}, {3, 0, 2, 5}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the existing rectangle is subset of new rectangle.
   */
  @Test
  public void test24() {
    rect.add(0, 0, 1, 1);
    rect.add(-1, -1, 3, 3);
    int x = rect.size();
    assertEquals(1, x);
    int[][] output = {{-1, -1, 3, 3}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * If without addding any rectangle, subtract method is called.
   */
  @Test
  public void test25() {
    rect.subtract(1, 2, 2, 3);
    int x = rect.size();
    assertEquals(0, x);
  }

  /**
   * If the subtracting rectangle does not overlap with any rectangle in set.
   */
  @Test
  public void test26() {
    rect.add(0, 0, 1, 1);
    rect.subtract(3, 3, 1, 1);
    int x = rect.size();
    assertEquals(1, x);
    int[][] output = {{0, 0, 1, 1}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the subtracting rectangle overlaps with 1 rectangle.
   */
  @Test
  public void test27() {
    rect.add(0, 0, 5, 5);
    rect.subtract(3, 3, 7, 7);
    int x = rect.size();
    assertEquals(2, x);
    int[][] output = {{0, 0, 3, 5}, {3, 0, 2, 3}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the subtracting rectangle overlaps with more than 1 existing rectangles.
   */
  @Test
  public void test28() {
    rect.add(2, 2, 2, 2);
    rect.add(1, 1, 3, 4);
    rect.subtract(2, 3, 5, 5);
    int x = rect.size();
    assertEquals(2, x);
    int[][] output = {{1, 1, 1, 4}, {2, 1, 2, 2}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the subtracting box is subset of existing box.
   */
  @Test
  public void test29() {
    rect.add(0, 0, 5, 5);
    rect.subtract(1, 1, 1, 1);
    int x = rect.size();
    assertEquals(4, x);
    int[][] output = {{0, 0, 1, 5}, {1, 2, 1, 3}, {2, 0, 3, 5}, {1, 0, 1, 1}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * If the subtracting box lies on the edge of existing box.
   */
  @Test
  public void test30() {
    rect.add(0, 0, 5, 5);
    rect.subtract(3, 3, 2, 1);
    int x = rect.size();
    assertEquals(3, x);
    int[][] output = {{0, 0, 3, 5}, {3, 4, 2, 1}, {3, 0, 2, 3}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the subtracting box lies inside and is on the edge of existing rectangle.
   * Checks if the cutting is done vertically.
   */
  @Test
  public void test31() {
    rect.add(0, 0, 5, 5);
    rect.subtract(3, 0, 2, 2);
    int x = rect.size();
    assertEquals(2, x);
    int[][] output = {{0, 0, 3, 5}, {3, 2, 2, 3}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

  /**
   * if the existing rectangle is subset of subtracting rectangle.
   */
  @Test
  public void test32() {
    rect.add(0, 0, 5, 5);
    rect.subtract(-1, -1, 8, 8);
    int x = rect.size();
    assertEquals(0, x);
    int[][] output = {{}};
    int[][] compare = rect.getBoxes();
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        assertEquals(output[i][j], compare[i][j]);
      }
    }
  }

}
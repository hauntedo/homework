package darthrusya.SortedSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppTest {

    private MySortedSet<Integer> ss = new MySortedSet<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            ss.add(i);
        }
    }

    @Test
    public void testSubSet() {

        Integer[] actual = new Integer[4];
        Integer[] expected = new Integer[]{3,4,5,6};
        int k = 0;
        for (Integer i : ss.subSet(2, 7)) {
            actual[k++] = i;
        }

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testHeadSet() {

        Integer[] actual = new Integer[5];
        Integer[] expected = new Integer[]{0,1,2,3,4};
        int k = 0;
        for (Integer i : ss.headSet(5)) {
            actual[k++] = i;
        }

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testTailSet() {

        Integer[] actual = new Integer[3];
        Integer[] expected = new Integer[]{7,8,9};
        int k = 0;
        for (Integer i : ss.tailSet(6)) {
            actual[k++] = i;
        }

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFirst() {

        int actual = ss.first();
        int expected = 0;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFirstAfterRemove() {

        ss.remove(0);
        int actual = ss.first();
        int expected = 1;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testLast() {

        int actual = ss.last();
        int expected = 9;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testLastAfterRemove() {

        ss.remove(9);
        int actual = ss.last();
        int expected = 8;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSize() {

        int actual = ss.size();
        int expected = 10;

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testSizeAfterRemove() {

        ss.remove(2);
        int actual = ss.size();
        int expected = 9;

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testAdd() {
        Assert.assertNotNull(ss);
    }

    @Test
    public void testRemove() {

        ss.remove(9);

        int actual = ss.last();
        int expected = 8;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddAll() {

        List<Integer> list = new ArrayList<>();
        list.add(42);
        list.add(13);

        ss.addAll(list);

        int k = 0;
        Integer[] actual = new Integer[12];
        Integer[] expected = new Integer[]{0,1,2,3,4,5,6,7,8,9,42,13};
        for (Integer i : ss) {
            actual[k] = i;
            k++;
        }

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testRemoveAll() {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);

        ss.removeAll(list);

        int k = 0;
        Integer[] actual = new Integer[8];
        Integer[] expected = new Integer[]{0,2,3,4,6,7,8,9};
        for (Integer i : ss) {
            actual[k] = i;
            k++;
        }

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testSubSetToException() {
        Integer[] actual = new Integer[4];
        int k = 0;
        try {
            for (Integer i : ss.subSet(9, 54)) {
                actual[k++] = i;
            }
        } catch(IllegalArgumentException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    public void testHeadSetToException() {
        Integer[] actual = new Integer[4];
        int k = 0;
        try {
            for (Integer i : ss.headSet(62)) {
                actual[k++] = i;
            }
        } catch(IllegalArgumentException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    public void testTailSetToException() {
        Integer[] actual = new Integer[4];
        int k = 0;
        try {
            for (Integer i : ss.tailSet(-1)) {
                actual[k++] = i;
            }
        } catch(IllegalArgumentException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}

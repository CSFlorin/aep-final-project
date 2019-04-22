import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    private static Node a = new Node();
    private static Node b = new Node();
    private static Node c = new Node();
    private static Node d = new Node();
    private static Node e = new Node();
    private static Node f = new Node();
    private static Node g = new Node();
    private static Node h = new Node();

    static {
        h.add(b, 20);
        b.add(c, 10);
        c.add(d, 6);
        d.add(e, 8);
        e.add(b, 9);
        b.add(a, 12);
        a.add(f, 8);
        c.add(e, 16);
        c.add(e, 22);
    }

    @Test
    public void canReachSelf() {
        Node a = new Node();
        assertTrue(a.reaches(a).isReachable());
    }

    @Test
    public void cannotReachDisconnectedNode() {
        Node a = new Node();
        Node b = new Node();
        assertFalse(a.reaches(b).isReachable());
    }

    @Test
    public void hCanReachB() {
        assertTrue(h.reaches(b).isReachable());
    }

    @Test
    public void hCanReachC() {
        assertTrue(h.reaches(c).isReachable());
    }

    @Test
    public void hCanReachE() {
        assertTrue(h.reaches(e).isReachable());
    }

    @Test
    public void hCannotReachG() {
        assertFalse(h.reaches(g).isReachable());
    }

    @Test
    public void stepsFromHtoH() {
        assertEquals(0, h.reaches(h).getSteps());
    }

    @Test
    public void stepsFromHtoB() {
        assertEquals(1, h.reaches(b).getSteps());
    }

    @Test
    public void stepsFromHtoC() {
        assertEquals(2, h.reaches(c).getSteps());
    }

    @Test
    public void costFromHtoB() { assertEquals(20, h.reaches(b).getCost()); }

    @Test
    public void costFromBtoC() { assertEquals(10, b.reaches(c).getCost()); }

    @Test
    public void costFromHtoC() { assertEquals(30, h.reaches(c).getCost()); }

    @Test
    public void costFromHtoD() { assertEquals(36, h.reaches(d).getCost()); }

    @Test
    public void costFromCtoE() { assertEquals(16, h.reaches(d).getCost()); }

}

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    private static Node a;
    private static Node b;
    private static Node c;
    private static Node d;
    private static Node e;
    private static Node f;
    private static Node g;
    private static Node h;


    void initializeGraph() {
        a = new Node();
        b = new Node();
        c = new Node();
        d = new Node();
        e = new Node();
        f = new Node();
        g = new Node();
        h = new Node();

        {
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
    }



    @Test
    public void canReachSelf() {
        initializeGraph();
        Node a = new Node();
        assertTrue(a.reaches(a).isReachable());
    }

    @Test
    public void cannotReachDisconnectedNode() {
        initializeGraph();
        Node a = new Node();
        Node b = new Node();
        assertFalse(a.reaches(b).isReachable());
    }

    @Test
    public void hCanReachB() {
        initializeGraph();
        assertTrue(h.reaches(b).isReachable());
    }

    @Test
    public void hCanReachC() {
        initializeGraph();
        assertTrue(h.reaches(c).isReachable());
    }

    @Test
    public void hCanReachE() {
        initializeGraph();
        assertTrue(h.reaches(e).isReachable());
    }

    @Test
    public void costFromHToF() {
        initializeGraph();
        assertEquals(40, h.reaches(f).getCost());
    }

    @Test
    public void hCannotReachG() {
        initializeGraph();
        assertFalse(h.reaches(g).isReachable());
    }

    @Test
    public void stepsFromHtoH() {
        assertEquals(0, h.reaches(h).getSteps());
    }

    @Test
    public void stepsFromHtoB() {
        initializeGraph();
        assertEquals(1, h.reaches(b).getSteps());
    }

    @Test
    public void stepsFromHtoC() {
        initializeGraph();
        assertEquals(2, h.reaches(c).getSteps());
    }

    @Test
    public void costFromHtoB() {
        initializeGraph();
        assertEquals(20, h.reaches(b).getCost()); }

    @Test
    public void costFromBtoC() {
        initializeGraph();
        assertEquals(10, b.reaches(c).getCost()); }

    @Test
    public void costFromHtoC() {
        initializeGraph();
        assertEquals(30, h.reaches(c).getCost()); }

    @Test
    public void costFromHtoD() {
        initializeGraph();
        assertEquals(36, h.reaches(d).getCost()); }

    @Test
    public void costFromCtoE() {
        initializeGraph();
        assertEquals(14, c.reaches(e).getCost());
    }

    @Test
    public void costFromBtoB() {
        initializeGraph();
        assertEquals(0, b.reaches(b).getCost());
    }

}

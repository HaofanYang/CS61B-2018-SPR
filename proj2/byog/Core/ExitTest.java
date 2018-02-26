package byog.Core;

import org.junit.Test;
import static org.junit.Assert.*;


public class ExitTest {
    @Test
    public void testGetType() {
        Exit e = new Exit(4, 6);
        Sector sector = new RoomTE(10,7,0,0);
        String type = e.getType(sector);
        System.out.println(type);
    }
}

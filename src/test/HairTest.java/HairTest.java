import org.junit.*;
import static org.junit.Assert.*;

public class HairTest {

    // instantiate hair object

    @Test
public void Hair_instantiateCorrectley_true() {
    Hair myHair = new Hair ("bob cut");
    assertEquals(true, myHair instanceof Hair);
}

// assigns each hairstylist a name and saves it

    @Test
    public void Hair_instantiateWithDescription_string() {
        Hair myHair = new Hair ("bob cut");
        assertEquals(" bob cut", myHair.getDescription();
    }


}
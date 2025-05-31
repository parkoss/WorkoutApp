import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
public class PlanTest {

    private Plan plan;
    private String testUser = "testUserPlan";
    private File planFile;

    @BeforeEach
    void setUp() {
        plan = new Plan(testUser);
        planFile = new File("plan_" + testUser + ".txt");
        planFile.delete(); // clean up
    }

    @AfterEach
    void tearDown() {
        if (planFile.exists()) {
            planFile.delete();
        }
    }

    @Test
    void testSavePlanToFile() throws IOException {
        String name = "TestPlan";
        String date = "2025-12-31";
        String time = "18:30";
        String note = "Leg day";

        plan.savePlanToFile(name, date, time, note);

        assertTrue(planFile.exists(), "Soubor by měl existovat");

        try (BufferedReader reader = new BufferedReader(new FileReader(planFile))) {
            String line = reader.readLine();
            assertNotNull(line);
            String[] parts = line.split(";");
            assertEquals(5, parts.length);
            assertEquals(name, parts[1]);
            assertEquals(date, parts[2]);
            assertEquals(time, parts[3]);
            assertEquals(note, parts[4]);
        }
    }

    @Test
    void testGetTodayPlansTextNoPlans() {
        String name = "Swim";
        String date = "2099-01-01";
        String time = "10:00";
        String note = "Test note";

        plan.savePlanToFile(name, date, time, note);

        String result = plan.getTodayPlansText();
        assertEquals("No plans today.", result);
    }
}




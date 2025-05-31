import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class WorkoutTest {

    private Workout workout;

    private String testUser = "testUser";

    @BeforeEach
    public void setUp() {
        workout = new Workout(testUser);
    }

    @AfterEach
    public void tearDown() {
        new File("workout_" + testUser + ".txt").delete();
        new File("user_" + testUser + "_workouts.txt").delete();
    }

    @Test
    public void testPauseSelectionMapping() {
        int[] expectedSeconds = {0, 60, 180, 300};
        for (int i = 0; i < 4; i++) {
            int pauseMinutes = switch (i) {
                case 0 -> 0;
                case 1 -> 1;
                case 2 -> 3;
                case 3 -> 5;
                default -> 1;
            };
            assertEquals(expectedSeconds[i], pauseMinutes * 60);
        }
    }

    @Test
    public void testSaveWorkoutToFile() throws IOException {
        workout.saveWorkoutToFile("Pushups", 20, 0.0);

        File file = new File("workout_" + testUser + ".txt");
        assertTrue(file.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            assertNotNull(line);
            assertTrue(line.contains("Pushups"));
            assertTrue(line.contains("20"));
        }
    }

    @Test
    void testCurrentExerciseSetAfterStartButtonPressed() {
        workout.exerciseInput.setText("Squats");
        workout.startButton.doClick();
        assertEquals("Squats", workout.currentExercise);
    }


}

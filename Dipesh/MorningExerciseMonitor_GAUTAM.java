

public class MorningExerciseMonitor_GAUTAM {
    private String name;
    private float[] distances;

    // No-argument constructor
    public MorningExerciseMonitor_GAUTAM() {
        name = "";
        distances = new float[7];
    }

    // Parameterized constructor
    public MorningExerciseMonitor_GAUTAM(String name, float[] distances) {
        this.name = name;
        this.distances = distances;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PERSON\n");
        sb.append("Name = ").append(name).append("\n");
        sb.append("Week Exercise Distance = ");
        for (float distance : distances) {
            sb.append(distance).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // Remove the trailing comma and space
        return sb.toString();
    }
}

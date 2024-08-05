public class Exercise {
    // Các thuộc tính của lớp Exercise
    private String code;
    private String name;
    private String muscleGroup;
    private int duration; // in minutes
    private int difficulty; // scale from 1 to 10

    // Constructor
    public Exercise(String code, String name, String muscleGroup, int duration, int difficulty) {
        this.code = code;
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMuscleGroup() { return muscleGroup; }
    public void setMuscleGroup(String muscleGroup) { this.muscleGroup = muscleGroup; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    @Override
    public String toString() {
        return "Exercise{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", duration=" + duration +
                ", difficulty=" + difficulty +
                '}';
    }

    // Chuyển đổi đối tượng Exercise thành chuỗi
    public String toFileString() {
        return code + "," + name + "," + muscleGroup + "," + duration + "," + difficulty;
    }

    // Chuyển đổi chuỗi thành đối tượng Exercise
    public static Exercise fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        String code = parts[0];
        String name = parts[1];
        String muscleGroup = parts[2];
        int duration = Integer.parseInt(parts[3]);
        int difficulty = Integer.parseInt(parts[4]);
        return new Exercise(code, name, muscleGroup, duration, difficulty);
    }
}

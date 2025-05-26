class WorkoutPlan {
    String name;
    String date;
    String note;

    public WorkoutPlan(String name, String date, String note) {
        this.name = name;
        this.date = date;
        this.note = note;
    }

    @Override
    public String toString() {
        return name + " (" + date + ")";
    }

}

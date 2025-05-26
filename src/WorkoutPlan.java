class WorkoutPlan {
    String name;
    String date;
    String time;
    String note;

    public WorkoutPlan(String name, String date, String time, String note) {
        this.name = name;
        this.date = date;
        this.time=time;
        this.note = note;
    }

    @Override
    public String toString() {
        return name + " (" + date + " " + time + ")";
    }

}

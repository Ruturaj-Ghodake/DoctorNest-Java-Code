package co.in.doctornest.enums;

public enum Gender{
    MALE("M", "Male", "Man", 0),
    FEMALE("F", "Female", "Women",1),
    OTHER("O", "Other", "Other", 2);

    private int value;
    private String shortForm;
    private String name;
    private String desc;

    Gender(String shortForm, String name, String desc, int value) {
        this.shortForm = shortForm;
        this.name = name;
        this.desc = desc;
        this.value = value;
    }

    public static Gender findGender(short val) {
        int intVal=val;
        for (Gender g : Gender.values()) {
            if (intVal == g.value) {
                return g;
            }
        }
        return null;
    }

}

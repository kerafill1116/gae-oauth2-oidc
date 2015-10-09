package gae.oauth2.oidc.schema;

public class Gender {
    public static final Gender MALE = new Gender("male");
    public static final Gender FEMALE = new Gender("female");

    private final String gender;

    public Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}

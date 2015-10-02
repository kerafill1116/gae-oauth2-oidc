package gae.oauth2.oidc.schema;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.net.URL;
import java.util.Date;

@Entity
public class EndUser {

    @Id
    private String sub;
    private String email;
    private boolean emailVerified;
    private String zoneinfo;
    private String locale;
    private Date updatedAt;
    private Date dateCreated;
    // optional
    private String name;
    private String givenName;
    private String familyName;
    private String middleName;
    private String nickname;
    private String preferredUsername;
    private URL profile;
    private URL picture;
    private URL website;
    private Gender gender;
    private Date birthdate;
    private String phoneNumber;
    private String phoneNumberVerified;
    private String address;

    private EndUser() { }

    public EndUser(String sub, String email, String zoneinfo, String locale, Date dateCreated) {
        this.sub = sub;
        this.email = email;
        this.zoneinfo = zoneinfo;
        this.locale = locale;
        this.updatedAt = dateCreated;
        this.dateCreated = dateCreated;
    }

    public URL getProfile() {
        return profile;
    }

    public void setProfile(URL profile) {
        this.profile = profile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

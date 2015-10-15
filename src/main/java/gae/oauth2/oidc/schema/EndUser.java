package gae.oauth2.oidc.schema;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.net.URL;
import java.util.Date;
import javax.mail.internet.InternetAddress;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.map.HashedMap;

@Entity
public class EndUser {
    @Id
    private String sub;
    private String name;
    private IterableMap<String, String> names = new HashedMap<>();
    private String givenName;
    private IterableMap<String, String> givenNames = new HashedMap<>();
    private String familyName;
    private IterableMap<String, String> familyNames = new HashedMap<>();
    private String middleName;
    private IterableMap<String, String> middleNames = new HashedMap<>();
    private String nickname;
    private IterableMap<String, String> nicknames = new HashedMap<>();
    private String preferredUsername;
    private URL profile;
    private IterableMap<String, URL> profiles = new HashedMap<>();
    private URL picture;
    private IterableMap<String, URL> pictures = new HashedMap<>();
    private URL website;
    private IterableMap<String, URL> websites = new HashedMap<>();
    private InternetAddress email;
    private boolean emailVerified;
    private Gender gender;
    private Date birthdate;
    private String zoneinfo;
    private String locale;
    private String phoneNumber;
    private String phoneNumberVerified;
    private Address address;
    private IterableMap<String, Address> addresses= new HashedMap<>();
    private Date updatedAt;
    private Date dateCreated;

    private EndUser() { }

    public EndUser(String sub, Date dateCreated) {
        this.sub = sub;
        this.updatedAt = dateCreated;
        this.dateCreated = dateCreated;
    }

    public String getSub() {
        return sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(String lang) {
        return names.get(lang);
    }

    public void setName(String lang, String name) {
        names.put(lang, name);
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getGivenName(String lang) {
        return givenNames.get(lang);
    }

    public void setGivenName(String lang, String givenName) {
        givenNames.put(lang, givenName);
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName(String lang) {
        return familyNames.get(lang);
    }

    public void setFamilyName(String lang, String familyName) {
        familyNames.put(lang, familyName);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName(String lang) {
        return middleNames.get(lang);
    }

    public void setMiddleName(String lang, String middleName) {
        middleNames.put(lang, middleName);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname(String lang) {
        return nicknames.get(lang);
    }

    public void setNickname(String lang, String nickname) {
        nicknames.put(lang, nickname);
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    public URL getProfile() {
        return profile;
    }

    public void setProfile(URL profile) {
        this.profile = profile;
    }

    public URL getProfile(String lang) {
        return profiles.get(lang);
    }

    public void setProfile(String lang, URL profile) {
        profiles.put(lang, profile);
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public URL getPicture(String lang) {
        return pictures.get(lang);
    }

    public void setPicture(String lang, URL picture) {
        pictures.put(lang, picture);
    }

    public URL getWebsite() {
        return website;
    }

    public void setWebsite(URL website) {
        this.website = website;
    }

    public URL getWebsite(String lang) {
        return websites.get(lang);
    }

    public void setWebsite(String lang, URL website) {
        websites.put(lang, website);
    }

    public InternetAddress getEmail() {
        return email;
    }

    public void setEmail(InternetAddress email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getZoneinfo() {
        return zoneinfo;
    }

    public void setZoneinfo(String zoneinfo) {
        this.zoneinfo = zoneinfo;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberVerified() {
        return phoneNumberVerified;
    }

    public void setPhoneNumberVerified(String phoneNumberVerified) {
        this.phoneNumberVerified = phoneNumberVerified;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress(String lang) {
        return addresses.get(lang);
    }

    public void setAddress(String lang, Address address) {
        addresses.put(lang, address);
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}

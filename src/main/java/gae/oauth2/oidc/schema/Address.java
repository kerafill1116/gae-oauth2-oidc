package gae.oauth2.oidc.schema;

import org.apache.commons.lang3.StringUtils;

public class Address {

    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String country;

    private Address() { }

    public Address(String streetAddress, String locality, String region, String postalCode, String country) {
        this.streetAddress = streetAddress;
        this.locality = locality;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * streetAddress\n
     * locality, region postalCode\n
     * country
     */
    public String getFormatted() {
        StringBuilder formatted = new StringBuilder();
        boolean inbStreetAddress = !StringUtils.isBlank(streetAddress);
        boolean inbLocality = !StringUtils.isBlank(locality);
        boolean inbRegion = !StringUtils.isBlank(region);
        boolean inbPostalCode = !StringUtils.isBlank(postalCode);
        boolean inbCountry = !StringUtils.isBlank(country);

        if(inbStreetAddress)
            formatted.append(streetAddress);
        if(inbStreetAddress && (inbLocality || inbRegion || inbPostalCode || inbCountry))
            formatted.append("\n");
        if(inbLocality)
            formatted.append(locality);
        if(inbLocality && (inbRegion || inbPostalCode))
            formatted.append(", ");
        if(inbRegion)
            formatted.append(region);
        if(inbRegion && inbPostalCode)
            formatted.append(" ");
        if(inbPostalCode)
            formatted.append(postalCode);
        if(formatted.length() > 0 && inbCountry)
            formatted.append("\n");
        if(inbCountry)
            formatted.append(country);

        return formatted.toString();
    }
}

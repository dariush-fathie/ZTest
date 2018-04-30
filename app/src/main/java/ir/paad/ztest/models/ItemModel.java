package ir.paad.ztest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemModel {

    @SerializedName("Centers")
    private List<Center> centers;
    @SerializedName("Work_address")
    private List<Address> addresses;
    @SerializedName("Slideshow")
    private List<Slides> Slides;
    @SerializedName("C_insurance")
    private List<CInsurance> cInsurances;
    @SerializedName("Specialties")
    private List<Specialties> specialties;
    @SerializedName("Levels")
    private List<Level> levels;
    @SerializedName("Insurance")
    private List<Insurance> insurances;

    public List<CInsurance> getCInsurances() {
        return cInsurances;
    }

    public void setCInsurances(List<CInsurance> cInsurances) {
        this.cInsurances = cInsurances;
    }

    public List<Specialties> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialties> specialties) {
        this.specialties = specialties;
    }

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<ItemModel.Slides> getSlides() {
        return Slides;
    }

    public void setSlides(List<ItemModel.Slides> slides) {
        Slides = slides;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    class Center {

        /*"center_id":"1",
         "naCode":"6456",
         "system_num":"554",
         "firstName":"????????????????????????????????",
         "lastName":"fghfgh",
         "reg_date":"2018-04-30",
         "valid_date":"2018-04-30",
         "active":"1",
         "logo_img":null,
         "building_img":null,
         "short_desc":"ghf",
         "bio":"fghfg",
         "equipment":"fgh",
         "service_list":"hgfh",
         "work_team":"hfgh",
         "elc_rec":"1",
         "grade":"55"
         */
        @SerializedName("center_id")
        int id;
        @SerializedName("naCode")
        int naCode;
        @SerializedName("system_num")
        int system_num;
        @SerializedName("firstName")
        String firstName;
        @SerializedName("lastName")
        String lastName;
        @SerializedName("reg_date")
        String regDate;
        @SerializedName("valid_date")
        String validDate;
        @SerializedName("active")
        int active;
        @SerializedName("logo_img")
        String logoUrl;
        @SerializedName("building_img")
        String buildingUrl;
        @SerializedName("short_desc")
        String shortDesc;
        @SerializedName("bio")
        String bio;
        @SerializedName("equipment")
        String equipment;
        @SerializedName("service_list")
        String services;
        @SerializedName("work_team")
        String workTeam;
        @SerializedName("elc_rec")
        String elc_rec;
        @SerializedName("grade")
        int gradle;

    }

    class Address {

    }

    class Slides {

    }

    class Level {

    }

    class Insurance {

    }

    class Specialties {

    }

    class CInsurance {

    }

}

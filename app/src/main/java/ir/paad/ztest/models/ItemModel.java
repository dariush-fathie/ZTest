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



    /*


{
   "Centers":[
      {
         "center_id":"1",
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
      }
   ],
   "Work_address":[
      {
         "center_id":"1",
         "auto_id":"1",
         "loc_title":"????? ???",
         "prov_id":"1",
         "city_id":"1",
         "region":"?????",
         "mainSt":"?????? ????",
         "bySt":"?????? ????",
         "alley":"????",
         "building":"???????",
         "floor":"????",
         "plaque":"????",
         "postal_code":"123456789",
         "tel1":"0873",
         "tel1_desc":"??? ???? 1",
         "tel2":"0543",
         "tel2_desc":"??? ???? 2",
         "mob1":"0918",
         "mob1_desc":"??? ?????? 1",
         "mob2":"0912",
         "mod2_desc":"??? ?????? 2",
         "gen_desc":"??????? ???",
         "default_add":"1",
         "longitude":"1236.2",
         "latitude":"145.3",
         "site":"www",
         "mail":"gmail",
         "sat_desc":"??? ????",
         "sun_desc":"??? 1????",
         "mon_desc":"??? 2????",
         "tues_desc":"??? 3????",
         "wed_desc":"??? 4????",
         "thurs_desc":"??? 5????",
         "fri_desc":"??? ????"
      }
   ],
   "Slideshow":[
      {
         "center_id":"1",
         "auto_id":"1",
         "file_url":"url",
         "description":"???",
         "arrange":"1"
      }
   ],
   "C_insurance":[
      {
         "center_id":"1",
         "auto_id":"1",
         "insurance_id":"2",
         "description":"???"
      }
   ],
   "Specialties":[
      {
         "center_id":"1",
         "specialty_id":"1",
         "name":"???"
      }
   ],
   "Levels":[
      {
         "center_id":"1",
         "level_id":"1",
         "name":"?????"
      }
   ],
   "Insurance":[
      {
         "insurance_id":"2",
         "name":"????? ???????"
      }
   ]
}

     */


}

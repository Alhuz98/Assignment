package AssignmentProject.com;

public class AddressDetails {
    private static String cityName = "";
    private static String stateName = "";
    private static String streetName = "";
    private static String sectionName = "";
    private static String no = "";
    private static int postcode =0;


    public AddressDetails(){
        clearAllData();

    }

    private void clearAllData() {
        cityName = "";
        stateName = "";
        streetName = "";
        sectionName = "";
        no = "";
        postcode =0;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    public void setStreetName(String streetName) {
        if(this.streetName.isEmpty()){
            this.streetName = streetName;

        }
        else{
            this.streetName = this.streetName +" " +streetName;
        }
    }
    public void setSectionName(String sectionName) {
        if(this.sectionName.isEmpty()){
            this.sectionName = sectionName;
        }
        else{

            this.sectionName = this.sectionName +" " +sectionName;
        }
    }
    public void setNumApt(String no) {
        if(this.no.isEmpty()){
            this.no = no;

        }
        else{
            this.no = this.no +" " +no;
        }
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }



    public int getPostcode() {
        return postcode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getNo() {
        return no;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getStreetName() {
        return streetName;
    }
    public String getStateName(){
        return stateName;
    }

}

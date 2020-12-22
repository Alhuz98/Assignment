package AssignmentProject.com;

import java.util.*;


public class ScannerParser {
    private String inputAddress;
//    private String[] tokens;
    private static String cityName = "";
    private static String stateName = "";
    private static String streetName = "";
    private static String sectionName = "";
    private static String no = "";
    private static int postcode =0;
    private Map allData = new HashMap();
//    private String[] tempCity = new String[]{"Kuala Terengganu", "Kuala Lumpur", "Kajang", "Bangi", "Damansara", "Petaling Jaya", "Puchong", "Subang Jaya", "Cyberjaya", "Putrajaya", "Mantin", "Kuching", "Seremban"};
//    private String[] tempState = new String[]{"Selangor", "Terengganu", "Pahang", "Kelantan", "Melaka", "Pulau Pinang", "Kedah", "Johor", "Perlis", "Sabah", "Sarawak"};

    public ScannerParser(String input){
        this.inputAddress = input.toUpperCase();
        String[] tokens = inputAddress.split("[// ,.\n]+");
        setAllData(tokens);
    }

    public void setAllData(String[] tokens){
        List<String> cityList = new ArrayList();
        String[] tempCity = new String[]{"Kuala Terengganu", "Kuala Lumpur", "Kajang", "Bangi", "Damansara", "Petaling Jaya", "Puchong", "Subang Jaya", "Cyberjaya", "Putrajaya", "Mantin", "Kuching", "Seremban"};
        cityList.addAll(Arrays.asList(tempCity));
        cityList.replaceAll(String::toUpperCase);
        ArrayList<String> stateList = new ArrayList();
        String[] tempState = new String[]{"Selangor", "Terengganu", "Pahang", "Kelantan", "Melaka", "Pulau Pinang", "Kedah", "Johor", "Perlis", "Sabah", "Sarawak"};
        stateList.addAll(Arrays.asList(tempState));
        stateList.replaceAll(String::toUpperCase);
        ArrayList<String> jalanList = new ArrayList();
        String[] tempJalan = new String[]{"Jalan", "Jln", "Lorong", "Persiaran"};
        jalanList.addAll(Arrays.asList(tempJalan));
        jalanList.replaceAll(String::toUpperCase);
        boolean skip = false;
        int tag = 0;
        for(int i = 0; i < tokens.length; ++i) {
            if (skip) {
                skip = false;
            } else {
                String chekcString;
                if (!tokens[i].equals("KUALA") && !tokens[i].equals("PETALING") && !tokens[i].equals("SUBANG") && !tokens[i].equals("PULAU")) {
                    chekcString = tokens[i];
                } else {
                    chekcString = tokens[i] + " " + tokens[i + 1];
                    skip = true;
                }

                boolean number = checkNumber(chekcString);
                if ((tokens[i].equals("NO")||tokens[i].equals("NO.")) && tag == 0) {
                    tag = 1;
                    no = no.concat(" " + tokens[i]);
                } else if (tag == 1) {
                    no = no.concat(" " + tokens[i]);
                    tag = 0;
                } else if (number && Integer.parseInt(chekcString) >= 1000 && Integer.parseInt(chekcString) <= 98859) {
                    postcode = Integer.parseInt(chekcString);
                   // System.out.println(postcode + "t");
                    tag = 0;
                } else if (cityList.contains(chekcString) && tag == 0) {
                    cityName = chekcString;
                    tag = 0;
                } else if (stateList.contains(chekcString) && tag == 0) {
                    stateName = chekcString;
                    tag = 0;
                } else if (jalanList.contains(chekcString) && tag == 0) {
                    streetName = chekcString;
                    tag = 2;
                } else if (tag == 2) {
                    if (cityList.contains(chekcString) && cityName == "" && !jalanList.contains(tokens[i - 1])) {
                        cityName = chekcString;
                        tag = 0;
                    } else if (stateList.contains(chekcString) && stateName == "" && !jalanList.contains(tokens[i - 1])) {
                        stateName = chekcString;
                        tag = 0;
                    } else {
                        streetName = streetName + " " + chekcString;
                        tag = 2;
                    }
                } else {
                    sectionName = sectionName + " " + chekcString;
                }
            }
        }
    }
    public boolean checkNumber(String temp) {
        boolean checkResult = true;

        try {
            int var2 = Integer.parseInt(temp);
        } catch (NumberFormatException var3) {
            checkResult = false;
        }

        return checkResult;
    }

    public Map printAll(){
        if(!no.equals("")){
            allData.put("Apt Number",no);
        }

        if(!cityName.equals("")){
            allData.put("City",cityName);
        }
        if(!stateName.equals("")){
            allData.put("State",stateName);
        }
        if(postcode!=0){
            allData.put("Postcode",postcode);
        }

        if(!streetName.equals("")){
            allData.put("Street",streetName);
        } if(!sectionName.equals("")){
            allData.put("Section",sectionName);
        }

        return allData;
    }
    public void removeData(){
        this.cityName = "";
        this.stateName = "";
        this.streetName = "";
        this.sectionName = "";
        this.no = "";
        this.postcode =0;
    }

}

package AssignmentProject.com;

import java.util.*;

public class ScannerAddress {
    private List<String> inputAddress = new ArrayList();
    private AddressDetails addressDetails;
    private ArrayList<Integer> indexRemoval = new ArrayList();
    private Map mappingData = new HashMap();


    public ScannerAddress(String input){
        addressDetails = new AddressDetails();
        String[] tokens = input.toUpperCase().split("[// ,.\n]+");
        inputAddress.addAll(Arrays.asList(tokens));
        setInputAddressDetails(inputAddress);
        identifyData();
    }

    public void identifyData() {


        searchStreetName(this.inputAddress);


        searchNumberApt(this.inputAddress);


        searchPostCode(this.inputAddress);


        searchCityName(this.inputAddress);


        searchStateName(this.inputAddress);

        searchSectionName(this.inputAddress);


    }


    public Map printAll(){
        if(!addressDetails.getNo().equals("")){
            mappingData.put("Apt Number",addressDetails.getNo());
        }
        if(!addressDetails.getCityName().equals("")){
            mappingData.put("City",addressDetails.getCityName());
        }
        if(!addressDetails.getStateName().equals("")){
            mappingData.put("State",addressDetails.getStateName());
        }
        if(addressDetails.getPostcode()!=0){
            mappingData.put("Postcode",addressDetails.getPostcode());
        }
        if(!addressDetails.getStreetName().equals("")){
            mappingData.put("Street",addressDetails.getStreetName());
        } if(!addressDetails.getSectionName().equals("")){
            mappingData.put("Section",addressDetails.getSectionName());
        }

        return mappingData;
    }

    public void searchNumberApt(List<String> inputAddress){
        if(!inputAddress.isEmpty()){
            for(int i =0; i<inputAddress.size();i++){
                if(inputAddress.get(i).equals("NO")||inputAddress.get(i).equals("NO.")){
                    addressDetails.setNumApt(inputAddress.get(i)+" "+inputAddress.get(i+1));
                    indexRemoval.add(i);
                    indexRemoval.add(i+1);
                }
            }
            removeIndex(indexRemoval);
        }
    }



    public void searchPostCode(List<String> inputAddress){
        if(!inputAddress.isEmpty()){
            for(int i =0; i<inputAddress.size();i++){
                if(containsPostCode(inputAddress.get(i))){
                    addressDetails.setPostcode(Integer.parseInt(inputAddress.get(i)));
                    indexRemoval.add(i);
                }
            }
            removeIndex(indexRemoval);
        }

    }

    public void searchCityName(List<String> inputAddress){
        boolean checkRemove = false;
        if(!inputAddress.isEmpty()) {
            for (int i = 0; i < inputAddress.size(); i++) {
                if (containsCity(inputAddress.get(i))) {
                    addressDetails.setCityName(inputAddress.get(i));
                    indexRemoval.add(i);
                    checkRemove = true;
                }
            }
            if(checkRemove){
                removeIndex(indexRemoval);
            }

        }
    }

    public void searchStateName(List<String> inputAddress){
        boolean checkRemove = false;
        if(!inputAddress.isEmpty()) {
            for (int i = 0; i < inputAddress.size(); i++) {
                if (containsState(inputAddress.get(i))) {
                    addressDetails.setStateName(inputAddress.get(i));
                    indexRemoval.add(i);
                    checkRemove = true;
                }
            }
            if(checkRemove){
                removeIndex(indexRemoval);
            }
        }
    }

    public void searchStreetName(List<String> inputAddress){
        boolean checkRemove = false;
        if(!inputAddress.isEmpty()) {
            ArrayList<String> jalanList = new ArrayList();
            String[] tempJalan = new String[]{"Jalan", "Jln", "Lorong", "Persiaran"};
            jalanList.addAll(Arrays.asList(tempJalan));
            jalanList.replaceAll(String :: toUpperCase);
            int tag = 0;

            for (int i = 0; i < inputAddress.size(); i++) {
                if (jalanList.contains(inputAddress.get(i)) && tag == 0) {
                    tag = 2;
                    addressDetails.setStreetName(inputAddress.get(i));
                    indexRemoval.add(i);
                    checkRemove = true;
                } else if (tag == 2) {
                    if (containsCity(inputAddress.get(i)) && addressDetails.getCityName().equals("") && addressDetails.getStreetName().contains(inputAddress.get(i))) {
                        tag = 0;
                    } else if (containsState(inputAddress.get(i)) && addressDetails.getStateName().equals("") && addressDetails.getStreetName().contains(inputAddress.get(i))) {
                         tag = 0;
                    } else if (containsPostCode(inputAddress.get(i))) {
                        tag = 0;
                    } else {
                        addressDetails.setStreetName(inputAddress.get(i));
                        indexRemoval.add(i);
                        checkRemove = true;
                        tag = 2;
                    }
                }
            }
            if(checkRemove){
                removeIndex(indexRemoval);
            }
        }
    }

    public void searchSectionName(List<String> inputAddress){
        boolean checkRemove = false;
        if(!inputAddress.isEmpty()) {
            for (int i = 0; i < inputAddress.size(); i++) {
                addressDetails.setSectionName(inputAddress.get(i));
                indexRemoval.add(i);
                checkRemove = true;
            }
            if(checkRemove){
                removeIndex(indexRemoval);
            }
        }
    }




    public boolean  containsCity(String inputAddress){
        List<String> cityList = new ArrayList();
        String[] tempCity = new String[]{"Kuala Terengganu", "Kuala Lumpur", "Kajang", "Bangi", "Damansara", "Petaling Jaya", "Puchong", "Subang Jaya", "Cyberjaya", "Putrajaya", "Mantin", "Kuching", "Seremban"};
        cityList.addAll(Arrays.asList(tempCity));
        cityList.replaceAll(String::toUpperCase);
        boolean checked = false;
        if (cityList.contains(inputAddress)) {
                checked = true;
        }
        return checked;
    }

    public boolean  containsState(String inputAddress){
        ArrayList<String> stateList = new ArrayList();
        String[] tempState = new String[]{"Selangor", "Terengganu", "Pahang", "Kelantan", "Melaka", "Pulau Pinang", "Kedah", "Johor", "Perlis", "Sabah", "Sarawak"};
        stateList.addAll(Arrays.asList(tempState));
        stateList.replaceAll(String::toUpperCase);
        boolean checked = false;
        if (stateList.contains(inputAddress)) {
            checked = true;
        }
        return checked;
    }

    public boolean containsPostCode(String inputAddress){
        boolean checked;
        if((checkNumber(inputAddress) && (Integer.parseInt(inputAddress) >= 1000 && Integer.parseInt(inputAddress) <= 98859))){
            checked = true;
        }
        else{
            checked = false;
        }
        return checked;
    }

    public void setInputAddressDetails(List<String> inputAddress){
        for(int i =0; i<inputAddress.size();i++){
            if (inputAddress.get(i).equals("KUALA") || inputAddress.get(i).equals("PETALING") || inputAddress.get(i).equals("SUBANG") || inputAddress.get(i).equals("PULAU")){
                String temp = inputAddress.get(i) + " " +inputAddress.get(i + 1);
                this.inputAddress.set(i,temp);
                this.inputAddress.remove((i+1));
            }
            else{
                continue;
            }
        }
    }

    public void removeIndex(ArrayList<Integer> indexRemoval) {
        for(int i =0; i<indexRemoval.size();i++){
            int k = indexRemoval.get(i);

                k = k -i;
            if(k==inputAddress.size()){
                continue;
            }else{
                this.inputAddress.remove(k);
            }

        }
        this.indexRemoval.clear();

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
}
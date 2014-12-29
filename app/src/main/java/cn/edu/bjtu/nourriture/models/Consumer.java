package cn.edu.bjtu.nourriture.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Pavel Procházka on 26/12/14.
 */
public class Consumer {

    // Constants definition
    public static final String CONSUMER_USERNAME       = "username";
    public static final String CONSUMER_NAME           = "name";
    public static final String CONSUMER_PICTURE        = "picture";
    public static final String CONSUMER_OCCUPATION     = "occupation";
    public static final String CONSUMER_BIRTHDATE      = "birthdate";
    public static final String CONSUMER_WEBSITE        = "website";
    public static final String CONSUMER_BIO            = "bio";
    public static final String CONSUMER_EMAIL          = "email";
    public static final String CONSUMER_GENDER         = "gender";
    
    // Properties
    private String  consumerUsername;    //ID
    private String  consumerName;
    private String  consumerPicture;
    private String  consumerOccupation;
    private Date    consumerBirthdate;
    private String  consumerWebsite;
    private String  consumerBio;
    private String  consumerEmail;      //not used anywhere yet
    private int     consumerGender;     // 0 = not assigned, 1 = woman, 2 = man
    private ArrayList<HashMap> consumerInfoToDisplay = null;   //consumer may provide different amount of information, therefore we return only the provided one

    public String getConsumerWebsite() {
        return consumerWebsite;
    }

    public void setConsumerWebsite(String consumerWebsite) {
        this.consumerWebsite = consumerWebsite;
    }

    public String getConsumerUsername() {
        return consumerUsername;
    }

    public void setConsumerUsername(String consumerUsername) {
        this.consumerUsername = consumerUsername;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerEmail() {
        return consumerEmail;
    }

    public void setConsumerEmail(String consumerEmail) {
        this.consumerEmail = consumerEmail;
    }

    public String getConsumerPicture() {
        return consumerPicture;
    }

    public void setConsumerPicture(String consumerPicture) {
        this.consumerPicture = consumerPicture;
    }

    public String getConsumerOccupation() {
        return consumerOccupation;
    }

    public void setConsumerOccupation(String consumerOccupation) {
        this.consumerOccupation = consumerOccupation;
    }

    public String getConsumerBio() {
        return consumerBio;
    }

    public void setConsumerBio(String consumerBio) {
        this.consumerBio = consumerBio;
    }

    public int getConsumerGender() {
        return consumerGender;
    }

    public void setConsumerGender(int consumerGender) {
        this.consumerGender = consumerGender;
    }

    public Date getConsumerBirthdate() {
        return consumerBirthdate;
    }

    public void setConsumerBirthdate(Date consumerBirthdate) {
        this.consumerBirthdate = consumerBirthdate;
    }

    /**
     *
     * @return array list of hashmaps -> each consisting of attribute name and its value
     */
   public ArrayList<HashMap> getConsumerInfoToDisplay(){

       if (consumerInfoToDisplay == null){
           consumerInfoToDisplay = findAvailableInfo();
       }

       return consumerInfoToDisplay;
    }

    /**
     * Sets the order of items!!!
     * @return
     */
    private ArrayList findAvailableInfo(){
        ArrayList<HashMap> result = new ArrayList<>();

        if (this.consumerPicture != null && !this.consumerPicture.isEmpty()){
            HashMap picDic = new HashMap();
            picDic.put(Consumer.CONSUMER_PICTURE, this.consumerPicture);
            result.add(picDic);
        }

        /*if (this.consumerUsername != null && !this.consumerUsername.isEmpty()){
            HashMap usernameDic = new HashMap();
            usernameDic.put(Consumer.CONSUMER_USERNAME, this.consumerUsername);
            result.add(usernameDic);
        }*/

        if (this.consumerName != null && !this.consumerName.isEmpty()){
            HashMap nameDic = new HashMap();
            nameDic.put(Consumer.CONSUMER_NAME, this.consumerName);
            result.add(nameDic);
        }

        if (this.consumerOccupation != null && !this.consumerOccupation.isEmpty()){
            HashMap occupationDic = new HashMap();
            occupationDic.put(Consumer.CONSUMER_OCCUPATION, this.consumerOccupation);
            result.add(occupationDic);
        }

        if (this.consumerBirthdate != null && this.consumerBirthdate != null){
            HashMap birthdateDic = new HashMap();
            birthdateDic.put(Consumer.CONSUMER_BIRTHDATE, this.consumerBirthdate.toString());
            result.add(birthdateDic);
        }

        if (this.consumerWebsite != null && !this.consumerWebsite.isEmpty()){
            HashMap websiteDic = new HashMap();
            websiteDic.put(Consumer.CONSUMER_WEBSITE, this.consumerWebsite);
            result.add(websiteDic);
        }

        if (this.consumerBio != null && !this.consumerBio.isEmpty()){
            HashMap bioDic = new HashMap();
            bioDic.put(Consumer.CONSUMER_BIO, this.consumerBio);
            result.add(bioDic);
        }

        /*if (this.consumerEmail != null && !this.consumerEmail.isEmpty()){
            HashMap emailDic = new HashMap();
            emailDic.put(Consumer.CONSUMER_EMAIL, this.consumerEmail);
            result.add(emailDic);
        }

        if (this.consumerGender != 0){
            HashMap genderDic = new HashMap();
            genderDic.put(Consumer.CONSUMER_GENDER, "" + this.consumerGender);
            result.add(genderDic);
        }*/

        return result;
    }
}

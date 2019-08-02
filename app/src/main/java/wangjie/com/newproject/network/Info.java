package wangjie.com.newproject.network;

/**
 * Created by Administrator on 2018/5/9.
 */

public class Info {

    private String idNumber = "13022358";
    private String agedCardNumber  = "6221385";
    private String subsidyRuleId = "2";
    private String namel = "赵六";
    private String sex = "0";
    private String nation = "汉";
    private String birthDate = "1997-05-09";
    private String idAddress = "北京市";
    private String location = "迎风街道办";
    private String operator = "zhuxuan";
    private String mobilePhone = "186176";
    private String community = "1";
    private String classify = "0";
    private String street = "所属街道";
    private String image = "加密后图片";
    private String agent  = "代理人" ;
    private String agentIdNumber = "1306271995";

    public Info(String image) {
        this.image = image;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAgedCardNumber() {
        return agedCardNumber;
    }

    public void setAgedCardNumber(String agedCardNumber) {
        this.agedCardNumber = agedCardNumber;
    }

    public String getSubsidyRuleId() {
        return subsidyRuleId;
    }

    public void setSubsidyRuleId(String subsidyRuleId) {
        this.subsidyRuleId = subsidyRuleId;
    }

    public String getNamel() {
        return namel;
    }

    public void setNamel(String namel) {
        this.namel = namel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgentIdNumber() {
        return agentIdNumber;
    }

    public void setAgentIdNumber(String agentIdNumber) {
        this.agentIdNumber = agentIdNumber;
    }

    @Override
    public String toString() {
        return "Info{" +
                "idNumber='" + idNumber + '\'' +
                ", agedCardNumber='" + agedCardNumber + '\'' +
                ", subsidyRuleId='" + subsidyRuleId + '\'' +
                ", namel='" + namel + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", idAddress='" + idAddress + '\'' +
                ", location='" + location + '\'' +
                ", operator='" + operator + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", community='" + community + '\'' +
                ", classify='" + classify + '\'' +
                ", street='" + street + '\'' +
                ", image='" + image + '\'' +
                ", agent='" + agent + '\'' +
                ", agentIdNumber='" + agentIdNumber + '\'' +
                '}';
    }

}

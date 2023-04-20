package fdc;

public class Labelgenerator {
	private String company_name;
	private String qr_code;
	private String tracking_number;
	private String country_code;
	private String phone_number;
	private String postal_code;
	private String state_province;
	private String city;
	private String line1;
	private String last_name;
	private String first_name;
	private String country_code_1;
	private String sms;
	private String postal_code_1;
	private String state_province_1;
	private String city_1;
	private String line1_1;

	public Labelgenerator(String company_name, String line1_1, String city_1, String state_province_1,
			String postal_code_1, String sms, String country_code_1, String first_name, String last_name, String line1,
			String city, String state_province, String postal_code, String phone_number, String country_code,
			String tracking_number, String qr_code) {
		// TODO Auto-generated constructor stub
		this.company_name = company_name;
		this.line1_1 = line1_1;
		this.city_1 = city_1;
		this.state_province_1 = state_province_1;
		this.postal_code_1 = postal_code_1;
		this.sms = sms;
		this.country_code_1 = country_code_1;
		this.first_name = first_name;
		this.last_name = last_name; 
		this.line1 = line1;
		this.city = city;
		this.state_province = state_province;
		this.postal_code = postal_code; 
		this.phone_number = phone_number;
		this.country_code = country_code;
		this.tracking_number = tracking_number;
		this.qr_code = qr_code;
				
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getQr_code() {
		return qr_code;
	}

	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getState_province() {
		return state_province;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getCountry_code_1() {
		return country_code_1;
	}

	public void setCountry_code_1(String country_code_1) {
		this.country_code_1 = country_code_1;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getPostal_code_1() {
		return postal_code_1;
	}

	public void setPostal_code_1(String postal_code_1) {
		this.postal_code_1 = postal_code_1;
	}

	public String getState_province_1() {
		return state_province_1;
	}

	public void setState_province_1(String state_province_1) {
		this.state_province_1 = state_province_1;
	}

	public String getCity_1() {
		return city_1;
	}

	public void setCity_1(String city_1) {
		this.city_1 = city_1;
	}

	public String getLine1_1() {
		return line1_1;
	}

	public void setLine1_1(String line1_1) {
		this.line1_1 = line1_1;
	}

}
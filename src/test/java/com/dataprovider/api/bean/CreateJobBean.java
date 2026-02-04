package com.dataprovider.api.bean;

import com.opencsv.bean.CsvBindByName;
import com.poiji.annotation.ExcelCellName;

public class CreateJobBean {
	
	@ExcelCellName("mst_service_location_id")
	@CsvBindByName(column = "mst_service_location_id")
	private String mst_service_location_id;
	
	@ExcelCellName("mst_platform_id")
	@CsvBindByName(column = "mst_platform_id")
	private String mst_platform_id;
	
	@ExcelCellName("mst_warrenty_status_id")
	@CsvBindByName(column = "mst_warrenty_status_id")
	private String mst_warrenty_status_id;
	
	@ExcelCellName("mst_oem_id")
	@CsvBindByName(column = "mst_oem_id")
	private String mst_oem_id;
	
	@ExcelCellName("customer__first_name")
	@CsvBindByName(column = "customer__first_name")
	private String customer__first_name;
	
	@ExcelCellName("customer__last_name")
	@CsvBindByName(column = "customer__last_name")
	private String customer__last_name;
	
	@ExcelCellName("customer__mobile_number")
	@CsvBindByName(column = "customer__mobile_number")
	private String customer__mobile_number;
	
	@ExcelCellName("customer__mobile_number_alt")
	@CsvBindByName(column = "customer__mobile_number_alt")
	private String customer__mobile_number_alt;
	
	@ExcelCellName("customer__email_id")
	@CsvBindByName(column = "customer__email_id")
	private String customer__email_id;
	
	@ExcelCellName("customer__email_id_alt")
	@CsvBindByName(column = "customer__email_id_alt")
	private String customer__email_id_alt;
	
	@ExcelCellName("customer_address__flat_number")
	@CsvBindByName(column = "customer_address__flat_number")
	private String customer_address__flat_number;
	
	@ExcelCellName("customer_address__apartment_name")
	@CsvBindByName(column = "customer_address__apartment_name")
	private String customer_address__apartment_name;
	
	@ExcelCellName("customer_address__street_name")
	@CsvBindByName(column = "customer_address__street_name")
	private String customer_address__street_name;
	
	@ExcelCellName("customer_address__landmark")
	@CsvBindByName(column = "customer_address__landmark")
	private String customer_address__landmark;
	
	@ExcelCellName("customer_address__area")
	@CsvBindByName(column = "customer_address__area")
	private String customer_address__area;
	
	@ExcelCellName("customer_address__pincode")
	@CsvBindByName(column = "customer_address__pincode")
	private String customer_address__pincode;
	
	@ExcelCellName("customer_address__country")
	@CsvBindByName(column = "customer_address__country")
	private String customer_address__country;
	
	@ExcelCellName("customer_address__state")
	@CsvBindByName(column = "customer_address__state")
	private String customer_address__state;
	
	@ExcelCellName("customer_product__dop")
	@CsvBindByName(column = "customer_product__dop")
	private String customer_product__dop;
	
	@ExcelCellName("customer_product__serial_number")
	@CsvBindByName(column = "customer_product__serial_number")
	private String customer_product__serial_number;
	
	@ExcelCellName("customer_product__imei1")
	@CsvBindByName(column = "customer_product__imei1")
	private String customer_product__imei1;
	
	@ExcelCellName("customer_product__imei2")
	@CsvBindByName(column = "customer_product__imei2")
	private String customer_product__imei2;
	
	@ExcelCellName("customer_product__popurl")
	@CsvBindByName(column = "customer_product__popurl")
	private String customer_product__popurl;
	
	@ExcelCellName("customer_product__product_id")
	@CsvBindByName(column = "customer_product__product_id")
	private String customer_product__product_id;
	
	@ExcelCellName("customer_product__mst_model_id")
	@CsvBindByName(column = "customer_product__mst_model_id")
	private String customer_product__mst_model_id;
	
	@ExcelCellName("problems__id")
	@CsvBindByName(column = "problems__id")
	private String problems__id;
	
	@ExcelCellName("problems__remark")
	@CsvBindByName(column = "problems__remark")
	private String problems__remark;
	
	public CreateJobBean() {
	}

	public String getMst_service_location_id() {
		return mst_service_location_id;
	}

	public void setMst_service_location_id(String mst_service_location_id) {
		this.mst_service_location_id = mst_service_location_id;
	}

	public String getMst_platform_id() {
		return mst_platform_id;
	}

	public void setMst_platform_id(String mst_platform_id) {
		this.mst_platform_id = mst_platform_id;
	}

	public String getMst_warrenty_status_id() {
		return mst_warrenty_status_id;
	}

	public void setMst_warrenty_status_id(String mst_warrenty_status_id) {
		this.mst_warrenty_status_id = mst_warrenty_status_id;
	}

	public String getMst_oem_id() {
		return mst_oem_id;
	}

	public void setMst_oem_id(String mst_oem_id) {
		this.mst_oem_id = mst_oem_id;
	}

	public String getCustomer__first_name() {
		return customer__first_name;
	}

	public void setCustomer__first_name(String customer__first_name) {
		this.customer__first_name = customer__first_name;
	}

	public String getCustomer__last_name() {
		return customer__last_name;
	}

	public void setCustomer__last_name(String customer__last_name) {
		this.customer__last_name = customer__last_name;
	}

	public String getCustomer__mobile_number() {
		return customer__mobile_number;
	}

	public void setCustomer__mobile_number(String customer__mobile_number) {
		this.customer__mobile_number = customer__mobile_number;
	}

	public String getCustomer__mobile_number_alt() {
		return customer__mobile_number_alt;
	}

	public void setCustomer__mobile_number_alt(String customer__mobile_number_alt) {
		this.customer__mobile_number_alt = customer__mobile_number_alt;
	}

	public String getCustomer__email_id() {
		return customer__email_id;
	}

	public void setCustomer__email_id(String customer__email_id) {
		this.customer__email_id = customer__email_id;
	}

	public String getCustomer__email_id_alt() {
		return customer__email_id_alt;
	}

	public void setCustomer__email_id_alt(String customer__email_id_alt) {
		this.customer__email_id_alt = customer__email_id_alt;
	}

	public String getCustomer_address__flat_number() {
		return customer_address__flat_number;
	}

	public void setCustomer_address__flat_number(String customer_address__flat_number) {
		this.customer_address__flat_number = customer_address__flat_number;
	}

	public String getCustomer_address__apartment_name() {
		return customer_address__apartment_name;
	}

	public void setCustomer_address__apartment_name(String customer_address__apartment_name) {
		this.customer_address__apartment_name = customer_address__apartment_name;
	}

	public String getCustomer_address__street_name() {
		return customer_address__street_name;
	}

	public void setCustomer_address__street_name(String customer_address__street_name) {
		this.customer_address__street_name = customer_address__street_name;
	}

	public String getCustomer_address__landmark() {
		return customer_address__landmark;
	}

	public void setCustomer_address__landmark(String customer_address__landmark) {
		this.customer_address__landmark = customer_address__landmark;
	}

	public String getCustomer_address__area() {
		return customer_address__area;
	}

	public void setCustomer_address__area(String customer_address__area) {
		this.customer_address__area = customer_address__area;
	}

	public String getCustomer_address__pincode() {
		return customer_address__pincode;
	}

	public void setCustomer_address__pincode(String customer_address__pincode) {
		this.customer_address__pincode = customer_address__pincode;
	}

	public String getCustomer_address__country() {
		return customer_address__country;
	}

	public void setCustomer_address__country(String customer_address__country) {
		this.customer_address__country = customer_address__country;
	}

	public String getCustomer_address__state() {
		return customer_address__state;
	}

	public void setCustomer_address__state(String customer_address__state) {
		this.customer_address__state = customer_address__state;
	}

	public String getCustomer_product__dop() {
		return customer_product__dop;
	}

	public void setCustomer_product__dop(String customer_product__dop) {
		this.customer_product__dop = customer_product__dop;
	}

	public String getCustomer_product__serial_number() {
		return customer_product__serial_number;
	}

	public void setCustomer_product__serial_number(String customer_product__serial_number) {
		this.customer_product__serial_number = customer_product__serial_number;
	}

	public String getCustomer_product__imei1() {
		return customer_product__imei1;
	}

	public void setCustomer_product__imei1(String customer_product__imei1) {
		this.customer_product__imei1 = customer_product__imei1;
	}

	public String getCustomer_product__imei2() {
		return customer_product__imei2;
	}

	public void setCustomer_product__imei2(String customer_product__imei2) {
		this.customer_product__imei2 = customer_product__imei2;
	}

	public String getCustomer_product__popurl() {
		return customer_product__popurl;
	}

	public void setCustomer_product__popurl(String customer_product__popurl) {
		this.customer_product__popurl = customer_product__popurl;
	}

	public String getCustomer_product__product_id() {
		return customer_product__product_id;
	}

	public void setCustomer_product__product_id(String customer_product__product_id) {
		this.customer_product__product_id = customer_product__product_id;
	}

	public String getCustomer_product__mst_model_id() {
		return customer_product__mst_model_id;
	}

	public void setCustomer_product__mst_model_id(String customer_product__mst_model_id) {
		this.customer_product__mst_model_id = customer_product__mst_model_id;
	}

	public String getProblems__id() {
		return problems__id;
	}

	public void setProblems__id(String problems__id) {
		this.problems__id = problems__id;
	}

	public String getProblems__remark() {
		return problems__remark;
	}

	public void setProblems__remark(String problems__remark) {
		this.problems__remark = problems__remark;
	}

	@Override
	public String toString() {
		return "CreateJobBean [mst_service_location_id=" + mst_service_location_id + ", mst_platform_id="
				+ mst_platform_id + ", mst_warrenty_status_id=" + mst_warrenty_status_id + ", mst_oem_id=" + mst_oem_id
				+ ", customer__first_name=" + customer__first_name + ", customer__last_name=" + customer__last_name
				+ ", customer__mobile_number=" + customer__mobile_number + ", customer__mobile_number_alt="
				+ customer__mobile_number_alt + ", customer__email_id=" + customer__email_id
				+ ", customer__email_id_alt=" + customer__email_id_alt + ", customer_address__flat_number="
				+ customer_address__flat_number + ", customer_address__apartment_name="
				+ customer_address__apartment_name + ", customer_address__street_name=" + customer_address__street_name
				+ ", customer_address__landmark=" + customer_address__landmark + ", customer_address__area="
				+ customer_address__area + ", customer_address__pincode=" + customer_address__pincode
				+ ", customer_address__country=" + customer_address__country + ", customer_address__state="
				+ customer_address__state + ", customer_product__dop=" + customer_product__dop
				+ ", customer_product__serial_number=" + customer_product__serial_number + ", customer_product__imei1="
				+ customer_product__imei1 + ", customer_product__imei2=" + customer_product__imei2
				+ ", customer_product__popurl=" + customer_product__popurl + ", customer_product__product_id="
				+ customer_product__product_id + ", customer_product__mst_model_id=" + customer_product__mst_model_id
				+ ", problems__id=" + problems__id + ", problems__remark=" + problems__remark + "]";
	}
	
	

}

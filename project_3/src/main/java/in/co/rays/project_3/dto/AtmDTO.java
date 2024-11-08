package in.co.rays.project_3.dto;

import java.util.Date;

import in.co.rays.project_3.util.DataUtility;

public class AtmDTO extends BaseDTO {

	private String location;
	private Double cashavailable;
	private Date dob;
	private String remark;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getCashavailable() {
		return cashavailable;
	}

	public void setCashavailable(Double cashavailable) {
		this.cashavailable = cashavailable;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return DataUtility.getDateString(dob);
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return DataUtility.getDateString(dob);
	}

}

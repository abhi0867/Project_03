package in.co.rays.project_3.dto;

public class BusDTO extends BaseDTO {

	private Integer busNo;
	private Double busId;
	private Long km;

	public Integer getBusNo() {
		return busNo;
	}

	public void setBusNo(Integer busNo) {
		this.busNo = busNo;
	}

	public Double getBusId() {
		return busId;
	}

	public void setBusId(Double busId) {
		this.busId = busId;
	}

	public Long getKm() {
		return km;
	}

	public void setKm(Long km) {
		this.km = km;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}

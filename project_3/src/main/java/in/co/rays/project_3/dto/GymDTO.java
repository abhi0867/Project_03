package in.co.rays.project_3.dto;

import java.util.Date;

public class GymDTO extends BaseDTO {
	
	private String GymName;
	private Date JoningDate;
	private String tranerName;
    private String time;
	

	public String getGymName() {
		return GymName;
	}

	public void setGymName(String gymName) {
		GymName = gymName;
	}

	public Date getJoningDate() {
		return JoningDate;
	}

	public void setJoningDate(Date joningDate) {
		JoningDate = joningDate;
	}

	public String getTranerName() {
		return tranerName;
	}

	public void setTranerName(String tranerName) {
		this.tranerName = tranerName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

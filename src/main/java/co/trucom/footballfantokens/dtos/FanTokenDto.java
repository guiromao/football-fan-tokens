package co.trucom.footballfantokens.dtos;

public class FanTokenDto {

	private String id;
	private String club;
	private Double price;

	public FanTokenDto() {
	}

	public FanTokenDto(String id, String club, Double value) {
		this.id = id;
		this.club = club;
		this.price = value;
	}

	public String toString() {
		return new String("[Id=" + id + ", Club=" + club + ", Price=" + price + "]");
	}

	public String getId() {
		return id;
	}

	public String getClub() {
		return club;
	}

	public Double getPrice() {
		return price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public void setPrice(Double value) {
		this.price = value;
	}

}

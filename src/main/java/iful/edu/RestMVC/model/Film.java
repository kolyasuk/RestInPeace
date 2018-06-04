package iful.edu.RestMVC.model;

public class Film {

	private int id;

	private String name;

	private int year;

	private String genre;

	private String director;

	private String country;

	private String description;

	private String movie_length;

	private byte[] image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMovie_length() {
		return movie_length;
	}

	public void setMovie_length(String movie_length) {
		this.movie_length = movie_length;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + " " + name + " " + year + " " + genre + " " + director + " " + country + " " + description + " " + movie_length + " " + image;
	}

}

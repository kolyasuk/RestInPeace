package iful.edu.RestMVC.model;

import java.util.Date;

public class CinemaSession {

	private int id;
	private int film_id;
	private String film_name;
	private int cinema_id;
	private String cinema_name;
	private int hall_id;
	private String hall_name;
	private Date show_date;
	private String show_time;
	private int ticket_price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFilm_id() {
		return film_id;
	}

	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}

	public int getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}

	public Date getShow_date() {
		return show_date;
	}

	public void setShow_date(Date show_date) {
		this.show_date = show_date;
	}

	public String getShow_time() {
		return show_time;
	}

	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}

	public int getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(int ticket_price) {
		this.ticket_price = ticket_price;
	}

	public String getFilm_name() {
		return film_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	public String getCinema_name() {
		return cinema_name;
	}

	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}

	public String getHall_name() {
		return hall_name;
	}

	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}

	public int getHall_id() {
		return hall_id;
	}

	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}

}

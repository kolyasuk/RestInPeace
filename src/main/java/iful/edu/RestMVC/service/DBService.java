package iful.edu.RestMVC.service;

import java.util.List;

import iful.edu.RestMVC.model.Cinema;
import iful.edu.RestMVC.model.CinemaSession;
import iful.edu.RestMVC.model.Film;
import iful.edu.RestMVC.model.Hall;

public interface DBService {
	Film getFilmByID(int id);

	Hall getHallByID(int id);

	Cinema getCinemaByID(int id);

	List<Film> getFilmList();

	List<Hall> getHallList();

	List<CinemaSession> getCinemaSessions(boolean selectType, int pageEmount);

	List<Cinema> getCinemaList();

	void inputFilm(Film film);

	void inputHall(Hall hall);

	void inputSession(CinemaSession session);

	void inputCinema(Cinema cinema);

	void updateCinema(Cinema cinema);

	void updateSession(CinemaSession session);

	void updateFilm(Film film);

	void updateHall(Hall hall);

	void deleteCinemaById(int id);

	void deleteFilmById(int id);

	void deleteSessionById(int id);

	void deleteHallById(int id);

}

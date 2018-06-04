package iful.edu.RestMVC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import iful.edu.RestMVC.model.Film;
import iful.edu.RestMVC.service.DBService;

@RestController
@RequestMapping("rest")
public class SpringRestController {

	@Autowired
	DBService dbService;

	@RequestMapping(value = "/checkStrength/{id}", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
	public @ResponseBody String checkStrength(@PathVariable("id") int id) {
		return "test" + id;
	}

	@RequestMapping(value = "/film", method = RequestMethod.GET)
	public ResponseEntity<List<Film>> listAllFilms() {
		List<Film> films = dbService.getFilmList();
		if (films.isEmpty()) {
			return new ResponseEntity<List<Film>>(HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
	}

	@RequestMapping(value = "/film/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> getFilm(@PathVariable("id") int id) {
		Film film = dbService.getFilmByID(id);

		if (film == null) {
			return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Film>(film, HttpStatus.OK);
	}

	@RequestMapping(value = "/film", method = RequestMethod.POST)
	public void createFilm(@RequestBody Film film) {
		dbService.inputFilm(film);
	}

	@RequestMapping(value = "/film/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Film> updateFilm(@PathVariable("id") int id, @RequestBody Film film) {
		Film currentFilm = dbService.getFilmByID(id);
		if (currentFilm == null) {
			return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
		}
		currentFilm.setName(film.getName());
		currentFilm.setYear(film.getYear());
		currentFilm.setDirector(film.getDirector());
		currentFilm.setCountry(film.getCountry());
		currentFilm.setMovie_length(film.getMovie_length());
		currentFilm.setDescription(film.getDescription());
		currentFilm.setImage(film.getImage());
		currentFilm.setGenre(film.getGenre());

		dbService.updateFilm(currentFilm);
		return new ResponseEntity<Film>(currentFilm, HttpStatus.OK);
	}

	@RequestMapping(value = "/film/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Film> deleteFilm(@PathVariable("id") int id) {

		Film film = dbService.getFilmByID(id);
		if (film == null) {
			return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
		}

		dbService.deleteFilmById(id);
		return new ResponseEntity<Film>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "calc", method = RequestMethod.POST)
	public ResponseEntity<Integer> calc(@RequestParam("n1") String n1, @RequestParam("n2") String n2) {
		return new ResponseEntity<Integer>(Integer.valueOf(n1) + Integer.valueOf(n2), HttpStatus.OK);
	}

}

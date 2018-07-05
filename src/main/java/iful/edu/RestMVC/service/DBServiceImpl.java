package iful.edu.RestMVC.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iful.edu.RestMVC.model.Cinema;
import iful.edu.RestMVC.model.CinemaSession;
import iful.edu.RestMVC.model.Film;
import iful.edu.RestMVC.model.Hall;
import iful.edu.RestMVC.model.User;
import iful.edu.RestMVC.utils.DBUtility;
import iful.edu.RestMVC.utils.PaginationUtils;

@Service("userService")
@Transactional
public class DBServiceImpl implements DBService {

	private NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(DBUtility.getDataSource());

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();

		return users;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public Cinema getCinemaByID(int id) {
		String sql = "select * from cinema where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);
		return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<Cinema>() {
			@Override
			public Cinema mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cinema cinema = new Cinema();
				cinema.setId(rs.getInt("id"));
				cinema.setName(rs.getString("name"));
				cinema.setImage(rs.getBytes("image"));
				cinema.setAddress(rs.getString("address"));
				cinema.setDescription(rs.getString("description"));
				cinema.setSite(rs.getString("site"));
				cinema.setTelephone(rs.getString("telephone"));
				return cinema;
			}
		});
	}

	@Override
	public Film getFilmByID(int id) {
		String sql = "select * from film where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);

		return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<Film>() {

			@Override
			public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setName(rs.getString("name"));
				film.setYear(rs.getInt("year"));
				film.setDirector(rs.getString("director"));
				film.setCountry(rs.getString("country"));
				film.setMovie_length(rs.getString("movie_length"));
				film.setImage(rs.getBytes("image"));
				film.setDescription(rs.getString("description"));
				film.setGenre(rs.getString("genre"));
				return film;
			}
		});
	}

	@Override
	public Hall getHallByID(int id) {
		String sql = "select * from hall where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);
		return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<Hall>() {

			@Override
			public Hall mapRow(ResultSet rs, int rowNum) throws SQLException {
				Hall hall = new Hall();
				hall.setId(rs.getInt("id"));
				hall.setName(rs.getString("name"));
				hall.setFloor(rs.getInt("floor"));
				hall.setDescription(rs.getString("description"));
				hall.setCinema_id(rs.getInt("cinema_id"));
				hall.setSeats(rs.getInt("seats"));

				return hall;
			}
		});
	}

	public CinemaSession getCinemaSessionByID(int id) {
		String sql = "select * from session where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);

		return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<CinemaSession>() {

			@Override
			public CinemaSession mapRow(ResultSet rs, int rowNum) throws SQLException {
				CinemaSession session = new CinemaSession();
				session.setId(rs.getInt("id"));
				session.setCinema_id(rs.getInt("cinema_id"));
				session.setFilm_id(rs.getInt("film_id"));
				session.setHall_id(rs.getInt("hall_id"));
				session.setShow_date(Date.valueOf(rs.getString("date")));
				session.setShow_time(rs.getString("time"));
				session.setTicket_price(rs.getInt("ticket_price"));

				return session;
			}
		});
	}

	@Override
	public List<Cinema> getCinemaList() {
		String sql = "select * from cinema";
		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cinema cinema = new Cinema();
				cinema.setId(rs.getInt("id"));
				cinema.setName(rs.getString("name"));
				cinema.setImage(rs.getBytes("image"));
				cinema.setAddress(rs.getString("address"));
				cinema.setSite(rs.getString("site"));
				cinema.setDescription(rs.getString("description"));
				cinema.setTelephone(rs.getString("telephone"));
				return cinema;
			}
		});
	}

	@Override
	public List<CinemaSession> getCinemaSessions(boolean selectType, int pageEmount) {
		String sql = "";
		if (selectType == false) {
			sql = "select * from sessionView ORDER BY session_date, session_time ASC limit 10" + " offset " + PaginationUtils.getOffcet(pageEmount);
		} else
			sql = "select * from sessionView  where strftime('%Y-%m-%d', session_date)>= strftime('%Y-%m-%d','now') " + " ORDER BY session_date, session_time ASC limit 10 offset " + PaginationUtils.getOffcet(pageEmount);
		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				CinemaSession session = new CinemaSession();
				session.setId(rs.getInt("session_id"));
				session.setCinema_id(rs.getInt("cinema_id"));
				session.setCinema_name(rs.getString("cinema_name"));
				session.setFilm_id(rs.getInt("film_id"));
				session.setFilm_name(rs.getString("film_name"));
				session.setHall_id(rs.getInt("hall_id"));
				session.setHall_name(rs.getString("hall_name"));
				session.setShow_date(Date.valueOf(rs.getString("session_date")));
				session.setShow_time(rs.getString("session_time"));
				session.setTicket_price(rs.getInt("ticket_price"));
				return session;
			}
		});
	}

	@Override
	public List<Film> getFilmList() {
		String sql = "select * from film";
		return jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setName(rs.getString("name"));
				film.setYear(rs.getInt("year"));
				film.setDirector(rs.getString("director"));
				film.setCountry(rs.getString("country"));
				film.setMovie_length(rs.getString("movie_length"));
				film.setImage(rs.getBytes("image"));
				film.setDescription(rs.getString("description"));
				film.setGenre(rs.getString("genre"));
				return film;
			}
		});
	}

	@Override
	public List<Hall> getHallList() {
		String sql = "select * from hall";
		return jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Hall hall = new Hall();
				hall.setId(rs.getInt("id"));
				hall.setName(rs.getString("name"));
				hall.setFloor(rs.getInt("floor"));
				hall.setDescription(rs.getString("description"));
				hall.setCinema_id(rs.getInt("cinema_id"));
				hall.setSeats(rs.getInt("seats"));

				return hall;
			}
		});
	}

	@Override
	public void inputCinema(Cinema cinema) {
		String sql = "insert into cinema (name, image, address, description, site, telephone) " + "VALUES (:name, :image, :address, :description, :site, :telephone)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", cinema.getName());
		paramMap.addValue("image", cinema.getImage());
		paramMap.addValue("address", cinema.getAddress());
		paramMap.addValue("description", cinema.getDescription());
		paramMap.addValue("site", cinema.getSite());
		paramMap.addValue("telephone", cinema.getTelephone());
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void inputSession(CinemaSession session) {
		String sql = "insert into session (film_id, cinema_id, hall_id, date, time, ticket_price) VALUES (:film_id, :cinema_id, :hall_id, :date, :time, :ticket_price)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("film_id", session.getFilm_id());
		paramMap.addValue("cinema_id", session.getCinema_id());
		paramMap.addValue("hall_id", session.getHall_id());
		paramMap.addValue("date", session.getShow_date().toString());
		paramMap.addValue("time", session.getShow_time().toString());
		paramMap.addValue("ticket_price", session.getTicket_price());

		jdbcTemplate.update(sql, paramMap);

	}

	@Override
	public void inputFilm(Film film) {
		String sql = "insert into film (name, year, genre, director, country, movie_length, image, description) VALUES (:name, :year, :genre, :director, :country, :movie_length, :image, :description)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", film.getName());
		paramMap.addValue("year", film.getYear());
		paramMap.addValue("director", film.getDirector());
		paramMap.addValue("country", film.getCountry());
		paramMap.addValue("movie_length", film.getMovie_length());
		paramMap.addValue("description", film.getDescription());
		paramMap.addValue("image", film.getImage());
		paramMap.addValue("genre", film.getGenre());

		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void inputHall(Hall hall) {
		String sql = "insert into hall (name, floor, description, cinema_id, seats) VALUES (:name, :floor, :description, :cinema_id, :seats)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", hall.getName());
		paramMap.addValue("floor", hall.getFloor());
		paramMap.addValue("description", hall.getDescription());
		paramMap.addValue("cinema_id", hall.getCinema_id());
		paramMap.addValue("seats", hall.getSeats());

		jdbcTemplate.update(sql, paramMap);

	}

	@Override
	public void updateCinema(Cinema cinema) {
		String sql = "update cinema set name=:name, image=:image, address=:address, description=:description," + " telephone=:telephone where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", cinema.getId());
		paramMap.addValue("name", cinema.getName());
		paramMap.addValue("image", cinema.getImage());
		paramMap.addValue("address", cinema.getAddress());
		paramMap.addValue("description", cinema.getDescription());
		paramMap.addValue("telephone", cinema.getTelephone());
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void updateSession(CinemaSession session) {
		String sql = "update session set film_id=:film_id, cinema_id=:cinema_id, hall_id=:hall_id, date=:date, time=:time, ticket_price=:ticket_price where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", session.getId());
		paramMap.addValue("film_id", session.getFilm_id());
		paramMap.addValue("cinema_id", session.getCinema_id());
		paramMap.addValue("hall_id", session.getHall_id());
		paramMap.addValue("date", session.getShow_date().toString());
		paramMap.addValue("time", session.getShow_time().toString());
		paramMap.addValue("ticket_price", session.getTicket_price());
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void updateFilm(Film film) {
		String sql = "update film set name=:name, year=:year, genre=:genre, director=:director, country=:country, movie_length=:movie_length, image=:image, description=:description where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", film.getId());
		paramMap.addValue("name", film.getName());
		paramMap.addValue("year", film.getYear());
		paramMap.addValue("director", film.getDirector());
		paramMap.addValue("country", film.getCountry());
		paramMap.addValue("movie_length", film.getMovie_length());
		paramMap.addValue("description", film.getDescription());
		paramMap.addValue("image", film.getImage());
		paramMap.addValue("genre", film.getGenre());

		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void updateHall(Hall hall) {
		String sql = "update hall set name=:name, floor=:floor, description=:description, cinema_id=:cinema_id, seats=:seats where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", hall.getId());
		paramMap.addValue("name", hall.getName());
		paramMap.addValue("floor", hall.getFloor());
		paramMap.addValue("description", hall.getDescription());
		paramMap.addValue("cinema_id", hall.getCinema_id());
		paramMap.addValue("seats", hall.getSeats());

		jdbcTemplate.update(sql, paramMap);

	}

	@Override
	public void deleteCinemaById(int id) {
		String sql = "delete from Cinema where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void deleteFilmById(int id) {
		String sql = "delete from film where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void deleteSessionById(int id) {
		String sql = "delete from session where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void deleteHallById(int id) {
		String sql = "delete from Hall where id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("id", id);
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public User getUser(String login) {
		String sql = "select * from user where login=:login";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("login", login);
		return jdbcTemplate.queryForObject(sql, paramMap, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));

				return user;
			}
		});
	}

}

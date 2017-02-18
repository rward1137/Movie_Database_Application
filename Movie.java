/**
 * 
 * @author Rachel Ward
 * 
 * This class represents a movie object
 * 
 * parameters include a String name, a String 
 * rating and an integer ID 
 *
 */

public class Movie {
	
	private String movieName;
	private String movieRating;
	private int movieID;
	
	public Movie() {}
	
	public Movie(String movieName, String movieRating, int movieID) {
		this.movieName = movieName;
		this.movieRating = movieRating;
		this.movieID = (movieID);
	}

	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public String getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

}

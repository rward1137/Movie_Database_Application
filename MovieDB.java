import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * @author Rachel Ward
 * 
 * This class creates a connection to our database of movies
 * using the database.properties file
 * and builds the SQL statements/queries to interact with it
 *
 */

public class MovieDB {
	
	private Connection connection;
	
	private PreparedStatement listMovies;
	private PreparedStatement listReviews;
	private PreparedStatement getID;
	
	public MovieDB() {
		Properties properties = new Properties();
		try
		{
			properties.load(new FileInputStream("database.properties"));
			String url = properties.getProperty("jdbc.url");
			connection = DriverManager.getConnection(url);
		}
		catch (IOException ioException)
		{
			System.err.printf("Unable to read database properties from file: %s\n",
								ioException.getMessage());
		}
		catch (SQLException sqlException)
		{
			System.err.printf("Unable to connect to database: %s\n",
								sqlException.getMessage());
		}
	}
	
	//make search string for movie ID, given the movie title
	public String buildQueryID(String title)
	{
		StringBuilder findID = new StringBuilder();
		
		findID.append("SELECT MOVIE.MOVIE_ID"
				+ "FROM MOVIE"
				+ "WHERE MOVIE.MOVIE_NAME = '"
				+ title + "'/;");
		return findID.toString();
	}
	
	//make search string for content rating
	public String buildQueryRating(String rating)
	{
		StringBuilder findMovies = new StringBuilder();
		
		findMovies.append("SELECT *"
				+ "FROM MOVIE"
				+ "WHERE MOVIE.MOVIE_RATING = '"
				+ rating.toUpperCase() + "'/;");
		return findMovies.toString();
	}
	
	//make search string for quality review
	public String buildQueryReviews(String title)
	{
		StringBuilder findReviews = new StringBuilder();
		
		findReviews.append("SELECT MOVIE.MOVIE_NAME, MOVIE_REVIEW.STARS,"
				+ "MOVIE_REVIEW.REVIEW"
				+ "FROM MOVIE, MOVIE_REVIEW"
				+ "WHERE MOVIE.MOVIE_ID = MOVIE_REVIEW.MOVIE_ID"
				+ "AND MOVIE.MOVIE_NAME = '"
				+ title.toUpperCase() + "'/;");
		return findReviews.toString();
	}
	
	//This method helps the MovieDBApp exchange a title for an ID int
	//it takes a string query and returns the int ID that results
	public int getMovieID(String query)
	{
		int movieID = 0;
		
		ResultSet result = null;
		
		try {
			getID = connection.prepareStatement(query);
			result = getID.executeQuery();
			
			movieID = result.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return movieID;
	}
	
	//use listMovies query to get an ArrayList of movies of a certain rating
	public ArrayList<Movie> getMovies(String query)
	{
		ArrayList<Movie> moviesFound = new ArrayList<>();
		
		ResultSet result = null;
		
		try {
			//send search string to database in a query
			listMovies = connection.prepareStatement(query);
			result = listMovies.executeQuery();
			
			while(result.next())
			{
				//each result is used to fill ArrayList of movie objects
				Movie m = new Movie();
				m.setMovieID(result.getInt(1));
				m.setMovieName(result.getString(2));
				m.setMovieRating(result.getString(3));
				
				moviesFound.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//return the ArrayList
		return moviesFound;
	}
	
	//use listReviews query to get an ArrayList of reviews of a certain movie
	public ArrayList<MovieReview> getReviews(String query)
	{
		ArrayList<MovieReview> reviewsFound = new ArrayList<>();
		
		ResultSet result = null;
		
		try {
			//send search string to database in a query
			listReviews = connection.prepareStatement(query);
			result = listReviews.executeQuery();
			
			while(result.next())
			{
				//each result is used to fill ArrayList of movieReview objects
				MovieReview r = new MovieReview();
				r.setMovieID(result.getInt(1));
				r.setReview(result.getString(2));
				r.setStars(result.getInt(3));
				
				reviewsFound.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//return the ArrayList
		return reviewsFound;
	}

	//add a movie review to the review table
	public int addReview(MovieReview review)
	{
		int result = 0;
		String update = String.format("INSERT INTO MOVIE_REVIEW(REVIEW_ID, MOVIE_ID, REVIEW, STARS)"
									+ " values('%d', '%d', '%s', '%d')", review.getReviewID()
									, review.getMovieID(), review.getReview(), review.getStars());
		Statement statement = null;
		try
		{
			statement = connection.createStatement();
			result = statement.executeUpdate(update);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (statement != null)
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
			}
		}
		return result;
	}

	
	
}

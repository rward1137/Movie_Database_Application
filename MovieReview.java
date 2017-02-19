/**
 * 
 * @author Rachel Ward
 * 
 * This class represents a movie review object
 * 
 * parameters include a int review number, int movie number,
 * a String review, and an int number of stars.
 *
 */
public class MovieReview {
	
	private int reviewID;
	private int movieID;
	private String review;
	private int stars;
	
	public MovieReview(){}
	
	public MovieReview(int movieID, String review, int stars) {
		this.movieID = movieID;
		this.review = review;
		this.stars = stars;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}

}

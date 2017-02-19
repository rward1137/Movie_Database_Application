import java.util.Scanner;

/**
 * 
 * @author Rachel Ward
 * 
 * This class is the user interface, where out menu is displayed
 * and user input is gathered for processing
 *
 */

public class MovieDBApp 
{
	private static final int SELECTION_FIND_MOVIE = 1;
	private static final int SELECTION_WRITE_REVIEW = 2;
	private static final int SELECTION_VIEW_REVIEWS = 3;
	private static final int SELECTION_EXIT = 4;


	public static void main(String[] args) 
	{
		MovieDB movies = new MovieDB();
		Scanner in = new Scanner(System.in);
		int selection = getMenuSelection(in);
		
		while(selection != SELECTION_EXIT)
		{
			if(selection == SELECTION_FIND_MOVIE)
			{
				System.out.print("ENTER RATING>> ");
				String rating = in.nextLine();
				
				//rating from user is given to query-building method
				//which passes the query string to getMovies. that will
				//return a list of movies with ratings that match the users rating
				System.out.println(movies.getMovies(movies.buildQueryRating(rating)));
			}
			else if(selection == SELECTION_WRITE_REVIEW)
			{
				System.out.print("ENTER MOVIE NAME>> ");
				String movieName = in.nextLine();
				int movieID = movies.getMovieID(movies.buildQueryID(movieName));
				
				System.out.print("ENTER NUMBER OF STARS (1 - 5)>> ");
				int stars = in.nextInt();
				in.nextLine();
				
				System.out.print("ENTER MOVIE REVIEW TEXT>> ");
				String reviewText = in.nextLine();
				
				movies.addReview(new MovieReview(movieID, reviewText, stars));
			}
			else if(selection == SELECTION_VIEW_REVIEWS)
			{
				System.out.print("ENTER MOVIE NAME>> ");
				String movieName = in.nextLine();
				System.out.println(movies.getReviews(movies.buildQueryReviews(movieName)));
			}
			else
			{
				System.out.println("INVALID MENU OPTION");
			}
			selection = getMenuSelection(in);
		}
		in.close();
	}
	
	
	public static int getMenuSelection(Scanner in)
	{
		int selection = 0;
		
		System.out.println("\n--- MAIN MENU ---");
		System.out.println("(1) Find Movie");
		System.out.println("(2) Write Review");
		System.out.println("(3) View Movie Reviews");
		System.out.println("(4) Exit");
		System.out.print("\nENTER MENU SELECTION>> ");

		if(in.hasNextInt())
		{
			selection = in.nextInt();
			in.nextLine();
		}
		else
		{
			in.nextLine();
		}
		
		System.out.println();

		return selection;
	}

}

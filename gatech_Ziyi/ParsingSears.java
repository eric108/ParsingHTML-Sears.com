package gatech_Ziyi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
/**
 * Sears implements two queries based on different inputs.
 *
 * With one argument input as a keyword, Sears will give the total amount of
 * items found related to that keyword. With two arguments input as a keyword 
 * and a page index, Sears will print out the detailed info of results found 
 * based on that keyword on that specific page.  
 *
 * @author Ziyi Jiang
 * @version 1.0 09/15/2013
 */
public class ParsingSears {
	private final String homeURL = "http://www.sears.com";
	private final String connectURL = "/search=";
	private String htmlTaker;
	private ArrayList<ParsingResult> resultsInfo;
	
	/**
	 * Using methods in java.net to read input URL and read the related HTML
	 * in a String format
	 *
	 * @param url input URL 
	 * @param String content of HTML in String format
	 */
	private String retrieveHTML(String url){
		String newHTML ="";
		try{
			URL sears = new URL(url);
			/* Set up the initial connection */
			URLConnection connec = sears.openConnection();
			
			/* read the result from the server */
			BufferedReader in = new BufferedReader(
				new InputStreamReader(connec.getInputStream()));
			String line="";
			while((line = in.readLine() )!=null){
				newHTML += line +'\n';
			}
			return newHTML;
		}catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("There is MalformedURLException!");
			return null;
		} catch (ProtocolException e) {
			e.printStackTrace();
			System.out.println("There is ProtocolException!");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("There is IOException!");
			return null;
		} 
	}
	/**
	 * With input keyword and method retriveHTML, this method set the htmlTaker
	 * the HTML of that target page.
	 *
	 * @param String keyword 
	 */
	private void retrieveSearchResult(String keyword){
		String searchURL;
		String kw = keyword.replace(" ", "%20");
		
		/*get the full URL for further use*/
		searchURL = homeURL + connectURL + kw;
		htmlTaker = retrieveHTML(searchURL);
		
		/* Locate the keyword "class=\"cardInner\"" to get our URL
		 * With some search keyword like <"stroller", 2>, the server 
		 * will generate two HTML sequentially and basic method cannot work, 
		 * and the while-loop code deals with this problem*/
		while(!htmlTaker.contains("class=\"cardInner\"")){
			
			/*Handle no results exception*/
			if(htmlTaker.contains("No Result"))
			{	
				System.out.println("No result of keyword " + keyword);
				System.exit(0);
			}
			String[] lines = htmlTaker.split("\n");
			int i=0;
			while(!lines[i].contains("var url = ") && i< lines.length){
				i++;
			}
			/*Deal with case that the HTML page do not contain "var url = "anymore 
			 *due to modification from the server side of Sears.com*/
			if (i >= lines.length){
				System.out.println("URL of searching result page " +
						"cannot be retrieved at this moment. " +
						"\nPlese try later.");
				System.exit(-1);
			}
			searchURL =  homeURL + lines[i].substring(lines[i].indexOf("/")
					,lines[i].length()-2).replace("\\", "");
			htmlTaker = retrieveHTML(searchURL);
		}
	}
	/**
	 * Implement Query1
	 * 
	 * keyword ->searchedURL -> retrieveSearchResult, get HTML -> 
	 * -> find answer after "productCount"
	 *
	 * @param keyword input from user
	 * @return int total number of searching result given the keyword 
	 */
	private int query1(String keyword){
		int count=0;
		
		retrieveSearchResult(keyword);
		
		/*find the position of answer after "productCount = "*/
		int index = htmlTaker.indexOf("productCount = ");
		int index2 = htmlTaker.indexOf("\"", index+16);
		try{
			count = Integer.parseInt(htmlTaker.substring(index+16, index2));
		}catch (NumberFormatException e){
			System.out.println("Please try later.");
		}
		return count;
	}

	/**
	 * User can use this get-method to retrieve the data 
	 * 
	 * @return ArrayList<ParsingResult> the data stored in ArrayList
	 */
	public ArrayList<ParsingResult> getResultsInfo(){
		if (resultsInfo ==null){
			System.out.println("No results found.");
		}
		return (ArrayList<ParsingResult>) resultsInfo.clone();
	}
	/**
	 * Main method calls two queries based different user inputs
	 * 
	 * @param args input from user
	 */
	public static void main(String[] args){
		if ((args == null) || (args.length <1)){
			System.out.println("Please run it again " +
					"and give one or two input parameters!");
			return;
		}else if(args.length >2){
			System.out.println("Too many input parameters!");
		}
		ParsingSears scraper = new ParsingSears();
		/*Query1*/
		if (args.length ==1){
			int count = scraper.query1(args[0].trim());
				if(count ==0){
					System.out.print("No result found in Sears.");
				}else{
					System.out.println("The total number of " + args[0].trim() 
					+ " is " + count + ".");
				}
		}
		
	}
}

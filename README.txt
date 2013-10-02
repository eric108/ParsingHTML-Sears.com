Sears.com
=========
Documentation for Sears.com Results Text Scraper
Sep. 15th, 2013

This package includes (only listed required items here):
Assignment.jar
Ducument_Sears_Parsing.pdf
ParsingResult.java (in “src” folder)
ParsingSears.java (in “src” folder)
	
Target
The package is mainly for two questions:

Query 1: Total number of results
Given a keyword, such as "digital camera", return the total number of results found.

Query 2: Result Object
Given a keyword (e.g. "digital cameras") and page number (e.g. "1"), return the results in a result object and then print results on screen. For each result, return title, price, vendor, and vendor information.

Usage
To use the package, one must specify the Assignment.jar file for javac so the compiler knows where to find it. Another thing is to add a line of “import gatech_Ziyi.*;” to the proper class file(s) that trying to use this package. 

A simple example usage:
import gatech.Ziyi.*;
public class HelloSears{
	public static void main(String args[]){
		ParsingSears parse= new ParsingSears ();
		String query = “digital camera”;
		int page = 15;
		//amount will be the amount of items which are about “digital camera”
		int amount = parse.query1(query);
		//results will be the items about “digital camera” on page 15. 
		String results_str = parse.query2(query, page);
		ArrayList<ParsingResult> results_data = parse.getResultsInfo();
		...
		}
}

The Assignment.jar file can be executed in two ways:
With one argument to get the total number of the results 
java -jar Assignment.jar <keyword> 
(e.g. java –jar Assignment.jar “baby strollers”)  

With two arguments to get the detailed information on a specific page given a keyword 
java -jar Assignment.jar <keyword> <page number>
 (e.g. java -jar Assignment.jar "baby strollers" 2)


Running Environment

This scraper (Assignment.jar) is developed in Java with Eclipse in win8 system. And it can also work well in Win7, IOS and Linux Systems as tested. Execution of this program requires JVM. 

Website-Results-Text-Scraper

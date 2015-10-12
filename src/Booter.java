// As created by Bastiaan Wuisman on 18-7-2015
// Created using IntelliJ IDEA


public class Booter {
	public static void main( String[] args ) {
		SiteManager siteManager = new SiteManager("http://www.nu.nl/");
		siteManager.readSourceCode();
		siteManager.findTag("<span class=\"title\">", "</span>");
		siteManager.printTagList();
	}
}
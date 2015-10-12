// As created by Bastiaan Wuisman on 19-8-2015
// Created using IntelliJ IDEA


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SiteManager {
	URL url;
	ArrayList<String> sourceCode = new ArrayList<String>();
	ArrayList<String> tagList = new ArrayList<String>();

	SiteManager( String website ) {
		try {
			url = new URL(website);
		}catch( MalformedURLException e ) {
		}
	}

	void setUrl( String website ) {
		try {
			url = new URL(website);
		}catch( MalformedURLException e ) {
		}
	}

	void readSourceCode() {
		sourceCode = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			while( (inputLine = reader.readLine()) != null ) {
				if( !inputLine.isEmpty() ) sourceCode.add(inputLine);
			}
			reader.close();
		}catch( IOException e ) {
		}
	}

	void filterOffset() {
		ArrayList<String> filteredCode = new ArrayList<String>();
		for( String code : sourceCode ) {
			String filtering = code;
			if( !filtering.isEmpty() ) {
				while( filtering.charAt(0) == ' ' ) {
					if( filtering.length() == 1 ) {
						filtering = "";
						break;
					}
					filtering = filtering.substring(1);
				}
			}
			if( !filtering.isEmpty() ) filteredCode.add(filtering);
		}
		sourceCode = filteredCode;
	}

	void findTag( String start, String end ) {
		for( String code : sourceCode ) {
			if( code.contains(start) && code.contains(end) ) {
				String tagContent = code.substring(code.indexOf(start) + start.length(), code.indexOf(end)).replace
						("&#39;", "'").replace("&amp;","&").replace("&#34;","\"");
				tagList.add(tagContent);
			}
		}
	}

	void printCode() {
		for( String code : sourceCode ) {
			System.out.println(code);
		}
	}

	void printTagList() {
		for( String tag : tagList ) {
			System.out.println(tag);
		}
	}
}
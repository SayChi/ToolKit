// As created by Bastiaan Wuisman on 18-7-2015
// Created using IntelliJ IDEA


import java.io.*;
import java.util.ArrayList;

public class FileManager {
	String root;
	String mainFileName;
	String multipleFileName;

	//constructor: root
	FileManager( String rootSet ) {
		root = rootSet;
	}

	//constructor: root, main file, file list
	FileManager( String rootSet, String mainFileNameSet, String multipleFileNameSet ) {
		root = rootSet;
		mainFileName = mainFileNameSet;
		multipleFileName = multipleFileNameSet;
	}

	//write list: content of file
	//calls writeList using multipleFileName
	void writeList( String[] content ) {
		writeList(content, multipleFileName);
	}

	//write list: content of file, file name
	//finds the first un-used number in the file list and writes the contents to that file
	void writeList( String[] content, String fileName ) {
		int fileNumber = 0;
		while( new File(root + "\\" + fileName + fileNumber + ".txt").exists() ) fileNumber++;
		write(content, fileName + fileNumber);
	}

	//write: content of file
	//calls write using mainFileName
	void write( String[] content ) {
		write(content, mainFileName);
	}

	//write: content of file, file name
	//writes the content to the specified file starting at the root
	void write( String[] content, String fileName ) {
		File file = new File(root + "\\" + fileName + ".txt");

		try {
			if( !file.exists() ) file.createNewFile();
			BufferedWriter filePrinter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

			for( String line : content ) {
				filePrinter.write(line);
				filePrinter.newLine();
			}

			filePrinter.close();
		}catch( IOException e ) {
		}
	}

	//read list
	//calls readList using multipleFileName
	String[][] readList() {
		return readList(multipleFileName);
	}

	//read list: file name
	//returns: String[][] filled with the files at the first lvl and the lines at the second lvl
	String[][] readList( String fileName ) {
		ArrayList<String[]> contentList = new ArrayList<String[]>();
		int fileNumber = 0;

		while( new File(root + "\\" + fileName + fileNumber + ".txt").exists() ) {
			contentList.add(read(fileName + fileNumber));
			fileNumber++;
		}

		return contentList.toArray(new String[0][0]);
	}

	//read
	//calls read using mainFileName
	String[] read() {
		return read(mainFileName);
	}

	//read: file name
	//returns: String[] filled with the lines of the specified file
	String[] read( String fileName ) {
		File file = new File(root + "\\" + fileName + ".txt");
		ArrayList<String> fileContent = new ArrayList<String>();

		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			String lineTemp;
			while( (lineTemp = fileReader.readLine()) != null ) {
				fileContent.add(lineTemp);
			}
			fileReader.close();
		}catch( IOException e ) {
		}

		return fileContent.toArray(new String[0]);
	}

	//rename: original file name, new file name
	//returns: boolean true=success false=failed
	//copies the content of the original file to the new file and deletes the original file
	boolean rename( String fileName, String newFileName ) {
		String[] content = read(fileName);
		if( delete(fileName) ) {
			write(content, newFileName);
			return true;
		}else {
			return false;
		}
	}

	//delete: file name
	//deletes the specified file
	boolean delete( String fileName ) {
		File file = new File(root + "\\" + fileName + ".txt");
		return file.delete();
	}

	//fix list: maximum amount of files to check past the last found file
	//calls fixList using multipleFileName
	void fixList( int maxGapSize ) {
		fixList(maxGapSize, multipleFileName);
	}

	//fix list: maximum amount of files to check past the last found file, file name
	//fixes the list of files created with writeList since some files might have been deleted
	void fixList( int maxGapSize, String fileName ) {
		ArrayList<Integer> missing = new ArrayList<Integer>();
		int fileNumber = 0;

		while( missing.size() <= maxGapSize ) {
			if( !(new File(root + "\\" + fileName + fileNumber + ".txt").exists()) ) missing.add(fileNumber);
			else if( !missing.isEmpty() ) {
				rename(fileName + fileNumber, fileName + missing.remove(0));
				missing.add(fileNumber);
			}
			fileNumber++;
		}
	}
}
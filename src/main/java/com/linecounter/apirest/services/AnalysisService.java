package com.linecounter.apirest.services;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import com.linecounter.apirest.entities.Info;


public class AnalysisService {
	
	public AnalysisService() {}
	
	public static ArrayList<Info> getAllInfos() throws IOException {
		ArrayList<String> allFiles = getAllFilesLocation();
		ArrayList<Info> allInfos = new ArrayList<Info>();
		
	    for(String item: allFiles) {
	    	Info archiveInfo = new Info(getExtension(item), countLines(item), getSize(item) );
	    	allInfos = InfoService.organize(allInfos, archiveInfo);
	    }
	    return allInfos;
	}
	
	private static ArrayList<String> getAllFilesLocation() {
		String folder = "repository";
		Path repositoryPath = (Paths.get(folder));
		
		try (Stream<Path> paths = Files.walk(repositoryPath)) {
			ArrayList<String> allFiles = new ArrayList<String>();
    	    paths
    	        .filter(Files::isRegularFile)
    	        .forEach(item->{
					allFiles.add(item.toString());
    	        });
    	    return allFiles;
    	} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static long getSize(String filename) {
		try {
			Path file = Paths.get(filename);
			BasicFileAttributes archiveAttrs = Files.readAttributes(file, BasicFileAttributes.class);
			return archiveAttrs.size();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private static String getExtension(String filename) {
	    return FilenameUtils.getExtension(filename);
	}
	
	private static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int readChars = is.read(c);
            if (readChars == -1) {
                return 0;
            }
            int count = 1;
            while (readChars == 1024) {
                for (int i=0; i<1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }
            while (readChars != -1) {
                for (int i=0; i<readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }
            return count;
        } finally {
            is.close();
        }
    }


}

package com.linecounter.apirest.services;

import java.io.File;
import java.nio.file.Paths;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

@Service
public class GitRepositoryService {	
	
	public GitRepositoryService() {}
	
	public boolean download(String repoUrl) throws Exception {
		String cloneDirectoryPath = "repository";
		deleteFolder(Paths.get(cloneDirectoryPath).toFile());
	    try {
	        System.out.println("[ START! ] ==> Cloning "+repoUrl+" for future analysis!");
	        Git.cloneRepository()
	            .setURI(repoUrl)
	            .setDirectory(Paths.get(cloneDirectoryPath).toFile())
	            .call();
	        System.out.println("[ FINISH ] ==> Git Repository cloned!");
	        return true;
	    } catch (Exception e) {
	        System.out.println("[ ERROR - GitRepositoryService.download() ] Exception occurred while cloning!");
	         e.printStackTrace();
	        throw new Exception();
	    }
	}
	
	
	public void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) {
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}

}

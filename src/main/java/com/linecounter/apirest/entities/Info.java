package com.linecounter.apirest.entities;

public class Info {
	private String extension;
	private int lines;
	private long bytes;
	
	public Info(String extension, int lines, long bytes) {
		this.extension = extension;
		this.lines = lines;
		this.bytes = bytes;
	}
	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
		
	public int getLines() {
		return this.lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}
	
	public void addLines(int lines) {
		this.lines = this.lines + lines;
	}

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
		this.bytes = bytes;
	}
	
	public void addBytes(long bytes) {
		this.bytes = this.bytes + bytes;
	}
	
	public String toString() {
		return " | extension: " + this.extension + " | lines: " + this.lines + " | bytes: " + this.bytes+ " | " ;
		
	}

}

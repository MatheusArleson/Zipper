package br.com.xavier.zipper.abstractions.io.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractReadableFileOutputStream extends AbstractReadableOutputStream<FileOutputStream> {
	
	//XXX PROPERTIES
	private File file;
	
	//XXX CONSTRUCTORS
	public AbstractReadableFileOutputStream(String filePath) throws IOException {
		this(new File(filePath));
	}
	
	public AbstractReadableFileOutputStream(File file) throws IOException {
		super(new FileOutputStream(file));
		this.file = file;
	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected final InputStream transformToInputStream(FileOutputStream fileOutputStream) throws IOException {
		InputStream fis = new FileInputStream(file);
		return fis;
	}

}

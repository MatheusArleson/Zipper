package br.com.xavier.zipper.impl.io.stream;

import java.io.File;
import java.io.IOException;

import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableFileOutputStream;

public final class ReadableFileOutputStream extends AbstractReadableFileOutputStream {
	
	public ReadableFileOutputStream(String filePath) throws IOException {
		super(filePath);
	}
	
	public ReadableFileOutputStream(File file) throws IOException {
		super(file);
	}

}

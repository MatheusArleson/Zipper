package br.com.xavier.zipper.abstractions.io.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractReadableByteArrayOutputStream extends AbstractReadableOutputStream<ByteArrayOutputStream> {
	
	//XXX CONSTRUCTOR
	public AbstractReadableByteArrayOutputStream() {
		super( new ByteArrayOutputStream() );
	}
	
	public AbstractReadableByteArrayOutputStream(int initialBufferSize) {
		super( new ByteArrayOutputStream(initialBufferSize) );
	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected final InputStream transformToInputStream(ByteArrayOutputStream outputStream) throws IOException {
		byte[] bytes = outputStream.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		return bis;
	}

}

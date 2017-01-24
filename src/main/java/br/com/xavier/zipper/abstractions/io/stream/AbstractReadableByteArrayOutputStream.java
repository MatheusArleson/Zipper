package br.com.xavier.zipper.abstractions.io.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NoCopyByteArrayOutputStream;

public abstract class AbstractReadableByteArrayOutputStream extends AbstractReadableOutputStream<NoCopyByteArrayOutputStream> {
	
	//XXX CONSTRUCTOR
	public AbstractReadableByteArrayOutputStream() {
		super( new NoCopyByteArrayOutputStream() );
	}
	
	public AbstractReadableByteArrayOutputStream(int initialBufferSize) {
		super( new NoCopyByteArrayOutputStream(initialBufferSize) );
	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected final InputStream transformToInputStream(NoCopyByteArrayOutputStream baos) throws IOException {
		byte[] bytes = baos.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		return bis;
	}

}

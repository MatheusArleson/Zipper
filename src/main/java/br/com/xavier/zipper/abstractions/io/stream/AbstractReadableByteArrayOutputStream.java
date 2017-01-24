package br.com.xavier.zipper.abstractions.io.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractReadableByteArrayOutputStream extends AbstractReadableOutputStream<NoCopyWriteManyReadOnceByteArrayOutputStream> {
	
	//XXX CONSTRUCTOR
	public AbstractReadableByteArrayOutputStream() {
		super( new NoCopyWriteManyReadOnceByteArrayOutputStream() );
	}
	
	public AbstractReadableByteArrayOutputStream(int initialBufferSize) {
		super( new NoCopyWriteManyReadOnceByteArrayOutputStream(initialBufferSize) );
	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected final InputStream transformToInputStream(NoCopyWriteManyReadOnceByteArrayOutputStream baos) throws IOException {
		byte[] bytes = baos.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		return bis;
	}

}

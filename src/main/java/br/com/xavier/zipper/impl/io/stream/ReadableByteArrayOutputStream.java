package br.com.xavier.zipper.impl.io.stream;

import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableByteArrayOutputStream;

public final class ReadableByteArrayOutputStream extends AbstractReadableByteArrayOutputStream {
	
	public ReadableByteArrayOutputStream() {
		super();
	}
	
	public ReadableByteArrayOutputStream(int initialSize) {
		super(initialSize);
	}

}

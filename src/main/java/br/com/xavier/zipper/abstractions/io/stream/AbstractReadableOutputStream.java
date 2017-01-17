package br.com.xavier.zipper.abstractions.io.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.xavier.zipper.interfaces.io.stream.IReadableOutputStream;

public abstract class AbstractReadableOutputStream<T extends OutputStream> extends OutputStream implements IReadableOutputStream<T> {
	
	//XXX PROPERTIES
	private AtomicBoolean readOnlyLock;
	private T outputStream;
	
	//XXX CONSTRUCTOR
	public AbstractReadableOutputStream(T outStream) {
		this.readOnlyLock = new AtomicBoolean(false);
		this.outputStream = Objects.requireNonNull(outStream);
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public final InputStream toInputStream() throws IOException {
		if(readOnlyLock.get()){
			throw new UnsupportedOperationException("Stream can only be transformed to input stream once.");
		}
		
		flush();
		close();
		readOnlyLock.set(true);
		
		return transformToInputStream(outputStream);
	}
	
	//XXX ABSTRACT METHODS
	protected abstract InputStream transformToInputStream(T outputStream) throws IOException;
	
	//XXX DELEGATE METHODS
	public int hashCode() {
		return outputStream.hashCode();
	}
	
	public boolean equals(Object obj) {
		return outputStream.equals(obj);
	}

	public void write(int b) throws IOException {
		checkReadOnlyState();
		outputStream.write(b);
	}

	public void write(byte[] b) throws IOException {
		checkReadOnlyState();
		outputStream.write(b);
	}
	
	public void write(byte[] b, int off, int len) throws IOException {
		checkReadOnlyState();
		outputStream.write(b, off, len);
	}

	public void flush() throws IOException {
		checkReadOnlyState();
		outputStream.flush();
	}

	public void close() throws IOException {
		checkReadOnlyState();
		outputStream.close();
	}

	public String toString() {
		return outputStream.toString();
	}

	//XXX PRIVATE METHODS
	private void checkReadOnlyState() {
		if(readOnlyLock.get()){
			throw new UnsupportedOperationException("Cannot write or after transform to input stream.");
		}
	}
}

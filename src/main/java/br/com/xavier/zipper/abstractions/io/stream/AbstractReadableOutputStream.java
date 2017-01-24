package br.com.xavier.zipper.abstractions.io.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.xavier.zipper.interfaces.io.stream.IReadable;

public abstract class AbstractReadableOutputStream<T extends OutputStream> extends OutputStream implements IReadable {
	
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
		
		InputStream inputStream = transformToInputStream(outputStream);
		return inputStream;
	}
	
	@Override
	public void write(int b) throws IOException {
		checkReadOnlyState();
		outputStream.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		checkReadOnlyState();
		outputStream.write(b);
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		checkReadOnlyState();
		outputStream.write(b, off, len);
	}

	@Override
	public void flush() throws IOException {
		checkReadOnlyState();
		outputStream.flush();
	}

	@Override
	public void close() throws IOException {
		checkReadOnlyState();
		outputStream.close();
	}
	
	//XXX ABSTRACT METHODS
	protected abstract InputStream transformToInputStream(T outStream) throws IOException;
	
	//XXX DELEGATE METHODS
	public int hashCode() {
		return outputStream.hashCode();
	}
	
	public boolean equals(Object obj) {
		return outputStream.equals(obj);
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

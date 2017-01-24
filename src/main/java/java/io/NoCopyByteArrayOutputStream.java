package java.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class NoCopyByteArrayOutputStream extends ByteArrayOutputStream {
	
	//XXX PROPERTIES
	private AtomicBoolean readOnlyLock;
	private ByteArrayOutputStream baos;
	
	//XXX CONSTRUCTOR
	public NoCopyByteArrayOutputStream(){
		this.baos = new ByteArrayOutputStream();
	}
	
	public NoCopyByteArrayOutputStream(int size){
		this.baos = new ByteArrayOutputStream(size);
	}
	
	//XXX OVERRIDE METHOD
	@Override
	public byte[] toByteArray() {
		checkReadOnlyState();
		
		this.readOnlyLock.set(true);
		return baos.buf;
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		checkReadOnlyState();
		baos.write(b);
	}

	@Override
	public void write(int b) {
		checkReadOnlyState();
		baos.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) {
		checkReadOnlyState();
		baos.write(b, off, len);
	}
	
	@Override
	public void writeTo(OutputStream out) throws IOException {
		checkReadOnlyState();
		baos.writeTo(out);
	}
	
	@Override
	public void reset() {
		checkReadOnlyState();
		baos.reset();
	}

	@Override
	public void flush() throws IOException {
		checkReadOnlyState();
		baos.flush();
	}
	
	@Override
	public void close() throws IOException {
		checkReadOnlyState();
		baos.close();
	}
	
	//XXX DELEGATE METHODS
	public int hashCode() {
		return baos.hashCode();
	}
	
	public boolean equals(Object obj) {
		return baos.equals(obj);
	}
	
	public String toString() {
		return baos.toString();
	}

	public String toString(String charsetName) throws UnsupportedEncodingException {
		return baos.toString(charsetName);
	}

	public int size() {
		return baos.size();
	}
	
	//XXX PRIVATE METHODS
	private void checkReadOnlyState() {
		if(readOnlyLock.get()){
			throw new UnsupportedOperationException("Cannot write after exported to byte array.");
		}
	}
}

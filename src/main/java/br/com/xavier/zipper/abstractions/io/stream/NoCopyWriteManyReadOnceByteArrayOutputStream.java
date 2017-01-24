package br.com.xavier.zipper.abstractions.io.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public final class NoCopyWriteManyReadOnceByteArrayOutputStream extends OutputStream {
	
	//XXX CONSTANTS
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	//XXX PROPERTIES
	private byte buf[];
	private int count;
	private AtomicBoolean readOnlyLock;
	
	//XXX CONSTRUCTOR
	public NoCopyWriteManyReadOnceByteArrayOutputStream() {
		this(32);
	}

	public NoCopyWriteManyReadOnceByteArrayOutputStream(int size) {
		if (size < 0) {
			throw new IllegalArgumentException("Negative initial size: " + size);
		}
		
		this.buf = new byte[size];
		this.readOnlyLock = new AtomicBoolean(false);
	}
	
	//XXX WRITE METHODS
	@Override
	public synchronized void write(int b) {
		checkReadOnlyState();
		
		ensureCapacity(count + 1);
		buf[count] = (byte) b;
		count += 1;
	}
	
	@Override
	public synchronized void write(byte b[], int off, int len) {
		checkReadOnlyState();
		
		if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) - b.length > 0)) {
			throw new IndexOutOfBoundsException();
		}
		
		ensureCapacity(count + len);
		System.arraycopy(b, off, buf, count, len);
		count += len;
	}

	public synchronized void writeTo(OutputStream out) throws IOException {
		out.write(buf, 0, count);
	}
	
	//XXX OTHER METHODS
	@Override
	public synchronized String toString() {
		return new String(buf, 0, count);
	}
	
	public synchronized String toString(String charsetName) throws UnsupportedEncodingException {
		return new String(buf, 0, count, charsetName);
	}
	
	public synchronized int size() {
		return count;
	}
	
	public synchronized void reset() {
		checkReadOnlyState();
		count = 0;
	}
	
	@Override
	public void flush() throws IOException {
		checkReadOnlyState();
		super.flush();
	}
	
	@Override
	public void close() throws IOException {
		checkReadOnlyState();
		super.close();
	}
	
	public synchronized byte toByteArray()[] {
		checkReadOnlyState();
		readOnlyLock.set(true);
		return buf;
	}
	
	//XXX PRIVATE METHODS
	private void ensureCapacity(int minCapacity) {
		// overflow-conscious code
		if (minCapacity - buf.length > 0)
			grow(minCapacity);
	}

	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = buf.length;
		int newCapacity = oldCapacity << 1;
		
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}
		
		buf = Arrays.copyOf(buf, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0)  {
			// overflow
			throw new OutOfMemoryError();
		}
		
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	//XXX PRIVATE METHODS
	private void checkReadOnlyState() {
		if(readOnlyLock.get()){
			throw new UnsupportedOperationException("Cannot write or after transform to input stream.");
		}
	}
}

package br.com.xavier.zipper.abstractions.compression;

import java.io.OutputStream;
import java.util.zip.Checksum;

import br.com.xavier.zipper.interfaces.compression.IZipperCompresserConfig;

public abstract class AbstractZipperCompresserConfig implements IZipperCompresserConfig {

	private static final long serialVersionUID = 6553913109653284553L;
	
	//XXX PROPERTIES
	private OutputStream bufferOutputStream;
	private Integer bytesPerRead;
	private Checksum checksumGenerator;
	
	//XXX CONSTRUCTOR
	public AbstractZipperCompresserConfig() {}
	
	//XXX GETTERS/SETTERS
	@Override
	public OutputStream getBufferOutputStream() {
		return bufferOutputStream;
	}

	@Override
	public void setBufferOutputStream(OutputStream bufferOutputStream) {
		this.bufferOutputStream = bufferOutputStream;
	}

	@Override
	public Integer getBytesPerRead() {
		return bytesPerRead;
	}

	@Override
	public void setBytesPerRead(Integer bytesPerRead) {
		this.bytesPerRead = bytesPerRead;
	}

	@Override
	public Checksum getChecksumGenerator() {
		return checksumGenerator;
	}

	@Override
	public void setChecksumGenerator(Checksum checksumGenerator) {
		this.checksumGenerator = checksumGenerator;
	}
	
}

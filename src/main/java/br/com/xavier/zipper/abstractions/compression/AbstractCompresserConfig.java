package br.com.xavier.zipper.abstractions.compression;

import java.io.OutputStream;
import java.util.zip.Checksum;

import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public abstract class AbstractCompresserConfig implements ICompresserConfig {

	private static final long serialVersionUID = 6553913109653284553L;
	
	//XXX PROPERTIES
	private OutputStream bufferOutputStream;
	private Integer bytesPerRead;
	private Checksum checksumGenerator;
	private CompressStrategy compressStrategy;
	
	//XXX CONSTRUCTOR
	public AbstractCompresserConfig() {}
	
	//XXX GETTERS/SETTERS
	@Override
	public OutputStream getBufferOutputStream() {
		return bufferOutputStream;
	}

	@Override
	public void setBufferOutputStream(OutputStream bufferOutputStream) {
		if( bufferOutputStream == null ){
			throw new IllegalArgumentException("Buffer outputstream cannot be null.");
		}
		
		this.bufferOutputStream = bufferOutputStream;
	}

	@Override
	public Integer getBytesPerRead() {
		return bytesPerRead;
	}

	@Override
	public void setBytesPerRead(Integer bytesPerRead) {
		if( bytesPerRead == null ){
			throw new IllegalArgumentException("Bytes per read cannot be null.");
		}
		
		if( bytesPerRead < 1 ){
			throw new IllegalArgumentException("Bytes per read cannot be less than one.");
		}
		
		this.bytesPerRead = bytesPerRead;
	}

	@Override
	public Checksum getChecksumGenerator() {
		return checksumGenerator;
	}

	@Override
	public void setChecksumGenerator(Checksum checksumGenerator) {
		if( checksumGenerator == null ){
			throw new IllegalArgumentException("Checksum generator cannot be null.");
		}
		
		this.checksumGenerator = checksumGenerator;
	}
	
	@Override
	public CompressStrategy getCompressStrategy() {
		return compressStrategy;
	}
	
	@Override
	public void setCompressStrategy(CompressStrategy compressStrategy) {
		if( compressStrategy == null ){
			throw new IllegalArgumentException("Compress strategy cannot be null.");
		}
		
		this.compressStrategy = compressStrategy;
	}
}

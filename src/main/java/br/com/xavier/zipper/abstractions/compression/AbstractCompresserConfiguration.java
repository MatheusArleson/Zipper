package br.com.xavier.zipper.abstractions.compression;

import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public abstract class AbstractCompresserConfiguration implements ICompresserConfig {

	private static final long serialVersionUID = 6553913109653284553L;
	
	//XXX PROPERTIES
	private final BufferMode bufferMode;
	private final Integer bytesPerRead;
	private final CompressStrategy compressStrategy;
	
	//XXX CONSTRUCTORS
	public AbstractCompresserConfiguration( ) {
		this( DEFAULT_BUFFER_MODE, DEFAULT_BYTES_PER_READ, DEFAULT_COMPRESS_STRATEGY );
	}
	
	public AbstractCompresserConfiguration( BufferMode bufferMode ) {
		this( bufferMode, DEFAULT_BYTES_PER_READ, DEFAULT_COMPRESS_STRATEGY );
	}
	
	public AbstractCompresserConfiguration( Integer bytesPerRead ) {
		this( DEFAULT_BUFFER_MODE, bytesPerRead, DEFAULT_COMPRESS_STRATEGY );
	}
	
	public AbstractCompresserConfiguration( CompressStrategy compressStrategy ) {
		this( DEFAULT_BUFFER_MODE, DEFAULT_BYTES_PER_READ, compressStrategy );
	}
	
	public AbstractCompresserConfiguration( BufferMode bufferMode, Integer bytesPerread) {
		this( bufferMode, bytesPerread, DEFAULT_COMPRESS_STRATEGY );
	}
	
	public AbstractCompresserConfiguration( BufferMode bufferMode, CompressStrategy compressStrategy ) {
		this( bufferMode, DEFAULT_BYTES_PER_READ, compressStrategy );
	}
	
	public AbstractCompresserConfiguration( Integer bytesPerRead, CompressStrategy compressStrategy ) {
		this( DEFAULT_BUFFER_MODE, bytesPerRead, compressStrategy );
	}
	
	public AbstractCompresserConfiguration( BufferMode bufferMode, Integer bytesPerRead, CompressStrategy compressStrategy ) {
		if( bufferMode == null ){
			throw new IllegalArgumentException( "Buffer mode cannot be null." );
		}
		
		if( bytesPerRead == null ){
			throw new IllegalArgumentException("Bytes per read cannot be null.");
		}
		
		if( bytesPerRead < 1 ){
			throw new IllegalArgumentException("Bytes per read cannot be less than one.");
		}
		
		if( compressStrategy == null ){
			throw new IllegalArgumentException("Compress strategy cannot be null.");
		}
		
		this.bufferMode = bufferMode;
		this.bytesPerRead = new Integer( bytesPerRead );
		this.compressStrategy = compressStrategy;
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bufferMode == null) ? 0 : bufferMode.hashCode());
		result = prime * result + ((bytesPerRead == null) ? 0 : bytesPerRead.hashCode());
		result = prime * result + ((compressStrategy == null) ? 0 : compressStrategy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCompresserConfiguration other = (AbstractCompresserConfiguration) obj;
		if (bufferMode != other.bufferMode)
			return false;
		if (compressStrategy != other.compressStrategy)
			return false;
		if (bytesPerRead == null) {
			if (other.bytesPerRead != null)
				return false;
		} else if (!bytesPerRead.equals(other.bytesPerRead))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractCompresserConfiguration [" 
			+ "bufferMode=" + bufferMode 
			+ ", bytesPerRead=" + bytesPerRead
			+ ", compressStrategy=" + compressStrategy 
		+ "]";
	}

	//XXX OVERRIDE METHODS
	@Override
	public BufferMode getBufferMode() {
		return bufferMode;
	}
	
	@Override
	public Integer getBytesPerRead() {
		return bytesPerRead;
	}

	@Override
	public CompressStrategy getCompressStrategy() {
		return compressStrategy;
	}
	
}

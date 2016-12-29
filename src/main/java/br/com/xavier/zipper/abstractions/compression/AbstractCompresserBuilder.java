package br.com.xavier.zipper.abstractions.compression;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.Checksum;

import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserBuilder;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public abstract class AbstractCompresserBuilder implements ICompresserBuilder {

	private static final long serialVersionUID = 6038018187329066597L;
	
	//XXX PROPERTIES
	private ICompresserConfig compresserConfig;
	
	//XXX CONSTRUCTOR
	public AbstractCompresserBuilder() {
		this.compresserConfig = getZipperCompresserConfigInstance();
	}
	
	//XXX ABSTRACT METHODS
	protected abstract ICompresserConfig getZipperCompresserConfigInstance( );
	protected abstract ICompresser getZipperCompresserInstance( ICompresserConfig compresserConfig );
	
	//XXX OVERRIDE METHODS
	@Override
	public ICompresserBuilder buffer( OutputStream os ) {
		this.compresserConfig.setBufferOutputStream( os ); 
		return this;
	}
	
	@Override
	public ICompresserBuilder bytesPerRead( Integer bytesPerRead ) {
		this.compresserConfig.setBytesPerRead( bytesPerRead );
		return this;
	}
	
	@Override
	public ICompresserBuilder checksum( Checksum checksumGenerator ) {
		this.compresserConfig.setChecksumGenerator( checksumGenerator );
		return this;
	}
	
	@Override
	public ICompresserBuilder strategy( CompressStrategy compressStrategy ) {
		this.compresserConfig.setCompressStrategy( compressStrategy );
		return this;
	}
	
	@Override
	public ICompresser build() {
		preventNullConfigProperties();
		
		ICompresser compresserInstance = getZipperCompresserInstance( compresserConfig );
		return compresserInstance;
	}

	//XXX PRIVATE METHODS
	private void preventNullConfigProperties() {
		if( compresserConfig.getBytesPerRead() == null ){
			compresserConfig.setBytesPerRead( 1024 );
		}
		
		if( compresserConfig.getBufferOutputStream() == null ){
			ByteArrayOutputStream baos = new ByteArrayOutputStream( compresserConfig.getBytesPerRead() );
			compresserConfig.setBufferOutputStream( baos );
		}
		
		if( compresserConfig.getCompressStrategy() == null ){
			compresserConfig.setCompressStrategy( CompressStrategy.LAZY );
		}
		
	}

}

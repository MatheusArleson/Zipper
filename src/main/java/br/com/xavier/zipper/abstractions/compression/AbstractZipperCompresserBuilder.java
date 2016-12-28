package br.com.xavier.zipper.abstractions.compression;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.Checksum;

import br.com.xavier.zipper.interfaces.compression.IZipperCompresser;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserBuilder;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserConfig;

public abstract class AbstractZipperCompresserBuilder implements IZipperCompresserBuilder {

	private static final long serialVersionUID = 6038018187329066597L;
	
	//XXX PROPERTIES
	private IZipperCompresserConfig compresserConfig;
	
	//XXX CONSTRUCTOR
	public AbstractZipperCompresserBuilder() {
		this.compresserConfig = getZipperCompresserConfigInstance();
	}
	
	//XXX ABSTRACT METHODS
	protected abstract IZipperCompresserConfig getZipperCompresserConfigInstance( );
	protected abstract IZipperCompresser getZipperCompresserInstance( IZipperCompresserConfig compresserConfig );
	
	//XXX OVERRIDE METHODS
	@Override
	public IZipperCompresserBuilder buffer( OutputStream os ) {
		if( os == null ){
			throw new IllegalArgumentException("Buffer outputstream cannot be null.");
		}
		
		this.compresserConfig.setBufferOutputStream( os ); 
		return this;
	}
	
	@Override
	public IZipperCompresserBuilder bytesPerRead( Integer bytesPerRead ) {
		if( bytesPerRead == null ){
			throw new IllegalArgumentException("Bytes per read cannot be null.");
		}
		
		if( bytesPerRead < 1 ){
			throw new IllegalArgumentException("Bytes per read cannot be less than one.");
		}
		
		this.compresserConfig.setBytesPerRead( bytesPerRead );
		return this;
	}
	
	@Override
	public IZipperCompresserBuilder checksum( Checksum checksumGenerator ) {
		Objects.requireNonNull( checksumGenerator );
		
		this.compresserConfig.setChecksumGenerator( checksumGenerator );
		return this;
	}
	
	@Override
	public IZipperCompresser build() {
		preventNullConfigProperties();
		
		IZipperCompresser compresserInstance = getZipperCompresserInstance( compresserConfig );
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
		
	}

}

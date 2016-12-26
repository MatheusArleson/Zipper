package br.com.xavier.zipper.abstractions;

import br.com.xavier.zipper.interfaces.IZipper;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserBuilder;
import br.com.xavier.zipper.interfaces.decompression.IZipperDecompresserBuilder;

public abstract class AbstractZipper implements IZipper {

	private static final long serialVersionUID = 8681918294833841166L;

	// XXX CONSTRUCTOR
	public AbstractZipper() {	}

	// XXX ABSTRACT METHODS
	protected abstract IZipperCompresserBuilder getZipperCompresserBuilderInstance(  );
	protected abstract IZipperDecompresserBuilder getZipperDecompresserBuilderInstance(  );

	// XXX OVERRIDE METHODS
	public IZipperCompresserBuilder prepareCompresser() {
		return getZipperCompresserBuilderInstance( );
	}

	public IZipperDecompresserBuilder prepareDecompress( ) {
		return getZipperDecompresserBuilderInstance( );
	}
	
}

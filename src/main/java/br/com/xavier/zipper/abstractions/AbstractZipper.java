package br.com.xavier.zipper.abstractions;

import br.com.xavier.zipper.interfaces.IZipper;
import br.com.xavier.zipper.interfaces.compression.ICompresserBuilder;
import br.com.xavier.zipper.interfaces.decompression.IDecompresserBuilder;

public abstract class AbstractZipper implements IZipper {

	private static final long serialVersionUID = 8681918294833841166L;

	// XXX CONSTRUCTOR
	public AbstractZipper() {	}

	// XXX ABSTRACT METHODS
	protected abstract ICompresserBuilder getZipperCompresserBuilderInstance(  );
	protected abstract IDecompresserBuilder getZipperDecompresserBuilderInstance(  );

	// XXX OVERRIDE METHODS
	public ICompresserBuilder prepareCompresser() {
		return getZipperCompresserBuilderInstance( );
	}

	public IDecompresserBuilder prepareDecompress( ) {
		return getZipperDecompresserBuilderInstance( );
	}
	
}

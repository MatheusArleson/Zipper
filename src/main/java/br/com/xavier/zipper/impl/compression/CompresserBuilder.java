package br.com.xavier.zipper.impl.compression;

import br.com.xavier.zipper.abstractions.compression.AbstractCompresserBuilder;
import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public final class CompresserBuilder extends AbstractCompresserBuilder {
	
	private static final long serialVersionUID = -915464407027095310L;

	//XXX CONSTRUCTOR
	public CompresserBuilder() {	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected ICompresserConfig getZipperCompresserConfigInstance() {
		return new CompresserConfiguration();
	}

	@Override
	protected ICompresser getZipperCompresserInstance(ICompresserConfig compresserConfig) {
		CompressStrategy compressStrategy = compresserConfig.getCompressStrategy();
		switch ( compressStrategy ) {
		case EAGER:
			return new EagerCompresser( compresserConfig );
			
		case LAZY:
			return new LazyCompresser( compresserConfig );

		default:
			throw new UnsupportedOperationException("Unknow compress strategy.");
		}
		
	}


}

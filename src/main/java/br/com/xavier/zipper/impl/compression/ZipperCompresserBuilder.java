package br.com.xavier.zipper.impl.compression;

import br.com.xavier.zipper.abstractions.compression.AbstractZipperCompresserBuilder;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresser;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserConfig;

public final class ZipperCompresserBuilder extends AbstractZipperCompresserBuilder {
	
	private static final long serialVersionUID = -915464407027095310L;

	//XXX CONSTRUCTOR
	public ZipperCompresserBuilder() {	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected IZipperCompresserConfig getZipperCompresserConfigInstance() {
		return new ZipperCompresserConfiguration();
	}

	@Override
	protected IZipperCompresser getZipperCompresserInstance(IZipperCompresserConfig compresserConfig) {
		return new ZipperCompresser( compresserConfig );
	}


}

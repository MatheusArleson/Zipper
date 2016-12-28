package br.com.xavier.zipper.impl.compression;

import br.com.xavier.zipper.abstractions.compression.AbstractZipperCompresser;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserConfig;

public class ZipperCompresser extends AbstractZipperCompresser {

	private static final long serialVersionUID = 7992303233061093156L;

	//XXX CONSTRUCTOR
	public ZipperCompresser(IZipperCompresserConfig compresserConfiguration) {
		super(compresserConfiguration);
	}
	
}

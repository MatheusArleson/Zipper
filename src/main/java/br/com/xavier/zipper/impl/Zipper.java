package br.com.xavier.zipper.impl;

import br.com.xavier.zipper.abstractions.AbstractZipper;
import br.com.xavier.zipper.impl.compression.ZipperCompresserBuilder;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserBuilder;
import br.com.xavier.zipper.interfaces.decompression.IZipperDecompresserBuilder;

public final class Zipper extends AbstractZipper {
	
	private static final long serialVersionUID = -3873377322610220541L;

	//XXX CONSTRUCTOR
	public Zipper() {
		super();
	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected IZipperCompresserBuilder getZipperCompresserBuilderInstance() {
		return new ZipperCompresserBuilder();
	}
	
	@Override
	protected IZipperDecompresserBuilder getZipperDecompresserBuilderInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

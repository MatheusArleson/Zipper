package br.com.xavier.zipper.impl;

import br.com.xavier.zipper.abstractions.AbstractZipper;
import br.com.xavier.zipper.interfaces.compression.ICompresserBuilder;
import br.com.xavier.zipper.interfaces.decompression.IDecompresserBuilder;

public final class Zipper extends AbstractZipper {
	
	private static final long serialVersionUID = -3873377322610220541L;

	//XXX CONSTRUCTOR
	public Zipper() {
		super();
	}
	
	//XXX OVERRIDE METHODS
	@Override
	protected ICompresserBuilder getZipperCompresserBuilderInstance() {
		return null;
	}
	
	@Override
	protected IDecompresserBuilder getZipperDecompresserBuilderInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

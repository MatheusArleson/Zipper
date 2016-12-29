package br.com.xavier.zipper.impl.compression;

import br.com.xavier.zipper.abstractions.compression.AbstractLazyCompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public final class LazyCompresser extends AbstractLazyCompresser {

	private static final long serialVersionUID = -7751271127372414804L;
	
	//XXX CONSTRUCTOR
	public LazyCompresser(ICompresserConfig compresserConfiguration) {
		super(compresserConfiguration);
	}

}

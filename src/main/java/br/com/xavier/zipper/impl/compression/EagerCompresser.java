package br.com.xavier.zipper.impl.compression;

import br.com.xavier.zipper.abstractions.compression.AbstractEagerCompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public class EagerCompresser extends AbstractEagerCompresser {

	private static final long serialVersionUID = 7992303233061093156L;

	//XXX CONSTRUCTOR
	public EagerCompresser(ICompresserConfig compresserConfiguration) {
		super(compresserConfiguration);
	}
	
}

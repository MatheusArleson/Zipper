package br.com.xavier.zipper.impl.compression;

import java.io.IOException;

import br.com.xavier.zipper.abstractions.compression.AbstractCompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;

public final class Compresser extends AbstractCompresser {

	private static final long serialVersionUID = -3577494747036581968L;
	
	//XXX CONSTRUCTOR
	public Compresser(ICompresserConfig compresserConfiguration) throws IOException {
		super(compresserConfiguration);
	}

}

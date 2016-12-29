package br.com.xavier.zipper.abstractions.compression;

import java.io.IOException;

import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;
import br.com.xavier.zipper.interfaces.io.IZipEntryOutput;

public abstract class AbstractEagerCompresser extends AbstractCompresser {

	private static final long serialVersionUID = -7418122431755395107L;
	
	//XXX CONSTRUCTOR
	public AbstractEagerCompresser(ICompresserConfig compresserConfiguration) {
		super(compresserConfiguration);
		
	}

	//XXX OVERRIDE METHODS
	@Override
	public ICompresser add(IZipEntryInput zipperEntryInput) throws IOException {
		if( zipperEntryInput == null ){
			throw new IllegalArgumentException( "Zip entry input cannot be null." );
		}
		
		compress( zipperEntryInput );
		return this;
	}
	
	@Override
	public IZipEntryOutput output() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}

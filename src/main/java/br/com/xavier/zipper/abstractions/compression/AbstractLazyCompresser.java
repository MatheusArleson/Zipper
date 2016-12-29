package br.com.xavier.zipper.abstractions.compression;

import java.util.LinkedList;
import java.util.Queue;

import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;
import br.com.xavier.zipper.interfaces.io.IZipEntryOutput;

public abstract class AbstractLazyCompresser extends AbstractCompresser {

	private static final long serialVersionUID = -7464058619281434207L;
	
	//XXX PROPERTIES
	private Queue<IZipEntryInput> zipEntriesInputs;

	//XXX CONSTRUCTOR
	public AbstractLazyCompresser(ICompresserConfig compresserConfiguration) {
		super(compresserConfiguration);
		this.zipEntriesInputs = new LinkedList<>();
	}

	//XXX OVERRIDE METHODS
	@Override
	public ICompresser add(IZipEntryInput zipperEntryInput) {
		if( zipperEntryInput == null ){
			throw new IllegalArgumentException( "Zip entry input cannot be null." );
		}
		
		zipEntriesInputs.add( zipperEntryInput );
		return this;
	}
	
	@Override
	public IZipEntryOutput output() {
		compress( zipEntriesInputs );
		
		return null;
	}
}

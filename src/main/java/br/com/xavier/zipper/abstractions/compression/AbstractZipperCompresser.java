package br.com.xavier.zipper.abstractions.compression;

import java.util.Objects;

import br.com.xavier.zipper.interfaces.compression.IZipperCompresser;
import br.com.xavier.zipper.interfaces.compression.IZipperCompresserConfig;
import br.com.xavier.zipper.interfaces.io.IZipperInput;
import br.com.xavier.zipper.interfaces.io.IZipperOutput;

public class AbstractZipperCompresser implements IZipperCompresser {

	private static final long serialVersionUID = -1279268509840118053L;
	
	//XXX PROPERTIES
	private IZipperCompresserConfig compresserConfiguration;
	
	//XXX CONSTRUCTOR
	public AbstractZipperCompresser( IZipperCompresserConfig compresserConfiguration ) {
		this.compresserConfiguration = Objects.requireNonNull( compresserConfiguration );
	}

	//XXX OVERRIDE METHODS
	public IZipperCompresser add( IZipperInput zipperInput ) {
		if( zipperInput == null ){
			throw new IllegalArgumentException( "Input cannot be null." );
		}
		
		return this;
	}

	public IZipperOutput output() {
		// TODO Auto-generated method stub
		return null;
	}

}

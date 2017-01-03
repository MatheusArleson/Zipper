package br.com.xavier.zipper.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import br.com.xavier.zipper.interfaces.io.ITransform;

public class Base64ZipEntryTransform implements ITransform<String> {


	@Override
	public String transform( InputStream inputStream ) throws IOException {
		byte[] buffer = new byte[10];
		inputStream.read( buffer );
		Base64.getEncoder().encodeToString( buffer );
		return null;
	}

}

package br.com.xavier.zipper.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import br.com.xavier.zipper.interfaces.io.ITransform;

public class Base64Transformer implements ITransform<String> {

	@Override
	public String transform(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[inputStream.available()];
		inputStream.read(buffer);
		
		String base64Str = Base64.getEncoder().encodeToString(buffer);
		return base64Str;
	}

}

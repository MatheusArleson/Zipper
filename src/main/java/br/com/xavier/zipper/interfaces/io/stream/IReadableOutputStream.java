package br.com.xavier.zipper.interfaces.io.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IReadableOutputStream<T extends OutputStream> {
	
	InputStream toInputStream() throws IOException;

}

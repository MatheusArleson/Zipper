package br.com.xavier.zipper.interfaces.io.stream;

import java.io.IOException;
import java.io.InputStream;

public interface IReadable {
	
	InputStream toInputStream() throws IOException;

}

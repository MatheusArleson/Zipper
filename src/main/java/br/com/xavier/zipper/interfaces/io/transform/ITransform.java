package br.com.xavier.zipper.interfaces.io.transform;

import java.io.IOException;
import java.io.InputStream;

public interface ITransform<T> {
	
	T transform( InputStream inputStream ) throws IOException;
	
}

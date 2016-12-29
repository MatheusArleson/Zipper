package br.com.xavier.zipper;

import java.io.ByteArrayOutputStream;
import java.util.zip.Adler32;

import br.com.xavier.zipper.impl.Zipper;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public class Sandbox2 {
	
	public static void main(String[] args) {
		Zipper zipper = new Zipper();
		
		ICompresser compresser = zipper.prepareCompresser()
											.buffer(new ByteArrayOutputStream())
											.bytesPerRead(2048)
											.checksum(new Adler32())
											.build();
		
		IZipEntryInput zipperInput = null;
		compresser.add( zipperInput );
		
		compresser.output(  );
		
		
		
	}

}

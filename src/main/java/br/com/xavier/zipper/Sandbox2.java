package br.com.xavier.zipper;

import java.io.IOException;

import br.com.xavier.zipper.enums.BufferMode;
import br.com.xavier.zipper.enums.CompressStrategy;
import br.com.xavier.zipper.impl.Zipper;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public class Sandbox2 {
	
	public static void main(String[] args) throws IOException {
		Zipper zipper = new Zipper();
		
		ICompresser compresser = zipper.prepareCompresser()
											.bufferMode( BufferMode.DISK )
											.bytesPerRead( 8096 )
											.strategy( CompressStrategy.LAZY )
											.build();
		
		IZipEntryInput zipperInput = null;
		compresser.add( zipperInput );
		
		
		compresser.output(  );
		
		
		
	}

}

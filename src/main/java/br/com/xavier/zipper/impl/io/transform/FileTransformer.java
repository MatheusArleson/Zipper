package br.com.xavier.zipper.impl.io.transform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

import br.com.xavier.zipper.interfaces.io.transform.ITransform;

public final class FileTransformer implements ITransform<File> {
	
	//XXX PROPERTIES
	private String filePath;
	
	//XXX CONSTRUCTOR
	public FileTransformer(String filePath) {
		super();
		this.filePath = Objects.requireNonNull(filePath);
	}
	
	//XXX OVERRIDE METHODS
	@Override
	public File transform(InputStream inputStream) throws IOException {
		File outputFile = new File(this.filePath);
		FileOutputStream fos = null;
		
		try {
			
			fos = new FileOutputStream(outputFile);
			IOUtils.copy(inputStream, fos);
			
			fos.flush();
			fos.close();
			
			return outputFile;
			
		} catch(IOException e){
			throw e;
		} finally {
			if(fos != null){
				IOUtils.closeQuietly(fos);
			}
			
			if(inputStream != null){
				IOUtils.closeQuietly(inputStream);
			}
		}
	}

}

package br.com.xavier.zipper.abstractions.compression;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.com.xavier.zipper.abstractions.io.stream.AbstractReadableOutputStream;
import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public abstract class AbstractCompresser implements ICompresser {

	private static final long serialVersionUID = -1279268509840118053L;

	// XXX PROPERTIES
	private Integer bytesPerRead;
	private ;
	
	// XXX CONSTRUCTOR
	public AbstractCompresser() throws IOException {

	}

	// XXX COMPRESS METHODS
	protected void compress(Collection<IZipEntryInput> zipEntriesInputs) throws IOException {
		if (zipEntriesInputs == null || zipEntriesInputs.isEmpty()) {
			throw new IllegalArgumentException("Zip entries inputs cannot be null or empty.");
		}

		Iterator<IZipEntryInput> iterator = zipEntriesInputs.iterator();
		while (iterator.hasNext()) {
			compress(iterator.next());
		}
	}

	protected void compress(IZipEntryInput zipEntryInput) throws IOException {
		writeZipEntry(bytesPerRead, zipOutputStream, zipEntryInput);
	}

	private void writeZipEntry(Integer bytesPerRead, ZipOutputStream zipOutputStream, IZipEntryInput zipEntryInput) throws IOException {
		ZipEntry zipEntry = generateZipEnty(zipEntryInput);
		zipOutputStream.putNextEntry(zipEntry);

		InputStream zipEntryContentStream = generateZipEntryInputStream(zipEntryInput, bytesPerRead);
		byte contentBuffer[] = new byte[bytesPerRead];

		int count;
		while ((count = zipEntryContentStream.read(contentBuffer, 0, bytesPerRead)) != -1) {
			zipOutputStream.write(contentBuffer, 0, count);
		}

		zipEntryContentStream.close();
		zipOutputStream.closeEntry();
	}

	private ZipEntry generateZipEnty(IZipEntryInput zipEntryInput) {
		ZipEntry zipEntry = new ZipEntry(zipEntryInput.getZipEntryName());
		zipEntry.setMethod(ZipEntry.DEFLATED);
		zipEntry.setCreationTime(FileTime.from(Instant.now()));
		zipEntry.setLastModifiedTime(FileTime.from(Instant.now()));
		return zipEntry;
	}

	private InputStream generateZipEntryInputStream(IZipEntryInput zipEntryInput, Integer bytesPerRead) {
		InputStream zipEntryContentStream = zipEntryInput.getZipEntryInputStream();
		BufferedInputStream bis = new BufferedInputStream(zipEntryContentStream, bytesPerRead);
		return bis;
	}

}

package br.com.xavier.zipper.abstractions.compression;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.com.xavier.zipper.interfaces.compression.ICompresser;
import br.com.xavier.zipper.interfaces.compression.ICompresserConfig;
import br.com.xavier.zipper.interfaces.io.IZipEntryInput;

public abstract class AbstractCompresser implements ICompresser {

	private static final long serialVersionUID = -1279268509840118053L;

	// XXX PROPERTIES
	private ICompresserConfig compresserConfiguration;
	private ZipOutputStream compresserStream;

	// XXX CONSTRUCTOR
	public AbstractCompresser(ICompresserConfig compresserConfiguration) {
		this.compresserConfiguration = Objects.requireNonNull(compresserConfiguration);
		generateCompresserBufferStreamInstance(compresserConfiguration);
	}

	private void generateCompresserBufferStreamInstance(ICompresserConfig compresserConfiguration) {
		OutputStream configBufferStream = compresserConfiguration.getBufferOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(configBufferStream);
		ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
		zipOutputStream.setMethod(ZipOutputStream.DEFLATED);

		this.compresserStream = zipOutputStream;
	}

	// XXX COMPRESS METHODS
	protected void compress( Collection<IZipEntryInput> zipEntriesInputs ) throws IOException {
		if (zipEntriesInputs == null || zipEntriesInputs.isEmpty()) {
			throw new IllegalArgumentException("Zip entries inputs cannot be null or empty.");
		}

		Iterator<IZipEntryInput> iterator = zipEntriesInputs.iterator();
		while (iterator.hasNext()) {
			compress(iterator.next());
		}
	}

	protected void compress( IZipEntryInput zipEntryInput ) throws IOException {
		ZipEntry zipEntry = generateZipEnty(zipEntryInput);
		compresserStream.putNextEntry(zipEntry);
		
		writeZipEntryContent( zipEntryInput, compresserConfiguration, compresserStream );
	}

	private ZipEntry generateZipEnty(IZipEntryInput zipEntryInput) {
		ZipEntry zipEntry = new ZipEntry( zipEntryInput.getZipEntryName() );
		zipEntry.setMethod( ZipEntry.DEFLATED );
		zipEntry.setCreationTime( FileTime.from(Instant.now()) );
		zipEntry.setLastModifiedTime( FileTime.from(Instant.now()) );
		return zipEntry;
	}
	
	private void writeZipEntryContent( IZipEntryInput zipEntryInput, ICompresserConfig config, OutputStream compresserStream ) throws IOException {
		InputStream zipEntryContentStream = generateZipEntryInputStream( zipEntryInput, config );
		Integer bytesPerRead = config.getBytesPerRead();
		byte contentBuffer[] = new byte[ bytesPerRead ];
		
		int count;
		while ((count = zipEntryContentStream.read(contentBuffer, 0, bytesPerRead)) != -1) {
			compresserStream.write(contentBuffer, 0, count);
		}
		
		compresserStream.flush();
	}

	private InputStream generateZipEntryInputStream(IZipEntryInput zipEntryInput, ICompresserConfig config) {
		InputStream zipEntryContentStream = zipEntryInput.getZipEntryContentStream();
		Integer bytesPerRead = config.getBytesPerRead();

		Checksum checksumGenerator = config.getChecksumGenerator();
		if (checksumGenerator != null) {
			checksumGenerator.reset();
			zipEntryContentStream = new CheckedInputStream(zipEntryContentStream, checksumGenerator);
		}

		BufferedInputStream bis = new BufferedInputStream(zipEntryContentStream, bytesPerRead);
		return bis;
	}

	// XXX GETTERS/SETTERS
	protected ICompresserConfig getCompresserConfiguration() {
		return compresserConfiguration;
	}
}

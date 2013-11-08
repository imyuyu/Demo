package org.demo.plugin.exchange.base;

import java.io.IOException;
import java.io.InputStream;

public class ProtectInputStream extends InputStream {
	private InputStream inputStream;

	public ProtectInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	@Override
	public void close() throws IOException {
		// do nothing
	}

	public void trueClose() throws IOException {
		this.inputStream.close();
	}

	@Override
	public synchronized void mark(int readlimit) {
		this.inputStream.mark(readlimit);
	}

	@Override
	public boolean markSupported() {
		return this.inputStream.markSupported();
	}

	@Override
	public int available() throws IOException {
		return this.inputStream.available();
	}

	@Override
	public int read() throws IOException {
		return this.inputStream.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		return this.inputStream.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return this.inputStream.read(b, off, len);
	}

	@Override
	public synchronized void reset() throws IOException {
		this.inputStream.reset();
	}

	@Override
	public long skip(long n) throws IOException {
		return this.inputStream.skip(n);
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}

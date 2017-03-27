/* Ace by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.hashing;

import ace.Ace;
import ace.Sandboxed;
import ace.binary.Hex;
import java.io.BufferedInputStream;
import java.security.MessageDigest;
import java.security.Provider;

/**
 * Useful message digester class.
 */
public class Digester extends Ace {

	private final MessageDigest _messageDigest;

	public Digester(final String algorithm) {
		_messageDigest = new Sandboxed<MessageDigest>() {
			@Override public MessageDigest run() throws Exception {
				return MessageDigest.getInstance(algorithm);
			}
		}.go();
	}

	public Digester(final String algorithm, final String provider) {
		_messageDigest = new Sandboxed<MessageDigest>() {
			@Override public MessageDigest run() throws Exception {
				return MessageDigest.getInstance(algorithm, provider);
			}
		}.go();
	}

	public Digester(final String algorithm, final Provider provider) {
		_messageDigest = new Sandboxed<MessageDigest>() {
			@Override public MessageDigest run() throws Exception {
				return MessageDigest.getInstance(algorithm, provider);
			}
		}.go();
	}

	public String getAlgorithm() {
		if (assigned(_messageDigest)) {
			return _messageDigest.getAlgorithm();
		}
		return null;
	}

	public Provider getProvider() {
		if (assigned(_messageDigest)) {
			return _messageDigest.getProvider();
		}
		return null;
	}

	public Integer getDigestLength() {
		if (assigned(_messageDigest)) {
			return _messageDigest.getDigestLength();
		}
		return null;
	}

	public Digester reset() {
		if (assigned(_messageDigest)) {
			_messageDigest.reset();
		}
		return this;
	}

	public Digester digest(final String text) {
		if (assigned(_messageDigest, text)) {
			final byte[] buffer = text.getBytes();
			_messageDigest.update(buffer, 0, buffer.length);
		}
		return this;
	}

	public Digester digest(final byte[] buffer) {
		if (assigned(_messageDigest)) {
			_messageDigest.update(buffer, 0, buffer.length);
		}
		return this;
	}

	public Digester digest(final BufferedInputStream in, final int bufferSize) {
		if (assigned(_messageDigest)) {
			try {
				final byte[] buffer = new byte[bufferSize];
				int sizeRead;
				while ((sizeRead = in.read(buffer)) != -1) {
					_messageDigest.update(buffer, 0, sizeRead);
				}
			} catch (final Exception e) {
				GEH.setLastException(e);
			}
		}
		return this;
	}

	public byte[] getAsByteArray() {
		return new Sandboxed<byte[]>() {
			@Override public byte[] run() throws Exception {
				return _messageDigest.digest();
			}
		}.go();
	}

	public String getAsString() {
		return new Sandboxed<String>() {
			@Override public String run() throws Exception {
				return Hex.fromByteArray(_messageDigest.digest());
			}
		}.go();
	}

}

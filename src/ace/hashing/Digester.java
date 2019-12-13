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

	/**
	 * Constructor accepting the digest algorithm to be used.
	 * 
	 * @param algorithm 
	 */
	public Digester(final String algorithm) {
		_messageDigest = new Sandboxed<MessageDigest>() {
			@Override public MessageDigest run() throws Exception {
				return MessageDigest.getInstance(algorithm);
			}
		}.go();
	}

	/**
	 * Constructor accepting the digest algorithm and the provider name to be used.
	 * 
	 * @param algorithm 
	 * @param provider 
	 */
	public Digester(final String algorithm, final String provider) {
		_messageDigest = new Sandboxed<MessageDigest>() {
			@Override public MessageDigest run() throws Exception {
				return MessageDigest.getInstance(algorithm, provider);
			}
		}.go();
	}

	/**
	 * Constructor accepting the digest algorithm and the provider instance to be used.
	 * 
	 * @param algorithm 
	 * @param provider 
	 */
	public Digester(final String algorithm, final Provider provider) {
		_messageDigest = new Sandboxed<MessageDigest>() {
			@Override public MessageDigest run() throws Exception {
				return MessageDigest.getInstance(algorithm, provider);
			}
		}.go();
	}

	/**
	 * Gets the digest algorithm being used.
	 * 
	 * @return the digest algorithm being used
	 */
	public String getAlgorithm() {
		if (assigned(_messageDigest)) {
			return _messageDigest.getAlgorithm();
		}
		return null;
	}

	/**
	 * Gets the provider being used.
	 * 
	 * @return the provider being used
	 */
	public Provider getProvider() {
		if (assigned(_messageDigest)) {
			return _messageDigest.getProvider();
		}
		return null;
	}

	/**
	 * Gets the digest length.
	 * 
	 * @return the digest length
	 */
	public Integer getDigestLength() {
		if (assigned(_messageDigest)) {
			return _messageDigest.getDigestLength();
		}
		return null;
	}

	/**
	 * Resets the digester for further use.
	 * 
	 * @return itself
	 */
	public Digester reset() {
		if (assigned(_messageDigest)) {
			_messageDigest.reset();
		}
		return this;
	}

	/**
	 * Performs the digest of the specified string.
	 * 
	 * @param text
	 * @return itself
	 */
	public Digester digest(final String text) {
		if (assigned(_messageDigest, text)) {
			final byte[] buffer = text.getBytes();
			_messageDigest.update(buffer, 0, buffer.length);
		}
		return this;
	}

	/**
	 * Performs the digest of the specified byte array.
	 * 
	 * @param buffer
	 * @return itself
	 */
	public Digester digest(final byte[] buffer) {
		if (assigned(_messageDigest)) {
			_messageDigest.update(buffer, 0, buffer.length);
		}
		return this;
	}

	/**
	 * Performs the digest of the specified input stream using the specified buffer size.
	 * 
	 * @param in
	 * @param bufferSize
	 * @return itself
	 */
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

	/**
	 * Gets the array of bytes for the resulting hash value.
	 * 
	 * @return the array of bytes for the resulting hash value
	 */
	public byte[] getAsByteArray() {
		return new Sandboxed<byte[]>() {
			@Override public byte[] run() throws Exception {
				return _messageDigest.digest();
			}
		}.go();
	}

	/**
	 * Gets the hex string representation of the array of bytes for the resulting hash value.
	 * 
	 * @return the hex string representation of the array of bytes for the resulting hash value
	 */
	public String getAsString() {
		return new Sandboxed<String>() {
			@Override public String run() throws Exception {
				return Hex.fromByteArray(_messageDigest.digest());
			}
		}.go();
	}

}

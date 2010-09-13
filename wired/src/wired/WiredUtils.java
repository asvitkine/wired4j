//
// Copyright (c) 2010 Alexei Svitkine
// 
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without
// restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following
// conditions:
// 
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.
//

package wired;

import java.security.MessageDigest;

public class WiredUtils {
	public static String SHA1(String text) {
		try {
			return SHA1(text.getBytes("iso-8859-1"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String SHA1(byte[] bytes) {
		try {
			MessageDigest d = MessageDigest.getInstance("SHA-1");
			d.update(bytes, 0, bytes.length);
			return bytesToHex(d.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final char[] HEX_CHARS = {
		'0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};
	public static String bytesToHex(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			buf.append(HEX_CHARS[(b & 0xF0) >> 4]);
			buf.append(HEX_CHARS[b & 0x0F]);
		}
		return buf.toString();
	}

	public static String bytesToBase64(byte[] bytes) {
		return new String(Base64Coder.encode(bytes));
	}
}

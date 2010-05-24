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

import java.io.IOException;
import java.io.OutputStream;

public class WiredClientBase {
	public static final byte EOT = 4;
	public static final byte FS = 28;
	public static final byte GS = 29;
	public static final byte RS = 30;
	public static final byte SP = 32;

	protected OutputStream out;

	public WiredClientBase(OutputStream out) {
		this.out = out;
	}
	
	protected WiredClientBase send(String str) throws IOException {
		out.write(str.getBytes("UTF-8"));
		return this;
	}

	protected WiredClientBase send(boolean b) throws IOException {
		return send(b ? "1" : "0");
	}

	protected WiredClientBase send(byte b) throws IOException {
		out.write(b);
		return this;
	}
}

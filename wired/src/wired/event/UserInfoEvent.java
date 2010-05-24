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

package wired.event;

import java.util.List;

public class UserInfoEvent extends WiredEvent {
	private User user;
	private String ip;
	private String host;
	private String clientVersion;
	private String cipherName;
	private String cipherBits;
	private String loginTime;
	private String idleTime;
	private List<Transfer> downloads;
	private List<Transfer> uploads;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getClientVersion() {
		return clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	public String getCipherName() {
		return cipherName;
	}
	public void setCipherName(String cipherName) {
		this.cipherName = cipherName;
	}
	public String getCipherBits() {
		return cipherBits;
	}
	public void setCipherBits(String cipherBits) {
		this.cipherBits = cipherBits;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(String idleTime) {
		this.idleTime = idleTime;
	}
	public List<Transfer> getDownloads() {
		return downloads;
	}
	public void setDownloads(List<Transfer> downloads) {
		this.downloads = downloads;
	}
	public List<Transfer> getUploads() {
		return uploads;
	}
	public void setUploads(List<Transfer> uploads) {
		this.uploads = uploads;
	}

	private static class Transfer {
		private String path;
		private int transferred;
		private int size;
		private int speed;

		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public int getTransferred() {
			return transferred;
		}
		public void setTransferred(int transferred) {
			this.transferred = transferred;
		}
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public int getSpeed() {
			return speed;
		}
		public void setSpeed(int speed) {
			this.speed = speed;
		}
	}
}

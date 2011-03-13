//
// Copyright (c) 2011 Alexei Svitkine
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

import java.io.Serializable;

public class AccountPrivileges implements Serializable {
	private boolean canGetUserInfo;
	private boolean canBroadcast;
	private boolean canPostNews;
	private boolean canClearNews;
	private boolean canDownload;
	private boolean canUpload;
	private boolean canUploadAnywhere;
	private boolean canCreateFolders;
	private boolean canAlterFiles;
	private boolean canDeleteFiles;
	private boolean canViewDropBoxes;
	private boolean canCreateAccounts;
	private boolean canEditAccounts;
	private boolean canDeleteAccounts;
	private boolean canElevatePrivileges;
	private boolean canKickUsers;
	private boolean canBanUsers;
	private boolean canNotBeKicked;
	private int downloadSpeed;
	private int uploadSpeed;
	private int downloadLimit;
	private int uploadLimit;
	private boolean canChangeTopic;

	public boolean getCanGetUserInfo() {
		return canGetUserInfo;
	}
	public void setCanGetUserInfo(boolean canGetUserInfo) {
		this.canGetUserInfo = canGetUserInfo;
	}
	public boolean getCanBroadcast() {
		return canBroadcast;
	}
	public void setCanBroadcast(boolean canBroadcast) {
		this.canBroadcast = canBroadcast;
	}
	public boolean getCanPostNews() {
		return canPostNews;
	}
	public void setCanPostNews(boolean canPostNews) {
		this.canPostNews = canPostNews;
	}
	public boolean getCanClearNews() {
		return canClearNews;
	}
	public void setCanClearNews(boolean canClearNews) {
		this.canClearNews = canClearNews;
	}
	public boolean getCanDownload() {
		return canDownload;
	}
	public void setCanDownload(boolean canDownload) {
		this.canDownload = canDownload;
	}
	public boolean getCanUpload() {
		return canUpload;
	}
	public void setCanUpload(boolean canUpload) {
		this.canUpload = canUpload;
	}
	public boolean getCanUploadAnywhere() {
		return canUploadAnywhere;
	}
	public void setCanUploadAnywhere(boolean canUploadAnywhere) {
		this.canUploadAnywhere = canUploadAnywhere;
	}
	public boolean getCanCreateFolders() {
		return canCreateFolders;
	}
	public void setCanCreateFolders(boolean canCreateFolders) {
		this.canCreateFolders = canCreateFolders;
	}
	public boolean getCanAlterFiles() {
		return canAlterFiles;
	}
	public void setCanAlterFiles(boolean canAlterFiles) {
		this.canAlterFiles = canAlterFiles;
	}
	public boolean getCanDeleteFiles() {
		return canDeleteFiles;
	}
	public void setCanDeleteFiles(boolean canDeleteFiles) {
		this.canDeleteFiles = canDeleteFiles;
	}
	public boolean getCanViewDropBoxes() {
		return canViewDropBoxes;
	}
	public void setCanViewDropBoxes(boolean canViewDropBoxes) {
		this.canViewDropBoxes = canViewDropBoxes;
	}
	public boolean getCanCreateAccounts() {
		return canCreateAccounts;
	}
	public void setCanCreateAccounts(boolean canCreateAccounts) {
		this.canCreateAccounts = canCreateAccounts;
	}
	public boolean getCanEditAccounts() {
		return canEditAccounts;
	}
	public void setCanEditAccounts(boolean canEditAccounts) {
		this.canEditAccounts = canEditAccounts;
	}
	public boolean getCanDeleteAccounts() {
		return canDeleteAccounts;
	}
	public void setCanDeleteAccounts(boolean canDeleteAccounts) {
		this.canDeleteAccounts = canDeleteAccounts;
	}
	public boolean getCanElevatePrivileges() {
		return canElevatePrivileges;
	}
	public void setCanElevatePrivileges(boolean canElevatePrivileges) {
		this.canElevatePrivileges = canElevatePrivileges;
	}
	public boolean getCanKickUsers() {
		return canKickUsers;
	}
	public void setCanKickUsers(boolean canKickUsers) {
		this.canKickUsers = canKickUsers;
	}
	public boolean getCanBanUsers() {
		return canBanUsers;
	}
	public void setCanBanUsers(boolean canBanUsers) {
		this.canBanUsers = canBanUsers;
	}
	public boolean getCanNotBeKicked() {
		return canNotBeKicked;
	}
	public void setCanNotBeKicked(boolean canNotBeKicked) {
		this.canNotBeKicked = canNotBeKicked;
	}
	public int getDownloadSpeed() {
		return downloadSpeed;
	}
	public void setDownloadSpeed(int downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}
	public int getUploadSpeed() {
		return uploadSpeed;
	}
	public void setUploadSpeed(int uploadSpeed) {
		this.uploadSpeed = uploadSpeed;
	}
	public int getDownloadLimit() {
		return downloadLimit;
	}
	public void setDownloadLimit(int downloadLimit) {
		this.downloadLimit = downloadLimit;
	}
	public int getUploadLimit() {
		return uploadLimit;
	}
	public void setUploadLimit(int uploadLimit) {
		this.uploadLimit = uploadLimit;
	}
	public boolean getCanChangeTopic() {
		return canChangeTopic;
	}
	public void setCanChangeTopic(boolean canChangeTopic) {
		this.canChangeTopic = canChangeTopic;
	}
}

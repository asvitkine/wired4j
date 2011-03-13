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

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import wired.event.BroadcastEvent;
import wired.event.ChatCreatedEvent;
import wired.event.ChatEvent;
import wired.event.DeclineChatEvent;
import wired.event.FileInfo;
import wired.event.FileInfoEvent;
import wired.event.FileListEvent;
import wired.event.InviteEvent;
import wired.event.NewsListEvent;
import wired.event.NewsPost;
import wired.event.NewsPostEvent;
import wired.event.PrivateMessageEvent;
import wired.event.TopicChangedEvent;
import wired.event.TransferReadyEvent;
import wired.event.User;
import wired.event.UserAccountEvent;
import wired.event.UserInfoEvent;
import wired.event.UserJoinEvent;
import wired.event.UserLeaveEvent;
import wired.event.UserListEvent;
import wired.event.UserStatusChangeEvent;
import wired.event.WiredErrorEvent;

public class EventBasedWiredClient extends WiredClient {
	private WiredEventHandler handler;
	private HashMap<Long,ArrayList<User>> users = new HashMap<Long,ArrayList<User>>();
	private ArrayList<FileInfo> files = new ArrayList<FileInfo>();
	private ArrayList<NewsPost> newsPosts = new ArrayList<NewsPost>();

	public EventBasedWiredClient(InputStream in, OutputStream out, WiredEventHandler handler) {
		super(in, out);
		this.handler = handler;
	}

	@Override
	protected void processServerMessage(int code, List<String> params) {
		System.out.println("Got " + code);
		if (params != null) {
			System.out.println("With params: ");
			int i=0;
			for (String p : params)
				System.out.println(i+++"  " + p);
		}
		if (code == MSG_CHAT || code == MSG_ACTION_CHAT) {
			ChatEvent event = new ChatEvent();
			event.setChatId(Long.valueOf(params.get(0)));
			event.setUserId(Long.valueOf(params.get(1)));
			event.setMessage(params.get(2));
			event.setEmote(code == WiredClient.MSG_ACTION_CHAT);
			handler.handleEvent(event);
		} else if (code == MSG_BROADCAST) {
			BroadcastEvent event = new BroadcastEvent();
			event.setUserId(Long.valueOf(params.get(0)));
			event.setMessage(params.get(1));
			handler.handleEvent(event);
		} else if (code == MSG_USERLIST) {
			long chatId = Long.valueOf(params.get(0));
			ArrayList<User> userlist = users.get(chatId);
			if (userlist == null) {
				userlist = new ArrayList<User>();
				users.put(chatId, userlist);
			}
			userlist.add(readUser(params));
		} else if (code == MSG_USERLIST_DONE) {
			long chatId = Long.valueOf(params.get(0));
			ArrayList<User> userlist = users.get(chatId);
			if (userlist != null) {
				UserListEvent event = new UserListEvent();
				event.setChatId(chatId);
				event.setUsers(userlist);
				handler.handleEvent(event);
				users.remove(chatId);
			}
		} else if (code == MSG_CLIENT_JOIN) {
			UserJoinEvent event = new UserJoinEvent();
			event.setChatId(Long.valueOf(params.get(0)));
			event.setUser(readUser(params));
			handler.handleEvent(event);
		} else if (code == MSG_CLIENT_LEAVE) {
			UserLeaveEvent event = new UserLeaveEvent();
			event.setChatId(Long.valueOf(params.get(0)));
			event.setUserId(Long.valueOf(params.get(1)));
			handler.handleEvent(event);
		} else if (code == MSG_FILE_LISTING) {
			files.add(readFile(params));
		} else if (code == MSG_FILE_LISTING_DONE) {
			FileListEvent event = new FileListEvent();
			event.setPath(params.get(0));
			event.setFiles(files);
			files = new ArrayList<FileInfo>();
			handler.handleEvent(event);
		} else if (code == MSG_NEWS) {
			newsPosts.add(readNewsPost(params));
		} else if (code == MSG_NEWS_DONE) {
			NewsListEvent event = new NewsListEvent();
			event.setNewsPosts(newsPosts);
			newsPosts = new ArrayList<NewsPost>();
			handler.handleEvent(event);
		} else if (code == MSG_NEWS_POSTED) {
			NewsPostEvent event = new NewsPostEvent();
			event.setNewsPost(readNewsPost(params));
			handler.handleEvent(event);			
		} else if (code == MSG_PRIVATE_CHAT_INVITE) {
			InviteEvent event = new InviteEvent();
			event.setChatId(Long.valueOf(params.get(0)));
			event.setUserId(Long.valueOf(params.get(1)));
			handler.handleEvent(event);
		} else if (code == MSG_PRIVATE_MESSAGE) {
			PrivateMessageEvent event = new PrivateMessageEvent();
			event.setFromUserId(Long.valueOf(params.get(0)));
			event.setMessage(params.get(1));
			handler.handleEvent(event);
		} else if (code == MSG_CLIENT_INFO) {
			UserInfoEvent event = new UserInfoEvent();
			User user = new User();
			readBaseUserFields(user, 0, params);
			if (params.size() > 16)
				user.setImage(params.get(16));
			event.setUser(user);
			event.setIp(params.get(6));
			event.setHost(params.get(7));
			event.setClientVersion(params.get(8));
			event.setCipherName(params.get(9));
			event.setCipherBits(params.get(10));
			event.setLoginTime(params.get(11));
			event.setIdleTime(params.get(10));
			handler.handleEvent(event);
		} else if (code == MSG_STATUS_CHANGE) {
			UserStatusChangeEvent event = new UserStatusChangeEvent();
			User user = new User();
			readBaseUserFields(user, 0, params);
			event.setUser(user);
			handler.handleEvent(event);
		} else if (code == MSG_PRIVATE_CHAT_CREATED) {
			ChatCreatedEvent event = new ChatCreatedEvent();
			event.setChatId(Long.valueOf(params.get(0)));
			handler.handleEvent(event);
		} else if (code == MSG_PRIVATE_CHAT_DECLINED) {
			DeclineChatEvent event = new DeclineChatEvent();
			event.setChatId(Long.valueOf(params.get(0)));
			event.setUserId(Long.valueOf(params.get(1)));
			handler.handleEvent(event);
		} else if (code == MSG_CHAT_TOPIC) {
			TopicChangedEvent event = new TopicChangedEvent();
			event.setChatId(Long.valueOf(params.get(0)));
			event.setNick(params.get(1));
			event.setLogin(params.get(2));
			event.setIp(params.get(3));
			event.setTime(parseDate(params.get(4)));
			event.setTopic(params.get(5));
			handler.handleEvent(event);
		} else if (code == MSG_FILE_INFO) {
			FileInfoEvent event = new FileInfoEvent();
			event.setFileInfo(readFile(params));
			event.setChecksum(params.get(5));
			if (params.size() > 6)
				event.setComment(params.get(6));
			handler.handleEvent(event);
		} else if (code == MSG_TRANSFER_READY) {
			TransferReadyEvent event = new TransferReadyEvent();
			event.setPath(params.get(0));
			event.setOffset(Long.valueOf(params.get(1)));
			event.setHash(params.get(2));
			handler.handleEvent(event);
		} else if (code == MSG_USER_SPECIFICATION) {
			UserAccountEvent event = new UserAccountEvent();
			event.setName(params.get(0));
			event.setPassword(params.get(1));
			event.setGroup(params.get(2));
			event.setPriveleges(parsePrivileges(params, 3));
			handler.handleEvent(event);
		} else if (code >= 500 && code < 600) {
			WiredErrorEvent event = new WiredErrorEvent();
			event.setMessage(params.get(0));
			event.setErrorCode(code);
			handler.handleEvent(event);
		}
	}

	private NewsPost readNewsPost(List<String> params) {
		NewsPost post = new NewsPost();
		post.setNick(params.get(0));
		post.setPostTime(parseDate(params.get(1)));
		post.setPost(params.get(2));
		return post;
	}

	protected FileInfo readFile(List<String> params) {
		FileInfo file = new FileInfo();
		file.setPath(params.get(0));
		file.setName(new File(params.get(0)).getName());
		file.setType(Long.valueOf(params.get(1)));		
		file.setSize(Long.valueOf(params.get(2)));
		file.setCreationDate(parseDate(params.get(3)));
		file.setModificationDate(parseDate(params.get(4)));
		//file.setIcon(getIconAsString(params.get(0)));
		return file;
	}

	private static long parseDate(String dateString) {
		return ISO8601.parse(dateString).getTimeInMillis();
	}

	private static void readBaseUserFields(User user, int index, List<String> params) {
		user.setId(Long.valueOf(params.get(index++)));
		user.setIdle(Long.valueOf(params.get(index++)) == 1);
		user.setAdmin(Long.valueOf(params.get(index++)) == 1);
		user.setIcon(Long.valueOf(params.get(index++)));
		user.setNick(params.get(index++));
		user.setLogin(params.get(index++));
	}

	private static User readUser(List<String> params) {
		User user = new User();
		readBaseUserFields(user, 1, params);
		if (params.size() > 10)
			user.setImage(params.get(10));
		return user;
	}

}

package wired.event;

public class NewsPostEvent extends WiredEvent {
	private NewsPost newsPost;

	public void setNewsPost(NewsPost newsPost) {
		this.newsPost = newsPost;
	}

	public NewsPost getNewsPost() {
		return newsPost;
	}
}

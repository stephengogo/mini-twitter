import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class User extends Subject implements Group {
	private String uniqueID;
	private List<String> followers;
	private List<String> followings;
	private List<String> newsFeed;
	private int messageCount;
	private int positiveCount;
	private long creationTime;
	
	public User(String uniqueID) {
		this.uniqueID = uniqueID;
		this.followers = new ArrayList<String>();
		this.followings = new ArrayList<String>();
		this.newsFeed = new ArrayList<String>();
		this.messageCount = 0;
		this.positiveCount = 0;
		this.creationTime = System.currentTimeMillis();
	}
	
	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
	
	public void addFollowers(String follower) {
		this.followers.add(follower);
	}

	public List<String> getFollowings() {
		return followings;
	}

	public void setFollowings(List<String> followings) {
		this.followings = followings;
	}
	
	public void addFollowings(String followings) {
		this.followings.add(followings);
	}

	public void addNewsFeed(String news) {
		this.newsFeed.add(news);
	}
	
	public List<String> getNewsFeed() {
		return newsFeed;
	}

	public void setNewsFeed(List<String> newsFeed) {
		this.newsFeed = newsFeed;
	}

	public void addMessageCount() {
		this.messageCount++;
	}
	
	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	
	public void addPositiveCount() {
		this.positiveCount++;
	}
	
	public int getPositiveCount() {
		return positiveCount;
	}

	public void setPositiveCount(int positiveCount) {
		this.positiveCount = positiveCount;
	}
	
	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	// override Group interface method
	@Override
	public DefaultMutableTreeNode render() {
		DefaultMutableTreeNode user = new DefaultMutableTreeNode(this, false);
		user.setUserObject(this.getUniqueID());
		return user;
	}
	
}

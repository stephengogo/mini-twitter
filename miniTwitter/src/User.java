import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class User extends Subject implements Group {
	private String uniqueID;
	private List<String> followers;
	private List<String> followings;
	private List<String> newsFeed;
	
	public User(String uniqueID) {
		this.uniqueID = uniqueID;
		this.followers = new ArrayList<String>();
		this.followings = new ArrayList<String>();
		this.newsFeed = new ArrayList<String>();
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

	public List<String> getFollowings() {
		return followings;
	}

	public void setFollowings(List<String> followings) {
		this.followings = followings;
	}
	
	public void addFollowers(String follower) {
		this.followers.add(follower);
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

	// override Group interface method
	@Override
	public DefaultMutableTreeNode render() {
		DefaultMutableTreeNode user = new DefaultMutableTreeNode(this, false);
		user.setUserObject(this.getUniqueID());
		return user;
	}

	
}

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class User implements Group{
	private String uniqueID;
	private ArrayList<User> followers;
	private ArrayList<User> followings;
	
	public User(String uniqueID) {
		this.uniqueID = uniqueID;
		this.followers = new ArrayList<User>();
		this.followings = new ArrayList<User>();
	}
	

	public String getUniqueID() {
		return uniqueID;
	}


	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}


	public ArrayList<User> getFollowers() {
		return followers;
	}


	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}


	public ArrayList<User> getFollowings() {
		return followings;
	}


	public void setFollowings(ArrayList<User> followings) {
		this.followings = followings;
	}


	@Override
	public DefaultMutableTreeNode render() {
		DefaultMutableTreeNode user = new DefaultMutableTreeNode(uniqueID, false);
		return user;
	}
	
}

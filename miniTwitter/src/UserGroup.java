import java.util.ArrayList; 
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class UserGroup implements Group {
	
	private String groupID;
	private List<User> userList = new ArrayList<User>();
	private static DefaultMutableTreeNode root;
	private long creationTime;
	
	public UserGroup() {
		root = new DefaultMutableTreeNode("Root");
	}
	
	public UserGroup(String groupID) {
		this.groupID = groupID;
	}
	
	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public void setRoot(DefaultMutableTreeNode root) {
		UserGroup.root = root;
	}

	public void addUser(User user) {
		userList.add(user);
	}
	
	public String getGroupID() {
		return groupID;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	// to differentiate tree view added GROUP: groupname
	@Override
	public DefaultMutableTreeNode render() {
		DefaultMutableTreeNode group = new DefaultMutableTreeNode("GROUP: " + this.groupID, true);
		this.creationTime = System.currentTimeMillis();
		return group;
	}

}

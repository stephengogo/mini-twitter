import java.util.ArrayList; 
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class UserGroup implements Group {
	
	private String groupID;
	private List<User> userList = new ArrayList<User>();
	private static DefaultMutableTreeNode root;
	
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

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	

	@Override
	public DefaultMutableTreeNode render() {
		DefaultMutableTreeNode group = new DefaultMutableTreeNode(this.groupID, true);
//		for(User singleUser: userList) {
//			group.add(singleUser.render());
//		}
		return group;
	}

}

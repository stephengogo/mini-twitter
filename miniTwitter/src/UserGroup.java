import java.util.ArrayList; 
import java.util.List;

public class UserGroup implements Group {
	
	private List<Group> groupList = new ArrayList<Group>();
	private String groupID;
	private List<User> userList = new ArrayList<User>();
    
	public UserGroup(String groupID) {
		this.groupID = groupID;
	}
	
	public void addGroup(Group group) {
		groupList.add(group);
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
	
	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}

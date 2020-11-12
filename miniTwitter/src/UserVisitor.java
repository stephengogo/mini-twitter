import java.util.ArrayList;

public class UserVisitor implements UserElementVisitor {

	private int userTotal = 0;
	private int groupTotal = 0;
	private int messageTotal;
	private double positiveMessage;
	
	@Override
	public int visitUserTotal() {
		return userTotal;
	}
	
	public void setVisitorTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	@Override
	public int visitGroupTotal() {
		return groupTotal;
		
	}
	
	public void setGroupTotal(int groupTotal) {
		this.groupTotal = groupTotal;
	}
	
	@Override
	public int visitMessagesTotal(ArrayList<User> user) {
		messageTotal = 0;
		
		for(int i = 0; i < user.size(); i++) {
			messageTotal += user.get(i).getMessageCount();
		}
		
		return messageTotal;
	}

	@Override
	public double visitPositivePercentage(ArrayList<User> user) {
		positiveMessage = 0.0;
		int messages = 0;
		int positiveMessages = 0;
		for(int i = 0; i < user.size(); i++) {
			messages += user.get(i).getMessageCount();
			positiveMessages += user.get(i).getPositiveCount();
		}
		positiveMessage = ((positiveMessages* 100) / messages);
		return positiveMessage;
	}

}

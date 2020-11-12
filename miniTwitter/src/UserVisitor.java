import javax.swing.JOptionPane;

public class UserVisitor implements UserElementVisitor {

	private int userTotal = 0;
	private int groupTotal = 0;
	
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
	public void visitMessagesTotal(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitPositivePercentage(User user) {
		// TODO Auto-generated method stub
		
	}

}

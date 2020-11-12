
public interface UserElementVisitor {
	
	public int visitUserTotal();
	public int visitGroupTotal();
	public void visitMessagesTotal(User user);
	public void visitPositivePercentage(User user);
	
}

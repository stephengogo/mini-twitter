import java.util.ArrayList;

public interface UserElementVisitor {
	
	public int visitUserTotal();
	public int visitGroupTotal();
	public int visitMessagesTotal(ArrayList<User> user);
	public double visitPositivePercentage(ArrayList<User> user);
	
}

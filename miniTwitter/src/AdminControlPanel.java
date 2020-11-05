import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class AdminControlPanel {
	
	private static AdminControlPanel instance; 
	
	public static AdminControlPanel getInstance() {
		if (instance == null) {
			instance = new AdminControlPanel();
		}
		return instance;
	}
	private JTree tree;
	private JFrame frame;
	private JPanel panel;
	
	private AdminControlPanel() {
		frame = new JFrame();
		panel = new JPanel();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        tree = new JTree(root);
        panel.add(tree);
        
		frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
		
	}
	
}

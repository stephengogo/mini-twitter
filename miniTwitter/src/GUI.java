
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
	private JFrame frame;
	private JButton addUserButton;
	private JButton addGroupButton;
	private JButton openUserViewButton;
	private JButton showUserTotalButton;
	private JButton showGroupTotalButton;
	private JButton showMessagesTotalButton;
	private JButton showPositivePercentageButton;
	private JTextArea userIDTextArea;
	private JTextArea groupIDTextArea;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	
	private DefaultMutableTreeNode root; 
	private JTree tree;
	

    public GUI() {

    	JPanel panel = new JPanel();
        layout = new GridBagLayout();
        frame = new JFrame();
        
        panel.setLayout(layout);
        gbc = new GridBagConstraints();
        
        userIDTextArea = new JTextArea(2,17);
        addUserButton = new JButton("Add User");
        groupIDTextArea = new JTextArea(2,17);
        addGroupButton = new JButton("Add Group");
        openUserViewButton = new JButton("Open User View");
        showUserTotalButton = new JButton("Show User Total");
        showGroupTotalButton = new JButton("Show GroupTotal");
        showMessagesTotalButton = new JButton("Show Messages Total");
        showPositivePercentageButton = new JButton("Show Positive Percentage");
        
        root = new DefaultMutableTreeNode("Root");
        tree = new JTree(root);
        
        root.add(new DefaultMutableTreeNode("HI"));
        
        panel.add(tree);

        panel.add(userIDTextArea);
        panel.add(addUserButton);
 
        panel.add(groupIDTextArea);
        panel.add(addGroupButton);
        
        panel.add(openUserViewButton);
        
        panel.add(showUserTotalButton);
        
        panel.add(showGroupTotalButton);
        
        panel.add(showMessagesTotalButton);
        
        panel.add(showPositivePercentageButton);

        
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //panel.setLayout(new GridLayout(0, 1));
        
        panel.add(tree);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
    }

    // process the button clicks
    public void actionPerformed(ActionEvent e) {
    	
    }

}
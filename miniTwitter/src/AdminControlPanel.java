import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.*;


public class AdminControlPanel {
	
	private static AdminControlPanel instance; 
	
	public static AdminControlPanel getInstance() {
		if (instance == null) {
			instance = new AdminControlPanel();
		}
		return instance;
	}

    
	private AdminControlPanel() {
		startView();
		listeners();
	}
	
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
	
	private UserGroup userGroup;
	private DefaultMutableTreeNode root; 
	private JTree tree;
	
	private void startView() {
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
        
        //root.add(new DefaultMutableTreeNode("HI"));
        
        //gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
//        panel.add(tree, gbc);
 
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userIDTextArea, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(addUserButton, gbc);
 
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(groupIDTextArea, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(addGroupButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(openUserViewButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(showUserTotalButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(showGroupTotalButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(showMessagesTotalButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(showPositivePercentageButton, gbc);
        
        JPanel mainPanel = new JPanel(new GridLayout(0,2));
        mainPanel.add(tree);
        mainPanel.add(panel);
        
        frame.setSize(500, 500);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin Panel");
        frame.pack();
        frame.setVisible(true);
	}
	
	private void listeners() {
		this.addUserButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
                User newUser = new User(userIDTextArea.getText());
                //addNodeToSelectedNode(newUser);
                root.add(new DefaultMutableTreeNode(newUser, false));
                userGroup.addGroup(newUser);
                userIDTextArea.setText("");
            }
        });
		
		
//		private void addNodeToSelectedNode(UserGroupComponent newNodeObject){
//	        UserGroupComponent selectedGroupNode = 
//	                (UserGroupComponent) this.userGroupTreePane.getLastSelectedPathComponent();
//	        addNodeToGroupNode(newNodeObject, selectedGroupNode);
//	    }
        
        this.addGroupButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
//                GroupComposite newGroup = new GroupComposite(groupIDTextArea.getText());
//                addNodeToSelectedNode(newGroup);
                groupIDTextArea.setText("");
            }
        });
	}

}

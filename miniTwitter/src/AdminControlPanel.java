
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

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
	
	private UserGroup rootGroup;
	private DefaultMutableTreeNode root; 
	private DefaultTreeModel defaultTreeModel;

	private JTree jTree = new JTree();
	
	private String selectedString;
	
	private void startView() {
        JPanel panel = new JPanel();
        layout = new GridBagLayout();
        frame = new JFrame();
        rootGroup = new UserGroup();
        
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
        
        root = new DefaultMutableTreeNode(rootGroup.getRoot());
        defaultTreeModel = new DefaultTreeModel(root);
        jTree.setModel(defaultTreeModel);
        //jTree = new JTree(defaultTreeModel);
        jTree.setCellRenderer(new DefaultTreeCellRenderer());
        System.out.println(jTree.getSelectionPath());
        //jTree.setShowsRootHandles(true);
        //root.add(new DefaultMutableTreeNode("HI"));
        
        gbc.insets = new Insets(10,10,10,10);
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
        mainPanel.add(jTree);
        mainPanel.add(panel);
        
        frame.setSize(500, 500);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin Panel");
        frame.pack();
        frame.setVisible(true);
	}
	
	private void listeners() {
//		this.jTree.addTreeSelectionListener((TreeSelectionEvent) -> {
//        		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
//        		selectedString = (String) selectedNode.getUserObject();
//        		System.out.print(selectedString);
//		});
		this.addUserButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	//User newUser = new User(userIDTextArea.getText());
            	if(jTree.getSelectionPath() == null) {
            		User newUser = new User(userIDTextArea.getText());
            		//DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(newUser, false);
            		//root.add(userNode);
            		
            		root.add(newUser.render());
                    //root.add(new DefaultMutableTreeNode(newUser.getUniqueID(), false));
                    //userGroup.addGroup(newUser);
                    
            	}
            	else {
            		DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
            		if(selectedElement.getAllowsChildren()) {
            			User newUser = new User(userIDTextArea.getText());
            			//selectedElement.add(newUser.render());
            			
            			// trying to change
            			//DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(newUser, false);
            			
            			DefaultMutableTreeNode userNode = newUser.render();
            			
            			selectedElement.add(userNode);
            			//System.out.println((String) selectedElement.getUserObject() );
            		}
//            		if(selectedElement == root) {
//            			User newUser = new User(userIDTextArea.getText());
//            			//User newUser = new User(userIDTextArea.getText());
//            			root.add(newUser.render());
//                        //root.add(new DefaultMutableTreeNode(newUser.getUniqueID(), false));
//                        //userGroup.addGroup(newUser);
//            		}else if(selectedElement.getAllowsChildren()) {
//            			User newUser = new User(userIDTextArea.getText());
//            			//selectedElement.add(newUser.render());
//            			
//            			// trying to change
//            			//DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(newUser, false);
//            			
//            			DefaultMutableTreeNode userNode = newUser.render();
//            			
//            			selectedElement.add(userNode);
//            			//System.out.println((String) selectedElement.getUserObject() );
//            		}
            	}
            	
            	userIDTextArea.setText("");
            	defaultTreeModel.reload(root);
            	for (int i = 0; i < jTree.getRowCount(); i++) {
            		jTree.expandRow(i);
            	}
            	//expandAllNodes(jTree, 0, jTree.getRowCount());
            }
        });
		
        this.addGroupButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
                //UserGroup newGroup = new UserGroup(groupIDTextArea.getText());
                //DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
//                if(selectedElement.getAllowsChildren()) {
//                	
//                	System.out.println((String) selectedElement.getUserObject() );
//                	groupIDTextArea.setText("");
//            	}
                if(jTree.getSelectionPath() == null) {
                	rootGroup = new UserGroup(groupIDTextArea.getText());
                	
                	// trying to use composite pattern 
                	//DefaultMutableTreeNode newestGroup = new DefaultMutableTreeNode(rootGroup);
                	DefaultMutableTreeNode newestGroup = rootGroup.render();
                	//newestGroup.setUserObject(rootGroup.getGroupID());
            		root.add(newestGroup);
            	}
                else {
            		DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
            		if(selectedElement.getAllowsChildren()) {
            			rootGroup = new UserGroup(groupIDTextArea.getText());
            			DefaultMutableTreeNode newestGroup = rootGroup.render();
            			selectedElement.add(newestGroup);
            		}
            	}
                
                groupIDTextArea.setText("");
                defaultTreeModel.reload(root);
                //expandAllNodes(jTree, 0, jTree.getRowCount());
            	for (int i = 0; i < jTree.getRowCount(); i++) {
            		jTree.expandRow(i);
            	}
            }
        });
        
	}
	// method to expand all the descendant nodes using recursive method 
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
	    for(int i = startingIndex; i < rowCount; ++i){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount() != rowCount){
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}

}

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

import java.util.ArrayList;
import java.util.HashMap;

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
	private JButton userAndGroupIDverificationButton;
	private JTextArea userIDTextArea;
	private JTextArea groupIDTextArea;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	private UserGroup rootGroup;
	private DefaultMutableTreeNode root; 
	private DefaultTreeModel defaultTreeModel;
	private JTree jTree;
	private ArrayList<String> containStringList;
	private ArrayList<String> groupStringArrList;
	private HashMap<String, User> userHashMap;
	private UserVisitor userVisitor;
	private Integer groupSize;
	private ArrayList<User> userList; 
	
	private void startView() {
        JPanel panel = new JPanel();
        layout = new GridBagLayout();
        panel.setLayout(layout);
        frame = new JFrame();
        
        rootGroup = new UserGroup();
        root = new DefaultMutableTreeNode(rootGroup.getRoot());
        defaultTreeModel = new DefaultTreeModel(root);
        
        jTree = new JTree();
        jTree.setModel(defaultTreeModel);
        jTree.setCellRenderer(new DefaultTreeCellRenderer());
        
        userIDTextArea = new JTextArea(2,17);
        addUserButton = new JButton("Add User");
        groupIDTextArea = new JTextArea(2,17);
        addGroupButton = new JButton("Add Group");
        openUserViewButton = new JButton("Open User View");
        showUserTotalButton = new JButton("Show User Total");
        showGroupTotalButton = new JButton("Show GroupTotal");
        showMessagesTotalButton = new JButton("Show Messages Total");
        showPositivePercentageButton = new JButton("Show Positive Percentage");
        userAndGroupIDverificationButton = new JButton("Verify User/Group ID");
        
        containStringList = new ArrayList<String>();
        groupStringArrList = new ArrayList<String>();
        userHashMap = new HashMap<String, User>();
        userVisitor = new UserVisitor();
        groupSize = 0;
        userList = new ArrayList<User>();
        
        gbc = new GridBagConstraints();
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
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(userAndGroupIDverificationButton, gbc);
        
        JPanel mainPanel = new JPanel(new GridLayout(0,2));
        JScrollPane jScrollTreePane = new JScrollPane(jTree);
        mainPanel.add(jScrollTreePane);
        mainPanel.add(panel);
        
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin Panel");
        frame.setVisible(true);
	}
	
	private void listeners() {
		// adds user : if none is selected or if is group then adds user 
		this.addUserButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	// check if unique user ID, add user to respective default mutable tree node, else throw error message 
            	if(!containStringList.contains(userIDTextArea.getText()) && (userIDTextArea.getText().length() != 0) ) {
            		User newUser = new User(userIDTextArea.getText());
            		
                	if(jTree.getSelectionPath() == null) {
                		root.add(newUser.render());
                		containStringList.add(userIDTextArea.getText());
                		userHashMap.put(userIDTextArea.getText(), newUser);
                		userList.add(newUser);
                	}
                	else {
                		DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
                		if(selectedElement.getAllowsChildren()) {
                			DefaultMutableTreeNode userNode = newUser.render();
                			selectedElement.add(userNode);
                			containStringList.add(userIDTextArea.getText());
                			userHashMap.put(userIDTextArea.getText(), newUser);
                			userList.add(newUser);
                		} else {
                			JOptionPane.showMessageDialog(frame,
                    			    "Group must be selected",
                    			    "Error message",
                    			    JOptionPane.PLAIN_MESSAGE);
                		}
                	}
            	} else {
            		JOptionPane.showMessageDialog(frame,
            			    "User can't be same nor the length of 0\nPlease add to a group.",
            			    "Error message",
            			    JOptionPane.PLAIN_MESSAGE);
            	}
            	
            	userIDTextArea.setText("");
            	defaultTreeModel.reload(root);
            	for (int i = 0; i < jTree.getRowCount(); i++) {
            		jTree.expandRow(i);
            	}
            }
        });
		
		// add group if none is selected to root then only add when a group is selected, else throw error message 
        this.addGroupButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	if(groupIDTextArea.getText().length() != 0) {
            		if(jTree.getSelectionPath() == null) {
                    	rootGroup = new UserGroup(groupIDTextArea.getText());
                    	DefaultMutableTreeNode newestGroup = rootGroup.render();
                		root.add(newestGroup);
                		groupSize++;
                		groupStringArrList.add(groupIDTextArea.getText());
                	}
                    else {
                		DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
                		if(selectedElement.getAllowsChildren()) {
                			rootGroup = new UserGroup(groupIDTextArea.getText());
                			DefaultMutableTreeNode newestGroup = rootGroup.render();
                			selectedElement.add(newestGroup);
                			groupSize++;
                			groupStringArrList.add(groupIDTextArea.getText());
                		}
                	}
            	} else {
            		JOptionPane.showMessageDialog(frame,
            			    "Group can't be Null.",
            			    "Error message",
            			    JOptionPane.PLAIN_MESSAGE);
            	}
                
                
                groupIDTextArea.setText("");
                defaultTreeModel.reload(root);
            	for (int i = 0; i < jTree.getRowCount(); i++) {
            		jTree.expandRow(i);
            	}
            }
        });
        
        // check if the selected is a user then open user view
        this.openUserViewButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
            	if(!selectedElement.getAllowsChildren()) {
            		UserViewUI userview = new UserViewUI(userHashMap.get(selectedElement.toString()));
            		userview.setContainStringList(containStringList);
            		userview.setUserMap(userHashMap);
        		}
            }
        });
    	
        this.showUserTotalButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	userVisitor.setVisitorTotal(userHashMap.size());
            	JOptionPane.showMessageDialog(frame,
            			userVisitor.visitUserTotal() + " User Total",
        			    "UserTotal",
        			    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        this.showGroupTotalButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	userVisitor.setGroupTotal(groupSize);
            	JOptionPane.showMessageDialog(frame,
            			userVisitor.visitGroupTotal() + " Group Total",
        			    "GroupTotal",
        			    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        this.showMessagesTotalButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(frame,
            			userVisitor.visitMessagesTotal(userList) + " Message Total",
        			    "MessageTotal",
        			    JOptionPane.PLAIN_MESSAGE);
            	
            }
        });
        
        this.showPositivePercentageButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(frame,
            			userVisitor.visitPositivePercentage(userList) + "% Positive Messages",
        			    "PositiveMessages",
        			    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        // Test is user and groupID name is unique and has no spaces 
        this.userAndGroupIDverificationButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(frame,
            			"All User and Group Valid: " + userVisitor.visitUserGroupValidation(containStringList, groupStringArrList),
        			    "Verification",
        			    JOptionPane.PLAIN_MESSAGE);
            }
        });
        
	}

}

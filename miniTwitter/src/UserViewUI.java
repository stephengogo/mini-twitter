import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

public class UserViewUI implements Observer {

	private JFrame frame;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	private JTextArea userTextArea;
	private JTextArea tweetTextArea;
	private JButton followUserButton;
	private JButton postTweetButton;
	private JScrollPane followersScrollPane;
	private JScrollPane tweetScrollPane;
	private User currentUser;
	private JList currentFollowingListView;
	private JList newsFeedListView;
	private JLabel creationTimeText;
	
	private DefaultListModel<String> followingDefaultListmodel;
	private DefaultListModel<String> newsFeedDefaultListmodel;
	private ArrayList<String> containStringList;
	private HashMap<String, User> userMap;
	
	public ArrayList<String> getContainStringList() {
		return containStringList;
	}

	public void setContainStringList(ArrayList<String> containStringList) {
		this.containStringList = containStringList;
	}
	
	public HashMap<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(HashMap<String, User> userMap) {
		this.userMap = userMap;
	}

	// constructor
	public UserViewUI(User user) {
		currentUser = user;
		initUI();
		listeners();
		
	}

	// set up user ui
	@SuppressWarnings("unchecked")
	private void initUI() {
		JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame = new JFrame();
        
        // follower ui section
        userTextArea = new JTextArea(2,17);
        followUserButton = new JButton("Follow User");
        currentFollowingListView = new JList(this.currentUser.getFollowings().toArray());
        followersScrollPane = new JScrollPane();
        followersScrollPane.setViewportView(currentFollowingListView);
        followingDefaultListmodel = new DefaultListModel<String>();
        
        // tweet ui section
        tweetTextArea = new JTextArea(2,17);
        postTweetButton = new JButton("Post Tweet");
        newsFeedListView = new JList(this.currentUser.getNewsFeed().toArray());
        tweetScrollPane = new JScrollPane();
        tweetScrollPane.setViewportView(newsFeedListView);
        newsFeedDefaultListmodel = new DefaultListModel<String>();
        
        creationTimeText = new JLabel("Created Time in Current millis: " + currentUser.getCreationTime());
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userTextArea, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(followUserButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(followersScrollPane, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(tweetTextArea, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(postTweetButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(tweetScrollPane, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(creationTimeText, gbc);
        
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle(currentUser.getUniqueID() + "'s view");
        frame.setVisible(true);
	}
	
	// listen for button click
	private void listeners() {
		this.followUserButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	// check if the user inside text is valid if it is add the user else error message 
            	if(containStringList.contains(userTextArea.getText()) && !followingDefaultListmodel.contains(userTextArea.getText()) ) {
            		currentUser.addFollowings(userTextArea.getText());
            		
            		for(int i = 0; i < currentUser.getFollowings().size(); i++) {
            			if(!followingDefaultListmodel.contains(currentUser.getFollowings().get(i))) {
            				followingDefaultListmodel.addElement(currentUser.getFollowings().get(i));
            			}
            		}
            		userMap.get(userTextArea.getText()).addFollowers(currentUser.getUniqueID());
            		
            		currentFollowingListView.setModel(followingDefaultListmodel);
            		followersScrollPane.revalidate();
            		followersScrollPane.repaint();
                } else {
                	JOptionPane.showMessageDialog(frame,
            			    "Invalid User: Can't follow same user or Null.",
            			    "Error message",
            			    JOptionPane.PLAIN_MESSAGE);
                }
            	userTextArea.setText("");
            }
        });
		
		// listen for post tweet button and add message to user and followers 
		this.postTweetButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	currentUser.addNewsFeed(currentUser.getUniqueID() + ": " + tweetTextArea.getText());
            	
            	for(int i = 0; i < currentUser.getFollowers().size(); i++ ) {
            		userMap.get(currentUser.getFollowers().get(i)).addNewsFeed(currentUser.getUniqueID() + ": " + tweetTextArea.getText());
            	}
            	
            	for(int i = 0; i < currentUser.getNewsFeed().size(); i++) {
            		if(!newsFeedDefaultListmodel.contains(currentUser.getNewsFeed().get(i))) {
            			newsFeedDefaultListmodel.addElement(currentUser.getNewsFeed().get(i));
            		}
            	}
            	
            	currentUser.addMessageCount();
            	if(tweetTextArea.getText().contains("good") || tweetTextArea.getText().contains("great") || tweetTextArea.getText().contains("excellent")) {
            		currentUser.addPositiveCount();
            	}
            	
            	newsFeedListView.setModel(newsFeedDefaultListmodel);
            	tweetScrollPane.revalidate();
            	tweetScrollPane.repaint();
            	tweetTextArea.setText("");
            }
        });
	}

	@Override
	public void update(Subject subject) {
		subject.attach(this);
	}
}

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	
	private DefaultListModel<String> followingDefaultListmodel;
	private DefaultListModel<String> newsFeedDefaultListmodel;
	private ArrayList<String> containStringList;
	
	public ArrayList<String> getContainStringList() {
		return containStringList;
	}

	public void setContainStringList(ArrayList<String> containStringList) {
		this.containStringList = containStringList;
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
        
        userTextArea = new JTextArea(2,17);
        tweetTextArea = new JTextArea(2,17);
        followUserButton = new JButton("Follow User");
        postTweetButton = new JButton("Post Tweet");
        currentFollowingListView = new JList(this.currentUser.getFollowers().toArray());
        followersScrollPane = new JScrollPane();
        followersScrollPane.setViewportView(currentFollowingListView);
        
        followingDefaultListmodel = new DefaultListModel<String>();
        newsFeedDefaultListmodel = new DefaultListModel<String>();
        
        newsFeedListView = new JList(this.currentUser.getNewsFeed().toArray());
        tweetScrollPane = new JScrollPane(newsFeedListView);
        //tweetScrollPane.setViewportView(newsFeedListView);
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
            		currentUser.addFollowers(userTextArea.getText());
            		
            		for(int i = 0; i < currentUser.getFollowers().size(); i++) {
            			if(!followingDefaultListmodel.contains(currentUser.getFollowers().get(i))) {
            				followingDefaultListmodel.addElement(currentUser.getFollowers().get(i));
            			}
            		}
            		
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
		
		this.postTweetButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	
            	System.out.println(tweetTextArea.getText());
            }
        });
	}

	@Override
	public void update(Subject subject) {
		subject.attach(this);
	}
}

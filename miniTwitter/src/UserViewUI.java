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
	
	private ArrayList<String> containStringList;
	
//	public UserViewUI() {
//		initUI();
//		listeners();
//	}
	
	public ArrayList<String> getContainStringList() {
		return containStringList;
	}

	public void setContainStringList(ArrayList<String> containStringList) {
		this.containStringList = containStringList;
	}

	public UserViewUI(User user) {
		currentUser = user;
		initUI();
		listeners();
		
	}
	
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
        //followersScrollPane.setPreferredSize();
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
	
	private void listeners() {
		this.followUserButton.addActionListener(new ActionListener() {  
            @Override      
            public void actionPerformed(ActionEvent e) {
            	System.out.print(containStringList.get(0));
            	if(containStringList.contains(userTextArea.getText())) {
            		currentUser.addFollowers(userTextArea.getText());
            		DefaultListModel<String> model = new DefaultListModel<String>();
            		for(int i = 0; i < currentUser.getFollowers().size(); i++) {
            			model.addElement(currentUser.getFollowers().get(i));
            			System.out.print(currentUser.getFollowers().get(i));
            		}
            		//model.addElement(userTextArea.getText());
            		currentFollowingListView.setModel(model);
            		userTextArea.setText("");
            		
            		followersScrollPane.revalidate();
            		followersScrollPane.repaint();
            		
            		//frame.revalidate();
                    //frame.repaint();
                }
            }
        });
	}

	@Override
	public void update(Subject subject) {
		// TODO Auto-generated method stub
		followersScrollPane.repaint();
	}
}

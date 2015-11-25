package tutorial28;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Choice;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {
	
	private JPanel panel1;
	private JPanel panel2;
	private JTabbedPane tabbedPane;
	private JTextArea txtrEnterUrlHere;
	private JButton btnGetImage;
	private JButton btnGetFile;
	private Choice choice;
	private JLabel lblFormat;
	private JButton btnView;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmSaveImage;
	private JMenu mnOptions;
	private JMenuItem mntmExit;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel imageLabel;

	public MainWindow(int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(631, 471);
		getContentPane().setLayout(null);
		
		panel1 = new JPanel(null);
		panel2 = new JPanel(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 42, 605, 389);
		getContentPane().add(tabbedPane);
		setResizable(false);
		tabbedPane.addTab("Tab 1", panel1);
		
		txtrEnterUrlHere = new JTextArea();
		txtrEnterUrlHere.setText("Enter URL here...");
		txtrEnterUrlHere.setBounds(10, 40, 580, 330);
		panel1.add(txtrEnterUrlHere);
		
		btnGetImage = new JButton("Get Image");
		btnGetImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Main.setImage(new URL(txtrEnterUrlHere.getText()));
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invalid URL or no internet connection");
				}
			}
		});
		btnGetImage.setBounds(10, 11, 142, 23);
		panel1.add(btnGetImage);
		
		btnGetFile = new JButton("Get File");
		btnGetFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			JFileChooser chouser = new JFileChooser();
			int reply = chouser.showOpenDialog(null);
			if (reply==JFileChooser.APPROVE_OPTION){
			Main.setImage(chouser.getSelectedFile());
			}
			}
		});
		btnGetFile.setBounds(164, 11, 157, 23);
		panel1.add(btnGetFile);
		tabbedPane.addTab("Tab 2", panel2);
		
		choice = new Choice();
		choice.setBounds(520, 10, 70, 20);
		panel2.add(choice);
		
		choice.add("png");
		choice.add("jpg");
		
		
		lblFormat = new JLabel("Format:");
		lblFormat.setBounds(468, 10, 46, 14);
		panel2.add(lblFormat);
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if(Main.getImage()==null){return;}
				imageLabel.setIcon(new ImageIcon(Main.getImage()));
				imageLabel.updateUI();
			}
		});
		btnView.setBounds(10, 7, 89, 23);
		panel2.add(btnView);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 580, 309);
		panel2.add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		imageLabel = new JLabel("");
		panel.add(imageLabel, BorderLayout.CENTER);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 625, 21);
		getContentPane().add(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSaveImage = new JMenuItem("Save Image");
		mntmSaveImage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chouser = new JFileChooser();
				int reply = chouser.showSaveDialog(null);
				if (reply==JFileChooser.APPROVE_OPTION){
					Main.saveImage(chouser.getSelectedFile(), choice.getSelectedItem());
				}
			}
			
		});
		mnFile.add(mntmSaveImage);
		
		mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
				);
		mnOptions.add(mntmExit);
		
		setVisible(true);
	}
}
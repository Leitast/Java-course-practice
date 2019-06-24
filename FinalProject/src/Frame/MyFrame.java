package Frame;

import MyServer.*;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {
	public static JFrame child=new JFrame("日志");
	private JPanel contentPane;
	private JTextField textField;
	public static JTextArea textArea = new JTextArea();
	public static JTextArea textArea2 = new JTextArea();
	private JTextField textField_1;
	public static String frameurl=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setTitle("Web Test GUI");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(97, 96, 174, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7AEF\u53E3:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		lblNewLabel.setBounds(14, 94, 89, 37);
		contentPane.add(lblNewLabel);
		
		//JTextArea textArea = new JTextArea();
		textArea.setBounds(14, 144, 383, 313);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("\u6253\u5F00");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//server.InitWeb();
					Desktop desktop = Desktop.getDesktop();
					if(frameurl.equals("")) {
					     desktop.browse(new URI("http://localhost:"+server.port + "/all.html"));
					}
					else {
					     desktop.browse(new URI(frameurl));
					}
					server.InitWeb();
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(300, 96, 95, 37);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.port=Integer.parseInt(textField.getText()); 
				frameurl=textField_1.getText();
				//JFrame child=new JFrame("日志");
				//child.setLocation(100,50);
				//child.setSize(400,400);
				//child.setVisible(true);
				//child.add(textArea2);
			}
		});
		button.setBounds(300, 32, 95, 37);
		contentPane.add(button);
		
		JLabel lblUrl = new JLabel("URL:");
		lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrl.setFont(new Font("楷体", Font.PLAIN, 20));
		lblUrl.setBounds(14, 30, 89, 37);
		contentPane.add(lblUrl);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(97, 32, 174, 37);
		contentPane.add(textField_1);
	}
}

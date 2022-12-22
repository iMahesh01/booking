package Ticketbooking;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Ticketbooking.BookingPage;

public class BookingPage {
	private JFrame frame;
	private JTextField name;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingPage window = new BookingPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public BookingPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setBounds(100, 100, 774, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Passanger Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(74, 117, 192, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("From :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(74, 174, 107, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(74, 231, 92, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("No of Tickets :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(74, 275, 162, 33);
		frame.getContentPane().add(lblNewLabel_3);
		
		name = new JTextField();
		name.setBounds(291, 122, 162, 30);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JList list = new JList();
		list.setBounds(293, 291, 58, -47);
		frame.getContentPane().add(list);
		
		String[] a = {"select","miyapur","kukatpally","jntu","balanagar","mosapet"};
		ArrayList<String> ab = new ArrayList<String>(Arrays.asList(a));
		
		JComboBox cb1 = new JComboBox();
		cb1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cb1.setModel(new DefaultComboBoxModel(a));
		cb1.setBounds(291, 175, 162, 31);
		frame.getContentPane().add(cb1);
		JComboBox cb2 = new JComboBox();
		cb2.setModel(new DefaultComboBoxModel(new String[] {"select"}));
		cb2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=(String) cb1.getSelectedItem();
				ArrayList<String> bc= new ArrayList<String>();
				Object[] b= {"select"};
				if(!str.equalsIgnoreCase("select")) {
					for(int i=0;i<a.length;i++) {
						if(str.equals(ab.get(i)))
							continue;
						else
							bc.add(ab.get(i));
					}
					b=bc.toArray();
					;
					cb2.setModel(new DefaultComboBoxModel(b));
				}	
			}
		});
		
		cb2.setBounds(291, 232, 162, 31);
		frame.getContentPane().add(cb2);
		
		JComboBox no = new JComboBox();
		no.setFont(new Font("Tahoma", Font.PLAIN, 20));
		no.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		no.setBounds(291, 284, 162, 33);
		frame.getContentPane().add(no);
		
		Label label = new Label("Tiket Booking");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(187, 10, 221, 76);
		frame.getContentPane().add(label);
		
		
		
		JButton btnNewButton = new JButton("Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customer_name=name.getText();
				String to_station=(String) cb1.getSelectedItem();
				String from_station = (String) cb2.getSelectedItem();
				String no_of_tickets=(String) no.getSelectedItem();
				int total=Integer.parseInt(no_of_tickets);
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking","root","root");
					Statement st=con.createStatement();
					String str="insert into ticketdata values('"+customer_name+"','"+from_station+"','"+to_station+"',"+no_of_tickets+");";
					System.out.println(str);
					st.executeUpdate(str);
					JOptionPane.showMessageDialog(btnNewButton, "Booking done");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(70, 358, 152, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Booking Details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database s = new Database();
				s.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(315, 359, 201, 32);
		frame.getContentPane().add(btnNewButton_1);
	}
}
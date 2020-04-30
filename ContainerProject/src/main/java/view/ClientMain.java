package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import model.Application;
import model.Client;


public class ClientMain extends TopMain{
	
	private Client currentClient;
//	private JourneySectionPanels j;
//	private ContainerSelectionPanels cont;

//	public JourneySectionPanels getJ() {
//		return j;
//	}
//
//	public ContainerSelectionPanels getCont() {
//		return cont;
//	}
	
//	private JPanel options;
//	private JPanel cards;
//	private CardLayout cl;
//
//	public JPanel getCards() {
//		return cards;
//	}
//
//	public CardLayout getCl() {
//		return cl;
//	}

	public Client getCurrentClient() {
		return currentClient;
	}

	public ClientMain(final Application application, final JFrame login) {
		super(application, login);

//		final JFrame client = new JFrame("Client Overview");
//
//		// CardLayout
//		cards = new JPanel(new CardLayout());
////		
//		options(database, main, client);
////		
//		cl = (CardLayout)(cards.getLayout());
////		
////		
//		client.add(options, BorderLayout.WEST);
//		client.add(cards, BorderLayout.EAST);
//		cl.show(cards, "menu");
//		client.pack();		
//		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		client.setVisible(true);
	}
	
	@Override
	public void options(Application application, JFrame login) {
		currentClient = application.getCurrentUser();
		setOptions(new JPanel());
		getOptions().setLayout(new BoxLayout(getOptions(), BoxLayout.Y_AXIS));
		
		JButton menu = new JButton("Profile");
		menu.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		getOptions().add(menu);
		menuButton(application, login, menu);
		
//		JButton clients = new JButton("View Clients");
//		clients.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
//		options.add(clients);
//		clientButton(database, main, clients);
		
		
		JButton journeys = new JButton("My Journeys");
		journeys.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		getOptions().add(journeys);
		journeyButton(application, login, journeys);
		
		
		JButton containers = new JButton("My Containers");
		containers.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		getOptions().add(containers);
		containerButton(application, login, containers);
		
		
//		JButton simulation = new JButton("Start Simulation");
//		simulation.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
//		options.add(simulation);
//		simulationButton(database, main, simulation);
		
	
	} 


//	public void menuPanel(Database database, JFrame login, JPanel menupanel) {
//		menupanel.removeAll();
//		
//		JPanel clientDetails = new JPanel();
//		clientDetails.setLayout(new BoxLayout(clientDetails, BoxLayout.Y_AXIS));
//		
//		JLabel lbl = new JLabel("Description");
//		Font f = lbl.getFont();
//		lbl.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
//		JLabel company = new JLabel("Company: " + currentClient.getCompany());
//		JLabel ref = new JLabel("Reference person: " + currentClient.getName());
//		JLabel mail = new JLabel("E-mail: " + currentClient.getEmail());
//		JLabel address = new JLabel("Address: " + currentClient.getAddress());
//		JLabel id = new JLabel("Company id: " + currentClient.getId());
//		clientDetails.add(lbl);
//		clientDetails.add(company);
//		clientDetails.add(ref);
//		clientDetails.add(mail);
//		clientDetails.add(address);
//		clientDetails.add(id);
//		menupanel.add(clientDetails, BorderLayout.CENTER);
//		
//		logOutButton(database, login, menupanel);
//	}
//	
//	public void clientButton(Database database, JFrame main, JButton clients) {
//		ClientSectionPanels c = new ClientSectionPanels(database, this);
//		cards.add(c.getClientSearch(), "clientSearch");
//		cards.add(c.getViewClients(), "viewClients");
//		clients.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				
//				cl.show(cards, "clientSearch");
//			}
//		});
//		
//	}
	@Override
	public void journeyButton(final Application application, JFrame login, JButton journeys) {
		// journey section
		
		setJ(new JourneySectionPanels(application, this));
		getCards().add(getJ().getJourneySearch(), "journeySearch");
		getCards().add(getJ().getViewJourneys(), "viewJourneys");
		
		journeys.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				removeListeners();
				application.addObserver(getJ());
				getCl().show(getCards(),  "journeySearch");
			}
		});
		

		
	}
	
	@Override
	public void containerButton(final Application application, JFrame login, JButton containers) {
	// container section
	
		setCont(new ContainerSelectionPanels(application, this));
		getCards().add(getCont().getContainerSearch(), "containerSearch");
		getCards().add(getCont().getViewContainers(), "viewContainers");
		getCards().add(getCont().getPlotPanel(), "plotPanel");
		
		containers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeListeners();
				application.addObserver(getCont());
				getCl().show(getCards(),  "containerSearch");
				}
		});
			
	}
	
//	@Override
//	public void logOutButton(final Database database, final JFrame login, final JPanel menupanel) {
//		// Logout as company user
//		
//		final JButton profile = new JButton("Profile");
//		ImageIcon img = new ImageIcon("src/main/resources/profile.png");
//		Image image = img.getImage(); // transform it 
//		Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
//		img = new ImageIcon(newimg);  // transform it back
//	    profile.setIcon(img);
//	    
//		JPanel top = new JPanel(new BorderLayout());
//		menupanel.add(top, BorderLayout.NORTH);
//		top.add(profile, BorderLayout.EAST);
//		final JPopupMenu menu = new JPopupMenu("Profile Options");
//		
//		JMenuItem setDetails = new JMenuItem("Update profile details");
//		JMenuItem logout = new JMenuItem("Logout");
//
//		menu.add(setDetails);
//		menu.add(logout);
//
//		profile.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				
//				Component b=(Component)e.getSource();
//				Point p=b.getLocationOnScreen();
//				menu.show(profile, 0, 0);;
//				menu.setLocation(p.x,p.y+b.getHeight());
//				
//			}
//		});
//		
//		logout.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				login.setVisible(true);
//				getMain1().dispose();
//			}
//		});
//		
//		setDetails.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent e) {
//				updateClientDetails(database);
//			}		
//		});
//	}
//
//
//	public void updateClientDetails(final Database database) {
//		final JFrame updateDetails = new JFrame("Update details");
//		updateDetails.setPreferredSize(new Dimension(300, 200));
//		
//		JPanel textFields = new JPanel();
//		textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
//			
////			JPanel company = new JPanel();
////			company.add(new JLabel("Company name: "));
////			JTextField companyText = new JTextField("companyname");
////			companyText.setPreferredSize(new Dimension(100, 25));
////			company.add(companyText);
////			textFields.add(company);
//			
//		JPanel refname = new JPanel();
//		refname.add(new JLabel("Reference name: "));
//		final JTextField refnameText = new JTextField(currentClient.getName());
//		refnameText.setPreferredSize(new Dimension(100, 25));
//		refname.add(refnameText);
//		textFields.add(refname);
//			
//		JPanel email = new JPanel();
//		email.add(new JLabel("Email: "));
//		final JTextField emailText = new JTextField(currentClient.getEmail());
//		emailText.setPreferredSize(new Dimension(100, 25));
//		email.add(emailText);
//		textFields.add(email);
//			
//		JPanel address = new JPanel();
//		address.add(new JLabel("Address: "));
//		final JTextField addressText = new JTextField(currentClient.getAddress());
//		addressText.setPreferredSize(new Dimension(100, 25));
//		address.add(addressText);
//		textFields.add(address);
//		
//		JPanel password = new JPanel();
//		password.add(new JLabel("Password: "));
//		final JPasswordField passwordText = new JPasswordField(currentClient.getPassword());
//		passwordText.setPreferredSize(new Dimension(100, 25));
//		password.add(passwordText);
//		textFields.add(password);
//			
//		JButton confirm = new JButton("Confirm changes");
//		confirm.addActionListener(new ActionListener() {
//				
//			public void actionPerformed(ActionEvent e) {
//				String name = refnameText.getText();
//				String mail = emailText.getText();
//				String address = addressText.getText();
//				String password = new String(passwordText.getPassword());
//					
//				database.updateClientName(currentClient, name);
//				database.updateClientMail(currentClient,mail);
//				database.updateClientAddress(currentClient,address);
//				database.updateClientPassword(currentClient, password);
//					
//				updateDetails.dispose();
//				
//					
//			}
//		});
//			
//		updateDetails.add(textFields, BorderLayout.CENTER);
//		updateDetails.add(confirm, BorderLayout.SOUTH);
//		updateDetails.pack();
//		updateDetails.setVisible(true);
//	}
	
//	public void simulationButton(final Database database, JFrame main, JButton simulation) {	
//		// simulation section
//		
//		JPanel sim = new JPanel();
//		sim.setPreferredSize(new Dimension(800, 600));
//		cards.add(sim, "sim");
//		
//		JLabel lbldays = new JLabel("amount of days");
//		final JTextField days = new JTextField();
//		days.setPreferredSize(new Dimension(100, 25));
//		
//		JButton start = new JButton("Press Start");
//		
//		start.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				Simulator simulation = new Simulator();
//				simulation.Simulation(database, Integer.parseInt(days.getText()));
//			}
//		});
//		sim.add(lbldays);
//		sim.add(days);
//		sim.add(start);
//		
//		simulation.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				cl.show(cards, "sim");
//			}
//		});
//	}
	
//	public void logOutButton(Database database, final JFrame main, final JFrame company, JPanel menupanel) {
//		// Logout as company user
//		
//		JButton logout = new JButton("Logout");
//		JPanel top = new JPanel(new BorderLayout());
//		menupanel.add(top, BorderLayout.NORTH);
//		top.add(logout, BorderLayout.EAST);
//		logout.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				main.setVisible(true);
//				company.dispose();
//			}
//		});
//	}
}
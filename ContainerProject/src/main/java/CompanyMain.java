import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CompanyMain {
	
	private JPanel cards;
	private CardLayout cl;

	public JPanel getCards() {
		return cards;
	}

	public CardLayout getCl() {
		return cl;
	}

	public CompanyMain(final Database database) {
		
		final JFrame company = new JFrame("Company Overview");
		
		JPanel options = new JPanel();
		final JPanel rest = new JPanel();
		rest.setPreferredSize(new Dimension(800, 600));
		rest.setBackground(Color.RED);
		final JPanel rest2 = new JPanel(new CardLayout());
		rest2.setPreferredSize(new Dimension(800, 600));
		rest2.setBackground(Color.BLUE);
		cards = new JPanel(new CardLayout());
		cards.add(rest, "rest");
		cards.add(rest2, "rest2");
		
		JButton clients = new JButton("View Clients");
		clients.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		
		JButton journeys = new JButton("View Journeys");
		journeys.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		
		JButton containers = new JButton("View Containers");
		containers.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		
		JButton simulation = new JButton("Start Simulation");
		simulation.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	
		
		
		// menu options panel
		
		ClientSectionPanels c = new ClientSectionPanels(database, this);
		cards.add(c.getClientSearch(), "clientSearch");
		cards.add(c.getViewClients(), "viewClients");
		
		JourneySectionPanels j = new JourneySectionPanels(database, this);
		cards.add(j.getJourneySearch(), "journeySearch");
		cards.add(j.getViewJourneys(), "viewJourneys");
		
		JPanel sim = new JPanel();
		sim.setPreferredSize(new Dimension(800, 600));
		cards.add(sim, "sim");
		
		// CardLayout
		cl = (CardLayout)(cards.getLayout());
		
		// client section
		
		clients.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cl.show(cards, "clientSearch");
			}
		});
		
		
		
		// journey section
		
		journeys.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cl.show(cards,  "journeySearch");
			}
		});
		
		// container section
		
		
		
		// simulation section
		
		JLabel lbldays = new JLabel("amount of days");
		final JTextField days = new JTextField();
		days.setPreferredSize(new Dimension(100, 25));
		
		JButton start = new JButton("Press Start");
		
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Simulator simulation = new Simulator();
				simulation.Simulation(database, Integer.parseInt(days.getText()));
			}
		});
		sim.add(lbldays);
		sim.add(days);
		sim.add(start);
		
		simulation.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "sim");
			}
		});
		
		
		
		// rest panel
		
		JButton con = new JButton("Continue");
		JLabel lbl = new JLabel("Description");
		rest.add(lbl, BorderLayout.NORTH);
		rest.add(con, BorderLayout.PAGE_END);
		con.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "rest2");
			}
			
		});
		
		
		
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		company.add(options, BorderLayout.WEST);
		company.add(cards, BorderLayout.EAST);
		cl.show(cards, "rest");
		options.add(clients);
		options.add(journeys);
		options.add(containers);
		options.add(simulation);
		company.pack();		
		company.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		company.setVisible(true);
	}
	
}

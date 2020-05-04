package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Container;
import model.Application;
import model.Journey;

public class JourneySectionPanels implements PropertyChangeListener {

	private JPanel journeySearch;
	private JPanel viewJourneys;
	private ArrayList<Journey> wJourneys = new ArrayList<Journey>();
	private boolean showAllCommand;
	private Application application;
	private TopMain topmain;
	private String keyword;
	private boolean isPast;
	private JButton showAll;
	
	public ArrayList<Journey> getwJourneys() {
		return wJourneys;
	}

	public JPanel getJourneySearch() {
		return journeySearch;
	}

	public JPanel getViewJourneys() {
		return viewJourneys;
	}

	public JourneySectionPanels(final Application application, final TopMain topmain) {
		
		this.application = application;
		this.topmain = topmain;
		
		journeySearch = new JPanel();
		journeySearch.setPreferredSize(new Dimension(800, 600));
		
		viewJourneys = new JPanel(new BorderLayout());
		viewJourneys.setPreferredSize(new Dimension(800, 600));
			
		searchActiveJourneys(application, topmain);
			
		showAll(application, topmain, false);
			
		journeyPastSearch(application, topmain);
			
		showAll(application, topmain, true);	
		
		if (topmain instanceof ClientMain) {
			signUpGoods(application, topmain);
		}
	}
	public void showAll(final Application application, final TopMain topmain, final boolean b) {
		showAll = new JButton("Show All");
		journeySearch.add(showAll);
		showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Journey> journeys = new ArrayList<Journey>();
				if (b) {
					journeys = application.getJourneyContainerDat().getPastJourneys();
				}
				else {
					journeys = application.getJourneyContainerDat().getActiveJourneys();	
				}
				showAllCommand = true;
				isPast = b;
				ArrayList<Journey> result = filterJourneysForClients(application, topmain, journeys);
				if (topmain instanceof ClientMain) {
					wJourneys = result;
				}
				else {
					wJourneys = result;
				}
				checksSearchEntryC(application, topmain);
			}
		});
	}
	public void checksSearchEntryC(final Application application, final TopMain topmain) {
		if (wJourneys.size() == 0) {
			if (showAllCommand) {
				new ErrorFrame();
			}
			else {
				new ErrorFrame(keyword);
			}
		}
		else {
			displayJourneys();
			topmain.getCl().show(topmain.getCards(), "viewJourneys");
		}
	}
	
	public ArrayList<Journey> filterJourneysForClients(final Application application, final TopMain topmain, ArrayList<Journey> unfiltered) {
		if (topmain instanceof ClientMain) {
			ArrayList<Journey> result = new ArrayList<Journey>();
			result.addAll(application.findClientJourneys(unfiltered));
			return result;
		}
		else {
			return unfiltered;
		}
	}
	
	public ArrayList<Journey> filterActiveJourneysForClient(final Application application, final TopMain topmain) {
		if (topmain instanceof ClientMain) {
			Set<Journey> clientJourneys = application.findClientJourneys(application.getJourneyContainerDat().getActiveJourneys());
			ArrayList<Journey> result = new ArrayList<Journey>();
			result.addAll(clientJourneys);
			return result;
		}
		else {
			return application.getJourneyContainerDat().getActiveJourneys();
		}
		
	}

//	public void showAllPast(final Application application, final TopMain topmain) {
//		JButton showAllPast = new JButton("Show All");
//		journeySearch.add(showAllPast);
//		showAllPast.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				isPast = true;
//				showAllCommand = true;
//				ArrayList<Journey> result = new ArrayList<Journey>(filterPastJourneysForClient(application, topmain));
//				wJourneys = result;
//				checksSearchEntryJ(application, topmain);
//			}
//
//		});
//	}

	public void journeyPastSearch(final Application application, final TopMain topmain) {
		JLabel journeyPast = new JLabel("Journey's History");
		journeySearch.add(journeyPast);
		final JTextField searchjourneyPastTxt = new JTextField();
		searchjourneyPastTxt.setPreferredSize(new Dimension(100, 25));
		journeySearch.add(searchjourneyPastTxt);
		
		JButton journeyPastSearch = new JButton("Search");
		journeySearch.add(journeyPastSearch);
		journeyPastSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				isPast = true;
				showAllCommand = false;
				ArrayList<Journey> result = new ArrayList<Journey>(filterPastJourneysForClient(application, topmain));
				keyword = searchjourneyPastTxt.getText();
				wJourneys = application.findUsingLoop(keyword, result);
				checksSearchEntryJ(application, topmain);
			}
		});
	}

	public ArrayList<Journey> filterPastJourneysForClient(Application application, TopMain topmain) {
		if (topmain instanceof ClientMain) {
			Set<Journey> clientJourneys = application.findClientJourneys(application.getJourneyContainerDat().getPastJourneys());
			ArrayList<Journey> result = new ArrayList<Journey>();
			result.addAll(clientJourneys);
			return result;
		}
		else {
			return application.getJourneyContainerDat().getPastJourneys();
		}
	}

//	public void showAllActiveJourneys(final Application application, final TopMain topmain) {
//		
//		JButton showAllActive = new JButton("Show All");
//		journeySearch.add(showAllActive);
//		showAllActive.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//				isPast = false;
//				showAllCommand = true;
//				ArrayList<Journey> result = new ArrayList<Journey>(filterActiveJourneysForClient(application, topmain));
//				wJourneys = result;
//				checksSearchEntryJ(application, topmain);
//			}
//
//		});
//	}

	public void searchActiveJourneys(final Application application, final TopMain topmain) {
		
		JLabel journeyActive = new JLabel("Active Journeys");
		final JTextField searchActive = new JTextField();
		searchActive.setPreferredSize(new Dimension(100, 25));
		journeySearch.add(journeyActive);
		journeySearch.add(searchActive);
		
		JButton searchActiveButton = new JButton("Search");
		journeySearch.add(searchActiveButton);
		
		searchActiveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				isPast = false;
				showAllCommand = false;
				ArrayList<Journey> result = new ArrayList<Journey>(filterActiveJourneysForClient(application, topmain));
				keyword = searchActive.getText();
				application.findUsingLoop(keyword, result);
				wJourneys = application.findUsingLoop(keyword, result);;
				checksSearchEntryJ(application, topmain);
			}
		});
	}
	
	// Change current location of a journey
	
	public void changeloc(final Application application) {
		
		JPanel locationUpdate = new JPanel(new BorderLayout());
		viewJourneys.add(locationUpdate, BorderLayout.SOUTH);
		JPanel updateLocation = new JPanel(new BorderLayout());
		locationUpdate.add(updateLocation, BorderLayout.CENTER);
		String[] options = new String[application.getJourneyContainerDat().getActiveJourneys().size()];
		int i = 0;
		for (Journey j : application.getJourneyContainerDat().getActiveJourneys()) {
			options[i] = j.getId();
			i++;
		}
		final JComboBox<String> id = new JComboBox<String>(options);
		updateLocation.add(id, BorderLayout.NORTH);
		
		final JTextField loc = new JTextField();
		loc.setPreferredSize(new Dimension(100, 25));
		updateLocation.add(loc, BorderLayout.CENTER);
		
		JButton update = new JButton("Update Location");
		update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String newcurrentLocation = loc.getText();
				ArrayList<Journey> result = new ArrayList<Journey>();
				result.addAll(application.findUsingLoop(id.getSelectedItem().toString(), application.getJourneyContainerDat().getActiveJourneys()));
				Journey j = result.get(0);
				application.updateCurrentLocation(j, newcurrentLocation);
				application.endOfJourney(j);
			}
		});
		locationUpdate.add(update, BorderLayout.SOUTH);
	}
	
	public void signUpGoods(final Application application, final TopMain topmain) {
		
		JPanel journeySearchRest = new JPanel(new BorderLayout());
		journeySearch.add(journeySearchRest);
		// preferred would be a picture instead of space!!!
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(350,320));
		journeySearchRest.add(space, BorderLayout.NORTH);
		JPanel signUp = new JPanel(new BorderLayout());
		journeySearchRest.add(signUp, BorderLayout.CENTER);
		JLabel lbl = new JLabel("Sign up your goods for a new journey here!");
		lbl.setPreferredSize(new Dimension(100,70));
		signUp.add(lbl, BorderLayout.NORTH);
		String[] options = new String[application.getJourneyContainerDat().getActiveJourneys().size()];
		int i = 0;
		for (Journey j : application.getJourneyContainerDat().getActiveJourneys()) {
			options[i] = j.getId();
			i++;
		}
		JPanel inputs = new JPanel();
		inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
		signUp.add(inputs, BorderLayout.CENTER);
		
		final JTextField content = new JTextField();
		content.setPreferredSize(new Dimension(100, 25));
		inputs.add(new JLabel("Input content: "));
		inputs.add(content);
		
		final JTextField origin = new JTextField();
		origin.setPreferredSize(new Dimension(100, 25));
		inputs.add(new JLabel("Input origin: "));
		inputs.add(origin);
		
		final JTextField destination = new JTextField();
		destination.setPreferredSize(new Dimension(100, 25));
		inputs.add(new JLabel("Input destination: "));
		inputs.add(destination);
		
		
		JButton confirm = new JButton("Confirm new journey");
		confirm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String newContent = content.getText();
				String newOrigin = origin.getText();
				String newDestination = destination.getText();
				application.createJourney(newOrigin, newDestination, newContent, application.getCurrentUser().getCompany());
			}
		});
		signUp.add(confirm, BorderLayout.SOUTH);
	}
	
	public void checksSearchEntryJ(final Application application, final TopMain topmain) {
		if (wJourneys.size() == 0) {
			if (showAllCommand) {
				new ErrorFrame();
			}
			else {
				new ErrorFrame(keyword);
			}
		}
		else {
			displayJourneys();
			topmain.getCl().show(topmain.getCards(), "viewJourneys");
		}
	}
	
	// view Journeys
	
	public void displayJourneys() {
		viewJourneys.removeAll();
		if (topmain instanceof CompanyMain) {
			changeloc(application);
		}
		
		DefaultTableModel tableModel = new DefaultTableModel();
		setTableLabel();
		JTable table = new JTable(tableModel);
		String[] columnNames = {
				"ID",
                "Origin",
                "Destination",
                "cur. Location",
                "Container ID's"
                };
		
		for (String s : columnNames) {
			tableModel.addColumn(s);
		}
		for (Journey j : wJourneys) {
			ArrayList<String> containerids = filterClientContainers(application, topmain, j);
			tableModel.insertRow(0, new Object[] {j.getId(),j.getOrigin(),j.getDestination(),j.getCurrentLocation(), containerids});
		}
		viewJourneys.add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setTableLabel() {
		JLabel label = new JLabel("");
		if (isPast == false && showAllCommand) {
			label = new JLabel("All active containers");
			Font f = label.getFont();
			label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
		}
		else if ( isPast && showAllCommand) {
			label = new JLabel("All past containers");
			Font f = label.getFont();
			label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
		}
		else if (isPast == false && showAllCommand == false) {
			label = new JLabel("Active containers related to " + keyword);
			Font f = label.getFont();
			label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
		}
		else if (isPast && showAllCommand == false) {
			label = new JLabel("Past containers related to " + keyword);
			Font f = label.getFont();
			label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
		}
		viewJourneys.add(label, BorderLayout.NORTH);
	}
	
	public ArrayList<String> filterClientContainers(Application application, TopMain topmain, Journey j){
		ArrayList<String> containerids = new ArrayList<String>();
		if ( topmain instanceof CompanyMain) {
			for (Container c : j.getContainers()) {
				containerids.add(c.getContainerId());
			}
		}
		
		else if ( topmain instanceof ClientMain) {
			for (Container c : j.getContainers()) {
				if (c.getCompany().contentEquals(application.getCurrentUser().getCompany())) {
				containerids.add(c.getContainerId());
				}
			}
		}
		return containerids;
	}

	public void propertyChange(PropertyChangeEvent evt) {

		Application dat = ((Application)evt.getSource());
		if (wJourneys.size()!= 0) {
			if ((isPast && (evt.getPropertyName().contentEquals("history")))) {
				
				ArrayList<Journey> jList = new ArrayList<Journey>(filterPastJourneysForClient(dat, topmain));
				showAllOrSearch(jList, dat);
			}
			else if (isPast == false && (evt.getPropertyName().contentEquals("journey"))) {
				ArrayList<Journey> jList = new ArrayList<Journey>(filterActiveJourneysForClient(dat, topmain));
				showAllOrSearch(jList, dat);
			}
			displayJourneys();
			topmain.getMain1().revalidate();
		}
	}

	public void showAllOrSearch(ArrayList<Journey> jList, Application dat) {
		if (showAllCommand) {
			wJourneys = jList;
		}
		else {
			wJourneys = dat.findUsingLoop(keyword,jList);
		}
	}
	
}
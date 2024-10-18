package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class SampETO extends JFrame {

	private static final long serialVersionUID = 1L;
	private int[] travelOrdersPerEmployee;
	private String[] employeeDetails;
	private int totalEmployees;
	private List<String> employeeIds;
	private int employeeCount = 0;
	private int passNum = 0;
	private int travelNum = 12345;

	private JPanel JPane;
	private JTextField TFEmpNum, TFEmpName, TFEmpID;
	private JTextArea TADisplay;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SampETO frame = new SampETO();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public SampETO() {
		employeeIds = new ArrayList<>();
		setAutoRequestFocus(false);
		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 294);
		JPane = new JPanel();
		JPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JPane);
		JPane.setLayout(new CardLayout(0, 0));

		JPanel FirstPanel = new JPanel();
		JPane.add(FirstPanel, "FirstPanel");
		FirstPanel.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome to RMAS Corporation!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWelcome.setBounds(83, 21, 248, 30);
		FirstPanel.add(lblWelcome);

		JLabel lblEmpNum = new JLabel("Enter the number of employees:");
		lblEmpNum.setBounds(32, 108, 190, 14);
		FirstPanel.add(lblEmpNum);

		TFEmpNum = new JTextField();
		TFEmpNum.setBounds(219, 105, 159, 20);
		FirstPanel.add(TFEmpNum);
		TFEmpNum.setColumns(10);

		JButton btnEmpNum = new JButton("Submit");
		btnEmpNum.addActionListener(e -> {
			String input = TFEmpNum.getText();
			try {
				totalEmployees = Integer.parseInt(input);
				if (totalEmployees <= 0) {
					JOptionPane.showMessageDialog(SampETO.this, "Number of employees must be greater than 0.");
					return;
				}
				travelOrdersPerEmployee = new int[totalEmployees];
				employeeDetails = new String[totalEmployees];

				CardLayout cl = (CardLayout) (JPane.getLayout());
				cl.show(JPane, "SecondPanel");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(SampETO.this, "Please enter a valid number.");
			}
		});

		btnEmpNum.setBounds(159, 136, 89, 23);
		FirstPanel.add(btnEmpNum);

		JLabel lblCaption = new JLabel("Employment and Travel Order Management System");
		lblCaption.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblCaption.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaption.setBounds(32, 45, 346, 14);
		FirstPanel.add(lblCaption);

		JPanel SecondPanel = new JPanel();
		JPane.add(SecondPanel, "SecondPanel");
		SecondPanel.setLayout(null);

		JLabel lblTracking = new JLabel("Enter details for Employee " + (employeeCount + 1));
		lblTracking.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTracking.setBounds(40, 58, 270, 14);
		SecondPanel.add(lblTracking);

		JLabel lblEmpDetails = new JLabel("Employee Details");
		lblEmpDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmpDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpDetails.setBounds(102, 11, 208, 29);
		SecondPanel.add(lblEmpDetails);

		JLabel lblEmpName = new JLabel("Enter Employee Name:");
		lblEmpName.setBounds(40, 97, 146, 14);
		SecondPanel.add(lblEmpName);

		JLabel lblEmpID = new JLabel("Enter Employee ID:");
		lblEmpID.setBounds(40, 128, 146, 14);
		SecondPanel.add(lblEmpID);

		TFEmpName = new JTextField();
		TFEmpName.setBounds(177, 94, 191, 20);
		SecondPanel.add(TFEmpName);
		TFEmpName.setColumns(10);

		TFEmpID = new JTextField();
		TFEmpID.setBounds(159, 125, 210, 20);
		SecondPanel.add(TFEmpID);
		TFEmpID.setColumns(10);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(e -> {
			if (employeeCount < totalEmployees) {
				String name = TFEmpName.getText().trim();
				String id = TFEmpID.getText().trim();

				if (name.isEmpty() || id.isEmpty()) {
					JOptionPane.showMessageDialog(SampETO.this, "All fields are required.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!name.matches("[a-zA-Z ]+")) {
					JOptionPane.showMessageDialog(SampETO.this,
							"Please enter a valid name format (letters and spaces only).", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!id.matches("\\d{5}")) {
					JOptionPane.showMessageDialog(SampETO.this, "Employee ID must contain exactly 5 digits.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (employeeIds.contains(id)) {
					JOptionPane.showMessageDialog(SampETO.this, "Employee ID already exists. Please enter a unique ID.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				employeeIds.add(id);

				employeeDetails[employeeCount] = "   Employee " + (employeeCount + 1) + "\n" + "     Name: " + name
						+ "\n" + "     ID: " + id + "\n" + "     Pass Number: EP000" + (passNum + 1) + "\n";

				while (true) {
					String travelOrderInput = JOptionPane.showInputDialog(SampETO.this,
							"Enter the number of travel orders for Employee " + (employeeCount + 1) + ":");
					if (travelOrderInput == null) {
						int confirm = JOptionPane.showConfirmDialog(SampETO.this,
								"Do you want to cancel entering this employee's details?", "Confirm Cancel",
								JOptionPane.YES_NO_OPTION);
						if (confirm == JOptionPane.YES_OPTION) {
							employeeIds.remove(id);
							clearInputs();
							return;
						} else {
							continue;
						}
					}

					travelOrderInput = travelOrderInput.trim();

					if (travelOrderInput.isEmpty()) {
						JOptionPane.showMessageDialog(SampETO.this, "This field is required.");
						continue;
					}

					try {
						int travelOrders = Integer.parseInt(travelOrderInput);

						if (travelOrders <= 0) {
							JOptionPane.showMessageDialog(SampETO.this, "Travel Order must be greater than 0.");
							continue;
						}

						if (travelOrders > 3) {
							JOptionPane.showMessageDialog(SampETO.this,
									"Travel Order exceeds limit of 3. It has been set to 3.");
							travelOrders = 3;
						}

						travelOrdersPerEmployee[employeeCount] = travelOrders;
						break;

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(SampETO.this, "Please enter a valid number.");
					}
				}

				boolean proceed = collectTravelOrderDetails();
				if (proceed) {
					employeeCount++;
					passNum++;
					if (employeeCount < totalEmployees) {
						clearInputs();
						lblTracking.setText("Enter details for Employee " + (employeeCount + 1));
						CardLayout cl = (CardLayout) (JPane.getLayout());
						cl.show(JPane, "SecondPanel");
					} else {
						CardLayout cl = (CardLayout) (JPane.getLayout());
						cl.show(JPane, "FourthPanel");
						displayDetails();
					}
				}
			}
		});

		btnCreate.setBounds(159, 156, 89, 23);
		SecondPanel.add(btnCreate);

		JPanel ThirdPanel = new JPanel();
		JPane.add(ThirdPanel, "FourthPanel");
		ThirdPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 404, 195);
		ThirdPanel.add(scrollPane);

		TADisplay = new JTextArea();
		TADisplay.setBackground(SystemColor.window);
		TADisplay.setEditable(false);
		scrollPane.setViewportView(TADisplay);

		JLabel lblDetails = new JLabel("Employee and Travel Order Details");
		lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDetails.setBounds(10, 19, 404, 14);
		ThirdPanel.add(lblDetails);

		// Initialize to show the first panel
		CardLayout cl = (CardLayout) (JPane.getLayout());
		cl.show(JPane, "FirstPanel");
	}

	private boolean collectTravelOrderDetails() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

		for (int i = 0; i < travelOrdersPerEmployee[employeeCount]; i++) {
			String destination;
			while (true) {
				destination = JOptionPane.showInputDialog(SampETO.this,
						"Enter Destination for Travel Order " + (i + 1) + " of Employee " + (employeeCount + 1));
				if (destination == null) {
					int confirm = JOptionPane.showConfirmDialog(SampETO.this,
							"Do you want to cancel entering this employee's travel orders?", "Confirm Cancel",
							JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						employeeIds.remove(employeeDetails[employeeCount].split("\n")[2].split(": ")[1]);
						employeeDetails[employeeCount] = null;
						clearInputs();
						return false;
					} else {
						continue;
					}
				}
				destination = destination.trim();
				if (destination.isEmpty()) {
					JOptionPane.showMessageDialog(SampETO.this, "Destination is required.");
				} else {
					break;
				}
			}

			String travelDate;
			LocalDate parsedTravelDate = null;
			while (true) {
				travelDate = JOptionPane.showInputDialog(SampETO.this,
						"Enter Travel Date (MM-DD-YYYY) for Travel Order " + (i + 1) + " of Employee "
								+ (employeeCount + 1));
				if (travelDate == null) {
					int confirm = JOptionPane.showConfirmDialog(SampETO.this,
							"Do you want to cancel entering this employee's travel orders?", "Confirm Cancel",
							JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						employeeIds.remove(employeeDetails[employeeCount].split("\n")[2].split(": ")[1]);
						employeeDetails[employeeCount] = null;
						clearInputs();
						return false;
					} else {
						continue;
					}
				}
				travelDate = travelDate.trim();
				if (travelDate.isEmpty()) {
					JOptionPane.showMessageDialog(SampETO.this, "Travel Date is required.");
					continue;
				}
				try {
					parsedTravelDate = LocalDate.parse(travelDate, dateFormatter);
					if (!parsedTravelDate.format(dateFormatter).equals(travelDate)) {
						JOptionPane.showMessageDialog(SampETO.this, "Invalid date format. Please use MM-DD-YYYY.");
					} else if (parsedTravelDate.isBefore(LocalDate.now())) {
						JOptionPane.showMessageDialog(SampETO.this, "The travel date cannot be in the past.");
					} else {
						break;
					}
				} catch (DateTimeParseException e) {
					JOptionPane.showMessageDialog(SampETO.this, "Invalid date format. Please use MM-DD-YYYY.");
				}
			}

			String travelReturn;
			LocalDate parsedTravelReturn = null;
			while (true) {
				travelReturn = JOptionPane.showInputDialog(SampETO.this,
						"Enter Return Date (MM-DD-YYYY) for Travel Order " + (i + 1) + " of Employee "
								+ (employeeCount + 1));
				if (travelReturn == null) {
					int confirm = JOptionPane.showConfirmDialog(SampETO.this,
							"Do you want to cancel entering this employee's travel orders?", "Confirm Cancel",
							JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						employeeIds.remove(employeeDetails[employeeCount].split("\n")[2].split(": ")[1]);
						employeeDetails[employeeCount] = null;
						clearInputs();
						return false;
					} else {
						continue;
					}
				}
				travelReturn = travelReturn.trim();
				if (travelReturn.isEmpty()) {
					JOptionPane.showMessageDialog(SampETO.this, "Return Date is required.");
					continue;
				}
				try {
					parsedTravelReturn = LocalDate.parse(travelReturn, dateFormatter);
					if (!parsedTravelReturn.format(dateFormatter).equals(travelReturn)) {
						JOptionPane.showMessageDialog(SampETO.this, "Invalid date format. Please use MM-DD-YYYY.");
					} else if (parsedTravelReturn.isBefore(parsedTravelDate)) {
						JOptionPane.showMessageDialog(SampETO.this, "Return date cannot be before the travel date.");
					} else {
						break;
					}
				} catch (DateTimeParseException e) {
					JOptionPane.showMessageDialog(SampETO.this, "Invalid date format. Please use MM-DD-YYYY.");
				}
			}

			String travelDetails = "\n   Travel Order " + (i + 1) + ":\n" + "     Destination: " + destination + "\n"
					+ "     Order Number: TO" + travelNum + "\n" + "     Travel Date: " + travelDate + "\n"
					+ "     Return Date: " + travelReturn + "\n";

			employeeDetails[employeeCount] += travelDetails;
			travelNum++;
		}
		return true;
	}

	private void displayDetails() {
		StringBuilder allDetails = new StringBuilder();
		for (String detail : employeeDetails) {
			if (detail != null) {
				allDetails.append(detail).append("\n");
			}
		}
		TADisplay.setText(allDetails.toString());
	}

	private void clearInputs() {
		TFEmpName.setText("");
		TFEmpID.setText("");
	}
}

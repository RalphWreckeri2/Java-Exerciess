import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTO {

	JFrame firstPart;
	JPanel welcome; // main panel
	JPanel employeePanel;
	JLabel firstLabel;
	JLabel numberOfEmployees;
	JTextField numberOfEmployeeTF;
	JButton submit;

	int employeeCount = 0; // Storage of increments
	int totalEmployees; // Total number of employees to enter
	int passCount = 1;

	List<String> employeeTODetails; // Storage of employee details

	public EmployeeTO() {
		employeeTODetails = new ArrayList<>();

		firstPart = new JFrame();
		firstPart.setTitle("Employee Pass and Travel Order Management System");
		firstPart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		welcome = new JPanel();
		welcome.setLayout(new BoxLayout(welcome, BoxLayout.Y_AXIS));

		welcome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

		firstLabel = new JLabel("Welcome to XYZ Corporation!");
		firstLabel.setFont(new Font("Arial", Font.BOLD, 16));
		firstLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
		welcome.add(firstLabel);

		welcome.add(Box.createRigidArea(new Dimension(0, 5))); // 5 pixels of vertical space

		employeePanel = new JPanel();
		employeePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		numberOfEmployees = new JLabel("Enter the number of employees:");
		numberOfEmployeeTF = new JTextField(5); // Text field with 15 columns width

		submit = new JButton("Submit");

		welcome.add(Box.createRigidArea(new Dimension(0, 5))); // 5 pixels of vertical space

		employeePanel.add(numberOfEmployees);
		employeePanel.add(numberOfEmployeeTF);
		employeePanel.add(submit);

		welcome.add(employeePanel);

		firstPart.add(welcome);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = numberOfEmployeeTF.getText();
				try {
					totalEmployees = Integer.parseInt(input); // Convert to integer
					firstPart.dispose();
					EmployeeField();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(firstPart, "Please enter a valid number.");
				}
			}
		});

		firstPart.setSize(300, 150); // width, height
		firstPart.setLocationRelativeTo(null);
		firstPart.setVisible(true);
	}

	JFrame employeeInput;
	JPanel instruct; // main panel
	JPanel inputPanel; // panel containing inputs: labels and text fields
	JLabel welcomeInstruct;

	// Labels = guides
	JLabel name;
	JLabel id;
	JLabel destination;
	JLabel orderNumber;

	// type answers here
	JTextField nameTF;
	JTextField idTF;
	JTextField destinationTF;
	JTextField orderNumberTF;

	// button
	JButton create;

	private void EmployeeField() {
		employeeInput = new JFrame();
		employeeInput.setTitle("Employee Information Form");
		employeeInput.setLayout(new BoxLayout(employeeInput.getContentPane(), BoxLayout.Y_AXIS));

		instruct = new JPanel();
		instruct.setLayout(new BoxLayout(instruct, BoxLayout.Y_AXIS));

		instruct.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		welcomeInstruct = new JLabel("Enter the necessary information:");
		welcomeInstruct.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
		instruct.add(welcomeInstruct);

		instruct.add(Box.createRigidArea(new Dimension(0, 10)));

		inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align components left

		name = new JLabel("Enter employee name:");
		nameTF = new JTextField(21); // specify column width

		id = new JLabel("Enter employee ID:");
		idTF = new JTextField(23);

		destination = new JLabel("Enter travel order destination:");
		destinationTF = new JTextField(17);

		orderNumber = new JLabel("Enter travel order number:");
		orderNumberTF = new JTextField(19);

		create = new JButton("Create");

		inputPanel.add(name);
		inputPanel.add(nameTF);
		inputPanel.add(id);
		inputPanel.add(idTF);
		inputPanel.add(destination);
		inputPanel.add(destinationTF);
		inputPanel.add(orderNumber);
		inputPanel.add(orderNumberTF);
		inputPanel.add(create);

		employeeInput.add(instruct);
		employeeInput.add(inputPanel);

		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String employeeName = nameTF.getText();
				String employeeId = idTF.getText();
				String travelDestination = destinationTF.getText();
				String travelOrderNumber = orderNumberTF.getText();

				// When field is empty
				if (employeeName.isEmpty() || employeeId.isEmpty() || travelDestination.isEmpty()
						|| travelOrderNumber.isEmpty()) {
					JOptionPane.showMessageDialog(employeeInput, "All fields must be filled.", "Creation unsuccessful",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validate string fields (only alphabetic characters for name and destination)
				if (!employeeName.matches("[a-zA-Z ]+") || !travelDestination.matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(employeeInput,
							"Invalid input on Name and Travel Destination.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validate Employee ID (should contain only digits)
				if (!employeeId.matches("\\d+")) {
				    JOptionPane.showMessageDialog(employeeInput,
				        "Invalid input on Employee ID. It should contain only digits.", "Input Error",
				        JOptionPane.ERROR_MESSAGE);
				    return;
				}

				// Validate Travel Order Number (should contain a combination of letters and digits)
				if (!travelOrderNumber.matches("(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+")) {
				    JOptionPane.showMessageDialog(employeeInput,
				        "Invalid input on Travel Order Number. It should contain a combination of letters and digits.", "Input Error",
				        JOptionPane.ERROR_MESSAGE);
				    return;
				}


				if (employeeCount < totalEmployees) {
					String details = "\n  Employee " + employeeCount + 1 + " Details: \n" + 
									 "     Name: " + employeeName + "\n" + 
									 "     Employee ID: " + employeeId + "\n" + 
									 "     Employee Pass EP0000" + passCount++ + "\n\n" +
							
									 "  Travel Order Details: \n" + 
									 "     Travel Destination: " + travelDestination + "\n" +
									 "     Travel Order Number: " + travelOrderNumber + "\n" + 
									 "     Employee: " + employeeName + "\n";
						
					employeeTODetails.add(details); // add sa array list

					JOptionPane.showMessageDialog(employeeInput, details, "Creation Successful",
							JOptionPane.INFORMATION_MESSAGE);

					nameTF.setText("");
					idTF.setText("");
					destinationTF.setText("");
					orderNumberTF.setText("");

					employeeCount++; // Increment the employee count

					if (employeeCount >= totalEmployees) {
						employeeInput.dispose();
						DisplayDetails();
					}
				}
			}
		});

		employeeInput.setSize(400, 230); // Set the size of the new frame
		employeeInput.setLocationRelativeTo(null);
		employeeInput.setVisible(true);
	}

	JFrame allDetailsOfEmployees; // main frame
	JLabel lastLabel;
	JTextArea textArea; // lalagyan ng details
	JScrollPane scroll;
	JPanel paddingPanel;
	JPanel labelPanel;

	private void DisplayDetails() {
		allDetailsOfEmployees = new JFrame();
		allDetailsOfEmployees.setTitle("Employee Travel Order Details");
		allDetailsOfEmployees.setLayout(new BorderLayout());

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setBorder(null);
		textArea.setWrapStyleWord(true);
		textArea.setMargin(new Insets(10, 10, 10, 10)); // tlbr
		textArea.setFocusable(false); 

		// Append all employee details to the text area
		for (String details : employeeTODetails) {
			textArea.append(details + "\n");
		}

		// Ensure that the JScrollPane wraps the JTextArea
		scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(new Dimension(350, 200));
		scroll.setOpaque(false); // Makes the scroll pane transparent
		scroll.getViewport().setOpaque(false); // Ensures the viewport is transparent too
		scroll.setBorder(BorderFactory.createEmptyBorder()); // Removes the scroll pane's border

		// Create a panel to hold the JScrollPane
		paddingPanel = new JPanel();
		paddingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		paddingPanel.setLayout(new BorderLayout()); // Use BorderLayout for proper placement
		paddingPanel.add(scroll, BorderLayout.CENTER);

		// Create a panel for the label at the top
		labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the label
		lastLabel = new JLabel("Employee and Travel Order Details");
		labelPanel.add(lastLabel);

		// Add both the label panel and the padding panel to the main frame
		allDetailsOfEmployees.add(labelPanel, BorderLayout.NORTH);
		allDetailsOfEmployees.add(paddingPanel, BorderLayout.CENTER);

		allDetailsOfEmployees.setSize(400, 300);
		allDetailsOfEmployees.setLocationRelativeTo(null);
		allDetailsOfEmployees.setVisible(true);
	}

	public static void main(String[] args) {
		new EmployeeTO();
	}
}
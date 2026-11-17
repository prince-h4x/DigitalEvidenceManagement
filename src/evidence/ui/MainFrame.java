
package evidence.ui;

import evidence.core.*;
import evidence.dao.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class MainFrame extends JFrame {

    private CaseDAO caseDAO = new CaseDAO();
    private EvidenceDAO evidenceDAO = new EvidenceDAO();
    private CustodyDAO custodyDAO = new CustodyDAO();

    private final Color BACKGROUND = new Color(240, 244, 250);
    private final Color NAVY = new Color(20, 32, 55);
    private final Color BLUE = new Color(43, 103, 220);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(40, 50, 70);

    public MainFrame() {

        setTitle("Digital Evidence Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(WHITE);
        header.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel heading = new JLabel("DIGITAL EVIDENCE MANAGEMENT");
        heading.setFont(new Font("Arial", Font.BOLD, 23));
        heading.setForeground(NAVY);

        JLabel subtitle = new JLabel("Evidence Tracking & Case Intelligence System");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);

        JPanel headingPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headingPanel.setBackground(WHITE);
        headingPanel.add(heading);
        headingPanel.add(subtitle);

        header.add(headingPanel, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(NAVY);
        sidebar.setPreferredSize(new Dimension(235, 0));
        sidebar.setBorder(new EmptyBorder(30, 15, 20, 15));

        JLabel brand = new JLabel("  G R O U P  0 9");
        brand.setForeground(new Color(130, 170, 255));
        brand.setFont(new Font("Arial", Font.BOLD, 16));
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(brand);
        sidebar.add(Box.createVerticalStrut(35));

        addSidebarButton(sidebar, "Dashboard", () -> showDashboard());
        addSidebarButton(sidebar, "Case Registration", () -> showCaseRegistration());
        addSidebarButton(sidebar, "Evidence Entry", () -> showEvidenceEntry());
        addSidebarButton(sidebar, "Chain of Custody", () -> showCustodyEntry());
        addSidebarButton(sidebar, "Search Evidence", () -> searchEvidence());
        addSidebarButton(sidebar, "Case Reports", () -> showCaseReport());

        sidebar.add(Box.createVerticalGlue());

        JLabel systemStatus = new JLabel("● SYSTEM OPERATIONAL");
        systemStatus.setForeground(new Color(100, 220, 150));
        systemStatus.setFont(new Font("Arial", Font.BOLD, 11));
        sidebar.add(systemStatus);

        add(sidebar, BorderLayout.WEST);

        // MAIN CONTENT
        showDashboard();

        // FOOTER
        JLabel footer = new JLabel(
                "Digital Evidence Management System  |  Group 9",
                SwingConstants.CENTER
        );
        footer.setOpaque(true);
        footer.setBackground(WHITE);
        footer.setForeground(Color.GRAY);
        footer.setBorder(new EmptyBorder(12, 10, 12, 10));

        add(footer, BorderLayout.SOUTH);
    }

    private void addSidebarButton(JPanel sidebar, String text, Runnable action) {

        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(WHITE);
        button.setBackground(NAVY);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(14, 12, 14, 5));
        button.setMaximumSize(new Dimension(205, 48));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> action.run());

        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(8));
    }

    private JPanel createContentPanel() {

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(BACKGROUND);
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        return panel;
    }

    // DASHBOARD
    private void showDashboard() {

        JPanel content = createContentPanel();

        JLabel welcome = new JLabel("Welcome to your Dashboard");
        welcome.setFont(new Font("Arial", Font.BOLD, 25));
        welcome.setForeground(NAVY);

        JLabel description = new JLabel(
                "Manage cases, evidence records and custody information."
        );
        description.setForeground(Color.GRAY);

        JPanel top = new JPanel(new GridLayout(2, 1, 0, 8));
        top.setBackground(BACKGROUND);
        top.add(welcome);
        top.add(description);

        content.add(top, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(2, 2, 18, 18));
        cards.setBackground(BACKGROUND);

        cards.add(createCard("01", "Case Registration",
                "Register and manage investigation cases"));

        cards.add(createCard("02", "Evidence Entry",
                "Store documents, images and logs"));

        cards.add(createCard("03", "Chain of Custody",
                "Maintain evidence handling history"));

        cards.add(createCard("04", "Evidence Search",
                "Find evidence and tracking details"));

        content.add(cards, BorderLayout.CENTER);

        JButton reportButton = new JButton("Generate Case Report");
        styleButton(reportButton);

        reportButton.addActionListener(e -> showCaseReport());

        content.add(reportButton, BorderLayout.SOUTH);

        setContentPane(createApplicationShell(content));
        revalidate();
        repaint();
    }

    private JPanel createApplicationShell(JPanel content) {

        JPanel shell = new JPanel(new BorderLayout());
        shell.add(getSidebar(), BorderLayout.WEST);
        shell.add(content, BorderLayout.CENTER);

        JLabel footer = new JLabel(
                "Digital Evidence Management System  |  Group 9",
                SwingConstants.CENTER
        );

        footer.setOpaque(true);
        footer.setBackground(WHITE);
        footer.setForeground(Color.GRAY);
        footer.setBorder(new EmptyBorder(12, 10, 12, 10));

        shell.add(footer, BorderLayout.SOUTH);

        return shell;
    }

    private JPanel getSidebar() {

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(NAVY);
        sidebar.setPreferredSize(new Dimension(235, 0));
        sidebar.setBorder(new EmptyBorder(30, 15, 20, 15));

        JLabel brand = new JLabel("  G R O U P  0 9");
        brand.setForeground(new Color(130, 170, 255));
        brand.setFont(new Font("Arial", Font.BOLD, 16));
        sidebar.add(brand);
        sidebar.add(Box.createVerticalStrut(35));

        addSidebarButton(sidebar, "Dashboard", this::showDashboard);
        addSidebarButton(sidebar, "Case Registration", this::showCaseRegistration);
        addSidebarButton(sidebar, "Evidence Entry", this::showEvidenceEntry);
        addSidebarButton(sidebar, "Chain of Custody", this::showCustodyEntry);
        addSidebarButton(sidebar, "Search Evidence", this::searchEvidence);
        addSidebarButton(sidebar, "Case Reports", this::showCaseReport);

        sidebar.add(Box.createVerticalGlue());

        JLabel status = new JLabel("● SYSTEM OPERATIONAL");
        status.setForeground(new Color(100, 220, 150));
        status.setFont(new Font("Arial", Font.BOLD, 11));
        sidebar.add(status);

        return sidebar;
    }

    private JPanel createCard(String number, String title, String detail) {

        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 228, 240)),
                new EmptyBorder(22, 22, 22, 22)
        ));

        JLabel num = new JLabel(number);
        num.setForeground(BLUE);
        num.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel name = new JLabel(title);
        name.setForeground(NAVY);
        name.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel info = new JLabel(detail);
        info.setForeground(Color.GRAY);
        info.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 7));
        textPanel.setBackground(WHITE);
        textPanel.add(name);
        textPanel.add(info);

        card.add(num, BorderLayout.NORTH);
        card.add(textPanel, BorderLayout.CENTER);

        return card;
    }

    private void styleButton(JButton button) {

        button.setBackground(BLUE);
        button.setForeground(WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(13, 20, 13, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // 1. CASE REGISTRATION
    private void showCaseRegistration() {

        JTextField titleField = new JTextField(20);
        JTextField investigatorField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(4, 20);

        String[] statuses = {"Open", "Under Investigation", "Closed"};
        JComboBox<String> statusBox = new JComboBox<>(statuses);

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Case Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Investigator:"));
        panel.add(investigatorField);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descriptionArea));
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Register New Case",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            String title = titleField.getText().trim();
            String investigator = investigatorField.getText().trim();

            if (title.isEmpty() || investigator.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Case title and investigator are required!");
                return;
            }

            Case newCase = new Case(
                    0, title, descriptionArea.getText().trim(),
                    investigator, LocalDate.now(),
                    (String) statusBox.getSelectedItem()
            );

            JOptionPane.showMessageDialog(this,
                    caseDAO.addCase(newCase)
                            ? "Case registered successfully!"
                            : "Failed to register case!");
        }
    }

    // 2. EVIDENCE ENTRY
    private void showEvidenceEntry() {

        JTextField caseIdField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextArea descriptionArea = new JTextArea(4, 15);

        JComboBox<String> typeBox = new JComboBox<>(
                new String[]{"Document", "Image", "Log"}
        );

        JTextField filePathField = new JTextField(15);
        filePathField.setEditable(false);

        JButton browseButton = new JButton("Browse");

        browseButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                filePathField.setText(
                        chooser.getSelectedFile().getAbsolutePath()
                );
            }
        });

        JPanel filePanel = new JPanel(new BorderLayout());
        filePanel.add(filePathField, BorderLayout.CENTER);
        filePanel.add(browseButton, BorderLayout.EAST);

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));

        panel.add(new JLabel("Case ID:"));
        panel.add(caseIdField);
        panel.add(new JLabel("Evidence Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Evidence Type:"));
        panel.add(typeBox);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descriptionArea));
        panel.add(new JLabel("Select File:"));
        panel.add(filePanel);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Add New Evidence",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            try {

                int caseId = Integer.parseInt(caseIdField.getText().trim());
                String name = nameField.getText().trim();
                String type = (String) typeBox.getSelectedItem();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Evidence name is required!");
                    return;
                }

                if (caseDAO.searchCase(caseId) == null) {
                    JOptionPane.showMessageDialog(this,
                            "Case ID does not exist!");
                    return;
                }

                File file = filePathField.getText().isEmpty()
                        ? null : new File(filePathField.getText());

                String description = descriptionArea.getText().trim();

                Evidence evidence;

                switch (type) {
                    case "Image":
                        evidence = new evidence.core.Image(
                                0, caseId, name, description, file
                        );
                        break;
                    case "Log":
                        evidence = new Log(
                                0, caseId, name, description, file
                        );
                        break;
                    default:
                        evidence = new Document(
                                0, caseId, name, description, file
                        );
                }

                JOptionPane.showMessageDialog(this,
                        evidenceDAO.addEvidence(evidence)
                                ? "Evidence added successfully!"
                                : "Failed to add evidence!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Enter a valid Case ID!");
            }
        }
    }

    // 3. CHAIN OF CUSTODY
    private void showCustodyEntry() {

        JTextField evidenceIdField = new JTextField(15);
        JTextField actionField = new JTextField(15);
        JTextField handledByField = new JTextField(15);
        JTextArea notesArea = new JTextArea(4, 15);

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));

        panel.add(new JLabel("Evidence ID:"));
        panel.add(evidenceIdField);
        panel.add(new JLabel("Action:"));
        panel.add(actionField);
        panel.add(new JLabel("Handled By:"));
        panel.add(handledByField);
        panel.add(new JLabel("Notes:"));
        panel.add(new JScrollPane(notesArea));

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Add Custody Record",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            try {

                int id = Integer.parseInt(evidenceIdField.getText().trim());

                if (actionField.getText().trim().isEmpty()
                        || handledByField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Action and handler are required!");
                    return;
                }

                evidenceDAO.searchEvidence(id);

                CustodyEntry entry = new CustodyEntry(
                        0, id, actionField.getText().trim(),
                        handledByField.getText().trim(),
                        notesArea.getText().trim()
                );

                JOptionPane.showMessageDialog(this,
                        custodyDAO.addCustodyEntry(entry)
                                ? "Custody record added successfully!"
                                : "Failed to add custody record!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Enter a valid Evidence ID!");
            } catch (EvidenceNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    // 4. SEARCH EVIDENCE
    
private void searchEvidence() {

    String input = JOptionPane.showInputDialog(
            this, "Enter Evidence ID:"
    );

    if (input == null || input.trim().isEmpty()) return;

    try {

        int id = Integer.parseInt(input.trim());
        Evidence evidence = evidenceDAO.searchEvidence(id);

        String details =
                "EVIDENCE DETAILS\n\n" +
                "Evidence ID: " + evidence.getEvidenceId() +
                "\nCase ID: " + evidence.getCaseId() +
                "\nName: " + evidence.getEvidenceName() +
                "\nType: " + evidence.getEvidenceType() +
                "\nDescription: " + evidence.getDescription() +
                "\nFile: " + evidence.getFileName();

        JTextArea output = new JTextArea(details, 15, 40);
        output.setEditable(false);

        JOptionPane.showMessageDialog(
                this, new JScrollPane(output),
                "Evidence Search",
                JOptionPane.INFORMATION_MESSAGE
        );

        String[] options = {
                "Update Evidence",
                "Delete Evidence",
                "Cancel"
        };

        int choice = JOptionPane.showOptionDialog(
                this,
                "What would you like to do with this evidence?",
                "Manage Evidence",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // UPDATE EVIDENCE
        if (choice == 0) {

            JTextField nameField =
                    new JTextField(evidence.getEvidenceName());

            JTextArea descriptionArea =
                    new JTextArea(evidence.getDescription(), 4, 20);

            JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));

            panel.add(new JLabel("New Evidence Name:"));
            panel.add(nameField);

            panel.add(new JLabel("New Description:"));
            panel.add(new JScrollPane(descriptionArea));

            int result = JOptionPane.showConfirmDialog(
                    this, panel, "Update Evidence",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {

                String newName = nameField.getText().trim();

                if (newName.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this, "Evidence name cannot be empty!"
                    );
                    return;
                }

                boolean updated = evidenceDAO.updateEvidence(
                        id,
                        newName,
                        descriptionArea.getText().trim()
                );

                JOptionPane.showMessageDialog(
                        this,
                        updated
                                ? "Evidence updated successfully!"
                                : "Evidence update failed!"
                );
            }
        }

        // DELETE EVIDENCE
        else if (choice == 1) {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this evidence?\n" +
                    "Its associated custody records will also be deleted.",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {

                boolean deleted = evidenceDAO.deleteEvidence(id);

                JOptionPane.showMessageDialog(
                        this,
                        deleted
                                ? "Evidence deleted successfully!"
                                : "Evidence deletion failed!"
                );
            }
        }

        // Show custody history
        if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) {
            return;
        }

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                this, "Enter a valid Evidence ID!"
        );

    } catch (EvidenceNotFoundException ex) {

        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
}


    // 5. CASE REPORT
    
private void showCaseReport() {

    String input = JOptionPane.showInputDialog(
            this, "Enter Case ID:"
    );

    if (input == null || input.trim().isEmpty()) return;

    try {

        int caseId = Integer.parseInt(input.trim());
        Case c = caseDAO.searchCase(caseId);

        if (c == null) {
            JOptionPane.showMessageDialog(this, "Case not found!");
            return;
        }

        String report =
                "========== CASE REPORT ==========\n\n" +
                "Case ID: " + c.getCaseId() +
                "\nTitle: " + c.getCaseTitle() +
                "\nInvestigator: " + c.getInvestigator() +
                "\nDescription: " + c.getDescription() +
                "\nDate: " + c.getCaseDate() +
                "\nStatus: " + c.getStatus() +
                "\n\n========== EVIDENCE DETAILS ==========\n";

        List<Evidence> allEvidence = evidenceDAO.getAllEvidence();

        int count = 0;

        for (Evidence ev : allEvidence) {

            if (ev.getCaseId() == caseId) {

                count++;

                report += "\nEvidence ID: " + ev.getEvidenceId() +
                        "\nName: " + ev.getEvidenceName() +
                        "\nType: " + ev.getEvidenceType() +
                        "\nDescription: " + ev.getDescription() +
                        "\n";

                List<CustodyEntry> history =
                        custodyDAO.getCustodyHistory(ev.getEvidenceId());

                report += "Custody Records:\n";

                if (history.isEmpty()) {
                    report += "No records available.\n";
                } else {
                    for (CustodyEntry entry : history) {
                        report += entry + "\n";
                    }
                }
            }
        }

        if (count == 0) {
            report += "\nNo evidence registered for this case.";
        }

        report += "\n\nTotal Evidence: " + count;

        JTextArea output = new JTextArea(report, 25, 50);
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JOptionPane.showMessageDialog(
                this, new JScrollPane(output),
                "Case Report",
                JOptionPane.INFORMATION_MESSAGE
        );

        String[] options = {
                "Update Case Status",
                "Delete Case",
                "Cancel"
        };

        int choice = JOptionPane.showOptionDialog(
                this,
                "Manage this case:",
                "Case Management",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // UPDATE CASE STATUS
        if (choice == 0) {

            String[] statuses = {
                    "Open",
                    "Under Investigation",
                    "Closed"
            };

            JComboBox<String> statusBox =
                    new JComboBox<>(statuses);

            statusBox.setSelectedItem(c.getStatus());

            int result = JOptionPane.showConfirmDialog(
                    this,
                    statusBox,
                    "Update Case Status",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {

                String newStatus =
                        (String) statusBox.getSelectedItem();

                boolean updated =
                        caseDAO.updateCaseStatus(caseId, newStatus);

                JOptionPane.showMessageDialog(
                        this,
                        updated
                                ? "Case status updated successfully!"
                                : "Case status update failed!"
                );
            }
        }

        // DELETE CASE
        else if (choice == 1) {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Deleting this case will also delete all its " +
                    "associated evidence and custody records.\n\n" +
                    "Are you sure?",
                    "Confirm Case Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {

                boolean deleted = caseDAO.deleteCase(caseId);

                JOptionPane.showMessageDialog(
                        this,
                        deleted
                                ? "Case and associated records deleted!"
                                : "Case deletion failed!"
                );
            }
        }

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                this, "Enter a valid Case ID!"
        );
    }
}
}


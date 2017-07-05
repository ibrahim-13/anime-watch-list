//    Copyright (C) 2017 MD. Ibrahim Khan
//
//    Project Name: 
//    Author: MD. Ibrahim Khan
//    Author's Email: ib.arshad777@gmail.com
//
//    Redistribution and use in source and binary forms, with or without modification,
//    are permitted provided that the following conditions are met:
//
//    1. Redistributions of source code must retain the above copyright notice, this
//       list of conditions and the following disclaimer.
//
//    2. Redistributions in binary form must reproduce the above copyright notice, this
//       list of conditions and the following disclaimer in the documentation and/or
//       other materials provided with the distribution.
//
//    3. Neither the name of the copyright holder nor the names of the contributors may
//       be used to endorse or promote products derived from this software without
//       specific prior written permission.
//
//    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
//    ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
//    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
//    IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
//    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING
//    BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
//    DATA, OR PROFITS; OR BUSINESS INTERRUPTIONS) HOWEVER CAUSED AND ON ANY THEORY OF
//    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//    OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
//    OF THE POSSIBILITY OF SUCH DAMAGE.

package AnimeWatchList.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import AnimeWatchList.database.dataBase;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.RowFilter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import java.util.List;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 16L;
	private static final double programVersion = (double)serialVersionUID/10;
	private String aboutMsg = "Ibrahim Khan Arshad\nDepartment of CSE\nDaffodil International University\n\nEmail : ib.arshad777@gmail.com";
	private String exportFileName = "ExportedAnimeList.csv";
	
	private dataBase DataBase;

	private JPanel contentPane;
	private JPanel panelMain;
	private JPanel panelAddNew;
	private JPanel panelEdit;
	
	private JScrollPane scrollPane;
	private JTable table;
	private TableRowSorter<TableModel> sorter;
	
	private JLabel lblMainAnimeWatchList;
	
	private JButton btnMainAddNew;
	private JButton btnMainEdit;
	private JButton btnMainDelete;
	private JButton btnMainExport;
	private JButton btnAddNewGoBack;
	private JButton btnEditGoBack;
	private JButton btnAddNewClear;
	private JButton btnAddNewApply;
	private JButton btnEditApply;
	private JButton btnMainExit;
	private JButton btnMainAbout;
	
	private JTextPane textPaneStatusMain;
	private JTextPane textPaneStatusEdit;
	private JTextPane textPaneStatusAddNew;
	
	private JLabel lblAddNewAddNewTo;
	private JLabel lblAddNewName;	
	private JLabel lblAddNewInfo;	
	private JLabel lblAddNewStatus;	
	private JLabel lblAddNewEpisodes;	
	private JLabel lblAddNewSeen;	
	private JLabel lblAddNewAnimeWatchList;
	private JLabel lblEditAnimeWatchList;
	private JLabel lblEditIndex;
	private JLabel lblEditName;
	private JLabel lblEditInfo;
	private JLabel lblEditStatus;
	private JLabel lblEditEpisodes;
	private JLabel lblEditSeen;
	private JLabel lblMainVersion;
	
	private JTextField textFieldAddNewName;
	private JTextField textFieldAddNewSeen;
	private JTextField textFieldAddNewEpisodes;
	private JTextField textFieldSAddNewStatus;
	private JTextField textFieldAddNewInfo;
	private JTextPane textPaneEditIndex;
	private JTextField textFieldEditName;
	private JTextField textFieldEditInfo;
	private JTextField textFieldEditStatus;
	private JTextField textFieldEditEpisodes;
	private JTextField textFieldEditSeen;
	private JTextField textFieldMainSearch;
	private JTextField textFieldIndexCount;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setResizable(false);
		setTitle("Anime Watch List " + programVersion);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				exitApplication();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panelMain = new JPanel();
		contentPane.add(panelMain, "name_28105791638332");
		panelMain.setLayout(null);
		
		lblMainAnimeWatchList = new JLabel("Anime Watch List");
		lblMainAnimeWatchList.setBounds(130, 0, 389, 64);
		lblMainAnimeWatchList.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainAnimeWatchList.setFont(new Font("Tahoma", Font.ITALIC, 50));
		panelMain.add(lblMainAnimeWatchList);
		
		btnMainAddNew = new JButton("Add New");
		btnMainAddNew.setBounds(10, 75, 91, 23);
		btnMainAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelAddNew.setVisible(true);
				panelMain.setVisible(false);
				panelEdit.setVisible(false);
			}
		});
		panelMain.add(btnMainAddNew);
		
		textPaneStatusMain = new JTextPane();
		textPaneStatusMain.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPaneStatusMain.setEditable(false);
		textPaneStatusMain.setBounds(0, 387, 685, 20);
		panelMain.add(textPaneStatusMain);
		
		btnMainEdit = new JButton("Edit");
		btnMainEdit.setBounds(107, 75, 91, 23);
		btnMainEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(row == -1) {
					textPaneStatusMain.setText("No row is selected. Please select a row first.");
				} else {					
					panelMain.setVisible(false);
					panelEdit.setVisible(true);
					panelAddNew.setVisible(false);
					resetStatusPane();
					setValuesEditPanel(DataBase.getRowPackage((String)table.getModel().getValueAt(table.convertRowIndexToModel(row), 0)));
					
				}			
			}
		});
		panelMain.add(btnMainEdit);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 109, 664, 228);
		scrollPane.getHorizontalScrollBar();
		panelMain.add(scrollPane);
		
		btnMainDelete = new JButton("Delete");
		btnMainDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row == -1) {
					textPaneStatusMain.setText("No row is selected. Please select a row first.");
				} else {
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure ?", "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
						String index = table.getModel().getValueAt(row, 0).toString();
						DataBase.deleteRow(Integer.parseInt(index));
					}
				}
				updateTable();
			}
		});
		btnMainDelete.setBounds(208, 75, 91, 23);
		panelMain.add(btnMainDelete);
		
		btnMainExit = new JButton("Exit");
		btnMainExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitApplication();
				System.exit(0);
			}
		});
		btnMainExit.setBounds(583, 75, 91, 23);
		panelMain.add(btnMainExit);
		
		btnMainAbout = new JButton("About");
		btnMainAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, aboutMsg, "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnMainAbout.setBounds(482, 75, 91, 23);
		panelMain.add(btnMainAbout);
		
		lblMainVersion = new JLabel("Version " + programVersion);
		lblMainVersion.setBounds(605, 0, 69, 14);
		panelMain.add(lblMainVersion);
		
		btnMainExport = new JButton("Export");
		btnMainExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataBase.exportToFileCSV(exportFileName);
			}
		});
		btnMainExport.setBounds(309, 75, 89, 23);
		panelMain.add(btnMainExport);
		
		textFieldMainSearch = new JTextField();
		textFieldMainSearch.setBounds(142, 356, 238, 20);
		textFieldMainSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				searchTable(1, textFieldMainSearch.getText());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchTable(1, textFieldMainSearch.getText());
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateTable();
			}
		});
		textFieldMainSearch.setColumns(10);
		panelMain.add(textFieldMainSearch);
		
		JLabel lblSearchByName = new JLabel("Search by Name");
		lblSearchByName.setBounds(10, 353, 122, 23);
		panelMain.add(lblSearchByName);
		
		JLabel lblIndexCount = new JLabel("Index Count");
		lblIndexCount.setBounds(492, 353, 89, 23);
		panelMain.add(lblIndexCount);
		
		textFieldIndexCount = new JTextField();
		textFieldIndexCount.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIndexCount.setEditable(false);
		textFieldIndexCount.setBounds(588, 356, 86, 20);
		textFieldIndexCount.setText("");
		panelMain.add(textFieldIndexCount);
		textFieldIndexCount.setColumns(10);
		panelMain.setVisible(true);
		
		panelEdit = new JPanel();
		contentPane.add(panelEdit, "name_28213104250774");
		panelEdit.setLayout(null);
		
		textPaneStatusEdit = new JTextPane();
		textPaneStatusEdit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPaneStatusEdit.setEditable(false);
		textPaneStatusEdit.setBounds(0, 387, 700, 20);
		panelEdit.add(textPaneStatusEdit);
		
		btnEditGoBack = new JButton("Go Back");
		btnEditGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMain.setVisible(true);
				panelEdit.setVisible(false);
				panelAddNew.setVisible(false);
				resetStatusPane();
				resetEditPanel();
				updateTable();
			}
		});
		btnEditGoBack.setBounds(10, 311, 91, 23);
		panelEdit.add(btnEditGoBack);
		
		lblEditAnimeWatchList = new JLabel("Anime Watch List");
		lblEditAnimeWatchList.setFont(new Font("Mistral", Font.PLAIN, 50));
		lblEditAnimeWatchList.setBounds(189, 0, 311, 58);
		panelEdit.add(lblEditAnimeWatchList);
		
		lblEditIndex = new JLabel("Index :");
		lblEditIndex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEditIndex.setBounds(10, 72, 55, 14);
		panelEdit.add(lblEditIndex);
		
		textPaneEditIndex = new JTextPane();
		textPaneEditIndex.setBounds(10, 97, 97, 23);
		panelEdit.add(textPaneEditIndex);
		
		lblEditName = new JLabel("Name");
		lblEditName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEditName.setBounds(189, 125, 46, 14);
		panelEdit.add(lblEditName);
		
		lblEditInfo = new JLabel("Info");
		lblEditInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEditInfo.setBounds(189, 159, 46, 14);
		panelEdit.add(lblEditInfo);
		
		lblEditStatus = new JLabel("Status");
		lblEditStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEditStatus.setBounds(189, 193, 46, 14);
		panelEdit.add(lblEditStatus);
		
		lblEditEpisodes = new JLabel("Episodes");
		lblEditEpisodes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEditEpisodes.setBounds(189, 226, 64, 14);
		panelEdit.add(lblEditEpisodes);
		
		lblEditSeen = new JLabel("Seen");
		lblEditSeen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEditSeen.setBounds(189, 260, 46, 14);
		panelEdit.add(lblEditSeen);
		
		textFieldEditName = new JTextField();
		textFieldEditName.setBounds(377, 122, 155, 23);
		panelEdit.add(textFieldEditName);
		textFieldEditName.setColumns(10);
		
		textFieldEditInfo = new JTextField();
		textFieldEditInfo.setBounds(377, 156, 155, 23);
		panelEdit.add(textFieldEditInfo);
		textFieldEditInfo.setColumns(10);
		
		textFieldEditStatus = new JTextField();
		textFieldEditStatus.setBounds(377, 190, 155, 23);
		panelEdit.add(textFieldEditStatus);
		textFieldEditStatus.setColumns(10);
		
		textFieldEditEpisodes = new JTextField();
		textFieldEditEpisodes.setBounds(377, 223, 155, 23);
		panelEdit.add(textFieldEditEpisodes);
		textFieldEditEpisodes.setColumns(10);
		
		textFieldEditSeen = new JTextField();
		textFieldEditSeen.setBounds(377, 257, 155, 23);
		panelEdit.add(textFieldEditSeen);
		textFieldEditSeen.setColumns(10);
		
		btnEditApply = new JButton("Apply");
		btnEditApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(textFieldEditName.getText().equals("") || textFieldEditInfo.getText().equals("") || textFieldEditStatus.getText().equals("") || textFieldEditEpisodes.getText().equals("") || textFieldEditSeen.getText().equals(""))) {
					if(textPaneEditIndex.getText().equals("")) {
						textPaneStatusEdit.setText("Index not selected. Go back and select from the table.");
					} else {
						DataBase.updateExisting(textPaneEditIndex.getText(), textFieldEditName.getText(), textFieldEditInfo.getText(), textFieldEditStatus.getText(), textFieldEditEpisodes.getText(), textFieldEditSeen.getText());
					}
				}
				else {
					textPaneStatusEdit.setText("No field can be empty. Please fillup all fields.");
				}
			}
		});
		btnEditApply.setBounds(542, 311, 91, 23);
		panelEdit.add(btnEditApply);
		panelEdit.setVisible(false);
		
		panelAddNew = new JPanel();
		contentPane.add(panelAddNew, "name_28186273357708");
		panelAddNew.setLayout(null);
		
		textPaneStatusAddNew = new JTextPane();
		textPaneStatusAddNew.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPaneStatusAddNew.setEditable(false);
		textPaneStatusAddNew.setBounds(0, 387, 700, 20);
		panelAddNew.add(textPaneStatusAddNew);
		
		btnAddNewGoBack = new JButton("Go Back");
		btnAddNewGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(true);
				panelEdit.setVisible(false);
				panelAddNew.setVisible(false);
				resetStatusPane();
				updateTable();
			}
		});
		btnAddNewGoBack.setBounds(10, 311, 91, 23);
		panelAddNew.add(btnAddNewGoBack);
		
		lblAddNewAnimeWatchList = new JLabel("Anime Watch List");
		lblAddNewAnimeWatchList.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewAnimeWatchList.setFont(new Font("Mistral", Font.PLAIN, 50));
		lblAddNewAnimeWatchList.setBounds(135, 0, 389, 64);
		panelAddNew.add(lblAddNewAnimeWatchList);
		
		lblAddNewAddNewTo = new JLabel("Add New To The List");
		lblAddNewAddNewTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddNewAddNewTo.setBounds(10, 75, 149, 14);
		panelAddNew.add(lblAddNewAddNewTo);
		
		lblAddNewName = new JLabel("Name");
		lblAddNewName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddNewName.setBounds(113, 122, 46, 14);
		panelAddNew.add(lblAddNewName);
		
		textFieldAddNewName = new JTextField();
		textFieldAddNewName.setBounds(425, 120, 149, 20);
		panelAddNew.add(textFieldAddNewName);
		textFieldAddNewName.setColumns(10);
		
		lblAddNewInfo = new JLabel("Info");
		lblAddNewInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddNewInfo.setBounds(113, 147, 46, 14);
		panelAddNew.add(lblAddNewInfo);
		
		textFieldAddNewInfo = new JTextField();
		textFieldAddNewInfo.setBounds(425, 145, 149, 20);
		panelAddNew.add(textFieldAddNewInfo);
		textFieldAddNewInfo.setColumns(10);
		
		lblAddNewStatus = new JLabel("Status");
		lblAddNewStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddNewStatus.setBounds(113, 172, 46, 14);
		panelAddNew.add(lblAddNewStatus);
		
		textFieldSAddNewStatus = new JTextField();
		textFieldSAddNewStatus.setBounds(425, 170, 149, 20);
		panelAddNew.add(textFieldSAddNewStatus);
		textFieldSAddNewStatus.setColumns(10);
		
		lblAddNewEpisodes = new JLabel("Episodes");
		lblAddNewEpisodes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddNewEpisodes.setBounds(113, 197, 57, 14);
		panelAddNew.add(lblAddNewEpisodes);
		
		textFieldAddNewEpisodes = new JTextField();
		textFieldAddNewEpisodes.setBounds(425, 195, 149, 20);
		panelAddNew.add(textFieldAddNewEpisodes);
		textFieldAddNewEpisodes.setColumns(10);
		
		lblAddNewSeen = new JLabel("Seen");
		lblAddNewSeen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddNewSeen.setBounds(113, 222, 46, 14);
		panelAddNew.add(lblAddNewSeen);
		
		textFieldAddNewSeen = new JTextField();
		textFieldAddNewSeen.setBounds(425, 220, 149, 20);
		panelAddNew.add(textFieldAddNewSeen);
		textFieldAddNewSeen.setColumns(10);
		
		btnAddNewApply = new JButton("Apply");
		btnAddNewApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textFieldAddNewName.getText().equals("") || textFieldAddNewInfo.getText().equals("") || textFieldSAddNewStatus.getText().equals("") || textFieldAddNewEpisodes.getText().equals("") || textFieldAddNewSeen.getText().equals(""))) {
					DataBase.addNew(textFieldAddNewName.getText(), textFieldAddNewInfo.getText(), textFieldSAddNewStatus.getText(), textFieldAddNewEpisodes.getText(), textFieldAddNewSeen.getText());
					resetAddNewPanel();
				}
				else {
					textPaneStatusAddNew.setText("No field can be empty. Please fillup all fields.");
				}
			}
		});
		btnAddNewApply.setBounds(583, 311, 91, 23);
		panelAddNew.add(btnAddNewApply);
		
		btnAddNewClear = new JButton("Clear");
		btnAddNewClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetAddNewPanel();
			}
		});
		btnAddNewClear.setBounds(483, 311, 91, 23);
		panelAddNew.add(btnAddNewClear);
		panelAddNew.setVisible(false);
		
		initDataBase();
	}
	
	private void initDataBase() {
		DataBase = new dataBase();
		JTextPane[] statusList = {textPaneStatusMain,textPaneStatusEdit,textPaneStatusAddNew};
		DataBase.getStatus(statusList);
		
		postDatabaseLoad();
	}
	
	private void postDatabaseLoad() {
		updateTable();
	}
	
	private void updateTable() {
		table.setModel(DataBase.getTableModel());
		
		// Sort the table
		sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		// First parameter of the RowSorter.SortKey() method is the column name of witch accordance with the table will be sorted
		// Could add multiple rows to sort
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		
		sorter.setSortKeys(sortKeys);
		
		textFieldIndexCount.setText(String.valueOf(table.getRowCount()));
	}
	
	private void searchTable(int row, String...text) {
		List<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
		
		for(String tmp : text) {
			if(!tmp.equals("")) {
				RowFilter<TableModel, Object> filter = RowFilter.regexFilter("^" + "(?i)" + tmp, row);
				filters.add(filter);
			}
		}
		
		RowFilter<TableModel, Object> compoundFilter = RowFilter.andFilter(filters);
		
		sorter = new TableRowSorter<TableModel>(table.getModel());
		sorter.setRowFilter(compoundFilter);
		table.setRowSorter(sorter);
	}
	
	private void resetStatusPane() {
		textPaneStatusAddNew.setText("");
		textPaneStatusEdit.setText("");
		textPaneStatusMain.setText("");
	}
	
	private void setValuesEditPanel(String[] dataPack) {
		textPaneEditIndex.setText(dataPack[0]);
		textFieldEditEpisodes.setText(dataPack[4]);
		textFieldEditInfo.setText(dataPack[2]);
		textFieldEditName.setText(dataPack[1]);
		textFieldEditSeen.setText(dataPack[5]);
		textFieldEditStatus.setText(dataPack[3]);		
	}
	
	private void resetEditPanel() {
		textFieldEditEpisodes.setText("");
		textFieldEditInfo.setText("");
		textFieldEditName.setText("");
		textFieldEditSeen.setText("");
		textFieldEditStatus.setText("");
		textPaneEditIndex.setText("");
	}
	
	private void resetAddNewPanel() {
		textFieldAddNewEpisodes.setText("");
		textFieldAddNewInfo.setText("");
		textFieldAddNewName.setText("");
		textFieldAddNewSeen.setText("");
		textFieldSAddNewStatus.setText("");
	}
	
	private void exitApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("Closing database before exit...");
		DataBase.closeDatabase();
	}
}

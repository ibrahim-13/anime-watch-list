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

package AnimeWatchList.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

//Last error code 2013

public class dataBase {
	
	// Database specific String variables
	private String databaseLocation = "jdbc:sqlite:animeWatchList.db";
	private static String driverName = "org.sqlite.JDBC";
	
	private String tableName = "AnimeWatchList";
	private String[] columnNames = {"index","name","info","status","episodes","seen"};
	private String[] columnNamesType = {"INTEGER PRIMARY KEY AUTOINCREMENT","TEXT","TEXT","TEXT","TEXT","TEXT"};
	private Vector<String> columnNamesVec = new Vector<String>();
	
	// Used for confirmation of error output in the JTextPane Objects
	private boolean setText;

	// If error output to JTextPane is enabled, all reference to the JTextPane Objects will be stored
	// in this variable array
	private JTextPane[] statusDestination;
	
	// Database connectivity variables
	private Connection databaseConn;
	private Statement databaseStmt;
	private ResultSet databaseRs;
	
	// Load database driver
	static {
		try {
			Class.forName(driverName).newInstance();
		} catch (InstantiationException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (IllegalAccessException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	// Constructor for database class
	public dataBase() {
		try {
			// Get connection from the database location
			databaseConn = DriverManager.getConnection(databaseLocation);
		} catch (SQLException e) {
			setStatusMsg("Error code : 2001\n" + e.getLocalizedMessage());
		}
		
		try {
			// Create statement from the received connection
			databaseStmt = databaseConn.createStatement();
		} catch (SQLException e) {
			setStatusMsg("Error code : 2002\n" + e.getLocalizedMessage());
		}
		
		// Run this method every time this class is initialized.
		// Checks for table, will create table if not found.
		init();
	}
	
	private void init() {
		try {
			// Tries to select the first column from the database table.
			// If error encountered, it will call method to create the table.
			databaseRs = databaseStmt.executeQuery("SELECT '" + columnNames[0] + "' FROM " + tableName);
		} catch (SQLException e) {
			setStatusMsg("Error code : 2003\n" + e.getLocalizedMessage());
			setStatusMsg("Creating Table...");
			// Calls method to create table
			createTable();
		}

		// Export all column names to a Vector Object for later use in JTable creation.
		for(int i = 0; i < columnNames.length; i++) {
			columnNamesVec.add(columnNames[i].toUpperCase());
		}
	}
	
	// Table Creation method
	private void createTable() {
		String tmp = "";
		if(columnNames.length == columnNamesType.length) {
			// Produce formated String for SQL Query, intended for automation
			for(int i=0; i<columnNames.length; i++) {
				if(i != 0) {
					tmp = tmp + ",";
				}
				tmp = tmp + "'" + columnNames[i] + "'" + " " + columnNamesType[i];
			}
		}
		try {
			// Query to create database
			databaseStmt.execute("CREATE TABLE " + tableName + "(" + tmp + ");");
		} catch (SQLException e) {
			setStatusMsg("Error code : 2004\n" + e.getLocalizedMessage());
		}
	}
	
	// Method to add new data to the database table.
	public void addNew(String name, String info, String status, String episodes, String seen) {
		String tmp_s = "";
		
		// Produce formated String for SQL Query.
		for(int i=1; i<columnNames.length; i++) {
			if(i != 1) {
				tmp_s = tmp_s + ",";
			}
			tmp_s = tmp_s + "'" + columnNames[i] + "'";
		}
		
		try {
			// Database query to insert data into the table.
			databaseStmt.execute("INSERT INTO '" + tableName + "'(" + tmp_s + ") VALUES('" + name + "','" + info + "','" + status + "','" + episodes + "','" + seen + "');");
			// Export message to status method
			setStatusMsg("Added Successfully :: " + name);
		} catch (SQLException e) {
			setStatusMsg("Error code : 2006\n" + e.getLocalizedMessage());
		}
	}
	
	// Method to update any existing entry of the given index.
	public void updateExisting(String index, String name, String info, String status, String episodes, String seen) {
		try {
			databaseStmt.execute("UPDATE " + tableName + " SET "
					+ columnNames[1] + " = '" + name + "', "
					+ columnNames[2] + " = '" + info + "', "
					+ columnNames[3] + " = '" + status + "', "
					+ columnNames[4] + " = '" + episodes + "', "
					+ columnNames[5] + " = '" + seen + "' "
					+ "WHERE `" + columnNames[0] + "` = " + Integer.parseInt(index) + ";");
			setStatusMsg("Index " + index + " ::  Updated Successfully !!!");
		} catch (SQLException e) {
			setStatusMsg("Error code : 2012\n" + e.getLocalizedMessage());
		}
	}
	
	// Return full row data of a specific index number.
	// The index number is passed through the parameter.
	public String[] getRowPackage(String index) {
		String tmp = "`" + columnNames[0] + "`";
		for(int i = 1; i < columnNames.length; i++) {
			tmp += "," + "`" + columnNames[i] + "`";
		}
		
		try {
			// Selects all columns in the table of the given index
			databaseRs = databaseStmt.executeQuery("SELECT " + tmp + " FROM " + tableName + " WHERE `" + columnNames[0] + "` = " + Integer.parseInt(index) + ";");
		} catch (SQLException e) {
			setStatusMsg("Error code : 2010\n" + e.getLocalizedMessage());
		}
		
		String[] pack = new String[columnNames.length];
		
		try {
			// Add row data with respect to the column names.
			for(int i = 0; i < columnNames.length; i++) {
				pack[i] = databaseRs.getString(columnNames[i]);
			}
		} catch(SQLException e) {
			setStatusMsg("Error code : 2011\n" + e.getLocalizedMessage());
		}
		
		return pack;
	}
	
	// Returns the String array of the column names.
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public DefaultTableModel getTableModel() {
		Vector<Vector<String>> dataVec = new Vector<Vector<String>>();
		//Vector<Vector<String>> sorted = new Vector<Vector<String>>();
		Vector<String> tmp;
		
		try{
			databaseRs = databaseStmt.executeQuery("SELECT * FROM " + tableName +";");
		} catch(SQLException e) {
			setStatusMsg("Error code : 2008\n" + e.getLocalizedMessage());
		}
		
		try {
			while(databaseRs.next()) {
				tmp = new Vector<String>();
				for(int i=0; i < columnNames.length; i++) {
					tmp.add(databaseRs.getString(columnNames[i]));
				}
				dataVec.add(tmp);
			}
		} catch (SQLException e) {
			setStatusMsg("Error code : 2009\n" + e.getLocalizedMessage());
		}
		
		/* OLD way to sort table malually
		int i = dataVec.size();
		int j;
		String[] aa = new String[i];		
		
		for(j = 0; j < i; j++)
		{
			aa[j] = dataVec.get(j).get(1);
		}
		Arrays.sort(aa);		
		for(j = 0; j < i; j++)
		{
			for(Vector<String> cc : dataVec)
			{
				if(aa[j].equals(cc.get(1)))
				{
					sorted.add(cc);
				}
			}
		}
		
		return new DefaultTableModel(sorted, columnNamesVec);
		*/
		
		return new DefaultTableModel(dataVec, columnNamesVec);
	}
	
	// Deletes the row of the given index.
	// The specific index passed in the parameter.
	public void deleteRow(int index) {
		try {
			databaseStmt.execute("DELETE FROM " + tableName + " WHERE `" + columnNames[0] + "`=" + index + " ;");
			setStatusMsg("Index " + index + "  ::  " + "Deleted successfully !!!");
		} catch (SQLException e) {
			setStatusMsg("Error code : 2013\n" + e.getLocalizedMessage());
		}
	}
	
	// Method to enable getting status output to multiple JTextPane Objects.
	// Takes an array of JTextPane Objects as parameter.
	public void getStatus(JTextPane[] destination) {
		this.statusDestination = destination;
		this.setText = true;
	}
	
	public void exportToFileCSV(String fileName) {
		int i;
		FileWriter file = null;
		
		try {
			file = new FileWriter(new File(fileName));
		} catch (IOException e) {
			setStatusMsg("Error code : 2014\n" + e.getLocalizedMessage());
		}
		
		BufferedWriter writer = new BufferedWriter(file);
		
		try{
			databaseRs = databaseStmt.executeQuery("SELECT * FROM " + tableName +";");
		} catch(SQLException e) {
			setStatusMsg("Error code : 2015\n" + e.getLocalizedMessage());
		}
		
		try {
			writer.write(columnNames[0]);
			for(i = 1; i < columnNames.length; i++)
			{
			
				writer.write(";");
				writer.write(columnNames[i]);		
			}
			writer.newLine();
		} catch (IOException e) {
			setStatusMsg("Error code : 2016\n" + e.getLocalizedMessage());
		}
		
		try {
			while(databaseRs.next()) {
				writer.write(databaseRs.getString(columnNames[0]));
				for(i=1; i < columnNames.length; i++) {
					writer.write(";");
					writer.write(databaseRs.getString(columnNames[i]));
				}
				writer.newLine();
			}
		} catch (SQLException e) {
			setStatusMsg("Error code : 2017\n" + e.getLocalizedMessage());
		} catch (IOException e) {
			setStatusMsg("Error code : 2018\n" + e.getLocalizedMessage());
		}
		
		try {
			writer.close();
			setStatusMsg("Exported to file successfully : " + fileName);
		} catch (IOException e) {
			setStatusMsg("Error code : 2019\n" + e.getLocalizedMessage());
		}
	}
	
	// Method to handle error and status messages.
	private void setStatusMsg(String msg) {
		if(setText) {
			for(JTextPane a : statusDestination) {
				a.setText(msg);
			}
		}
		System.out.println(msg);
	}
	
	// Close database connection and statement connection.
	public void closeDatabase() {
		try {
			this.databaseStmt.close();
			this.databaseConn.close();
		} catch (SQLException e) {
			setStatusMsg("Error code : 2007\n" + e.getLocalizedMessage());
		}
	}
	
}

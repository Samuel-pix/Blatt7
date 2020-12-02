package de.hfu;

import de.hfu.residents.service.ResidentServiceException;

public class main {

	public static void main(String[] args) throws ResidentServiceException {
		testResident test = new testResident();
		test.testFilteredResidentsList();
		test.testUniqueResident();

	}

}

package de.hfu;
import de.hfu.residents.service.*;
import junit.framework.TestCase;
import de.hfu.residents.repository.*;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hfu.residents.domain.*;
public class testResident extends TestCase {

	@SuppressWarnings("deprecation")
	public void testFilteredResidentsList() {
		
		BaseResidentService service = new BaseResidentService();
		
		Resident buerger1 = new Resident("Karl","Burger","Hauptstraße 4","Furtwangen",new Date(100,12,12));
		Resident buerger3 = new Resident("Martin","Fischer","Hauptstraße 10","Freiburg",new Date(100,12,12));
		
		Resident testBuerger1 = new Resident("K*","B*","Haup*","F*",null);
		Resident testBuerger2 = new Resident("M*","F*","H*","F*",null);
		
		ResidentRepositoryStub repository = new ResidentRepositoryStub();
		service.setResidentRepository(repository);
		
		List<Resident> testListe1 = service.getFilteredResidentsList(testBuerger1);
		List<Resident> testListe2 = service.getFilteredResidentsList(testBuerger2);
		assertEquals(2,testListe1.size());
		assertEquals(buerger1.getFamilyName(),testListe1.get(0).getFamilyName());
		assertEquals(buerger3.getFamilyName(),testListe2.get(0).getFamilyName());
		
	}
	
	@SuppressWarnings("deprecation")
	public void testUniqueResident() throws ResidentServiceException {
BaseResidentService service = new BaseResidentService();
		
		Resident buerger1 = new Resident("Karl","Burger","Hauptstraße 4","Furtwangen",new Date(100,12,12));
		ResidentRepositoryStub repository = new ResidentRepositoryStub();
		service.setResidentRepository(repository);
		try {
		Resident testBuerger = service.getUniqueResident(buerger1);
		assertTrue(testBuerger.vergleich(buerger1));
	}
		catch(ResidentServiceException e) {}
		
		try {
			Resident testBuerger = new Resident("K*","B*","Haup*","F*",null);
			service.getUniqueResident(testBuerger);
			fail("Wildcards (*) sind nicht erlaubt!");
		}catch(ResidentServiceException e) {}
		
		try {
			Resident testBuerger = new Resident("Kal","Bauer","Haupt","Elzach",null);
			service.getUniqueResident(testBuerger);
			fail("Suchanfrage lieferte kein eindeutiges Ergebnis!");
		}catch(ResidentServiceException e) {}

}
	
	public void mockTest() {
	BaseResidentService service = new BaseResidentService();
	
	Resident buerger1 = new Resident("Karl","Burger","Hauptstraße 4","Furtwangen",new Date(100,12,12));
	Resident buerger2 = new Resident("Karl","Bader","Hauptstraße 10","Freiburg",new Date(50,10,9));
	Resident buerger3 = new Resident("Martin","Fischer","Hauptstraße 10","Freiburg",new Date(80,10,9));
	
	List<Resident> liste = new ArrayList<Resident>();
	liste.add(buerger1);
	liste.add(buerger2);
	liste.add(buerger3);
	
	ResidentRepository mock = createMock(ResidentRepository.class);
	expect(mock.getResidents()).andReturn(liste);
	replay(mock);
	
	service.setResidentRepository(mock);
	Resident testBuerger=new Resident("Karl","B*","Hauptstraße 10","Freiburg",null);
	List<Resident> testListe= service.getFilteredResidentsList(testBuerger);
	
	assertEquals(testListe.get(0).getFamilyName(),equals(buerger1.getFamilyName()));
	assertEquals(testListe.get(1).getStreet(),equals(buerger2.getStreet()));
	
	}
}

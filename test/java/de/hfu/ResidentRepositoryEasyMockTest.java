package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
public class ResidentRepositoryEasyMockTest {
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
	
	assertThat(testListe.get(0).getFamilyName(),equals(buerger1.getFamilyName()));
	assertThat(testListe.get(1).getStreet(),equals(buerger2.getStreet()));
	
	}
}

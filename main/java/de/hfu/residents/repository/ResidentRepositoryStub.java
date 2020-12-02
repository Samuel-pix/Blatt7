package de.hfu.residents.repository;
import de.hfu.residents.domain.*;


import java.util.List;
import java.util.Date;
import java.util.ArrayList;

public class ResidentRepositoryStub implements ResidentRepository {
	@SuppressWarnings("deprecation")
	Resident buerger1 = new Resident("Karl","Burger","Hauptstraße 4","Furtwangen",new Date(100,12,12));
	@SuppressWarnings("deprecation")
	Resident buerger2 = new Resident("Karl","Bader","Hauptstraße 10","Freiburg",new Date(50,10,9));
	@SuppressWarnings("deprecation")
	Resident buerger3 = new Resident("Martin","Fischer","Hauptstraße 10","Freiburg",new Date(80,10,9));


	@Override
	public List<Resident> getResidents() {
		List<Resident> a = new ArrayList<Resident>();
		a.add(buerger1);
		a.add(buerger2);
		a.add(buerger3);
		return a;
	}


}

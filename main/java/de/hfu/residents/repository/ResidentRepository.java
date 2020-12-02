package de.hfu.residents.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hfu.residents.domain.Resident;

public interface ResidentRepository {
  List<Resident> getResidents();

}
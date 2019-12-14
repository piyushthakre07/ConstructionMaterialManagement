package com.app.module.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.MaterialCategary;
import com.app.module.master.dao.IMaterialCategaryDao;
import com.app.module.master.service.IMaterialCategaryService;

@Service
public class MaterialCategaryImpl implements IMaterialCategaryService {
	
@Autowired
IMaterialCategaryDao materialCategaryDao;
	
	@Override
	public List<MaterialCategary> getallmaterialcategary() {
		return materialCategaryDao.findAll();
	}

}

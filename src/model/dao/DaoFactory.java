package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {

	public static VendedorDao createVendedorDao() {
		return new VendedorDaoJDBC(DB.getConnection());
	}

	public static DepartmentDao createDepartmentDao()  {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {


		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller findById = sellerDao.findById(3);
		
		System.out.println(findById.toString());
		
		System.out.println();
		System.out.println("findByDepartament");
		
		Department department = new Department(2, null);
		List<Seller> findByDepartment = sellerDao.findByDepartment(department);
		
		for (Seller seller : findByDepartment) {
			System.out.println(seller.toString());
		}
	}

}

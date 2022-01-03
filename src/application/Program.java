package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {

		Department department = new Department(1, "Books");
		System.out.println(department.toString());

		Seller seller = new Seller(21, "bob", "bob@gmail.com", new Date(), 3000.0, department);

		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println(seller.toString());
	}

}

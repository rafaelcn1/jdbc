package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) throws ParseException {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println();
		System.out.println("UPdate");

		Seller sellerInsert = new Seller();
		sellerInsert.setId(9);
		sellerInsert.setName("Rafael Cunha Nascimento");
		sellerInsert.setEmail("rafaelcunha@hotmail.com");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sellerInsert.setBirthDate(sdf.parse("21/09/1984"));
		sellerInsert.setBaseSalary(4500.00);
		sellerInsert.setDepartment(new Department(3, null));
		
		sellerDao.update(sellerInsert);

		Seller findById = sellerDao.findById(3);

		System.out.println(findById.toString());

		System.out.println();
		System.out.println("findByDepartament");

		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);

		for (Seller seller : list) {
			System.out.println(seller.toString());
		}
		
		System.out.println();
		System.out.println("Delete");
		sellerDao.deleteById(13);

		System.out.println();
		System.out.println("findByAll");

		list = sellerDao.findAll();

		for (Seller seller : list) {
			System.out.println(seller.toString());
		}
		
		
	}

}

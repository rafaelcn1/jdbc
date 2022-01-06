package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartamentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DepartamentDao dao = DaoFactory.createDepartamentDao();
		
		
		System.out.println("Teste Insert");
		Department department = new Department();
		department.setName("Eletronics");
		department.setId(2);
		
		//dao.insert(department);
		System.out.println("Teste Update");
		dao.update(department);
		
		System.out.println("Teste Delete por ID");
		//dao.deleteById(5);
		
		System.out.println("Teste Find por ID");
		System.out.println(dao.findById(3).toString());
		
		System.out.println("Teste Todos os Departamentos");
		List<Department> findAll = dao.findAll();
		
		for (Department dep : findAll) {
			System.out.println(dep.toString());
		}
		
		
		
		

	}

}

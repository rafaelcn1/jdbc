package model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department department = instantiateDepartament(rs);

				Seller seller = instantiateSeller(rs, department);
				return seller;

			}
			return null;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeConnection(rs, st);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(department);
		return seller;
	}

	private Department instantiateDepartament(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		return department;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, department.getId());
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartament(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller sellerTemp = instantiateSeller(rs, dep);
				list.add(sellerTemp);
			}
			return list;

		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeConnection(rs, st);
		}
	}

}

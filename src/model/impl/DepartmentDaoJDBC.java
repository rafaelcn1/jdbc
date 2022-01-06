package model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.Result;

import db.DB;
import db.DbException;
import model.dao.DepartamentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartamentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department department) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "INSERT INTO department (Name)" + "VALUES (?)";
		try {
			st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, department.getName());
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}
			} else {
				throw new DbException("Nenhum departamento foi cadastrado!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
		} finally {
			DB.closeConnection(rs, st);
		}

	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE `department` SET Name = ? WHERE Id = ?";

		try {
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, department.getName());
			ps.setInt(2, department.getId());
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}
			} else {
				throw new DbException("Nenhuma alteração realizada!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeConnection(rs, ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "DELETE FROM department WHERE Id = ?";
		Department departmentDelete = null;
		try {
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			int rowAffected = ps.executeUpdate();

			if (rowAffected > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int idDelete = rs.getInt(1);
					departmentDelete = new Department(idDelete, null);
					System.out.println("Departamento deletado : " + departmentDelete.toString());
				}
			} else {
				throw new DbException("Nenhum departamento deletado!");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeConnection(rs, ps);
		}

	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `department` WHERE id = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("Id"));
				department.setName(rs.getString("Name"));
				return department;
			} else {
				throw new DbException("Nenhum departamento encontrado com o id: " + id + " informado!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		} finally {
			DB.closeConnection(rs, ps);
		}

	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `department`";

		List<Department> listDepartaments = new ArrayList<Department>();
		Map<Integer, Department> map = new HashMap<Integer, Department>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("Id"));
				department.setName(rs.getString("Name"));
				map.put(department.getId(), department);
				listDepartaments.add(department);
			}
			return listDepartaments;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}

	}

}

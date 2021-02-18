package member.model.service;

import java.sql.Connection;

import java.util.List;

import member.model.dao.ProductDao;
import member.model.vo.Product;
import member.model.vo.ProductIO;

import static common.JDBCTemplate.*;	//이거 타이핑 말고 어떻게 불러오지?

////2. Connection
//Connection conn = getConnection();
////Dao
////6. 트랜잭션
////7. 자원반납
//close(conn);

public class ProductService {	
	
	private ProductDao productDao = new ProductDao();

	public List<Product> selectAll() {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		List<Product> list = productDao.selectAll(conn);
		//6. 트랜잭션
		//7. 자원반납
		close(conn);
		
		return list;
	}

	public Product selectById(String inputStr) {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		Product product = productDao.selectById(conn, inputStr);
		//6. 트랜잭션
		//7. 자원반납
		close(conn);
		return product;
	}

	public List<Product> selectByName(String inputStr) {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		List<Product> list = productDao.selectByName(conn, inputStr);
		//6. 트랜잭션
		//7. 자원반납
		close(conn);
		return list;
	}

	public int insertProduct(Product product) {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		int result = productDao.insertProduct(conn,product);
		//6. 트랜잭션
		if(result>0) commit(conn);
		else rollback(conn);
		//7. 자원반납
		close(conn);
		return result;
	}

	public int deleteById(String inputStr) {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		int result = productDao.deleteById(conn,inputStr);
		//6. 트랜잭션
		if(result>0) commit(conn);
		else rollback(conn);
		//7. 자원반납
		close(conn);
		return result;
	}

	public int updateProduct(Product product) {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		int result = productDao.updateProduct(conn,product);
		//6. 트랜잭션
		if(result>0) commit(conn);
		else rollback(conn);
		//7. 자원반납
		close(conn);
		return result;
	}

	public List<ProductIO> selectAllIO() {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		List<ProductIO> list = productDao.selectAllIO(conn);
		//6. 트랜잭션
		//7. 자원반납
		close(conn);
		
		return list;
	}

	public int insertProduct(ProductIO productIO) {
		//2. Connection
		Connection conn = getConnection();
		//Dao
		int result = productDao.insertProduct(conn,productIO);
		//6. 트랜잭션
		if(result>0) commit(conn);
		else rollback(conn);
		//7. 자원반납
		close(conn);
		return result;
	}

}

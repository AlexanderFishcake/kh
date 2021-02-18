package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.exception.ProductException;
import member.model.vo.Product;
import member.model.vo.ProductIO;

import static common.JDBCTemplate.*;

//3. PreparedStatement객체 생성(미완성쿼리)
//3.1 ? 값대입
//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
//4.1 ResultSet -> Java객체 옮겨담기
//5. 자원반납(생성역순 rset - pstmt) 

public class ProductDao {
	
	private Properties prop = new Properties();
	
	public ProductDao() {
		String fileName = "resources/member-query.properties";
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Product> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAll");
		List<Product> list = null;
		
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			rset = pstmt.executeQuery();
			//4.1 ResultSet -> Java객체 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				Product product = new Product();
				product.setProductId(rset.getString("product_id"));
				product.setProductName(rset.getString("product_name"));
				product.setPrice(rset.getInt("price"));
				product.setDescription(rset.getString("description"));
				product.setStock(rset.getInt("stock"));
				
				list.add(product);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new ProductException("전체상품조회", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Product selectById(Connection conn, String inputStr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectById");
		Product product = new Product();
		
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			pstmt.setString(1, inputStr);
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			rset = pstmt.executeQuery();
			//4.1 ResultSet -> Java객체 옮겨담기
			while(rset.next()) {
				product.setProductId(rset.getString("product_id"));
				product.setProductName(rset.getString("product_name"));
				product.setPrice(rset.getInt("price"));
				product.setDescription(rset.getString("description"));
				product.setStock(rset.getInt("stock"));				
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new ProductException("상품아이디검색", e);
		}
		//5. 자원반납(생성역순 rset - pstmt) 
		return product;
	}

	public List<Product> selectByName(Connection conn, String inputStr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectByName");
		List<Product> list = null;
		
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			pstmt.setString(1, '%' + inputStr + '%');
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			rset = pstmt.executeQuery();
			//4.1 ResultSet -> Java객체 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				Product product = new Product();
				product.setProductId(rset.getString("product_id"));
				product.setProductName(rset.getString("product_name"));
				product.setPrice(rset.getInt("price"));
				product.setDescription(rset.getString("description"));
				product.setStock(rset.getInt("stock"));
				
				list.add(product);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new ProductException("상품아이디검색", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertProduct");
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			pstmt.setString(1, product.getProductId());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setInt(5, product.getStock());
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			result = pstmt.executeUpdate();
			//4.1 ResultSet -> Java객체 옮겨담기
		} catch (Exception e) {
//			e.printStackTrace();
			throw new ProductException("상품추가", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt) 
			close(pstmt);
		}
		
		return result;
	}

	public int deleteById(Connection conn, String inputStr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteById");
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			pstmt.setString(1, inputStr);
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			result = pstmt.executeUpdate();
			//4.1 ResultSet -> Java객체 옮겨담기
		} catch (Exception e) {
//			e.printStackTrace();
			throw new ProductException("상품삭제", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt) 
			close(pstmt);
		}
		
		return result;
	}

	public int updateProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateProduct");
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getDescription());
			pstmt.setInt(4, product.getStock());
			pstmt.setString(5, product.getProductId());
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			result = pstmt.executeUpdate();
			//4.1 ResultSet -> Java객체 옮겨담기
		} catch (Exception e) {
//			e.printStackTrace();
			throw new ProductException("상품정보변경", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt) 
			close(pstmt);
		}
		
		return result;
	}

	public List<ProductIO> selectAllIO(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllIO");
		List<ProductIO> list = null;
		
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			rset = pstmt.executeQuery();
			//4.1 ResultSet -> Java객체 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				ProductIO pio = new ProductIO();
				pio.setIoNo(rset.getInt("io_no"));
				pio.setProductId(rset.getString("product_id"));
				pio.setIodate(rset.getDate("iodate"));
				pio.setAmount(rset.getInt("amount"));
				pio.setStatus(rset.getString("status").charAt(0));
				
				list.add(pio);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new ProductException("전체입출고내역", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt) 
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertProduct(Connection conn, ProductIO productIO) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertProductIO");
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입 id amount
			pstmt.setString(1, productIO.getProductId());
			pstmt.setInt(2, productIO.getAmount());
			pstmt.setString(3, String.valueOf(productIO.getStatus()));
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			throw new ProductException("상품 입출고", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt) 
			close(pstmt);
		}
		
		return result;
	}

}

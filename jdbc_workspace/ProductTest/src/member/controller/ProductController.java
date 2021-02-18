package member.controller;

import java.util.List;

import member.model.exception.ProductException;
import member.model.service.ProductService;
import member.model.vo.Product;
import member.model.vo.ProductIO;
import member.view.ProductMenu;

public class ProductController {
	private ProductService productService = new ProductService();

	public List<Product> selectAll() {
		List<Product> list= null;
		try {
			list = productService.selectAll();
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return list;
	}

	public Product selectById(String inputStr) {
		Product product = null;
		try {
			product = productService.selectById(inputStr);
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return product;
	}

	public List<Product> selectByName(String inputStr) {
		List<Product> list= null;
		try {
			list = productService.selectByName(inputStr);
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return list;
	}

	public int insertProduct(Product product) {
		int result = 0;
		try {
			result = productService.insertProduct(product);
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return result;
	}

	public int deleteById(String inputStr) {
		int result = 0;
		try {
			result = productService.deleteById(inputStr);
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return result;
	}

	public int updateProduct(Product product) {
		int result = 0;
		try {
			result = productService.updateProduct(product);
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return result;
	}

	public List<ProductIO> selectAllIO() {
		List<ProductIO> list= null;
		try {
			list = productService.selectAllIO();
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return list;
	}

	public int insertProduct(ProductIO productIO) {
		int result = 0;
		try {
			result = productService.insertProduct(productIO);
		} catch (ProductException e) {
			new ProductMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return result;
	}

}

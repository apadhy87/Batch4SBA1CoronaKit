package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	private HttpSession session;
	PrintWriter out;
	
	

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		//Get the data from DB
		return "ordersummary.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException {
		
		//insert data into DB
		session=request.getSession();
		List<KitDetail>  customerkit= (List<KitDetail>)session.getAttribute("ShoppingCart");
		int p=(int)session.getAttribute("Coronakitid");
		String c=session.getAttribute("name").toString();
		String g=session.getAttribute("email").toString();
		String h=session.getAttribute("phone").toString();
		int o=(int)session.getAttribute("totalamount");
		String a=request.getParameter("address").toString();
		String v=LocalDateTime.now().toString();
				
		
		
		CoronaKit coronaKitDetail=new CoronaKit((int)session.getAttribute("Coronakitid"),
				session.getAttribute("name").toString(),
				session.getAttribute("email").toString(),
				session.getAttribute("phone").toString(),
				(int)session.getAttribute("totalamount"),
				request.getParameter("address").toString(),
				LocalDateTime.now().toString(),
				true);
		
		boolean OrderDetailStoreresult=this.kitDAO.insertCustomerOrderDetails(coronaKitDetail,customerkit);
		
		if(!OrderDetailStoreresult)
			throw new ServletException("Order Details are not saved");
		
		OrderSummary orderSummary=new OrderSummary(coronaKitDetail,customerkit); 
		session.invalidate();
		request.getSession().setAttribute("ordersummary", orderSummary);
		
		
		return "user?action=ordersummary";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		session=request.getSession();
		if(session.getAttribute("ShoppingCart")==null)
		{
			return "user?action=showproducts";
		}
		return "showkit.jsp";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		session=request.getSession();
		int productId= Integer.parseInt(request.getParameter("id"));
		ProductMaster product=this.productMasterDao.getSpecificProductDetail(productId);
		
			if(session.getAttribute("ShoppingCart")==null)
			{
				
				return "user?action=showproducts";
			}
			else
			{
				List<KitDetail>  existingKits=(List<KitDetail>)session.getAttribute("ShoppingCart");
				int alreadyExists= isAlreadyExistsinCart(productId,existingKits);
				if(alreadyExists==-1)
				{
					return "user?action=showproducts";
					
				}
				else
				{
					int quantity=existingKits.get(alreadyExists).getQuantity()-1;
					existingKits.get(alreadyExists).setQuantity(quantity);
					existingKits.get(alreadyExists).setAmount(quantity*product.getCost());
					if(quantity==0)
					existingKits.remove(alreadyExists);
					
				}
				if(existingKits.size()>0)
				session.setAttribute("ShoppingCart", existingKits);
				else
				{
					session.invalidate();
					return "user?action=showproducts";
				}
			}
			return "user?action=showkit";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
	
	KitDetail kit;
	session=request.getSession();
	int productId= Integer.parseInt(request.getParameter("id"));
	ProductMaster product=this.productMasterDao.getSpecificProductDetail(productId);
	
		if(session.getAttribute("ShoppingCart")==null)
		{
			Random r= new Random();
			List<KitDetail>  kits=new ArrayList<KitDetail>();
			kit=new KitDetail(1, r.nextInt(), product, 1, product.getCost());
			kits.add(kit);
			
			session.setAttribute("ShoppingCart", kits);
			session.setAttribute("coronaKitID", kit.getCoronaKitId());
			
		}
		else
		{
			List<KitDetail>  existingKits=(List<KitDetail>)session.getAttribute("ShoppingCart");
			int alreadyExists= isAlreadyExistsinCart(productId,existingKits);
			if(alreadyExists==-1)
			{
				kit=new KitDetail(existingKits.get(existingKits.size()-1).getId()+1,(int)session.getAttribute("coronaKitID"),product,1,product.getCost());
				existingKits.add(kit);
				
			}
			else
			{
				int quantity=existingKits.get(alreadyExists).getQuantity()+1;
				existingKits.get(alreadyExists).setQuantity(quantity);
				existingKits.get(alreadyExists).setAmount(quantity*product.getCost());
				
				
			}
			session.setAttribute("ShoppingCart", existingKits);
		
		}
		
		
		return "user?action=showkit";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		List<ProductMaster>  products=this.productMasterDao.getProductList();
		request.setAttribute("products", products);
		return "showproductstoadd.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		session.setAttribute("name", request.getParameter("username"));
		session.setAttribute("email", request.getParameter("email"));
		session.setAttribute("phone", request.getParameter("phone"));
		return "user?action=showproducts";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		return "newuser.jsp";
	}
	
	private int isAlreadyExistsinCart(int productId, List<KitDetail> existingKits)
	{
	  int i=-1;	
	  for(int k=0;k<existingKits.size();k++)
	  {
		  if(existingKits.get(k).getProduct().getId()==productId)
			  return k;
	  }
	  return i;
	}
}
package com.javawebtutor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
//import com.programmingfree.dao.CrudDao;
//import com.programmingfree.model.User;
import com.daniel.model.Product;
import com.daniel.dao.ProductDao;



public class ProductCRUDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao dao;
    
    public ProductCRUDController() {
        dao=new ProductDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("action")!=null){
			List<Product> products=new ArrayList<Product>();
			String action=(String)request.getParameter("action");
			Gson gson = new Gson();
			response.setContentType("application/json");
			
			if(action.equals("list")){
				try{						
				//Fetch Data from User Table
				//lstUser=dao.getAllUsers();
					products=dao.getAllProductDB();
				//Convert Java Object to Json				
				JsonElement element = gson.toJsonTree(products, new TypeToken<List<Product>>() {}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();				
				//Return Json in the format required by jTable plugin
				listData="{\"Result\":\"OK\",\"Records\":"+listData+"}";			
				response.getWriter().print(listData);
				}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}				
			}
			else if(action.equals("create") || action.equals("update")){
				Product product=new Product();
//				if(request.getParameter("prodid")==null){				   
//				   int prodid=Integer.parseInt(request.getParameter("prodid"));
//				   product.setProdid(prodid);
//				}
				if(request.getParameter("name")!=null){
					String name=(String)request.getParameter("name");
					//group.setU(uuid);
					product.setName(name);
				}
				if(request.getParameter("description")!=null){
				   String description=(String)request.getParameter("description");
				   product.setDescription(description);
				}
				if(request.getParameter("suggestedunitprice")!=null){
				   String suggestedunitprice=(String)request.getParameter("suggestedunitprice");
				   product.setSuggestedunitprice(suggestedunitprice);
				}
				if(request.getParameter("buyunitprice")!=null){
					   String buyunitprice=(String)request.getParameter("buyunitprice");
					   product.setBuyunitprice(buyunitprice);
					}
				if(request.getParameter("unitsinstock")!=null){
					   String unitsinstock=(String)request.getParameter("unitsinstock");
					   product.setUnitsinstock(unitsinstock);
					}
				if(request.getParameter("productcode")!=null){
					   String productcode=(String)request.getParameter("productcode");
					   product.setProductcode(productcode);
					}
				if(request.getParameter("uuid")!=null){
					   String uuid=(String)request.getParameter("uuid");
					   product.setUuid(uuid);
					}
				try{											
					if(action.equals("create")){//Create new record
						dao.addProduct(product);					
						products.add(product);
						//Convert Java Object to Json				
						String json=gson.toJson(product);					
						//Return Json in the format required by jTable plugin
						String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						response.getWriter().print(listData);
					}else if(action.equals("update")){//Update existing record
						dao.updateProduct(product);
						String listData="{\"Result\":\"OK\"}";									
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
						response.getWriter().print(error);
				}
			}else if(action.equals("delete")){//Delete record
				try{
					if(request.getParameter("prodid")!=null){
						String id=(String)request.getParameter("userid");
						dao.deleteProduct(Integer.parseInt(id));
						String listData="{\"Result\":\"OK\"}";								
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
				String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
				response.getWriter().print(error);
			}				
		}
	 }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action")!=null){
			List<Product> products=new ArrayList<Product>();
			String action=(String)request.getParameter("action");
			Gson gson = new Gson();
			response.setContentType("application/json");
			
			if(action.equals("list")){
				try{						
				//Fetch Data from User Table
				//lstUser=dao.getAllUsers();
					products=dao.getAllProductDB();
				//Convert Java Object to Json				
				JsonElement element = gson.toJsonTree(products, new TypeToken<List<Product>>() {}.getType());
				JsonArray jsonArray = element.getAsJsonArray();
				String listData=jsonArray.toString();				
				//Return Json in the format required by jTable plugin
				listData="{\"Result\":\"OK\",\"Records\":"+listData+"}";			
				response.getWriter().print(listData);
				}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}				
			}
			else if(action.equals("create") || action.equals("update")){
				Product product=new Product();
//				if(request.getParameter("prodid")==null){				   
//				   int prodid=Integer.parseInt(request.getParameter("prodid"));
//				   product.setProdid(prodid);
//				}
				if(request.getParameter("name")!=null){
					String name=(String)request.getParameter("name");
					//group.setU(uuid);
					product.setName(name);
				}
				if(request.getParameter("description")!=null){
				   String description=(String)request.getParameter("description");
				   product.setDescription(description);
				}
				if(request.getParameter("suggestedunitprice")!=null){
				   String suggestedunitprice=(String)request.getParameter("suggestedunitprice");
				   product.setSuggestedunitprice(suggestedunitprice);
				}
				if(request.getParameter("buyunitprice")!=null){
					   String buyunitprice=(String)request.getParameter("buyunitprice");
					   product.setBuyunitprice(buyunitprice);
					}
				if(request.getParameter("unitsinstock")!=null){
					   String unitsinstock=(String)request.getParameter("unitsinstock");
					   product.setUnitsinstock(unitsinstock);
					}
				if(request.getParameter("productcode")!=null){
					   String productcode=(String)request.getParameter("productcode");
					   product.setProductcode(productcode);
					}
				if(request.getParameter("uuid")!=null){
					   String uuid=(String)request.getParameter("uuid");
					   product.setUuid(uuid);
					}
				try{											
					if(action.equals("create")){//Create new record
						dao.addProduct(product);					
						products.add(product);
						//Convert Java Object to Json				
						String json=gson.toJson(product);					
						//Return Json in the format required by jTable plugin
						String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						response.getWriter().print(listData);
					}else if(action.equals("update")){//Update existing record
						dao.updateProduct(product);
						String listData="{\"Result\":\"OK\"}";									
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
						response.getWriter().print(error);
				}
			}else if(action.equals("delete")){//Delete record
				try{
					if(request.getParameter("prodid")!=null){
						String id=(String)request.getParameter("userid");
						dao.deleteProduct(Integer.parseInt(id));
						String listData="{\"Result\":\"OK\"}";								
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
				String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
				response.getWriter().print(error);
			}				
		}
	 }
  }
}

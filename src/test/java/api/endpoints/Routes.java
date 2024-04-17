package api.endpoints;

/*
 * Swagger URI --> https://petstore.swagger.io * 
 * Create User (POST): https://petstore.swagger.io/v2/user
 * Get User(GET) : https://petstore.swagger.io/v2/user/{username}
 * UPDATE User(UPDATE) : https://petstore.swagger.io/v2/user/{username}
 * Delete User(DELETE) : https://petstore.swagger.io/v2/user/{username}
 * https://petstore.swagger.io
 * 
 * base URL: https://petstore.swagger.io/v2
 * endpoint: /user/{username}
 * 
 * 
 *  Base URL: petstore.swagger.io/v2
 *  
 *  
 *  use this class only for urls
 * 
 * */


public class Routes {
	
	public static String base_url= "https://petstore.swagger.io/v2";
	
//User Module
	
	public static String post_url =base_url + "/user";
	public static String get_url =base_url + "/user/{username}";
	public static String update_url =base_url + "/user/{username}";
	public static String delete_url =base_url + "/user/{username}";

	
	
	//Store Module
	public static String store_post_url =base_url + "/store/order";

	public static String store_get_url =base_url + "/store/inventory";
	public static String store_update_url =base_url + "/store/order/{orderId}";
	public static String store_delete_url =base_url + "/store/order{orderId}";
	
	
	
	//Pet Module
//	
//	public static String pet_post_url =base_url + "/store/order";
//	public static String pet_post_url =base_url + "/store/order";
//	public static String store_post_url =base_url + "/store/order";
//	public static String store_post_url =base_url + "/store/order";
//	public static String store_post_url =base_url + "/store/order";

	

}

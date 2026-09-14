package api.endpoints;
/*
 Swagger URI-->https://petstore.swagger.io
 create user(post)-->https://petstore.swagger.io/v2/user
 get user(get)-->https://petstore.swagger.io/v2/{username}
 update user(put)-->https://petstore.swagger.io/v2/{username}
 delete user(delete)-->https://petstore.swagger.io/v2/{username}
 */
public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    // User Module

    public static String post_url = base_url + "/user";

    public static String get_url = base_url + "/user/{username}";

    public static String update_url = base_url + "/user/{username}";

    public static String delete_url = base_url + "/user/{username}";
}


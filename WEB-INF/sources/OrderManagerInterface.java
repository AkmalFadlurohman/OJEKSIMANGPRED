package com.ojeksimangpred.OjolServices;


import com.ojeksimangpred.bean.Order;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)

public interface OrderManagerInterface {
<<<<<<< HEAD
        @WebMethod void setOrder(int idDriver, int idCustomer, int score, String destLoc, String pickLoc, String currComment, String driverVisibility, String customerVisibility);
        @WebMethod void insertToDatabase();
        @WebMethod public Order getOrder();
        @WebMethod void hideOrder(int id, boolean isDriver);
        @WebMethod int[] getPreferedLocation (String location);
        @WebMethod int getLength();
        @WebMethod public Order getOrderI(int i);
=======
        @WebMethod void setOrder(int idDriver, int idCustomer, int score, String destLoc, String pickLoc, String currComment, boolean driverVisibility, boolean customerVisibility);
		@WebMethod void insertToDatabase();
		@WebMethod public Order getOrder();
        @WebMethod void hideOrder(int id, boolean isDriver);
        @WebMethod int[] getPreferedLocation (String location);
        @WebMethod int getLength();
        @WebMethod Order getOrderI(int i);
>>>>>>> a894d33753cfb49771dd8f97e3999c834b0e266e
        @WebMethod void getListOrderCustomer(int idDriver);
        @WebMethod void getListOrderDriver(int idDriver);
}
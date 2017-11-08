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
        @WebMethod void setOrder(int idDriver, int idCustomer, int score, String destLoc, String pickLoc, String currComment, boolean driverVisibility, boolean customerVisibility);
	@WebMethod void insertToDatabase();
	@WebMethod public Order getOrder();
        @WebMethod void hideOrder(int id, boolean isDriver);
        @WebMethod int[] getPreferedLocation (String location);
        @WebMethod int getLength();
        @WebMethod Order[] getListOrderCustomer(int idDriver);
        @WebMethod Order[] getListOrderDriver(int idDriver);
}
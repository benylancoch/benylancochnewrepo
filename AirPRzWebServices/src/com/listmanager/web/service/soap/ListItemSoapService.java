package com.listmanager.web.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.listmanager.model.ListItem;
import com.listmanager.service.ListItemService;
import com.listmanager.service.impl.ListItemServiceImpl;

@WebService(serviceName = "ListItemSoapService")
public class ListItemSoapService {
	private final ListItemService listItemService;
	
	public ListItemSoapService() {
		listItemService = new ListItemServiceImpl();
	}
	
	@WebMethod
	public ListItem add(
			@WebParam(name = "userId") Long userId,
			@WebParam(name = "value") String value) {
		return listItemService.addListItem(userId, value);
	}
	
	@WebMethod
	public ListItem update(
			@WebParam(name = "userId") Long userId,
			@WebParam(name = "listItemId") Long listItemId,
			@WebParam(name = "value") String value) {
		return listItemService.updateListItem(userId, listItemId, value);
	}

	@WebMethod
	public void delete(
			@WebParam(name = "userId") Long userId,
			@WebParam(name = "listItemId") Long listItemId) {
		listItemService.deleteListItem(userId, listItemId);
	}

}

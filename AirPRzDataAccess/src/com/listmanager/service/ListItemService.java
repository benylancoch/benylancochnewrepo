package com.listmanager.service;

import com.listmanager.model.ListItem;

public interface ListItemService {
	
	ListItem addListItem(Long userId, String value);
	
	ListItem updateListItem(Long userId, Long listItemId, String newValue);
	
	void deleteListItem(Long userId, Long listItemId);

}

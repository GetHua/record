package com.fern.record.services;

import com.fern.record.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserItemService {

    @Autowired
    private UserItemRepository userItemRepository;

}

package com.leaforbook.orange.common.service;

import com.leaforbook.orange.common.dao.model.CommonResource;
import org.springframework.stereotype.Service;

@Service
public interface CommonResourceService {
    void insert(CommonResource resource);
    void delete(CommonResource resource);
    void deleteResource(String resourceId);
    void update(CommonResource resource);
    boolean hasResource(String userId, String resourceType, String resourceId);
    void removeResource(String userId, String resourceId);
}

package com.leaforbook.orange.service;

import com.github.pagehelper.Page;
import com.leaforbook.orange.controller.form.CustomAddressIdForm;
import com.leaforbook.orange.controller.form.CustomAddressForm;
import com.leaforbook.orange.controller.form.CustomAddressQueryForm;
import com.leaforbook.orange.controller.form.CustomAddressUpdateForm;
import com.leaforbook.orange.dao.model.OrangeCustomAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomAddressService {
    void create(String userId, CustomAddressForm form);

    void update(CustomAddressUpdateForm form);

    void delete(CustomAddressIdForm form);

    OrangeCustomAddress get(CustomAddressIdForm form);

    Page<OrangeCustomAddress> query(String userId, CustomAddressQueryForm form);
}

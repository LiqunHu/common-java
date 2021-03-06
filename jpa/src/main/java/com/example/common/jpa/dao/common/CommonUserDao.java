package com.example.common.jpa.dao.common;

import com.example.common.jpa.dao.BaseIntegerDao;
import com.example.common.jpa.model.common.CommonUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommonUserDao extends BaseIntegerDao<CommonUser>{

    @Query("select new CommonUser(userId,userUsername,userEmail,userPhone,userName) from CommonUser where state='1' and userType='00' and (userUsername like CONCAT ('%',?1,'%') or userPhone like CONCAT ('%',?1,'%') or userName like CONCAT ('%',?1,'%'))")
    List<CommonUser> searchUser(String searchText, Pageable pageable);

    @Query("select new CommonUser(userId,userUsername,userEmail,userPhone,userName) from CommonUser where state='1' and userType='00' and (userUsername like CONCAT ('%',?1,'%') or userPhone like CONCAT ('%',?1,'%') or userName like CONCAT ('%',?1,'%'))")
    List<CommonUser> searchUser(String searchText);

    CommonUser findByUserId(String userId);

    List<CommonUser> findByUserIdIn(String [] userIds);

    List<CommonUser> findByUserPhoneIn(List<String> phones);

    CommonUser findByUserPhone(String userPhone);
}

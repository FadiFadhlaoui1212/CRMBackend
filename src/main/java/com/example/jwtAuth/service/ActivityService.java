package com.example.jwtAuth.service;


import com.example.jwtAuth.dto.ActivityUpdateDTO;
import com.example.jwtAuth.model.entity.Activity;

import java.util.List;

public interface ActivityService {

    void createActivity(Activity activity);

    void deleteActivityById(Long id);

    void updateActivityById(Long id, ActivityUpdateDTO dto);

}

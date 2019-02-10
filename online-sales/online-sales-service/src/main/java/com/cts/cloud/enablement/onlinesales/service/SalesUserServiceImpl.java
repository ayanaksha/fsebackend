package com.cts.cloud.enablement.onlinesales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.cloud.enablement.onlinesales.domain.SalesUser;
import com.cts.cloud.enablement.onlinesales.repository.SalesUserRepository;

/**
 * @author 547991
 *
 */
@Service
public class SalesUserServiceImpl implements SalesUserService {
	
	@Autowired
	SalesUserRepository salesUserRepository;

	@Override
	public SalesUser retrieveUserByEmpID(SalesUser user) {
		return salesUserRepository.findByEmpID(user.getEmpid());
	}
	
	@Override
	public SalesUser retrieveUserByEmailId(SalesUser user) {
		return salesUserRepository.findByUserEmailId(user.getUserEmailId());
	}
	
	@Override
	public SalesUser retrieveUserByEmpIDAndPassword(SalesUser user) {
		return salesUserRepository.findByEmpIDAndPassword(user.getEmpid(), user.getPassword());
	}

	@Override
	public SalesUser createNewUser(SalesUser user) {
		SalesUser existingUser = salesUserRepository.findByEmpID(user.getEmpid());
		SalesUser newUser = new SalesUser();
		if(existingUser == null) {
			newUser.setEmpID(user.getEmpid());
			newUser.setPassword(user.getPassword());
			newUser.setEmpName(user.getEmpname());
			newUser.setUserEmailId(user.getUserEmailId());
			newUser.setPhone(user.getPhone());
			newUser.setProjID(user.getProjId());
			newUser.setProjName(user.getProjname());
			newUser.setLocation(user.getLocation());
			newUser.setRole(user.getRole());
		}
		salesUserRepository.save(newUser);
		return newUser;
	}

	
	@Override
	public SalesUser updateUserRole(SalesUser user) {
		SalesUser existingUser = salesUserRepository.findByEmpID(user.getEmpid());
		
		existingUser.setRole(user.getRole());
		
		salesUserRepository.save(existingUser);
		return existingUser;
	}
	
	@Override
	public SalesUser retrieveUserByUserEmailIdAndPassword(SalesUser user) {
		return salesUserRepository.findByUserEmailIdAndPassword(user.getUserEmailId(), user.getPassword());
	}

	@Override
	public List<SalesUser> retrieveEmpByProj(SalesUser requestUser) {
		return salesUserRepository.findByProjID(requestUser.getProjId());
	}
	
	
}
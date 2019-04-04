package com.csi.leavemanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csi.leavemanagement.exceptions.LeaveCategoryServiceException;
import com.csi.leavemanagement.models.LeaveCategory;
import com.csi.leavemanagement.repositories.LeaveCategoryRepository;

@Service
public class LeaveCategoryService {

	private LeaveCategoryRepository leaveCategoryRepository;
	
	@Autowired
	public LeaveCategoryService(LeaveCategoryRepository leaveCategoryRepository) {
		this.leaveCategoryRepository = leaveCategoryRepository;
	}
	
	public List<LeaveCategory> findAll() {
		List<LeaveCategory> leaveCategories = (List<LeaveCategory>)this.leaveCategoryRepository.findAll();
		return leaveCategories;
	}
	
	public LeaveCategory create(LeaveCategory newLeaveCategory) throws LeaveCategoryServiceException {
		
		LeaveCategory leaveCategoryCheck = this.findById(newLeaveCategory.getLeaveCode());
		if(leaveCategoryCheck != null)
			throw new LeaveCategoryServiceException(LeaveCategoryServiceException.LEAVE_CATEGORY_ALREADY_EXIST);
		
		return this.leaveCategoryRepository.save(newLeaveCategory);
	}
	
	public LeaveCategory save(LeaveCategory leaveCategory) {
		return this.leaveCategoryRepository.save(leaveCategory);
	}
	
	public LeaveCategory findById(String id) {
		LeaveCategory leaveCategory = this.leaveCategoryRepository.findById(id).orElse(null);
		return leaveCategory;		
	}
	
	public void deleteByID(String id) {
		this.leaveCategoryRepository.deleteById(id);		
	}
}

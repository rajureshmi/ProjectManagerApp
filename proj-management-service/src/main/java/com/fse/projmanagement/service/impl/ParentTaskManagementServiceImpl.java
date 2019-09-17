package com.fse.projmanagement.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projmanagement.dao.ParentTaskDao;
import com.fse.projmanagement.dao.repo.ParentTaskRepository;
import com.fse.projmanagement.model.ParentTask;
import com.fse.projmanagement.service.ParentTaskManagementService;

@Service
public class ParentTaskManagementServiceImpl implements ParentTaskManagementService {

	private static final Logger logger = LoggerFactory.getLogger(ParentTaskManagementServiceImpl.class);

	@Autowired
	private ParentTaskRepository parentTaskRepository;

	@Override
	public List<ParentTask> fetchParentTasks() {
		List<ParentTaskDao> parentTaskDaos = parentTaskRepository.findAll();
		if (Objects.nonNull(parentTaskDaos)) {
			logger.info("ParentTasks retrieved: "+ parentTaskDaos.size());
			return parentTaskDaos.stream().map(parentTaskDao -> new ParentTask(parentTaskDao))
					.collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public ParentTask addParentTask(ParentTask parentTaskToAdd) {
		ParentTaskDao parentTaskDao = new ParentTaskDao(parentTaskToAdd);
		ParentTaskDao addedProject = parentTaskRepository.saveAndFlush(parentTaskDao);
		if (Objects.nonNull(addedProject)) {
			logger.info("ParentTask added successfully. Id: : "+ addedProject.getParentId());
			parentTaskToAdd.setParentId(addedProject.getParentId());
			return parentTaskToAdd;
		}
		return null;
	}

}

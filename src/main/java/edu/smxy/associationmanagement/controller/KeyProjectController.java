package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.KeyProject;
import edu.smxy.associationmanagement.services.keyproject.KeyProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: associationmanagement
 * @description: 重点立项Controller
 * @author: SDH
 * @create: 2019-03-27 20:46
 */
@RestController
@EnableAutoConfiguration
public class KeyProjectController {
	@Autowired
	KeyProjectService keyProjectService;

	@PostMapping({"/getKeyProjectById"})
	public JSONResult getKeyProjectById(int id) {
		KeyProject keyProject = keyProjectService.selectByPrimaryKey(id);
		if (keyProject != null) {
			return JSONResult.build(200, "ok", keyProject);
		} else {
			return JSONResult.build(500, "error", null);
		}
	}

	@PostMapping({"/getAllRunningKeyProjectByAssId"})
	public JSONResult getAllRunningKeyProjectByAssId(int assid) {
		List<KeyProject> keyProjects = keyProjectService.getAllRunningKeyProjectByAssId(assid);
		if (keyProjects.size() > 0) {
			return JSONResult.build(200, "ok", keyProjects);
		} else {
			return JSONResult.build(500, "error", null);
		}
	}
}

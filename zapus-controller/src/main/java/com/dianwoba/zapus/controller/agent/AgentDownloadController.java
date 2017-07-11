package com.dianwoba.zapus.controller.agent;

import static com.dianwoba.zapus.common.util.ExceptionUtils.processException;
import static com.dianwoba.zapus.common.util.Preconditions.checkNotEmpty;
import static com.dianwoba.zapus.common.util.Preconditions.checkNotNull;

import com.dianwoba.zapus.service.agent.AgentPackageService;
import com.dianwoba.zapus.common.controller.BaseController;
import com.dianwoba.zapus.common.util.FileDownloadUtils;
import com.dianwoba.zapus.model.region.RegionInfo;
import com.dianwoba.zapus.service.region.RegionService;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Agent Download Controller.
 *
 * @author Tobi
 * @since 3.0
 */
@Controller
@RequestMapping("/agent")
public class AgentDownloadController extends BaseController {

	@Autowired
	private AgentPackageService agentPackageService;

	@Autowired
	private RegionService regionService;

	/**
	 * Download agent.
	 *
	 * @param fileName file path of agent
	 * @param response response.
	 */
	@RequestMapping(value = "/download/{fileName:[a-zA-Z0-9\\.\\-_]+}")
	public void download(@PathVariable String fileName, HttpServletResponse response) {
		File home = getConfig().getHome().getDownloadDirectory();
		File ngrinderFile = new File(home, fileName);
		FileDownloadUtils.downloadFile(response, ngrinderFile);
	}


	/**
	 * Download the latest agent.
	 *
	 * @param owner   agent owner
	 * @param region  agent region
	 * @param request request.
	 */
	@RequestMapping(value = "/download/{region}/{owner}")
	public String downloadDirect(@PathVariable(value = "owner") String owner,
	                             @PathVariable(value = "region") String region,
	                             ModelMap modelMap,
	                             HttpServletRequest request) {
		return downloadFile(owner, region, modelMap, request);
	}


	/**
	 * Download the latest agent.
	 *
	 * @param owner   agent owner
	 * @param region  agent region
	 * @param request request.
	 */
	@RequestMapping(value = "/download")
	public String download(@RequestParam(value = "owner", required = false) String owner,
	                       @RequestParam(value = "region", required = false) String region,
	                       ModelMap modelMap,
	                       HttpServletRequest request) {
		return downloadFile(owner, region, modelMap, request);
	}

	private String downloadFile(String owner, String region, ModelMap modelMap, HttpServletRequest request) {
		String connectingIP = request.getServerName();
		int port = getConfig().getControllerPort();
		try {
			if (isClustered()) {
				checkNotEmpty(region, "region should be provided to download agent in cluster mode.");
				RegionInfo regionInfo = checkNotNull(regionService.getOne(region), "selecting region '" + region + "'" +
						" is not valid");
				port = regionInfo.getControllerPort();
				connectingIP = regionInfo.getIp();
			}
			final File agentPackage = agentPackageService.createAgentPackage(region, connectingIP, port, owner);
			modelMap.clear();
			return "redirect:/agent/download/" + agentPackage.getName();
		} catch (Exception e) {
			throw processException(e);
		}
	}
}

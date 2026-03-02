package org.upyog.employee.dasboard.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upyog.employee.dasboard.query.constant.DashboardConstants;
import org.upyog.employee.dasboard.repository.impl.ServiceRequestRepositoryImpl;
import org.upyog.employee.dasboard.service.EmployeeDashboardService;
import org.upyog.employee.dasboard.util.ResponseInfoUtil;
import org.upyog.employee.dasboard.web.models.*;
import org.upyog.employee.dasboard.web.models.ResponseInfo.StatusEnum;

import java.util.*;

/**
 * Implementation of Employee Dashboard Service
 * Handles dashboard data retrieval for modules and role-based requests
 */
@Service
public class EmployeeDashboardServiceImpl implements EmployeeDashboardService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeDashboardServiceImpl.class);

	@Autowired
	private ServiceRequestRepositoryImpl dashboardRepository;

	/**
	 * Fetches dashboard data for a specific module
	 */
	public EmployeeDashboardResponse getEmployeeDashboardData(EmployeeDashboardRequest employeeDashboardRequest) {
		log.info("Fetching dashboard data for module: {} and tenant: {}",
				employeeDashboardRequest.getModuleName(), employeeDashboardRequest.getTenantId());

		EmployeeDashboardDetails dashboardDetails = dashboardRepository.fetchModuleData(employeeDashboardRequest);

		log.info("Dashboard data fetched successfully for module: {}", employeeDashboardRequest.getModuleName());

		ResponseInfo info = ResponseInfoUtil.createResponseInfo(
				employeeDashboardRequest.getRequestInfo(),
				DashboardConstants.DATA_FETCHED_SUCCESSFULLY,
				StatusEnum.SUCCESSFUL
		);

		return EmployeeDashboardResponse.builder()
				.responseInfo(info)
				.employeeDashbaord(dashboardDetails)
				.build();
	}

	/**
	 * Fetches dashboard data based on user roles
	 * Iterates through roles, maps them to modules, and fetches data for each unique module
	 */
	@Override
	public RoleBasedDashboardResponse getRoleBasedDashboardData(RoleBasedDashboardRequest request) {
		log.info("Processing role-based dashboard request for tenant: {} with {} roles",
				request.getTenantId(), request.getRoles().size());

		Map<String, EmployeeDashboardDetails> dashboardDataMap = new HashMap<>();
		Set<String> processedModules = new HashSet<>();

		// Iterate through roles and fetch data for corresponding modules
		for (Role role : request.getRoles()) {
			String moduleCode = DashboardConstants.ROLE_TO_MODULE_MAP.get(role.getCode());

			if (moduleCode != null && !processedModules.contains(moduleCode)) {
				log.info("Processing role: {} -> module: {}", role.getCode(), moduleCode);
				processedModules.add(moduleCode);

				// Create module-specific request
				EmployeeDashboardRequest moduleRequest = new EmployeeDashboardRequest();
				moduleRequest.setRequestInfo(request.getRequestInfo());
				moduleRequest.setModuleName(ModuleName.valueOf(moduleCode));
				moduleRequest.setTenantId(request.getTenantId());

				// Fetch dashboard data for the module
				EmployeeDashboardDetails details = dashboardRepository.fetchModuleData(moduleRequest);
				dashboardDataMap.put(moduleCode, details);

				log.debug("Data fetched for module: {} - Received: {}, Approved: {}, Pending: {}",
						moduleCode, details.getApplicationReceived(),
						details.getApplicationApproved(), details.getApplicationPending());
			} else if (moduleCode == null) {
				log.warn("No module mapping found for role code: {}", role.getCode());
			}
		}

		log.info("Role-based dashboard data fetched successfully. Total modules processed: {}",
				processedModules.size());

		ResponseInfo info = ResponseInfoUtil.createResponseInfo(
				request.getRequestInfo(),
				DashboardConstants.DATA_FETCHED_SUCCESSFULLY,
				StatusEnum.SUCCESSFUL
		);

		return RoleBasedDashboardResponse.builder()
				.responseInfo(info)
				.dashboardData(dashboardDataMap)
				.build();
	}
}

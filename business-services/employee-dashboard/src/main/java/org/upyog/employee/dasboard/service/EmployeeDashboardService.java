package org.upyog.employee.dasboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upyog.employee.dasboard.repository.ServiceRequestRepository;
import org.upyog.employee.dasboard.web.models.*;

@Service
public interface EmployeeDashboardService {

	
	
	EmployeeDashboardResponse getEmployeeDashboardData(EmployeeDashboardRequest employeeDashboardRequest);

	RoleBasedDashboardResponse getRoleBasedDashboardData(RoleBasedDashboardRequest request);

}

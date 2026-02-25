import React, { useEffect, useState } from 'react';
import { useTranslation } from "react-i18next";
import { Loader } from "@upyog/digit-ui-react-components";
import { MODULE_ROLE_MAPPING } from '../config/module-role-mapping';

/**
 * @author - Shivank Shukla - NIUA
 * 
 * This component displays a dashboard with key metrics for employees.
 * 
 * How it works:
 * 1. The component initially renders cards with loaders.
 * 2. When the component loads, it fetches dashboard data from an API.
 * 3. It displays four cards showing different metrics:
 *    - Applications Received
 *    - Total Amount
 *    - Pending Applications
 *    - Approved Applications
 * 
 * Technical details:
 * - Fetches data using the Digit.EmployeeDashboardService.search method.
 * - Uses the current tenant ID from Digit.ULBService.getCurrentUlb().
 * - Each card has its own loading state, replaced by data when available.
 * - Smart number formatting for large values (lakhs/crores)
 * 
 * Note: If the API call fails, an error is logged to the console, and the cards
 * will remain in their loading state.
 * 
 */

const formatNumbers = (amount) => {
  if (amount === null || amount === undefined) return '';
  const num = Number(amount);
  const numStr = num.toString();
  
  if (numStr.length <= 5) {
    const lastThree = numStr.substring(numStr.length - 3);
    const otherNums = numStr.substring(0, numStr.length - 3);
    const formatted = otherNums.replace(/\B(?=(\d{2})+(?!\d))/g, ',');
    return otherNums ? formatted + ',' + lastThree : lastThree;
  }
  else if (num >= 10000000) {
    const crores = num / 10000000;
    return crores >= 100 ? `${Math.round(crores)} Crores` : `${crores.toFixed(2)} Crores`;
  } else if (num >= 100000) {
    const lakhs = num / 100000;
    return lakhs >= 100 ? `${Math.round(lakhs)} Lakhs` : `${lakhs.toFixed(2)} Lakhs`;
  }
  return numStr;
};

const formatIndianCurrency = (amount) => {
  if (amount === null || amount === undefined) return '';
  return `₹${formatNumbers(amount)}`;
};

const getEmployeeModules = () => {
  const user = Digit.UserService.getUser();
  if (!user || !user.info || !user.info.roles) return [];
  
  const roles = user.info.roles;
  const employeeModules = [];
  
  for (const [module, roleCodes] of Object.entries(MODULE_ROLE_MAPPING)) {
    const hasModuleRole = roles.some(role => 
      roleCodes.some(roleCode => role.code.includes(roleCode))
    );
    if (hasModuleRole) employeeModules.push(module);
  }
  
  return employeeModules;
};

const ModuleDashboardSection = ({ moduleName, data, t }) => {
  return (
    <div style={{ marginBottom: "40px" }}>
      <div style={{ 
        fontWeight: "bold", 
        fontSize: "20px", 
        marginBottom: "15px",
        textAlign: "center",
        color: "#505A5F"
      }}>
        {t(`${moduleName}_DASHBOARD`)}
      </div>
      
      <div className="ground-container moduleCardWrapper gridModuleWrapper">
        {[
          { title: t("ES_APPLICATION_RECEIVED"), count: data.applicationReceived || 0, color: "blue" },
          { title: t("ES_TOTAL_AMOUNT"), count: data.totalAmount || 0, color: "teal", isAmount: true },
          { title: t("ES_APPLICATION_PENDING"), count: data.applicationPending || 0, color: "purple" },
          { title: t("ES_APPLICATION_APPROVED"), count: data.applicationApproved || 0, color: "green" },
        ].map(({ title, count, color, isAmount }, index) => (
          <div key={index} className={`status-card ${color}`}>
            <div className="card-content">
              {count === null ? (
                <Loader />
              ) : (
                <React.Fragment>
                  <span className="count">
                    {isAmount ? formatIndianCurrency(count) : formatNumbers(count)}
                  </span>
                  <span className="title">{title}</span>
                </React.Fragment>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

const EmployeeDashboard = () => {
  const { t } = useTranslation();
  const [modulesData, setModulesData] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchAllModuleDashboards = async () => {
      try {
        const tenantId = Digit.ULBService.getCurrentUlb().code;
        const employeeModules = getEmployeeModules();
        
        if (employeeModules.length === 0) return;
        
        const responses = await Promise.all(
          employeeModules.map(async (moduleName) => {
            const response = await Digit.EmployeeDashboardService.search({ tenantId, moduleName });
            return { moduleName, data: response?.employeeDashboard || null };
          })
        );
        
        const dataByModule = {};
        responses.forEach(({ moduleName, data }) => {
          if (data) dataByModule[moduleName] = data;
        });
        
        setModulesData(dataByModule);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching dashboard data:", error);
        setLoading(false);
      }
    };
    
    fetchAllModuleDashboards();
  }, []);

  if (loading) {
    return <Loader />;
  }

  return (
    <div className="employee-app-container" style={{ padding: "20px" }}>
      <div style={{ 
        textAlign: "center", 
        fontWeight: "bold", 
        fontSize: "24px", 
        marginBottom: "30px" 
      }}>
        {t("COMMON_ULB_DASHBOARD")}
      </div>
      
      {Object.entries(modulesData).map(([moduleName, data]) => (
        <ModuleDashboardSection 
          key={moduleName}
          moduleName={moduleName}
          data={data}
          t={t}
        />
      ))}
    </div>
  );
};

export default EmployeeDashboard;
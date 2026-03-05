import React, { useEffect, useState } from 'react';
import { useTranslation } from "react-i18next";
import { Loader } from "@upyog/digit-ui-react-components";

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
              <React.Fragment>
                <span className="count">
                  {isAmount ? formatIndianCurrency(count) : formatNumbers(count)}
                </span>
                <span className="title">{title}</span>
              </React.Fragment>
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
    const fetchDashboardData = async () => {
      try {
        const response = await Digit.EmployeeDashboardService.roleBaseSearch({});
        
        if (response?.dashboardData) {
          setModulesData(response.dashboardData);
        }
        
        setLoading(false);
      } catch (error) {
        console.error("Error fetching dashboard data:", error);
        setLoading(false);
      }
    };
    
    fetchDashboardData();
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
      
      {Object.keys(modulesData).length === 0 ? (
        <div style={{ 
          textAlign: "center", 
          padding: "40px", 
          color: "#505A5F",
          fontSize: "16px" 
        }}>
          {t("NO_DASHBOARD_ACCESS")}
        </div>
      ) : (
        Object.entries(modulesData).map(([moduleName, data]) => (
          <ModuleDashboardSection 
            key={moduleName}
            moduleName={moduleName}
            data={data}
            t={t}
          />
        ))
      )}
    </div>
  );
};

export default EmployeeDashboard;

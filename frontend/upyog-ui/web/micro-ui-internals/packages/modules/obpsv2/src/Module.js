import React from "react";
import { useTranslation } from "react-i18next";
import { useRouteMatch } from "react-router-dom";
import { Loader, CitizenHomeCard, OBPSIcon, CitizenInfoLabel } from "@upyog/digit-ui-react-components";
import CitizenApp from "./pages/citizen";
import Create from "./pages/citizen/Create";
import ApplicantDetails  from "./pageComponents/ApplicantDetails";
import AddressDetails from "./pageComponents/AddressDetails";
import LandDetails from "./pageComponents/LandDetails";
import CheckPage from "./pages/citizen/Create/CheckPage";
// import EmployeeApp from "./pages/employee";

const OBPSModule = ({ stateCode, userType, tenants }) => {
  const moduleCode = ["bpa", "bpareg", "common"]; //"bpa";
  const { path, url } = useRouteMatch();
  const language = Digit.StoreData.getCurrentLanguage();
  const { isLoading, data: store } = Digit.Services.useStore({ stateCode, moduleCode, language });

  Digit.SessionStorage.set("OBPSV2_TENANTS", tenants);

  if (isLoading) {
    return <Loader />;
  }

  if (userType === "citizen") {
    return <CitizenApp path={path} stateCode={stateCode} />;
  }

  return <EmployeeApp path={path} stateCode={stateCode} />
}

const OBPSLinks = ({ matchPath, userType }) => {
  const { t } = useTranslation();

  const links = [
    
    {
      link: `${matchPath}/stakeholder/apply/stakeholder-docs-required`,
      i18nKey: t("BPA_CITIZEN_HOME_STAKEHOLDER_LOGIN_LABEL"),
    },
    {
      link: `${matchPath}/home`,
      i18nKey: t("BPA_CITIZEN_HOME_ARCHITECT_LOGIN_LABEL"),
    },
  ];

  return (
    <CitizenHomeCard header={t("ACTION_TEST_BUILDING_PLAN_APPROVAL")} links={links} Icon={() => <OBPSIcon />}
      Info={() => <CitizenInfoLabel style={{margin: "0px", padding: "10px"}} info={t("CS_FILE_APPLICATION_INFO_LABEL")} text={t(`BPA_CITIZEN_HOME_STAKEHOLDER_INCLUDES_INFO_LABEL`)} />} isInfo={true}
    />
  );
} 

const componentsToRegister = {
  OBPSModule,
  OBPSLinks,
  ApplicantDetails,
  AddressDetails,
  LandDetails,
  BPACreate: Create,
  CheckPage
}

export const initOBPSV2Components = () => {
  Object.entries(componentsToRegister).forEach(([key, value]) => {
    Digit.ComponentRegistryService.setComponent(key, value);
  });
};
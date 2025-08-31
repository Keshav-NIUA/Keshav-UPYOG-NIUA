import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { Switch, useLocation, Route } from "react-router-dom";
import { PrivateRoute, BackButton } from "@upyog/digit-ui-react-components";

const App = ({ path }) => {
  const location = useLocation();
  const { t } = useTranslation();


  const BPACreate= Digit?.ComponentRegistryService?.getComponent("BPACreate");
  const isDocScreenAfterEdcr = sessionStorage.getItem("clickOnBPAApplyAfterEDCR") === "true" ? true : false
  console.log("path", path);
  return (
    <React.Fragment>
      <div className="ws-citizen-wrapper">
       {!location.pathname.includes("response") && !location.pathname.includes("openlink/stakeholder") && !location.pathname.includes("/acknowledgement") && !isDocScreenAfterEdcr && <BackButton style={{ border: "none" }}>{t("CS_COMMON_BACK")}</BackButton>}
      <Switch>
        <PrivateRoute path={`${path}/my-applications`} />
        <PrivateRoute path={`${path}/stakeholder/apply/stakeholder-docs-required`} component={BPACreate}/>
      </Switch>
      </div>
    </React.Fragment>
  );
};

export default App;

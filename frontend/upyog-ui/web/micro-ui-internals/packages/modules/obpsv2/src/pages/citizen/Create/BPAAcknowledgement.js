import { Banner, Card, LinkButton, Loader, Row, StatusTable, SubmitBar, Toast } from "@upyog/digit-ui-react-components";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { bpaPayload } from "../../../utils";
import getBPAAcknowledgementData from "../../../utils/getBPAAcknowledgementData";

const GetActionMessage = (props) => {
  const { t } = useTranslation();
  if (props.isSuccess) {
    return t("BPA_SUBMIT_SUCCESSFULL");
  } else if (props.isLoading) {
    return t("BPA_APPLICATION_PENDING");
  } else if (!props.isSuccess) {
    return t("BPA_APPLICATION_FAILED");
  }
};

const rowContainerStyle = {
  padding: "4px 0px",
  justifyContent: "space-between",
};

const BannerPicker = (props) => {
  return (
    <Banner
      message={GetActionMessage(props)}
      applicationNumber={props.data?.bpaApplicationDetail?.applicationNo}
      info={props.isSuccess ? props.t("BPA_APPLICATION_NO") : ""}
      successful={props.isSuccess}
      style={{ width: "100%" }}
    />
  );
};

const BPAAcknowledgement = ({ data, onSuccess }) => {
  const { t } = useTranslation();
  const tenantId = Digit.ULBService.getCitizenCurrentTenant(true) || Digit.ULBService.getCurrentTenantId();
  const mutation = Digit.Hooks.bpa.useBPACreateAPI(tenantId);
  const user = Digit.UserService.getUser().info;
  const { data: storeData } = Digit.Hooks.useStore.getInitData();
  const { tenants } = storeData || {};
  const [showToast, setShowToast] = useState(null);

  useEffect(() => {
    try {
      data.tenantId = tenantId;
      let formdata = bpaPayload(data);
      mutation.mutate(formdata, { onSuccess });
    } catch (err) {
      setShowToast({ error: true, label: t("BPA_APPLICATION_SUBMIT_ERROR") });
    }
  }, []);

  useEffect(() => {
    if (showToast) {
      const timer = setTimeout(() => {
        setShowToast(null);
      }, 2000); // Close toast after 2 seconds
      return () => clearTimeout(timer); // Clear timer on cleanup
    }
  }, [showToast]);

  Digit.Hooks.useCustomBackNavigation({
    redirectPath: `upyog-ui/citizen`
  });

  const handleDownloadPdf = async () => {
    try {
      let bpaApplicationDetail = mutation.data?.bpaApplicationDetail;
      const tenantInfo = tenants.find((tenant) => tenant.code === bpaApplicationDetail.tenantId);
      let tenantId = bpaApplicationDetail.tenantId || tenantId;
      const data = await getBPAAcknowledgementData({ ...bpaApplicationDetail }, tenantInfo, t);
      Digit.Utils.pdf.generate(data);
    } catch (err) {
      setShowToast({ error: true, label: t("BPA_ACKNOWLEDGEMENT_PDF_ERROR") });
    }
  };

  return mutation.isLoading || mutation.isIdle ? (
    <Loader />
  ) : (
    <Card>
      <BannerPicker t={t} data={mutation.data} isSuccess={mutation.isSuccess} isLoading={mutation.isIdle || mutation.isLoading} />
      <StatusTable>
         {mutation.isSuccess && (
            <Row
            rowContainerStyle={rowContainerStyle}
            last       
            textStyle={{ whiteSpace: "pre", width: "60%" }}
            />
        )}
        {mutation.isSuccess && (
          <Row
            rowContainerStyle={rowContainerStyle}
            label={t("BPA_APPLICANT_NAME")}
            text={data?.applicant?.applicantName || "N/A"}
          />
        )}
        {mutation.isSuccess && (
          <Row
            rowContainerStyle={rowContainerStyle}
            label={t("BPA_CONSTRUCTION_TYPE")}
            text={t(data?.land?.constructionType?.name) || "N/A"}
          />
        )}
        {mutation.isSuccess && (
          <Row
            rowContainerStyle={rowContainerStyle}
            label={t("BPA_PROPERTY_ADDRESS")}
            text={`${data?.address?.permanent?.houseNo || ""}, ${data?.address?.permanent?.addressLine1 || ""}, ${data?.address?.permanent?.city?.name || ""}`}
          />
        )}
        {mutation.isSuccess && (
          <Row
            rowContainerStyle={rowContainerStyle}
            label={t("BPA_SUBMISSION_DATE")}
            text={new Date().toLocaleDateString()}
          />
        )}
        {mutation.isSuccess && (
          <Row
            rowContainerStyle={rowContainerStyle}
            label={t("BPA_STATUS")}
            text={t("BPA_SUBMITTED")}
            last
          />
        )}
      </StatusTable>
      {mutation.isSuccess && <SubmitBar label={t("BPA_DOWNLOAD_ACKNOWLEDGEMENT")} onSubmit={handleDownloadPdf} />}
      {user?.type === "CITIZEN" ? (
        <Link to={`upyog-ui/citizen`}>
          <LinkButton label={t("CORE_COMMON_GO_TO_HOME")} />
        </Link>
      ) : (
        <Link to={`upyog-ui/employee`}>
          <LinkButton label={t("CORE_COMMON_GO_TO_HOME")} />
        </Link>
      )}
      {showToast && (
        <Toast
          error={showToast.error}
          warning={showToast.warning}
          label={t(showToast.label)}
          onClose={() => {
            setShowToast(null);
          }}
        />
      )}
    </Card>
  );
};

export default BPAAcknowledgement;
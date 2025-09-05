import {
    Card,
    CardSubHeader,
    Header,
    Loader,
    Row,
    StatusTable,
    MultiLink,
    Toast,
    CheckBox
  } from "@upyog/digit-ui-react-components";
  import React, { useState } from "react";
  import { useTranslation } from "react-i18next";
  import { useParams } from "react-router-dom";
  import get from "lodash/get";
  // import WFApplicationTimeline from "../../pageComponents/WFApplicationTimeline";
  import getBPAAcknowledgementData from "../../utils/getBPAAcknowledgementData";
  
  /**
   * `BPAApplicationDetails` is a React component that fetches and displays detailed information for a specific Building Plan Approval (BPA) application.
   * It fetches data for the application using the `useBPASearchAPI` hook and displays the details in sections such as:
   * - Application Number
   * - Applicant Information (name, mobile number, email, father's name, mother's name, PAN, Aadhaar)
   * - Address Information (permanent and correspondence address)
   * - Land Details (construction type, plot details, adjoining owners, future provisions, technical person details)
   * 
   * The component also handles:
   * - Displaying a loading state (via a `Loader` component) while fetching data.
   * - A "toast" notification for any errors or status updates.
   * - Showing downloadable options via `MultiLink` if available.
   * 
   * @returns {JSX.Element} Displays detailed BPA application information with applicant details, address, and land details.
   */
  const BPAApplicationDetails = () => {
    const { t } = useTranslation();
    const { acknowledgementIds, tenantId } = useParams();
    const [showOptions, setShowOptions] = useState(false);
    const [showToast, setShowToast] = useState(null);
    const { data: storeData } = Digit.Hooks.useStore.getInitData();
    const { tenants } = storeData || {};
  
    const { isLoading, isError, error, data, refetch } = Digit.Hooks.bpa.useBPASearchAPI({
      tenantId,
      filters: { applicationNo: acknowledgementIds },
    });
  
    const bpaApplicationDetail = get(data, "BPAApplications", []);
    const bpaId = get(data, "BPAApplications[0].applicationNo", []);
  
    let bpa_details = (bpaApplicationDetail && bpaApplicationDetail.length > 0 && bpaApplicationDetail[0]) || {};
    const application = bpa_details;
  
    sessionStorage.setItem("bpa", JSON.stringify(application));
  
    const mutation = Digit.Hooks.bpa.useBPACreateAPI(tenantId, false);
  
    const { data: reciept_data, isLoading: recieptDataLoading } = Digit.Hooks.useRecieptSearch(
      {
        tenantId: tenantId,
        businessService: "bpa-services",
        consumerCodes: acknowledgementIds,
        isEmployee: false,
      },
      { enabled: acknowledgementIds ? true : false }
    );
  
    /**
     * This function handles the receipt generation and updates the BPA application details
     * with the generated receipt's file store ID.
     */
    async function getRecieptSearch({ tenantId, payments, ...params }) {
      let application = bpaApplicationDetail[0] || {};
      let fileStoreId = application?.paymentReceiptFilestoreId
      if (!fileStoreId) {
        let response = { filestoreIds: [payments?.fileStoreId] };
        response = await Digit.PaymentService.generatePdf(tenantId, { Payments: [{ ...payments }] }, "bpa-services-receipt");
        const updatedApplication = {
          ...application,
          paymentReceiptFilestoreId: response?.filestoreIds[0]
        };
        await mutation.mutateAsync({
          BPAApplications: [updatedApplication]
        });
        fileStoreId = response?.filestoreIds[0];
        refetch();
      }
      const fileStore = await Digit.PaymentService.printReciept(tenantId, { fileStoreIds: fileStoreId });
      window.open(fileStore[fileStoreId], "_blank");
    }
  
    let dowloadOptions = [];
    dowloadOptions.push({
      label: t("BPA_DOWNLOAD_ACKNOWLEDGEMENT"),
      onClick: () => getAcknowledgementData(),
    });
  
    const getAcknowledgementData = async () => {
      const applications = application || {};
      const tenantInfo = tenants.find((tenant) => tenant.code === applications.tenantId);
      const acknowldgementDataAPI = await getBPAAcknowledgementData({ ...applications }, tenantInfo, t);
      Digit.Utils.pdf.generate(acknowldgementDataAPI);
    };
  
    if (isLoading) {
      return <Loader />;
    }
  
    if (reciept_data && reciept_data?.Payments.length > 0 && recieptDataLoading == false) {
      dowloadOptions.push({
        label: t("BPA_FEE_RECEIPT"),
        onClick: () => getRecieptSearch({ tenantId: reciept_data?.Payments[0]?.tenantId, payments: reciept_data?.Payments[0] }),
      });
    }
  
    return (
      <React.Fragment>
        <div>
          <div className="cardHeaderWithOptions" style={{ marginRight: "auto", maxWidth: "960px" }}>
            <Header styles={{ fontSize: "32px" }}>{t("BPA_APPLICATION_DETAILS")}</Header>
            {dowloadOptions && dowloadOptions.length > 0 && (
              <MultiLink
                className="multilinkWrapper"
                onHeadClick={() => setShowOptions(!showOptions)}
                displayOptions={showOptions}
                options={dowloadOptions}
              />
            )}
          </div>
          <Card>
            <StatusTable>
              <Row className="border-none" label={t("BPA_APPLICATION_NO")} text={bpa_details?.applicationNo || t("CS_NA")} />
            </StatusTable>
  
            <CardSubHeader style={{ fontSize: "24px" }}>{t("BPA_APPLICANT_DETAILS")}</CardSubHeader>
            <StatusTable>
              <Row
                label={t("BPA_APPLICANT_NAME")}
                text={bpa_details?.applicant?.applicantName || t("CS_NA")}
              />
              <Row
                label={t("BPA_MOBILE_NO")}
                text={bpa_details?.applicant?.mobileNumber || t("CS_NA")}
              />
              <Row
                label={t("BPA_ALT_MOBILE_NO")}
                text={bpa_details?.applicant?.alternateNumber || t("CS_NA")}
              />
              <Row
                label={t("BPA_EMAIL_ID")}
                text={bpa_details?.applicant?.emailId || t("CS_NA")}
              />
              <Row
                label={t("BPA_FATHER_NAME")}
                text={bpa_details?.applicant?.fatherName || t("CS_NA")}
              />
              <Row
                label={t("BPA_MOTHER_NAME")}
                text={bpa_details?.applicant?.motherName || t("CS_NA")}
              />
              <Row
                label={t("BPA_PAN_CARD")}
                text={bpa_details?.applicant?.panCardNumber || t("CS_NA")}
              />
              <Row
                label={t("BPA_AADHAAR_CARD")}
                text={bpa_details?.applicant?.aadhaarNumber || t("CS_NA")}
              />
            </StatusTable>
  
            <CardSubHeader style={{ fontSize: "24px" }}>{t("BPA_ADDRESS_DETAILS")}</CardSubHeader>
            <CardSubHeader style={{ fontSize: "20px" }}>{t("BPA_PERMANENT_ADDRESS")}</CardSubHeader>
            <StatusTable>
              <Row
                label={t("BPA_HOUSE_NO")}
                text={bpa_details?.address?.permanent?.houseNo || t("CS_NA")}
              />
              <Row
                label={t("BPA_ADDRESS_LINE_1")}
                text={bpa_details?.address?.permanent?.addressLine1 || t("CS_NA")}
              />
              <Row
                label={t("BPA_ADDRESS_LINE_2")}
                text={bpa_details?.address?.permanent?.addressLine2 || t("CS_NA")}
              />
              <Row
                label={t("BPA_LANDMARK")}
                text={bpa_details?.address?.permanent?.landmark || t("CS_NA")}
              />
              <Row
                label={t("BPA_DISTRICT")}
                text={bpa_details?.address?.permanent?.district?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_CITY")}
                text={bpa_details?.address?.permanent?.city?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_STATE")}
                text={bpa_details?.address?.permanent?.state?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_PIN_CODE")}
                text={bpa_details?.address?.permanent?.pincode || t("CS_NA")}
              />
            </StatusTable>
  
            <CardSubHeader style={{ fontSize: "20px" }}>{t("BPA_CORRESPONDENCE_ADDRESS")}</CardSubHeader>
            {bpa_details?.address?.sameAsPermanent ? (
              <div style={{ marginTop: "16px" }}>
                <CheckBox
                  label={t("BPA_SAME_AS_PERMANENT")}
                  checked={true}
                  disabled={true}
                />
              </div>
            ) : (
              <StatusTable style={{ marginTop: "16px" }}>
                <Row
                  label={t("BPA_HOUSE_NO")}
                  text={bpa_details?.address?.correspondence?.houseNo || t("CS_NA")}
                />
                <Row
                  label={t("BPA_ADDRESS_LINE_1")}
                  text={bpa_details?.address?.correspondence?.addressLine1 || t("CS_NA")}
                />
                <Row
                  label={t("BPA_ADDRESS_LINE_2")}
                  text={bpa_details?.address?.correspondence?.addressLine2 || t("CS_NA")}
                />
                <Row
                  label={t("BPA_DISTRICT")}
                  text={bpa_details?.address?.correspondence?.district?.name || t("CS_NA")}
                />
                <Row
                  label={t("BPA_CITY")}
                  text={bpa_details?.address?.correspondence?.city?.name || t("CS_NA")}
                />
                <Row
                  label={t("BPA_STATE")}
                  text={bpa_details?.address?.correspondence?.state?.name || t("CS_NA")}
                />
                <Row
                  label={t("BPA_PIN_CODE")}
                  text={bpa_details?.address?.correspondence?.pincode || t("CS_NA")}
                />
              </StatusTable>
            )}
  
            <CardSubHeader style={{ fontSize: "24px" }}>{t("BPA_LAND_DETAILS")}</CardSubHeader>
            <StatusTable>
              <Row
                label={t("BPA_CONSTRUCTION_TYPE")}
                text={bpa_details?.land?.constructionType?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_AREA_AUTHORITY_MAPPING")}
                text={bpa_details?.land?.areaAuthority || t("CS_NA")}
              />
              <Row
                label={t("BPA_MOUZA")}
                text={bpa_details?.land?.mouza || t("CS_NA")}
              />
              <Row
                label={t("BPA_OLD_DAG_NUMBER")}
                text={bpa_details?.land?.oldDagNumber || t("CS_NA")}
              />
              <Row
                label={t("BPA_NEW_DAG_NUMBER")}
                text={bpa_details?.land?.newDagNumber || t("CS_NA")}
              />
              <Row
                label={t("BPA_OLD_PATTA_NUMBER")}
                text={bpa_details?.land?.oldPattaNumber || t("CS_NA")}
              />
              <Row
                label={t("BPA_NEW_PATTA_NUMBER")}
                text={bpa_details?.land?.newPattaNumber || t("CS_NA")}
              />
              <Row
                label={t("BPA_TOTAL_PLOT_AREA")}
                text={bpa_details?.land?.totalPlotArea ? `${bpa_details.land.totalPlotArea} sq. ft.` : t("CS_NA")}
              />
            </StatusTable>
  
            <CardSubHeader style={{ fontSize: "20px" }}>{t("BPA_ADJOINING_LAND_OWNERS")}</CardSubHeader>
            <StatusTable>
              <Row
                label={t("BPA_NORTH")}
                text={bpa_details?.land?.adjoiningOwners?.north || t("CS_NA")}
              />
              <Row
                label={t("BPA_SOUTH")}
                text={bpa_details?.land?.adjoiningOwners?.south || t("CS_NA")}
              />
              <Row
                label={t("BPA_EAST")}
                text={bpa_details?.land?.adjoiningOwners?.east || t("CS_NA")}
              />
              <Row
                label={t("BPA_WEST")}
                text={bpa_details?.land?.adjoiningOwners?.west || t("CS_NA")}
              />
            </StatusTable>
  
            <CardSubHeader style={{ fontSize: "20px" }}>{t("BPA_FUTURE_PROVISIONS")}</CardSubHeader>
            <StatusTable>
              <Row
                label={t("BPA_VERTICAL_EXTENSION")}
                text={bpa_details?.land?.futureProvisions?.verticalExtension?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_HORIZONTAL_EXTENSION")}
                text={bpa_details?.land?.futureProvisions?.horizontalExtension?.name || t("CS_NA")}
              />
            </StatusTable>
  
            <StatusTable style={{ marginTop: "16px" }}>
              <Row
                label={t("BPA_RTP_CATEGORY")}
                text={bpa_details?.land?.rtpCategory?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_REGISTERED_TECHNICAL_PERSON")}
                text={bpa_details?.land?.registeredTechnicalPerson?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_OCCUPANCY_TYPE")}
                text={bpa_details?.land?.occupancyType?.name || t("CS_NA")}
              />
              <Row
                label={t("BPA_TOD_BENEFITS")}
                text={bpa_details?.land?.todBenefits ? t("CS_YES") + ", " + t("BPA_WITH_TDR") : t("CS_NA")}
              />
              <Row
                label={t("BPA_FORM_36")}
                text={bpa_details?.land?.documents?.some(doc => doc.documentType === "FORM_36") ? t("BPA_FILE_UPLOADED") : t("CS_NA")}
              />
              <Row
                label={t("BPA_FORM_39")}
                text={bpa_details?.land?.documents?.some(doc => doc.documentType === "FORM_39") ? t("BPA_FILE_UPLOADED") : t("CS_NA")}
              />
              <Row
                label={t("BPA_TOD_ZONE")}
                text={bpa_details?.land?.todZone?.name || t("CS_NA")}
              />
            </StatusTable>
  
            {/* <WFApplicationTimeline application={application} id={application?.applicationNo} userType={"citizen"} /> */}
            {showToast && (
              <Toast
                error={showToast.key}
                label={t(showToast.label)}
                style={{ bottom: "0px" }}
                onClose={() => {
                  setShowToast(null);
                }}
              />
            )}
          </Card>
        </div>
      </React.Fragment>
    );
  };
  
  export default BPAApplicationDetails;
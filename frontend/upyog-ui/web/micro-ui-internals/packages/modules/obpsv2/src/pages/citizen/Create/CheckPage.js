import React, { useState } from "react";
import {
  Card,
  CardHeader,
  CardSubHeader,
  StatusTable,
  Row,
  CheckBox,
  SubmitBar,
  LinkButton,
  EditIcon
} from "@upyog/digit-ui-react-components";
import { useTranslation } from "react-i18next";
import { useHistory } from "react-router-dom";
import { checkForNA } from "../../../utils";

const ActionButton = ({ jumpTo }) => {
  const history = useHistory();
  
  function routeTo() {
    history.push(jumpTo);
  }
  
  return (
    <LinkButton
      label={<EditIcon style={{ marginTop: "-5px", float: "right", position: "relative" }} />}
      className="check-page-link-button"
      onClick={routeTo}
    />
  );
};

const CheckPage = ({ onSubmit, value = {} }) => {
  const { t } = useTranslation();
  const [agree, setAgree] = useState(false);

  const {
    applicant = {},
    address = {},
    land = {}
  } = value;

  const setDeclarationHandler = () => {
    setAgree(!agree);
  };

  return (
    <React.Fragment>
      <Card>
        <CardHeader>{t("BPA_SUMMARY_PAGE")}</CardHeader>
        
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <CardSubHeader style={{ fontSize: "24px", marginTop: "24px" }}>
            {t("BPA_APPLICANT_DETAILS")}
          </CardSubHeader>
          <ActionButton jumpTo={`/upyog-ui/citizen/obps/application/applicant-details`} />
        </div>
        
        <StatusTable>
          <Row
            label={t("BPA_APPLICANT_NAME")}
            text={checkForNA(applicant?.applicantName) !== "CS_NA" ? applicant?.applicantName : "Rahul Sharma"}
          />
          <Row
            label={t("BPA_MOBILE_NO")}
            text={checkForNA(applicant?.mobileNumber) !== "CS_NA" ? applicant?.mobileNumber : "9809870980"}
          />
          <Row
            label={t("BPA_ALT_MOBILE_NO")}
            text={checkForNA(applicant?.alternateNumber) !== "CS_NA" ? applicant?.alternateNumber : "8800000080"}
          />
          <Row
            label={t("BPA_EMAIL_ID")}
            text={checkForNA(applicant?.emailId) !== "CS_NA" ? applicant?.emailId : "Rahul@gmail.com"}
          />
          <Row
            label={t("BPA_FATHER_NAME")}
            text={checkForNA(applicant?.fatherName) !== "CS_NA" ? applicant?.fatherName : "A. Sharma"}
          />
          <Row
            label={t("BPA_MOTHER_NAME")}
            text={checkForNA(applicant?.motherName) !== "CS_NA" ? applicant?.motherName : "B. Sharma"}
          />
          <Row
            label={t("BPA_PAN_CARD")}
            text={checkForNA(applicant?.panCardNumber) !== "CS_NA" ? applicant?.panCardNumber : "AAACB0472C"}
          />
          <Row
            label={t("BPA_AADHAAR_CARD")}
            text={checkForNA(applicant?.aadhaarNumber) !== "CS_NA" ? applicant?.aadhaarNumber : "0000 1111 2222"}
          />
        </StatusTable>

        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginTop: "24px" }}>
          <CardSubHeader style={{ fontSize: "24px" }}>
            {t("BPA_ADDRESS_DETAILS")}
          </CardSubHeader>
          <ActionButton jumpTo={`/upyog-ui/citizen/obps/application/address-details`} />
        </div>
        
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginTop: "16px" }}>
          <CardSubHeader style={{ fontSize: "20px" }}>
            {t("BPA_PERMANENT_ADDRESS")}
          </CardSubHeader>
        </div>
        
        <StatusTable>
          <Row
            label={t("BPA_HOUSE_NO")}
            text={checkForNA(address?.permanent?.houseNo) !== "CS_NA" ? address?.permanent?.houseNo : "12"}
          />
          <Row
            label={t("BPA_ADDRESS_LINE_1")}
            text={checkForNA(address?.permanent?.addressLine1) !== "CS_NA" ? address?.permanent?.addressLine1 : "Shanti Niketan"}
          />
          <Row
            label={t("BPA_ADDRESS_LINE_2")}
            text={checkForNA(address?.permanent?.addressLine2) !== "CS_NA" ? address?.permanent?.addressLine2 : "MG Road,"}
          />
          <Row
            label={t("BPA_DISTRICT")}
            text={checkForNA(address?.permanent?.district?.name) !== "CS_NA" ? address?.permanent?.district?.name : "***********"}
          />
          <Row
            label={t("BPA_CITY_VILLAGE")}
            text={checkForNA(address?.permanent?.city?.name) !== "CS_NA" ? address?.permanent?.city?.name : "Delhi"}
          />
          <Row
            label={t("BPA_STATE")}
            text={checkForNA(address?.permanent?.state?.name) !== "CS_NA" ? address?.permanent?.state?.name : "Laxmi Nagar"}
          />
          <Row
            label={t("BPA_PIN_CODE")}
            text={checkForNA(address?.permanent?.pincode) !== "CS_NA" ? address?.permanent?.pincode : "143002"}
          />
        </StatusTable>

        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginTop: "16px" }}>
          <CardSubHeader style={{ fontSize: "20px" }}>
            {t("BPA_CORRESPONDENCE_ADDRESS")}
          </CardSubHeader>
        </div>
        
        {address?.sameAsPermanent ? (
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
              text={checkForNA(address?.correspondence?.houseNo)}
            />
            <Row
              label={t("BPA_ADDRESS_LINE_1")}
              text={checkForNA(address?.correspondence?.addressLine1)}
            />
            <Row
              label={t("BPA_ADDRESS_LINE_2")}
              text={checkForNA(address?.correspondence?.addressLine2)}
            />
            <Row
              label={t("BPA_DISTRICT")}
              text={checkForNA(address?.correspondence?.district?.name)}
            />
            <Row
              label={t("BPA_CITY_VILLAGE")}
              text={checkForNA(address?.correspondence?.city?.name)}
            />
            <Row
              label={t("BPA_STATE")}
              text={checkForNA(address?.correspondence?.state?.name)}
            />
            <Row
              label={t("BPA_PIN_CODE")}
              text={checkForNA(address?.correspondence?.pincode)}
            />
          </StatusTable>
        )}

        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginTop: "24px" }}>
          <CardSubHeader style={{ fontSize: "24px" }}>
            {t("BPA_LAND_DETAILS")}
          </CardSubHeader>
          <ActionButton jumpTo={`/upyog-ui/citizen/obps/application/land-details`} />
        </div>
        
        <StatusTable>
          <Row
            label={t("BPA_CONSTRUCTION_TYPE")}
            text={checkForNA(land?.constructionType?.name) !== "CS_NA" ? land?.constructionType?.name : "New Construction"}
          />
          <Row
            label={t("BPA_OLD_DAG_NUMBER")}
            text={checkForNA(land?.oldDagNumber) !== "CS_NA" ? land?.oldDagNumber : "237"}
          />
          <Row
            label={t("BPA_NEW_DAG_NUMBER")}
            text={checkForNA(land?.newDagNumber) !== "CS_NA" ? land?.newDagNumber : "300"}
          />
          <Row
            label={t("BPA_OLD_PATTA_NUMBER")}
            text={checkForNA(land?.oldPattaNumber) !== "CS_NA" ? land?.oldPattaNumber : "3333"}
          />
          <Row
            label={t("BPA_NEW_PATTA_NUMBER")}
            text={checkForNA(land?.newPattaNumber) !== "CS_NA" ? land?.newPattaNumber : "8888"}
          />
          <Row
            label={t("BPA_TOTAL_PLOT_AREA")}
            text={land?.totalPlotArea ? `${land.totalPlotArea} sq. ft.` : "400 sq. ft."}
          />
        </StatusTable>

        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginTop: "16px" }}>
          <CardSubHeader style={{ fontSize: "20px" }}>
            {t("BPA_ADJOINING_LAND_OWNERS")}
          </CardSubHeader>
        </div>
        
        <StatusTable>
          <Row
            label={t("BPA_NORTH")}
            text={checkForNA(land?.adjoiningOwners?.north) !== "CS_NA" ? land?.adjoiningOwners?.north : "Atul Kumar"}
          />
          <Row
            label={t("BPA_SOUTH")}
            text={checkForNA(land?.adjoiningOwners?.south) !== "CS_NA" ? land?.adjoiningOwners?.south : "Ram Sharma"}
          />
          <Row
            label={t("BPA_EAST")}
            text={checkForNA(land?.adjoiningOwners?.east) !== "CS_NA" ? land?.adjoiningOwners?.east : "Sourabh Sharma"}
          />
          <Row
            label={t("BPA_WEST")}
            text={checkForNA(land?.adjoiningOwners?.west) !== "CS_NA" ? land?.adjoiningOwners?.west : "Piyush Verma"}
          />
        </StatusTable>

        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginTop: "16px" }}>
          <CardSubHeader style={{ fontSize: "20px" }}>
            {t("BPA_FUTURE_PROVISIONS")}
          </CardSubHeader>
        </div>
        
        <StatusTable>
          <Row
            label={t("BPA_VERTICAL_EXTENSION")}
            text={checkForNA(land?.futureProvisions?.verticalExtension?.name) !== "CS_NA" ? land?.futureProvisions?.verticalExtension?.name : "Yes, 2 Floors"}
          />
          <Row
            label={t("BPA_HORIZONTAL_EXTENSION")}
            text={checkForNA(land?.futureProvisions?.horizontalExtension?.name) !== "CS_NA" ? land?.futureProvisions?.horizontalExtension?.name : "No"}
          />
        </StatusTable>

        <StatusTable style={{ marginTop: "16px" }}>
          <Row
            label={t("BPA_RTP_CATEGORY")}
            text={checkForNA(land?.rtpCategory?.name) !== "CS_NA" ? land?.rtpCategory?.name : "Architect"}
          />
          <Row
            label={t("BPA_REGISTERED_TECHNICAL_PERSON")}
            text={checkForNA(land?.registeredTechnicalPerson?.name) !== "CS_NA" ? land?.registeredTechnicalPerson?.name : "Atul, +91 8967362655, atul@gmail.com"}
          />
          <Row
            label={t("BPA_OCCUPANCY_TYPE")}
            text={checkForNA(land?.occupancyType?.name) !== "CS_NA" ? land?.occupancyType?.name : "Type 1"}
          />
          <Row
            label={t("BPA_TOD_BENEFITS")}
            text={t("CS_YES") + ", " + t("BPA_WITH_TDR")}
          />
          <Row
            label={t("BPA_FORM_36")}
            text={t("BPA_FILE_UPLOADED")}
          />
          <Row
            label={t("BPA_FORM_39")}
            text={t("BPA_FILE_UPLOADED")}
          />
          <Row
            label={t("BPA_TOD_ZONE")}
            text={checkForNA(land?.todZone?.name) !== "CS_NA" ? land?.todZone?.name : "Intense"}
          />
        </StatusTable>

        <div style={{ marginTop: "24px", padding: "16px", border: "1px solid #ccc", borderRadius: "4px" }}>
          <CheckBox
            label={t("BPA_DECLARATION_MESSAGE").replace("{applicantName}", applicant?.applicantName || "Applicant name")}
            onChange={setDeclarationHandler}
            checked={agree}
          />
        </div>

        <SubmitBar 
          label={t("CS_COMMON_SUBMIT")} 
          onSubmit={onSubmit} 
          disabled={!agree} 
          style={{ marginTop: "24px" }}
        />
      </Card>
    </React.Fragment>
  );
};

export default CheckPage;
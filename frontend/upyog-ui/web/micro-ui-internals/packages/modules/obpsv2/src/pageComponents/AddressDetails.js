import React, { useEffect, useState } from "react";
import { FormStep, TextInput, CardLabel, CheckBox, Dropdown, CardHeader } from "@upyog/digit-ui-react-components";
import Timeline from "../components/Timeline";

const AddressDetails = ({ t, config, onSelect, formData }) => {
  // Dropdown options
  const cityOptions = [
    { code: "CITY1", name: "City 1" },
    { code: "CITY2", name: "City 2" },
    { code: "CITY3", name: "City 3" }
  ];

  const stateOptions = [
    { code: "STATE1", name: "State 1" },
    { code: "STATE2", name: "State 2" },
    { code: "STATE3", name: "State 3" }
  ];

  // Permanent Address Fields
  const [permanentHouseNo, setPermanentHouseNo] = useState(formData?.address?.permanent?.houseNo || "");
  const [permanentAddressLine1, setPermanentAddressLine1] = useState(formData?.address?.permanent?.addressLine1 || "");
  const [permanentAddressLine2, setPermanentAddressLine2] = useState(formData?.address?.permanent?.addressLine2 || "");
  const [permanentDistrict, setPermanentDistrict] = useState(formData?.address?.permanent?.district || "");
  const [permanentCity, setPermanentCity] = useState(formData?.address?.permanent?.city || "");
  const [permanentState, setPermanentState] = useState(formData?.address?.permanent?.state || "");
  const [permanentPincode, setPermanentPincode] = useState(formData?.address?.permanent?.pincode || "");

  // Correspondence Address Fields
  const [sameAsPermanent, setSameAsPermanent] = useState(formData?.address?.sameAsPermanent || false);
  const [correspondenceHouseNo, setCorrespondenceHouseNo] = useState(formData?.address?.correspondence?.houseNo || "");
  const [correspondenceAddressLine1, setCorrespondenceAddressLine1] = useState(formData?.address?.correspondence?.addressLine1 || "");
  const [correspondenceAddressLine2, setCorrespondenceAddressLine2] = useState(formData?.address?.correspondence?.addressLine2 || "");
  const [correspondenceDistrict, setCorrespondenceDistrict] = useState(formData?.address?.correspondence?.district || "");
  const [correspondenceCity, setCorrespondenceCity] = useState(formData?.address?.correspondence?.city || "");
  const [correspondenceState, setCorrespondenceState] = useState(formData?.address?.correspondence?.state || "");
  const [correspondencePincode, setCorrespondencePincode] = useState(formData?.address?.correspondence?.pincode || "");

  // Update correspondence address when sameAsPermanent is checked
  useEffect(() => {
    if (sameAsPermanent) {
      setCorrespondenceHouseNo(permanentHouseNo);
      setCorrespondenceAddressLine1(permanentAddressLine1);
      setCorrespondenceAddressLine2(permanentAddressLine2);
      setCorrespondenceDistrict(permanentDistrict);
      setCorrespondenceCity(permanentCity);
      setCorrespondenceState(permanentState);
      setCorrespondencePincode(permanentPincode);
    }
    else {
        // Clear correspondence address when unchecked
        setCorrespondenceHouseNo("");
        setCorrespondenceAddressLine1("");
        setCorrespondenceAddressLine2("");
        setCorrespondenceDistrict("");
        setCorrespondenceCity("");
        setCorrespondenceState("");
        setCorrespondencePincode("");
      }
  }, [sameAsPermanent, permanentHouseNo, permanentAddressLine1, permanentAddressLine2, permanentDistrict, permanentCity, permanentState, permanentPincode]);

  // Go next
  const goNext = () => {
    let addressStep = {
      permanent: {
        houseNo: permanentHouseNo,
        addressLine1: permanentAddressLine1,
        addressLine2: permanentAddressLine2,
        district: permanentDistrict,
        city: permanentCity,
        state: permanentState,
        pincode: permanentPincode
      },
      correspondence: sameAsPermanent ? null : {
        houseNo: correspondenceHouseNo,
        addressLine1: correspondenceAddressLine1,
        addressLine2: correspondenceAddressLine2,
        district: correspondenceDistrict,
        city: correspondenceCity,
        state: correspondenceState,
        pincode: correspondencePincode
      },
      sameAsPermanent
    };

    onSelect(config.key, { ...formData[config.key], ...addressStep });
  };

  const onSkip = () => onSelect();

  return (
    <React.Fragment>
      <Timeline currentStep={2} flow="buildingPlanPermit" />
      <FormStep
        config={config}
        onSelect={goNext}
        onSkip={onSkip}
        t={t}
        isDisabled={
          !permanentHouseNo ||
          !permanentAddressLine1 ||
          !permanentDistrict ||
          !permanentCity ||
          !permanentState ||
          !permanentPincode ||
          (!sameAsPermanent && (
            !correspondenceHouseNo ||
            !correspondenceAddressLine1 ||
            !correspondenceDistrict ||
            !correspondenceCity ||
            !correspondenceState ||
            !correspondencePincode
          ))
        }
      >
        <div>
          {/* Permanent Address Section */}
          <CardHeader>{t("BPA_PERMANENT_ADDRESS")}</CardHeader>
          
          {/* House No */}
          <CardLabel>{`${t("BPA_HOUSE_NO")}`}</CardLabel>
          <TextInput
            t={t}
            type="text"
            name="permanentHouseNo"
            placeholder={t("BPA_ENTER_HOUSE_NO")}
            value={permanentHouseNo}
            onChange={(e) => setPermanentHouseNo(e.target.value)}
            {...{ pattern: "^[a-zA-Z0-9\\s\\-\\/#]+$", title: t("BPA_HOUSE_NO_ERROR_MESSAGE") }}
          />

          {/* Address Line 1 */}
          <CardLabel>{`${t("BPA_ADDRESS_LINE_1")}`} <span className="check-page-link-button">*</span></CardLabel>
          <TextInput
            t={t}
            type="text"
            name="permanentAddressLine1"
            placeholder={t("BPA_ENTER_ADDRESS")}
            value={permanentAddressLine1}
            onChange={(e) => setPermanentAddressLine1(e.target.value)}
            ValidationRequired={true}
            {...{ pattern: "^[a-zA-Z0-9\\s\\-\\/,.#]+$", title: t("BPA_ADDRESS_ERROR_MESSAGE") }}
          />

          {/* Address Line 2 */}
          <CardLabel>{`${t("BPA_ADDRESS_LINE_2")}`}</CardLabel>
          <TextInput
            t={t}
            type="text"
            name="permanentAddressLine2"
            placeholder={t("BPA_ENTER_ADDRESS")}
            value={permanentAddressLine2}
            onChange={(e) => setPermanentAddressLine2(e.target.value)}
            {...{ pattern: "^[a-zA-Z0-9\\s\\-\\/,.#]*$", title: t("BPA_ADDRESS_ERROR_MESSAGE") }}
          />

          {/* District */}
          <CardLabel>{`${t("BPA_DISTRICT")}`} <span className="check-page-link-button">*</span></CardLabel>
          <TextInput
            t={t}
            type="text"
            name="permanentDistrict"
            placeholder={t("BPA_ENTER_LANDMARK")}
            value={permanentDistrict}
            onChange={(e) => setPermanentDistrict(e.target.value)}
            ValidationRequired={true}
            {...{ pattern: "^[a-zA-Z\\s]+$", title: t("BPA_DISTRICT_ERROR_MESSAGE") }}
          />

          {/* City/Village */}
          <CardLabel>{`${t("BPA_CITY_VILLAGE")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={cityOptions}
            selected={permanentCity}
            optionKey="name"
            select={(value) => setPermanentCity(value)}
            placeholder={t("BPA_SELECT_CITY")}
          />

          {/* State */}
          <CardLabel>{`${t("BPA_STATE")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={stateOptions}
            selected={permanentState}
            optionKey="name"
            select={(value) => setPermanentState(value)}
            placeholder={t("BPA_SELECT_STATE")}
          />

          {/* Pincode */}
          <CardLabel>{`${t("BPA_PINCODE")}`} <span className="check-page-link-button">*</span></CardLabel>
          <TextInput
            t={t}
            type="text"
            name="permanentPincode"
            placeholder={t("BPA_ENTER_PINCODE")}
            value={permanentPincode}
            onChange={(e) => setPermanentPincode(e.target.value.replace(/[^0-9]/g, ""))}
            ValidationRequired={true}
            minLength={6}
            maxLength={6}
            {...{ pattern: "[0-9]{6}", title: t("BPA_PINCODE_ERROR_MESSAGE") }}
          />


          {/* Correspondence Address Section */}
          <CardHeader>{t("BPA_CORRESPONDENCE_ADDRESS")}</CardHeader>
          
          {/* Same as Permanent Address Checkbox */}
          <CheckBox
            label={t("BPA_SAME_AS_PERMANENT")}
            value={sameAsPermanent}
            onChange={(e) => setSameAsPermanent(e.target.checked)}
          />

          {/* Correspondence Address Fields - ALWAYS VISIBLE */}
          <div>
            {/* House No */}
            <CardLabel>{`${t("BPA_HOUSE_NO")}`}</CardLabel>
            <TextInput
              t={t}
              type="text"
              name="correspondenceHouseNo"
              placeholder={t("BPA_ENTER_HOUSE_NO")}
              value={correspondenceHouseNo}
              onChange={(e) => setCorrespondenceHouseNo(e.target.value)}
              disabled={sameAsPermanent}
              {...{ pattern: "^[a-zA-Z0-9\\s\\-\\/#]+$", title: t("BPA_HOUSE_NO_ERROR_MESSAGE") }}
            />

            {/* Address Line 1 */}
            <CardLabel>{`${t("BPA_ADDRESS_LINE_1")}`} <span className="check-page-link-button">*</span></CardLabel>
            <TextInput
              t={t}
              type="text"
              name="correspondenceAddressLine1"
              placeholder={t("BPA_ENTER_ADDRESS")}
              value={correspondenceAddressLine1}
              onChange={(e) => setCorrespondenceAddressLine1(e.target.value)}
              ValidationRequired={true}
              disabled={sameAsPermanent}
              {...{ pattern: "^[a-zA-Z0-9\\s\\-\\/,.#]+$", title: t("BPA_ADDRESS_ERROR_MESSAGE") }}
            />

            {/* Address Line 2 */}
            <CardLabel>{`${t("BPA_ADDRESS_LINE_2")}`}</CardLabel>
            <TextInput
              t={t}
              type="text"
              name="correspondenceAddressLine2"
              placeholder={t("BPA_ENTER_ADDRESS")}
              value={correspondenceAddressLine2}
              onChange={(e) => setCorrespondenceAddressLine2(e.target.value)}
              disabled={sameAsPermanent}
              {...{ pattern: "^[a-zA-Z0-9\\s\\-\\/,.#]*$", title: t("BPA_ADDRESS_ERROR_MESSAGE") }}
            />

            {/* District */}
            <CardLabel>{`${t("BPA_DISTRICT")}`} <span className="check-page-link-button">*</span></CardLabel>
            <TextInput
              t={t}
              type="text"
              name="correspondenceDistrict"
              placeholder={t("BPA_ENTER_LANDMARK")}
              value={correspondenceDistrict}
              onChange={(e) => setCorrespondenceDistrict(e.target.value)}
              ValidationRequired={true}
              disabled={sameAsPermanent}
              {...{ pattern: "^[a-zA-Z\\s]+$", title: t("BPA_DISTRICT_ERROR_MESSAGE") }}
            />

            {/* City/Village */}
            <CardLabel>{`${t("BPA_CITY_VILLAGE")}`} <span className="check-page-link-button">*</span></CardLabel>
            <Dropdown
              t={t}
              option={cityOptions}
              selected={correspondenceCity}
              optionKey="name"
              select={(value) => setCorrespondenceCity(value)}
              placeholder={t("BPA_SELECT_CITY")}
              disable={sameAsPermanent}
            />

            {/* State */}
            <CardLabel>{`${t("BPA_STATE")}`} <span className="check-page-link-button">*</span></CardLabel>
            <Dropdown
              t={t}
              option={stateOptions}
              selected={correspondenceState}
              optionKey="name"
              select={(value) => setCorrespondenceState(value)}
              placeholder={t("BPA_SELECT_STATE")}
              disable={sameAsPermanent}
            />

            {/* Pincode */}
            <CardLabel>{`${t("BPA_PINCODE")}`} <span className="check-page-link-button">*</span></CardLabel>
            <TextInput
              t={t}
              type="text"
              name="correspondencePincode"
              placeholder={t("BPA_ENTER_PINCODE")}
              value={correspondencePincode}
              onChange={(e) => setCorrespondencePincode(e.target.value.replace(/[^0-9]/g, ""))}
              ValidationRequired={true}
              minLength={6}
              maxLength={6}
              disabled={sameAsPermanent}
              {...{ pattern: "[0-9]{6}", title: t("BPA_PINCODE_ERROR_MESSAGE") }}
            />
          </div>
        </div>
      </FormStep>
    </React.Fragment>
  );
};

export default AddressDetails;
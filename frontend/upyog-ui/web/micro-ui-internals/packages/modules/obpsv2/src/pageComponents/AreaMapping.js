import React, { useEffect, useState } from "react";
import { FormStep, CardLabel, Dropdown } from "@upyog/digit-ui-react-components";

const AreaMapping = ({ t, config, onSelect, formData }) => {
  // State for all dropdown values
  const [district, setDistrict] = useState(formData?.areaMapping?.district || "");
  const [planningArea, setPlanningArea] = useState(formData?.areaMapping?.planningArea || "");
  const [ppAuthority, setPpAuthority] = useState(formData?.areaMapping?.ppAuthority || "");
  const [bpAuthority, setBpAuthority] = useState(formData?.areaMapping?.bpAuthority || "");
  const [revenueVillage, setRevenueVillage] = useState(formData?.areaMapping?.revenueVillage || "");
  const [mouza, setMouza] = useState(formData?.areaMapping?.mouza || "");
  const [ward, setWard] = useState(formData?.areaMapping?.ward || "");
  
  // State for dropdown options
  const [districts, setDistricts] = useState([]);
  const [planningAreas, setPlanningAreas] = useState([]);
  const [ppAuthorities, setPpAuthorities] = useState([]);
  const [bpAuthorities, setBpAuthorities] = useState([]);
  const [revenueVillages, setRevenueVillages] = useState([]);
  const [mouzas, setMouzas] = useState([]);
  const [wards, setWards] = useState([]);

  // Fetch data from MDMS
  const { data: areaMappingData, isLoading } = Digit.Hooks.useEnabledMDMS(
    "as", 
    "BPA", 
    [
      { name: "districts" }, 
      { name: "planningAreas" }, 
      { name: "ppAuthorities" }, 
      { name: "bpAuthorities" }, 
      { name: "revenueVillages" }, 
      { name: "mouzas" }, 
      { name: "wards" }
    ],
    {
      select: (data) => {
        const formattedData = data?.BPA || {};
        return formattedData;
      },
    }
  );

  // Initialize districts from MDMS data
  useEffect(() => {
    if (areaMappingData?.districts) {
      const formattedDistricts = areaMappingData.districts.map((district) => ({
        code: district.districtCode,
        name: district.districtName,
        i18nKey: district.districtName,
      }));
      setDistricts(formattedDistricts);
    }
  }, [areaMappingData]);

  // Update planning areas when district changes
  useEffect(() => {
    if (district && areaMappingData?.planningAreas) {
      // Filter planning areas based on selected district
      const filteredPlanningAreas = areaMappingData.planningAreas
      .filter(area => area.districtCode === district?.code)
      .map(area => ({
        code: area.planningAreaCode,
        name: area.planningAreaName,
        i18nKey: area.planningAreaCode,
      }));
      setPlanningAreas(filteredPlanningAreas);
      
      // Reset dependent fields
      setPlanningArea("");
      setPpAuthority("");
      setBpAuthority("");
      setRevenueVillage("");
      setMouza("");
      setWard("");
    }
  }, [district, areaMappingData]);

  // Update PP authorities when planning area changes
  useEffect(() => {
    if (planningArea && areaMappingData?.ppAuthorities) {
      // Filter PP authorities based on selected planning area
      const filteredPpAuthorities = areaMappingData.ppAuthorities.filter(
        authority => authority.planningAreaCode === planningArea
      );
      setPpAuthorities(filteredPpAuthorities);
      
      // Reset dependent fields
      setPpAuthority("");
      setBpAuthority("");
      setRevenueVillage("");
      setMouza("");
      setWard("");
    }
  }, [planningArea, areaMappingData]);

  // Update BP authorities when PP authority changes
  useEffect(() => {
    if (ppAuthority && areaMappingData?.bpAuthorities) {
      // Filter BP authorities based on selected PP authority
      const filteredBpAuthorities = areaMappingData.bpAuthorities.filter(
        authority => authority.ppAuthorityCode === ppAuthority
      );
      setBpAuthorities(filteredBpAuthorities);
      
      // Reset dependent fields
      setBpAuthority("");
      setRevenueVillage("");
      setMouza("");
      setWard("");
    }
  }, [ppAuthority, areaMappingData]);

  // Update revenue villages when BP authority changes
  useEffect(() => {
    if (bpAuthority && areaMappingData?.revenueVillages) {
      // Filter revenue villages based on selected BP authority
      const filteredRevenueVillages = areaMappingData.revenueVillages.filter(
        village => village.bpAuthorityCode === bpAuthority
      );
      setRevenueVillages(filteredRevenueVillages);
      
      // Reset dependent fields
      setRevenueVillage("");
      setMouza("");
      setWard("");
    }
  }, [bpAuthority, areaMappingData]);

  // Update mouzas when revenue village changes
  useEffect(() => {
    if (revenueVillage && areaMappingData?.mouzas) {
      // Filter mouzas based on selected revenue village
      const filteredMouzas = areaMappingData.mouzas.filter(
        mouza => mouza.revenueVillageCode === revenueVillage
      );
      setMouzas(filteredMouzas);
      
      // Reset dependent field
      setMouza("");
      setWard("");
    }
  }, [revenueVillage, areaMappingData]);

  // Update wards when mouza changes
  useEffect(() => {
    if (mouza && areaMappingData?.wards) {
      // Filter wards based on selected mouza
      const filteredWards = areaMappingData.wards.filter(
        ward => ward.mouzaCode === mouza
      );
      setWards(filteredWards);
      
      // Reset dependent field
      setWard("");
    }
  }, [mouza, areaMappingData]);

  // Go next
  const goNext = () => {
    let areaMappingStep = {
      district,
      planningArea,
      ppAuthority,
      bpAuthority,
      revenueVillage,
      mouza,
      ward
    };

    onSelect(config.key, { ...formData[config.key], ...areaMappingStep });
  };

  const onSkip = () => onSelect();

  return (
    <React.Fragment>
      <FormStep
        config={config}
        onSelect={goNext}
        onSkip={onSkip}
        t={t}
        isDisabled={
          !district ||
          !planningArea ||
          !ppAuthority ||
          !bpAuthority ||
          !revenueVillage ||
          !mouza ||
          !ward
        }
      >
        <div>
          {/* District */}
          <CardLabel>{`${t("DISTRICT")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={districts}
            optionKey="name"
            id="district"
            selected={district}
            select={(value) => setDistrict(value)}
            placeholder={isLoading ? "Loading districts..." : "Select District"}
          />

          {/* Planning Area */}
          <CardLabel>{`${t("PLANNING_AREA")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={planningAreas}
            optionKey="name"
            id="planningArea"
            selected={planningArea}
            select={(value) => setPlanningArea(value)}
            placeholder={!district ? "Select district first" : "Select Planning Area"}
            isDisabled={!district}
          />

          {/* PP Authority */}
          <CardLabel>{`${t("PP_AUTHORITY")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={ppAuthorities}
            optionKey="name"
            id="ppAuthority"
            selected={ppAuthority}
            select={(value) => setPpAuthority(value)}
            placeholder={!planningArea ? "Select planning area first" : "Select PP Authority"}
            isDisabled={!planningArea}
          />

          {/* BP Authority */}
          <CardLabel>{`${t("BP_AUTHORITY")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={bpAuthorities}
            optionKey="name"
            id="bpAuthority"
            selected={bpAuthority}
            select={(value) => setBpAuthority(value)}
            placeholder={!ppAuthority ? "Select PP authority first" : "Select BP Authority"}
            isDisabled={!ppAuthority}
          />

          {/* Revenue Village */}
          <CardLabel>{`${t("REVENUE_VILLAGE")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={revenueVillages}
            optionKey="name"
            id="revenueVillage"
            selected={revenueVillage}
            select={(value) => setRevenueVillage(value)}
            placeholder={!bpAuthority ? "Select BP authority first" : "Select Revenue Village"}
            isDisabled={!bpAuthority}
          />

          {/* Mouza */}
          <CardLabel>{`${t("MOUZA")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={mouzas}
            optionKey="name"
            id="mouza"
            selected={mouza}
            select={(value) => setMouza(value)}
            placeholder={!revenueVillage ? "Select revenue village first" : "Select Mouza"}
            isDisabled={!revenueVillage}
          />

          {/* Ward */}
          <CardLabel>{`${t("WARD")}`} <span className="check-page-link-button">*</span></CardLabel>
          <Dropdown
            t={t}
            option={wards}
            optionKey="name"
            id="ward"
            selected={ward}
            select={(value) => setWard(value)}
            placeholder={!mouza ? "Select mouza first" : "Select Ward"}
            isDisabled={!mouza}
          />
        </div>
      </FormStep>
    </React.Fragment>
  );
};

export default AreaMapping;
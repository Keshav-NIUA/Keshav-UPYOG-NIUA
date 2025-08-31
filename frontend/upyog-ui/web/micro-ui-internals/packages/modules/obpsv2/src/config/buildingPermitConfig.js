export const buildingPermitConfig = [
    {
      head: "ES_TITILE_APPLICANT_DETAILS",
      body: [
        {
          route: "applicant-details",
          component: "ApplicantDetails",
          withoutLabel: true,
          key: "applicant",
          type: "component",
          nextStep: "address-details",
          hideInEmployee: true,
          isMandatory: true,
          texts: {
            header:"APPLICANT_PERSONAL_DETAILS",
            submitBarLabel: "CS_COMMON_NEXT",
          },
        },
        {
          route: "address-details",
          component: "AddressDetails",
          withoutLabel: true,
          key: "address",
          type: "component",
          nextStep: "land-details",
          hideInEmployee: true,
          isMandatory: true,
          texts: {
            header:"APPLICANT_ADDRESS_DETAILS",
            submitBarLabel: "CS_COMMON_NEXT",
          },
        },
        {
            route: "land-details",
            component: "LandDetails",
            withoutLabel: true,
            key: "land",
            type: "component",
            nextStep: null,
            hideInEmployee: true,
            isMandatory: true,
            texts: {
              header:"APPLICANT_LAND_DETAILS",
              submitBarLabel: "CS_COMMON_NEXT",
            },
        }
      ],
    },
  ];
  
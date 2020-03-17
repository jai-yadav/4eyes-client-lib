*** Settings ***
Library           ITAS    0
Resource          ../../common.html
Resource          ../../funtions.html

*** Test Cases ***    Description                                                ExpectedResultMessage                            EntitlementFeature(M)    EntitlementNamespace(M)    EntitlementResource(M)    EntityCategory(M)    EntityDescription(M)    EntityName(M)    RequestedBy(M)    Comment       ExternalId       RequestGroup       RequestNamespace       RequestReason       RequestReasonDescription       UserData
Test Submit New Pending Request
                      [Setup]                                                    Setup
                      [Template]                                                 Submit New Request And Verify
                      Case 1 : All Mandatory fields are supplied                 OK                                               EntitlementFeature_1     EntitlementNamespace_1     EntitlementResource_1     EntityCategory_1     EntityDescription_1     EntityName_1     RequestedBy_1     Comment_1
                      Case 2 : EntitlementFeature is not supplied                EntitlementFeature : Value can not be empty      \                        EntitlementNamespace_2     EntitlementResource_2     EntityCategory_2     EntityDescription_2     EntityName_2     RequestedBy_2
                      Case 3 : EntitlementNamespace is not supplied              EntitlementNamespace : Value can not be empty    EntitlementFeature_3     \                          EntitlementResource_3     EntityCategory_3     EntityDescription_3     EntityName_3     RequestedBy_3
                      Case 4 : EntitlementResource is not supplied               EntitlementResource : Value can not be empty     EntitlementFeature_4     EntitlementNamespace_4     \                         EntityCategory_4     EntityDescription_4     EntityName_4     RequestedBy_4
                      Case 5 : EntityCategory is not supplied                    EntityCategory : Value can not be empty          EntitlementFeature_5     EntitlementNamespace_5     EntitlementResource_5     \                    EntityDescription_5     EntityName_5     RequestedBy_5
                      Case 6 : EntityDescription is not supplied                 EntityDescription : Value can not be empty       EntitlementFeature_6     EntitlementNamespace_6     EntitlementResource_6     EntityCategory_6     \                       EntityName_6     RequestedBy_6
                      Case 7 : EntityName is not supplied                        EntityName : Value can not be empty              EntitlementFeature_7     EntitlementNamespace_7     EntitlementResource_7     EntityCategory_7     EntityDescription_7     \                RequestedBy_7
                      Case 8 : RequestedBy is not supplied                       RequestedBy : Value can not be empty             EntitlementFeature_8     EntitlementNamespace_8     EntitlementResource_8     EntityCategory_8     EntityDescription_8     EntityName_8     ${EMPTY}
                      Case 9 : All Mandatory and Optional fields are supplied    OK                                               EntitlementFeature_10    EntitlementNamespace_10    EntitlementResource_10    EntityCategory_10    EntityDescription_10    EntityName_10    RequestedBy_10    Comment_10    ExternalId_10    RequestGroup_10    RequestNamespace_10    RequestReason_10    RequestReasonDescription_10    UserData_10
                      [Teardown]                                                 Teardown

*** Keywords ***
Setup
    Clear Old Requests 4ES
    ${qId}=    Open Queue Server_SearchFourEyesRequest
    Set Test Variable    ${qId}

Teardown
    Ion Queue Close    ${qId}

Submit New Request And Verify
    [Arguments]    ${Desc}    @{argList}
    ${requestId}=    Submit Four Eye Request DO    @{argList}
    Run Keyword If    '${ExpectedResult}'=='OK'    Wait and Verify Records On Queue    ${qId}    ${requestId}    @{ConsolidatedArgs}

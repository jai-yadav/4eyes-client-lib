*** Settings ***
Library           ITAS    0
Resource          ../../common.html
Resource          ../../funtions.html

*** Test Cases ***
Notification Recieved When Data owner Starts
    ${requestId_1}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    @{expectationList_1}=    Set Variable    @{ConsolidatedArgs}
    ${requestId_2}=    Submit Four Eye Request DO    OK    EntitlementFeature_B    EntitlementNamespace_B    EntitlementResource_B    EntityCategory_B
    ...    EntityDescription_B    EntityName_B    RequestedBy_B    Comment_B    ExternalId_B    RequestGroup_B
    ...    RequestNamespace_B    RequestReason_B    RequestReasonDescription_B    UserData_B
    @{expectationList_2}=    Set Variable    @{ConsolidatedArgs}
    Wait Until Keyword Succeeds    3 times    5s    Ion Remove Connection    ${router}    ${4E_Data_Owner}
    Wait Until Keyword Succeeds    3 times    5s    Ion Remove Connection    ${4E_Data_Owner}    ${router}
    Wait Until Keyword Succeeds    3 times    5s    Ion Create Connection    ${router}    ${4E_Data_Owner}    inout
    Ion Wait For Components Running    ${4E_Data_Owner}    180s
    ${qId}=    Open Queue DataOwner_Search4EyesRequestRecieved
    Set Test Variable    ${qId}
    Wait and Verify Records On Queue    ${qId}    ${requestId_1}    @{expectationList_1}
    Wait and Verify Records On Queue    ${qId}    ${requestId_2}    @{expectationList_2}
    [Teardown]    Teardown

Notification Recieved When Server Becomes Available
    ${requestId_1}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    @{expectationList_1}=    Set Variable    @{ConsolidatedArgs}
    ${requestId_2}=    Submit Four Eye Request DO    OK    EntitlementFeature_B    EntitlementNamespace_B    EntitlementResource_B    EntityCategory_B
    ...    EntityDescription_B    EntityName_B    RequestedBy_B    Comment_B    ExternalId_B    RequestGroup_B
    ...    RequestNamespace_B    RequestReason_B    RequestReasonDescription_B    UserData_B
    @{expectationList_2}=    Set Variable    @{ConsolidatedArgs}
    Remove Four Eyes Server Connection
    Clear Old Notifications DO
    ${qId}=    Open Queue DataOwner_Search4EyesRequestRecieved
    Set Test Variable    ${qId}
    Ion Queue Verify Record Does Not Exist    ${qId}    Id    ${requestId_1}
    Ion Queue Verify Record Does Not Exist    ${qId}    Id    ${requestId_2}
    Create Four Eyes Server Connection
    Wait and Verify Records On Queue    ${qId}    ${requestId_1}    @{expectationList_1}
    Wait and Verify Records On Queue    ${qId}    ${requestId_2}    @{expectationList_2}
    [Teardown]    Teardown

*** Keywords ***
Teardown
    Ion Queue Close    ${qId}

*** Settings ***
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ITAS    0
Resource          ../../common.html
Resource          ../../funtions.html

*** Test Cases ***
Request Creation Notification Recieved By Data Owner
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    @{expectationList}=    Set Variable    @{ConsolidatedArgs}
    Append To List    ${expectationList}    Status    Pending
    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectationList}

Request Approval Notification Recieved By Data Owner
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    @{expectationList}=    Create List    Status    Approved    ActionComment    Comment Approve Request
    Approve Pending Request 4ES    ${requestId}    Comment Approve Request
    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectationList}

Request Rejection Notification Recieved By Data Owner
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    @{expectationList}=    Create List    Status    Rejected    ActionComment    Comment Reject Request
    Reject Pending Request 4ES    ${requestId}    Comment Reject Request
    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectationList}

*** Keywords ***
Suite Teardown
    Ion Queue Close    ${qId}

Suite Setup
    ${qId}=    Open Queue DataOwner_Search4EyesRequestRecieved
    Set Suite Variable    ${qId}

*** Settings ***
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ITAS    0
Resource          ../../common.html
Resource          ../../funtions.html

*** Test Cases ***
Approval Processed
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    Process Or Deny Changes DO    Process
    @{expectationList}=    Create List    Status    Done    ActionComment    Processed Approval
    Approve Pending Request 4ES    ${requestId}    Comment Approve Request
    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectationList}

Approval Denied
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    Process Or Deny Changes DO    Deny
    @{expectationList}=    Create List    Status    Done    ActionComment    Denied Approval
    Approve Pending Request 4ES    ${requestId}    Comment Approve Request
    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectationList}

Rejection Processed
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    Process Or Deny Changes DO    Process
    @{expectationList}=    Create List    Status    Done    ActionComment    Processed Rejection
    Reject Pending Request 4ES    ${requestId}    Comment Reject Request
    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectationList}

Rejection Denial is Not Allowd
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A
    Process Or Deny Changes DO    Deny
    @{expectationList}=    Create List    Status    Done    ActionComment    Denied Rejection
    Reject Pending Request 4ES    ${requestId}    Comment Reject Request
    ${passed}=    Run Keyword And Return Status    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectationList}
    Should Be Equal As Strings    ${passed}    False

*** Keywords ***
Suite Setup
    Enable Or Disable Acknowledgement DO    Enable
    ${qId}=    Open Queue Server_SearchFourEyesRequest
    Set Suite Variable    ${qId}

SuiteTeardown
    Enable Or Disable Acknowledgement DO    Disable
    Ion Queue Close    ${qId}

*** Settings ***
Suite Setup       Suite Setup
Suite Teardown    Suite Teardown
Library           ITAS    0
Resource          ../../common.html
Resource          ../../funtions.html

*** Test Cases ***
Withdraw Pending Request
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A
    Withdraw Pending Request DO    ${requestId}
    Wait and Verify Records On Queue    ${qid}    ${requestId}    Status    Done    PreviousStatus    Pending

Withdraw Pending Request With Optional Comment
    ${requestId}=    Submit Four Eye Request DO    OK    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A
    Withdraw Pending Request DO    ${requestId}    Cancelled_By_AT
    Wait and Verify Records On Queue    ${qid}    ${requestId}    Status    Done    PreviousStatus    Pending
    ...    ActionComment    Cancelled_By_AT

*** Keywords ***
Suite Setup
    Clear Old Requests 4ES
    ${qId}=    Open Queue Server_SearchFourEyesRequest
    Set Suite variable    ${qId}

Suite Teardown
    Ion Queue Close    ${qId}

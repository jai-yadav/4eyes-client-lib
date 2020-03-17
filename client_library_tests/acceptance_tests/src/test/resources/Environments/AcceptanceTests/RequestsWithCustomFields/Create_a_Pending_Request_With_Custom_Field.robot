*** Settings ***
Library           ITAS    0
Resource          ../../common.html
Resource          ../../funtions.html

*** Test Cases ***    ExpectedResult                                                                           ExpectedJSON                                                                                                                                                         Name               Value                  DisplayName      Description                   Type
Custom Field Creation
                      [Setup]                                                                                  Setup
                      [Template]                                                                               Submit Request with custom field
                      OK                                                                                       {"customFields":[{"name":"Firm","value":"EUROPE"}]}                                                                                                                  Firm               EUROPE
                      OK                                                                                       {"customFields":[{"name":"Firm","value":"GREEN","description":"Firm Code","type":"STR"}]}                                                                            Firm               GREEN                  \                Firm Code                     STR
                      OK                                                                                       {"customFields":[{"name":"BusinessDate","displayName":"Business Date","value":"2011-06-22","description":"Run Date of Firm","type":"DATE"}]}                         BusinessDate       2011-06-22             Business Date    Run Date of Firm              DATE
                      OK                                                                                       {"customFields":[{"name":"Time","value":"10:55:22","description":"Current Time","type":"TIME"}]}                                                                     Time               10:55:22               \                Current Time                  TIME
                      OK                                                                                       {"customFields":[{"name":"LastUpdated","displayName":"Last Updated","value":"2011-06-22T10:55:12","description":"Last updated date and time","type":"DATETIME"}]}    LastUpdated        2011-06-22T10:55:12    Last Updated     Last updated date and time    DATETIME
                      Value of Custom field is not a valid DateTime. Name: InvalidDateTime, Value: 20110622    \                                                                                                                                                                    InvalidDateTime    20110622               \                \                             DATETIME
                      Value of Custom field is not a valid Date. Name: InvalidDate, Value: 20110622            \                                                                                                                                                                    InvalidDate        20110622               \                \                             DATE
                      Value of Custom field is not a valid Time. Name: InvalidTime, Value: 10:55:xx            \                                                                                                                                                                    InvalidTime        10:55:xx               \                \                             TIME
                      Value of Custom field is not a valid integer. Name: InvalidInt, Value: 10z               \                                                                                                                                                                    InvalidInt         10z                    \                \                             INT
                      Value of Custom field is not a valid Real Value. Name: InvalidReal, Value: 10.x          \                                                                                                                                                                    InvalidReal        10.x                   \                \                             REAL
                      [Teardown]                                                                               Teardown

*** Keywords ***
Submit Request with custom field
    [Arguments]    ${ExpectedResult}    ${expectedJSON}    ${name}    ${value}    ${displayName}=${null}    ${description}=${null}
    ...    ${type}=${null}
    ${requestId}=    Submit Four Eye Request DO    ${ExpectedResult}    EntitlementFeature_A    EntitlementNamespace_A    EntitlementResource_A    EntityCategory_A
    ...    EntityDescription_A    EntityName_A    RequestedBy_A    Comment_A    ExternalId_A    RequestGroup_A
    ...    RequestNamespace_A    RequestReason_A    RequestReasonDescription_A    UserData_A    ${name}    ${displayName}
    ...    ${description}    ${type}    ${value}
    @{expectation}=    Create List
    Append to list    ${expectation}    CustomFields    ${expectedJSON}
    Run Keyword If    '${ExpectedResult}'=='OK'    Wait and Verify Records On Queue    ${qid}    ${requestId}    @{expectation}

Setup
    Clear Old Requests 4ES
    ${qId}=    Open Queue Server_SearchFourEyesRequest
    Set Test Variable    ${qId}

Teardown
    Ion Queue Close    ${qId}

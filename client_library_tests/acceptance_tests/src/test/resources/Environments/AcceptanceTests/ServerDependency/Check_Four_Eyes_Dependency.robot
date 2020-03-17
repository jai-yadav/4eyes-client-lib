*** Settings ***
Library           ITAS    0
Resource          ../../common.html
Library           Dialogs
Resource          ../../funtions.html

*** Test Cases ***
Four Eyes Server Platform Availability
    [Setup]    Remove Four Eyes Server Connection
    When Create Four Eyes Server Connection
    Then Ion Bus Record Verify Fields    ANY.SERVICE_DICTIONARY.mock_4eyes_data_owner.DataOwnerService    Id==DataOwnerService    Active==1
    When Remove Four Eyes Server Connection
    Then Ion Bus Record Verify Fields    ANY.SERVICE_DICTIONARY.mock_4eyes_data_owner.DataOwnerService    Id==DataOwnerService    Active==0
    [Teardown]    Create Four Eyes Server Connection

Four Eyes Submitter Actions Bus Serivce Dependency
    When Set Four Eyes Submitter Actions Service Status    0
    Then Ion Bus Record Verify Fields    ANY.SERVICE_DICTIONARY.mock_4eyes_data_owner.DataOwnerService    Id==DataOwnerService    Active==0
    And Ion Bus Record Verify Fields    ANY.SERVICE_DICTIONARY.mock_4eyes_server.4EyesSubmitterActions    Id==4EyesSubmitterActions    Active==0
    When Set Four Eyes Submitter Actions Service Status    1
    Then Ion Bus Record Verify Fields    ANY.SERVICE_DICTIONARY.mock_4eyes_data_owner.DataOwnerService    Id==DataOwnerService    Active==1
    And Ion Bus Record Verify Fields    ANY.SERVICE_DICTIONARY.mock_4eyes_server.4EyesSubmitterActions    Id==4EyesSubmitterActions    Active==1
    [Teardown]    Set Four Eyes Submitter Actions Service Status

*** Keywords ***
Set Four Eyes Submitter Actions Service Status
    [Arguments]    ${status}=1
    ${functionName}=    Set Variable If    ${status}==1    ActiveSubmitterActionsService    DeactiveSubmitterActionsService
    Ion Function Define    ${Active_Server_Source}    ${functionName}
    Ion Function Verify Like Result    0:OK
    ${fResObj}=    Ion Function Call

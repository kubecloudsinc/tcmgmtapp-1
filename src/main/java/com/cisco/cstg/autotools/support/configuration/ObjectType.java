package com.cisco.cstg.autotools.support.configuration;

import com.cisco.cstg.autotools.support.enumeration.Namespace;
import com.cisco.cstg.autotools.support.enumeration.NamespaceElement;

/**
 * An enumeration of the objects that the Factory is able to build.
 * The values correspond to bean definitions in the configuration file.
 */
public class ObjectType implements NamespaceElement {
    /**
     * The namespace for the eumeration.
     */
    private static Namespace namespace = new Namespace();

    /**
     * The unique string value corresponding to each constant member of the
     * enumeration.
     */
    private String key = null;

    /**
     * Obtains a copy of the namespace object for the enumeration. This allows
     * access to the complete set of constants defined in the enumeration.
     * 
     * @return A copy of the enumeration's namespace.
     */
    public static Namespace getNamespace() {
        return namespace.copy();
    }

    /**
     * Private constructor for constant members of the enumeration.
     * 
     * @param key
     *        The unique key value to associate with the constant member.
     */
    private ObjectType(String key) {
        this.key = key;
        namespace.addElement(this);
    }

    /*
     * @see gov.g5.support.enumeration.NamespaceElement#getKey()
     */
    public String getKey() {
        return this.key;
    }
    /**
     * Obtains the key value corresponding to a constant object.
     * 
     * @return Its key value.
     */
    public String toString() {
        return getKey();
    }
    
    ///////////////////////////////////
    // Start of Constant Definitions //
    ///////////////////////////////////
    
    public static final ObjectType CONFIGURATION = new ObjectType("configuration");
    public static final ObjectType ENVIRONMENT = new ObjectType("environment");
    
    //Removed LOG definition to satisfy possible portlet unavailable when trying to instantiate a SystemLog
    //object using ObjectFactory.  Should now use 	return new SystemLog();
    //public static final ObjectType LOG = new ObjectType("log");
    
    public static final ObjectType SCHEDULER = new ObjectType("Scheduler");
    public static final ObjectType METRIC_CYCLER = new ObjectType("MetricsCycler");
    public static final ObjectType ACTION_RESULT = new ObjectType("ActionResult");
    public static final ObjectType TIME_CAPSULE = new ObjectType("TimeCapsule"); 
    
    public static final ObjectType REFUND_BANK_ACCOUNT_MANAGER = new ObjectType("RefundBankAccountManager");
    public static final ObjectType REFUND_BANK_ACCOUNT_DATA_BROKER = new ObjectType("RefundBankAccountDataBroker");
    public static final ObjectType REFUND_ACTIVITY_TRACKER = new ObjectType("RefundActivityTracker");
    public static final ObjectType OFFSET_SCHEDULE_MANAGER = new ObjectType("OffsetScheduleManager");
    public static final ObjectType OFFSET_SCHEDULE_DATA_BROKER = new ObjectType("OffsetScheduleDataBroker");
    public static final ObjectType SUBPROGRAM_MANAGER = new ObjectType("SubProgramManager");
    public static final ObjectType SUBPROGRAM_DATA_BROKER = new ObjectType("SubprogramDataBroker");
    public static final ObjectType CFDA_MANAGER = new ObjectType("CfdaManager");
    public static final ObjectType CFDA_MANAGER_STUB = new ObjectType("CfdaManagerStub");
    public static final ObjectType EMAIL_SENDER = new ObjectType("EmailSender"); 
	public static final ObjectType REVIEWER_DATA_BROKER = new ObjectType("ReviewerDataBroker");
	public static final ObjectType CODE_VALUE_MANAGER = new ObjectType("CodeValueManager");
	public static final ObjectType CODE_VALUE_DATA_BROKER = new ObjectType("CodeValueDataBroker");
	
	public static final ObjectType GRANT_SCHEDULE_MANAGER = new ObjectType("GrantScheduleManager");
	public static final ObjectType SCHEDULE_TEMPLATE_MANAGER = new ObjectType("ScheduleTemplateManager");
	
	public static final ObjectType CLIENT_MANAGER = new ObjectType("ClientManager");
	public static final ObjectType REPORTS_MANAGER = new ObjectType("ReportsManager");
	public static final ObjectType CFDA_DATA_BROKER = new ObjectType("CFDADataBroker");
	public static final ObjectType GET_ACTION_TYPES_ACTION = new ObjectType("GetActionTypesAction");
	public static final ObjectType POPULATE_CFDA_AGENCY_DESIGNATOR_CODES = new ObjectType("PopulateCFDAAgencyDesignatorCodes");
	public static final ObjectType PROCESS_SERVER_INTEGRATOR = new ObjectType ("CfdaInfoRequestWkFlowIntegrator");
	public static final ObjectType GET_REVIEW_TIERS_ACTION = new ObjectType("GetReviewTiersAction");
	public static final ObjectType GET_GRANT_TEAM_ACTION = new ObjectType("GetGrantTeamAction");
	public static final ObjectType GET_SECONDARY_CONTACTS_ACTION = new ObjectType("GetSecondaryContactsAction");
	public static final ObjectType GET_CONTACTS_ACTION = new ObjectType("GetContactsAction");
	public static final ObjectType VALIDATE_PLANNED_AWARDS_ACTION = new ObjectType("ValidatePlannedAwardsAction");
	public static final ObjectType VALIDATE_LOCK_UNLOCK_ACTION = new ObjectType("ValidateLockUnlockAction");
	public static final ObjectType PERSON_MANAGER = new ObjectType("PersonManager");
	public static final ObjectType PEOPLE_DATA_BROKER = new ObjectType("PeopleDataBroker");    
	public static final ObjectType GRANT_TEAM_MANAGER = new ObjectType("GrantTeamManager");
    public static final ObjectType GRANT_TEAM_DATA_BROKER = new ObjectType("GrantTeamDataBroker");
    public static final ObjectType PROGRAM_OFFICE_MANAGER = new ObjectType("ProgramOfficeManager");
    public static final ObjectType PROGRAM_OFFICE_DATA_BROKER = new ObjectType("ProgramOfficeDataBroker");
    public static final ObjectType GRANT_SCHEDULE = new ObjectType("GrantSchedule");
    public static final ObjectType ADD_MEMBER_SEARCH_ACTION = new ObjectType("AddMemberSearchAction");
    public static final ObjectType CREATE_GRANT_TEAM_ACTION = new ObjectType("CreateGrantTeamAction");
    public static final ObjectType DELETE_GRANT_TEAM_ACTION = new ObjectType("DeleteGrantTeamAction");
    public static final ObjectType GET_GRANT_TEAM_DETAILS_ACTION = new ObjectType("GetGrantTeamDetailsAction");
    public static final ObjectType GET_MEMBER_INFO_ACTION = new ObjectType("GetMemberInfoAction");
    public static final ObjectType MODIFY_GRANT_TEAM_ACTION = new ObjectType("ModifyGrantTeamAction");
    public static final ObjectType GRANT_TEAM_POPULATE_PO_ACTION = new ObjectType("GrantTeamPopulatePoAction");
    public static final ObjectType SEARCH_GRANT_TEAM_ACTION = new ObjectType("SearchGrantTeamAction");
    public static final ObjectType VALIDATE_PO_ACTION = new ObjectType("ValidatePoAction");
    public static final ObjectType VALIDATE_TEAM_ACTION = new ObjectType("ValidateTeamAction");
    public static final ObjectType VERIFY_REMOVE_SELECTION_ACTION = new ObjectType("VerifyRemoveSelectionAction");
    public static final ObjectType GRANT_TEAM_VERIFY_SELECTION_ACTION = new ObjectType("GrantTeamVerifySelectionAction");
    public static final ObjectType POPULATE_ADDRESS_LISTS_ACTION = new ObjectType("PopulateAddressListsAction");
    public static final ObjectType PEOPLE_POPULATE_PO_ACTION = new ObjectType("PeoplePopulatePoAction");
    public static final ObjectType POPULATE_STATUS_ACTION = new ObjectType("PopulateStatusAction");
    public static final ObjectType POPULATE_TYPE_ACTION = new ObjectType("PopulateTypeAction");
    public static final ObjectType SEARCH_PEOPLE_ACTION = new ObjectType("SearchPeopleAction");
    public static final ObjectType SUBMIT_ACTIVATE_ACTION = new ObjectType("SubmitActivateAction");
    public static final ObjectType SUBMIT_CREATE_ACTION = new ObjectType("SubmitCreateAction");
    public static final ObjectType SUBMIT_MODIFY_ACTION = new ObjectType("SubmitModifyAction");
    public static final ObjectType VALIDATE_ACTIVATE_ACTION = new ObjectType("ValidateActivateAction");
    public static final ObjectType VALIDATE_PEOPLE_ACTION = new ObjectType("ValidatePeopleAction");
    public static final ObjectType PEOPLE_VERIFY_SELECTION_ACTION = new ObjectType("PeopleVerifySelectionAction");
    public static final ObjectType CFDA_PROCESS_SERVER_INTEGRATOR = new ObjectType("CfdaProcessServerIntegrator");
    public static final ObjectType AUTOMATED_EMAIL_NOTIFIER = new ObjectType("AutomatedEmailNotifier");
    public static final ObjectType GET_NON_COMPETING_REPORTS_ACTION = new ObjectType("GetNonCompetingReportsAction");
    public static final ObjectType VALIDATE_ACCS_DATA_STRING = new ObjectType("ValidateACCSDataString");
    public static final ObjectType UPDATE_STATUS_CHANGE_ACTION = new ObjectType("UpdateStatusChangeAction");
    public static final ObjectType VALIDATE_STATUS_CHANGE_ACTION = new ObjectType("ValidateStatusChangeAction");
    public static final ObjectType ROLLOVER_ACCS_ACTION = new ObjectType("RolloverACCSAction");
    public static final ObjectType LOCK_UNLOCK_GRANT_SCHEDULE_ACTION = new ObjectType("LockUnlockGrantScheduleAction");
    public static final ObjectType LOCK_UNLOCK_SEARCH_GRANT_SCHEDULES_ACTION = new ObjectType("LockUnlockSearchGrantSchedulesAction");
    public static final ObjectType GET_PRIMARY_CONTACTS_ACTION = new ObjectType("GetPrimaryContactsAction");
    public static final ObjectType SET_USER_PERMISSION_ACTION = new ObjectType("SetUserPermissionAction");
    public static final ObjectType REVIEW_FORM_MANAGER = new ObjectType("ReviewFormManager");
    public static final ObjectType REVIEW_FORM_DATA_BROKER = new ObjectType("ReviewFormDataBroker");
    public static final ObjectType GRANT_SCHEDULE_DATA_BROKER = new ObjectType("GrantScheduleDataBroker");
    
    /**
     *  Definitions pertaining to the Select Reviewers and Establish Panel Structure functionality
     */
    public static final ObjectType PANEL_STRUCTURE_MANAGER = new ObjectType("EstablishPanelStructureManager");
    public static final ObjectType PANEL_STRUCTURE_DATA_BROKER = new ObjectType("EstablishPanelStructureDataBroker");
    public static final ObjectType MAINTAIN_POOL_MANAGER = new ObjectType("MaintainPoolOfReviewersManager");
    public static final ObjectType SELECT_REVIEWERS_MANAGER = new ObjectType("SelectReviewersManager");
    public static final ObjectType MAINTAIN_POOL_DATA_BROKER = new ObjectType("MaintainPoolOfReviewersDataBroker");
    public static final ObjectType SELECT_REVIEWERS_DATA_BROKER = new ObjectType("SelectReviewersDataBroker");
    
    /**
     * End definitions pertaining to the Select Reviewers and Establish Panel Structure functionality.
     */
    public static final ObjectType GRANT_APPLICATION_MANAGER = new ObjectType("GrantApplicationManager");
    public static final ObjectType GRANT_APPLICATION_DATA_BROKER = new ObjectType("GrantApplicationDataBroker");
    
    public static final ObjectType MOVE_GRANT_APPLICATION_SCHEDULE_ACTION = new ObjectType("MoveGrantApplicationScheduleAction");
    public static final ObjectType GET_GRANT_APPLICATION_ACTION = new ObjectType("GetGrantApplicationSearchListAction");
    public static final ObjectType VALIDATE_MODE_DELETE_GRANT_APPLICATION_ACTION = new ObjectType("ValidateMoveDeleteGrantApplicationAction");
    public static final ObjectType GET_MANAGE_GRANT_APPLICATION_ACTION = new ObjectType("GetManageGrantApplicationLogListAction");
    public static final ObjectType VALIDATE_MODIFY_GRANT_APPLICATION_ACTION = new ObjectType("ValidateModifyGrantApplicationAction");
    public static final ObjectType INQUIRE_GRANT_APPLICATION_ACTION = new ObjectType("InquireGrantApplicationAction");
    public static final ObjectType MOVE_GRANT_APPLICATIONS_FY_ACTION = new ObjectType("MoveGrantApplicationsFYAction");    
    public static final ObjectType LOAD_INELIGIBILITY_LETTER_ACTION = new ObjectType("LoadIneligibilityLetterAction");
    public static final ObjectType SEND_INELIGIBILITY_LETTER_ACTION = new ObjectType("SendIneligibilityLetterAction");
    public static final ObjectType SCREEN_GRANT_APPLICATION_ACTION = new ObjectType("ScreenGrantApplicationAction");
    public static final ObjectType DELETE_GRANT_APPLICATION__ACTION = new ObjectType("DeleteGrantApplicationsAction");
    public static final ObjectType UNDELETE_GRANT_APPLICATION__ACTION = new ObjectType("UndeleteGrantApplicationsAction");
    public static final ObjectType COPY_GRANT_APPLICATIONS_ACTION =  new ObjectType("CopyGrantApplicationsAction");
    public static final ObjectType COPY_GRANT_APPLICATIONS_NO_OVERWRITE_ACTION =  new ObjectType("CopyGrantApplicationsNoOverwriteAction");
    
    public static final ObjectType MANAGE_FORMULA_MANAGER = new ObjectType("ManageFormulaManager");
    public static final ObjectType MANAGE_FORMULA_DATA_BROKER = new ObjectType("ManageFormulaDataBroker");
    public static final ObjectType APPLICATION_REVIEW_MANAGER = new ObjectType("ApplicationReviewManager");
    public static final ObjectType APPLICATION_REVIEW_DATA_BROKER = new ObjectType("ApplicationReviewDataBroker");
    public static final ObjectType SYSTEM_POST_OFFICE = new ObjectType("SystemPostOffice");
    public static final ObjectType BASE_MANAGER = new ObjectType("BaseManager");
    
    public static final ObjectType DISCRETIONARY_SLATE_MANAGER = new ObjectType("DiscretionarySlateManager");
    public static final ObjectType DISCRETIONARY_SLATE_DATA_BROKER = new ObjectType("DiscretionarySlateDataBroker");
    
    public static final ObjectType MYHOMEPAGE_MANAGER = new ObjectType("MyHomePageManager");
    public static final ObjectType MYHOMEPAGE_BROKER = new ObjectType("MyHomePageDataBroker");
    
    public static final ObjectType EFORM_DATABROKER = new ObjectType("EformDataBroker");
    public static final ObjectType EFORM_MANAGER = new ObjectType("EformManager");
   
    public static final ObjectType DOCUMENT_SERVICE_MANAGER = new ObjectType("DocumentServiceManager");
    public static final ObjectType FILESYSTEM_STORAGE_ADAPTER = new ObjectType("FilesystemStorageAdapter");

    
    // ====================== EFORM BUSINESS VALIDATORS ========================
    public static final ObjectType EFORM_BASE_BUSINESS_VALIDATOR = new ObjectType("EFORM_BASE_BUSINESS_VALIDATOR");
    
    // ============ END EFORM BUSINESS VALIDATORS ==============================
    public static final ObjectType RECIPIENT_MANAGER = new ObjectType("RecipientManager");
    public static final ObjectType RECIPIENT_DATA_BROKER = new ObjectType("RecipientDataBroker");
    public static final ObjectType DUNS_REASSIGNMENT_PROCESS = new ObjectType("DunsReassignmentProcess");
    
    public static final ObjectType SUBLEDGER_MANAGER = new ObjectType("SubledgerManager");
    public static final ObjectType LOAN_SUBPROGRAM_MANAGER = new ObjectType("LoanSubProgramManager");
    public static final ObjectType LOAN_SUBPROGRAM_DATA_BROKER = new ObjectType("LoanSubProgramDataBroker");
    
    public static final ObjectType FEEDER_TRANSACTION_MANAGER = new ObjectType("FeederTransactionManager");
    public static final ObjectType FEEDER_TRANSACTION_DATA_BROKER = new ObjectType("FeederTransactionDataBroker");
   
    //
	// These definitions are needed for unit testing only.
	//
    public static final ObjectType TESTA = new ObjectType("TestComponentA");
    public static final ObjectType TESTB = new ObjectType("TestComponentB");
    public static final ObjectType TESTC = new ObjectType("TestComponentC");
    
    public static final ObjectType TIME_OF_DAY_SCHEDULER= new ObjectType("TimeOfDayScheduler");
    public static final ObjectType MY_SCHEDULED_PROCESS = new ObjectType("MyScheduledProcess");
    
    public static final ObjectType REVIEWER_MANAGER = new ObjectType("ReviewerManager");

    public static final ObjectType USER_REGISTRATION_MANAGER = new ObjectType("UserRegistrationManager");
    public static final ObjectType MANAGE_USER_DATA_BROKER = new ObjectType("ManageUserDataBroker");    
    
    
    // AWARD NOTIFICATION Monitors
    // Addded for GenerateAcknowledgementPostCards 
    public static final ObjectType AWARD_NOTIFICATION_GPA_MANAGER = new ObjectType("GPAManager");
    public static final ObjectType  AWARD_NOTIFICATION_GPA_BROKER = new ObjectType("GPADataBroker");
    
    public static final ObjectType AWARD_NOTIFICATION_CO_DATA_BROKER = new ObjectType("CommitObligateDataBroker");
    public static final ObjectType AWARD_NOTIFICATION_CO_MANAGER = new ObjectType("CommitObligateManager");
    
    public static final ObjectType AWARD_NOTIFICATION_TERM_BROKER = new ObjectType("MaintainTermDataBroker");
    public static final ObjectType AWARD_NOTIFICATION_TERM_MANAGER = new ObjectType("MaintainTermManager");
    
    
    public static final ObjectType GAN_MANAGER = new ObjectType("GANManager");
    public static final ObjectType GAN_DATA_BROKER = new ObjectType("GANDataBroker");
    
    public static final ObjectType PREPAREAWARD_MANAGER = new ObjectType("PrepareAwardManager");
    public static final ObjectType PREPAREAWARD_BROKER = new ObjectType("PrepareAwardDataBroker");
    
    public static final ObjectType ROLLOVER_RENEW_ROLLBACK_MANAGER = new ObjectType("RolloverRenewRollbackManager");
    public static final ObjectType ROLLOVER_RENEW_ROLLBACK_BROKER = new ObjectType("RolloverRenewRollbackDataBroker");
    
    public static final ObjectType AWARD_NOTIFICATION_GCN_BROKER = new ObjectType("GCNDataBroker");
    public static final ObjectType AWARD_NOTIFICATION_GCN_MANAGER = new ObjectType("GCNManager");
    
    public static final ObjectType PAYEE_REASSIGNMENT_MANAGER = new ObjectType("PayeeReassignmentManager");
    public static final ObjectType PAYEE_REASSIGNMENT_BROKER = new ObjectType("PayeeReassignmentDataBroker");
    
    
    public static final ObjectType MAINTAIN_SUPPLEMENT_MANAGER = new ObjectType("MaintainSupplementManager");
    public static final ObjectType MAINTAIN_SUPPLEMENT_BROKER = new ObjectType("MaintainSupplementDataBroker");
    //subledger Engine
    public static final ObjectType SUBLEDGER_DATA_BROKER = new ObjectType("SubledgerDataBroker");
    
    public static final ObjectType COF_FILE_DATA_BROKER = new ObjectType("CofFileDataBroker");
    
    //batch job to update the balances of the award.
    public static final ObjectType UPDATE_BALANCE_JOB = new ObjectType("UpdateAwardBalancesBatchJob");
    
    public static final ObjectType FMSS_INTERFACE_MANAGER = new ObjectType("FMSSManager");
    public static final ObjectType FMSS_INTERFACE_BROKER = new ObjectType("FMSSDatabroker");
    
    public static final ObjectType MAINTAIN_PAYMENTS_MANAGER = new ObjectType("PaymentsManager");
    public static final ObjectType MAINTAIN_PAYMENTS_BROKER = new ObjectType("PaymentsDataBroker");
    
    public static final ObjectType AWARD_CLOSEOUT_MANAGER = new ObjectType("AwardCloseoutManager");
    public static final ObjectType AWARD_CLOSEOUT_BROKER = new ObjectType("AwardCloseoutDataBroker");
    
    public static final ObjectType MAINTAIN_REFUND_MANAGER = new ObjectType("RefundManager");
    public static final ObjectType MAINTAIN_REFUND_BROKER = new ObjectType("RefundDataBroker");
    //Feeder 
    public static final ObjectType MAINTAIN_FEERDER_AWARD_MANAGER = new ObjectType("FeederAwardManager");
    public static final ObjectType MAINTAIN_FEERDER_AWARD_BROKER = new ObjectType("FeederAwardDataBroker");
    public static final ObjectType MANAGE_FEERDER_ERROR_LOG_MANAGER = new ObjectType("FeederErrorLogManager");
    public static final ObjectType MANAGE_FEERDER_ERROR_LOG_BROKER = new ObjectType("FeederErrorLogDataBroker");
    public static final ObjectType UD_DETERMINATION_MANAGER = new ObjectType("UdManager");
    public static final ObjectType UD_DETERMINATION_DATA_BROKER = new ObjectType("UdDataBroker");
    public static final ObjectType GENERATE_COF_FILE_PROCESS = new ObjectType("GenerateCofFileProcess");
    
    /**
     * Maintain eApplication
     */
    public static final ObjectType MAINTAIN_EAPPLICATION_MANAGER = new ObjectType("MaintaineApplicationManager");
    public static final ObjectType MAINTAIN_EAPPLICATION_BROKER = new ObjectType("MaintaineApplicationDataBroker");
    public static final ObjectType IMPACT_AID_BROKER = new ObjectType("EformImpactAidDataBroker");
    public static final ObjectType IMPACT_AID_MANAGER = new ObjectType("EformImpactAidManager");
    
    /**
     * Submit eApplication
     */
    public static final ObjectType EAPPLICATION_MANAGER = new ObjectType("EApplicationManager");
    public static final ObjectType EAPPLICATION_BROKER = new ObjectType("EApplicationDataBroker");
    
    //Grants.gov Admin monitor
    public static final ObjectType GRANTS_GOV_ADMIN_MANAGER = new ObjectType("GrantsGovAdminManager");
    public static final ObjectType GRANTS_GOV_ADMIN_BROKER = new ObjectType("GrantsGovAdminDataBroker");

    public static final ObjectType ADMIN_ACTION_MANAGER = new ObjectType("AdminActionManager");
    public static final ObjectType ADMIN_ACTION_BROKER = new ObjectType("AdminActionDataBroker");
    
    public static final ObjectType MASS_REASSIGNMENT_MANAGER=new ObjectType("MassReassignmentManager");
    public static final ObjectType MASS_REASSIGNMENT_BROKER=new ObjectType("MassReassignmentDataBroker");
    
    public static final ObjectType GRANT_TRANSFER_MANAGER=new ObjectType("GrantTransferManager"); 
    public static final ObjectType GRANT_TRANSFER_BROKER=new ObjectType("GrantTransferDataBroker"); 
    
    public static final ObjectType CLOSEOUT_NIGHTLY_BATCH = new ObjectType("AwardCloseoutNightlyBatchScheduler");
    
    public static final ObjectType FEEDER_FILE_MANAGER = new ObjectType("FeederFileManager");
    public static final ObjectType FEEDER_FILE_DATA_BROKER = new ObjectType("FeederFileDataBroker");
    public static final ObjectType FEERDER_COF_FILE_MANAGER = new ObjectType("CofFileManager");
    
    public static final ObjectType FMSS_TRANSACTION_VERIFIER = new ObjectType("FMSSTransactionVerifier");
    public static final ObjectType REPORTS_DATABROKER = new ObjectType("ReportsDataBroker");
    public static final ObjectType FEEDER_AUTOMATION_PROCESS = new ObjectType("FeederAutomationProcess");
    
    
    
    
    public static final ObjectType ELECTRONIC_GRANT_SURVEY_MANAGER = new ObjectType("ElectronicGrantSurveyManager");
    public static final ObjectType ELECTRONIC_GRANT_SURVEY_BROKER = new ObjectType("ElectronicGrantSurveyBroker");
    
    public static final ObjectType FELLOWSHIP_PROJECT_DIRECTOR_MANAGER = new ObjectType("FellowshipProjectDirectorManager");
    public static final ObjectType FELLOWSHIP_PROJECT_DIRECTOR_BROKER = new ObjectType("FellowshipProjectDirectorBroker");
    
    public static final ObjectType REGISTRATION_MANAGER = new ObjectType("RegistrationManager");
    public static final ObjectType REGISTRATION_BROKER = new ObjectType("RegistrationDataBroker");
    
    public static final ObjectType PAYEE_SERVICER_MANAGER = new ObjectType("PayeeServicerManager");
    public static final ObjectType PAYEE_SERVICER_DATA_BROKER = new ObjectType("PayeeServicerDataBroker");
    
    //Maintain Template Brokers and Managers
    public static final ObjectType TEMPLATES_AND_LETTERS_BROKER = new ObjectType("TemplatesAndLettersDataBroker");
    public static final ObjectType TEMPLATES_AND_LETTERS_MANAGER = new ObjectType("TemplatesAndLettersManager");
    
    
    
    //Configurable Fields Manager and Broker
    public static final ObjectType CONFIGURABLEFIELDS_BROKER = new ObjectType("ConfigurableFieldsDataBroker");
    public static final ObjectType CONFIGURABLEFIELDS_MANAGER = new ObjectType("ConfigurableFieldsManager");
    
    /*
     * Process Payments
     */
    public static final ObjectType PROCESS_PAYMENTS_MANAGER = new ObjectType("ProcessPaymentsManager");
    public static final ObjectType PROCESS_PAYMENTS_DATA_BROKER = new ObjectType("ProcessPaymentsDataBroker");
    
    //Performance Report Functional Area
    public static final ObjectType MAINTAIN_PERFORMANCE_REPORT_MANAGER = new ObjectType("MaintainPerformanceReportManager");
    public static final ObjectType MAINTAIN_PERFORMANCE_REPORT_BROKER = new ObjectType("MaintainPerformanceReportDataBroker");
    
    public static final ObjectType SUBMIT_PERFORMANCE_REPORT_MANAGER = new ObjectType("SubmitPerformanceReportManager");
    public static final ObjectType SUBMIT_PERFORMANCE_REPORT_BROKER = new ObjectType("SubmitPerformanceReportDataBroker");
    
    public static final ObjectType MONITOR_PERFORMANCE_REPORT_MANAGER = new ObjectType("MonitorPerformanceReportManager");  
    public static final ObjectType MONITOR_PERFORMANCE_REPORT_BROKER = new ObjectType("MonitorPerformanceReportsDataBroker");
    
    public static final ObjectType PERFORMANCE_REPORT_MILESTONE_MANAGER = new ObjectType("PerformanceReportMilestoneManager");  
    public static final ObjectType PERFORMANCE_REPORT_MILESTONE_BROKER = new ObjectType("PerformanceReportMilestoneDataBroker");
    public static final ObjectType MILESTONE_DUE_DATE_EMAIL_NOTIFIER = new ObjectType("PerformanceReportDueDateBatchJob");
    
    
}
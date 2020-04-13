package com.onecloud.autotools.semantic.diagnostics;

import com.onecloud.autotools.support.enumeration.Namespace;
import com.onecloud.autotools.support.enumeration.NamespaceElement;

/**
 * Enumerates conditions related to the success or failure of a requested
 * operation in a manner that might be mapped to a result rendered on the
 * application's user interface. Conditions in this enumeration may represent
 * errors, or warnings, or simple information.
 * <p>
 * Each object of this class contains a String-valued key. This may be used in
 * any manner to indicate any condition of interest.
 * 
 * @author sichituk
 *
 */
public class ConditionCode implements NamespaceElement {
	/**
	 * The namespace for the enumeration.
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
	 *            The unique key value to associate with the constant member.
	 */
	protected ConditionCode(String key) {
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
	 * Returns the key value of the constant.
	 *
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getKey();
	}

	// /////////////////////////////////
	// Start of Constant Definitions //
	// /////////////////////////////////

	/**
	 * Indicates a successful operation.
	 */
	public static final ConditionCode SUCCESS = new ConditionCode("SUCCESS");

	/**
	 * Indicates that the user made an unsuccessful transaction
	 */
	public static final ConditionCode UNSUCCESSFUL = new ConditionCode("UNSUCCESSFUL");

	/**
	 * Indicates that a document was not found
	 */
	public static final ConditionCode DOCUMENT_NOT_FOUND = new ConditionCode("DOCUMENT_NOT_FOUND");
	
	/**
	 * Indicates a failed operation.
	 */
	public static final ConditionCode FAILURE = new ConditionCode("FAILURE");
	
	/**
	 * Indicates a warning.  Should be used to indicate a warning message to be rendered in orange.
	 */
	public static final ConditionCode WARNING = new ConditionCode("WARNING");

	/**
	 * Indicates a fault condition.
	 */
	public static final ConditionCode FAULT = new ConditionCode("FAULT");

	/**
	 * Indicates a length error.
	 */
	public static final ConditionCode ERROR_LENGTH = new ConditionCode("ERROR_LENGTH");

	/**
	 * Indicates a syntax error.
	 */
	public static final ConditionCode ERROR_SYNTAX = new ConditionCode("ERROR_SYNTAX");

	/**
	 * Indicates an invalid value.
	 */
	public static final ConditionCode ERROR_VALUE = new ConditionCode("ERROR_VALUE");

	public static final ConditionCode INVALID_NUMBER = new ConditionCode("INVALID_NUMBER");
	
	/**
	 * Indicates an generic invalid value.
	 */
	public static final ConditionCode GENERIC_DATE_FORMAT_ERROR_VALUE = new ConditionCode("GENERIC_DATE_FORMAT_ERROR_VALUE");

	
	/**
	 * Indicates an invalid Phone/Fax Number Format
	 */
	public static final ConditionCode ERROR_INVALID_PHONE = new ConditionCode("ERROR_INVALID_PHONE");
	public static final ConditionCode ERROR_INVALID_TOLL_PHONE = new ConditionCode("ERROR_INVALID_TOLL_PHONE");
	
	/**
	 * Indicates an invalid Email address.
	 */
	public static final ConditionCode ERROR_INVALID_EMAIL_ADDRESS = new ConditionCode("ERROR_INVALID_EMAIL_ADDRESS");
	
	/**
	 * Indicates an invalid CFDA/Subprogram Format
	 */
	public static final ConditionCode ERROR_INVALID_CFDA_SUBPROGRAM = new ConditionCode("ERROR_INVALID_CFDA_SUBPROGRAM");
	
	/**
	 * Indicates an invalid DUNS Number Format
	 */
	public static final ConditionCode ERROR_INVALID_DUNS = new ConditionCode("ERROR_INVALID_DUNS");
	
	/**
	 * Indicates an invalid PR/Award Number Format
	 */
	public static final ConditionCode ERROR_INVALID_PR_AWARD_NO = new ConditionCode("ERROR_INVALID_PR_AWARD_NO");
	
	/**
	 * Indicates an iAcademic Year Format
	 */
	public static final ConditionCode ERROR_INVALID_ACADEMIC_YEAR = new ConditionCode("ERROR_INVALID_ACADEMIC_YEAR");

	/**
	 * Indicates a field is required
	 */
	public static final ConditionCode REQUIRED_FIELD = new ConditionCode("REQUIRED_FIELD");

	/**
	 * Indicates date errors
	 */
	public static final ConditionCode INVALID_DATE = new ConditionCode("INVALID_DATE");
	public static final ConditionCode INCORRECT_YEAR_FORMAT = new ConditionCode("INCORRECT_YEAR_FORMAT");
	/**
	 * Indicates parameter conflict
	 */
	public static final ConditionCode PARAMETER_CONFLICT = new ConditionCode("PARAMETER_CONFLICT");

	/**
	 * Used to emit custom messages
	 */
	public static final ConditionCode CUSTOM = new ConditionCode("CUSTOM");

	/**
	 * Indicates a validation error on a user-entered fiscal year
	 */
	public static final ConditionCode FISCAL_YEAR = new ConditionCode("FISCAL_YEAR");
	
	/**
	 * Indicates a validation error on a user-entered CongressionalCode
	 */
	public static final ConditionCode CONGRESSIONAL_CODE = new ConditionCode("CONGRESSIONAL_CODE");

	/**
	 * Indicates that there were no results returned for displaying
	 */
	public static final ConditionCode NO_RESULTS = new ConditionCode("NO_RESULTS");

	/**
	 * Indicates that no search results were returned after a search
	 */
	public static final ConditionCode NO_SEARCH_RESULTS = new ConditionCode("NO_SEARCH_RESULTS");
	
	/**
	 * Indicates that there are no contacts for the program office and division.
	 */
	public static final ConditionCode NO_CONTACTS = new ConditionCode("NO_CONTACTS");

	/**
	 * Indicates that there was an error detected by the report generating
	 * engine. Diagnostics usually include a message that originated in the
	 * report generating engine as a detail.
	 */
	public static final ConditionCode REPORT_ERROR = new ConditionCode("REPORT_ERROR");

	public static final ConditionCode DATE_ERROR = new ConditionCode("DATE_ERROR");

	/**
	 * Indicates that the schedule has already been locked.
	 */
	public static final ConditionCode ERROR_LOCKED = new ConditionCode("ERROR_LOCKED");

	/**
	 * Indicates that the schedule has already been unlocked.
	 */
	public static final ConditionCode ERROR_UNLOCKED = new ConditionCode("ERROR_UNLOCKED");

	public static final ConditionCode SELECT_REQUIRED = new ConditionCode("SELECT_REQUIRED");

	public static final ConditionCode NOT_REQUIRED = new ConditionCode("NOT_REQUIRED");
	
	/**
	 * Indicates a duplicate value.
	 */
	public static final ConditionCode ERROR_DUPLICATE = new ConditionCode("ERROR_DUPLICATE");

	/**
	 * Indicates that no search results were returned after a search, but user can continue on to create
	 */
	public static final ConditionCode NO_SEARCH_RESULTS_CREATE = new ConditionCode("NO_SEARCH_RESULTS_CREATE");

	/**
	 * Indicates a syntax error with a person's name
	 */
	public static final ConditionCode ERROR_NAME_SYNTAX = new ConditionCode("ERROR_NAME_SYNTAX");

	/**
	 * Indicates a syntax error with a number field
	 */
	public static final ConditionCode ERROR_NUMBER_SYNTAX = new ConditionCode("ERROR_NUMBER_SYNTAX");

	/**
	 * Indicates a syntax error with an Alpha-numeric field
	 */
	public static final ConditionCode ERROR_ALPHA_NUMERIC_SYNTAX = new ConditionCode("ERROR_ALPHA_NUMERIC_SYNTAX");

	/**
	 * Indicates a syntax error with a text field
	 */
	public static final ConditionCode ERROR_TEXT_SYNTAX = new ConditionCode("ERROR_TEXT_SYNTAX");

	public static final ConditionCode ADDRESS_COUNTRY_STATE_MISMATCH = new ConditionCode("ADDRESS_COUNTRY_STATE_MISMATCH");
	/* ---------------------------------------------------------------------------------------------------
	 *  Develop Application Review Plan - Condition Codes
	 * ---------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * Indicates an error with the min score on TRF.
	 */
	public static final ConditionCode ERROR_MIN_SCORE = new ConditionCode("ERROR_MIN_SCORE");
	
	/**
	 * Indicates an error with the max score on TRF.
	 */
	public static final ConditionCode ERROR_MAX_SCORE = new ConditionCode("ERROR_MAX_SCORE");
	
	/**
	 * Indicates an error with the score on TRF.
	 */
	public static final ConditionCode ERROR_SCORE = new ConditionCode("ERROR_SCORE");
	
	/**
	 * Indicates an error with the score on sub-Questions.
	 */
	public static final ConditionCode ERROR_SUB_QUESTION_SCORE = new ConditionCode("ERROR_SUB_QUESTION_SCORE");
	
	/**
	 * Indicates answer required.
	 */
	public static final ConditionCode ANSWER_REQUIRED = new ConditionCode("ANSWER_REQUIRED");
	/**
	 * Indicates Invalid Status
	 */
	public static final ConditionCode INVALID_STATUS = new ConditionCode("INVALID_STATUS");

	/**
	 * Indicates too few parameters
	 */
	public static final ConditionCode INSUFFICIENT_PARAMETERS = new ConditionCode("INSUFFICIENT_PARAMETERS");
	
	/**
	 * Indicates a negative parameters
	 */
	public static final ConditionCode ERROR_NEGATIVE_NUMBER = new ConditionCode("ERROR_NEGATIVE_NUMBER");     
	/**
	 * Indicates a fractional value for the resultant calculation
	 */
	public static final ConditionCode ERROR_FRACTION_VALUE = new ConditionCode("ERROR_FRACTION_VALUE");     

	/**
	 * Indicates a large value for the paramenter
	 */
	public static final ConditionCode LARGE_PARAMETER_VALUE = new ConditionCode("LARGE_PARAMETER_VALUE");     
	/**
	 * Indicates a large value for the paramenter
	 */
	public static final ConditionCode WARNING_REVIEWERS = new ConditionCode("WARNING_REVIEWERS");     
	/**
	 * Indicates a large value for the paramenter
	 */
	public static final ConditionCode ERROR_REVIEWERS = new ConditionCode("ERROR_REVIEWERS");
	
	/**
	 * Indicates that an item chosen out of a list can no longer be found in the database
	 */
	public static final ConditionCode SELECTION_NOT_FOUND = new ConditionCode("SELECTION_NOT_FOUND");
	/**
     * Indicates a Grant Application is not movable.
	 */
	public static final ConditionCode GRANT_APPLICATION_IS_NOT_MOVABLE = new ConditionCode("GRANT_APPLICATION_IS_NOT_MOVABLE");
    
    /**
     * Indicates a Grant Application is not deletable.
     */
    public static final ConditionCode GRANT_APPLICATION_IS_NOT_DELETABLE = new ConditionCode("GRANT_APPLICATION_IS_NOT_DELETABLE");

    /**
     * Indicates a Grant Application is not deletable.
     */
    public static final ConditionCode GRANT_APPLICATION_IS_NOT_UNDELETABLE = new ConditionCode("GRANT_APPLICATION_IS_NOT_UNDELETABLE");

    /**
     * Indicates a Grant Application is not modifiable.
     */
    public static final ConditionCode APPLICATION_NOT_MODIFIABLE = new ConditionCode("APPLICATION_NOT_MODIFIABLE");


    /**
     * Indicates a Validation exception occurred.
     */
    public static final ConditionCode VALIDATION = new ConditionCode("VALIDATION");
    
    /**
     * Indicates a No Privilege exception occurred.
     */
    public static final ConditionCode NO_PRIVILEGE = new ConditionCode("NO_PRIVILEGE");
    public static final ConditionCode NO_OP_PRIVILEGE = new ConditionCode("NO_OP_PRIVILEGE");
    
    /**
     * Indicates a ScheduleDoesNotExist exception occurred.
     */
    public static final ConditionCode SCHEDULE_DOES_NOT_EXIST = new ConditionCode("SCHEDULE_DOES_NOT_EXIST");
    
    /**
     * Indicates a ScheduleDoesNotExist exception occurred.
     */
    public static final ConditionCode SCHEDULE_IS_COMPETING_CONTINUATION = new ConditionCode("SCHEDULE_IS_COMPETING_CONTINUATION");

    /**
     * Indicates a ScheduleInvalidAwardType exception occurred.
     */
    public static final ConditionCode SCHEDULE_APPLICATIONS_INVALID_AWARD_TYPE = new ConditionCode("SCHEDULE_APPLICATIONS_INVALID_AWARD_TYPE");
    
    /**
     * Indicates a ScheduleInvalidAwardType exception occurred.
     */
    public static final ConditionCode SCHEDULE_INVALID_AWARD_TYPE = new ConditionCode("SCHEDULE_INVALID_AWARD_TYPE");
    
    
    /**
     * Indicates an AwardNumberAlreadyExists exception occurred.
     */
    public static final ConditionCode AWARD_NUMBER_ALREADY_EXISTS = new ConditionCode("AWARD_NUMBER_ALREADY_EXISTS");

    /**
     * Indicates an InvalidAwardNumberException exception occurred.
     */
    public static final ConditionCode INVALID_AWARD_NUMBER = new ConditionCode("INVALID_AWARD_NUMBER");
    
    /**
     * Indicates an GANAlreadyPrintedException exception occurred.
     */
    public static final ConditionCode GAN_ALREADY_PRINTED = new ConditionCode("GAN_ALREADY_PRINTED");
    
    /**
     * Indicates an ApplicationAlreadyExistsException exception occurred.
     */
    public static final ConditionCode APPLICATION_ALREADY_EXISTS = new ConditionCode("APPLICATION_ALREADY_EXISTS");
    
    /**
     * Indicates a GrantApplicationAlreadyCopiedException occurred
     */
    public static final ConditionCode GRANT_APPLICATION_ALREADY_COPIED_EXCEPTION = new ConditionCode("GRANT_APPLICATION_ALREADY_COPIED_EXCEPTION");

    /**
     * Used to display error information on Already Copied Grant Applications
     */
    public static final ConditionCode GRANT_APPLICATION_ALREADY_COPIED_DETAILS= new ConditionCode("GRANT_APPLICATION_ALREADY_COPIED_DETAILS");
    
    /**
     * Indicates a ScheduleInvalidState exception occurred
     */
    public static final ConditionCode SCHEDULE_INVALID_STATE_EXCEPTION = new ConditionCode("SCHEDULE_INVALID_STATE_EXCEPTION");
    /**
     * Indicates a ApplicationNotScreenable exception occurred
     */
    public static final ConditionCode APPLICATION_NOT_SCREENABLE = new ConditionCode("APPLICATION_NOT_SCREENABLE");

    /**
     * Indicates that an Application is of type Formula and cannot be for an Individual
     */
    public static final ConditionCode FORMAULA_NOT_INDIVIDUAL = new ConditionCode("FORMAULA_NOT_INDIVIDUAL");
    
    /**
     * Indicates that FileStorageAdapterException or BadArgumentsException exception occured.
     */
    public static final ConditionCode FILE_STORAGE_ERROR = new ConditionCode("FILE_STORAGE_ERROR");
    
    /**
     * Indicates that FileInfectedException exception occured.
     */
    public static final ConditionCode FILE_INFECTED = new ConditionCode("FILE_INFECTED");
    /**
     * Indicates that Application not selected.
     */
    public static final ConditionCode APPLICATION_NOT_SELECTED = new ConditionCode("APPLICATION_NOT_SELECTED");
    /**
     * Indicates that GrantApplicationIsNotIneligibleException exception occured.
     */
    public static final ConditionCode APPLICATION_NOT_INELIGIBLE = new ConditionCode("APPLICATION_NOT_INELIGIBLE");
    /**
     * Indicates that NoEmailExistsException exception occured.
     */
    public static final ConditionCode NO_EMAIL = new ConditionCode("NO_EMAIL");
    /**
     * Indicates that No Ineligible Comments are entered.
     */
    public static final ConditionCode NO_INELIGIBLE_COMMENTS= new ConditionCode("NO_INELIGIBLE_COMMENTS");
    /**
     * Indicates that No Ineligible Status is entered.
     */
    public static final ConditionCode NO_INELIGIBLE_STATUS= new ConditionCode("NO_INELIGIBLE_STATUS");
    /**
     * Indicates that No Ineligible Status is entered.
     */
    public static final ConditionCode ELIGIBLE_STATUS= new ConditionCode("ELIGIBLE_STATUS");
    /**
     * Indicates that UnableToSaveApplicationException exception occured.
     */
    public static final ConditionCode NO_SAVE= new ConditionCode("NO_SAVE");
    /**
     * Indicates that if no applicant name is entered.
     */
    public static final ConditionCode NO_APPLICANT_NAME= new ConditionCode("NO_APPLICANT_NAME");
    /**
     * Indicates a validation error on a user-entered process date.
     */
    public static final ConditionCode PROCESS_DATE= new ConditionCode("PROCESS_DATE");
    
	/**
	 * Indicates a validation error on a user-entered schedule no.
	 */
	public static final ConditionCode SCHEDULE_NUMBER = new ConditionCode("SCHEDULE_NUMBER");
	
	/**
	 * Indicates that there are no CMIA Clocks available when trying to delete a clock
	 */
	public static final ConditionCode NO_CMIA_EXISTS = new ConditionCode("NO_CMIA_EXISTS");
	/**
	 * Indicates a validation error on a user-entered schedule no.
	 */
	public static final ConditionCode DOLLAR_NEGATIVE = new ConditionCode("DOLLAR_NEGATIVE");
	/**
	 * Indicates a validation error on a user-entered schedule no.
	 */
	public static final ConditionCode DOLLAR_INVALID = new ConditionCode("DOLLAR_INVALID");
	
	/**
	 * Indiacats that no value was selected for the requested operation.
	 */
	public static final ConditionCode NO_SELECTION = new ConditionCode("NO_SELECTION");

	/**
	 * Indicates that there is no Cerifying Representative
	 */
	public static final ConditionCode NO_CERTIFYING_REPRESENTATIVE = new ConditionCode("NO_CERTIFYING_REPRESENTATIVE");
	
	/**
	 * Indicates that there is no Authorizing Official
	 */
	public static final ConditionCode NO_AUTHORIZING_OFFICAL = new ConditionCode("NO_AUTHORIZING_OFFICAL");

	/**
	 * Indicates that there is no Cerifying Representative
	 */
	public static final ConditionCode NO_DIRECTOR = new ConditionCode("NO_DIRECTOR");
	
	/**
	 * Indicates a field was entered in an incorrect format
	 */
	public static final ConditionCode INCORRECT_FIELD_FORMAT = new ConditionCode("INCORRECT_FIELD_FORMAT");
	 
	/**
	 * Indicates an email could not be sent 
	 */
	public static final ConditionCode EMAIL_COULD_NOT_BE_SENT = new ConditionCode("EMAIL_COULD_NOT_BE_SENT");
	public static final ConditionCode EMAIL_IS_SENT = new ConditionCode("EMAIL_IS_SENT");

	/**
	 * Indicates a duplicate Pr/Award number was entered
	 */
	public static final ConditionCode DUPLICATE_PRAWARD_NUMBER = new ConditionCode("DUPLICATE_PRAWARD_NUMBER");
	

	/**
	 * CMIA Process start date has not arrived
	 */
	public static final ConditionCode CMIA_PROCESS_START_DATE_NOT_ARRIVED  = new ConditionCode("CMIA_PROCESS_START_DATE_NOT_ARRIVED");

	/**
	 * CMIA Clock has already been started message
	 */
	public static final ConditionCode CMIA_CLOCK_ALREADY_STARTED  = new ConditionCode("CMIA_CLOCK_ALREADY_STARTED");

	/**
	 * CMIA Clock has already been stopped message
	 */
	public static final ConditionCode CMIA_CLOCK_ALREADY_STOPPED  = new ConditionCode("CMIA_CLOCK_ALREADY_STOPPED");

	/**
	 * CMIA Start date cannot be before last stop date message
	 */
	public static final ConditionCode  CMIA_START_DATE_BEFORE_STOP_DATE = new ConditionCode("CMIA_START_DATE_BEFORE_STOP_DATE");

	/**
	 * CMIA Stop date cannot be before last start date message
	 */
	public static final ConditionCode CMIA_STOP_DATE_BEFORE_START_DATE  = new ConditionCode("CMIA_STOP_DATE_BEFORE_START_DATE");

	/**
	 * CMIA Entry cannot be deleted message
	 */
	public static final ConditionCode  CMIA_CANNOT_BE_DELETED = new ConditionCode("CMIA_CANNOT_BE_DELETED");

	/**
	 * Fellow Start date cannot be after end date message
	 */
	public static final ConditionCode  FELLOW_START_DATE_AFTER_END_DATE = new ConditionCode("FELLOW_START_DATE_AFTER_END_DATE");

	/**
     * Indicates that InvalidZipCodeException exception occured.
     */
    public static final ConditionCode INVALID_ZIPCODE = new ConditionCode("INVALID_ZIPCODE");
	/**
     * Indicates that InvalidZipCodeException exception occured.
     */
    public static final ConditionCode INVALID_ZIPCODE_EXT = new ConditionCode("INVALID_ZIPCODE_EXT");
    /**
	 * Fellow Entry cannot be deleted message
	 */
	public static final ConditionCode FELLOW_CANNOT_BE_DELETED  = new ConditionCode("FELLOW_CANNOT_BE_DELETED");

	
    /**
     * Person is not an Ed employee
     */
	public static final ConditionCode NOT_ED_EMPLOYEE = new ConditionCode("NOT_ED_EMPLOYEE");
	
	/**
	 * Employee is already in the pool of reviewers
	 */
	public static final ConditionCode IN_POOL = new ConditionCode("IN_POOL");
	
	public static final ConditionCode ALREADY_PANEL_MONITOR = new ConditionCode("ALREADY_PANEL_MONITOR");
	
	public static final ConditionCode ERROR_PHONE_FORMAT = new ConditionCode("ERROR_PHONE_FORMAT");
    
    
    /**
     * Establish Panel Structure
     * Condition codes
     */
    /**
     * Indicates that no check boxes are selected for panels selection. 
     */
	public static final ConditionCode REVIEW_NOT_REQUIRED = new ConditionCode("REVIEW_NOT_REQUIRED");
    public static final ConditionCode CHECK_BOX_NOT_SELECTED = new ConditionCode("CHECK_BOX_NOT_SELECTED");
    public static final ConditionCode INVALID_SEARCH_CRITERIA_FOR_REVIEWER_ASSIGN = new ConditionCode("INVALID_SEARCH_CRITERIA_FOR_REVIEWER_ASSIGN");
    
    public static final ConditionCode REVIEWER_WITH_SCORING = new ConditionCode("REVIEWER_WITH_SCORING");
    public static final ConditionCode SINGLE_TIER_ERROR = new ConditionCode("SINGLE_TIER_ERROR");
    
    
    public static final ConditionCode SAME_STATE_ASSIGNED = new ConditionCode("SAME_STATE_ASSIGNED");
    public static final ConditionCode ALREADY_ASSIGNED = new ConditionCode("ALREADY_ASSIGNED");
    public static final ConditionCode REVIEWER_SHUFFEL_DISABLED = new ConditionCode("REVIEWER_SHUFFEL_DISABLED");
    
    public static final ConditionCode APPLICATION_ASSIGNED = new ConditionCode("APPLICATION_ASSIGNED");
    public static final ConditionCode SCORING_STARTED = new ConditionCode("SCORING_STARTED");
    public static final ConditionCode PANEL_START_DATE = new ConditionCode("PANEL_START_DATE");
    public static final ConditionCode PANEL_START_DATE_ERROR = new ConditionCode("PANEL_START_DATE_ERROR");
    
    public static final ConditionCode NO_PANELS = new ConditionCode("NO_PANELS");
    public static final ConditionCode NO_PRIMARY_CM = new ConditionCode("NO_PRIMARY_CM");

    public static final ConditionCode ACCS_DISABLED = new ConditionCode("ACCS_DISABLED");
   
	/**
	 * Indicates a field is required
	 */
	public static final ConditionCode FIELD_IS_REQUIRED = new ConditionCode("FIELD_IS_REQUIRED");
	
	/**
	 * Nothing was modified on the screen (Slate)
	 */
	public static final ConditionCode NOTHING_MODIFIED = new ConditionCode("NOTHING_MODIFIED");
    
	/**
	 * No applications ready for approval, but some were modified (Slate)
	 */
	public static final ConditionCode APPLICATIONS_NOT_READY_FOR_APPROVAL = new ConditionCode("APPLICATIONS_NOT_READY_FOR_APPROVAL");
	public static final ConditionCode APPLICATION_NOT_READY_FOR_APPROVAL = new ConditionCode("APPLICATION_NOT_READY_FOR_APPROVAL");
	public static final ConditionCode COMMON_NOT_READY_FOR_APPROVAL_CODE = new ConditionCode("COMMON_NOT_READY_FOR_APPROVAL_CODE");
    
	/**
	 * User chose not to approve the slate
	 */
	public static final ConditionCode SLATE_APPROVAL_CANCELED= new ConditionCode("SLATE_APPROVAL_CANCELED");
	
	
	/**
	 * User cannot manage slate on selected competition
	 */
	public static final ConditionCode NOT_ABLE_TO_MANAGE_SLATE= new ConditionCode("NOT_ABLE_TO_MANAGE_SLATE");
	
	
	public static final ConditionCode BIND_TO_VOLUNTARY_COST_SHARE_IS_ON = new ConditionCode("BIND_TO_VOLUNTARY_COST_SHARE_IS_ON");
	public static final ConditionCode DELETING_LAST_BUDGET_PERIOD = new ConditionCode("DELETING_LAST_BUDGET_PERIOD");
	public static final ConditionCode BUDGET_PERIOD_DATES_INCONSISTANT = new ConditionCode("BUDGET_PERIOD_DATES_INCONSISTANT");
	public static final ConditionCode RECOMMENDED_AMOUNT_EXCEEDS_REQUESTED_AMOUNT = new ConditionCode("RECOMMENDED_AMOUNT_EXCEEDS_REQUESTED_AMOUNT");
	public static final ConditionCode COST_SHARE_REQUIRED_PERCENTAGE_NOT_MET = new ConditionCode("COST_SHARE_REQUIRED_PERCENTAGE_NOT_MET");
	public static final ConditionCode FUTURE_BUDGET_PERIODS_WITHOUT_VALID_AMOUNTS = new ConditionCode("FUTURE_BUDGET_PERIODS_WITHOUT_VALID_AMOUNTS");
	public static final ConditionCode RECOMMENDED_AND_ACCS_AMOUNTS_DO_NOT_MATCH = new ConditionCode("RECOMMENDED_AND_ACCS_AMOUNTS_DO_NOT_MATCH");
	public static final ConditionCode NO_ACCS_BUDGET_COMPLETE = new ConditionCode("NO_ACCS_BUDGET_COMPLETE");
	public static final ConditionCode NO_FELLOWS_BUDGET_COMPLETE = new ConditionCode("NO_FELLOWS_BUDGET_COMPLETE");
	public static final ConditionCode NO_FELLOWS_BP_BUDGET_COMPLETE = new ConditionCode("NO_FELLOWS_BP_BUDGET_COMPLETE");
	public static final ConditionCode PROJECT_DIRECTOR_NOT_CERTIFIED = new ConditionCode("PROJECT_DIRECTOR_NOT_CERTIFIED");
	public static final ConditionCode APPLICATION_DUNS_AT_HIGH_RISK = new ConditionCode("APPLICATION_DUNS_AT_HIGH_RISK");
	public static final ConditionCode APPLICATION_DUNS_NOT_ENTERED = new ConditionCode("APPLICATION_DUNS_NOT_ENTERED");
	public static final ConditionCode AWARD_AMOUNT_EXCEEDS_REQUEST_AMOUNT = new ConditionCode("AWARD_AMOUNT_EXCEEDS_REQUEST_AMOUNT");
	public static final ConditionCode DUPLICATE_KEY_PERSONNEL = new ConditionCode("DUPLICATE_KEY_PERSONNEL");
	public static final ConditionCode PARTICIPATION_LEVEL_EXCEEDS_100 = new ConditionCode("PARTICIPATION_LEVEL_EXCEEDS_100");
	public static final ConditionCode PARTICIPATION_LEVEL_EXCEEDS_125 = new ConditionCode("PARTICIPATION_LEVEL_EXCEEDS_125");
	public static final ConditionCode NO_PROJECT_DIRECTOR_AS_KEY_PERSONNEL = new ConditionCode("NO_PROJECT_DIRECTOR_AS_KEY_PERSONNEL");
	public static final ConditionCode KEY_PERSONNEL_TITLE_ALREADY_EXISTS = new ConditionCode("KEY_PERSONNEL_TITLE_ALREADY_EXISTS");
	public static final ConditionCode CERTIFICATION_REQUIRED = new ConditionCode("CERTIFICATION_REQUIRED");
	public static final ConditionCode TITLE_STATE_PARTICIPATION_LEVEL_EXCEEDS_100 = new ConditionCode("TITLE_STATE_PARTICIPATION_LEVEL_EXCEEDS_100");
	public static final ConditionCode TITLE_STATE_PARTICIPATION_LEVEL_EXCEEDS_125 = new ConditionCode("TITLE_STATE_PARTICIPATION_LEVEL_EXCEEDS_125");
	public static final ConditionCode SUBSTANTIAL_PROGRESS_NOT_MET = new ConditionCode("SUBSTANTIAL_PROGRESS_NOT_MET");

	/**
	 * Some selected applications have completed budget periods.
	 */
	public static final ConditionCode APPLICATIONS_WITH_COMPLETE_BUDGET_PERIODS = new ConditionCode("APPLICATIONS_WITH_COMPLETE_BUDGET_PERIODS");
	
	/**
	 * The applications are not in the "In process" state or are in the "Do not process" state.
	 */
	public static final ConditionCode APPLICATIONS_NOT_IN_PROCESS = new ConditionCode("APPLICATIONS_NOT_IN_PROCESS");
	
	public static final ConditionCode ALL_APPLICATIONS_RECOMMENDED = new ConditionCode("ALL_APPLICATIONS_RECOMMENDED");
	
	/**
	 * The recommended amount has been entered
	 */
	public static final ConditionCode RECOMMENDED_AMOUNT_ENTERED = new ConditionCode("RECOMMENDED_AMOUNT_ENTERED");
	
	/**
	 * No supplements exist for this application and budget period.
	 */
	public static final ConditionCode NO_SUPPLEMENTS = new ConditionCode("NO_SUPPLEMENTS");
	
	/**
	 * The maximum number of budget periods have already been added.
	 */
	public static final ConditionCode BUDGET_PERIODS_LIMIT_REACHED = new ConditionCode("BUDGET_PERIODS_LIMIT_REACHED");

	/**
	 * At least one budget period is needed for an application.
	 */
	public static final ConditionCode BUDGET_PERIODS_NEEDED = new ConditionCode("BUDGET_PERIODS_NEEDED");

	/**
	 * At least one budget period is needed for an application.
	 */
	public static final ConditionCode BUDGET_PERIOD_NEEDS_END_DATE = new ConditionCode("BUDGET_PERIOD_NEEDS_END_DATE");

	/**
	 * At least one budget period is needed for an application.
	 */
	public static final ConditionCode BUDGET_PERIOD_NEEDS_START_DATE = new ConditionCode("BUDGET_PERIOD_NEEDS_START_DATE");

	/**
	 * At least one budget period is needed for an application.
	 */
	public static final ConditionCode PERF_PERD_FY_EXCEEDS_MAX = new ConditionCode("PERF_PERD_FY_EXCEEDS_MAX");

	/**
	 * At least one budget period is needed for an application.
	 */
	public static final ConditionCode PERF_PERD_END_DATE_EXCEEDS_MAX = new ConditionCode("PERF_PERD_END_DATE_EXCEEDS_MAX");

	/**
	 * The user said no to overwriting data.
	 */
	public static final ConditionCode OVERWRITE_NOT_ACCEPTED = new ConditionCode("OVERWRITE_NOT_ACCEPTED");

	/**
     * Indicates a SpecialPopulation NotApplicable   occurred
     */
    public static final ConditionCode NOT_APPLICABLE_CHECKED = new ConditionCode("NOT_APPLICABLE_CHECKED");
	
	public static final ConditionCode CONCURRENCY_ERROR = new ConditionCode("CONCURRENCY_ERROR");
	
	public static final ConditionCode PERIOD_NO_VALID_AMOUNT = new ConditionCode("PERIOD_NO_VALID_AMOUNT");

	
	//Subprogram
	public static final ConditionCode NO_DIVISIONS = new ConditionCode("NO_DIVISIONS");
	public static final ConditionCode INVALID_TYPE = new ConditionCode("INVALID_TYPE");
	
	// added for cfdaValidator in base page code.
	public static final ConditionCode ERROR_INVALID_CFDA = new ConditionCode("ERROR_INVALID_CFDA");
	
	
	/**
	 * Manage Formula
	 */
	
	public static final ConditionCode EDCONTACTS_NOT_ASSOCIATED_TO_SCHEDULE = new ConditionCode("EDCONTACTS_NOT_ASSOCIATED_TO_SCHEDULE");
	public static final ConditionCode ACCS_NOT_ASSOCIATED_TO_SCHEDULE = new ConditionCode("ACCS_NOT_ASSOCIATED_TO_SCHEDULE");
	public static final ConditionCode FORMULA_SCHEDULE_TOTAL_MISMATCH = new ConditionCode("FORMULA_SCHEDULE_TOTAL_MISMATCH");
	public static final ConditionCode FORMULA_ACCS_TOTAL_MISMATCH = new ConditionCode("FORMULA_ACCS_TOTAL_MISMATCH");
	public static final ConditionCode FORMULA_STATUS_NOT_RECIEVED_DUNS_CHANGED = new ConditionCode("FORMULA_STATUS_NOT_RECIEVED_DUNS_CHANGED");
	public static final ConditionCode FORMULA_WARNING_REAPPROVE_RECORD = new ConditionCode("FORMULA_WARNING_REAPPROVE_RECORD");
	public static final ConditionCode FORMULA_APPROVAL_DUNS_HIGHRISK_HRCOMPLETEIND_OFF = new ConditionCode("FORMULA_APPROVAL_DUNS_HIGHRISK_HRCOMPLETEIND_OFF");
	public static final ConditionCode FORMULA_APPROVAL_AWARD_HIGHRISK_HRCOMPLETEIND_OFF = new ConditionCode("FORMULA_APPROVAL_AWARD_HIGHRISK_HRCOMPLETEIND_OFF");
	public static final ConditionCode FORMULA_APPROVAL_EDPROGRAM_CONTACT_REQUIRED = new ConditionCode("FORMULA_APPROVAL_EDPROGRAM_CONTACT_REQUIRED");
	public static final ConditionCode FORMULA_APPROVAL_CMIA_TURNEDOFF = new ConditionCode("FORMULA_APPROVAL_CMIA_TURNEDOFF");
	public static final ConditionCode FORMULA_APPROVAL_PREVIOUSLY_APPROVED = new ConditionCode("FORMULA_APPROVAL_PREVIOUSLY_APPROVED");
	public static final ConditionCode FORMULA_WARNING_FUNDING_STATUS_PRINT = new ConditionCode("FORMULA_WARNING_FUNDING_STATUS_PRINT");
	public static final ConditionCode MAX_BUDGET_PERIODS_REACHED = new ConditionCode("MAX_BUDGET_PERIODS_REACHED");
	public static final ConditionCode PRAWARD_NO_BLANK = new ConditionCode("PRAWARD_NO_BLANK");
	public static final ConditionCode ERROR_ALLOTMENT_AMOUNT = new ConditionCode("ERROR_ALLOTMENT_AMOUNT");
	public static final ConditionCode ERROR_STUDENT_COUNT = new ConditionCode("ERROR_STUDENT_COUNT");
	public static final ConditionCode PRAWARD_NO_INVALID = new ConditionCode("PRAWARD_NO_INVALID");
	public static final ConditionCode PRAWARD_NO_NOT_ASSOCIATED_WITH_SCHEDULE = new ConditionCode("PRAWARD_NO_NOT_ASSOCIATED_WITH_SCHEDULE");
	public static final ConditionCode UPLOAD_ERRORS = new ConditionCode("UPLOAD_ERRORS");
	public static final ConditionCode UPLOAD_RECS = new ConditionCode("UPLOAD_RECS");
	public static final ConditionCode PRAWARD_NO_AND_ACCS_DUPLICATE = new ConditionCode("PRAWARD_NO_AND_ACCS_DUPLICATE");
	public static final ConditionCode ACCS_INVALID = new ConditionCode("ACCS_INVALID");
	public static final ConditionCode NO_END_DATE = new ConditionCode("NO_END_DATE");

	public static final ConditionCode MISSING_SEARCH_CRITERIA = new ConditionCode("MISSING_SEARCH_CRITERIA");
	public static final ConditionCode NO_SELECTION_MODIFY = new ConditionCode("NO_SELECTION_MODIFY");
	public static final ConditionCode NO_SELECTION_INQUIRE = new ConditionCode("NO_SELECTION_INQUIRE");

	public static final ConditionCode DUNS_NUMERIC = new ConditionCode("DUNS_NUMERIC");
	public static final ConditionCode DUNS_NOT_NULL_AND_NUMERIC = new ConditionCode("DUNS_NOT_NULL_AND_NUMERIC");

	public static final ConditionCode ALLOTMENT_AMOUNT_NUMERIC = new ConditionCode("ALLOTMENT_AMOUNT_NUMERIC");
	public static final ConditionCode ALLOTMENT_AMOUNT_CURRENCY = new ConditionCode("ALLOTMENT_AMOUNT_CURRENCY");
	public static final ConditionCode ALLOCATION_NOT_NULL = new ConditionCode("ALLOCATION_NOT_NULL");
	public static final ConditionCode STUDENT_COUNT_NOT_NULL = new ConditionCode("STUDENT_COUNT_NOT_NULL");
	public static final ConditionCode STUDENT_COUNT_NUMERIC = new ConditionCode("STUDENT_COUNT_NUMERIC");
	public static final ConditionCode FORMULA_RECORD_SELECTED_IMPROPERLY = new ConditionCode("FORMULA_RECORD_SELECTED_IMPROPERLY");

	public static final ConditionCode ALLOCATION_AMOUNT_CURRENCY = new ConditionCode("ALLOCATION_AMOUNT_CURRENCY");

	public static final ConditionCode SELECT_ONE_EDPROGRAMCONTACT = new ConditionCode("SELECT_ONE_EDPROGRAMCONTACT");
	public static final ConditionCode SELECT_ONE_POPULATIONS = new ConditionCode("SELECT_ONE_POPULATIONS");
	public static final ConditionCode SELECT_ONE_ED_CONTACT = new ConditionCode("SELECT_ONE_ED_CONTACT");
	public static final ConditionCode MAX_DESCRIPTORS = new ConditionCode("MAX_DESCRIPTORS");
	public static final ConditionCode NO_PAYEE_INFO = new ConditionCode("NO_PAYEE_INFO");
	public static final ConditionCode NO_GRANTEE_INFO = new ConditionCode("NO_GRANTEE_INFO");
	public static final ConditionCode NO_SUPPLEMENTS_INFO = new ConditionCode("NO_SUPPLEMENTS_INFO");
	public static final ConditionCode ERROR_FORMULA_RECORD_STATUS = new ConditionCode("ERROR_FORMULA_RECORD_STATUS");
	public static final ConditionCode ERROR_ATLEASTONECONTACT_REQUIRED = new ConditionCode("ERROR_ATLEASTONECONTACT_REQUIRED");
	public static final ConditionCode STATEAUTH_ALREADY_ASSIGNED = new ConditionCode("STATEAUTH_ALREADY_ASSIGNED");
	public static final ConditionCode STATEDIR_ALREADY_ASSIGNED = new ConditionCode("STATEDIR_ALREADY_ASSIGNED");
	
	//Federal Performance Period
	public static final ConditionCode PERF_PERIOD_EXCEEDS_MAX = new ConditionCode("PERF_PERIOD_EXCEEDS_MAX");
	public static final ConditionCode PERF_PERIOD_DATES_INCONSISTENT = new ConditionCode("PERF_PERIOD_DATES_INCONSISTENT");

	//Budget Period Validation
	public static final ConditionCode MIN_BUDGET_PERIODS = new ConditionCode("MIN_BUDGET_PERIODS");
	public static final ConditionCode BUDGET_PERIOD_START_DATE = new ConditionCode("BUDGET_PERIOD_START_DATE");
	public static final ConditionCode BUDGET_PERIOD_END_DATE = new ConditionCode("BUDGET_PERIOD_END_DATE");
	public static final ConditionCode BUDGET_PERIODS_FY_SEQUENTIAL = new ConditionCode("BUDGET_PERIODS_FY_SEQUENTIAL");
	public static final ConditionCode BUDGET_PERIODS_SEQUENTIAL = new ConditionCode("BUDGET_PERIODS_SEQUENTIAL");
	public static final ConditionCode MAX_BUDGET_FISCAL_YEAR = new ConditionCode("MAX_BUDGET_FISCAL_YEAR");
	public static final ConditionCode MAX_PERF_END = new ConditionCode("MAX_PERF_END");
	public static final ConditionCode CFDA_SUBPRGM = new ConditionCode("CFDA_SUBPRGM");

	/**
	 * Indicates that the Schedule selected cannot be modified.
	 */
	public static final ConditionCode FORMULA_SCHEDULE_NOT_MODIFIABLE = new ConditionCode("FORMULA_SCHEDULE_NOT_MODIFIABLE");
	public static final ConditionCode TEMPLATE_NOT_AVAILABLE = new ConditionCode("TEMPLATE_NOT_AVAILABLE");
	public static final ConditionCode SUBPRGM_NOT_EXISTS = new ConditionCode("SUBPRGM_NOT_EXISTS");
	
	public static final ConditionCode EMAIL_ALREADY_EXISTS = new ConditionCode("EMAIL_ALREADY_EXISTS");
	public static final ConditionCode PHONE_ALREADY_EXISTS = new ConditionCode("PHONE_ALREADY_EXISTS");
	public static final ConditionCode DONE_ADDING_POS = new ConditionCode("DONE_ADDING_POS");
	public static final ConditionCode DONE_ADDING_POS_DIVS = new ConditionCode("DONE_ADDING_PO_DIVS");
	public static final ConditionCode PO_ALREADY_ADDED = new ConditionCode("PO_ALREADY_ADDED");
	public static final ConditionCode PO_DIV_ALREADY_ADDED = new ConditionCode("PO_DIV_ALREADY_ADDED");
	public static final ConditionCode MISSING_REQUIRED_FIELDS = new ConditionCode("MISSING_REQUIRED_FIELDS");
	public static final ConditionCode USER_NOT_FOUND = new ConditionCode("USER_NOT_FOUND");
	public static final ConditionCode CANNOT_REGISTER_USER = new ConditionCode("CANNOT_REGISTER_USER");
	public static final ConditionCode USER_ALREADY_REGISTERED = new ConditionCode("USER_ALREADY_REGISTERED");
	public static final ConditionCode INVALID_ED_SCORE = new ConditionCode("INVALID_ED_SCORE");
	public static final ConditionCode INVALID_REVIEWER_SCORE = new ConditionCode("INVALID_REVIEWER_SCORE");
	public static final ConditionCode INVALID_PRIORITY_SCORE = new ConditionCode("INVALID_PRIORITY_SCORE");
	public static final ConditionCode INVALID_COMMENTS_LENGTH = new ConditionCode("INVALID_COMMENTS_LENGTH");
	public static final ConditionCode UPDATE_SUCCESSFUL = new ConditionCode("UPDATE_SUCCESSFUL");
	public static final ConditionCode UNMODIFIABLE_CFDA = new ConditionCode("UNMODIFIABLE_CFDA");
	public static final ConditionCode UNDELTABLE_CFDA_SUB = new ConditionCode("UNDELTABLE_CFDA_SUB");
	public static final ConditionCode UNDELTABLE_CFDA_APP = new ConditionCode("UNDELTABLE_CFDA_APP");
	public static final ConditionCode ALREADY_HAS_OFFSET = new ConditionCode("ALREADY_HAS_OFFSET");
	public static final ConditionCode CANT_MODIFY_CANCELLED_OFFSET = new ConditionCode("CANT_MODIFY_CANCELLED_OFFSET");
	public static final ConditionCode CANT_INQUIRE_CANCELLED_OFFSET = new ConditionCode("CANT_INQUIRE_CANCELLED_OFFSET");
	public static final ConditionCode NO_OFFSET = new ConditionCode("NO_OFFSET");
	public static final ConditionCode OFFSET_ALREADY_CANCELLED = new ConditionCode("OFFSET_ALREADY_CANCELLED");
	public static final ConditionCode ADD_PANEL_SUCESS = new ConditionCode("ADD_PANEL_SUCESS");
	public static final ConditionCode DELETE_PANEL_SUCESS = new ConditionCode("DELETE_PANEL_SUCESS");
	public static final ConditionCode UPDATE_PANEL_SUCESS = new ConditionCode("UPDATE_PANEL_SUCESS");
	public static final ConditionCode UPDATE_PANEL_SUCESS_APP = new ConditionCode("UPDATE_PANEL_SUCESS_APP");
	public static final ConditionCode COMPETITION_TIER_NULL = new ConditionCode("COMPETITION_TIER_NULL");
	public static final ConditionCode AWARD_NOT_DELETED = new ConditionCode("AWARD_NOT_DELETED");
	public static final ConditionCode AWARD_ALREADY_DELETED = new ConditionCode("AWARD_ALREADY_DELETED");
	public static final ConditionCode HIGH_RISK_AWARD_EXISTS = new ConditionCode("HIGH_RISK_AWARD_EXISTS");
	public static final ConditionCode HIGH_RISK_AWARD_ACTIVE = new ConditionCode("HIGH_RISK_AWARD_ACTIVE");
	public static final ConditionCode HIGH_RISK_RECORD_EXISTS = new ConditionCode("HIGH_RISK_RECORD_EXISTS");
	public static final ConditionCode HIGH_RISK_RECORD_ACTIVE = new ConditionCode("HIGH_RISK_RECORD_ACTIVE");
	public static final ConditionCode FILE_NOT_SELECTED = new ConditionCode("FILE_NOT_SELECTED");
	public static final ConditionCode DUNS_DOES_NOT_EXIST = new ConditionCode("DUNS_DOES_NOT_EXIST");
	public static final ConditionCode INACTIVE_DUNS = new ConditionCode("INACTIVE_DUNS");
	public static final ConditionCode NON_HIGH_RISK_DUNS = new ConditionCode("NON_HIGH_RISK_DUNS");
	public static final ConditionCode INVALID_DUNS_AWARD_ASSOCIATION = new ConditionCode("INVALID_DUNS_AWARD_ASSOCIATION");
	public static final ConditionCode INACTIVE_HIGH_RISK_AWARD = new ConditionCode("INACTIVE_HIGH_RISK_AWARD");
	public static final ConditionCode NON_HIGH_RISK_AWARD = new ConditionCode("NON_HIGH_RISK_AWARD");
	
	/**
	 * File upload conditions
	 */
	public static final ConditionCode UPLOAD_SUCCESS = new ConditionCode("UPLOAD_SUCCESS");
	public static final ConditionCode INFECTED_FILE = new ConditionCode("INFECTED_FILE");
	public static final ConditionCode INVALID_FILE = new ConditionCode("INVALID_FILE");
	public static final ConditionCode INCORRECT_FILE_FORMAT = new ConditionCode("INCORRECT_FILE_FORMAT");
	public static final ConditionCode INVALID_FILE_TYPE = new ConditionCode("INVALID_FILE_TYPE");

	/** Ends **/
	
	/* ---------------------------------------------------------------------------------------------------
	 *  Conduct Application Review
	 * ---------------------------------------------------------------------------------------------------
	 */
	public static final ConditionCode PANEL_END_DATE = new ConditionCode("PANEL_END_DATE");

	/* ---------------------------------------------------------------------------------------------------
	 *  Analyze and standardize scores
	 * ---------------------------------------------------------------------------------------------------
	 */
	public static final ConditionCode NO_REVIEWERS = new ConditionCode("NO_REVIEWERS");
	public static final ConditionCode NO_HEADER_ROW = new ConditionCode("NO_HEADER_ROW");
	public static final ConditionCode REVIEWER_SCORES_MISMATCH = new ConditionCode("REVIEWER_SCORES_MISMATCH");
	public static final ConditionCode ERROR_REVIEWER_SCORE = new ConditionCode("ERROR_REVIEWER_SCORE");

	
	
	
	
	/* ---------------------------------------------------------------------------------------------------
	 *  My Home Page
	 * ---------------------------------------------------------------------------------------------------
	 */
	public static final ConditionCode TIER_NO_SELECTED_IMPROPERLY = new ConditionCode("TIER_NO_SELECTED_IMPROPERLY");
	public static final ConditionCode DELETE = new ConditionCode("DELETE");
	public static final ConditionCode START_DATE_ERROR = new ConditionCode("START_DATE_ERROR");
	public static final ConditionCode DURATION_ERROR = new ConditionCode("DURATION_ERROR");
	
	/**
	 * Condition code for Partial address fields
	 */
	public static final ConditionCode PARTIAL_ADDRESS = new ConditionCode("PARTIAL_ADDRESS");
	
	/**
	 * Maintain People
	 */
	public static final ConditionCode DUPLICATE_FIRST_NAME = new ConditionCode("DUPLICATE_FIRST_NAME");
	public static final ConditionCode DUPLICATE_LAST_NAME = new ConditionCode("DUPLICATE_LAST_NAME");
	public static final ConditionCode DUPLICATE_PHONE = new ConditionCode("DUPLICATE_PHONE");
	public static final ConditionCode DUPLICATE_EMAIL = new ConditionCode("DUPLICATE_EMAIL");
	
	public static final ConditionCode ERROR_PRIMARY_ALTERNATE_EMAILS_MATCH = new ConditionCode("ERROR_PRIMARY_ALTERNATE_EMAILS_MATCH");
	//DMR 3439 -
	public static final ConditionCode DUPLICATE_REQUEST = new ConditionCode("DUPLICATE_REQUEST");
	
	/* ---------------------------------------------------------------------------------------------------
	 *  Maintain High Risk
	 * ---------------------------------------------------------------------------------------------------
	 */
	public static final ConditionCode END_DATE_B4_START_DATE = new ConditionCode("END_DATE_B4_START_DATE");
	public static final ConditionCode REVISED_END_DATE_B4_START_DATE = new ConditionCode("REVISED_END_DATE_B4_START_DATE");
	public static final ConditionCode DUNS_NOT_DELETED = new ConditionCode("DUNS_NOT_DELETED");
	public static final ConditionCode DUNS_CANNOT_BE_UNDELETED = new ConditionCode("DUNS_CANNOT_BE_UNDELETED");
	public static final ConditionCode DUNS_ALREADY_DELETED = new ConditionCode("DUNS_ALREADY_DELETED");
	public static final ConditionCode DUPLICATE_HIGH_RISK_AWARD_EXISTS = new ConditionCode("DUPLICATE_HIGH_RISK_AWARD_EXISTS");
	public static final ConditionCode AWARD_CANNOT_BE_UNDELETED = new ConditionCode("AWARD_CANNOT_BE_UNDELETED");
	public static final ConditionCode NO_AWARDS_SELECTED = new ConditionCode("NO_AWARDS_SELECTED");
	
	/* ---------------------------------------------------------------------------------------------------
	 * Maintain Supplement
	 * ---------------------------------------------------------------------------------------------------
	 */
	public static final ConditionCode TOTAL_SUPPLEMENT_AMOUNT_REQUIRED  = new ConditionCode("TOTAL_SUPPLEMENT_AMOUNT_REQUIRED");
	public static final ConditionCode TOTAL_SUPPLEMENT_AMOUNT_EXCEEDS  = new ConditionCode("TOTAL_SUPPLEMENT_AMOUNT_EXCEEDS");
	public static final ConditionCode ACCS_SUPPLEMENT_AMOUNT_EXCEEDS  = new ConditionCode("ACCS_SUPPLEMENT_AMOUNT_EXCEEDS");
    public static final ConditionCode SUPPLEMENT_AMOUNT_ZERO = new ConditionCode("SUPPLEMENT_AMOUNT_ZERO");
    public static final ConditionCode SUPPLEMENT_AMOUNT_MISMATCH = new ConditionCode("SUPPLEMENT_AMOUNT_MISMATCH");
	public static final ConditionCode SUPPLEMENT_MASS_CREATE_APPLY_SUPPLEMENT  = new ConditionCode("SUPPLEMENT_MASS_CREATE_APPLY_SUPPLEMENT");
	public static final ConditionCode NAGATIVE_SUPPLEMENT_PERCENT_NAGATIVE_VALUE  = new ConditionCode("NAGATIVE_SUPPLEMENT_PERCENT_NAGATIVE_VALUE");
	public static final ConditionCode NAGATIVE_SUPPLEMENT_AMOUNT_NAGATIVE_VALUE  = new ConditionCode("NAGATIVE_SUPPLEMENT_AMOUNT_NAGATIVE_VALUE");
	public static final ConditionCode NAGATIVE_SUPPLEMENT_TOTALLIMIT_NAGATIVE_VALUE  = new ConditionCode("NAGATIVE_SUPPLEMENT_TOTALLIMIT_NAGATIVE_VALUE");
	public static final ConditionCode SUPPLEMENT_BUDGET_PERIOD_ACCS_NOT_ASSOCIATED =  new ConditionCode("SUPPLEMENT_BUDGET_PERIOD_ACCS_NOT_ASSOCIATED");
	
	public static final ConditionCode SUPPLEMENT_NOPREVILEGE  = new ConditionCode("SUPPLEMENT_NOPREVILEGE"); 
	public static final ConditionCode SUPPLEMENT_STATUS_NOTELIGIBLE  = new ConditionCode("SUPPLEMENT_STATUS_NOTELIGIBLE");
	public static final ConditionCode MS_MODIFY_SUPPLEMENT_EXCEED  = new ConditionCode("MS_MODIFY_SUPPLEMENT_EXCEED");	
	public static final ConditionCode MS_CREATE_SUPPLEMENT_EXCEED  = new ConditionCode("MS_CREATE_SUPPLEMENT_EXCEED"); 
	public static final ConditionCode MS_AWARD_PENDING_ACTION  = new ConditionCode("MS_AWARD_PENDING_ACTION");	
	public static final ConditionCode SUPPLEMENT_NOTSELECTED_DELETE  = new ConditionCode("SUPPLEMENT_NOTSELECTED_DELETE");
	public static final ConditionCode SUPPLEMENT_NOTSELECTED_MODIFY  = new ConditionCode("SUPPLEMENT_NOTSELECTED_MODIFY");
	public static final ConditionCode MS_CREATESUPP_FELLOWSHIPAWARDEXCEPTION  = new ConditionCode("MS_CREATESUPP_FELLOWSHIPAWARDEXCEPTION");
	public static final ConditionCode MS_CREATESUPP_NONDISCORFORMULAEXCEPTION  = new ConditionCode("MS_CREATESUPP_NONDISCORFORMULAEXCEPTION");
	public static final ConditionCode MS_CREATESUPP_APPLICATIONNOTOBLIGATEDEXCEPTION  = new ConditionCode("MS_CREATESUPP_APPLICATIONNOTOBLIGATEDEXCEPTION");
	public static final ConditionCode MS_CREATESUPP_AWARDNOTOPENEXCEPTION  = new ConditionCode("MS_CREATESUPP_AWARDNOTOPENEXCEPTION");
	public static final ConditionCode MS_CREATESUPP_AWARDNOTINPROCESSEXCEPTION  = new ConditionCode("MS_CREATESUPP_AWARDNOTINPROCESSEXCEPTION");
	public static final ConditionCode MS_CREATESUPP_BPNOTCURRENTORPREVEXCEPTION  = new ConditionCode("MS_CREATESUPP_BPNOTCURRENTORPREVEXCEPTION");
	public static final ConditionCode MS_CREATESUPP_NOPREVILEGEEXCEPTION  = new ConditionCode("MS_CREATESUPP_NOPREVILEGEEXCEPTION");
	public static final ConditionCode TOTAL_SUPPLEMENT_AMOUNT_NEED_NEGATIVE  = new ConditionCode("TOTAL_SUPPLEMENT_AMOUNT_NEED_NEGATIVE");
	public static final ConditionCode MS_FELLOW_BUDGETAMT_NEGATIVE  = new ConditionCode("MS_FELLOW_BUDGETAMT_NEGATIVE");
	public static final ConditionCode MS_FELLOW_DATE_NULL  = new ConditionCode("MS_FELLOW_DATE_NULL");
	public static final ConditionCode MS_AWARDINELIGBLE_EXCEPTION  = new ConditionCode("MS_AWARDINELIGBLE_EXCEPTION");
	public static final ConditionCode MS_EDITSUPP_ACCS_SUPPLEMENT_AMOUNT_NEED_NEGATIVE  = new ConditionCode("MS_EDITSUPP_ACCS_SUPPLEMENT_AMOUNT_NEED_NEGATIVE");
	public static final ConditionCode MS_POSITIVESUPPLEMENT_PRINTED_STATUS_EXCEPTION  = new ConditionCode("MS_POSITIVESUPPLEMENT_PRINTED_STATUS_EXCEPTION");
	public static final ConditionCode MS_EDITSUPP_ACCS_SUPPLEMENT_AMOUNT_NEED_POSITIVE  = new ConditionCode("MS_EDITSUPP_ACCS_SUPPLEMENT_AMOUNT_NEED_POSITIVE");
	public static final ConditionCode MS_EDITSUPP_FELLOWS_SUPPLEMENT_AMOUNT_NEED_POSITIVE  = new ConditionCode("MS_EDITSUPP_FELLOWS_SUPPLEMENT_AMOUNT_NEED_POSITIVE");
	public static final ConditionCode MS_EDITSUPP_FELLOWS_SUPPLEMENT_AMOUNT_NEED_NEGATIVE  = new ConditionCode("MS_EDITSUPP_FELLOWS_SUPPLEMENT_AMOUNT_NEED_NEGATIVE");
	public static final ConditionCode MS_AMOUNT_NEED_NEGATIVE  = new ConditionCode("MS_AMOUNT_NEED_NEGATIVE");
	public static final ConditionCode MS_AMOUNT_NEED_POSITIVE  = new ConditionCode("MS_AMOUNT_NEED_POSITIVE");
	public static final ConditionCode MS_CALCULATE_AMOUNTFIELD_REQUIRED  = new ConditionCode("MS_CALCULATE_AMOUNTFIELD_REQUIRED");
	public static final ConditionCode MS_SEARCH_EITHERAPPLICANTNAMEORORGNAME  = new ConditionCode("MS_SEARCH_EITHERAPPLICANTNAMEORORGNAME");
	public static final ConditionCode MS_COMMENTS_MAXLENGTH_CONSTRAINT  = new ConditionCode("MS_COMMENTS_MAXLENGTH_CONSTRAINT");
	public static final ConditionCode MS_NEWSUPP_MODIFYSUPPLEMENT_AMOUNT_NEED_PSOITIVE  = new ConditionCode("MS_NEWSUPP_MODIFYSUPPLEMENT_AMOUNT_NEED_PSOITIVE");
	public static final ConditionCode MS_NEWSUPP_MODIFYSUPPLEMENT_AMOUNT_NEED_NEGATIVE  = new ConditionCode("MS_NEWSUPP_MODIFYSUPPLEMENT_AMOUNT_NEED_NEGATIVE");
	public static final ConditionCode MS_NEWSUPP_CREATESUPPLEMENT_AMOUNT_NEED_NEGATIVE  = new ConditionCode("MS_NEWSUPP_CREATESUPPLEMENT_AMOUNT_NEED_NEGATIVE");
	public static final ConditionCode MS_NEWSUPP_CREATESUPPLEMENT_AMOUNT_NEED_PSOITIVE  = new ConditionCode("MS_NEWSUPP_CREATESUPPLEMENT_AMOUNT_NEED_PSOITIVE");
	public static final ConditionCode MS_NEWSUPP_MODIFYSUPPLEMENT_NEEDAWARDSTATUS_APRJ  = new ConditionCode("MS_NEWSUPP_MODIFYSUPPLEMENT_NEEDAWARDSTATUS_APRJ");
	public static final ConditionCode MS_NEWSUPP_CREATEMASSSUPPLEMENT_NEEDAWARDSTATUS_APRJ  = new ConditionCode("MS_NEWSUPP_CREATEMASSSUPPLEMENT_NEEDAWARDSTATUS_APRJ");
	
	
	
	/* ---------------------------------------------------------------------------------------------------
	 *  Award Notification
	 * ---------------------------------------------------------------------------------------------------
	 */		
	public static final ConditionCode AN_CLAUSE_DELETE_VALIDATION = new ConditionCode("AN_CLAUSE_DELETE_VALIDATION");
	public static final ConditionCode AN_ATTACHMENT_DELETE_VALIDATION = new ConditionCode("AN_ATTACHMENT_DELETE_VALIDATION");	
	public static final ConditionCode AN_CLAUSE_NONSUPPLEMENT_NCC_VALIDATION = new ConditionCode("AN_CLAUSE_NONSUPPLEMENT_NCC_VALIDATION");	
	public static final ConditionCode AN_CLAUSE_NONSUPPLEMENT_NC_VALIDATION = new ConditionCode("AN_CLAUSE_NONSUPPLEMENT_NC_VALIDATION");	
	public static final ConditionCode AN_CLAUSE_SUPPLEMENT_ALL_VALIDATION = new ConditionCode("AN_CLAUSE_SUPPLEMENT_ALL_VALIDATION");	
	public static final ConditionCode AN_CLAUSE_STANDARDCLAUSE_PO_VALIDATION = new ConditionCode("AN_CLAUSE_STANDARDCLAUSE_PO_VALIDATION");
	public static final ConditionCode AN_DELETE_FAILED = new ConditionCode("AN_DELETE_FAILED");
	public static final ConditionCode AN_DETAILS_GET_FAILED = new ConditionCode("AN_DETAILS_GET_FAILED");
	public static final ConditionCode AN_CLAUSE_CREATED_SUCCESS = new ConditionCode("AN_CLAUSE_CREATED_SUCCESS");
	public static final ConditionCode AN_ATTACHMENT_CREATED_SUCCESS = new ConditionCode("AN_ATTACHMENT_CREATED_SUCCESS");
	public static final ConditionCode AN_CLAUSE_MODIFIED_SUCCESS = new ConditionCode("AN_CLAUSE_MODIFIED_SUCCESS");
	public static final ConditionCode AN_ATTACHMENT_MODIFIED_SUCCESS = new ConditionCode("AN_ATTACHMENT_MODIFIED_SUCCESS");
	public static final ConditionCode AN_CLUASE_DELETE_SUCCESS = new ConditionCode("AN_CLUASE_DELETE_SUCCESS");
	public static final ConditionCode AN_CLAUSEANDATTACHMENT_MODIFYDELETE_PO_VALIDATION = new ConditionCode("AN_CLAUSEANDATTACHMENT_MODIFYDELETE_PO_VALIDATION");
	public static final ConditionCode AN_ATTACHMENT_ATTACHMENTID_EXISTS = new ConditionCode("AN_ATTACHMENT_ATTACHMENTID_EXISTS");
	public static final ConditionCode AN_ATTACHMENT_WARNING_ASSOCIATED_TO_AWARD = new ConditionCode("AN_ATTACHMENT_WARNING_ASSOCIATED_TO_AWARD");
	public static final ConditionCode AN_CLAUSE_WARNING_ASSOCIATED_TO_AWARD = new ConditionCode("AN_CLAUSE_WARNING_ASSOCIATED_TO_AWARD");
	public static final ConditionCode AN_COMMIT_APPLICATION_IN_DECOMMIT = new ConditionCode("AN_COMMIT_APPLICATION_IN_DECOMMIT");
	public static final ConditionCode AN_COMMIT_REJECT_APPLICATION_IN_APPROVED = new ConditionCode("AN_COMMIT_REJECT_APPLICATION_IN_APPROVED");
	public static final ConditionCode AN_COMMIT_REJECT_APPLICATION_IN_ROLLEDOVER_OR_REJECTED = new ConditionCode("AN_COMMIT_REJECT_APPLICATION_IN_ROLLEDOVER_OR_REJECTED");
	public static final ConditionCode AN_CLAUSE_ASSOCIATED_AWARDS_MODIFIED = new ConditionCode("AN_CLAUSE_ASSOCIATED_AWARDS_MODIFIED");
	public static final ConditionCode AN_ATTACHMENT_ASSOCIATED_AWARDS_MODIFIED = new ConditionCode("AN_ATTACHMENT_ASSOCIATED_AWARDS_MODIFIED");
	public static final ConditionCode AN_CLAUSE_EFFECTED_AWARDS_ADDED = new ConditionCode("AN_CLAUSE_EFFECTED_AWARDS_ADDED");
	public static final ConditionCode AN_ATTACHMENT_EFFECTED_AWARDS_ADDED = new ConditionCode("AN_ATTACHMENT_EFFECTED_AWARDS_ADDED");
	public static final ConditionCode AN_CLAUSE_ASSOCIATED_AWARDS_DELETED = new ConditionCode("AN_CLAUSE_ASSOCIATED_AWARDS_DELETED");
	public static final ConditionCode AN_ATTACHMENT_ASSOCIATED_AWARDS_DELETED = new ConditionCode("AN_ATTACHMENT_ASSOCIATED_AWARDS_DELETED");
	public static final ConditionCode AN_CLAUSE_CFDA_ADDED = new ConditionCode("AN_CLAUSE_CFDA_ADDED");	
	
	
	
	public static final ConditionCode AN_GCN_CONGRESSIONAL_DISTRICT_ASSOCIATED_WITH_REPRESENTATIVE = new ConditionCode("AN_GCN_CONGRESSIONAL_DISTRICT_ASSOCIATED_WITH_REPRESENTATIVE");
	public static final ConditionCode AN_GCN_SENATOR_ALREADY_EXISTS = new ConditionCode("AN_GCN_SENATOR_ALREADY_EXISTS");
	public static final ConditionCode AN_GCN_REPRESENTATIVE_ALREADY_EXISTS = new ConditionCode("AN_GCN_REPRESENTATIVE_ALREADY_EXISTS");
	public static final ConditionCode AN_GCN_REPRESENTATIVE_STATECONGCODE_MISMATCH = new ConditionCode("AN_GCN_REPRESENTATIVE_STATECONGCODE_MISMATCH");
	public static final ConditionCode AN_GCN_MAX_SENATOR_PER_STATE_REACHED = new ConditionCode("AN_GCN_MAX_SENATOR_PER_STATE_REACHED");
	public static final ConditionCode AN_GCN_REPRESENTATIVE_VACANT_NAME_VALIDATION = new ConditionCode("AN_GCN_REPRESENTATIVE_VACANT_NAME_VALIDATION");
	
	public static final ConditionCode GAN_FIELDS_MISSING=new ConditionCode("GAN_FIELDS_MISSING");
	public static final ConditionCode GAN_FUNDING_STATUS_NOT_CORRECT=new ConditionCode("GAN_FUNDING_STATUS_NOT_CORRECT");
	public static final ConditionCode GAN_DOCUMENT_FAILED_TO_GENERATE= new ConditionCode("GAN_DOCUMENT_FAILED_TO_GENERATE");
	public static final ConditionCode GAN_ATTACHMENTS_NOT_FOUND= new ConditionCode("GAN_ATTACHMENTS_NOT_FOUND");
	
	// for Maintain Clause 
	public static final ConditionCode MC_UPLOAD = new ConditionCode("MC_UPLOAD");
	public static final ConditionCode MC_ERROR_LOGS = new ConditionCode("MC_ERROR_LOGS");
	public static final ConditionCode MC_NOSELECTION_FORDELETE = new ConditionCode("MC_NOSELECTION_FORDELETE");
	public static final ConditionCode MC_NOSELECTION_FORMODIFY = new ConditionCode("MC_NOSELECTION_FORMODIFY");
	public static final ConditionCode MC_CLAUSETEXT_LENGTH_EXCEEDED = new ConditionCode("MC_CLAUSETEXT_LENGTH_EXCEEDED");
	public static final ConditionCode MC_NOSELECTION_FORVIEWHISTORY = new ConditionCode("MC_NOSELECTION_FORVIEWHISTORY");
	
	
	
	
	
	
	
	
	// Condition Codes  to Maintain Congressional Data 
	public static final ConditionCode MCD_CANCEL_EDITVIEW_SENATOR= new ConditionCode("MCD_CANCEL_EDITVIEW_SENATOR");
	public static final ConditionCode MCD_CANCEL_EDITVIEW_REPS= new ConditionCode("MCD_CANCEL_EDITVIEW_REPS");
	public static final ConditionCode MCD_CANCEL_EDITVIEW_SENATOR_OTHERCLICK= new ConditionCode("MCD_CANCEL_EDITVIEW_SENATOR_OTHERCLICK");
	public static final ConditionCode MCD_CANCEL_EDITVIEW_REPS_OTHERCLICK= new ConditionCode("MCD_CANCEL_EDITVIEW_REPS_OTHERCLICK");
	public static final ConditionCode MCD_CANCEL_EDITVIEW_SENATOR_TOGO= new ConditionCode("MCD_CANCEL_EDITVIEW_SENATOR_TOGO");
	public static final ConditionCode MCD_CANCEL_EDITVIEW_REPS_TOGO= new ConditionCode("MCD_CANCEL_EDITVIEW_REPS_TOGO");
	public static final ConditionCode MCD_EMAILTEXT_MAXLENGTH_CONSTRAINT= new ConditionCode("MCD_EMAILTEXT_MAXLENGTH_CONSTRAINT");
	public static final ConditionCode ERROR_MCD_SENATOR_UPDATE_NM= new ConditionCode("ERROR_MCD_SENATOR_UPDATE_NM");
	public static final ConditionCode ERROR_MCD_REPS_UPDATE_NM= new ConditionCode("ERROR_MCD_REPS_UPDATE_NM");
	public static final ConditionCode SENATOR_UPDATE_ERROR_INVALID_EMAIL_ADDRESS= new ConditionCode("SENATOR_UPDATE_ERROR_INVALID_EMAIL_ADDRESS");
	public static final ConditionCode SENATOR_UPDATE_ERROR_REQUIRED_EMAIL_ADDRESS= new ConditionCode("SENATOR_UPDATE_ERROR_REQUIRED_EMAIL_ADDRESS");
	public static final ConditionCode SENATOR_ADD_ERROR_REQUIRED_EMAIL_ADDRESS= new ConditionCode("SENATOR_ADD_ERROR_REQUIRED_EMAIL_ADDRESS");
	public static final ConditionCode SENATOR_ADD_ERROR_PRIMARY_SEC_EMAILSAME= new ConditionCode("SENATOR_ADD_ERROR_PRIMARY_SEC_EMAILSAME");	
	public static final ConditionCode REPS_UPDATE_ERROR_INVALID_EMAIL_ADDRESS= new ConditionCode("REPS_UPDATE_ERROR_INVALID_EMAIL_ADDRESS");
	public static final ConditionCode SENATOR_UPDATE_ERROR_PRIMARY_SEC_EMAILSAME= new ConditionCode("SENATOR_UPDATE_ERROR_PRIMARY_SEC_EMAILSAME");
	
	public static final ConditionCode MCD_UPLOAD= new ConditionCode("MCD_UPLOAD");
	public static final ConditionCode MCD_UPLOAD_WRONGFILETYPE= new ConditionCode("MCD_UPLOAD_WRONGFILETYPE");	
	public static final ConditionCode MCD_REPS_NOSELECTION_FORDELETE= new ConditionCode("MCD_REPS_NOSELECTION_FORDELETE");
	public static final ConditionCode MCD_SENATOR_NOSELECTION_FORDELETE= new ConditionCode("MCD_SENATOR_NOSELECTION_FORDELETE");
	public static final ConditionCode MCD_UPDATE_REPS_CONGRESSIONAL_CODE_ERROR= new ConditionCode("MCD_UPDATE_REPS_CONGRESSIONAL_CODE_ERROR");
	public static final ConditionCode MCD_ADD_REPS_CONGRESSIONAL_CODE_ERROR= new ConditionCode("MCD_ADD_REPS_CONGRESSIONAL_CODE_ERROR");
	public static final ConditionCode REPS_ADD_ERROR_PRIMARY_SEC_EMAILSAME= new ConditionCode("REPS_ADD_ERROR_PRIMARY_SEC_EMAILSAME");
	public static final ConditionCode REPS_UPDATE_ERROR_REQUIRED_EMAIL_ADDRESS= new ConditionCode("REPS_UPDATE_ERROR_REQUIRED_EMAIL_ADDRESS");
	public static final ConditionCode REPS_UPDATE_ERROR_PRIMARY_SEC_EMAILSAME= new ConditionCode("REPS_UPDATE_ERROR_PRIMARY_SEC_EMAILSAME");
	
	
	
	
	
	
	
	
	

	
	
	
	public static final ConditionCode PREPAREAWARD_NO_GRANTEE= new ConditionCode("PREPAREAWARD_NO_GRANTEE");
	public static final ConditionCode PENDING_ACTIONS_EXIST= new ConditionCode("PENDING_ACTIONS_EXIST");
	public static final ConditionCode NO_ACTIVE_SCHEDULE= new ConditionCode("NO_ACTIVE_SCHEDULE");
	public static final ConditionCode BUDGET_FY_NOT_EXIST= new ConditionCode("BUDGET_FY_NOT_EXIST");
	public static final ConditionCode CLAUSE_NOT_DELETABLE= new ConditionCode("CLAUSE_NOT_DELETABLE");
	public static final ConditionCode DUNS_NOT_MODIFIED= new ConditionCode("DUNS_NOT_MODIFIED");
	public static final ConditionCode BUDGET_FISCAL_YEAR= new ConditionCode("BUDGET_FISCAL_YEAR");
	public static final ConditionCode NO_ACTIVE_BANK_ACCOUNT= new ConditionCode("NO_ACTIVE_BANK_ACCOUNT");
	public static final ConditionCode REASSIGNMENT_DATE_CHANGE= new ConditionCode("REASSIGNMENT_DATE_CHANGE");
	public static final ConditionCode REASSIGNMENT_PAST_DATE= new ConditionCode("REASSIGNMENT_PAST_DATE");
	public static final ConditionCode PAYEE_IS_NOT_ATTACHED= new ConditionCode("PAYEE_IS_NOT_ATTACHED");
	
	// signifies a failure in the condition code.
	public static final ConditionCode FMSS_POST_FAILURE = new ConditionCode("FMSS_POST_FAILURE");
//   signifies a failure in the condition code.
    public static final ConditionCode FMSS_POST_SUCCESS = new ConditionCode("FMSS_POST_SUCCESS");
    
	
	
	/**
	 * Maintain Electronic Form
	 */
	public static final ConditionCode UPLOAD_DOCUMENT_REQUIRED = new ConditionCode("UPLOAD_DOCUMENT_REQUIRED");
	public static final ConditionCode DUPLICATE_DOCUMENT_FILENAME = new ConditionCode("DUPLICATE_DOCUMENT_FILENAME");
	public static final ConditionCode TITLE_ALREADY_EXIST = new ConditionCode("TITLE_ALREADY_EXIST");
	public static final ConditionCode INVALID_FORM_TYPE = new ConditionCode("INVALID_FORM_TYPE");
	public static final ConditionCode MAX_DESCRIPTION_LENGTH = new ConditionCode("MAX_DESCRIPTION_LENGTH");
	public static final ConditionCode MAX_COMMENTS_LENGTH = new ConditionCode("MAX_COMMENTS_LENGTH");
	public static final ConditionCode FUTURE_PAYEE_EXISTS = new ConditionCode("FUTURE_PAYEE_EXISTS");
	/* ---------------------------------------------------------------------------------------------------
	 *  Subledger
	 * ---------------------------------------------------------------------------------------------------
	 */	
	public static final ConditionCode START_DATE_WITHOUT_END_DATE= new ConditionCode("START_DATE_WITHOUT_END_DATE");
	public static final ConditionCode ERROR_INVALID_FUND_CODE= new ConditionCode("ERROR_INVALID_FUND_CODE");
	public static final ConditionCode ERROR_INVALID_ACCS_LIMITATION= new ConditionCode("ERROR_INVALID_ACCS_LIMITATION");
	public static final ConditionCode ERROR_INVALID_GL_ACCOUNT_NUMBER= new ConditionCode("ERROR_INVALID_GL_ACCOUNT_NUMBER");
	public static final ConditionCode INQUIRE_DEBIT_CREDIT_SEARCH_TYPE_SELECT = new ConditionCode("INQUIRE_DEBIT_CREDIT_SEARCH_TYPE_SELECT");
	public static final ConditionCode TRANSACTION_DATE_VALIDATION = new ConditionCode("TRANSACTION_DATE_VALIDATION");
	public static final ConditionCode EFFECTIVE_DATE_VALIDATION = new ConditionCode("EFFECTIVE_DATE_VALIDATION");
	public static final ConditionCode EXCESSIVE_DRAWDOWN_MANDATORY_RADIO_BUTTON = new ConditionCode("EXCESSIVE_DRAWDOWN_MANDATORY_RADIO_BUTTON");
	public static final ConditionCode SUBLEDGER_TRANSACTION_SEARCH_TYPE_SELECT = new ConditionCode("SUBLEDGER_TRANSACTION_SEARCH_TYPE_SELECT");
	public static final ConditionCode SUBLEDGER_TRANSACTION_SF215_SELECT = new ConditionCode("SUBLEDGER_TRANSACTION_SF215_SELECT");
	public static final ConditionCode REQUIRED_FIELD_TO_CONTINUE = new ConditionCode("REQUIRED_FIELD_TO_CONTINUE");
	public static final ConditionCode ACCOUNTING_PERIOD_YR_VALID = new ConditionCode("ACCOUNTING_PERIOD_YR_VALID");
	public static final ConditionCode SEARCH_INPUT_VALIDATION = new ConditionCode("SEARCH_INPUT_VALIDATION");
	public static final ConditionCode ACCOUNTING_PERIOD_NOT_OPEN = new ConditionCode("ACCOUNTING_PERIOD_NOT_OPEN");
	public static final ConditionCode EFFECT_DATE_NOT_IN_ACCOUNT_PERIOD = new ConditionCode("EFFECT_DATE_NOT_IN_ACCOUNT_PERIOD");
	public static final ConditionCode EFFECTIVE_DATE_NOT_FUTURE = new ConditionCode("EFFECTIVE_DATE_NOT_FUTURE");
	public static final ConditionCode INPUT_ALL_COMMENTS = new ConditionCode("INPUT_ALL_COMMENTS");
	public static final ConditionCode SEARCH_SL_SF215_SF5515 = new ConditionCode("SEARCH_SL_SF215_SF5515");
	public static final ConditionCode SF215_REFUNDS_TRANS_TYPE = new ConditionCode("SF215_REFUNDS_TRANS_TYPE");
	public static final ConditionCode SF215_RETURNS_TRANS_TYPE = new ConditionCode("SF215_RETURNS_TRANS_TYPE");
	public static final ConditionCode SELECT_TRANSACTION_TO_MODIFY_ACCS = new ConditionCode("SELECT_TRANSACTION_TO_MODIFY_ACCS");
	public static final ConditionCode CONTINUE_RESUBMIT_TRANSACTIONS = new ConditionCode("CONTINUE_RESUBMIT_TRANSACTIONS");
	public static final ConditionCode COMMENTS_TO_APPLY_ALL = new ConditionCode("COMMENTS_TO_APPLY_ALL");
	public static final ConditionCode SEARCH_ACCOUNTING_PERIOD_MNTH_YR = new ConditionCode("SEARCH_ACCOUNTING_PERIOD_MNTH_YR");
	public static final ConditionCode SEARCH_CORRECT_SF5515 = new ConditionCode("SEARCH_CORRECT_SF5515");
	public static final ConditionCode SEARCH_CORRECT_REVERSE_TRANS_STATUS = new ConditionCode("SEARCH_CORRECT_REVERSE_TRANS_STATUS");
	public static final ConditionCode SEARCH_RESUBMIT_TRANS_STATUS = new ConditionCode("SEARCH_RESUBMIT_TRANS_STATUS");
	public static final ConditionCode SEARCH_REVERSE_TRANS_TYPE = new ConditionCode("SEARCH_REVERSE_TRANS_TYPE");
	public static final ConditionCode SEARCH_CORRECT_TRANS_TYPE = new ConditionCode("SEARCH_CORRECT_TRANS_TYPE");
	public static final ConditionCode SEARCH_REVERSE_SF215 = new ConditionCode("SEARCH_REVERSE_SF215");
	public static final ConditionCode SEARCH_CORRECT_SF215 = new ConditionCode("SEARCH_CORRECT_SF215");
	public static final ConditionCode SEARCH_REVERSE_OB_TRANS_TYPE = new ConditionCode("SEARCH_REVERSE_OB_TRANS_TYPE");
	public static final ConditionCode SEARCH_REVERSE_RF_TRANS_TYPE = new ConditionCode("SEARCH_REVERSE_RF_TRANS_TYPE");
	public static final ConditionCode SEARCH_CORRECT_PRAWARD = new ConditionCode("SEARCH_CORRECT_PRAWARD");
	public static final ConditionCode REV_NEG_BALANCE = new ConditionCode("REV_NEG_BALANCE");
	public static final ConditionCode RESUBMIT_ACCS_STATUS = new ConditionCode("RESUBMIT_ACCS_STATUS");
	public static final ConditionCode RESUBMIT_AWARD_STATUS = new ConditionCode("RESUBMIT_AWARD_STATUS");
	public static final ConditionCode RESUBMIT_DATE_INVALID = new ConditionCode("RESUBMIT_DATE_INVALID");
	public static final ConditionCode RESUBMIT_DATE_INVALID_REINSTATED = new ConditionCode("RESUBMIT_DATE_INVALID_REINSTATED");
	public static final ConditionCode NO_HISTORY = new ConditionCode("NO_HISTORY");
	public static final ConditionCode DRAW_DOWN_INSUFFICIENTBALANCE_MESSAGE = new ConditionCode("DRAW_DOWN_INSUFFICIENTBALANCE_MESSAGE");
	public static final ConditionCode TRANSACTION_DATE_TO_FROM_VALIDATION = new ConditionCode("TRANSACTION_DATE_TO_FROM_VALIDATION");
	public static final ConditionCode NOT_GENERATE_FSA_TRANSACTION_ID = new ConditionCode("NOT_GENERATE_FSA_TRANSACTION_ID");
	public static final ConditionCode CORRECT_CHANGE_VALIDATION = new ConditionCode("CORRECT_CHANGE_VALIDATION");
	
	
	public static final ConditionCode ONE_DEBIT_CREDIT_PAIR_REQUIRED = new ConditionCode("ONE_DEBIT_CREDIT_PAIR_REQUIRED");
	public static final ConditionCode DEBIT_OR_CREDIT_ACCOUNT_MISSING = new ConditionCode("DEBIT_OR_CREDIT_ACCOUNT_MISSING");
	public static final ConditionCode EFFECTIVE_END_DATE_ERROR = new ConditionCode("EFFECTIVE_END_DATE_ERROR");
	public static final ConditionCode ACCOUNTING_TREATMENT_EXISTS = new ConditionCode("ACCOUNTING_TREATMENT_EXISTS");
	public static final ConditionCode ERROR_MODIFY_EXPIRED = new ConditionCode("ERROR_MODIFY_EXPIRED");
	
	/**
	 *    Feeder Messages
	 */
	
	public static final ConditionCode NO_DATA_FOUND_FOR_CRITERIA = new ConditionCode("NO_DATA_FOUND_FOR_CRITERIA");
	public static final ConditionCode FILE_IN_PROCESSING = new ConditionCode("FILE_IN_PROCESSING");
	public static final ConditionCode FEEDER_FILE_SUBMITTED = new ConditionCode("FEEDER_FILE_SUBMITTED");
	public static final ConditionCode FEEDER_UD_NOTIF_LETTER_PRINTED_CORRECTLY = new ConditionCode("FEEDER_UD_NOTIF_LETTER_PRINTED_CORRECTLY");
	public static final ConditionCode UD_NOTIF_LETTER_NOT_PRINTED = new ConditionCode("UD_NOTIF_LETTER_NOT_PRINTED");
	public static final ConditionCode FEEDER_UD_NOTIFICATION_CREATED = new ConditionCode("FEEDER_UD_NOTIFICATION_CREATED");
	public static final ConditionCode FEEDER_UD_NOTIF_LETTER_NOT_PRINTED = new ConditionCode("FEEDER_UD_NOTIF_LETTER_NOT_PRINTED");
	public static final ConditionCode FEEDER_UD_NOTIF_SELECT_REQUIRED = new ConditionCode("FEEDER_UD_NOTIF_SELECT_REQUIRED");
	public static final ConditionCode FEEDER_UD_TEMPLATE_REQUIRED = new ConditionCode("FEEDER_UD_TEMPLATE_REQUIRED");
	public static final ConditionCode NO_HISTORY_SUSPENDED_FILES = new ConditionCode("NO_HISTORY_SUSPENDED_FILES");
		
	/*
	 * Feeder File Awards Messages
	 */
	public static final ConditionCode SEARCH_CRITERIA_INVALID_DUNS = new ConditionCode("SEARCH_CRITERIA_INVALID_DUNS");	
	public static final ConditionCode SEARCH_CRITERIA_MANDATORY = new ConditionCode("SEARCH_CRITERIA_MANDATORY");
	public static final ConditionCode CHANGE_GRANTEE_DUNS_INVALID = new ConditionCode("CHANGE_GRANTEE_DUNS_INVALID");
	public static final ConditionCode ACCS_ADJUSTMENT_INVALID = new ConditionCode("ACCS_ADJUSTMENT_INVALID");
	public static final ConditionCode SEARCH_DUNS_MANDATORY = new ConditionCode("SEARCH_DUNS_MANDATORY");
	public static final ConditionCode CHANGE_DUNS_INVALID = new ConditionCode("CHANGE_DUNS_INVALID");
	public static final ConditionCode ACCS_AMT_ADJUST_NEG = new ConditionCode("ACCS_AMT_ADJUST_NEG");
	public static final ConditionCode PELL_ADJUST_AMT_EXCEED = new ConditionCode("PELL_ADJUST_AMT_EXCEED");
	public static final ConditionCode IMPACT_ADJUST_AMT_EXCEED = new ConditionCode("IMPACT_ADJUST_AMT_EXCEED");
	public static final ConditionCode SAME_ACCS_ADJUST_VALIDATION = new ConditionCode("SAME_ACCS_ADJUST_VALIDATION");
	public static final ConditionCode AWARD_ADJUSTMENT_SUCCESS = new ConditionCode("AWARD_ADJUSTMENT_SUCCESS");
	public static final ConditionCode ACCS_NOT_AVAIL_FOR_AWARD = new ConditionCode("ACCS_NOT_AVAIL_FOR_AWARD");
	public static final ConditionCode ADJUSTMENTS_ACCS_INVALID = new ConditionCode("ADJUSTMENTS_ACCS_INVALID");
	public static final ConditionCode INVALID_AWARD_STATUS = new ConditionCode("INVALID_AWARD_STATUS");
	public static final ConditionCode NO_GRANTEE_ENTERED = new ConditionCode("NO_GRANTEE_ENTERED");
	public static final ConditionCode GRANTEE_CHANGE_TO_CONTINUE = new ConditionCode("GRANTEE_CHANGE_TO_CONTINUE");
	public static final ConditionCode ACCS_AMT_ADJUST_MANDATORY = new ConditionCode("ACCS_AMT_ADJUST_MANDATORY");
	public static final ConditionCode CHANGE_GRANTEE_FAIL = new ConditionCode("CHANGE_GRANTEE_FAIL");
	public static final ConditionCode NO_INQUIRE_RESULTS = new ConditionCode("NO_INQUIRE_RESULTS");
	public static final ConditionCode VERIFY_DUNS_TO_CONTINUE = new ConditionCode("VERIFY_DUNS_TO_CONTINUE");
	
	//Maintain Loan Base Record
	public static final ConditionCode NO_DELETE_PRIVILEGE = new ConditionCode("NO_DELETE_PRIVILEGE");
	public static final ConditionCode NO_MODIFY_PRIVILEGE = new ConditionCode("NO_MODIFY_PRIVILEGE");
	public static final ConditionCode NO_ROLLOVER_PRIVILEGE = new ConditionCode("NO_ROLLOVER_PRIVILEGE");
	public static final ConditionCode LOAN_BASE_RECORD_NOT_DELETABLE = new ConditionCode("LOAN_BASE_RECORD_NOT_DELETABLE");
	public static final ConditionCode DIVISIONS_NOT_UPDATED = new ConditionCode("DIVISIONS_NOT_UPDATED");
	public static final ConditionCode CONTACTS_NOT_UPDATED = new ConditionCode("CONTACTS_NOT_UPDATED");
	public static final ConditionCode CONTACT_DOESNT_MATCH = new ConditionCode("CONTACT_DOESNT_MATCH");
	public static final ConditionCode ACCS_NOT_ENTERED = new ConditionCode("ACCS_NOT_ENTERED");
	public static final ConditionCode PCCA_CANT_BE_ZERO = new ConditionCode("PCCA_CANT_BE_ZERO");
	public static final ConditionCode NO_PCCA_HISTORY = new ConditionCode("NO_PCCA_HISTORY");
	public static final ConditionCode INVALID_FISCAL_YEAR = new ConditionCode("INVALID_FISCAL_YEAR");
	public static final ConditionCode DUPLICATE_PO = new ConditionCode("DUPLICATE_PO");
	public static final ConditionCode INVALID_SUBPROGRAM_CODE = new ConditionCode("INVALID_SUBPROGRAM_CODE");
	public static final ConditionCode PCCA_LESS_THAN_ZERO = new ConditionCode("PCCA_LESS_THAN_ZERO");
	public static final ConditionCode ACCS_ALREADY_ADDED = new ConditionCode("ACCS_ALREADY_ADDED");
	
	//manage feeder error log
	public static final ConditionCode MODIFY_SEL_ERROR_LOG = new ConditionCode("MODIFY_SEL_ERROR_LOG");
	public static final ConditionCode PRAWARDNO_ERROR = new ConditionCode("PRAWARDNO_ERROR");
	public static final ConditionCode POC_ERROR = new ConditionCode("POC_ERROR");
	public static final ConditionCode ACCS_FMMS_ERROR = new ConditionCode("ACCS_FMMS_ERROR");
	public static final ConditionCode LIMITATION_ERROR = new ConditionCode("LIMITATION_ERROR");
	public static final ConditionCode RESUBMIT_FAIL_RESULT = new ConditionCode("RESUBMIT_FAIL_RESULT");
	//public static final ConditionCode RESUBMIT_SUCCESS_RESULT = new ConditionCode("RESUBMIT_SUCCESS_RESULT");
	public static final ConditionCode DELETE_FAIL_RESULT = new ConditionCode("DELETE_FAIL_RESULT");
	public static final ConditionCode DELETE_SUCCESS_RESULT = new ConditionCode("DELETE_SUCCESS_RESULT");
	public static final ConditionCode MODIFY_FAIL_RESULT = new ConditionCode("MODIFY_FAIL_RESULT");
	public static final ConditionCode MODIFY_SUCCESS_RESULT = new ConditionCode("MODIFY_SUCCESS_RESULT");
	public static final ConditionCode CANNOT_ALLOW_ERROR_CODE_46 = new ConditionCode("CANNOT_ALLOW_ERROR_CODE_46");
	public static final ConditionCode CANNOT_ALLOW_ERROR_CODE_60 = new ConditionCode("CANNOT_ALLOW_ERROR_CODE_60");
	public static final ConditionCode NO_GRANTEE_EXISTS_G5 = new ConditionCode("NO_GRANTEE_EXISTS_G5");
	public static final ConditionCode NO_PAYEE_EXISTS_G5 = new ConditionCode("NO_PAYEE_EXISTS_G5");
	public static final ConditionCode AMOUNT_SIGN_CHANGE_ERROR = new ConditionCode("AMOUNT_SIGN_CHANGE_ERROR");

	// Setuploanawards
	public static final ConditionCode AWARD_DOCUMENT_ALREADY_EXISTS = new ConditionCode("AWARD_DOCUMENT_ALREADY_EXISTS");
	public static final ConditionCode CFDA_SUBPROGRAM_DOES_NOT_EXIST = new ConditionCode("CFDA_SUBPROGRAM_DOES_NOT_EXIST");
	public static final ConditionCode CFDA_SUBPROGRAM_WRONG_TYPE = new ConditionCode("CFDA_SUBPROGRAM_WRONG_TYPE");
	public static final ConditionCode START_DATE_OUT_OF_SUBPROGRAM_DATE_RANGE = new ConditionCode("START_DATE_OUT_OF_SUBPROGRAM_DATE_RANGE");
	public static final ConditionCode PERF_START_DATE_AFTER_END_DATE = new ConditionCode("PERF_START_DATE_AFTER_END_DATE");
	public static final ConditionCode GRANTEE_DUNS_NOT_VERIFIED = new ConditionCode("GRANTEE_DUNS_NOT_VERIFIED");
	public static final ConditionCode SELECT_RECORD = new ConditionCode("SELECT_RECORD");
	public static final ConditionCode SELECTION_REQUIRED_TO_ADD_ACCS = new ConditionCode("SELECTION_REQUIRED_TO_ADD_ACCS");
	public static final ConditionCode SELECTION_REQUIRED_TO_REMOVE_ACCS = new ConditionCode("SELECTION_REQUIRED_TO_REMOVE_ACCS");
	public static final ConditionCode CCA_ADJUSTMENT_AMT_EXCEEDS_PCCA_AMT = new ConditionCode("CCA_ADJUSTMENT_AMT_EXCEEDS_PCCA_AMT");
	public static final ConditionCode ZERO_NOT_ALLOWED_FOR_CCA_AMOUNT = new ConditionCode("ZERO_NOT_ALLOWED_FOR_CCA_AMOUNT");
	public static final ConditionCode SELECTION_REQUIRED_TO_REMOVE_LEVEL = new ConditionCode("SELECTION_REQUIRED_TO_REMOVE_LEVEL");
	public static final ConditionCode PARTICIPATION_LEVEL_START_DATE_OUT_OF_LOAN_AWARD_DATE_RANGE = new ConditionCode("PARTICIPATION_LEVEL_START_DATE_OUT_OF_LOAN_AWARD_DATE_RANGE");
	public static final ConditionCode ATLEAST_ONE_PARTICIPATION_LEVELREQUIRED = new ConditionCode("ATLEAST_ONE_PARTICIPATION_LEVELREQUIRED");
	public static final ConditionCode PARTICIPATION_LEVEL_PERIODS_CANNOT_OVERLAP = new ConditionCode("PARTICIPATION_LEVEL_PERIODS_CANNOT_OVERLAP");
	public static final ConditionCode END_DATE_REQUIRED_FOR_MORE_THAN_ONE_PARTICIPATION_LEVEL = new ConditionCode("END_DATE_REQUIRED_FOR_MORE_THAN_ONE_PARTICIPATION_LEVEL");
	public static final ConditionCode PARTICIPATION_LEVEL_START_DATE_AFTER_END_DATE = new ConditionCode("PARTICIPATION_LEVEL_START_DATE_AFTER_END_DATE");
	public static final ConditionCode SELECTION_REQUIRED_FOR_FILTER = new ConditionCode("SELECTION_REQUIRED_FOR_FILTER");
	public static final ConditionCode AWARD_DOCUMENTS_NOT_ROLLEDOVER = new ConditionCode("AWARD_DOCUMENTS_NOT_ROLLEDOVER");
	public static final ConditionCode AWARD_DOCUMENTS_CANNOT_BE_ROLLED_OVER = new ConditionCode("AWARD_DOCUMENTS_CANNOT_BE_ROLLED_OVER");
	public static final ConditionCode LOAN_AWARD_DOCUMENT_ALREADY_EXISTS = new ConditionCode("LOAN_AWARD_DOCUMENT_ALREADY_EXISTS");
	public static final ConditionCode INVALID_FLAGS_COMMENTS_LENGTH = new ConditionCode("INVALID_FLAGS_COMMENTS_LENGTH");
	public static final ConditionCode AWARD_FLAGS_NO_REASON_SELECTED = new ConditionCode("AWARD_FLAGS_NO_REASON_SELECTED");
	public static final ConditionCode REASONS_SELECTED_WITHOUT_FLAG_SELECTION =  new ConditionCode("REASONS_SELECTED_WITHOUT_FLAG_SELECTION");
	public static final ConditionCode CCA_ADJUSTMENT_REDUCES_CCA_BELOW_NET =  new ConditionCode("CCA_ADJUSTMENT_REDUCES_CCA_BELOW_NET");
	public static final ConditionCode ACCS_CANNOT_BE_REMOVED =  new ConditionCode("ACCS_CANNOT_BE_REMOVED");
	public static final ConditionCode MANUALLY_CLOSED_AWARDS_CANNOT_MODIFY =  new ConditionCode("MANUALLY_CLOSED_AWARDS_CANNOT_MODIFY");
	public static final ConditionCode LOAN_AWARD_DOC_WITH_FIN_TRAN_CANNOT_DELETE =  new ConditionCode("LOAN_AWARD_DOC_WITH_FIN_TRAN_CANNOT_DELETE");
	public static final ConditionCode SCHOOL_NAME_IS_REQUIRED =  new ConditionCode("SCHOOL_NAME_IS_REQUIRED");
	public static final ConditionCode LOAN_AWARD_DOC_CREATED_MODIFIED_SUCCESSFULLY = new ConditionCode("LOAN_AWARD_DOC_CREATED_MODIFIED_SUCCESSFULLY");
	public static final ConditionCode COMMENTS_REQUIRED_FOR_OTHER_EMERGENCY = new ConditionCode("COMMENTS_REQUIRED_FOR_OTHER_EMERGENCY");
	public static final ConditionCode PROGRAM_INACTIVE = new ConditionCode("PROGRAM_INACTIVE");
	public static final ConditionCode NO_PENDING_PAYMENTS = new ConditionCode("NO_PENDING_PAYMENTS");
	public static final ConditionCode ROLLOVER_FISCAL_YR_FUTURE_YR = new ConditionCode("ROLLOVER_FISCAL_YR_FUTURE_YR");
	public static final ConditionCode INVALID_ZIPCODE_FOR_STATE = new ConditionCode("INVALID_ZIPCODE_FOR_STATE");
	// COF Generate 
	public static final ConditionCode ERROR_COF_GENERATE = new ConditionCode("ERROR_COF_GENERATE");

	/**
	 * Finalize Grant Transfer
	 */
	public static final ConditionCode INSUFFICIENT_BALANCE = new ConditionCode("INSUFFICIENT_BALANCE");
	public static final ConditionCode PENDING_PAYMENTS_EXIST = new ConditionCode("PENDING_PAYMENTS_EXIST");
	public static final ConditionCode INVALID_EFFECTIVE_DATE = new ConditionCode("INVALID_EFFECTIVE_DATE");	
	public static final ConditionCode NO_FINALIZE_TRANSACTION = new ConditionCode("NO_FINALIZE_TRANSACTION");	
	public static final ConditionCode NO_DUNS = new ConditionCode("NO_DUNS");
	public static final ConditionCode BUDGET_PERIOD_INCOMPLETE = new ConditionCode("BUDGET_PERIOD_INCOMPLETE");	
	public static final ConditionCode FROM_PRAWARD_NO_INVALID = new ConditionCode("FROM_PRAWARD_NO_INVALID");
	public static final ConditionCode TO_PRAWARD_NO_INVALID = new ConditionCode("TO_PRAWARD_NO_INVALID");
	public static final ConditionCode NOT_ELIGIBLE_FOR_REVERSAL = new ConditionCode("NOT_ELIGIBLE_FOR_REVERSAL");
	public static final ConditionCode FELLOWSHIP_INDIVIDUAL_AWARD_FOUND = new ConditionCode("FELLOWSHIP_INDIVIDUAL_AWARD_FOUND");	
	public static final ConditionCode DUNS_NOT_VERIFIED = new ConditionCode("DUNS_NOT_VERIFIED");
	public static final ConditionCode FIELDS_CANT_BE_BLANK = new ConditionCode("FIELDS_CANT_BE_BLANK");
	public static final ConditionCode CLAUSE_NOT_EDITABLE = new ConditionCode("CLAUSE_NOT_EDITABLE");
	public static final ConditionCode INVALID_SCHEDULE_NUMBER = new ConditionCode("INVALID_SCHEDULE_NUMBER");	
	public static final ConditionCode NOT_ELIGIBLE_FOR_GRANT_TRANSFER = new ConditionCode("NOT_ELIGIBLE_FOR_GRANT_TRANSFER");
	public static final ConditionCode EFFECTIVE_DATE_REQUIRED = new ConditionCode("EFFECTIVE_DATE_REQUIRED");
	public static final ConditionCode PRAWARDNO_REQUIRED = new ConditionCode("PRAWARDNO_REQUIRED");
	public static final ConditionCode COMMENTS_LIMIT = new ConditionCode("COMMENTS_LIMIT");
	public static final ConditionCode REVERSAL_FAILED = new ConditionCode("REVERSAL_FAILED");
	public static final ConditionCode REVERSAL_SUCCESS = new ConditionCode("REVERSAL_SUCCESS");
	public static final ConditionCode ERROR_INVALID_DOCUMENT_SEQ_NO = new ConditionCode("ERROR_INVALID_DOCUMENT_SEQ_NO");
	
	
	/**
	 * Admin Actions
	 */
	public static final ConditionCode NO_ADMIN_ACTION_SEQ = new ConditionCode("NO_ADMIN_ACTION_SEQ");
	public static final ConditionCode REQUEST_SUBMITTED = new ConditionCode("REQUEST_SUBMITTED");
	public static final ConditionCode REQUEST_IN_PROCESS = new ConditionCode("REQUEST_IN_PROCESS");
	public static final ConditionCode REQUEST_APPROVED = new ConditionCode("REQUEST_APPROVED");
	public static final ConditionCode REQUEST_DISAPPROVED = new ConditionCode("REQUEST_DISAPPROVED");
	public static final ConditionCode REQUEST_ACCEPTED = new ConditionCode("REQUEST_ACCEPTED");
	public static final ConditionCode REQUEST_UNACCEPTED = new ConditionCode("REQUEST_UNACCEPTED");
	public static final ConditionCode REQUEST_DELETED = new ConditionCode("REQUEST_DELETED");
	public static final ConditionCode BUDGET_DATA_AND_DATES = new ConditionCode("BUDGET_DATA_AND_DATES");
	public static final ConditionCode NO_TO_SCHEDULES = new ConditionCode("NO_TO_SCHEDULES");
	public static final ConditionCode SCORES_EXIST = new ConditionCode("SCORES_EXIST");
	public static final ConditionCode NO_ED_PROGRAM_CONTACT = new ConditionCode("NO_ED_PROGRAM_CONTACT");
	public static final ConditionCode NO_PRIVILEGES = new ConditionCode("NO_PRIVILEGES");
	public static final ConditionCode GAN_NOT_GENERATED= new ConditionCode("GAN_NOT_GENERATED");
	public static final ConditionCode GAN_NOT_PRINTED_CORRECTLY= new ConditionCode("GAN_NOT_PRINTED_CORRECTLY");
	public static final ConditionCode GAN_SHOULD_NOT_GENERATED= new ConditionCode("GAN_SHOULD_NOT_GENERATED");
	public static final ConditionCode NO_FUTURE_BUDGET_PERIODS= new ConditionCode("NO_FUTURE_BUDGET_PERIODS");
	public static final ConditionCode NO_SUFFIX= new ConditionCode("NO_SUFFIX");
	public static final ConditionCode DUPLICATES_EXIST=new ConditionCode("DUPLICATES_EXIST");
		
	/*
	 *  For Maintain Payment (including Payee Award and Payment Flags)
	 */
	public static final ConditionCode PAYEE_AWARD_NOTSELECTED  = new ConditionCode("PAYEE_AWARD_NOTSELECTED");
	public static final ConditionCode MAINTAIN_PAYMENT_SEARCH_NOTSELECTED  = new ConditionCode("MAINTAIN_PAYMENT_SEARCH_NOTSELECTED");
	public static final ConditionCode MAINTAIN_PAYMENT_SEARCH_MODIFY_NOTSELECTED  = new ConditionCode("MAINTAIN_PAYMENT_SEARCH_MODIFY_NOTSELECTED");	
	public static final ConditionCode MAINTAIN_PAYMENT_SEARCH_INQUIRE_NOTSELECTED  = new ConditionCode("MAINTAIN_PAYMENT_SEARCH_INQUIRE_NOTSELECTED");
	public static final ConditionCode MAINTAIN_PAYMENT_NOFLAG_SELECTED  = new ConditionCode("MAINTAIN_PAYMENT_NOFLAG_SELECTED");
	public static final ConditionCode MAINTAIN_PAYMENT_NOREASON_SELECTED  = new ConditionCode("MAINTAIN_PAYMENT_NOREASON_SELECTED");
	public static final ConditionCode MAINTAIN_PAYMENT_COMMENTS_REQUIRED_FORREASONS  = new ConditionCode("MAINTAIN_PAYMENT_COMMENTS_REQUIRED_FORREASONS");
	public static final ConditionCode MAINTAIN_PAYMENT_CHILDAWARD_STOP_FLAGREQUIRED  = new ConditionCode("MAINTAIN_PAYMENT_CHILDAWARD_STOP_FLAGREQUIRED");
	public static final ConditionCode PAYMENT_NOPREVILIGE_EXCEPTION  = new ConditionCode("PAYMENT_NOPREVILIGE_EXCEPTION");
	public static final ConditionCode PAYMENT_ROUTEREAONS_WITHOUTROUTEFLAG  = new ConditionCode("PAYMENT_ROUTEREAONS_WITHOUTROUTEFLAG");
	public static final ConditionCode PAYMENT_STOPREAONS_WITHOUTROUTEFLAG  = new ConditionCode("PAYMENT_STOPREAONS_WITHOUTROUTEFLAG");
	/*
	 *  For Maintain Refund and Reassign Loan Feeder Refund
	 */
	
	public static final ConditionCode INVALID_REASSIGN_AMOUNT  = new ConditionCode("INVALID_REASSIGN_AMOUNT");
	public static final ConditionCode AWARD_DOESNT_EXIST  = new ConditionCode("AWARD_DOESNT_EXIST");
	public static final ConditionCode AWARD_CLOSED  = new ConditionCode("AWARD_CLOSED");
	public static final ConditionCode LOAN_AWARD_FSA  = new ConditionCode("LOAN_AWARD_FSA");
	public static final ConditionCode INVALID_REASSIGNMENT_AMT  = new ConditionCode("INVALID_REASSIGNMENT_AMT");
	public static final ConditionCode REASSIGNMENT_AMT_NET_EXCEEDED  = new ConditionCode("REASSIGNMENT_AMT_NET_EXCEEDED");
	public static final ConditionCode AVAILABLE_BALANCE_REDUCED  = new ConditionCode("AVAILABLE_BALANCE_REDUCED");
	public static final ConditionCode REASSIGNEMENT_DATE_PAST  = new ConditionCode("REASSIGNEMENT_DATE_PAST");	
	public static final ConditionCode TOTAL_AMT_EXCEED_SF215AMT  = new ConditionCode("TOTAL_AMT_EXCEED_SF215AMT");
	public static final ConditionCode TOTAL_AMT_NEGATIVE  = new ConditionCode("TOTAL_AMT_NEGATIVE");	
	public static final ConditionCode REFUND_ALREADY_ASSIGNED  = new ConditionCode("REFUND_ALREADY_ASSIGNED");
	public static final ConditionCode RETURN_REFUND_ALREADY_RETURNED  = new ConditionCode("RETURN_REFUND_ALREADY_RETURNED");
	public static final ConditionCode REASSIGN_REFUND_ALREADY_RETURNED  = new ConditionCode("REASSIGN_REFUND_ALREADY_RETURNED");
	public static final ConditionCode REFUND_DUNSLOOKUP_SAMEDUNS_SELECTED  = new ConditionCode("REFUND_DUNSLOOKUP_SAMEDUNS_SELECTED");
	public static final ConditionCode REFUND_DUNSLOOKUP_REASSIGN_INVALID  = new ConditionCode("REFUND_DUNSLOOKUP_REASSIGN_INVALID");
	public static final ConditionCode REFUND_INVALID_DUNS  = new ConditionCode("REFUND_INVALID_DUNS");
	public static final ConditionCode REFUND_DUNS_NEED_REASSIGNED  = new ConditionCode("REFUND_DUNS_NEED_REASSIGNED");
	public static final ConditionCode LOAN_REFUND_ZERO  = new ConditionCode("LOAN_REFUND_ZERO");
	public static final ConditionCode LOAN_AWARD_DOESNOT_EXIST  = new ConditionCode("LOAN_AWARD_DOESNOT_EXIST");
	public static final ConditionCode INSUFFICIENT_BALANCE_EXCEPTION  = new ConditionCode("INSUFFICIENT_BALANCE_EXCEPTION");	
	public static final ConditionCode NO_ACCS_FOUND  = new ConditionCode("NO_ACCS_FOUND");
	public static final ConditionCode FSA_TRAX_ID_GENERATION_EXCEPTION= new ConditionCode("FSA_TRAX_ID_GENERATION_EXCEPTION");
	public static final ConditionCode FSA_TRAX_NOT_PROCESSED_EXCEPTION= new ConditionCode("FSA_TRAX_NOT_PROCESSED_EXCEPTION");
	public static final ConditionCode NO_CREDIT_DEBIT_GLACCOUYNT_EXIST_EXCEPTION= new ConditionCode("NO_CREDIT_DEBIT_GLACCOUYNT_EXIST_EXCEPTION");
	public static final ConditionCode ACCOUNT_PERIOD_NAME_NOTFOUND_EXCEPTION= new ConditionCode("ACCOUNT_PERIOD_NAME_NOTFOUND_EXCEPTION");
		
	public static final ConditionCode INVALID_OMB_CONTROL_NUM = new ConditionCode("INVALID_OMB_CONTROL_NUM");
	public static final ConditionCode NOT_FUTURE_DATE = new ConditionCode("NOT_FUTURE_DATE");
	public static final ConditionCode PRE_APPLICATION_INFO_REQUIRED = new ConditionCode("PRE_APPLICATION_INFO_REQUIRED");
	public static final ConditionCode FORM_ORDER_NUMBER_LESS_THAN_ZERO	 = new ConditionCode("FORM_ORDER_NUMBER_LESS_THAN_ZERO");
	public static final ConditionCode APPLICATION_PACKAGE_ALREADY_EXIST_FOR_GRANT_SCHEDULE	 = new ConditionCode("APPLICATION_PACKAGE_ALREADY_EXIST_FOR_GRANT_SCHEDULE");
	public static final ConditionCode EARMARK_PIN_GENERATED	 = new ConditionCode("EARMARK_PIN_GENERATED");
	public static final ConditionCode CANNOT_DELETE_INITIATED_PACKAGE = new ConditionCode("CANNOT_DELETE_INITIATED_PACKAGE");
	public static final ConditionCode CANNOT_DELETE_PUBLISHED_PACKAGE = new ConditionCode("CANNOT_DELETE_PUBLISHED_PACKAGE");
	public static final ConditionCode CANNOT_MODIDY_PUBLISHED_PACKAGE = new ConditionCode("CANNOT_MODIDY_PUBLISHED_PACKAGE");
	public static final ConditionCode CANNOT_MODIDY_UNPUBLISHED_PACKAGE = new ConditionCode("CANNOT_MODIDY_UNPUBLISHED_PACKAGE");
	public static final ConditionCode CANNOT_MODIDY_INITIATED_PACKAGE = new ConditionCode("CANNOT_MODIDY_INITIATED_PACKAGE");
	public static final ConditionCode PROGRAM_OFFOCE_ROLE_CANNOT_MODIDY_INITIATED_PACKAGE = new ConditionCode("PROGRAM_OFFOCE_ROLE_CANNOT_MODIDY_INITIATED_PACKAGE");
	public static final ConditionCode MISSING_REQUIRED_ELECTRONIC_FORMS = new ConditionCode("MISSING_REQUIRED_ELECTRONIC_FORMS");
	public static final ConditionCode PACKAGE_PDF_GENERATION_ERROR = new ConditionCode("PACKAGE_PDF_GENERATION_ERROR");
	public static final ConditionCode LATER_PACKAGE_VERSION_EXISTS = new ConditionCode("LATER_PACKAGE_VERSION_EXISTS");
	public static final ConditionCode CONFIG_ISSUE_EFORM_TEMPLATE_MISSING = new ConditionCode("CONFIG_ISSUE_EFORM_TEMPLATE_MISSING");
	public static final ConditionCode NO_PUBLISH_COMMENTS = new ConditionCode("NO_PUBLISH_COMMENTS");
	public static final ConditionCode FORM_ORDER_NON_UNIQUE = new ConditionCode("FORM_ORDER_NON_UNIQUE");
	/**
	 * 	Submit eApplication  
	 */
	public static final ConditionCode USER_NOT_SELECTED = new ConditionCode("USER_NOT_SELECTED");
	public static final ConditionCode USERS_REMOVED = new ConditionCode("USERS_REMOVED");
	public static final ConditionCode USER_CANNOT_BE_REMOVED = new ConditionCode("USER_CANNOT_BE_REMOVED");
	public static final ConditionCode FORM_NOT_SELECTED = new ConditionCode("FORM_NOT_SELECTED");
	public static final ConditionCode REMOVE_MANAGER_ERROR = new ConditionCode("REMOVE_MANAGER_ERROR");
	public static final ConditionCode REMOVE_SUBMITTER_ERROR = new ConditionCode("REMOVE_SUBMITTER_ERROR");
	public static final ConditionCode REMOVE_LOGGED_IN_USER_ERROR = new ConditionCode("REMOVE_LOGGED_IN_USER_ERROR");
	public static final ConditionCode PACKAGE_NOT_COMPLETE = new ConditionCode("PACKAGE_NOT_COMPLETE");
	public static final ConditionCode PACKAGE_ALREADY_SUBMITTED = new ConditionCode("PACKAGE_ALREADY_SUBMITTED");
	public static final ConditionCode IMPACT_AID_ACCESS = new ConditionCode("IMPACT_AID_ACCESS");
	public static final ConditionCode PACKAGE_SUBMISSION_LATE = new ConditionCode("PACKAGE_SUBMISSION_LATE");
	public static final ConditionCode IA_PACKAGE_SUBMISSION_LATE = new ConditionCode("IA_PACKAGE_SUBMISSION_LATE");
	public static final ConditionCode IA_PACKAGE_SUBMISSION_LATE_60 = new ConditionCode("IA_PACKAGE_SUBMISSION_LATE_60");	
	public static final ConditionCode FACE_SHEET_NOT_COMPLETE = new ConditionCode("FACE_SHEET_NOT_COMPLETE");	
	public static final ConditionCode NO_PRIOR_PACKAGE = new ConditionCode("NO_PRIOR_PACKAGE");	
	public static final ConditionCode REFERENCE_FORM_SUBMITTED = new ConditionCode("REFERENCE_FORM_SUBMITTED");	
	public static final ConditionCode PACKAGE_PDF_GENERATED = new ConditionCode("PACKAGE_PDF_GENERATED");
	public static final ConditionCode MISSING_REQUIRED_DATA = new ConditionCode("MISSING_REQUIRED_DATA");
	public static final ConditionCode INVALID_EARMARK_PIN = new ConditionCode("INVALID_EARMARK_PIN");	
	public static final ConditionCode INVALID_PRE_APPLICATION = new ConditionCode("INVALID_PRE_APPLICATION");
	/**
	 * 	Grants.gov Admin  
	 */
	public static final ConditionCode CLOSING_TIME_FORMAT = new ConditionCode("CLOSING_TIME_FORMAT");
	public static final ConditionCode DOWNLOAD_CONCURRENCY = new ConditionCode("DOWNLOAD_CONCURRENCY");
	public static final ConditionCode DOWNLOAD_FREQUENCY = new ConditionCode("DOWNLOAD_FREQUENCY");
	public static final ConditionCode DOWNLOAD_ORDER_NUMBER_LESS_THAN_ZERO = new ConditionCode("DOWNLOAD_ORDER_NUMBER_LESS_THAN_ZERO");
	public static final ConditionCode DOWNLOAD_NON_UNIQUE = new ConditionCode("DOWNLOAD_NON_UNIQUE");
	public static final ConditionCode NO_PENDING_DOWNLOADS = new ConditionCode("NO_PENDING_DOWNLOADS");
	public static final ConditionCode NO_APPS_TO_MANUAL_MARK = new ConditionCode("NO_APPS_TO_MANUAL_MARK");
	public static final ConditionCode PACKAGE_SUBMISSION_PROJ_DIR_SUBMITTED = new ConditionCode("PACKAGE_SUBMISSION_PROJ_DIR_SUBMITTED");
	public static final ConditionCode PACKAGE_SUBMISSION_PROJ_DIR_NOT_INITIATED = new ConditionCode("PACKAGE_SUBMISSION_PROJ_DIR_NOT_INITIATED");
	public static final ConditionCode INVALID_DOWNLOAD_FREQUENCY = new ConditionCode("INVALID_DOWNLOAD_FREQUENCY");
	public static final ConditionCode INVALID_DOWNLOAD_CONCURRENCY = new ConditionCode("INVALID_DOWNLOAD_CONCURRENCY");

	public static final ConditionCode MISSING_END_DATE = new ConditionCode("MISSING_END_DATE");
	public static final ConditionCode MISSING_START_DATE = new ConditionCode("MISSING_START_DATE");
	public static final ConditionCode INVALID_END_DATE = new ConditionCode("INVALID_END_DATE");
	
	
	/** 
	 *  For Award Closeout
	 */
	public static final ConditionCode AWARDCLOSEOUT_NOTSELECTED = new ConditionCode("AWARDCLOSEOUT_NOTSELECTED");
	public static final ConditionCode AWARDCLOSEOUT_PENDING_PAYMENT = new ConditionCode("AWARDCLOSEOUT_PENDING_PAYMENT");	
	public static final ConditionCode AWARDCLOSEOUT_RESOLVE_NOTSELECTED = new ConditionCode("AWARDCLOSEOUT_RESOLVE_NOTSELECTED");
	public static final ConditionCode AWARDCLOSEOUT_RESOLVE_NEED_ONEOTSELECTED = new ConditionCode("AWARDCLOSEOUT_RESOLVE_NEED_ONEOTSELECTED"); 
	public static final ConditionCode AWARDCLOSEOUT_PENDINGACTION = new ConditionCode("AWARDCLOSEOUT_PENDINGACTION");
	public static final ConditionCode AWARDCLOSEOUT_INCOMPLIANCE = new ConditionCode("AWARDCLOSEOUT_INCOMPLIANCE");
	public static final ConditionCode AWC_FINTRAN_CANTPROCESSED = new ConditionCode("AWC_FINTRAN_CANTPROCESSED");
	public static final ConditionCode AWC_FSA_TRAN_IDGENERATION_FAILED = new ConditionCode("AWC_FSA_TRAN_IDGENERATION_FAILED");
	public static final ConditionCode AWC_NO_CREDITDEBITGL_ACCOUNT_EXIST = new ConditionCode("AWC_NO_CREDITDEBITGL_ACCOUNT_EXIST");	
	public static final ConditionCode AWC_ACCOUNTPERIOD_NAMENOT_FOUND = new ConditionCode("AWC_ACCOUNTPERIOD_NAMENOT_FOUND");
	public static final ConditionCode AWC_BALANCE_MISMATCH = new ConditionCode("AWC_BALANCE_MISMATCH");	
	public static final ConditionCode AWC_NOACCS_FOUND = new ConditionCode("AWC_NOACCS_FOUND");
	public static final ConditionCode AWC_SUBLEDGER_FAILED = new ConditionCode("AWC_SUBLEDGER_FAILED");
	
	public static final ConditionCode ENDDATE_BEFORE_BEGINDATE = new ConditionCode("ENDDATE_BEFORE_BEGINDATE");
	public static final ConditionCode ENDDATE_BEFORE_TODAYDATE = new ConditionCode("ENDDATE_BEFORE_TODAYDATE");	
	public static final ConditionCode BEGINDATE_BEFORE_CLOSEOUTDATE = new ConditionCode("BEGINDATE_BEFORE_CLOSEOUTDATE");
	public static final ConditionCode DATERANG_NEED_THIRTYDAYS = new ConditionCode("DATERANG_NEED_THIRTYDAYS");
	public static final ConditionCode AWARDCLOSEOUT_RESOLUTIONORRECPTDATE_REQUIRED = new ConditionCode("AWARDCLOSEOUT_RESOLUTIONORRECPTDATE_REQUIRED");
	public static final ConditionCode AWARDCLOSEOUT_RESOLUTIONCODE_HS_RECPTDATE_REUIRED = new ConditionCode("AWARDCLOSEOUT_RESOLUTIONCODE_HS_RECPTDATE_REUIRED");	
	public static final ConditionCode AWARDCLOSEOUT_BOTHPROGRESSIND_OFF = new ConditionCode("AWARDCLOSEOUT_BOTHPROGRESSIND_OFF");
	public static final ConditionCode AWARDCLOSEOUT_BOTHPROGRESSIND_ON = new ConditionCode("AWARDCLOSEOUT_BOTHPROGRESSIND_ON");
	public static final ConditionCode AWARDCLOSEOUT_PROGRESSNOTMET_BUTNOCOMMENTS = new ConditionCode("AWARDCLOSEOUT_PROGRESSNOTMET_BUTNOCOMMENTS");	
	public static final ConditionCode IAR_SEARCH_EITHERAPPLICANTNAMEORORGNAME = new ConditionCode("IAR_SEARCH_EITHERAPPLICANTNAMEORORGNAME");
	public static final ConditionCode IAR_AWARDSELECTED_WTOREINSTATEAMOUNT = new ConditionCode("IAR_AWARDSELECTED_WTOREINSTATEAMOUNT");
	public static final ConditionCode IAR_ACCSREINSTAAMOUNT_GREATER_AVBLAMOUNT = new ConditionCode("IAR_ACCSREINSTAAMOUNT_GREATER_AVBLAMOUNT");
	public static final ConditionCode IAR_ACCSNOTSELECTED_AMTENTERED = new ConditionCode("IAR_ACCSNOTSELECTED_AMTENTERED");
	public static final ConditionCode AWARDCLOSEOUT_COMMENTS_MAXLENGTH_CONSTRAINT = new ConditionCode("AWARDCLOSEOUT_COMMENTS_MAXLENGTH_CONSTRAINT");
	public static final ConditionCode IAR_TOTALAMTTOREIST_GREATERTHAN_AMTAVBLTOREINST = new ConditionCode("IAR_TOTALAMTTOREIST_GREATERTHAN_AMTAVBLTOREINST");
	public static final ConditionCode IAR_MODIFY_NOTALLOWED = new ConditionCode("IAR_MODIFY_NOTALLOWED");
	public static final ConditionCode IAR_INITIATE_NOTALLOWED = new ConditionCode("IAR_INITIATE_NOTALLOWED");
	public static final ConditionCode IAR_INITIATE_NOTALLOWED_REINSDATE_NOT_BEFORETODAYDATE = new ConditionCode("IAR_INITIATE_NOTALLOWED_REINSDATE_NOT_BEFORETODAYDATE");
	public static final ConditionCode IAR_INITIATE_NOTALLOWED_FORLESSTHANMAX_REINSTATENO = new ConditionCode("IAR_INITIATE_NOTALLOWED_FORLESSTHANMAX_REINSTATENO");	
	public static final ConditionCode IAR_DELETE_NOTALLOWED = new ConditionCode("IAR_DELETE_NOTALLOWED");
	public static final ConditionCode IAR_SELECT_CONFIRM_OPTION = new ConditionCode("IAR_SELECT_CONFIRM_OPTION");
	public static final ConditionCode IAR_ATLEST_ONEACCCS_REQUIRED = new ConditionCode("IAR_ATLEST_ONEACCCS_REQUIRED");	
	public static final ConditionCode RPC_AN_BOXNO_LOCATION_MISING = new ConditionCode("RPC_AN_BOXNO_LOCATION_MISING");
	public static final ConditionCode LIQUIDATIONDATE_BEFORE_BEGINDATE = new ConditionCode("LIQUIDATIONDATE_BEFORE_BEGINDATE");
	public static final ConditionCode LIQUIDATIONDATE_EXTENSION = new ConditionCode("LIQUIDATIONDATE_EXTENSION");
	public static final ConditionCode LIQUIDATIONDATE_BEFORE_BEGINBUDGETDATE = new ConditionCode("LIQUIDATIONDATE_BEFORE_BEGINBUDGETDATE");
	public static final ConditionCode CLOSEOUTDATE_BEFORE_SUSPENSION = new ConditionCode("CLOSEOUTDATE_BEFORE_SUSPENSION");	
	public static final ConditionCode SUSPENSIONDATE_BEFORE_LIQUIDATIONDATE = new ConditionCode("SUSPENSIONDATE_BEFORE_LIQUIDATIONDATE");
	public static final ConditionCode LIQUIDATIONDATE_BEFORE_ORIGINALDATE = new ConditionCode("LIQUIDATIONDATE_BEFORE_ORIGINALDATE");
	public static final ConditionCode CLOSED_STATUS_WITHOUT_CLOSEOUTDATE = new ConditionCode("CLOSED_STATUS_WITHOUT_CLOSEOUTDATE");
	public static final ConditionCode CLOSED_STATUS_CLOSEOUTDATE_BEFORE_TODAYDATE = new ConditionCode("CLOSED_STATUS_CLOSEOUTDATE_BEFORE_TODAYDATE");
	public static final ConditionCode REPORT_DUEDATE_BUDGET_FINALDATE = new ConditionCode("REPORT_DUEDATE_BUDGET_FINALDATE");
	public static final ConditionCode IS_INVALID_DATE = new ConditionCode("IS_INVALID_DATE");
	public static final ConditionCode APPLY_DUEDATE = new ConditionCode("APPLY_DUEDATE");
	
	public static final ConditionCode KEY_PERSON_NOT_UPDATED = new ConditionCode("KEY_PERSON_NOT_UPDATED");
	public static final ConditionCode TEMPLATE_ASSOCIATED = new ConditionCode("TEMPLATE_ASSOCIATED");
	public static final ConditionCode NOTIFICATION_ONLY = new ConditionCode("NOTIFICATION_ONLY");
	/*
	 * Payments Rework
	 */
	public static final ConditionCode SAME_AWARD_INCREASE_DECREASE = new ConditionCode("SAME_AWARD_INCREASE_DECREASE");
	public static final ConditionCode INCREASE_AMT_EXCEED_NETDRAW = new ConditionCode("INCREASE_AMT_EXCEED_NETDRAW");
	public static final ConditionCode DECREASE_AMT_EXCEED_AVAIL_BALANCE = new ConditionCode("DECREASE_AMT_EXCEED_AVAIL_BALANCE");
	public static final ConditionCode INCREASE_DECREASE_NOT_BALANCED = new ConditionCode("INCREASE_DECREASE_NOT_BALANCED");
	public static final ConditionCode RETURN_AMOUNT_EXCEEDS_NET_DRAWS = new ConditionCode("RETURN_AMOUNT_EXCEEDS_NET_DRAWS");
	public static final ConditionCode HOTLINEUSER_ROLETYPE = new ConditionCode("HOTLINEUSER_ROLETYPE");
	public static final ConditionCode USER_ID_REQUIRED = new ConditionCode("USER_ID_REQUIRED");
	public static final ConditionCode DOB_PAYEE_REQUIRED = new ConditionCode("DOB_PAYEE_REQUIRED");
	public static final ConditionCode NO_DATA_FOUND = new ConditionCode("NO_DATA_FOUND");
	public static final ConditionCode NO_EMAIL_FOUND = new ConditionCode("NO_EMAIL_FOUND");
	
	public static final ConditionCode MISMATCH_SUM_TO_VOUCHER_AMT = new ConditionCode("MISMATCH_SUM_TO_VOUCHER_AMT");
	public static final ConditionCode SELECT_ONLY_ONE_SCHEDULE_FOR_REMOVE = new ConditionCode("SELECT_ONLY_ONE_SCHEDULE_FOR_REMOVE");
	/* Performance Report */	
	public static final ConditionCode REPORT_PACKAGE_ALREADY_EXIST_FOR_GRANT_SCHEDULE= new ConditionCode("REPORT_PACKAGE_ALREADY_EXIST_FOR_GRANT_SCHEDULE");
	
	public static final ConditionCode PACKAGE_NOT_FOUND = new ConditionCode("PACKAGE_NOT_FOUND"); 
	public static final ConditionCode NOT_UNIQUE_PACKAGE = new ConditionCode("NOT_UNIQUE_PACKAGE"); 
	public static final ConditionCode PRIOR_INSTANCE_NOT_LOCATED = new ConditionCode("PRIOR_INSTANCE_NOT_LOCATED");
	public static final ConditionCode PRIOR_INSTANCE_NOT_COPIED = new ConditionCode("PRIOR_INSTANCE_NOT_COPIED");
	public static final ConditionCode PRIOR_INSTANCE_SELECTION = new ConditionCode("PRIOR_INSTANCE_SELECTION"); 
	public static final ConditionCode LATE_AFTER_CLOSING_DATE = new ConditionCode("LATE_AFTER_CLOSING_DATE"); 
	public static final ConditionCode LATE_AFTER_DUE_DATE = new ConditionCode("LATE_AFTER_DUE_DATE"); 
	public static final ConditionCode REPORT_EXIST = new ConditionCode("REPORT_EXIST"); 
	
	public static final ConditionCode TEMPLATE_ALREADY_EXISTS = new ConditionCode("TEMPLATE_ALREADY_EXISTS");
	public static final ConditionCode FISCAL_YR_AND_PRAWARD_NUMBER_REQUIRED = new ConditionCode("FISCAL_YR_AND_PRAWARD_NUMBER_REQUIRED");
	public static final ConditionCode CANNOT_DELETE_TEMPLATE = new ConditionCode("CANNOT_DELETE_TEMPLATE");
	public static final ConditionCode CANNOT_CREATE_TEMPLATE = new ConditionCode("CANNOT_CREATE_TEMPLATE");
	public static final ConditionCode NEW_TEMPLATE_REQUIRED = new ConditionCode("NEW_TEMPLATE_REQUIRED");
	
	// for Maintain Cofnigurable Fields
	public static final ConditionCode CONFIGFIELDS_INVALIDCODE = new ConditionCode("CONFIGFIELDS_INVALIDCODE");
	public static final ConditionCode CONFIGFIELDS_ASSOSCIATEDING5 = new ConditionCode("CONFIGFIELDS_ASSOSCIATEDING5");
	public static final ConditionCode CONFIGFIELDS_CODE_ALREADY_EXISTS = new ConditionCode("CONFIGFIELDS_CODE_ALREADY_EXISTS");
	public static final ConditionCode CONFIGFIELDS_CODE_VALUE_DELETE = new ConditionCode("CONFIGFIELDS_CODE_VALUE_DELETE");
	public static final ConditionCode CONFIGFIELDS_SELECT_CONFIRM_OPTION = new ConditionCode("CONFIGFIELDS_SELECT_CONFIRM_OPTION");
	public static final ConditionCode CONFIGFIELDS_CODE_TWODIGIT = new ConditionCode("CONFIGFIELDS_CODE_TWODIGIT");
	public static final ConditionCode CONFIGFIELDS_CODE_TWOCHAR = new ConditionCode("CONFIGFIELDS_CODE_TWOCHAR");
	public static final ConditionCode CONFIG_PO_INVALID_CODE = new ConditionCode("CONFIG_PO_INVALID_CODE");
	public static final ConditionCode CONFIG_PO_EXISTS = new ConditionCode("CONFIG_PO_EXISTS");
	public static final ConditionCode CONFIG_PODIV_EXISTS = new ConditionCode("CONFIG_PODIV_EXISTS");
	public static final ConditionCode CONFIG_POSEARCH_PODIV_NOTEXISTS = new ConditionCode("CONFIG_POSEARCH_PODIV_NOTEXISTS");
	
	public static final ConditionCode CONFIG_SAVE_IVALID_PODETAILS = new ConditionCode("CONFIG_SAVE_IVALID_PODETAILS");
	public static final ConditionCode CONFIG_DELETE_IVALID_PODETAILS = new ConditionCode("CONFIG_DELETE_IVALID_PODETAILS");
	
	
	public static final ConditionCode SCHEDULE_TEMPLATE_ACTIVE = new ConditionCode("SCHEDULE_TEMPLATE_ACTIVE");
	public static final ConditionCode SCHEDULE_TEMPLATE_DELETED = new ConditionCode("SCHEDULE_TEMPLATE_DELETED");
	public static final ConditionCode SCHEDULE_TEMPLATE_ASSOCIATED_TO_SCHEDULES = new ConditionCode("SCHEDULE_TEMPLATE_ASSOCIATED_TO_SCHEDULES");
	public static final ConditionCode ACTION_REQUIRED = new ConditionCode("ACTION_REQUIRED");
	public static final ConditionCode NO_UNPROCESSED_DEOBLIGATE_RECORDS = new ConditionCode("NO_UNPROCESSED_DEOBLIGATE_RECORDS");
	
	public static final ConditionCode ERROR_TIME = new ConditionCode("ERROR_TIME");
    
}

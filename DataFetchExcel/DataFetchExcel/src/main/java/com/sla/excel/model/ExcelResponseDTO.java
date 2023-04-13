package com.sla.excel.model;

public class ExcelResponseDTO {

	//SLA-1
	private String sr;
	private String deliverable;
	private String track;
	private String quarter;
	private String contractualDate;
	private String submissionDate;
	private long slaCalculation;
	//SLA-2
	// Blnak data 
	//SLA-3
	//private String sr;
	private String ticketId;
	private String blocks;
	private String createdDate;
	private int month;
	private String severity;
	private String resolutionDate;
	private double daysVariance;
	private int appliedSeverityLevel;

	//SLA-4
	private String months;
	private double CurrentMonthWAC;
	private double baseline;
	private double variance;
	private double calculationofSeverityLevel;
	private String remarks;
	private int lDPoints;
	private int quarterlyLDPoints;
	private int applicableLD;
	//SLA-5
	private String environmentTypes;
	private String aBDMBuildingBlocks;
	private String Type;
	private String softwareLibrary;
	private String runningVersion;
	private String latestVersion;
	private String openSourceEnterprise;
	private String comments;
	private String n1Compliance;
	//SLA-6
	//private String sr;
	private String name;
	private String onboardingDate;
	private String title;
	private String profileDeploymentDate;
	private String quarterStartDate;
	private String deploymentGap;
	//private int appliedSeverityLevel;
	//private String remarks;

	//SLA-7
	//private String sr;
	private String oMResource;
	//private String onboardingDate;
	//private String title;
	private String plannedDate;
	private int target;
	
	private String appliedSeverityLevels;
	//private String remarks;
	private String cIEResource;

	//SLA-8
	//private String sr;
	//private String title ;
	private String onboardedResource  ;
	private String exitingresource  ;
	//private int appliedSeverityLevel  ;

	//SLA-9
	//private String sr;
	private String profile ;
	private String application;
	//private String onboardedResource  ;
	//private String exitingresource  ;
	//private int appliedSeverityLevel  ;

	//SLA-10
	//private String sr;
	//private String profile ;
	//private String application;
	//private String onboardedResource  ;
	//private String exitingresource  ;
	private String exitDate  ;
	//private int appliedSeverityLevel  ;

	//SLA-11
	private String core;
	//private String sr;
	private String resource ;
	private String leavesTaken;
	private String effectiveWorkngDays  ;
	//private String remarks  ;

	//SLA-12
	//SLA-13
	//SLA-14
	//SLA-15
	//private String sr;
	private String fy ;
	//private String month;
	private String date  ;
	private String iNNO  ;
	private String issueType  ;
	private String applicationimpacted  ;
	private String category  ;
	private String notifiedTime  ;
	private String timestamp  ;
	private String responseTime  ;
	private String responseTimeSLA  ;
	private String actiontaken  ;
	private String closuredate  ;
	private String closuretime   ;
	private String resolutionTime  ;
	
	//private String remarks  ;
	public String getSr() {
		return sr;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getExitDate() {
		return exitDate;
	}
	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}
	public void setSr(String sr) {
		this.sr = sr;
	}
	public String getDeliverable() {
		return deliverable;
	}
	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getContractualDate() {
		return contractualDate;
	}
	public void setContractualDate(String contractualDate) {
		this.contractualDate = contractualDate;
	}
	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}
	public long getSlaCalculation() {
		return slaCalculation;
	}
	public void setSlaCalculation(long slaCalculation) {
		this.slaCalculation = slaCalculation;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getBlocks() {
		return blocks;
	}
	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public double getDaysVariance() {
		return daysVariance;
	}
	public void setDaysVariance(double daysVariance) {
		this.daysVariance = daysVariance;
	}
	public int getAppliedSeverityLevel() {
		return appliedSeverityLevel;
	}
	public void setAppliedSeverityLevel(int appliedSeverityLevel) {
		this.appliedSeverityLevel = appliedSeverityLevel;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public double getCurrentMonthWAC() {
		return CurrentMonthWAC;
	}
	public void setCurrentMonthWAC(double currentMonthWAC) {
		CurrentMonthWAC = currentMonthWAC;
	}
	public double getBaseline() {
		return baseline;
	}
	public void setBaseline(double baseline) {
		this.baseline = baseline;
	}
	public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	public double getCalculationofSeverityLevel() {
		return calculationofSeverityLevel;
	}
	public void setCalculationofSeverityLevel(double calculationofSeverityLevel) {
		this.calculationofSeverityLevel = calculationofSeverityLevel;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getlDPoints() {
		return lDPoints;
	}
	public void setlDPoints(int lDPoints) {
		this.lDPoints = lDPoints;
	}
	public int getQuarterlyLDPoints() {
		return quarterlyLDPoints;
	}
	public void setQuarterlyLDPoints(int quarterlyLDPoints) {
		this.quarterlyLDPoints = quarterlyLDPoints;
	}
	public int getApplicableLD() {
		return applicableLD;
	}
	public void setApplicableLD(int applicableLD) {
		this.applicableLD = applicableLD;
	}
	public String getaBDMBuildingBlocks() {
		return aBDMBuildingBlocks;
	}
	public void setaBDMBuildingBlocks(String aBDMBuildingBlocks) {
		this.aBDMBuildingBlocks = aBDMBuildingBlocks;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getSoftwareLibrary() {
		return softwareLibrary;
	}
	public void setSoftwareLibrary(String softwareLibrary) {
		this.softwareLibrary = softwareLibrary;
	}
	public String getRunningVersion() {
		return runningVersion;
	}
	public void setRunningVersion(String runningVersion) {
		this.runningVersion = runningVersion;
	}
	public String getLatestVersion() {
		return latestVersion;
	}
	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}
	public String getOpenSourceEnterprise() {
		return openSourceEnterprise;
	}
	public void setOpenSourceEnterprise(String openSourceEnterprise) {
		this.openSourceEnterprise = openSourceEnterprise;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getN1Compliance() {
		return n1Compliance;
	}
	public void setN1Compliance(String n1Compliance) {
		this.n1Compliance = n1Compliance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOnboardingDate() {
		return onboardingDate;
	}
	public void setOnboardingDate(String onboardingDate) {
		this.onboardingDate = onboardingDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProfileDeploymentDate() {
		return profileDeploymentDate;
	}
	public void setProfileDeploymentDate(String profileDeploymentDate) {
		this.profileDeploymentDate = profileDeploymentDate;
	}
	public String getQuarterStartDate() {
		return quarterStartDate;
	}
	public void setQuarterStartDate(String quarterStartDate) {
		this.quarterStartDate = quarterStartDate;
	}
	public String getDeploymentGap() {
		return deploymentGap;
	}
	public void setDeploymentGap(String deploymentGap) {
		this.deploymentGap = deploymentGap;
	}
	public String getoMResource() {
		return oMResource;
	}
	public void setoMResource(String oMResource) {
		this.oMResource = oMResource;
	}
	public String getPlannedDate() {
		return plannedDate;
	}
	public void setPlannedDate(String plannedDate) {
		this.plannedDate = plannedDate;
	}
	
	
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getcIEResource() {
		return cIEResource;
	}
	public void setcIEResource(String cIEResource) {
		this.cIEResource = cIEResource;
	}
	public String getOnboardedResource() {
		return onboardedResource;
	}
	public void setOnboardedResource(String onboardedResource) {
		this.onboardedResource = onboardedResource;
	}
	public String getExitingresource() {
		return exitingresource;
	}
	public void setExitingresource(String exitingresource) {
		this.exitingresource = exitingresource;
	}
	public String getCore() {
		return core;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getLeavesTaken() {
		return leavesTaken;
	}
	public void setLeavesTaken(String leavesTaken) {
		this.leavesTaken = leavesTaken;
	}
	public String getEffectiveWorkngDays() {
		return effectiveWorkngDays;
	}
	public void setEffectiveWorkngDays(String effectiveWorkngDays) {
		this.effectiveWorkngDays = effectiveWorkngDays;
	}
	public String getFy() {
		return fy;
	}
	public void setFy(String fy) {
		this.fy = fy;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getiNNO() {
		return iNNO;
	}
	public void setiNNO(String iNNO) {
		this.iNNO = iNNO;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getApplicationimpacted() {
		return applicationimpacted;
	}
	public void setApplicationimpacted(String applicationimpacted) {
		this.applicationimpacted = applicationimpacted;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNotifiedTime() {
		return notifiedTime;
	}
	public void setNotifiedTime(String notifiedTime) {
		this.notifiedTime = notifiedTime;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getResponseTimeSLA() {
		return responseTimeSLA;
	}
	public void setResponseTimeSLA(String responseTimeSLA) {
		this.responseTimeSLA = responseTimeSLA;
	}
	public String getActiontaken() {
		return actiontaken;
	}
	public void setActiontaken(String actiontaken) {
		this.actiontaken = actiontaken;
	}
	public String getClosuredate() {
		return closuredate;
	}
	public void setClosuredate(String closuredate) {
		this.closuredate = closuredate;
	}
	public String getClosuretime() {
		return closuretime;
	}
	public void setClosuretime(String closuretime) {
		this.closuretime = closuretime;
	}
	public String getResolutionTime() {
		return resolutionTime;
	}
	public void setResolutionTime(String resolutionTime) {
		this.resolutionTime = resolutionTime;
	}

	public String getAppliedSeverityLevels() {
		return appliedSeverityLevels;
	}
	public void setAppliedSeverityLevels(String appliedSeverityLevels) {
		this.appliedSeverityLevels = appliedSeverityLevels;
	}
	public String getEnvironmentTypes() {
		return environmentTypes;
	}
	public void setEnvironmentTypes(String environmentTypes) {
		this.environmentTypes = environmentTypes;
	}
	


}

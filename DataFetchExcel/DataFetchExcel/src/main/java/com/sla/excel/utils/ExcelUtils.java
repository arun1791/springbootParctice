package com.sla.excel.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.sla.excel.constant.SlaConstant;
import com.sla.excel.model.ExcelResponseDTO;
import com.sla.excel.model.QuaterReportDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import lombok.extern.slf4j.Slf4j;

@Component
//@Slf4j
public class ExcelUtils {

	@Autowired
	ResourceLoader resourceLoader;

	SlaConstant slaConstant;

	@Value("${slaExcelFile}")
	private String file;

	@Value("${slaExcelFileQ4}")
	private String filetwo;

	private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);
	private SimpleDateFormat sdfn = new SimpleDateFormat("dd MMM yy", Locale.ENGLISH);

	public List<ExcelResponseDTO> readCSV(QuaterReportDTO quatorReportDTO) throws URISyntaxException {
		log.info("In ExcelUtils >> readCSV() Method-----");
		List<ExcelResponseDTO> response = new ArrayList<>();
		String slaType = quatorReportDTO.getSlaType();
		String slaQuater = quatorReportDTO.getQuaterDate();
		if (slaQuater.equalsIgnoreCase(slaConstant.fisrtQuter)) {
			response = fetachDataFromexcelOne(slaQuater, slaType);
		}

		else if (slaQuater.equalsIgnoreCase(slaConstant.secondQuter)) {
			response = fetachDataFromexcelQuterTwo(slaQuater, slaType);
		} else if (slaQuater.equalsIgnoreCase(slaConstant.thriedQuter)) {
			response = fetachDataFromexcelQuterThree(slaQuater, slaType);
		} else if (slaQuater.equalsIgnoreCase(slaConstant.fourthQuter)) {
			response = fetachDataFromexcelQuterFour(slaQuater, slaType);
		}
		System.out.println(slaType);
		return response;
	}

	public int calcluationDateDiff(Date date1, Date date2) {
		long date1InMs = date1.getTime();
		long date2InMs = date2.getTime();
		long timeDiff = 0;
		timeDiff = date1InMs - date2InMs;
		int daysDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));
		return daysDiff;
	}

	public List<ExcelResponseDTO> fetachDataFromexcelQuterThree(String slaQuater, String slaType) {
		List<ExcelResponseDTO> response = new ArrayList<>();
		Workbook workbook = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:files/" + file);
			File filedata = resource.getFile();
			String absolutePath = filedata.getAbsolutePath();
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			workbook = WorkbookFactory.create(new File(absolutePath));
			// Retrieving the number of sheets in the Workbook
			log.info("Number of sheets: ", workbook.getNumberOfSheets());
			// Print all sheets name
			workbook.forEach(sheet -> {
				log.info(" => " + sheet.getSheetName());
				// Create a DataFormatter to format and get each cell's value as String
				DataFormatter dataFormatter = new DataFormatter();
				// loop through all rows and columns and create Course object
				int index = 0;
				ExcelResponseDTO res = null;
				switch (slaType) {
				case "SLA-1":
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {

							if (index++ == 0)
								continue;
							if (index == 2) {

							} else {
								
								res = new ExcelResponseDTO();
								res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
								//res.setDeliverable((row.getCell(2).getStringCellValue().replace("\n", "<br/>")));
								//System.out.println("----"+(row.getCell(2).getStringCellValue().replace("\n", "<br/>")));
								try {
									res.setDeliverable(dataFormatter.formatCellValue(row.getCell(2)));
									// res.setDob(sdf.parse(dataFormatter.formatCellValue(row.getCell(2))));
									res.setTrack(dataFormatter.formatCellValue(row.getCell(3)));
									res.setQuarter(dataFormatter.formatCellValue(row.getCell(4)));
									Date date1 = sdfn.parse(dataFormatter.formatCellValue(row.getCell(5)));
									Date date2 = sdfn.parse(dataFormatter.formatCellValue(row.getCell(6)));
									int differnceDate = calcluationDateDiff(date2, date1);
									res.setContractualDate(dataFormatter.formatCellValue(row.getCell(5)));
									res.setSubmissionDate(dataFormatter.formatCellValue(row.getCell(6)));
									res.setSlaCalculation(differnceDate);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}

						}
					}

				case "SLA-2":
					// statemnet
					break;

				case "SLA-3":
					// statemnet
					// SLA-3
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res = new ExcelResponseDTO();
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setTicketId(dataFormatter.formatCellValue(row.getCell(2)));
										res.setBlocks(dataFormatter.formatCellValue(row.getCell(3)));
										res.setCreatedDate(dataFormatter.formatCellValue(row.getCell(4)));
										res.setMonth((int) row.getCell(5).getNumericCellValue());
										res.setSeverity(dataFormatter.formatCellValue(row.getCell(6)));
										res.setResolutionDate(dataFormatter.formatCellValue(row.getCell(7)));
										res.setDaysVariance(row.getCell(8).getNumericCellValue());
										res.setAppliedSeverityLevel((int) row.getCell(9).getNumericCellValue());
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}
						}
					}
					break;

				case "SLA-4":
					// statemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							// SLA-4
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								res.setMonths(dataFormatter.formatCellValue(row.getCell(1)));
								res.setCurrentMonthWAC((row.getCell(2).getNumericCellValue()));
								try {
									res.setBaseline((row.getCell(3).getNumericCellValue()));
									res.setVariance((row.getCell(4).getNumericCellValue()));
									res.setCalculationofSeverityLevel((row.getCell(5).getNumericCellValue()));
									res.setRemarks(dataFormatter.formatCellValue(row.getCell(6)));
									res.setlDPoints((int) (row.getCell(7).getNumericCellValue()));
									res.setQuarterlyLDPoints((int) (row.getCell(8).getNumericCellValue()));
									res.setApplicableLD((int) (row.getCell(9).getNumericCellValue()));
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}
						}
					}
					break;
				case "SLA-5":
					// statemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						// SLA-5
						for (Row row : sheet) {
							// SLA-4
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								res.setEnvironmentTypes(dataFormatter.formatCellValue(row.getCell(0)));
								res.setaBDMBuildingBlocks(dataFormatter.formatCellValue(row.getCell(1)));
								res.setType(dataFormatter.formatCellValue(row.getCell(2)));
								res.setSoftwareLibrary(dataFormatter.formatCellValue(row.getCell(3)));
								res.setRunningVersion(dataFormatter.formatCellValue(row.getCell(4)));
								res.setLatestVersion(dataFormatter.formatCellValue(row.getCell(5)));
								res.setOpenSourceEnterprise(dataFormatter.formatCellValue(row.getCell(6)));
								res.setComments(dataFormatter.formatCellValue(row.getCell(7)));
								res.setN1Compliance(dataFormatter.formatCellValue(row.getCell(8)));
								response.add(res);
							}
						}
					}
					break;
				case "SLA-6":
					// satatemnet
					// SLA-6
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setName(dataFormatter.formatCellValue(row.getCell(2)));
										res.setOnboardingDate(dataFormatter.formatCellValue(row.getCell(3)));
										res.setTitle(dataFormatter.formatCellValue(row.getCell(4)));
										res.setProfileDeploymentDate(dataFormatter.formatCellValue(row.getCell(5)));
										res.setQuarterStartDate(dataFormatter.formatCellValue(row.getCell(6)));
										res.setDeploymentGap(dataFormatter.formatCellValue(row.getCell(7)));
										res.setAppliedSeverityLevel((int) (row.getCell(8).getNumericCellValue()));
										res.setRemarks(dataFormatter.formatCellValue(row.getCell(9)));
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}
						}
					}
					break;
				case "SLA-7":
					// satatemnet
					// SLA-7
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
									res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
									res.setoMResource(dataFormatter.formatCellValue(row.getCell(2)));
									res.setOnboardingDate(dataFormatter.formatCellValue(row.getCell(3)));
									res.setTitle(dataFormatter.formatCellValue(row.getCell(4)));
									res.setPlannedDate(dataFormatter.formatCellValue(row.getCell(5)));
									
//
//									try {
//										Integer testData = (int) row.getCell(6).getNumericCellValue();
//										Integer testAppliedSeverity = (int) row.getCell(7).getNumericCellValue();
//										System.out.println("--kk-=----------" + testData);
//										if (testData > 0 && testData != null && testAppliedSeverity > 0
//												&& testAppliedSeverity != null) {
											try {
												res.setTarget((int) row.getCell(6).getNumericCellValue());
												res.setAppliedSeverityLevel((int) row.getCell(7).getNumericCellValue());

											} catch (Exception e) {
												log.error(e.getMessage(), e);
											}
//										} else {
//											//res.setTarget(Integer.valueOf(" "));
//											// res.setAppliedSeverityLevels(" ");
//											//res.setAppliedSeverityLevel(Integer.valueOf(" "));
//										}
//									} catch (Exception e) {
//										log.error(e.getMessage(), e);
//									}

									// res.setAppliedSeverityLevels(dataFormatter.formatCellValue(row.getCell(7)));
									// res.setTarget((int) row.getCell(6).getNumericCellValue());
									 //res.setAppliedSeverityLevel((int) row.getCell(7).getNumericCellValue());
									res.setRemarks(dataFormatter.formatCellValue(row.getCell(8)));
//									String nameNew = dataFormatter.formatCellValue(row.getCell(2));
//									if (nameNew.equalsIgnoreCase("CIE Resource(s)")) {
//										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
//										res.setcIEResource(dataFormatter.formatCellValue(row.getCell(2)));
//										res.setOnboardingDate(dataFormatter.formatCellValue(row.getCell(3)));
//										res.setTitle(dataFormatter.formatCellValue(row.getCell(4)));
//										res.setRemarks(dataFormatter.formatCellValue(row.getCell(5)));
//									}
									}
									else
									{
										int count=3;
										for(int i=1;i<=count;i++)
										{
											if(count<3)
											{
												continue;
											}
											else
											{
												break;
											}
										}
										
										
									}

									response.add(res);

								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;

				case "SLA-8":
					// satatemnet
					// SLA-7
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setTitle(dataFormatter.formatCellValue(row.getCell(2)));
										res.setOnboardedResource(dataFormatter.formatCellValue(row.getCell(3)));
										res.setExitingresource(dataFormatter.formatCellValue(row.getCell(4)));
										res.setAppliedSeverityLevel((int) (row.getCell(5).getNumericCellValue()));
									} else {
										break;
									}
									response.add(res);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;
				case "SLA-9":
					// satatemnet
					// SLA-7
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setProfile(dataFormatter.formatCellValue(row.getCell(2)));
										res.setApplication(dataFormatter.formatCellValue(row.getCell(3)));
										res.setOnboardedResource(dataFormatter.formatCellValue(row.getCell(4)));
										res.setExitingresource(dataFormatter.formatCellValue(row.getCell(5)));
										res.setAppliedSeverityLevel((int) (row.getCell(6).getNumericCellValue()));
									} else {
										break;
									}
									response.add(res);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;
				case "SLA-10":
					// satatemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setProfile(dataFormatter.formatCellValue(row.getCell(2)));
										res.setApplication(dataFormatter.formatCellValue(row.getCell(3)));
										res.setOnboardedResource(dataFormatter.formatCellValue(row.getCell(4)));
										res.setExitingresource(dataFormatter.formatCellValue(row.getCell(5)));
										res.setExitDate(dataFormatter.formatCellValue(row.getCell(6)));
										res.setAppliedSeverityLevel((int) (row.getCell(7).getNumericCellValue()));
									} else {
										break;
									}
									response.add(res);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;
				case "SLA-11":
					// satatemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setCore(dataFormatter.formatCellValue(row.getCell(0)));
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setResource(dataFormatter.formatCellValue(row.getCell(2)));
										res.setLeavesTaken(dataFormatter.formatCellValue(row.getCell(5)));
										res.setEffectiveWorkngDays(dataFormatter.formatCellValue(row.getCell(6)));
										res.setRemarks(dataFormatter.formatCellValue(row.getCell(7)));
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// res.setsLA_Calculation(dataFormatter.formatCellValue(row.getCell(7)));
								response.add(res);
							}
						}
					}
					break;
				case "SLA-15":
					// statemnet
					// SLA-3
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(0)));
										res.setFy(dataFormatter.formatCellValue(row.getCell(1)));
										res.setMonths(dataFormatter.formatCellValue(row.getCell(2)));
										res.setDate(dataFormatter.formatCellValue(row.getCell(3)));
										res.setiNNO(dataFormatter.formatCellValue(row.getCell(4)));
										res.setIssueType(dataFormatter.formatCellValue(row.getCell(5)));
										res.setApplicationimpacted(dataFormatter.formatCellValue(row.getCell(6)));
										res.setCategory(dataFormatter.formatCellValue(row.getCell(7)));
										res.setSeverity(dataFormatter.formatCellValue(row.getCell(8)));
										res.setNotifiedTime(dataFormatter.formatCellValue(row.getCell(9)));
										res.setTimestamp(dataFormatter.formatCellValue(row.getCell(10)));
										res.setResponseTime(dataFormatter.formatCellValue(row.getCell(11)));
										res.setResponseTimeSLA(dataFormatter.formatCellValue(row.getCell(12)));
										res.setActiontaken(dataFormatter.formatCellValue(row.getCell(13)));
										res.setClosuredate(dataFormatter.formatCellValue(row.getCell(14)));
										res.setClosuretime(dataFormatter.formatCellValue(row.getCell(15)));
										res.setResolutionTime(dataFormatter.formatCellValue(row.getCell(16)));
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// res.setsLA_Calculation(dataFormatter.formatCellValue(row.getCell(7)));
								response.add(res);
							}
						}
					}
					break;
				}

			});
		} catch (EncryptedDocumentException | IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return response;
	}

	public List<ExcelResponseDTO> fetachDataFromexcelQuterFour(String slaQuater, String slaType) {

		List<ExcelResponseDTO> response = new ArrayList<>();
		Workbook workbook = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:files/" + filetwo);
			File filedata = resource.getFile();
			String absolutePath = filedata.getAbsolutePath();
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			workbook = WorkbookFactory.create(new File(absolutePath));
			// Retrieving the number of sheets in the Workbook
			log.info("Number of sheets: ", workbook.getNumberOfSheets());
			// Print all sheets name
			workbook.forEach(sheet -> {
				log.info(" => " + sheet.getSheetName());
				// Create a DataFormatter to format and get each cell's value as String
				DataFormatter dataFormatter = new DataFormatter();
				// loop through all rows and columns and create Course object
				int index = 0;
				ExcelResponseDTO res = null;
				switch (slaType) {
				case "SLA-1":
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {

							if (index++ == 0)
								continue;
							if (index == 2) {

							} else {
								res = new ExcelResponseDTO();
								res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
								res.setDeliverable(dataFormatter.formatCellValue(row.getCell(2)));
								try {
									// res.setDob(sdf.parse(dataFormatter.formatCellValue(row.getCell(2))));
									res.setTrack(dataFormatter.formatCellValue(row.getCell(3)));
									res.setQuarter(dataFormatter.formatCellValue(row.getCell(4)));
									Date date1 = sdfn.parse(dataFormatter.formatCellValue(row.getCell(5)));
									Date date2 = sdfn.parse(dataFormatter.formatCellValue(row.getCell(6)));
									int differnceDate = calcluationDateDiff(date2, date1);
									res.setContractualDate(dataFormatter.formatCellValue(row.getCell(5)));
									res.setSubmissionDate(dataFormatter.formatCellValue(row.getCell(6)));
									res.setSlaCalculation(differnceDate);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}

						}
					}

				case "SLA-2":
					// statemnet
					break;

				case "SLA-3":
					// statemnet
					// SLA-3
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res = new ExcelResponseDTO();
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setTicketId(dataFormatter.formatCellValue(row.getCell(2)));
										res.setBlocks(dataFormatter.formatCellValue(row.getCell(3)));
										res.setCreatedDate(dataFormatter.formatCellValue(row.getCell(4)));
										res.setMonth((int) row.getCell(5).getNumericCellValue());
										res.setSeverity(dataFormatter.formatCellValue(row.getCell(6)));
										res.setResolutionDate(dataFormatter.formatCellValue(row.getCell(7)));
										res.setDaysVariance(row.getCell(8).getNumericCellValue());
										res.setAppliedSeverityLevel((int) row.getCell(9).getNumericCellValue());
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}
						}
					}
					break;

				case "SLA-4":
					// statemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							// SLA-4
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								res.setMonths(dataFormatter.formatCellValue(row.getCell(1)));
								res.setCurrentMonthWAC((row.getCell(2).getNumericCellValue()));
								try {
									res.setBaseline((row.getCell(3).getNumericCellValue()));
									res.setVariance((row.getCell(4).getNumericCellValue()));
									res.setCalculationofSeverityLevel((row.getCell(5).getNumericCellValue()));
									res.setRemarks(dataFormatter.formatCellValue(row.getCell(6)));
									res.setlDPoints((int) (row.getCell(7).getNumericCellValue()));
									res.setQuarterlyLDPoints((int) (row.getCell(8).getNumericCellValue()));
									res.setApplicableLD((int) (row.getCell(9).getNumericCellValue()));
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}
						}
					}
					break;
				case "SLA-5":
					// statemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						// SLA-5
						for (Row row : sheet) {
							// SLA-4
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								res.setEnvironmentTypes(dataFormatter.formatCellValue(row.getCell(0)));
								res.setaBDMBuildingBlocks(dataFormatter.formatCellValue(row.getCell(1)));
								res.setType(dataFormatter.formatCellValue(row.getCell(2)));
								res.setSoftwareLibrary(dataFormatter.formatCellValue(row.getCell(3)));
								res.setRunningVersion(dataFormatter.formatCellValue(row.getCell(4)));
								res.setLatestVersion(dataFormatter.formatCellValue(row.getCell(5)));
								res.setOpenSourceEnterprise(dataFormatter.formatCellValue(row.getCell(6)));
								res.setComments(dataFormatter.formatCellValue(row.getCell(7)));
								res.setN1Compliance(dataFormatter.formatCellValue(row.getCell(8)));
								response.add(res);
							}
						}
					}
					break;
				case "SLA-6":
					// satatemnet
					// SLA-6
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setName(dataFormatter.formatCellValue(row.getCell(2)));
										res.setOnboardingDate(dataFormatter.formatCellValue(row.getCell(3)));
										res.setTitle(dataFormatter.formatCellValue(row.getCell(4)));
										res.setProfileDeploymentDate(dataFormatter.formatCellValue(row.getCell(5)));
										res.setQuarterStartDate(dataFormatter.formatCellValue(row.getCell(6)));
										res.setDeploymentGap(dataFormatter.formatCellValue(row.getCell(7)));
										res.setAppliedSeverityLevel((int) (row.getCell(8).getNumericCellValue()));
										res.setRemarks(dataFormatter.formatCellValue(row.getCell(9)));
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								response.add(res);
							}
						}
					}
					break;
				case "SLA-7":
					// satatemnet
					// SLA-7
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								try {

									res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
									res.setoMResource(dataFormatter.formatCellValue(row.getCell(2)));
									res.setOnboardingDate(dataFormatter.formatCellValue(row.getCell(3)));
									res.setTitle((row.getCell(4).getStringCellValue()));
									res.setPlannedDate(dataFormatter.formatCellValue(row.getCell(5)));

									// try {
									// Integer testData = (int) row.getCell(6).getNumericCellValue();
									// Integer testAppliedSeverity = (int) row.getCell(7).getNumericCellValue();
									// System.out.println("--kk-=----------" + testData);
									// if (testData > 0 && testData != null && testAppliedSeverity > 0
									// && testAppliedSeverity != null) {
									// try {
									// res.setTarget((int) row.getCell(6).getNumericCellValue());
									// res.setAppliedSeverityLevel((int) row.getCell(7).getNumericCellValue());
									//
									// } catch (Exception e) {
									// log.error(e.getMessage(), e);
									// }
									// } else {
									// res.setTarget(Integer.valueOf(" "));
									// res.setAppliedSeverityLevels(" ");
									// res.setAppliedSeverityLevel(Integer.valueOf(" "));
									// }
									// } catch (Exception e) {
									// log.error(e.getMessage(), e);
									// }

									try {
										res.setTarget((int) row.getCell(6).getNumericCellValue());
										res.setAppliedSeverityLevel((int) row.getCell(7).getNumericCellValue());

									} catch (Exception e) {
										log.error(e.getMessage(), e);
									}
									// res.setAppliedSeverityLevels(dataFormatter.formatCellValue(row.getCell(7)));
									res.setRemarks(dataFormatter.formatCellValue(row.getCell(8)));
									// String nameNew = dataFormatter.formatCellValue(row.getCell(2));
									// if (nameNew.equalsIgnoreCase("CIE Resource(s)")) {
									// res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
									// res.setcIEResource(dataFormatter.formatCellValue(row.getCell(2)));
									// res.setOnboardingDate(dataFormatter.formatCellValue(row.getCell(3)));
									// res.setTitle(dataFormatter.formatCellValue(row.getCell(4)));
									// res.setRemarks(dataFormatter.formatCellValue(row.getCell(5)));
									// }

									response.add(res);

								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;

				case "SLA-8":
					// satatemnet
					// SLA-7
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setTitle(dataFormatter.formatCellValue(row.getCell(2)));
										res.setOnboardedResource(dataFormatter.formatCellValue(row.getCell(3)));
										res.setExitingresource(dataFormatter.formatCellValue(row.getCell(4)));
										res.setAppliedSeverityLevel((int) (row.getCell(5).getNumericCellValue()));
									} else {
										break;
									}
									response.add(res);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;
				case "SLA-9":
					// satatemnet
					// SLA-7
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setProfile(dataFormatter.formatCellValue(row.getCell(2)));
										res.setApplication(dataFormatter.formatCellValue(row.getCell(3)));
										res.setOnboardedResource(dataFormatter.formatCellValue(row.getCell(4)));
										res.setExitingresource(dataFormatter.formatCellValue(row.getCell(5)));
										res.setAppliedSeverityLevel((int) (row.getCell(6).getNumericCellValue()));
									} else {
										break;
									}
									response.add(res);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;
				case "SLA-10":
					// satatemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setProfile(dataFormatter.formatCellValue(row.getCell(2)));
										res.setApplication(dataFormatter.formatCellValue(row.getCell(3)));
										res.setOnboardedResource(dataFormatter.formatCellValue(row.getCell(4)));
										res.setExitingresource(dataFormatter.formatCellValue(row.getCell(5)));
										res.setExitDate(dataFormatter.formatCellValue(row.getCell(6)));
										res.setAppliedSeverityLevel((int) (row.getCell(7).getNumericCellValue()));
									} else {
										break;
									}
									response.add(res);
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// response.add(res);
							}
						}
					}
					break;
				case "SLA-11":
					// satatemnet
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setCore(dataFormatter.formatCellValue(row.getCell(0)));
										res.setSr(dataFormatter.formatCellValue(row.getCell(1)));
										res.setResource(dataFormatter.formatCellValue(row.getCell(2)));
										res.setLeavesTaken(dataFormatter.formatCellValue(row.getCell(5)));
										res.setEffectiveWorkngDays(dataFormatter.formatCellValue(row.getCell(6)));
										res.setRemarks(dataFormatter.formatCellValue(row.getCell(7)));
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// res.setsLA_Calculation(dataFormatter.formatCellValue(row.getCell(7)));
								response.add(res);
							}
						}
					}
					break;
				case "SLA-15":
					// statemnet
					// SLA-3
					if (sheet.getSheetName().equalsIgnoreCase(slaType)) {
						for (Row row : sheet) {
							if (index++ == 0)
								continue;
							if (index == 2 || index == 3) {
							} else {
								res = new ExcelResponseDTO();
								try {
									String data = dataFormatter.formatCellValue(row.getCell(1));
									if (data != null && !data.isBlank()) {
										res.setSr(dataFormatter.formatCellValue(row.getCell(0)));
										res.setFy(dataFormatter.formatCellValue(row.getCell(1)));
										res.setMonths(dataFormatter.formatCellValue(row.getCell(2)));
										res.setDate(dataFormatter.formatCellValue(row.getCell(3)));
										res.setiNNO(dataFormatter.formatCellValue(row.getCell(4)));
										res.setIssueType(dataFormatter.formatCellValue(row.getCell(5)));
										res.setApplicationimpacted(dataFormatter.formatCellValue(row.getCell(6)));
										res.setCategory(dataFormatter.formatCellValue(row.getCell(7)));
										res.setSeverity(dataFormatter.formatCellValue(row.getCell(8)));
										res.setNotifiedTime(dataFormatter.formatCellValue(row.getCell(9)));
										res.setTimestamp(dataFormatter.formatCellValue(row.getCell(10)));
										res.setResponseTime(dataFormatter.formatCellValue(row.getCell(11)));
										res.setResponseTimeSLA(dataFormatter.formatCellValue(row.getCell(12)));
										res.setActiontaken(dataFormatter.formatCellValue(row.getCell(13)));
										res.setClosuredate(dataFormatter.formatCellValue(row.getCell(14)));
										res.setClosuretime(dataFormatter.formatCellValue(row.getCell(15)));
										res.setResolutionTime(dataFormatter.formatCellValue(row.getCell(16)));
									} else {
										break;
									}
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
								// res.setsLA_Calculation(dataFormatter.formatCellValue(row.getCell(7)));
								response.add(res);
							}
						}
					}
					break;
				}

			});
		} catch (EncryptedDocumentException | IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (workbook != null)
					workbook.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return response;
	}

	public List<ExcelResponseDTO> fetachDataFromexcelOne(String slaQuater, String slaType) {
		return null;
	}

	public List<ExcelResponseDTO> fetachDataFromexcelQuterTwo(String slaQuater, String slaType) {
		return null;
	}

}

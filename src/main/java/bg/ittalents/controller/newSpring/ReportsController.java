package bg.ittalents.controller.newSpring;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

import bg.ittalents.model.DAO.IActivityDAO;
import bg.ittalents.model.POJO.Activity;
import bg.ittalents.model.POJO.ActivityStatus;
import bg.ittalents.model.POJO.Project;
import bg.ittalents.model.exception.DBException;
import bg.ittalents.model.exception.WorkPlanDAOException;


@Controller
@SessionAttributes({ "project" })
public class ReportsController {

	@RequestMapping(value = "/AllReports", method = RequestMethod.GET)
	public String allReports(Project project) {
		typeReport(project);
		statusReport(project);
		DaysToDoneReport(project);
		return "reports";
	}

	@RequestMapping(value = "/ReportsType", method = RequestMethod.GET)
	public void typeReport(Project project) {
		List<String> typeOfIssues = Arrays.asList("Bug", "Task", "Sub-task",
				"Story", "Epic");
		System.out.println(project);
		Map<String, ArrayList<Activity>> issuesByType = new HashMap<String, ArrayList<Activity>>();
		try {
			for (String type : typeOfIssues) {
				if (!issuesByType.containsKey(type)) {
					issuesByType.put(type, new ArrayList<Activity>());
				}

				issuesByType.get(type).addAll(
						IActivityDAO.getDAO("db")
								.getAllActivitiesWithTypeInWholeProject(type,
										project.getId()));

			}

			DefaultPieDataset dataset = new DefaultPieDataset();
			for (String type : issuesByType.keySet()) {
				dataset.setValue(type, issuesByType.get(type).size());
			}
			List<Comparable> keys = dataset.getKeys();
			for (Comparable a : keys) {
				// System.out.println(dataset.getValue(a));
				if (dataset.getValue(a) == null
						|| (double) dataset.getValue(a) == 0) {
					dataset.remove(a);
				}
			}
			JFreeChart chart = ChartFactory.createPieChart("Issues' types", // chart
					dataset, // data
					true, // include legend
					true, false);
			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
					"{0} - count:{1} -{2}"));
			File typeChart = new File("C:\\FPfiles\\Images\\Reports\\type.jpeg");
			ChartUtilities.saveChartAsJPEG(typeChart, chart, width, height);
			
//			Thread uploadThread= new Thread(new UploadThread(typeChart.toString(), "type"));
//			uploadThread.start();
			

		} catch (DBException | WorkPlanDAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/ReportsStatus", method = RequestMethod.GET)
	public void statusReport(Project project) {
		List<Activity> listWithAllActivitiesWithStatusDone;
		List<Activity> listWithAllActivitiesWithStatusInProgress;
		List<Activity> listWithAllActivitiesWithStatusToDo;
		try {
			listWithAllActivitiesWithStatusDone = IActivityDAO.getDAO("db")
					.getAllActivitiesWithStatusInProject(ActivityStatus.Done,
							(Integer) project.getId());
			listWithAllActivitiesWithStatusInProgress = IActivityDAO.getDAO(
					"db").getAllActivitiesWithStatusInProject(
					ActivityStatus.InProgress, project.getId());
			listWithAllActivitiesWithStatusToDo = IActivityDAO.getDAO("db")
					.getAllActivitiesWithStatusInProject(ActivityStatus.ToDo,
							project.getId());

			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("ToDo", listWithAllActivitiesWithStatusToDo.size());
			dataset.setValue("InProgress",
					listWithAllActivitiesWithStatusInProgress.size());
			dataset.setValue("Done", listWithAllActivitiesWithStatusDone.size());
			List<Comparable> keys = dataset.getKeys();
			for (Comparable a : keys) {
				System.out.println(dataset.getValue(a));
				if (dataset.getValue(a) == null
						|| (double) dataset.getValue(a) == 0) {
					dataset.remove(a);
				}
			}
			JFreeChart chart = ChartFactory.createPieChart("Issues' statuses", // chart
					dataset, // data
					true, // include legend
					true, false);
			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
					"{0} - count:{1} -{2}"));
			File statusChart = new File(
					"C:\\FPfiles\\Images\\Reports\\status.jpeg");
			ChartUtilities.saveChartAsJPEG(statusChart, chart, width, height);
			
			
//			Thread uploadThread= new Thread(new UploadThread(statusChart.toString(), "status"));
//			uploadThread.start();
			


		} catch (DBException | WorkPlanDAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/ReportsDaysToDone", method = RequestMethod.GET)
	public void DaysToDoneReport(Project project) {

		final String daysNeeded = "Average number of days issues took to be resloved";
		final String numberOfIssues = "Number of issues sloved on this date";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<LocalDate, SimpleEntry<Integer, Double>> map;
		try {
			map = IActivityDAO
					.getDAO("db")
					.getAllDoneActivitiesInPast30DaysWithDetail(project.getId());

			for (LocalDate date : map.keySet()) {
				dataset.addValue(map.get(date).getValue()
						/ map.get(date).getKey(), daysNeeded, date);
				dataset.addValue(map.get(date).getKey(), numberOfIssues, date);
			}

			JFreeChart barChart = ChartFactory
					.createBarChart(
							"This chart shows the average number of days issues took to be resolved.Issues were resolved in the last 10 days.",
							"", "", dataset, PlotOrientation.HORIZONTAL, true,
							true, false);

			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			File BarChart = new File("C:\\FPfiles\\Images\\Reports\\days.jpeg");
			ChartUtilities.saveChartAsJPEG(BarChart, barChart, width, height);
			
//			Thread uploadThread= new Thread(new UploadThread(BarChart.toString(), "daysDone"));
//			uploadThread.start();
//			
					
		} catch (DBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class UploadThread implements Runnable{
	
	private String path;
	public UploadThread(String path, String key) {
		this.path = path;
		this.key = key;
	}



	private String key;
	
	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	@Override
	public void run() {
//		// Uploading
//		AWSCredentials Credentials = new BasicAWSCredentials(
//				"********************", 
//				"***********************************");
//		
//				AWSUpload s3client = new AWSUpload();
//				
//				s3client.setUploadFileName(path);
//				s3client.setKeyName(key);
//				s3client.uploadfile(Credentials);
		
		
	}
	
}



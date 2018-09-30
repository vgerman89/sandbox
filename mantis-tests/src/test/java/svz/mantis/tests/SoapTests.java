package svz.mantis.tests;

import org.testng.annotations.Test;
import svz.mantis.model.Issue;
import svz.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println("Projects count: " + projects.size());
    for (Project project : projects) {
      System.out.println("Project name: " + project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException  {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test Issue")
            .withDescription("Test Issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
}

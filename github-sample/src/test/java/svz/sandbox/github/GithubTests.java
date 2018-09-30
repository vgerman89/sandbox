package svz.sandbox.github;

import com.jcabi.github.*;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

public class GithubTests {

  @Test
  public void testCommits() {
    Github github = new RtGithub("081621ee70378885cec202c8d2ac9056aa57e29e");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("vgerman89", "sandbox")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(commit);
      //System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}

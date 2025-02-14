package jira;

import org.testng.TestNG;

public class TestNGRunnerClassTest{
static TestNG testrun;
	public static void main(String[] args) {
		 testrun = new TestNG();
		testrun.setTestClasses(new Class[] {LaunchJiraTest.class} );
		testrun.run();
	}
}

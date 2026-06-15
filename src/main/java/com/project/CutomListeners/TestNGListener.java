package com.project.CutomListeners;

import com.project.drivers.UI;
import com.project.drivers.WebDriverProvider;
import com.project.utils.ConstantPaths;
import com.project.utils.FileUtils;
import com.project.utils.Media.ScreenShot;
import com.project.utils.Validation.SoftAssert;
import com.project.utils.dataReader.PropertyReader;
import com.project.utils.logs.logsManager;
import com.project.utils.reports.AllureAttachmentManager;
import com.project.utils.reports.AllureEnv;
import com.project.utils.reports.AllurerReportGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.lang.reflect.Method;

public class TestNGListener implements IExecutionListener, IInvokedMethodListener, ITestListener {

    public void onExecutionStart() {
        logsManager.info("test execution test started");
        cleanTestOutputDirs();
        logsManager.info("old  test output directories are  cleaned");
        createTestOutputDirs();
        logsManager.info("old  test output directories are  created");
        PropertyReader.loadProperties();

        logsManager.info("properties are  loaded");
        AllureEnv.setAllureEnvVariables();
        logsManager.info("allure  ENV  variable are set");


    }
    //C:\Users\hp\OneDrive\Desktop\Web_Auomation_Project\test-output\allure-results

    public void onExecutionFinish() {
        AllurerReportGenerator.copyHistoryToResultFolder();
        AllurerReportGenerator.generateAllureReport(false);
        AllurerReportGenerator.generateAllureReport(true);
        AllurerReportGenerator.openReport(AllurerReportGenerator.renameReport());
        logsManager.info("test is finished");

    }


    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {


    }






    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        if(method.isTestMethod()) {

            Class<?> testClass = testResult.getTestClass().getRealClass();

            if(testClass.isAnnotationPresent(UI.class)) {

                WebDriver driver = null;

                if(testResult.getInstance() instanceof WebDriverProvider provider)
                    driver = provider.getWebDriver();

                switch (testResult.getStatus()) {
                    case ITestResult.SUCCESS ->
                            ScreenShot.captureFullPage(driver, "passed " + testResult.getName());

                    case ITestResult.FAILURE ->
                            ScreenShot.captureFullPage(driver, "failed " + testResult.getName());

                    case ITestResult.SKIP ->
                            ScreenShot.captureFullPage(driver, "skipped " + testResult.getName());
                }
            }

            SoftAssert.assertAll(testResult);
        }
    }



    public void onTestSuccess(ITestResult result) {
        logsManager.info("test case: ",result.getMethod().getMethodName()," passed");
    }

    public void onTestFailure(ITestResult result) {
        logsManager.info("test case: ",result.getMethod().getMethodName()," failed");
    }

    public void onTestSkipped(ITestResult result) {
        logsManager.info("test case: ",result.getMethod().getMethodName()," skipped");
    }

    //  cleaning  and  creating  dirs(logs, screenshots,allure-results)
    private void cleanTestOutputDirs()
    {
        FileUtils.cleanDir(ConstantPaths.RESULTS.toFile());
        FileUtils.cleanDir(ConstantPaths.SCREENSHOTS.toFile());
        FileUtils.cleanDir(ConstantPaths.LOGS.toFile());
    }
    public void createTestOutputDirs()
    {
        FileUtils.createDir(ConstantPaths.SCREENSHOTS.toString());
        FileUtils.createDir(ConstantPaths.FULL_REPORT.toString());
        FileUtils.createDir(ConstantPaths.SINGLE_REPORT.toString());
    }

}

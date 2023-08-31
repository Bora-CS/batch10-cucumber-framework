package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/html-reports/index.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "api_stepdefinitions")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@API or @api")
public class RunAllAPITest {
}

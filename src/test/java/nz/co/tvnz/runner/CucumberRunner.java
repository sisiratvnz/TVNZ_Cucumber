package nz.co.tvnz.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "classpath:features",
        glue = "nz.co.tvnz.stepdefs",
        plugin = {"json:target/report.json"},
        publish = true,
        tags = " @registration or @login or @ghi or @jkl or @xyz"
)
public class CucumberRunner {

}

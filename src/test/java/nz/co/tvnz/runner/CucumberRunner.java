package nz.co.tvnz.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "classpath:features",
        glue = "nz.co.tvnz",
        plugin = {"json:target/report.json"},
        publish = true,
        tags = "@def"
)
public class CucumberRunner {

}

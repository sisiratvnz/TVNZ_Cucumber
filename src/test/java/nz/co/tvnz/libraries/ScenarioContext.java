package nz.co.tvnz.libraries;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ScenarioContext {
    private Map<String,Object> scenarioContext;

    public ScenarioContext() {
        this.scenarioContext = new HashMap<>();
    }

    public Object getScenarioContext(String key) {
        return scenarioContext.get(key);
    }

    public void setScenarioContext(String key, Object value) {
        this.scenarioContext.put(key, value);
    }

    public boolean isContains(String key){
        return this.scenarioContext.containsKey(key);
    }
}

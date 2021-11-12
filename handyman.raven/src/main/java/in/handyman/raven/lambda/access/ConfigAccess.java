package in.handyman.raven.lambda.access;

import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.ResourceConnection;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

@Slf4j
public class ConfigAccess {

    private static final HandymanRepo REPO = new HandymanRepoR2Impl();

    public static ResourceConnection getResourceConfig(final String name) {
        return REPO.getResourceConfig(name);
    }

    public static Map<String, String> getAllConfig(final String pipelineName) {
        return REPO.getAllConfig(pipelineName);
    }

    public static Map<String, String> getCommonConfig() {
        return REPO.getCommonConfig();
    }

    public static Set<String> getPackageAction() {
        return REPO.getPackageAction();
    }

    public static String getLambdaName(final String pipelineName) {
        if (pipelineName != null) {
            if (pipelineName.contains("#")) {
                return pipelineName.substring(0, pipelineName.lastIndexOf("#"));
            }
        }
        return pipelineName;
    }

}

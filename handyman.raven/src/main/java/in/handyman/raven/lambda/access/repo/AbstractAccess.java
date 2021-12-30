package in.handyman.raven.lambda.access.repo;

public abstract class AbstractAccess {

    protected String getLambdaName(final String pipelineName) {
        if (pipelineName != null && pipelineName.contains("#")) {
            return pipelineName.substring(0, pipelineName.lastIndexOf("#"));
        }
        return pipelineName;
    }


}

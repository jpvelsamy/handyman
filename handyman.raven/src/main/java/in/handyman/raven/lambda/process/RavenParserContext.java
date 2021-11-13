package in.handyman.raven.lambda.process;

import in.handyman.raven.compiler.RavenParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RavenParserContext {

    private String processName;

    private List<RavenParser.ActionContext> tryContext;
    private List<RavenParser.ActionContext> catchContext;
    private List<RavenParser.ActionContext> finallyContext;

}

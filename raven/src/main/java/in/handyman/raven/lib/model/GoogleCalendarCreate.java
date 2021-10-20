package in.handyman.raven.lib.model;

import in.handyman.raven.action.ActionContext;
import in.handyman.raven.action.IActionContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Auto Generated By Raven
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@ActionContext(
        actionName = "GoogleCalendarCreate"
)
public class GoogleCalendarCreate implements IActionContext {
    private String name;

    private String account;

    private String privateKey;

    private String ptwelveFile;

    private String project;

    private String impersonatedUser;

    private String dbSrc;

    private String value;

    private Boolean condition = true;
}

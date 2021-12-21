package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lib.model.ExportCsv;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ExportCsvActionTestExecutionAudit {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void execute() throws Exception {
        final ExportCsvAction createTARAction = new ExportCsvAction(ActionExecutionAudit.builder().build(),
                logger, ExportCsv.builder()
                .condition(true)
                .name("CCCU_DNA.dbo.MOCK_DATA")
                .executionSource("${cccu.DNA.db}")
                .stmt("select r.acctnbr, r.RTXNNBR, g.EXTCARDNBR from RTxn as r inner join rtxnagreement as a on a.rtxnnbr = r.RTXNNBR and a.acctnbr = r.ACCTNBR inner join CardAgreement as g on g.AGREENBR = a.agreenbr where r.ORIGPOSTDATE >= dateadd(d, -7, '2021-11-19') and r.CURRRTXNSTATCD = 'C' and r.RTXNTYPCD in ('DDEP', 'DWTH', 'PDEP', 'PWTH')")
                .targetLocation("/workspace/temp/")
                .build());

        createTARAction.execute();

    }
}

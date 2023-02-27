package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.EpisodeOfCoverage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EpisodeOfCoverageActionTest {

    @Test
    public void episodeOfCoverageTest() throws Exception {

        EpisodeOfCoverage  episodeOfCoverage= EpisodeOfCoverage.builder()
                .name("Episode of coverage")
                .resourceConn("intics_agadia_db_conn")
                .originId("INT-1")
                .totalPages("")
                .outputTable("")
                .eocGroupingItem("EOC_ID")
                .filepath("SELECT asset.file_path,sot.origin_id,asset.file_id,sot.paper_no,c.group_id\n" +
                        "\t\t\t             FROM info.source_of_truth sot\n" +
                        "\t\t\t             JOIN info.asset asset ON asset.file_id=sot.preprocessed_file_id\n" +
                        "\t\t\t             JOIN eoc_splitting.eoc_splitting_payload_queue c ON c.origin_id=sot.origin_id\n" +
                        "\t\t\t             WHERE c.origin_id='INT-1'")
                .value("")
                .build();

        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.qr-attribution.url", "http://172.20.0.5:10189/copro/qr-reader");
        EpisodeOfCoverageAction episodeOfCoverageAction=new EpisodeOfCoverageAction(action,log,episodeOfCoverage);
        episodeOfCoverageAction.execute();
    }



}